
$().ready(function() {
	loadPop104Table();
	if(disassembler){
	    $('#createTime').attr("disabled",false);
        $('#popValue').attr("disabled",false);
    }
    var today=new Date().Format("yyyy-MM-dd");
    $("#createTime").val(today);

	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
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
                    fixContent: "各橡胶制品齐全不超期，橡胶膜板及密封圈更换",
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
                    //value:currentUserName
                });
            },
            onUncheck:function(row, $element){
                row.worker="";
                $('#pop104Table').bootstrapTable('updateRow',{
                    index: $element.data('index'),
                    row:row
                    //value:currentUserName
                });
            },

            onLoadSuccess : function(data) {
                var data = $('#pop104Table').bootstrapTable('getData', true);
                // 合并单元格
                var fieldList=["fixPro"];
                mergeCells(data, "fixPro", 1, $('#pop104Table'),fieldList);
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
			        field : 'uId',
                    title : '主键',
                    visible:false
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
                   /** editable: {
                        type: 'text',
                        mode: "inline",
                        placeholder: '工作者',
                        validate: function (value) {
                            if ($.trim(value) == '') return '工作者不能为空';
                        }
                    }**/
                }
                ],

        });
}

 /**合并单元格

 @param data
            原始数据（在服务端完成排序）
@param fieldName
            合并参照的属性名称
 @param colspan
           合并开始列
@param target
           目标表格对象
 @param fieldList
           要合并的字段集合
**/
function mergeCells(data,fieldName,colspan,target,fieldList){
// 声明一个map计算相同属性值在data对象出现的次数和
    var sortMap = {};
    for(var i = 0 ; i < data.length ; i++){
        for(var prop in data[i]){
            //例如people.unit.name
            var fieldArr=fieldName.split(".");
            getCount(data[i],prop,fieldArr,0,sortMap);
        }
    }
    var index = 0;
    for(var prop in sortMap){
        var count = sortMap[prop];
        for(var i = 0 ; i < fieldList.length ; i++){
            $(target).bootstrapTable('mergeCells',{index:index, field:fieldList[i], colspan: colspan, rowspan: count});
        }
        index += count;
    }
}
/**
 * 递归到最后一层 统计数据重复次数
 * 比如例如people.unit.name 就一直取到name
 * 类似于data["people"]["unit"]["name"]
 */
function getCount(data,prop,fieldArr,index,sortMap){
    if(index == fieldArr.length-1){
        if(prop == fieldArr[index]){
            var key = data[prop];
            if(sortMap.hasOwnProperty(key)){
                sortMap[key] = sortMap[key]+ 1;
            } else {
                sortMap[key] = 1;
            }
        }
        return;
    }
    if(prop == fieldArr[index]){
        var sdata = data[prop];
        index=index+1;
        getCount(sdata,fieldArr[index],fieldArr,index,sortMap);
    }

}

function save() {
    var data1=$('#pop104Form').serialize();
    var data2=$('#pop104Form1').serialize();
    var data3=$('#returnRemark').text();
    var data4 = $.map($('#pop104Table').bootstrapTable('getSelections'), function (row) {
        var dataTemp="";
        if(row.uId=="0"){
            dataTemp =dataTemp+"&disassembleName="+currentUserName;
            dataTemp =dataTemp+"&disassembleNo="+currentUserNameNo;
        }
        if(row.uId=="1"){
            dataTemp =dataTemp+"&cleanerName="+currentUserName;
            dataTemp =dataTemp+"&cleanerNo="+currentUserNameNo;
        }
        if(row.uId=="2"){
            dataTemp =dataTemp+"&fixer1Name="+currentUserName;
            dataTemp =dataTemp+"&fixer1No="+currentUserNameNo;
        }
        if(row.uId=="3"){
            dataTemp =dataTemp+"&fixer2Name="+currentUserName;
            dataTemp =dataTemp+"&fixer2No="+currentUserNameNo;
        }
        if(row.uId=="4"){
            dataTemp =dataTemp+"&fixer3Name="+currentUserName;
            dataTemp =dataTemp+"&fixer3No="+currentUserNameNo;
        }
        if(row.uId=="5"){
            dataTemp =dataTemp+"&assemblerName="+currentUserName;
            dataTemp =dataTemp+"&gangmasterNo="+currentUserNameNo;
        }
        return dataTemp;
    });
    var data = data1+"&"+data2+"&returnRemark="+data3+data4;
	$.ajax({
		cache : true,
		type : "POST",
		url : "/biz/popValve104/save",
		data : data,// 你的formid
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
	$("#signupForm").validate({
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