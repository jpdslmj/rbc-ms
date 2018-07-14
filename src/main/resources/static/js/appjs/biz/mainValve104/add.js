$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
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
                    logId:$('#id').val()
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
                },
                {
                    title : '操作',
                    field : 'num',
                    align : 'center',
                    formatter : function(value, row, index) {
                        var e = '<a class="btn btn-primary btn-sm '+'" href="#" mce_href="#" title="编辑" onclick="editSafeChkTable(\''
                            + row.num
                            + '\')"><i class="fa fa-edit"></i></a> ';
                        var d = '<a class="btn btn-warning btn-sm '+'" href="#" title="删除"  mce_href="#" onclick="removeSafeChkTable(\''
                            + row.num
                            + '\')"><i class="fa fa-remove"></i></a> ';
                        return e + d ;
                    }
                } ]
        });
}




function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/biz/mainValve104/save",
		data : $('#signupForm').serialize(),// 你的formid
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