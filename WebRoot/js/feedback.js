$("#feedbckbutton").click(function() {
	$.ajax({
		type : 'POST',// 数据发送方式
		url : '/classroommanage/classroomser',// 后台处理程序的页面路径
		data : $("#feedbackform").serialize(),
		dataType : 'text',// 返回的数据格式
		error : function(XMLHttpRequest) {
			alert('出错l ,错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			if (data == "反馈成功") {
				alert(data);
				window.close();
			} else {
				alert(data);	
			}
		}
	});
});
