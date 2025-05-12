<!DOCTYPE html>
<html lang="ru">
<head>
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="icon" href="/static/favicon.ico" type="image/x-icon">
    <title>Мои проекты</title>
</head>
<body>
    <div class="ad">
        <div class="background-add-member">
            <form class="form-add" action="/projects/${project.projectId}/members/add" method="post">
                <#if isLead>
                    <h1 class="h1-mod">Добавить участников</h1>
                    <div class="form-group-add">
                        <h5 class="h5-mod">Роль участника:</h5>
                        <label>
                            <select class="wind-vuipad" name="role" class="form-control" required>
                                <#list roles as role>
                                    <option value="${role}">${role.name()}</option>
                                </#list>
                            </select>
                        </label>
                    </div>
                    <div class="form-add-member">
                        <div class="form-group">
                            <label class="mod-label">
                                <input type="text" name="userIdentifier" class="form-control" placeholder="Логин" required>
                            </label>
                        </div>
                        <button class="btn-a-add-member" type="submit">Добавить</button>
                    </div>

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                </form>
            </#if>
            <h1 class="h1-mod">Текущие участники</h1>
            <ul>
                <#list members as member>
                    <#if member.username??>
                        <li class="list-group-item">
                            <div class="spbtw">
                                <div>
                                    ${member.username!''} (${member.email!''})
                                </div>
                                <div>
                                    <#list membersGetRole as mgr>
                                        <#if member.id == mgr.userId>
                                            ${mgr.role.displayName}
                                        </#if>
                                    </#list>
                                </div>
                            </div>
                            <#if isLead && member.id != project.createdBy.id && member.id != currentUser.id>
                                <form class="bb" action="/projects/${project.projectId}/members/${member.id}/remove" method="post">
                                    <button type="submit" class="btn-a-add-member-delete">Удалить</button>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                </form>
                            </#if>
                        </li>
                    </#if>
                </#list>
            </ul>

            <a class="btn-back-form" href="/projects/${project.projectId}/tasks">Назад</a>
        </div>
    </div>
</body>