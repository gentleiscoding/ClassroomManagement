$(function() {
	$.ajax({
		type : 'POST',// 数据发送方式
		url : '/classroommanage/classroomser',
		data : 'state=judgelogin&identity=wuye',
		dataType : 'text',
		error : function(XMLHttpRequest) {
			alert('错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			if (data == "no") {
				alert("请用物业用户登录，嘻嘻");
				 window.location.href="/classroommanage/html/home.html";
			} else {
				$("#sayhello").html("<a href=\"#\" id=\"nihao\"><img src=\"../img/logo1.png\" alt=\"\" />你好，"+data+"</a>");
			}
		}
	});
});