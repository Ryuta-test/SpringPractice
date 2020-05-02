$(function() {
	$('#move_Home').click(function() {
		// ホームに戻る
		location.href = LOCATION_PATH + 'index';
	});
});

$(function() {
	$('#move_Readme').click(function() {
		location.href = LOCATION_PATH + 'Readme';
	});
});
