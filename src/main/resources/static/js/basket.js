$(function () {
    req.open('GET', 'api/basket/get', false);
    req.send();
    console.log(req.status);
    let basket = JSON.parse(req.responseText);
    console.log(basket);
    for (let i = 0; i < basket.length; i++) {
        $('.collection').append(
            '            <li class="collection-item avatar">\n' +
            '                <img class="circle" height="45" width="45" src="data:image/jpg;base64,' + basket[i].image + '">\n' +
            '                <p class="title left-align">' + basket[i].flower + '</p>\n' +
            '                <p class="left-align">' + basket[i].price + ' $</p>\n' +
            '                <p class="secondary-content">' + basket[i].count + '</p>\n' +
            '                <a id="delete-flower" class="btn waves-effect waves-light">delete one\n' +
            '                <i class="material-icons right">clear</i></a>\n' +
            '            </li>');
    }
});