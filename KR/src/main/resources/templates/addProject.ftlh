<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/static/css/style.css">
    <title>Создать проект</title>
</head>
<body>
<div class="adaptive">
    <form class="formBackground" action="/project/add" method="post">
        <h1>Создать новый проект</h1>
        <div class="insForm">
            <div class="marg">
                <label>
                    <input type="text" name="projectName" placeholder="Введите название">
                </label>
            </div>

            <div class="AAA">
                <label>
                    <textarea name="description" placeholder="Введите описание"></textarea>
                </label>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <div class="aaa">
                <button class="btnIn" type="submit">Создать проект</button>
            </div>
            <a class="btn-back-form" href="/project">Назад</a>
        </div>
    </form>
</div>