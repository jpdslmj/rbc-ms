$().ready(function() {

    $("#wenchAdd").hide();
	$("#wench").change(function(){
        var selectWenchVal=$("#wench").val();
        if(selectWenchVal!=1){
            $("#wenchAdd").show();
            loadwenchTable();
		}else{
            $("#wenchAdd").hide();
            $('#wenchTable').bootstrapTable("destroy");
		}
	});
    $("#toolAdd").hide();
    $("#otherTool").change(function(){
        var selectToolVal=$("#otherTool").val();
        if(selectToolVal!=1){
            $("#toolAdd").show();
            loadotherToolTable();
        }else{
            $("#toolAdd").hide();
            $('#otherToolTable').bootstrapTable("destroy");
		}
    });


    $("#fixWorkerNo").val(currentUserNameNo);
    $("#fixWorkerName").val(currentUserName);
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save("save");
	}
});
$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};
function save(flag) {
    var sWenckVal =$("#wench").val();
    var sotherToolVal =$("#otherTool").val();

    var inspectionFormData= $('#toolInspectionForm').serializeObject();

	var wenchTableData=$('#wenchTable').bootstrapTable('getData');
	if(sWenckVal==1){
        wenchTableData=[];
	}
    var otherToolTableData=$('#otherToolTable').bootstrapTable('getData');
    if(sotherToolVal==1){
        otherToolTableData=[];
    }
    var Data={
        "toolInspectionDo":inspectionFormData,
        "wenchDo":wenchTableData,
		"toolOtherDo":otherToolTableData
    };
    var  jasonData= JSON.stringify(Data);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/biz/toolInspection/"+flag,
        contentType: "application/json; charset=utf-8",
		data : jasonData,
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
                    location.href="/biz/toolInspection";
                });

			} else {
				//parent.layer.alert(data.msg)
                layer.alert(data.msg);
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#toolInspectionForm").validate({
		rules : {
            luminanceMeasure : {
				required : true
			},
            temperature : {
                required : true
            },
            humidity : {
                required : true
            },
            templet : {
                required : true
            },
            nozzleCleaner : {
                required : true
            },
            siliconeOil : {
                required : true
            },
            siliconeGrease : {
                required : true
            },
            wench : {
                required : true
            },
            otherTool : {
                required : true
            }

        },
		messages : {
            luminanceMeasure : {
                required : icon + "请输入白光照度"
            },
            temperature : {
                required : icon + "请输入温度"
            },
            humidity : {
                required : icon + "请输入湿度"
            },
            templet : {
                required : icon + "请检查样板"
            },
            nozzleCleaner : {
                required : icon + "请请检查通针"
            },
            siliconeOil : {
                required : icon + "请检查硅油"
            },
            siliconeGrease : {
                required : icon + "请检查硅脂"
            },
            wench : {
                required : icon + "请检查力矩扳手"
            },
            otherTool : {
                required : icon + "请检查其他工具"
            }
		}
	})
}