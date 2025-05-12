<!DOCTYPE html>
<html lang="ru">
<head>
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="icon" href="/static/favicon.ico" type="image/x-icon">
    <title>Добавить задачу</title>
</head>
<body>
<div class="adaptive">
    <form class="formBackground" action="/tasks/add" method="post">
        <h1>Добавить задачу</h1>
        <div class="insForm">
            <input type="hidden" name="projectId" value="${project.projectId}">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <div class="marg">
                <label>
                    <input type="text" name="title" placeholder="Введите название" required>
                </label>
            </div>
            <div class="marg">
                <label>
                    <textarea name="description" placeholder="Введите описание"></textarea>
                </label>
            </div>
            <div class="aaa">
                <label>
                    <input type="datetime-local" name="deadline">
                </label>
            </div>
            <div class="aaa">
                <button class="btnIn" type="submit">Добавить задачу</button>
            </div>
            <a class="btn-back-form" href="/projects/${project.projectId}/tasks">Назад</a>
        </div>
    </form>

</div>
</body>
</html>