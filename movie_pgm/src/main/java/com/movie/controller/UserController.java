package com.movie.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.movie.domain.MovieBean;
import com.movie.domain.MovieTheaterBean;
import com.movie.domain.ReservationInfoDispBean;
import com.movie.domain.SheetBean;
import com.movie.domain.TheaterPageBean;
import com.movie.domain.UserBean;
import com.movie.mapper.UserMapper;
import com.movie.util.Util;

@Controller
@SessionAttributes("scopedTarget.user")
@Scope("session")
public class UserController {
	@Autowired
	UserMapper userMapper;
	@Autowired
	UserBean userBean;

	@ModelAttribute("user")
	public UserBean setUserBean() {
		return userBean;
	}

	// 映画から探す
	@RequestMapping(value = "/searchMovie", method = RequestMethod.POST)
	String searchMovie(Model model) {
		List<MovieBean> movies = userMapper.selectAllMovie();
		model.addAttribute("movies", movies);
		return "resultSearchMovie";
	}

	// 映画検索Ajax
	@RequestMapping(value = "/ajaxMovieSearch", method = RequestMethod.POST)
	@ResponseBody
	List<MovieBean> returnMovieJson(@RequestParam(value = "title", required = false) String m_title,
			@RequestParam(value = "category", required = false) String m_category) {
		List<MovieBean> searchedMovies = userMapper.selectSearchMovie(m_title, m_category);
		return searchedMovies;
	}

	// 上映中映画館一覧画面へ遷移
	@RequestMapping("/searchTheater/{movie_cd}")
	String searchTheater(@PathVariable String movie_cd, Model model) {
		MovieBean movie = userMapper.selectMovie(movie_cd);
		HashMap map = new HashMap();
		map.put("movie_cd", movie_cd);
		List<MovieTheaterBean> theaterList = userMapper.selectRunningTheater(map);

		model.addAttribute("movie", movie);
		model.addAttribute("theaterList", theaterList);
		return "resultSearchRunningTheater";
	}

	// 映画館ページへ遷移
	@RequestMapping("/toTheaterPage/{theater_cd}")
	String toTheaterPage(@PathVariable String theater_cd, Model model) {
		// 日にち情報を取得
		List<Date> weekly = new ArrayList<Date>();
		Date date = new Date();
		weekly.add(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		for (int i = 1; i < 7; i++) {
			cal.add(Calendar.DAY_OF_MONTH, 1);
			weekly.add(cal.getTime());
		}
		model.addAttribute("weekly", weekly);

		List<TheaterPageBean> theaterPageData = userMapper.selectTheaterPageData(theater_cd);
		model.addAttribute("data", theaterPageData);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//System.out.println(sdf.format(theaterPageData.get(0).getMovie_start_time()));
		return "theaterPage";
	}

	// 映画館から探す
	@RequestMapping(value = "/searchMovieTheater", method = RequestMethod.POST)
	String searchFromTheater(Model model) {
		List<MovieTheaterBean> theaterList = userMapper.selectRunningTheater(null);
		model.addAttribute("theaterList", theaterList);
		return "resultSearchMovieTheater";

	}

	// 座席予約画面
	@RequestMapping("/reserveSheet/{show_control_no}")
	String reserveSheet(@PathVariable String show_control_no, Model model) {
		List<SheetBean> sheetBeanList = userMapper.selectSheetList(show_control_no);
		TheaterPageBean data = userMapper.selectSheetPageData(show_control_no);
		// 表示用に二次元配列に変換
		SheetBean[][] sheetList = new SheetBean[9][14];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 14; j++) {
				sheetList[i][j] = sheetBeanList.remove(0);
			}
		}
		model.addAttribute("sheetList", sheetList);
		model.addAttribute("data", data);
		return "reserveSheet";
	}

	// 座席予約(Ajax)
	@Transactional
	@RequestMapping(value = "/ajaxReserveSheet", method = RequestMethod.POST)
	@ResponseBody
	String ajaxReserveSheet(@RequestParam(value = "sheetList", required = true) String sheetList,
			@RequestParam(value = "showControlNo", required = true) String showControlNo) {
		String[] List = Util.toArrayJsonSheetCd(sheetList);
		try {
			for (String sheet : List) {
				SheetBean checkSheet = userMapper.selectCheckSheet(showControlNo, sheet);
				// 更新チェック
				if (checkSheet.getReservation_status().equals("0")) {
					// パラメータ作成
					HashMap map = new HashMap();
					map.put("reservation_cd", Util.makeReservationCd());
					map.put("user_id", userBean.getUser_id());
					map.put("show_control_no", showControlNo);
					map.put("sheet_cd", sheet);
					// 座席情報を選択済みに
					userMapper.updateSheetStatus(showControlNo, sheet);
					// 予約情報を作成
					userMapper.insertReservationInfo(map);
				} else {
					throw new Exception();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "エラーが発生しました。";
		}
		return "予約が完了しました";
	}

	// 予約の確認、取り消し画面へ
	@RequestMapping(value = "/doCheckAndDelete", method = RequestMethod.POST)
	String doCheckAndDelete(Model model) {
		List<ReservationInfoDispBean> reservationList = userMapper.selectReservationInfo(userBean.getUser_id());
		model.addAttribute("reservationList", reservationList);
		return "reservationConfirm";
	}

	// 予約キャンセル
	@Transactional
	@RequestMapping(value = "/ajaxCancel", method = RequestMethod.POST)
	@ResponseBody
	String ajaxCancel(@RequestParam(value = "reservationNo", required = true) String reservationNo,
			@RequestParam(value = "showControlNo", required = true) String showControlNo,
			@RequestParam(value = "sheetCd", required = true) String sheetCd) {
		try {
			if (!userMapper.updateReservationCancel(reservationNo)) {
				throw new Exception();
			}
			if (!userMapper.updateSheetCancel(showControlNo, sheetCd)) {
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "エラーが発生しました";
		}
		return "キャンセルが完了しました";

	}

	// ユーザーページへ戻る
	@RequestMapping(value = "/toUserPage", method = RequestMethod.POST)
	String toUserPage() {
		return "userPage";
	}

	// ログアウト
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	String logout(SessionStatus sessionStatus) {
		// sessionを破棄
		sessionStatus.setComplete();
		return "index";

	}
}
