
var prefix = "/biz/toolInspection"
/**$(function() {
	loadotherToolTable();
});**/
function loadotherToolTable() {
    $('#otherToolTable').bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/listOtherTool/", // 服务器数据的加载地址
                        editable:true,
                        clickToSelect: true,
						iconSize : 'outline',
                        sortable:true,
                        sortOrder:"asc",
						html:true,
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						singleSelect : false, // 设置为true将禁止多选
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
					           // name:$('#searchName').val(),
					           // username:$('#searchName').val()
                                toolId:$('#id').val(),
                                sort:"num",
                                order:"asc",
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
                                field : 'toolId',
                                title : '主表Id',
                                visible:false
                            },
                            {
                                field : 'id',
                                title : '主键',
                                visible:false
                            },
                            {
                                field : 'num',
                                title : '序号'
                            },
                            {
                                field : 'toolName',
                                title : '工具名称' ,
                                editable: {
                                    type: 'text',
                                    mode: "inline",
                                    placeholder: '工具名称',
                                    validate: function (value) {
                                        if ($.trim(value) == '') return '工具名称不能为空';
                                    }
                                }
                            },
                            {
                                field : 'dealSit',
                                title : '处置情况' ,
                                editable: {
                                    type: 'select',
                                    mode: "inline",
                                    placeholder: '处置情况',
                                    value:1,
                                    source: [
                                        {value: 1, text: '更换'},
                                        {value: 2, text: '借用'},
                                        {value: 3, text: '不处理'}
                                    ],
                                    validate: function (value) {
                                        if ($.trim(value) == '') return '处置情况不能为空';
                                    }
                                }

                            },
                            {
                                field : 'remark',
                                title : '备注',
                                editable: {
                                    type: 'text',
                                    mode: "inline",
                                    placeholder: '备注',
                                }
                            },
                            {
                                field : 'createTime',
                                title : '创建日期',
                                visible:false
                            },
                            {
                                field : 'updateTime',
                                title : '更新日期',
                                visible:false
                            } ]
					});
}
$('#addTool').click(function(){
	var Allrows=$('#otherToolTable').bootstrapTable('getData');
    var len= Allrows.length;
    var data = {num:++len,toolName:"",dealSit:1,remark:""};
    $('#otherToolTable').bootstrapTable('insertRow', {index:$('#otherToolTable').bootstrapTable('getData').length, row:data});
});

$('#delTool').click(function(){
    var rows = $('#otherToolTable').bootstrapTable('getSelections');
    removeOtherToolTableRows (rows);
});
function removeOtherToolTableRows (rows){
    var ids = $.map(rows, function (row) {
        return row.num;
    });
    $('#otherToolTable').bootstrapTable('remove', {
        field: 'num',
        values: ids
    });
    $.each($('#otherToolTable').bootstrapTable('getData'),function(i,row){
        var t=i;
        var r=row;
        $('#otherToolTable').bootstrapTable('updateCell',{
            index:i,
            field:'num',
            value:++i
        });
    })

}

function reLoadotherToolTable() {
	$('#otherToolTable').bootstrapTable('refresh');
    return;
}
function addotherToolTable() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
}
function editotherToolTable(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function removeotherToolTable(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
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

function resetPwdotherToolTable(id) {
}
function batchRemoveotherToolTable(rows,type) {
	 // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
       return;
    }
    var ids = new Array();
    // 遍历所有选择的行数据，取每条数据对应的ID
    $.each(rows, function(i, row) {
        if(row['id']!=null&&""!=row['id']){
            ids[i] = row['id'];
        }
    });
    if(ids.length == 0){
        removeOtherToolTableRows(rows);
	}
	layer.confirm("确认要从数据库删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemoveOtherTool',
			success : function(r) {
				if (r.code == 0) {
					if(type=="r"){
                        removeOtherToolTableRows(rows);
					}
					if(type=="d"){
                        $("#toolAdd").hide();
                        $('#otherToolTable').bootstrapTable("destroy");
					}

					layer.msg(r.msg);

				} else {

					layer.msg(r.msg);
				}
			}
		});
	}, function() {
	});
}