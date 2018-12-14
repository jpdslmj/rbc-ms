$().ready(function() {
	if(gangmaster){
		$("#realNum").attr("disabled",true);
        $("#planNum").attr("disabled",false);
	}
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
		url : "/biz/taskInfo/" + flag,
		data : $('#taskInfoForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			//parent.layer.alert("Connection error");
            layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
/*				parent.layer.msg("操作成功");
				parent.reLoad();
                parent.reload2();
                parent.reload3();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);*/
                layer.msg('操作成功',{time: 1000 },function () {
                    location.href="/biz/taskInfo";
                });

			} else {
				//parent.layer.alert(data.msg)
                layer.alert(data.msg);
			}

		}
	});

}

function openWorker(){
	var deptId="";
    deptId = $("#deptId").val();
    $("#getWorkerModal").on("show.bs.modal",function(){
        load(deptId)
	});
    $("#getWorkerModal").modal('show');

}
function selectWorker(){
 var rows =   $('#workerTable').bootstrapTable('getSelections');
 if(rows.length>1){
 	layer.msg("只能选择一个工作者！")
	 return;
 }
    if(rows.length==0){
        layer.msg("请选择一个工作者！")
        return;
    }

    $("#fixWorkerName").val(rows[0].name);
    $("#fixWorkerNo").val(rows[0].username);
    $("#getWorkerModal").on("hide.bs.modal",function(){
        $(this).removeData("bs.modal");
    });
    $("#getWorkerModal").modal('hide');
}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#taskInfoForm").validate({
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