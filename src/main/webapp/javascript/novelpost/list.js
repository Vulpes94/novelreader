/**
 * 
 */
function readCheck(n_post_num, root) {
	var url = root + "/novelpost/read.do?n_post_num=" + n_post_num;
	//	alert(url);

	location.href = url;
}
function deleteCheck(n_POST_NUM, root, n_num) {
	var url = root + "/novelpost/delete.do?n_post_num=" + n_POST_NUM + "&n_num=" + n_num;
	//	alert(url);

	var value = confirm("정말로 삭제 하겠습니까?");
	if (value == true) {
		location.href = url;
	} else {
		alert("삭제 되지 않았습니다.");
	}
}

function updateCheck(n_POST_NUM, root, n_num) {
	var url = root + "/novelpost/update.do?n_post_num=" + n_POST_NUM + "&n_num=" + n_num;
	//	alert(url);

	location.href = url;
}

function goProfile(root, nickname) {
	location.href = root + "/member/profile.do?nickname=" + nickname;
	//alert(url);
}
