function homework1() {
    var a=[{"currentPage":"1","pageSize":"5","keyWord":null,"orderBy":null,"sort":null}];
    data1={
        "countryId":244,
        "Serachvo":JSON.stringify(a),
    };
    $.ajax({
        url:"/City/getCity",
        data:data1,
        type:"post",
        contentType: "application/json",
        success:function (data) {
            console.log(data);
            $("#answer").text(data);
        }
    })
}
function homework2() {
    var data2={
        "countryId":244,
    }
    $.get("/City/getCity",data2,function (data) {
        console.log(data);
        $("#answer").text(data);
    })
}