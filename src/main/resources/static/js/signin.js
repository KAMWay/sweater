function signin() {
    var username = $("#username").val();
    var password = $("#password").val();

    $.post("login", {
        "username": username,
        "password": password
    })
        // .done(function (data) {
        //     alert("Data Loaded: " + data);
        //     $("#result").empty().append(data);
        // });
}