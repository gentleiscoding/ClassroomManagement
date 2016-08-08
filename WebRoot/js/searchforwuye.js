function searchwuye() {

	$.ajax({
		type : 'POST',// 数据发送方式
		url : '/classroommanage/classroomser',// 后台处理程序的页面路径
		data : $("#searchwuyeform").serialize(),
		dataType : 'text',// 返回的数据格式
		error : function(XMLHttpRequest) {
			alert('出错l ,错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			$("#douzailimian").html(data);
		}
	});
}

function wuyesearcholder() {

	$.ajax({
		type : 'POST',// 数据发送方式
		url : '/classroommanage/classroomser',// 后台处理程序的页面路径
		data : "state=wuyesearcholder",
		dataType : 'text',// 返回的数据格式
		error : function(XMLHttpRequest) {
			alert('出错l ,错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			if (data == "") {

			} else {
				$("#douzailimian").html(data);
			}
		}
	});
}

function wuyesearchnewer() {

	$.ajax({
		type : 'POST',// 数据发送方式
		url : '/classroommanage/classroomser',// 后台处理程序的页面路径
		data : "state=wuyesearchnewer",
		dataType : 'text',// 返回的数据格式
		error : function(XMLHttpRequest) {
			alert('出错l ,错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			if (data == "") {

			} else {
				$("#douzailimian").html(data);
			}
		}
	});
}