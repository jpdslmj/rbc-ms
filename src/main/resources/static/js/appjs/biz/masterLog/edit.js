$().ready(function() {
    var today=new Date().Format("yyyy-MM-dd");
    $("#updateTime").val(today);
    validateRule();
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
$.validator.setDefaults({
    submitHandler : function() {
        save();
    }
});
function save() {
    var rows=$('#safeChkTable').bootstrapTable('getData');
    //$.each(allrows,function(i,row){
    //if(row.securityProject==""||securityProject==null) {
    //layer.msg("安全检查项目不能为空！");
    //return;
    //}
    //})
    var masterLog= $("#masterLOgForm").serializeObject();
    var Data={
        "masterLogDo":masterLog,
        "securityCheckDos":rows
    };
    var  jasonData= JSON.stringify(Data);
    $.ajax({
        cache : true,
        type : "POST",
        url : "/biz/masterLog/saveSecurityCheck",
        contentType: "application/json; charset=utf-8",
        data : jasonData,//
        async : false,
        error : function(request) {
            //parent.layer.alert("保存安全检查数据Connection error");
            layer.alert("保存安全检查数据Connection error");
        },
        success : function(data) {
            if (data.code == 0) {
/*
                parent.layer.msg("操作成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
*/
                layer.msg('操作成功',{time: 1000 },function () {
                    location.href="/biz/masterLog";
                });
            } else {
               // parent.layer.alert(data.msg)
                layer.alert(data.msg);
            }

        }
    });

}
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#masterLOgForm").validate({
        rules : {
            createTime : {
                required : true
            },
            gangmasterNo:{
                required : true
            },
            gangmasterName:{
                required : true
            },
        },
        messages : {
            createTime : {
                required : icon + "时间不能为空！"
            },
            gangmasterNo : {
                required : icon + "工长工号不能为空！"
            },
            gangmasterName : {
                required : icon + "工长名称不能为空！"
            },
        }
    })
}