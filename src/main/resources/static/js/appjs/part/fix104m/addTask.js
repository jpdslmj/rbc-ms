$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {

	}
});
function saveFixTask() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/part/fix104m/saveFixTask",
		data : $('#taskForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function submitTask() {
    $.ajax({
        cache : true,
        type : "POST",
        url : "/part/fix104m/addTask",
        data : $('#taskForm').serialize(),// 你的formid
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                parent.reLoad();
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
	$("#taskForm").validate({
		rules : {
			name : {
				//required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入名字"
			}
		}
	})
}