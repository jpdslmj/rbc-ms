
$().ready(function() {
    loadmain104Table();
    var crtTm=new Date(createTime).Format("yyyy-MM-dd");
    $("#createTime").val(crtTm);

    $.each($('#main104Table').bootstrapTable('getData'),function(i,row){
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
            if(fixer4Name!=null){row.worker=fixer4Name;}
        }
        if(row.uId==6){
            if(assemblerName!=null){row.worker=assemblerName;}
        }
        $('#main104Table').bootstrapTable('updateRow',{
            index:row.uId,
            row:row
        });
    });
});

function loadmain104Table() {
    $('#main104Table').bootstrapTable(
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
                    fixContent: "阀体、阀盖和阀内各配件清洗 检测主活塞杆无变形",
                    worker: ""
                },
                {
                    uId:"2",
                    fixPro: "检修",
                    fixContent: "样板检测滑阀室限度≤46.8mm，滑阀限度≥16mm,节制阀限度≥5mm，缓解联络槽限度≥2.2mm",
                    worker: ""
                },
                {
                    uId:"3",
                    fixPro: "检修",
                    fixContent: "通针疏通限制孔：IФ0.8.8mm IIФ1.0mm 滑阀充气孔Ф1.2mm ，零部件检查",
                    worker: ""

                },
                {
                    uId:"4",
                    fixPro: "检修",
                    fixContent: "各橡胶制品齐全不超期，橡胶膜板及密封圈更换",
                    worker: ""
                },
                {
                    uId:"5",
                    fixPro: "检修",
                    fixContent: "弹簧测量",
                    worker: ""
                },
                {
                    uId:"6",
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
                    $('#fixer4No').val(currentUserNameNo);
                    $('#fixer4Name').val(currentUserName);
                }
                if(row.uId==6){
                    $('#assemblerNo').val(currentUserNameNo);
                    $('#assemblerName').val(currentUserName);
                }
                $('#main104Table').bootstrapTable('updateRow',{
                    index: $element.data('index'),
                    row:row
                });
            },
            onUncheck:function(row, $element){
                row.worker="";
                $('#main104Table').bootstrapTable('updateRow',{
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
                    $('#fixer4No').val("");
                    $('#fixer4Name').val("");
                }
                if(row.uId==6){
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
    var partType="mainValve104";
    window.location.href="/common/sysFile/imgUpload/"+partId+"/"+partType+"/"+uploadType;

}

