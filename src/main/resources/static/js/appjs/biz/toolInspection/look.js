$().ready(function() {

	if(wenchVal!=1){
        $("#wenchAdd").show();
        loadwenchTable();
	}else{
		$("#wenchAdd").hide();
	}
	if(otherToolVal!=1){
        $("#toolAdd").show();
        loadotherToolTable();
	}else{
        $("#toolAdd").hide();
	}

    $("#wench").change(function(){
        var selectWenchVal=$("#wench").val();
        if(selectWenchVal!=1){
            $("#wenchAdd").show();
            loadwenchTable();
        }else{
            $("#wenchAdd").hide();
            $('#wenchTable').bootstrapTable("destroy");
        }
    });
    $("#otherTool").change(function(){
        var selectToolVal=$("#otherTool").val();
        if(selectToolVal!=1){
            $("#toolAdd").show();
            loadotherToolTable();
        }else{
            $("#toolAdd").hide();
            $('#otherToolTable').bootstrapTable("destroy");
        }
    });

    if(gangmaster){
         $("#inspectionField").attr("disabled","disabled");
    }
});

$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};