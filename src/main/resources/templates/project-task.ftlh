<!DOCTYPE html>
<html lang="ru">
<head>
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="icon" href="/static/favicon.ico" type="image/x-icon">
    <title>${project.projectName} - Задачи</title>
</head>

<body>
<div class="project-task-str">
    <div class="nav-bar">
        <a class="btn-add" href="/projects/${project.projectId}/members">Добавить участника</a>
        <a class="btn-back" href="/project">Назад</a>
    </div>
    <div class="main-v">
        <div class="head-main-task">
            <div class="ehai">
                <div class="name-proj-task">
                    <div class="name-project">
                        <h1>${project.projectName}</h1>
                    </div>
                    <div class="dir-project">
                        <p>${project.description!''}</p>
                    </div>
                </div>
            </div>
            <div class="head-main-task-active-button">
                <a class="btn-add-add" href="/projects/${project.projectId}/addTask">Создать задачу</a>
            </div>
        </div>
        <div class="palochka"> </div>
        <div class="grid-task">
            <div>
                <div class="ToDo">
                    <h5>To Do</h5>
                </div>
                <#list tasks as tas>
                    <#if tas.status == 'TODO'>
                        <div class="task-card">
                            <div class="padding-title">
                                <h1>${tas.title}</h1>
                            </div>
                            <div class="padding-dis">
                                <h5><#if tas.description??>${tas.description}<#else>Нет описания</#if></h5>
                            </div>
                            <h6><#if tas.formattedDeadline??>До: ${tas.formattedDeadline}<#else>Неограничено</#if></h6>

                            <div class="div-btn-task-card">
                                <form action="/tasks/${tas.taskId}/status" method="post">
                                    <input type="hidden" name="projectId" value="${project.projectId}">
                                    <label class="wind">
                                        <select class="wind-vuipad" name="status" onchange="this.form.submit()">
                                            <#list TaskStatusValues as st>
                                                <option value="${st}" ${(st == tas.status)?then('selected', '')}>
                                                    ${st}
                                                </option>
                                            </#list>
                                        </select>
                                    </label>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                </form>
                                <form action="/tasks/${tas.taskId}/delete" method="post">
                                    <input type="hidden" name="projectId" value="${project.projectId}">
                                    <input type="submit" value="Удалить"/>

                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                </form>
                            </div>
                        </div>
                    </#if>
                </#list>
            </div>

            <div>
                <div class="Doing">
                    <h5>Doing</h5>
                </div>
                <#list tasks as tas>
                    <#if tas.status == 'DOING'>
                        <div class="task-card">
                            <div class="padding-title">
                                <h1>${tas.title}</h1>
                            </div>
                            <div class="padding-dis">
                                <h5><#if tas.description??>${tas.description}<#else>Нет описания</#if></h5>
                            </div>
                            <h6><#if tas.formattedDeadline??>До: ${tas.formattedDeadline}<#else>Неограничено</#if></h6>
                            <div class="div-btn-task-card">
                                <form action="/tasks/${tas.taskId}/status" method="post">
                                    <input type="hidden" name="projectId" value="${project.projectId}">
                                    <label class="wind">
                                        <select class="wind-vuipad" name="status" onchange="this.form.submit()">
                                            <#list TaskStatusValues as st>
                                                <option value="${st}" ${(st == tas.status)?then('selected', '')}>
                                                    ${st}
                                                </option>
                                            </#list>
                                        </select>
                                    </label>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                </form>
                                <form action="/tasks/${tas.taskId}/delete" method="post">
                                    <input type="hidden" name="projectId" value="${project.projectId}">
                                    <input type="submit" value="Удалить"/>

                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                </form>
                            </div>
                        </div>
                    </#if>
                </#list>
            </div>
            <div>
                <div class="Done">
                    <h5>Done</h5>
                </div>
                <#list tasks as tas>
                        <#if tas.status == 'DONE'>
                            <div class="task-card">
                                <div class="padding-title">
                                    <h1>${tas.title}</h1>
                                </div>
                                <div class="padding-dis">
                                    <h5><#if tas.description??>${tas.description}<#else>Нет описания</#if></h5>
                                </div>
                                <h6><#if tas.formattedDeadline??>До: ${tas.formattedDeadline}<#else>Неограничено</#if></h6>
                                <div class="div-btn-task-card">
                                    <form action="/tasks/${tas.taskId}/status" method="post">
                                        <input type="hidden" name="projectId" value="${project.projectId}">
                                        <label class="wind">
                                            <select class="wind-vuipad" name="status" onchange="this.form.submit()">
                                                <#list TaskStatusValues as st>
                                                    <option value="${st}" ${(st == tas.status)?then('selected', '')}>
                                                        ${st}
                                                    </option>
                                                </#list>
                                            </select>
                                        </label>
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                    </form>
                                    <form action="/tasks/${tas.taskId}/delete" method="post">
                                        <input type="hidden" name="projectId" value="${project.projectId}">
                                        <input type="submit" value="Удалить"/>

                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                    </form>
                                </div>
                            </div>
                        </#if>
                </#list>
            </div>
        </div>
    </div>
</div>



</body>
</html>