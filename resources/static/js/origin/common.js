/**
 * ページ読み込み時処理
 */
$(function() {
	// CSRF対策のトークンをリクエストヘッダーにセット
	// Spring Securityを設定すると、POST送信をするために、この設定が必要になる
	let token = $("meta[name='_csrf']").attr("content");
	let header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
});

/**
 * ajax処理(GETリクエスト)
 */
$(function() {
	$('#ajaxGet').click(function() {

		let ajaxGetURL = LOCATION_PATH + 'ajaxGet'

		const dataJson = {
			'test' : "ajaxGetReqTest"
		};

		$.ajax({
			url : ajaxGetURL,
			type : 'GET',
			data : dataJson,
			contentType : 'application/json',
			dataType : 'json',
			async : true,
			success : function(response) {
				alert(response.message);
			},
			error : function(xhr, status, error) {
				alert(xhr.responseText);
			}

		});
	});
});

/**
 * ajax処理(POSTリクエスト)
 */
$(function() {

	$('#ajaxPost').click(function() {
		let dataStr;
		let ajaxPostURL = LOCATION_PATH + 'ajaxPost'

		const dataJson = {
			'test' : "ajaxPostReqTest"
		};

		// オブジェクトをjson文字列に変換
		dataStr = JSON.stringify(dataJson);

		$.ajax({
			url : ajaxPostURL,
			type : 'POST',
			data : dataStr,
			contentType : 'application/json',
			dataType : 'json',
			async : true,
			success : function(response) {
				alert(response.message);
			},
			error : function(xhr, status, error) {
				alert(xhr.responseText);
			}
		});
	});
});

/**
 * ajax完了時
 */
$(document).ajaxStop(function() {
	// ajax完了時の処理
});

/**
 * ajax設定
 */
$.ajaxSetup({
	// タイムアウト
	// timeout : 10000,

	// ステータスコード
	statusCode : {
		404 : function() {
			window.location.href = LOCATION_PATH + "login";
		},
		500 : function() {
			window.location.href = LOCATION_PATH + "login";
		}
	}
});