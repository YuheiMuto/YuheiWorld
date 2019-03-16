package com.movie.domain;

import java.util.Date;

public class ReservationInfoDispBean {
	private int reservation_no;
	private String reservation_cd;
	private String user_id;
	private String show_control_no;
	private String sheet_cd;
	private String movie_title;
	private String movie_theater_name;
	private String screen_name;
	private Date movie_start_time;
	private Date movie_end_time;
	private String reservatioin_status;
	public int getReservation_no() {
		return reservation_no;
	}
	public void setReservation_no(int reservation_no) {
		this.reservation_no = reservation_no;
	}
	public String getReservation_cd() {
		return reservation_cd;
	}
	public void setReservation_cd(String reservation_cd) {
		this.reservation_cd = reservation_cd;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getShow_control_no() {
		return show_control_no;
	}
	public void setShow_control_no(String show_control_no) {
		this.show_control_no = show_control_no;
	}
	public String getSheet_cd() {
		return sheet_cd;
	}
	public void setSheet_cd(String sheet_cd) {
		this.sheet_cd = sheet_cd;
	}
	public String getMovie_title() {
		return movie_title;
	}
	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}
	public String getMovie_theater_name() {
		return movie_theater_name;
	}
	public void setMovie_theater_name(String movie_theater_name) {
		this.movie_theater_name = movie_theater_name;
	}
	public String getScreen_name() {
		return screen_name;
	}
	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
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
	public String getReservatioin_status() {
		return reservatioin_status;
	}
	public void setReservatioin_status(String reservatioin_status) {
		this.reservatioin_status = reservatioin_status;
	}
	
	
}
