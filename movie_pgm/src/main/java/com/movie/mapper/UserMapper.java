package com.movie.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.movie.domain.MovieBean;
import com.movie.domain.MovieTheaterBean;
import com.movie.domain.ReservationInfoDispBean;
import com.movie.domain.SheetBean;
import com.movie.domain.TheaterPageBean;
import com.movie.domain.UserBean;

@Mapper
public interface UserMapper {
	//ユーザ情報取得
	UserBean selectUser(String user_id);
	//ユーザ情報登録
	boolean insertUser(String user_id,String name,String birthday,int pref_cd,String password);
	//映画一覧取得
	List<MovieBean> selectAllMovie();
	//映画取得
	MovieBean selectMovie(String movie_cd);
	//映画検索
	List<MovieBean> selectSearchMovie(String m_title,String m_category);
	//映画館一覧取得
	List<MovieTheaterBean> selectRunningTheater(HashMap map);
	//映画館ページ情報取得
	List<TheaterPageBean> selectTheaterPageData(String theater_cd);
	//座席予約ページ情報取得
	TheaterPageBean selectSheetPageData(String show_control_no);
	//座席情報取得
	List<SheetBean> selectSheetList(String show_control_no);
	//座席更新チェック
	SheetBean selectCheckSheet(String show_control_no,String sheet_cd);
	//座席情報書き換え(予約)
	boolean updateSheetStatus(String show_control_no,String sheet_cd);
	//予約情報作成
	boolean insertReservationInfo(HashMap map);
	//予約情報取得
	List<ReservationInfoDispBean> selectReservationInfo(String user_id);
	//予約キャンセル
	boolean updateReservationCancel(String reservationNo);
	//座席情報書き換え(キャンセル)
	boolean updateSheetCancel(String showControlNo,String sheetCd);
}
