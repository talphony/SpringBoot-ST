<#import "/spring.ftl" as spring>
<form action="/projects/${project.projectId}/members/add" method="post">
    <div class="form-group">
        <label>Email или логин пользователя:</label>
        <label>
            <input type="text" name="userIdentifier" class="form-control" required>
        </label>
    </div>

    <div class="form-group">
        <label>Роль:</label>
        <label>
            <select name="role" class="form-control" required>
                <#list roles as role>
                    <option value="${role}">${role.name()}</option>
                </#list>
            </select>
        </label>
    </div>
    <button type="submit" class="btn btn-primary">Добавить участника</button>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
</form>

<h3>Текущие участники</h3>
<ul class="list-group">
    <#list members as member>
        <#if member.username??>
            <li class="list-group-item">
                ${member.username!''} (${member.email!''})
                <#list membersGetRole as mgr>
                    <#if member.id == mgr.userId>
                        ${mgr.role.displayName}
                    </#if>
                </#list>
                <#if isLead && member.id != project.createdBy.id && member.id != currentUser.id>
                    <form action="/projects/${project.projectId}/members/${member.id}/remove" method="post">
                        <button type="submit" class="btn btn-sm btn-danger">Удалить</button>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    </form>
                </#if>
            </li>
        </#if>
    </#list>
</ul>

<a href="/projects/${project.projectId}/tasks">Назад</a>