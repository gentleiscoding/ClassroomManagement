$(function() {
	// 获取unexamined页码
	$.ajax({
		type : 'get',// 数据发送方式
		url : '/classroommanage/classroomser',
		data : 'state=showunpagenumber',
		dataType : 'text',
		error : function(XMLHttpRequest) {
			alert('错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			$("#unpagination").html(data);
		}
	});

	// 获取examined页码
	$.ajax({
		type : 'get',// 数据发送方式
		url : '/classroommanage/classroomser',
		data : 'state=showedpagenumber',
		dataType : 'text',
		error : function(XMLHttpRequest) {
			alert('错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			$("#edpagination").html(data);
		}
	});

	// 显示un第一页内容
	$.ajax({
		type : 'post',// 数据发送方式
		url : '/classroommanage/classroomser',
		data : 'state=showun1',
		dataType : 'text',
		error : function(XMLHttpRequest) {
			alert('错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			$("#uncontent").html(data);
		}
	});
	// 显示ed第一页内容
	$.ajax({
		type : 'post',// 数据发送方式
		url : '/classroommanage/classroomser',
		data : 'state=showed1',
		dataType : 'text',
		error : function(XMLHttpRequest) {
			alert('错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			$("#edcontent").html(data);
		}
	});
});