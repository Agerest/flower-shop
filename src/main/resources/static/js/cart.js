$(function () {
    req.open('GET', 'api/cart/get', false);
    req.send();
    console.log(req.status);
    let cart = JSON.parse(req.responseText);
    console.log(cart);
    for (let i = 0; i < cart.length; i++) {
        $('.collection').append(
            '            <li class="collection-item avatar">\n' +
            '                <img class="circle" height="45" width="45" src="data:image/jpg;base64,' + cart[i].image + '">\n' +
            '                <p class="title left-align">' + cart[i].flower + '</p>\n' +
            '                <p class="left-align">' + cart[i].price + ' $</p>\n' +
            '                <p class="secondary-content">' + cart[i].count + '</p>\n' +
            '                <a id="' + cart[i].flowerId + '" class="delete-flower btn waves-effect waves-light">delete one\n' +
            '                   <i class="material-icons right">clear</i>\n' +
            '                </a>\n' +
            '            </li>');
    }

    $('body').on("click", ".delete-flower", function () {
        let flowerId = $(this).attr('id');
        req.open('DELETE', 'api/cart/delete?flowerId=' + flowerId, false);
        req.send();
        console.log(req.status);
        window.location.href = "/cart";
    });

    $('#delete-all').click(function () {
        req.open('DELETE', 'api/cart/delete/all', false);
        req.send();
        console.log(req.status);
        window.alert("You have emptied your cart");
        window.location.href = "/main";
    });

    $('#buy').click(function () {
        req.open('DELETE', 'api/cart/delete/all', false);
        req.send();
        console.log(req.status);
        window.alert("Your order is ready");
        window.location.href = "/main";
    });
});