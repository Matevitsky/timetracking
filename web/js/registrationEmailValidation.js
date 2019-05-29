$(document).ready(function () {
});
var pattern = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
var email = $('#emailRegistration');
email.blur(function () {
    if (email.val() != '') {
        if (email.val().search(pattern) == 0) {
            $('#registrationValid').text('Подходит');
            $('#register-submit').attr('disabled', false);
            email.removeClass('error').addClass('ok');
        } else {
            $('#registrationValid').text('Не подходит');
            $('#register-submit').attr('disabled', true);
            email.addClass('ok');
        }
    } else {
        $('#registrationValid').text('Поле e-email не должно быть пустым!');
        email.addClass('error');
        $('#register-submit').attr('disabled', true);
    }
});