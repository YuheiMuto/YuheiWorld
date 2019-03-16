package com.movie.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.movie.domain.UserBean;
import com.movie.mapper.UserMapper;

@Controller
@RequestMapping(value = "/")
@Scope("session")
public class IndexController {
	@Autowired
	UserMapper userMapper;
	@Autowired
	UserBean userBean;
	@RequestMapping(name = "/", method = RequestMethod.GET)
	String index() {
		return "index";
	}
	//登録ボタン押下
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	ModelAndView registration(
		@RequestParam(value="register_username",required=false)String userName,
		@RequestParam(value="birthday",required=false)String birthday,
		@RequestParam(value="pref_cd",required=false)int pref_cd,
		@RequestParam(value="register_user_id",required=false)String userId,
		@RequestParam(value="register_password",required=false)String password,
		ModelAndView mav ) {
		boolean isSuccess = false;
		UserBean userBean = userMapper.selectUser(userId);
		//IDが登録済みかチェック
		if(userBean == null) {
			isSuccess = (boolean) userMapper.insertUser(userId, userName, birthday, pref_cd, password);
		}
		//成功
		if(isSuccess) {
			mav.setViewName("registerSuccess");
		}else {
			mav.addObject("msg","ユーザーIDは使われています");
			mav.setViewName("index");
		}
		return mav;
		
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	ModelAndView login(
		@RequestParam(value="login_user_id",required=false)String user_id,
		@RequestParam(value="login_password",required=false)String password,
		ModelAndView mav ) {
		UserBean temp = userMapper.selectUser(user_id);
		
		
		//logincheck
		if(temp == null) {
			mav.addObject("msg2","ユーザーIDは存在しません");
			mav.setViewName("index");
		}else if(!(password.equals(temp.getPassword()))) {
			mav.addObject("msg2","パスワードが一致しません");
			mav.setViewName("index");
		}else {
			//sessionスコープに格納
			userBean.setName(temp.getName());
			userBean.setBirthday(temp.getBirthday());
			userBean.setPref_cd(temp.getPref_cd());
			userBean.setUser_id(temp.getUser_id());
			userBean.setPassword(temp.getPassword());
			mav.addObject("user",userBean);
			mav.setViewName("userPage");
		}
		return mav;
	}
	@RequestMapping(value = "/toTopPage", method = RequestMethod.GET)
	String toTopPage() {
		return "/index";
	}
}
