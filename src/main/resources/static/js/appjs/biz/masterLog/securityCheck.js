
var prefix = "/biz/masterLog"
$(function() {
	loadSafeChkTable();
});
function loadSafeChkTable() {
    $('#safeChkTable').bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/listSecurity/", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
                        editable:true,
                        clickToSelect: true,
						iconSize : 'outline',
						html:true,
						//toolbar : '#safeChkTable',
						//striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
                        sortable:true,
                        sortOrder:"asc",
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
                                 logId:$('#id').val(),
                                 sort:"num",
                                 order:"asc"
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
									field : 'logId',
									title : '日志表主键',
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
									field : 'securityProject', 
									title : '抽查项目名' ,
									editable: {
										type: 'text',
										mode: "inline",
                                        placeholder: '抽查项目',
                                        validate: function (value) {
											if ($.trim(value) == '') return '质检项目不能为空';
											}
											}
								},
																{
									field : 'description', 
									title : '安全质量状况' ,
                                    editable: {
                                        type: 'text',
                                        mode: "inline",
                                        placeholder: '安全质量状况',
                                        validate: function (value) {
                                        if ($.trim(value) == '') return '安全质量状况不能为空';
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
									field : 'gangmasterNo', 
									title : '工长工号' ,
                                     visible:false
								},
																{
									field : 'gangmasterName', 
									title : '工长名称' ,
                                    visible:false
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
								}
								]
					});
}
$('#addData').click(function(){
	var Allrows=$('#safeChkTable').bootstrapTable('getData');
    var len= Allrows.length;


    var data = {num:++len,securityProject:"",description:"",remark:""};
    $('#safeChkTable').bootstrapTable('insertRow', {index:$('#safeChkTable').bootstrapTable('getData').length, row:data});
});

$('#delData').click(function(){
    var ids = $.map($('#safeChkTable').bootstrapTable('getSelections'), function (row) {
        return row.num;
    });
    $('#safeChkTable').bootstrapTable('remove', {
        field: 'num',
        values: ids
    });
    $.each($('#safeChkTable').bootstrapTable('getData'),function(i,row){
    	var t=i;
    	var r=row;
       $('#safeChkTable').bootstrapTable('updateCell',{
       	index:i,
	    field:'num',
	    value:++i
       });
	})
});


function reLoadSafeChkTable() {
	$('#safeChkTable').bootstrapTable('refresh');
}
function addSafeChkTable() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
}
function editSafeChkTable(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function removeSafeChkTable(id) {
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

function resetPwdSafeChkTable(id) {
}
function batchRemoveSafeChkTable() {
	var rows = $('#safeChkTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
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
			url : prefix + '/batchRemove',
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