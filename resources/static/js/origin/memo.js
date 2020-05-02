// 最大メモ数
const MAX_MEMO_SIZE = 3;

/**
 * メモ初期表示
 * @returns
 */
$(function() {
	$('#max_memo_size').text(MAX_MEMO_SIZE + "個まで");
});

/**
 * メモをインサート
 * 
 * @returns
 */
$(function() {
	$('#memo_insert').click(function() {
		let dataStr;
		let ajaxPostURL = LOCATION_PATH + 'memo/insert';

		// メモのタイトル
		let title = $('#memo_title').val();

		if (title.length == 0) {
			// タイトルが入力されていない場合
			alert("タイトルを入力してください")
			return;
		}

		if ($('#memo_list_size').val() >= MAX_MEMO_SIZE) {
			// メモの数
			alert(MAX_MEMO_SIZE + "個以上のメモを追加できません");
			return;
		}

		const dataJson = {
			// テキストエリアに入力した内容を取得
			'memo' : $('#memo_text').val(),
			'title' : title
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
				// ajax成功後、リロードして画面に反映
				location.reload();
			},
			error : function(xhr, status, error) {
				alert(xhr.responseText);
			}
		});
	});
});

/**
 * メモをアップデート
 * 
 * @returns
 */
$(function() {

	// メモのタイトル格納用変数
	let title;

	// モーダル表示前
	$('#memo_modify').on('show.bs.modal', function(event) {
		let button = $(event.relatedTarget); // モーダルを呼び出すときに使われたボタンを取得
		title = button.data('title'); // data-title の値を取得
		let body = button.data('body'); // data-body の値を取得

		$(this).find('.modal-title').val(title); // モーダルのタイトルに値を表示
		$(this).find('#memo_up_text').val(body); // モーダルbodyにセット
	});

	$('#memo_update').click(function() {
		let dataStr;
		let ajaxPostURL = LOCATION_PATH + 'memo/update'

		const dataJson = {
			// 「メモを更新」ボタンのvalue（付箋title）を取得
			'title' : title,
			// テキストエリアに入力した内容を取得
			'memo' : $('#memo_up_text').val()
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
				// ajax成功後、リロードして画面に反映
				location.reload();
			},
			error : function(xhr, status, error) {
				alert(xhr.responseText);
			}
		});
	});
});

/**
 * メモを削除
 * 
 * @returns
 */
$(function() {
	// 前方一致
	$('[id^=memo_delete]').click(function() {
		let dataStr;
		let ajaxPostURL = LOCATION_PATH + 'memo/delete'

		const dataJson = {
			// 「メモを削除」ボタンのvalue（付箋title）を取得
			'title' : $(this).val()
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
				// ajax成功後、リロードして画面に反映
				location.reload();
			},
			error : function(xhr, status, error) {
				alert(xhr.responseText);
			}
		});
	});
});
