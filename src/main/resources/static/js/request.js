var req = new XMLHttpRequest();

$(function () {
    req.open('GET', 'api/basket/count', false);
    req.send();
    console.log(req.status);
    let count = req.responseText;
    console.log(count);
    $('#top-panel').append('<li><a href="/basket">Basket (' + count + ')</a></li>');
})