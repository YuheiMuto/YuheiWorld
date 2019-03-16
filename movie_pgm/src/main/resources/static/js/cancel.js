/**
 * キャンセル処理Ajax
 */
var reservationNo;
var showControlNo;
var sheetCd;


// キャンセルボタン押下
$('.confirmCancel').click(function(event) {
	//event.preventDefault();
	var list = event.target.name.split(" ");
	showControlNo = list[0];
	reservationNo = list[1];
	sheetCd = list[2];

	var a = $(".confirmCancel").parent().next().children();
	console.log(a);	
	setTimeout(function() {
		$("#modal-area").fadeIn("slow");
		$("#contents-area").fadeIn("slow");
	}, 100);
});
// 閉じるボタン
$('#close').click(function(){
	$("#contents-area").hide();
	$("#modal-area").hide();
});
$("#cancel").click(function(){	
	$('#msg').html("<progress  max='100' value='0'> 0% </progress>")
	$.ajax({
		type : "POST",
		url  : "/ajaxCancel",
		dataType : "text",
		data:{
			"reservationNo" :reservationNo ,
			"showControlNo" : showControlNo,
			"sheetCd":sheetCd
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
	})	
});

//戻るボタン押下(ユーザーページに移動)
$(document).on('click','#back_btn',function(){
	 $("#toUserPage").submit();
});