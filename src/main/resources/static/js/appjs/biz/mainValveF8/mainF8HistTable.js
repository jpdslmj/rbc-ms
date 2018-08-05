
var prefix2 = "/biz/mainValveF8"
$(function() {
    load2();
});

function load2() {
    $('#mainValveF8Table')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix2 + "/list", // 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect : false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize : 10, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns : false, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams : function(params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset:params.offset,
                        queryType:"his",
                        createTime:$('#createTime').val(),
                        mainValue:$('#mainValue').val(),
                        username:currentUserNameNo
                    };
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns : [
                    {
                        checkbox : true
                    },
                    {
                        field : 'id',
                        title : '',
                        visible:false
                    },
                    {
                        field : 'taskId',
                        title : '',
                        visible:false
                    },
                    {
                        field : 'popValue',
                        title : 'F8主阀编号'
                    },
                    {
                        field : 'disassembleNo',
                        title : '分解者工号',
                        visible:false
                    },
                    {
                        field : 'disassembleName',
                        title : '分解者名称'
                    },
                    {
                        field : 'cleanerNo',
                        title : '清洗者工号',
                        visible:false
                    },
                    {
                        field : 'cleanerName',
                        title : '清洗者名称'
                    },
                    {
                        field : 'fixer1No',
                        title : '检修者1工号' ,
                        visible:false
                    },
                    {
                        field : 'fixer1Name',
                        title : '检修者1名称'
                    },
                    {
                        field : 'fixer1Remark',
                        title : '检修者1备注',
                        visible:false
                    },
                    {
                        field : 'fixer2No',
                        title : '检修者2工号',
                        visible:false
                    },
                    {
                        field : 'fixer2Name',
                        title : '检修者2名称',
                        visible:false
                    },
                    {
                        field : 'fixer2Remark',
                        title : '检修者2备注',
                        visible:false
                    },
                    {
                        field : 'fixer3No',
                        title : '检修者3工号',
                        visible:false
                    },
                    {
                        field : 'fixer3Name',
                        title : '检修者3名称',
                        visible:false
                    },
                    {
                        field : 'fixer3Remark',
                        title : '检修者3备注',
                        visible:false
                    },
                    {
                        field : 'fixer4No',
                        title : '检修者4工号',
                        visible:false
                    },
                    {
                        field : 'fixer4Name',
                        title : '检修者4名称',
                        visible:false
                    },
                    {
                        field : 'fixer4Remark',
                        title : '检修者4备注',
                        visible:false
                    },
                    {
                        field : 'assemblerNo',
                        title : '组装者工号',
                        visible:false
                    },
                    {
                        field : 'assemblerName',
                        title : '组装者名称'
                    },
                    {
                        field : 'assemblerRemark',
                        title : '组装者备注' ,
                        visible:false
                    },
                    {
                        field : 'assembleMark',
                        title : '组装人字标志' ,
                        visible:false
                    },
                    {
                        field : 'gangmasterNo',
                        title : '工长工号' ,
                        visible:false
                    },
                    {
                        field : 'gangmasterName',
                        title : '工长名称',
                        visible:false
                    },
                    {
                        field : 'gangmasterAudit',
                        title : '工长审核意见：1同意 0不同意',
                        visible:false
                    },
                    {
                        field : 'gangmasterRemark',
                        title : '工长审核备注',
                        visible:false
                    },
                    {
                        field : 'gangmasterMark',
                        title : '工长一字标志',
                        visible:false
                    },
                    {
                        field : 'inspectorNo',
                        title : '质检员工号',
                        visible:false
                    },
                    {
                        field : 'inspectorName',
                        title : '质检员名称',
                        visible:false
                    },
                    {
                        field : 'inspectorMark',
                        title : '质检员合字标志',
                        visible:false
                    },
                    {
                        field : 'inspectorAudit',
                        title : '质检员审核意见：1同意 0不同意',visible:false
                    },
                    {
                        field : 'inspectorRemark',
                        title : '质检员审核备注' ,visible:false
                    },
                    {
                        field : 'returnOpinion',
                        title : '退回意见：1同意 0不同意' ,visible:false
                    },
                    {
                        field : 'returnRemark',
                        title : '退回备注',visible:false
                    },
                    {
                        field : 'createTime',
                        title : '创建日期'
                    },
                    {
                        field : 'updateTime',
                        title : '更新日期', visible:false
                    },	 							{
                        field : 'taskId',
                        title : '当前任务ID', visible:false
                    },	 							{
                        field : 'taskName',
                        title : '当前环节'
                    },	 							{
                        field : 'processInstanceId',
                        title : '流程实例ID', visible:false
                    },
                    {
                        title : '操作',
                        field : 'mainValue',
                        align : 'center',
                        formatter : function(value, row, index) {
                            if(row.taskId != null && row.taskId != "null" && row.taskId != "") {
                                var e = '<a class="btn btn-primary btn-sm" href="#" mce_href="#" title="查看" onclick="look(\''
                                    + row.id
                                    + '\')"><i class="fa fa-eye"></i></a> ';
                                return e;
                            }
                        }
                    } ]
            });
}
function reLoad2() {
    $('#mainValveF8Table').bootstrapTable('refresh');
}
function add() {
    layer.open({
        type : 2,
        title : '增加',
        shadeClose : false, // 点击遮罩关闭层
        content : prefix2 + '/add', // iframe的url
        maxmin : true,
        fixed:false,
        resize:true,
        area : ['600px','400px'],
        success:function(layero ,index){
            layer.full(index);
        }
    });
}
function edit2(id) {
    layer.open({
        type : 2,
        title : '编辑',
        shadeClose : false, // 点击遮罩关闭层
        content : prefix2 + '/edit/' + id ,// iframe的url
        maxmin : true,
        fixed:false,
        resize:true,
        area : ['600px','400px'],
        success:function(layero ,index){
            layer.full(index);
        }
    });
}
function look(id) {
    layer.open({
        type : 2,
        title : '查看',
        shadeClose : false, // 点击遮罩关闭层
        content : prefix2 + '/look/' + id ,// iframe的url
        maxmin : true,
        fixed:false,
        resize:true,
        area : ['600px','400px'],
        success:function(layero ,index){
            layer.full(index);
        }
    });
}
function remove2(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            url : prefix2+"/remove",
            type : "post",
            data : {
                'id' : id
            },
            success : function(r) {
                if (r.code==0) {
                    layer.msg(r.msg);
                    reLoad();
                }else{
                    layer.msg(r.msg);
                }
            }
        });
    })
}

function resetPwd2(id) {
}
function batchRemove2() {
    var rows = $('#mainValveF8Table').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn : [ '确定', '取消' ]
        // 按钮
    }, function() {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function(i, row) {
            ids[i] = row['id'];
        });
        $.ajax({
            type : 'POST',
            data : {
                "ids" : ids
            },
            url : prefix2 + '/batchRemove',
            success : function(r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function() {

    });
}