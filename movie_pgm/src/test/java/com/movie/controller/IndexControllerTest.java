package com.movie.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Date;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.movie.domain.UserBean;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class IndexControllerTest {
	
	UserBean userBean = new UserBean();
	/*
	userBean.setUser_id("muto1002");
	userBean.setName("武藤 雄平");
	userBean.setBirthday(new Date(1995, 10, 2));
	userBean.setPref_cd(12);
	userBean.setPassword("0711");
	*/
	private MockMvc mockMvc;
	@Autowired
	IndexController target;
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(target).build();
	}
	//index
	@Test
	public void getIndexTest() throws Exception  {
		 mockMvc.perform(get("/"))
		 	.andExpect(status().isOk())
		 	.andExpect(view().name("index"));
	}
	//login
	@Test
	public void postLoginSuccessTest() throws Exception {
		mockMvc.perform(post("/login")
				.param("login_user_id","muto1002")
				.param("login_password","0711"))
			//.andExpect(model().attribute("user", userBean))
			.andExpect(view().name("userPage"));
	}
	@Test
	public void postLoginUserErrorTest() throws Exception {
		mockMvc.perform(post("/login")
				.param("login_user_id","muto9999")
				.param("login_password","0711"))
		.andExpect(view().name("index"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("msg2", "ユーザーIDは存在しません"));
	}
	@Test
	public void postLoginPasswordErrorTest() throws Exception {
		mockMvc.perform(post("/login")
				.param("login_user_id","muto1002")
				.param("login_password","9999"))
			.andExpect(view().name("index"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("msg2", "パスワードが一致しません"));
	}
	@Test
	public void postRegisterSuccessTest() throws Exception{
		mockMvc.perform(post("/register")
				.param("register_user_id","naga7111")
				.param("register_password", "4444")
				.param("birthday", "1994-07-06")
				.param("pref_cd", "12")
				.param("register_username", "永井 杏奈"))
			.andExpect(view().name("registerSuccess"))
			.andExpect(status().isOk());
	}
	@Test
	public void postRegisterErrorTest() throws Exception{
		mockMvc.perform(post("/register")
				.param("register_user_id","nagai111")
				.param("register_password", "4444")
				.param("birthday", "1994-07-06")
				.param("pref_cd", "12")
				.param("register_username", "永井 杏奈"))
			.andExpect(view().name("index"))
			.andExpect(model().attribute("msg", "ユーザーIDは使われています"))
			.andExpect(status().isOk());
	}
    
 }


