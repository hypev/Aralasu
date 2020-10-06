<%@ page import="db.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="db.DBManager" %><%
    User user = (User)request.getAttribute("user");
    ArrayList<User> usersBirthday = DBManager.getLastUsersByBirthDateOrder(5);
    String pictureUrl = user.getPictureUrl();
    if (user.getPictureUrl().equals("")) pictureUrl = "https://www.flaticon.com/svg/static/icons/svg/847/847969.svg";
%>
<div class="mt-5 pt-5" style="position: fixed; width: 100%; top: 0">
    <div class="container">
        <div class="row">
            <div class="col-3">
                <div class="avatar bg-theme-<%=theme%>">
                    <img src="<%=pictureUrl%>" alt="Avatar">
                </div>

                <div class="card mt-5 bg-theme-<%=theme%>">
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item font-weight-bold bg-theme-<%=theme%>">
                            <%=user.getFullName()%>, <%=user.getAge()%> years
                        </li>

                        <li class="list-group-item font-weight-bold bg-theme-<%=theme%>">
                            <a class="text-primary" href="/profile"><i class="fas fa-id-card mr-2"></i> My Profile</a>
                        </li>

                        <li class="list-group-item font-weight-bold bg-theme-<%=theme%>">
                            <a class="text-primary" href="/settings"><i class="fas fa-cogs mr-2"></i> Settings</a>
                        </li>

                        <li class="list-group-item font-weight-bold bg-theme-<%=theme%>">
                            <a class="text-danger" href="/logout"><i class="fas fa-sign-out-alt mr-2"></i>Logout</a>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="col-3 offset-6">
                <div class="card bg-theme-<%=theme%>" style="width: 18rem;">
                    <div class="card-header bg-info text-white font-weight-bold bg-theme-<%=theme%>">
                        Latest birthdays
                    </div>
                    <ul class="list-group list-group-flush">
                        <%  for (User u : usersBirthday) {   %>
                            <li class="list-group-item border-0 bg-theme-<%=theme%>"><%=u.getFullName()%>, <%=u.getBirthday()%></li>
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
            </div>
        </div>
    </div>
</div>