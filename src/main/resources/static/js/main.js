$(function () {
    req.open('GET', 'api/category/list', false);
    req.send();
    console.log(req.status);
    let categories = JSON.parse(req.responseText);
    console.log(categories);
    localStorage.setItem("categories", categories);

    $.each(categories, function (key, value) {
        $('#category')
            .append($("<option></option>")
                .attr("value", value.id)
                .text(value.name));
    })

    let flowers;
    $('body').on("change", "#category", function () {
        let category = $(this).find("option:selected").val();
        req.open('GET', 'api/flower/list?categoryId=' + category, false);
        req.send();
        console.log(req.status);
        flowers = JSON.parse(req.responseText);
        $('.cards').empty();
        for (let i = 0; i < flowers.length; i++) {
            $('.cards').append(
                "        <div class=\"card hoverable medium col s3\">\n" +
                "            <div class=\"card-image\">\n" +
                "                <img src=\"data:image/jpg;base64," + flowers[i].image + "\">\n" +
                "            </div>\n" +
                "            <div class=\"card-content\">\n" +
                "                <span class=\"card-title black-text\">" + flowers[i].name + "</span>\n" +
                "                <p>" + flowers[i].price + " $</p>\n" +
                "            </div>\n" +
                "            <div class=\"card-action\">\n" +
                "                <a class='btn go-flower' id=\"" + flowers[i].id + "\">info</a>\n" +
                "                <a class='btn buy-flower' id=\"" + flowers[i].id + "\">buy</a>\n" +
                "            </div>\n" +
                "         </div>");
        }
    });

    req.open('GET', 'api/flower/list', false);
    req.send();
    console.log(req.status);
    flowers = JSON.parse(req.responseText);
    console.log(flowers);

    for (let i = 0; i < flowers.length; i++) {
        $('.cards').append(
            "        <div class=\"card hoverable medium col s3\">\n" +
            "            <div class=\"card-image\">\n" +
            "                <img src=\"data:image/jpg;base64," + flowers[i].image + "\">\n" +
            "            </div>\n" +
            "            <div class=\"card-content\">\n" +
            "                <span class=\"card-title black-text\">" + flowers[i].name + "</span>\n" +
            "                <p>" + flowers[i].price + " $</p>\n" +
            "            </div>\n" +
            "            <div class=\"card-action\">\n" +
            "                <a class='btn go-flower' id=\"" + flowers[i].id + "\">info</a>\n" +
            "                <a class='btn buy-flower' id=\"" + flowers[i].id + "\">buy</a>\n" +
            "            </div>\n" +
            "         </div>");
    }
    $('body').on("click", ".go-flower", function () {
        let flowerId = $(this).attr('id');
        console.log(flowerId)
        window.localStorage.setItem("flowerId", flowerId)
        window.location.href = "/flower";
    })
    $('body').on("click", ".buy-flower", function () {
        let flowerId = $(this).attr('id');
        req.open('POST', 'api/cart/add?flowerId=' + flowerId, false);
        req.send();
        console.log(req.status);
        window.location.href = "/main";
    })
});