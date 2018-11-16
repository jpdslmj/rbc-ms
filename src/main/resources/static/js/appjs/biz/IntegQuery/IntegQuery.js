
var prefix = "/biz/IntegQuery";
$(function() {
	load();
});

function load() {
	$('#IntegQuery')
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
								offset:params.offset,
					            valueNo:$('#valueNo').val(),
					            createTime:$('#createTime').val(),
                                valueType:$('#valueType').val(),
                                taskName:"归档打印"
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
									field : 'valueType',
									title : '部件类型',
									visible:false
								},
								{
									field : 'popValue',
									title : '部件编号'
								},
								{
									field : 'disassembleNo',
									title : '分解者工号' ,
									visible:false

								},
								{
									field : 'disassembleName', 
									title : '分解者名称' ,
									visible:false
								},
								{
									field : 'cleanerNo', 
									title : '清洗者工号' ,
									visible:false
								},
								{
									field : 'cleanerName', 
									title : '清洗者名称' ,
									visible:false
								},
								{
									field : 'fixer1No', 
									title : '检修者1工号',
									visible:false
								},
								{
									field : 'fixer1Name', 
									title : '检修者1名称' 
								},
								{
									field : 'fixer1Remark', 
									title : '检修者1备注' ,
									visible:false
								},
								{
									field : 'fixer2No', 
									title : '检修者2工号',
                                    visible:false
								},
								{
									field : 'fixer2Name', 
									title : '检修者2名称' ,
									visible:false
								},
								{
									field : 'fixer2Remark', 
									title : '检修者2备注' ,
									visible:false
								},
								{
									field : 'fixer3No', 
									title : '检修者3工号' ,
									visible:false
								},
								{
									field : 'fixer3Name', 
									title : '检修者3名称' ,
									visible:false
								},
								{
									field : 'fixer3Remark', 
									title : '检修者3备注' ,
									visible:false
								},
								{
									field : 'fixer4No', 
									title : '检修者4工号' ,
									visible:false
								},
								{
									field : 'fixer4Name', 
									title : '检修者4名称',
									visible:false
								},
								{
									field : 'fixer4Remark', 
									title : '检修者4备注' ,
									visible:false
								},
								{
									field : 'assemblerNo', 
									title : '组装者工号' ,
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
									title : '工长名称' 
								},

								{
									field : 'inspectorNo', 
									title : '质检员工号' ,
									visible:false
								},
								{
									field : 'inspectorName', 
									title : '质检员名称' 
								},

								{
									field : 'createTime', 
									title : '检修日期'
								},

								{
									field : 'taskid', 
									title : '当前任务ID',
									visible:false
								},
								{
									field : 'taskName',
									title : '当前任务名称' 
								},
								{
									title : '打印',
									field : 'popValue',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm " href="#" mce_href="#" title="查看" onclick="print(\''
												+ row.popValue+'\',\''+row.valueType
												+ '\')"><i class="fa fa-edit"></i></a> ';
										return e;
									}
								} ]
					});
}
function reLoad() {
   var valueType = $('#valueType').val();
	if(valueType=='0'||valueType==null||valueType==""){
		layer.alert("请选择部件类型！");
		return;
	}
	if($('#createTime').val()==null||$('#createTime').val()==""){
        layer.alert("请选择查询时间！");
        return;
	}
	$('#IntegQuery').bootstrapTable('refresh');
}
function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
}
function print(popValue,valueType) {

	layer.open({
        type : 2,
        title : '打印',
        shadeClose : false, // 点击遮罩关闭层
        content : prefix + '/print/' + popValue +'/'+valueType,// iframe的url
        maxmin : true,
        fixed:false,
        resize:true,
        area : ['600px','400px'],
        success:function(layero ,index){
            layer.full(index);
        }
	});
}
function remove(id) {
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

function resetPwd(id) {
}
function batchRemove() {
	var rows = $('#IntegQuery').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
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