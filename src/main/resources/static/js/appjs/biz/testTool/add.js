$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save("save");
	}
});
function save(flag) {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/biz/testTool/"+flag,
		data : $('#testToolForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			//parent.layer.alert("Connection error");
            layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
/*				parent.layer.msg("操作成功");
				parent.reLoad();
                parent.reLoad2();
                parent.reLoad3();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);*/
                layer.msg('操作成功',{time: 1000 },function () {
                    location.href="/biz/testTool";
                });

			} else {
				//parent.layer.alert(data.msg);
                layer.alert(data.msg);
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#testToolForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			}
		}
	})
}