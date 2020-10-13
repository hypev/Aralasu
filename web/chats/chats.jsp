<%@ page import="db.Chat" %>
<%@ page import="db.Message" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Chat> chats = (ArrayList<Chat>)request.getAttribute("chats");
    ArrayList<Message> messages = (ArrayList<Message>)request.getAttribute("messages");
    String search = (String)request.getParameter("message");
%>
<!DOCTYPE HTML>
<html>
<head>
    <title>Aralasu | Chats</title>
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
                    <form method="get" action="/search-message" class="d-flex align-items-center justify-content-between">
                        <div class="form-group" style="width: 60%; margin-bottom: 0">
                            <input name="message" type="text" class="form-control bg-theme-<%=theme%>" placeholder="Search Message">
                        </div>
                        <button type="submit" class="btn btn-outline-primary" style="width: 30%">Search</button>
                    </form>
                </div>
            </div>

            <% if (search != null) { %>
            <div class="card bg-theme-<%=theme%> mt-4">
                <div class="card-body">
                    <h4>Search results for: "<%=search%>"</h4>
                </div>
            </div>
            <% if (messages != null) { %>
                <% for(Message m : messages) { %>
            <div class="card mt-4 bg-theme-<%=theme%> msg">
                <a href="/chat?id=<%=DBManager.getChatBetween(m.getUser().getId(), m.getSender().getId()).getId()%>" class="card-body d-flex align-items-center">
                    <img class="rounded-circle" src="<%=m.viewPoint(user).getPictureUrl()%>" alt="Avatar" style="width: 100px; height: 100px; padding: 0; border: 0">
                    <div class="d-flex flex-column justify-content-between align-items-start ml-4" style="flex:1 1 auto;">
                        <h6><%=m.viewPoint(user).getFullName()%></h6>
                        <p><%=m.getMessage()%></p>
                    </div>
                    <p class="align-self-start mt-2"><%=m.getDatetime()%></p>
                </a>
            </div>
                <% } %>
            <% } %>
            <% } else { %>
            <% if (chats != null) { %>
                <% for(Chat c : chats) { %>
            <div class="card mt-4 bg-theme-<%=theme%> msg">
                <a href="/chat?id=<%=c.getId()%>" class="card-body d-flex align-items-center">
                    <img class="rounded-circle" src="<%=c.viewPoint(user).getPictureUrl()%>" alt="Avatar" style="width: 100px; height: 100px; padding: 0; border: 0">
                    <div class="d-flex flex-column justify-content-between align-items-start ml-4" style="flex:1 1 auto;">
                        <h6><%=c.viewPoint(user).getFullName()%></h6>
                        <p><span class="font-weight-bold"><%=c.lastSender(user)%></span>
                            <%
                                if (c.getLatestMsgText().length() > 15) {
                            %>
                                <%=c.getLatestMsgText().substring(0, 15) + "..."%>
                            <%
                                } else {
                            %>
                                <%=c.getLatestMsgText()%>
                            <%
                                }
                            %>
                            <% if (c.getUnreadMsgCount(user) != 0) { %>
                            <span class="badge badge-pill badge-primary" style="background-color:#007bff !important;"><%=c.getUnreadMsgCount(user)%></span>
                            <% } %>
                        </p>
                    </div>
                    <p class="align-self-start mt-2"><%=c.getTime()%></p>
                </a>
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
