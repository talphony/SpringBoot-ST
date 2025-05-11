<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>KR - Вход</title>
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
<div class="adaptive">
    <form class="formBackground" action="/login" method="post">
        <h1>Вход</h1>
        <div class="insForm">
            <div class="marg">
                <label>
                    <input type="text" name="username" placeholder="Введите логин" required>
                </label>
            </div>
            <div class="AAA">
                <label >
                    <input type="password" name="password" placeholder="Введите пароль" required>
                </label>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <button class="btnIn" type="submit">Войти</button>
        </div>
        <div class="btnReg">
            <p>Нет аккаунта? </p>
            <a href="/registration">Зарегистрируйтесь</a>
        </div>
    </form>
</div>
</body>
</html>