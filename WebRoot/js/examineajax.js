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
function newerun() {

	$.ajax({
		type : 'POST',// 数据发送方式
		url : '/classroommanage/classroomser',// 后台处理程序的页面路径
		data : 'state=newerun',
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
function olderun() {
	$.ajax({
		type : 'POST',// 数据发送方式
		url : '/classroommanage/classroomser',// 后台处理程序的页面路径
		data : 'state=olderun',
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
function newered() {

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
function oldered() {
	$.ajax({
		type : 'POST',// 数据发送方式
		url : '/classroommanage/classroomser',// 后台处理程序的页面路径
		data : 'state=oldered',
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
// 待审核的某一页
function showun(unpage) {

	$.ajax({
		type : 'POST',// 数据发送方式
		url : '/classroommanage/classroomser',// 后台处理程序的页面路径
		data : 'state=showun&unpage=' + unpage,
		dataType : 'text',// 返回的数据格式
		error : function(XMLHttpRequest) {
			alert('出错l ,错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			$("#uncontent").html(data);
		}

	});
}
// 已审核的某一页
function showed(edpage) {

	$.ajax({
		type : 'POST',// 数据发送方式
		url : '/classroommanage/classroomser',// 后台处理程序的页面路径
		data : 'state=showed&edpage=' + edpage,
		dataType : 'text',// 返回的数据格式
		error : function(XMLHttpRequest) {
			alert('出错l ,错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			$("#edcontent").html(data);
		}

	});
}
//通过
function pass(applyid) {
	$.ajax({
		type : 'POST',// 数据发送方式
		url : '/classroommanage/classroomser',// 后台处理程序的页面路径
		data : 'state=pass&applyid=' + applyid,
		dataType : 'text',// 返回的数据格式
		error : function(XMLHttpRequest) {
			alert('出错l ,错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			if (data == "操作成功") {
				history.go(0);
			} else {
				alert(data);
			}
		}

	});
}

//不通过
function fail(applyid) {
	$.ajax({
		type : 'POST',// 数据发送方式
		url : '/classroommanage/classroomser',// 后台处理程序的页面路径
		data : 'state=fail&applyid=' + applyid,
		dataType : 'text',// 返回的数据格式
		error : function(XMLHttpRequest) {
			alert('出错l ,错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			if (data == "操作成功") {
				history.go(0);
			} else {
				alert(data);
			}
		}

	});
}