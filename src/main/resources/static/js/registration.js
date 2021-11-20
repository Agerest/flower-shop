$(function () {
    $('#registration').click(function () {
        let username = $("#username").val();
        let email = $("#email").val();
        let password = $("#password").val();
        let matchingPassword = $("#confirm").val();
        let body = {};
        body.username = username;
        body.email = email;
        body.password = password;
        body.matchingPassword = matchingPassword;
        console.log(body)
        req.open('POST', 'api/registration/new-user', false);
        req.setRequestHeader("Content-type", "application/json;charset=UTF-8");
        req.send(JSON.stringify(body));
        console.log(req.status);
        if (req.status === 204) {
            window.location.href = "/main";
        } else {
            $('.check-info').remove();
            $('.last-item').append("<h5 class=\"red-text check-info center-align\">check input data</h5>");
        }
    });
})