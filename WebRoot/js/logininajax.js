$(function() {
	$("#loginbutton").click(function() {
		$('#loginform').ajaxSubmit({
			type : 'POST',// 数据发送方式
			url : '/classroommanage/classroomser',// 后台处理程序的页面路径
			data : $('#loginform').serialize(),
			dataType : 'text',// 返回的数据格式
			error : function(XMLHttpRequest) {
				alert('输入错误，请重新输入');
			},
			success : function(data) {
				if(data=="student"){
					 window.location.href="/classroommanage/html/homeforstudent.html";
				}else if(data=="admin"){
					 window.location.href="/classroommanage/html/homeforadmin.html";
				}else if(data=="wuye"){
					 window.location.href="/classroommanage/html/homeforwuye.html";
				}else{
				$("#error").html("<p>"+data+"</p>");}
			}

		});
	});
});