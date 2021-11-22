var req = new XMLHttpRequest();

$(function () {
    req.open('GET', 'api/cart/count', false);
    req.send();
    console.log(req.status);
    if (req.status === 200) {
        let count = req.responseText;
        console.log(count);
        $('#top-panel').append('<li><a href="/cart">Cart (' + count + ')</a></li>');
    }
})