$(function () {
    req.open('GET', 'api/category/list', false);
    req.send();
    console.log(req.status);
    let categories = JSON.parse(req.responseText);
    console.log(categories);
    $.each(categories, function (key, value) {
        $('#category')
            .append($("<option></option>")
                .attr("value", value.id)
                .text(value.name));
    })
    $('#submit-flower').click(function () {
        let imageId;

        let photo = $('#photo1');
        console.log(photo)
        let formData = new FormData();
        let image = photo[0].files[0];
        console.log(image)
        formData.append("image", image)
        req.open('POST', 'api/image/create', false);
        req.send(formData);
        console.log(req.status);
        if (req.status === 200) {
            imageId = req.responseText;
            console.log(imageId)
        } else {
            $('.check-info').remove();
            $('.last-item').append("<h5 class=\"red-text check-info center-align\">check input data</h5>");
            return;
        }

        let flowerId;

        let name = $("#name").val();
        let description = $("#description").val();
        let price = $("#price").val();
        let categoryId = $('#category option:selected').attr('value');
        let body = {};
        body.name = name;
        body.description = description;
        body.imageId = imageId;
        body.price = price
        body.categoryId = categoryId
        console.log(body)
        req.open('POST', 'api/flower/save', false);
        req.setRequestHeader("Content-type", "application/json;charset=UTF-8");
        req.send(JSON.stringify(body));
        console.log(req.status);
        if (req.status === 204) {
            flowerId = req.responseText;
            console.log(flowerId);
            window.location.href = "/main";
        } else {
            $('.check-info').remove();
            $('.last-item').append("<h5 class=\"red-text check-info center-align\">check input data</h5>");
        }
    });
})