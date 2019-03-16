package com.movie.domain;

import java.util.Date;

public class TheaterPageBean {
	private int show_control_no;
	private int movie_theater_cd;
	private int screen_cd;
	private Date movie_start_time;
	private Date movie_end_time;
	private String movie_cd;
	private String movie_theater_name;
	private String movie_title;
	private String screen_name;
	public int getShow_control_no() {
		return show_control_no;
	}
	public void setShow_control_no(int show_control_no) {
		this.show_control_no = show_control_no;
	}
	public int getMovie_theater_cd() {
		return movie_theater_cd;
	}
	public void setMovie_theater_cd(int movie_theater_cd) {
		this.movie_theater_cd = movie_theater_cd;
	}
	public int getScreen_cd() {
		return screen_cd;
	}
	public void setScreen_cd(int screen_cd) {
		this.screen_cd = screen_cd;
	}
	public Date getMovie_start_time() {
		return movie_start_time;
	}
	public void setMovie_start_time(Date movie_start_time) {
		this.movie_start_time = movie_start_time;
	}
	public Date getMovie_end_time() {
		return movie_end_time;
	}
	public void setMovie_end_time(Date movie_end_time) {
		this.movie_end_time = movie_end_time;
	}
	public String getMovie_cd() {
		return movie_cd;
	}
	public void setMovie_cd(String movie_cd) {
		this.movie_cd = movie_cd;
	}
	public String getMovie_theater_name() {
		return movie_theater_name;
	}
	public void setMovie_theater_name(String movie_theater_name) {
		this.movie_theater_name = movie_theater_name;
	}
	public String getMovie_title() {
		return movie_title;
	}
	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}
	public String getScreen_name() {
		return screen_name;
	}
	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}
	
	
}
