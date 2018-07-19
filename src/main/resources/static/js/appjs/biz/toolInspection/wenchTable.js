
var prefix = "/biz/toolInspection"
/**$(function() {
	loadwenchTable();
});**/
function loadwenchTable() {
    $('#wenchTable').bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/listWench/", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
                        editable:true,
                        clickToSelect: true,
						iconSize : 'outline',
						html:true,
						//toolbar : '#wenchTable',
						//striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						sortable:true,
						sortOrder:"asc",
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
									field : 'wenchNo',
									title : '工具编号',
									editable: {
										type: 'text',
										mode: 'inline',
                                        placeholder: '工具编号',
                                        validate: function (value) {
											if ($.trim(value) == '') return '工具编号不能为空';
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
                                       placeholder: '备注'
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
$('#addWench').click(function(){
	var Allrows=$('#wenchTable').bootstrapTable('getData');
    var len= Allrows.length;
    var data = {num:++len,wenchNo:"",dealSit:1,remark:""};
    $('#wenchTable').bootstrapTable('insertRow', {index:$('#wenchTable').bootstrapTable('getData').length, row:data});
});

$('#delWench').click(function(){
    var rows = $('#wenchTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    removeWenchTableRows (rows);
});
function removeWenchTableRows (rows){
    var ids = $.map(rows, function (row) {
        return row.num;
    });
    $('#wenchTable').bootstrapTable('remove', {
        field: 'num',
        values: ids
    });
    $.each($('#wenchTable').bootstrapTable('getData'),function(i,row){
        var t=i;
        var r=row;
        $('#wenchTable').bootstrapTable('updateCell',{
            index:i,
            field:'num',
            value:++i
        });
    })

}

function reLoadwenchTable() {
	$('#wenchTable').bootstrapTable('refresh');
}
function addwenchTable() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
}
function editwenchTable(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function removewenchTable(id) {
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

function resetPwdwenchTable(id) {
}
function batchRemovewenchTable(rows) {
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
        removeWenchTableRows(rows);
        return;
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
			url : prefix + '/batchRemoveWench',
			success : function(r) {
				if (r.code == 0) {
					if(type=="r"){
                        removeWenchTableRows(rows);
					}
				if(type=="d"){
					$("#wenchAdd").hide();
					$('#wenchTable').bootstrapTable("destroy");
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