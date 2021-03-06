
$().ready(function() {
    loadPop104Table();
    var crtTm=new Date(createTime).Format("yyyy-MM-dd");
    $("#createTime").val(crtTm);

    $.each($('#pop104Table').bootstrapTable('getData'),function(i,row){
        if(row.uId==0){
            if(disassembleName!=null) {row.worker=disassembleName;}
    }
        if(row.uId==1){
            if(cleanerName!=null){row.worker=cleanerName;}
        }
        if(row.uId==2){
            if(fixer1Name!=null){row.worker=fixer1Name;}
        }
        if(row.uId==3){
            if(fixer2Name!=null){row.worker=fixer2Name;}

        }
        if(row.uId==4){
            if(fixer3Name!=null){row.worker=fixer3Name;}
        }
        if(row.uId==5){
           if(assemblerName!=null){row.worker=assemblerName;}
        }
        $('#pop104Table').bootstrapTable('updateRow',{
            index:row.uId,
            row:row
        });
    });
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
            editable:false,
            clickToSelect: false,
            iconSize : 'outline',
            html:true,
            dataType : "json", // 服务器返回的数据类型
            singleSelect : false, // 设置为true将禁止多选
            showColumns : false, // 是否显示内容下拉框（选择显示的列）
            onCheck:function(row, $element){
                row.worker=currentUserName;
                var workerName="";
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
                $('#pop104Table').bootstrapTable('updateRow',{
                    index: $element.data('index'),
                    row:row
                });
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
                }
            ],

        });
}

function uploadImg(uploadType){
    var partId=$("#id").val();
    var partType="popValve104";
    window.location.href="/common/sysFile/imgUpload/"+partId+"/"+partType+"/"+uploadType;

}
