<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<User> friends = (ArrayList<User>)request.getAttribute("friends");
    String search = (String)request.getParameter("fullname");
%>
<!DOCTYPE HTML>
<html>
<head>
    <title>Aralasu | Friends</title>
    <%@include file="../includes/head.jsp"%>
</head>
<body class="body-<%=theme%>">

<div class="wrap" style="width: 100%; min-height: 100%; display: flex; flex-direction: column; overflow: hidden">
    <%@include file="../includes/navbarAuthorized.jsp"%>
    <%@include file="../includes/menu.jsp"%>

    <div id="main" class="container mt-5 pt-5 pb-5" style="flex: 1 1 auto">
        <div class="col-6 offset-3">
            <%@include file="../includes/alert/alert.jsp"%>
            <div class="card bg-theme-<%=theme%>">
                <div class="card-body">
                    <form method="get" action="/search-friend" class="d-flex align-items-center justify-content-between">
                        <div class="form-group" style="width: 60%; margin-bottom: 0">
                            <input name="fullname" type="text" class="form-control bg-theme-<%=theme%>" placeholder="Search Friends">
                        </div>
                        <button type="submit" class="btn btn-outline-primary" style="width: 30%">Search</button>
                    </form>
                </div>
            </div>
            <% if (search != null) { %>
            <div class="card bg-theme-<%=theme%> mt-4">
                <div class="card-body">
                    <% if (search.equals("")) { %>
                    <h4>All users</h4>
                    <% } else { %>
                    <h4>Search results for: "<%=search%>"</h4>
                    <% } %>
                </div>
            </div>
            <% } %>
            <% if (friends != null) { %>
                <% for(User u : friends) { %>
                    <% if (!user.getId().equals(u.getId())) { %>
                    <% String status = DBManager.getStatusBetween(user, u); %>
                    <div class="card mt-4 bg-theme-<%=theme%>">
                        <div class="card-body d-flex align-items-center">
                            <img class="rounded-circle" src="<%=u.getPictureUrl()%>" alt="Avatar" style="width: 100px; height: 100px; padding: 0; border: 0">
                            <div class="d-flex flex-column justify-content-between align-items-start ml-4">
                                <h5><a href="/profile?id=<%=u.getId()%>" class="text-primary"><%=u.getFullName()%></a></h5>
                                <p><%=u.getAge()%> years old</p>
                                <% if (status.equals("friends")) { %>
                                    <button onclick="sendMessageTo(<%=u.getId()%>, `<%=u.getFullName()%>`)" class="btn btn-outline-primary btn-sm" data-toggle="modal" data-target="#sendMessage" ><i class="fab fa-telegram-plane mr-1"></i> Send message</button>
                                <% } else if(status.equals("request")) { %>
                                    <form action="/friend-request" method="post" class="d-flex align-items-center">
                                        <input type="hidden" name="requesterId" value="<%=u.getId()%>">
                                        <button class="btn btn-outline-primary btn-sm" name="Confirm"><i class="fas fa-plus-circle mr-1"></i> Confirm</button>
                                        <button class="btn btn-outline-primary btn-sm ml-2" name="Reject"><i class="fas fa-trash-alt mr-1"></i> Reject</button>
                                    </form>
                                <% } else if(status.equals("sent")) { %>
                                    <button class="btn btn-outline-primary btn-sm" disabled><i class="fas fa-check mr-1"></i> Request Sent</button>
                                <% } else { %>
                                    <form action="/friend-add" method="post">
                                        <input type="hidden" name="userId" value="<%=u.getId()%>">
                                        <button class="btn btn-outline-primary btn-sm"><i class="fas fa-plus-circle mr-1"></i> Add To Friend</button>
                                    </form>
                                <% } %>
                            </div>
                        </div>
                    </div>
                    <% } %>
                <% } %>
            <% } %>
        </div>
    </div>

    <%@include file="../includes/footer.jsp"%>
</div>

</body>
</html>
