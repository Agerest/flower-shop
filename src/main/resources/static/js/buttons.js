var serverId;
var channelId;

$(function () {
    $('#add-flower').click(function () {
        $('#index').empty();
        $('#index').append(
            '    <div class="container">\n' +
            '        <h2 class="center-align">CREATE FLOWER</h2>\n' +
            '        <form class="col12">\n' +
            '            <div class="row">\n' +
            '                <div class="input-field col s12">\n' +
            '                    <i class="material-icons prefix">info</i>\n' +
            '                    <input id="name" name="name" type="text" autofocus required>\n' +
            '                    <label for="name">name</label>\n' +
            '                </div>\n' +
            '            </div>\n' +
            '            <div class="row">\n' +
            '                <div class="input-field col s12">\n' +
            '                    <i class="material-icons prefix">info</i>\n' +
            '                    <input id="description" name="description" type="text" required>\n' +
            '                    <label for="description">description</label>\n' +
            '                </div>\n' +
            '            </div>\n' +
            '            <div class="row">\n' +
            '                <div class="input-field col s12">\n' +
            '                    <i class="material-icons prefix">info</i>\n' +
            '                    <input id="price" name="price" type="text" required>\n' +
            '                    <label for="price">price</label>\n' +
            '                </div>\n' +
            '            </div>\n' +
            '            <div class="row">\n' +
            '               <div id="select" class="input-field col s12">\n' +
            '                   <select id="category" class="browser-default">\n' +
            '                       <option value="" disabled selected>Choose your category</option>\n' +
            '                   </select>\n' +
            '               </div>\n' +
            '            </div>\n' +
            '            <div class="row last-item">\n' +
            '                <div class="file-field input-field col s12">\n' +
            '                    <div class="btn">\n' +
            '                        <span>Browse</span>\n' +
            '                        <input id="photo1" type="file" name="photo" accept="Image/*">\n' +
            '                        <label for="photo1"></label>\n' +
            '                    </div>\n' +
            '                    <div class="file-path-wrapper">\n' +
            '                        <input id="photo2" class="file-path validate" type="text" name="photo" placeholder="photo" >\n' +
            '                        <label for="photo2"></label>\n' +
            '                    </div>\n' +
            '                </div>\n' +
            '            </div>\n' +
            '            <div class="center-align">\n' +
            '                <button type="button" id="submit-flower" class="btn waves-effect waves-light">Submit\n' +
            '                    <i class="material-icons right">send</i>\n' +
            '                </button>\n' +
            '            </div>\n' +
            '        </form>\n' +
            '    </div>');
        $.each(categories, function(key, value) {
            $('#category')
                .append($("<option></option>")
                    .attr("value", value.id)
                    .text(value.name));
        });
    });
    $('body').on("click", "#submit-flower", function () {
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
        } else {
            $('.check-info').remove();
            $('.last-item').append("<h5 class=\"red-text check-info center-align\">check input data</h5>");
            return;
        }
        window.location.href = "/";
    });
});