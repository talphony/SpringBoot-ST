<!DOCTYPE html>
<html lang="ru">
<head>
    <title>KR - Регистрация</title>
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>

<#if errorMessage??>
    <div class="error">
        ${errorMessage}
    </div>
</#if>
<div class="adaptive">
    <form class="formBackground" action="/registration" method="post">
        <h1>Регистрация</h1>
        <div class="insForm">
            <div class="marg">
                <label>
                    <input class="error" type="text" name="username" placeholder="Имя пользователя" required>
                </label>
            </div>
            <div class="marg">
                <label>
                    <input type="email" name="email" placeholder="Email" required>
                </label>
            </div>
            <div class="marg">
                <label>
                    <input type="text" name="full_Name" placeholder="Имя">
                </label>
            </div>
            <div class="AAA">
                <label >
                    <input type="password" name="password" placeholder="Пароль" required>
                </label>
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button class="btnIn" type="submit">Регистрация</button>
        </div>
        <div class="btnReg">
            <p>Есть аккаунт? </p>
            <a href="/login">Войти</a>
        </div>
    </form>

</div>
</body>
</html>