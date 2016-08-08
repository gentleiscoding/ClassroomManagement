$(function() {
	// 显示第一页un内容
	$.ajax({
		type : 'post',// 数据发送方式
		url : '/classroommanage/classroomser',
		data : 'state=showfeedbackun1',
		dataType : 'text',
		error : function(XMLHttpRequest) {
			alert('错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			$("#unshow").html(data);
		}
	});
});

$(function() {
	// 显示第一页ed内容
	$.ajax({
		type : 'post',// 数据发送方式
		url : '/classroommanage/classroomser',
		data : 'state=edfeedbackshow1',
		dataType : 'text',
		error : function(XMLHttpRequest) {
			alert('错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			$("#edshow").html(data);
		}
	});
});

function examined() {
	$('#examined').css({
		visibility : 'visible',
	});
	$('#unexamined').css({
		visibility : 'hidden',
	});
}

function umexamined() {
	$('#unexamined').css({
		visibility : 'visible',
	});
	$('#examined').css({
		visibility : 'hidden',
	});
}

// 待审核的下一页
function newerfeedbackun() {
	$.ajax({
		type : 'POST',// 数据发送方式
		url : '/classroommanage/classroomser',// 后台处理程序的页面路径
		data : 'state=newerfeedbackun',
		dataType : 'text',// 返回的数据格式
		error : function(XMLHttpRequest) {
			alert('出错l ,错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			if (data == "") {
			} else {
				$("#uncontent").html(data);
			}
		}

	});
}
// 待审核的上一页
function olderfeedbackun() {
	$.ajax({
		type : 'POST',// 数据发送方式
		url : '/classroommanage/classroomser',// 后台处理程序的页面路径
		data : 'state=olderfeedbackun',
		dataType : 'text',// 返回的数据格式
		error : function(XMLHttpRequest) {
			alert('出错l ,错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			if (data == "") {
			} else {
				$("#uncontent").html(data);
			}
		}
	});
}

// 已审核的下一页
function newerfeedbacked() {

	$.ajax({
		type : 'POST',// 数据发送方式
		url : '/classroommanage/classroomser',// 后台处理程序的页面路径
		data : 'state=newered',
		dataType : 'text',// 返回的数据格式
		error : function(XMLHttpRequest) {
			alert('出错l ,错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			if (data == "") {
			} else {
				$("#edcontent").html(data);
			}
		}

	});
}
// 已审核的上一页
function olderfeedbacked() {
	$.ajax({
		type : 'POST',// 数据发送方式
		url : '/classroommanage/classroomser',// 后台处理程序的页面路径
		data : 'state=olderfeedbacked',
		dataType : 'text',// 返回的数据格式
		error : function(XMLHttpRequest) {
			alert('出错l ,错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			if (data == "") {
			} else {
				$("#edcontent").html(data);
			}
		}
	});
}
