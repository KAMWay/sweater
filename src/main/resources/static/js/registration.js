function registration() {
    var fullName = $("#fullName").val();
    var email = $("#email").val();
    var phoneCountry = $("#phoneCountry").val();
    var phone = $("#phone").val();
    var job = $("#job").val();
    var password = $("#password").val();
    var confirm = $("#confirm").val();

    $.post("registration", {
        "fullName": fullName,
        "email": email,
        "phoneCountry": phoneCountry,
        "phone": phone,
        "job": job,
        "password": password,
        "confirm": confirm
    })
        // .done(function (data) {
        //     alert("Data Loaded: " + data);
        //     $("#result").empty().append(data);
        // });
}