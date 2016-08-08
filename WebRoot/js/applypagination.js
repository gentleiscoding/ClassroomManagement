
$(function() {
	//获取页码
	$.ajax({
		type : 'get',// 数据发送方式
		url : '/classroommanage/classroomser',
		data : 'state=showapplypagenumber',
		dataType : 'text',
		error : function(XMLHttpRequest) {
			alert('错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			$("#pagination").html(data);
		}
	});
	
	
	//显示第一页内容
	$.ajax({
		type : 'post',// 数据发送方式
		url : '/classroommanage/classroomser',
		data : 'state=showapply1',
		dataType : 'text',
		error : function(XMLHttpRequest) {
			alert('错误信息:' + XMLHttpRequest);
		},
		success : function(data) {
			$("#applycontent").html(data);
		}
	});
	
});

