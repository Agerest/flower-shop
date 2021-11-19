var categories;

$(function () {
    req.open('GET', 'api/category/list', false);
    req.send();
    console.log(req.status);
    categories = JSON.parse(req.responseText);
    console.log(categories);

    req.open('GET', 'api/flower/list', false);
    req.send();
    console.log(req.status);
    let flowers = JSON.parse(req.responseText);
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
            "                <a class='btn' id=\"" + flowers[i].id + "\">go</a>\n" +
            "            </div>\n" +
            "         </div>");
    }
});