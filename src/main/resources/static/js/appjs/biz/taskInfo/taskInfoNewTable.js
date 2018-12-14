
var prefix3 = "/biz/taskInfo";
$(function() {
    if(gangmaster){
        curUserNo1=currentUserNameNo;
    }else{
        curUserNo2=currentUserNameNo;
    }
	load3();
});

function load3() {
	$('#taskInfoNewTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix3 + "/listNew", // 服务器数据的加载地址
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
                                queryType:"new",
                                distribTime:$('#createTimeNew').val(),
                                fixWorkerNo:curUserNo2,
                                gangmasterNo:curUserNo1
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
                                title : '任务序号' ,
                            },
                            {
                                field : 'fixTask',
                                title : '检修任务',
                                formatter : function(value, row, index)	{
                                    var optionName="";
                                    $.ajax({
                                        cache : true,
                                        type : "POST",
                                        url : "/biz/taskInfo/getOptionName",
                                        data : {
                                            "optionValue":value
                                        },
                                        async : false,
                                        error : function(request) {
                                            parent.layer.alert("Connection error");
                                        },
                                        success : function(data) {
                                            if (data.code == 0) {
                                                optionName=data.optionName;
                                            } else {
                                                optionName="取值失败！";
                                            }

                                        }
                                    });
                                    return optionName;
                                }
                            },
                            {
                                field : 'planNum',
                                title : '计划数量'
                            },
                            {
                                field : 'realNum',
                                title : '实际数量'
                            },
                            {
                                field : 'fixWorkerName',
                                title : '工作者名称'
                            },
                            {
                                field : 'gangmasterName',
                                title : '工长名称'
                            },
                            {
                                field : 'distribTime',
                                title : '发布时间'
                            },
                            {
                                title : '操作',
                                field : 'id',
                                align : 'center',
                                formatter : function(value, row, index) {
                                    var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit3(\''
                                        + row.id
                                        + '\')"><i class="fa fa-edit"></i></a> ';
                                    var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove3(\''
                                        + row.id
                                        + '\')"><i class="fa fa-remove"></i></a> ';
                                    return e + d ;
                                }
                            } ]
					});
}
function reload3(){
	$('#taskInfoNewTable').bootstrapTable('refresh');
}
function add3() {
/*	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		content : prefix3 + '/add', // iframe的url
        fixed:false,
        resize:true,
        area : ['600x','400px'],
        success:function(layero ,index){
            layer.full(index);
        }
	});*/
    window.location.href=prefix3 + '/add';
}
function edit3(id) {
/*	layer.open({
		type : 2,
		title : '编辑',
		shadeClose : false, // 点击遮罩关闭层
		content : prefix3 + '/edit/' + id, // iframe的url
        maxmin : true,
        fixed:false,
        resize:true,
        area : ['600x','400px'],
        success:function(layero ,index){
            layer.full(index);
        }
	});*/
    window.location.href=prefix3 + '/edit/' + id;
}
function remove3(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix3+"/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reload3();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}

function resetPwd3(id) {
}
function batchRemove3() {
	var rows = $('#taskInfoNewTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
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
			url : prefix3 + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reload3();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}