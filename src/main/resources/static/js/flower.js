$(function () {
    flowerId = window.localStorage.getItem("flowerId");
    console.log(flowerId)
    req.open('GET', 'api/flower/get?id=' + flowerId, false);
    req.send();
    console.log(req.status);
    let flower = JSON.parse(req.responseText);
    console.log(flower);
    $('#photo').attr("src","data:image/jpg;base64," + flower.image);
    $('#info').append(
        "            <div class=\"row\">\n" +
        "                <div class=\"col s12\">\n" +
        "                    <h5>name: " + flower.name + "</h5>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "            <div class=\"row\">\n" +
        "                <div class=\"col s12\">\n" +
        "                    <h5>description: " + flower.description + "</h5>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "            <div class=\"row\">\n" +
        "                <div class=\"col s12\">\n" +
        "                    <h5>price: " + flower.price + "</h5>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "            <div class=\"row\">\n" +
        "                <div class=\"col s12\">\n" +
        "                    <h5>category: " + flower.categoryName + "</h5>\n" +
        "                </div>\n" +
        "            </div>")
    let comments = flower.comments;
    console.log(comments)
    for (let i = 0; i < comments.length; i++) {
        $('.collection').append(
            '            <li class="collection-item avatar">\n' +
            '                <i class="material-icons circle red">chat_bubble_outline</i>\n' +
            '                <p style="font-size:24px">' + comments[i] + '</p>\n' +
            '            </li>');
    }
    $('#index').append(
        '    <div class="row main center-align">\n' +
        '        <a class="btn messageForm waves-effect waves-light">new comment\n' +
        '            <i class="material-icons right">add</i></a>\n' +
        '    </div>')
    $('#newMessage').click(function () {
        req.open('POST', 'api/comment/save', false);
        req.setRequestHeader("Content-type", "application/json;charset=UTF-8");
        let body = {};
        body.flowerId = flowerId;
        body.text = $(".message").val();
        console.log(body)
        req.send(JSON.stringify(body));
        console.log(req.status);
        window.location.href = "/flower";
    });

    $('#delete-flower').click(function () {
        req.open('DELETE', 'api/flower/delete?id=' + flowerId, false);
        req.send();
        console.log(req.status);
        window.location.href = "/main";
    });
});