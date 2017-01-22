$(window).load(function(){
    $.ajax({
        url: "/go",
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success:function(data){
            //alert(data);
            //var roles = JSON.parse(data);
            $.each(data, function( index, value ) {
                $('#role').append("<option value = " + value + ">" +value +"</option>");
            });

            //var qualifications = JSON.parse(response.listQualifications);
            //$.each(qualifications, function( index, value ) {
            //    $('#qualification').append("<option value = " + value.qualificationId + ">" +value.qualificationName +"</option>");
            //});
        },
        error:function(jqXhr, textStatus, errorThrown){
            alert(textStatus);
        }
    });
});