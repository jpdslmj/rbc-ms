
var prefix = "/biz/popValve104/history";
$(function() {
    loadHistory();
});

function loadHistory() {
    $('#historyTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/list", // 服务器数据的加载地址
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
                        offset:params.offset
                        // name:$('#searchName').val(),
                        // username:$('#searchName').val()
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
                        field : 'popValue',
                        title : '104紧急阀编号'
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
                        title : '更新日期',
                        visible:false
                    },
                    {
                        title : '操作',
                        field : 'popValue',
                        align : 'center',
                        formatter : function(value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="viewHistory(\''
                                + row.id
                                + '\')"><i class="fa fa-edit"></i></a> ';

                            return e  ;
                        }
                    } ]
            });
}

function viewHistory(id) {
    layer.open({
        type : 2,
        title : '详情',
        maxMin : true,
        shadeClose : false, // 点击遮罩关闭层
        content : prefix +"/"+ id ,// iframe的url
        fixed:false,
        resize:true,
        area : ['360px','640px'],
        success:function(layero ,index){
            layer.full(index);
        }
    });
}