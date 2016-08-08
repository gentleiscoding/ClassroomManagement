$("#applybutton").click(function() {
	$.ajax({
		type : 'POST',// 数据发送方式
		url : '/classroommanage/classroomser',// 后台处理程序的页面路径
		data : $("#applyform").serialize(),
		dataType : 'text',// 返回的数据格式
		error : function(XMLHttpRequest) {
			alert('出错l ,错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			if (data == "申请成功") {
				alert(data);
				history.go(0);
			} else {
				alert(data);
			}
		}
	});
});

// 显示第几页的申请
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
			alert(data);
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
function showapply(page) {

	$.ajax({
		type : 'POST',// 数据发送方式
		url : '/classroommanage/classroomser',// 后台处理程序的页面路径
		data : 'state=showapply&page=' + page,
		dataType : 'text',// 返回的数据格式
		error : function(XMLHttpRequest) {
			alert('出错l ,错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			$("#applycontent").html(data);
		}

	});
}

function newer() {

	$.ajax({
		type : 'POST',// 数据发送方式
		url : '/classroommanage/classroomser',// 后台处理程序的页面路径
		data : 'state=newerapply',
		dataType : 'text',// 返回的数据格式
		error : function(XMLHttpRequest) {
			alert('出错l ,错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			if (data == "") {
			} else {
				$("#applycontent").html(data);
			}
		}

	});
}

function older() {

	$.ajax({
		type : 'POST',// 数据发送方式
		url : '/classroommanage/classroomser',// 后台处理程序的页面路径
		data : 'state=olderapply',
		dataType : 'text',// 返回的数据格式
		error : function(XMLHttpRequest) {
			alert('出错l ,错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			alert(data);
			if (data == "") {
			} else {
				$("#applycontent").html(data);
			}
		}
	});
}