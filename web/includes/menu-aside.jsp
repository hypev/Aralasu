<div class="card bg-theme-<%=theme%>" style="width: 18rem;">
    <div class="card-header bg-info text-white font-weight-bold bg-theme-<%=theme%>">
        Latest birthdays
    </div>
    <ul class="list-group list-group-flush">
        <%  for (User u : usersBirthday) {   %>
        <li class="list-group-item border-0 bg-theme-<%=theme%>">
            <a href="/profile?id=<%=u.getId()%>" class="text-primary"><%=u.getFullName()%></a>
            <%=u.getBirthday()%>
        </li>
        <%  }   %>
    </ul>
</div>

<div class="card mt-3 bg-theme-<%=theme%>" style="width: 18rem;">
    <div class="card-header bg-info text-white font-weight-bold bg-theme-<%=theme%>">
        My Games
    </div>
    <ul class="list-group list-group-flush bg-theme-<%=theme%>">
        <li class="list-group-item font-weight-bold border-0 bg-theme-<%=theme%>">
            <a href="#" class="text-primary"><i class="far fa-futbol mr-2"></i> FOOTBALL ONLINE</a>
        </li>
        <li class="list-group-item font-weight-bold border-0 bg-theme-<%=theme%>">
            <a href="#" class="text-primary"><i class="fas fa-chess mr-2"></i> PING PONG ONLINE</a>
        </li>
        <li class="list-group-item font-weight-bold border-0 bg-theme-<%=theme%>">
            <a href="#" class="text-primary"><i class="fas fa-table-tennis mr-2"></i>CHESS MASTERS</a>
        </li>
        <li class="list-group-item font-weight-bold border-0 bg-theme-<%=theme%>">
            <a href="#" class="text-primary"><i class="fas fa-car mr-2"></i>RACES ONLINE</a>
        </li>
    </ul>
</div>