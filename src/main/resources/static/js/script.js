// user
var user_Boolean = false;
var password_Boolean = false;
var varconfirm_Boolean = false;
var emaile_Boolean = false;
var Mobile_Boolean = false;
var red_button1 = true;
var red_button2 = true;
var red_button3 = true;
var red_button4 = true;
var red_button = true;
$('.reg_user').blur(function () {
    if ((/^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]{4,8}$/).test($(".reg_user").val())) {
        $('.user_hint').html("✔").css("color", "green");
        user_Boolean = true;
        red_button1 = true;
    } else {
        $('.user_hint').html("×").css("color", "red");
        user_Boolean = false;
        red_button1 = false;
    }
});
// password
$('.reg_password').blur(function () {
    if ((/^[a-z0-9_-]{6,16}$/).test($(".reg_password").val())) {
        $('.password_hint').html("✔").css("color", "green");
        password_Boolean = true;
        red_button2 = true;
    } else {
        $('.password_hint').html("×").css("color", "red");
        password_Boolean = false;
        red_button2 = false;
    }
});


// password_confirm
$('.reg_confirm').blur(function () {
    if (($(".reg_password").val()) == ($(".reg_confirm").val())) {
        $('.confirm_hint').html("✔").css("color", "green");
        varconfirm_Boolean = true;
        red_button3 = true;
    } else {
        $('.confirm_hint').html("×").css("color", "red");
        varconfirm_Boolean = false;
        red_button3 = false;
    }
});


// Email
$('.reg_email').blur(function () {
    if ((/^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/).test($(".reg_email").val())) {
        $('.email_hint').html("✔").css("color", "green");
        emaile_Boolean = true;
        red_button4 = true;
    } else {
        $('.email_hint').html("×").css("color", "red");
        emaile_Boolean = false;
        red_button4 = false;

    }
});


// Mobile
$('.reg_mobile').blur(function () {
    if ((/^1[34578]\d{9}$/).test($(".reg_mobile").val())) {
        $('.mobile_hint').html("✔").css("color", "green");
        Mobile_Boolean = true;
        red_button = true;
    } else {
        $('.mobile_hint').html("×").css("color", "red");
        Mobile_Boolean = false;
        red_button = false;
    }
});


// click
$('.red_button').click(function () {
    if (red_button4 && red_button3 && red_button2 && red_button1 && red_button && user_Boolean && password_Boolea && varconfirm_Boolean && emaile_Boolean && Mobile_Boolean == true) {

    } else {
    }
});
