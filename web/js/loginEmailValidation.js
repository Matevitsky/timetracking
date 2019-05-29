$(document).ready(function () {
});
var pattern = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
var loginEmail = $('#email');

loginEmail.blur(function () {
    if (loginEmail.val() != '') {
        if (loginEmail.val().search(pattern) == 0) {
            $('#loginValid').text('Подходит');
            $('#login-submit').attr('disabled', false);
            loginEmail.removeClass('error').addClass('ok');
        } else {
            $('#loginValid').text('Не подходит');
            $('#login-submit').attr('disabled', true);
            loginEmail.addClass('ok');
        }
    } else {
        $('#loginValid').text('Поле e-mail не должно быть пустым!');
        loginEmail.addClass('error');
        $('#login-submit').attr('disabled', true);
    }

});