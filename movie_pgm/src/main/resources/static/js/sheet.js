/**
 * 座席予約ページ
 */
var GREEN_IMG = "http://localhost:8080/img/green.jpg";
var WHITE_IMG = "http://localhost:8080/img/white.jpg";
var BLACK_IMG = "http://localhost:8080/img/black.jpg";
var GREEN_IMG_TAG = "/img/green.jpg";
var WHITE_IMG_TAG = "/img/white.jpg";
var BLACK_IMG_TAG = "/img/black.jpg";

var reserveCount = 0;
var reservationSheetList = new Array();

// 席選択時処理
$("td").click(function(e) {
	selestSheet(e, this.id)
	setReservationList(e)
});

// 画像切り替え処理
function selestSheet(e, id) {
	if (e.target.src == GREEN_IMG) {
		if (reserveCount < 5) {
			e.target.src = WHITE_IMG;
			reserveCount++;
			reservationSheetList.unshift(id);
		} else {
			alert("座席は1度に５席まで予約できます")
		}
	} else if (e.target.src == WHITE_IMG) {
		reserveCount--;
		e.target.src = GREEN_IMG;
		reservationSheetList.splice(reservationSheetList.indexOf(id), 1);
	}
}
// 座席選択処理
function setReservationList(e) {
	var html = "\<span>";
	reservationSheetList
			.forEach(function(id) {
				html += ("\<span>\<img src='" + WHITE_IMG_TAG
						+ "'><input type='text' value='" + id + "' size='2' readonly='readonly' class='form-control'>\</span>");
			});
	html += "</span>";
	console.log(html);
	$('.reservationList').html(html);
}
// 登録確認ボタン押下
$('#confirmReservation').click(function() {
	setTimeout(function() {
		$("#modal-area").fadeIn("slow");
		$("#contents-area").fadeIn("slow");
	}, 100);
});
//閉じるボタン
$('#close').click(function(){
	$("#contents-area").hide();
	$("#modal-area").hide();
});
//予約ボタン押下（Ajax通信）
$("#reserve").click(function(){
	$('#msg').html("<progress  max='100' value='0'> 0% </progress>")
	$.ajax({
		type : "POST",
		url  : "/ajaxReserveSheet",
		dataType : "text",
		data:{
			"sheetList" :JSON.stringify(reservationSheetList) ,
			"showControlNo" : $("#showControlNo").val(),
		}
	}).done(function(data){
		var html = "<progress  max='100' value='100'> 100% </progress>";
		html += ("<h2>" + data + "</h2>");
		$('#msg').html(html)
		var button = "<button type='button' class='btn btn-success' id='back_btn'>戻る</button>";
		$('#button-area').html(button);
	}).fail(function(data){
		var html = "<progress  max='100' value='0'> 0% </progress>";
		html += ("<h2>" + data + "</h2>");
		var button = "<button type='button' class='btn btn-success' id='back_btn' >戻る</button>";
		$('#button-area').html(button);
	});
});
//戻るボタン押下(ユーザーページに移動)
$(document).on('click','#back_btn',function(){
	 $("#toUserPage").submit();
});

