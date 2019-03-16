/**
 * 入力チェック
 */
//必須チェック
function checkRequired(id){
	var val = document.getElementById(id).value;
	if(val === null || val === undefined || val === ""){
		return false
	}
	return true
}
//文字列長チェック
function checkTextLength(id,maxLength){
	var val = document.getElementById(id).value;
	if(val.length > maxLength){
		return false
	}
	return true
}
//入力規制チェック
function checkValidity(id,pattern){
	var val = document.getElementById(id).value;
	if(val.match(pattern) === null){
		return false
	}
	return true
}

//登録ボタン押下時のチェック
function registerCheck(){
	//必須チェック
	if(!checkRequired("register_username")){
		alert("名前を入力してください")
		return false
	}
	if(!checkRequired("birthday")){
		alert("生年月日を入力してください")
		return false
	}
	if(!checkRequired("pref_cd")){
		alert("都道府県を入力してください")
		return false
	}
	if(!checkRequired("register_user_id")){
		alert("ユーザIDを入力してください")
		return false
	}
	if(!checkRequired("register_password")){
		alert("パスワードを入力してください")
		return false
	}
	//個別チェック
	//名前
	if(!checkTextLength("register_username",20)){
		alert("名前が長すぎます")
		return false
	}
	//ユーザID
	if(!checkValidity("register_user_id",/^[0-9A-Za-z]{8}$/)){
		alert("ユーザIDは半角英数8文字以内で入力してください")
		return false
	}
	//パスワード
	if(!checkValidity("register_password",/^[0-9]{4}$/)){
		alert("パスワードは半角数字4文字で入力して下さい")
		return false
	}
	
	return true
}
//ログインボタン押下時のチェック
function loginCheck(){
	if(!checkRequired("login_user_id")){
		alert("ユーザIDを入力してください")
		return false
	}
	if(!checkRequired("login_password")){
		alert("パスワードを入力してください")
		return false
	}
	//ユーザID
	if(!checkValidity("login_user_id",/^[0-9A-Za-z]{8}$/)){
		alert("ユーザIDは半角英数8文字以内で入力してください")
		return false
	}
	//パスワード
	if(!checkValidity("login_password",/^[0-9]{4}$/)){
		alert("パスワードは半角数字4文字で入力して下さい")
		return false
	}
	return true
}