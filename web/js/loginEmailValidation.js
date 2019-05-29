$(document).ready(function () {
});
var pattern = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
var email = $('#email');
email.blur(function () {
    if (email.val() != '') {
        if (email.val().search(pattern) == 0) {
            $('#loginValid').text('Подходит');
            $('#login-submit').attr('disabled', false);
            email.removeClass('error').addClass('ok');
        } else {
            $('#loginValid').text('Не подходит');
            $('#login-submit').attr('disabled', true);
            email.addClass('ok');
        }
    } else {
        $('#loginValid').text('Поле e-email не должно быть пустым!');
        email.addClass('error');
        $('#login-submit').attr('disabled', true);
    }
});
