
$().ready(function() {
	loadPop104Table();
    if(disassembler){
        //$('#createTime').removeAttr("readonly");
        $('#popValue').removeAttr("readonly");
    }
    var today=new Date().Format("yyyy-MM-dd HH:mm:ss");
    $("#createTime").val(today);
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
        save('sign');
	}
});

function loadPop104Table() {
    $('#pop104Table').bootstrapTable(
        {
			data:[
                {
                    uId:"0",
                    fixPro: "分解",
                    fixContent: "检查除锈及配件状态",
                    worker: ""
                },
                {
                    uId:"1",
                    fixPro: "清洗",
                    fixContent: "阀体、阀盖和阀内各配件清洗",
                    worker: ""
                },
                {
                    uId:"2",
                    fixPro: "检修",
                    fixContent: "通针疏通限制孔：ШФ1.8mm IVФ0.5mm VФ1.15mm 零部件检查",
                    worker: ""
                },
                {
                    uId:"3",
                    fixPro: "检修",
                    fixContent: "各橡胶制品齐全不超期，橡胶膜板及密封圈更换",
                    worker: ""

                },
                {
                    uId:"4",
                    fixPro: "检修",
                    fixContent: "弹簧测量",
                    worker: ""
                },
                {
                    uId:"5",
                    fixPro: "组装",
                    fixContent: "各部件组装状态良好，结合部清洁，压板螺母紧固，丝扣紧固",
                    worker: ""

                }

            ],
            uniqueId:'uId',
            editable:true,
            clickToSelect: false,
            iconSize : 'outline',
            html:true,
            dataType : "json", // 服务器返回的数据类型
            singleSelect : false, // 设置为true将禁止多选
            showColumns : false, // 是否显示内容下拉框（选择显示的列）
            onCheck:function(row, $element){
                row.worker=currentUserName;
                $('#pop104Table').bootstrapTable('updateRow',{
                    index: $element.data('index'),
                    row:row
                });
                if(row.uId==0){
                    $('#disassembleNo').val(currentUserNameNo);
                    $('#disassembleName').val(currentUserName);
                }
                if(row.uId==1){
                    $('#cleanerNo').val(currentUserNameNo);
                    $('#cleanerName').val(currentUserName);
                }
                if(row.uId==2){

                    $('#fixer1No').val(currentUserNameNo);
                    $('#fixer1Name').val(currentUserName);
                }
                if(row.uId==3){
                    $('#fixer2No').val(currentUserNameNo);
                    $('#fixer2Name').val(currentUserName);

                }
                if(row.uId==4){
                    $('#fixer3No').val(currentUserNameNo);
                    $('#fixer3Name').val(currentUserName);
                }
                if(row.uId==5){
                    $('#assemblerNo').val(currentUserNameNo);
                    $('#assemblerName').val(currentUserName);
                }
            },
            onUncheck:function(row, $element){
                row.worker="";
                $('#pop104Table').bootstrapTable('updateRow',{
                    index: $element.data('index'),
                    row:row
                });
                if(row.uId==0){
                    $('#disassembleNo').val("");
                    $('#disassembleName').val("");
                }
                if(row.uId==1){
                    $('#cleanerNo').val("");
                    $('#cleanerName').val("");
                }
                if(row.uId==2){

                    $('#fixer1No').val("");
                    $('#fixer1Name').val("");
                }
                if(row.uId==3){
                    $('#fixer2No').val("");
                    $('#fixer2Name').val("");

                }
                if(row.uId==4){
                    $('#fixer3No').val("");
                    $('#fixer3Name').val("");
                }
                if(row.uId==5){
                    $('#assemblerNo').val("");
                    $('#assemblerName').val("");
                }
            },
            columns : [
                {
                    checkbox : true,
                    formatter:function stateFormatter(value,row,index){
                        if (disassembler&&row.fixPro=="分解") {
                            if(row.worker!=""){
                                return {
                                    disabled: false,//设置是否可用
                                    checked:true
                                }
                            }else{
                                return {
                                    disabled: false//设置是否可用
                                }
                            }

                        }else if (cleaner&&row.fixPro=="清洗") {
                            if(row.worker!=""){
                                return {
                                    disabled: false,//设置是否可用
                                    checked:true
                                }
                            }else{
                                return {
                                    disabled: false//设置是否可用
                                }
                            }
                        }else if (fixer&&row.fixPro=="检修") {
                            if(row.worker!=""){
                                return {
                                    disabled: false,//设置是否可用
                                    checked:true
                                }
                            }else{
                                return {
                                    disabled: false//设置是否可用
                                }
                            }
                        }else if (assembler&&row.fixPro=="组装") {
                            if(row.worker!=""){
                                return {
                                    disabled: false,//设置是否可用
                                    checked:true
                                }
                            }else{
                                return {
                                    disabled: false//设置是否可用
                                }
                            }
                        }else{
                            return {
                                disabled: true//设置是否可用
                            }
                        }
                        return value;
                    }
                },
                {
                    field : 'fixPro',
                    title : '检修工序'
                },
                {
                    field : 'fixContent',
                    title : '检修内容'
                },
                {
                    field : 'worker',
                    title : '工作者',
                },
                {
                    field : 'uId',
                    title : '主键',
                    visible:false
                },
                ],
        });
}
function workPermission(flag){
    var workPermMrk='';
    $.ajax({
        url : "/biz/popValve104/workPermission",
        type : "post",
        data : {
            "queryType" : "his",
        },
        async : false,
        success : function(r) {
            workPermMrk=r.code;
        }
    });
    if(workPermMrk==1&&flag=='sign'){
        return true;
    }else{
        return false;
    }
}
function save(flag) {
    if(disassembler){
        if(workPermission(flag)==true){
            layer.alert("请先完成当天工具检视任务！");
            return;
        }
        if($('#disassembleNo').val()==null||$('#disassembleNo').val()==''){
        alert("请签名！");
        return;
    }
}
    if(cleaner){
        if(workPermission(flag)==true){
            layer.alert("请先完成当天工具检视任务！");
            return;
        }
        if($('#cleanerNo').val()==null||$('#cleanerNo').val()==''){
            alert("请签名！");
            return;
        }
    }
    if(fixer&&flag=='sign'){
        if(workPermission(flag)==true){
            layer.alert("请先完成当天工具检视任务！");
            return;
        }
        var fixer1=$('#fixer1No').val();
        var fixer2=$('#fixer2No').val();
        var fixer3=$('#fixer3No').val();
        if(fixer1==null||fixer1==''||fixer2==null||fixer2==''||fixer3==null||fixer3==''){
            alert("检修请全部签名！");
            return;
        }
    }
    if(assembler){
        if(workPermission(flag)==true){
            layer.alert("请先完成当天工具检视任务！");
            return;
        }
        if($('#assemblerNo').val()==null||$('#assemblerNo').val()==''){
            alert("请签名！");
            return;
        }
    }
    var data=$('#pop104Form').serialize().replace(/\+/g," ");
	$.ajax({
		cache : true,
		type : "POST",
		url : "/biz/popValve104/"+flag,
		data : data,
		async : false,
		error : function(request) {
			//parent.layer.alert("Connection error");
            layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
                layer.msg('操作成功',{time: 1000 },function () {
                    location.href="/biz/popValve104";
                });
				// parent.layer.msg("操作成功");
				// parent.reLoad();
                // parent.reLoad2();
                // parent.reLoad3();
				// var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				// parent.layer.close(index);

			} else {
				//parent.layer.alert(data.msg)
                layer.alert(data.msg);
			}

		}
	});
}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
    $("#pop104Form").validate({
		rules : {
            popValue : {
				required : true,
                remote : {
                    url : "/biz/popValve104/exit", // 后台处理程序
                    type : "post", // 数据发送方式
                    dataType : "json", // 接受数据格式
                    data : { // 要传递的数据
                        popValue : function() {
                            return $("#popValue").val();
                        }
                    }
                }
			}
		},
		messages : {
            popValue : {
				required : icon + "编号不能为空",
                remote : icon + "编号已经存在"
			}
		}
	});
}