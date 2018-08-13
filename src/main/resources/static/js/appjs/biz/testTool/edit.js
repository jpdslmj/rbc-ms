$().ready(function() {
	if(gangmaster){
		$("#testField").attr("disabled","disabled");
	}
	if(inspector){
        $("#testField").attr("disabled","disabled");
        $("#gangmasterName").attr("disabled","disabled");
        $("#gangmasterAudit").attr("disabled","disabled");
	}
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update("update");
	}
});
function update(flag) {
    if(gangmaster){
        var taskpass= $('#taskPass').val();
        var gangmasterAudit= $('#gangmasterAudit').val();
        if(gangmasterAudit==null||gangmasterAudit==''){
            layer.alert("工长审核意见不能为空！");
            return;
        }
        if(taskpass!=gangmasterAudit){
            layer.alert("工长签批意见与操作不一致！");
            return;
        }
    }
    if(inspector){
        var taskpass= $('#taskPass').val();
        var inspectorAudit= $('#inspectorAudit').val();
        if(inspectorAudit==null||inspectorAudit==''){
            layer.alert("质检员审核意见不能为空！");
            return;
        }
        if(taskpass!=inspectorAudit){
            layer.alert("质检员签批意见与操作不一致！");
            return;
        }
    }
	$.ajax({
		cache : true,
		type : "POST",
		url : "/biz/testTool/"+flag,
		data : $('#testToolForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
                parent.reLoad2();
                parent.reLoad3();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
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
				required : icon + "请输入名字"
			}
		}
	})
}