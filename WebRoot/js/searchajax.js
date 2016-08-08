function searchroom() {

	$.ajax({
		type : 'POST',// 数据发送方式
		url : '/classroommanage/classroomser',// 后台处理程序的页面路径
		data : $("#searchroom").serialize(),
		dataType : 'text',// 返回的数据格式
		error : function(XMLHttpRequest) {
			alert('出错l ,错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			$('body').css("overflow", "hidden");
			$('#cover').css({
				visibility : 'visible',
			});
			$('#thumbnail').css({
				visibility : 'visible',
			});
			if (data == "") {
				$("#caption").html("<h3>无空闲教室</h3>");
			} else {
				$("#caption").html("<h3>空闲教室有:</h3>" + data);
			}
		}

	});
}
function searchtime() {

	$.ajax({
		type : 'POST',// 数据发送方式
		url : '/classroommanage/classroomser',// 后台处理程序的页面路径
		data : $("#searchtime").serialize(),
		dataType : 'text',// 返回的数据格式
		error : function(XMLHttpRequest) {
			alert('出错l ,错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			$('body').css("overflow", "hidden");
			$('#cover').css({
				visibility : 'visible',
			});
			$('#thumbnail').css({
				visibility : 'visible',
			});
			if (data == "") {
				$("#caption").html("<h3>无空闲时段</h3>");
			} else {
				$("#caption").html("<h3>空闲时段有:</h3>" + data);
			}
		}

	});
}
function back() {
	$('body').css("overflow", "visible");
	$('#cover').css({
		visibility : 'hidden',
	});
	$('#thumbnail').css({
		visibility : 'hidden',
	});
}