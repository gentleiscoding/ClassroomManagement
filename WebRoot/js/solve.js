// 解决
function solve(fid) {
	$.ajax({
		type : 'POST',// 数据发送方式
		url : '/classroommanage/classroomser',// 后台处理程序的页面路径
		data : 'state=solve&fid=' + fid,
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