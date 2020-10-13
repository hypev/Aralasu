<%@ page import="db.Chat" %>
<%@ page import="db.Message" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Chat chat = (Chat)request.getAttribute("chat");
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
            <div class="card bg-theme-<%=theme%> mb-4">
                <a class="card-body d-flex align-items-center" href="/profile?id=<%=chat.viewPoint(user).getId()%>">
                    <img class="rounded-circle" src="<%=chat.viewPoint(user).getPictureUrl()%>" style="width: 50px; height: 50px">
                    <h6 class="ml-4"><%=chat.viewPoint(user).getFullName()%></h6>
                </a>
            </div>

            <div id="chat" class="card pt-4 pb-4 bg-theme-<%=theme%> mb-4" style="height: 400px; display: block; overflow-y: scroll">
                <%
                    if (chat.getMessages() != null) {
                        String lastDate = "";
                        for (Message m : chat.getMessages()) {
                %>
                <%
                    if (!m.getDateOnly().equals(lastDate)) {
                %>
                    <p class="msg-d"><%=m.getDateOnly()%></p>
                <%
                        lastDate = m.getDateOnly();
                    }
                    if (chat.getUnreadMsgCount(user) != 0) {
                    if (chat.getMessages().indexOf(m) == chat.getMessages().size() - chat.getUnreadMsgCount(user)) {
                %>
                    <div class="msg-new">
                        <p>New messages</p>
                    </div>
                <%
                        }
                    }
                    if (m.getSender().getId().equals(user.getId())) {
                %>
                    <div class="msg-r">
                        <div class="msg-c">
                            <p><%=m.getMessage()%></p>
                            <span><%=m.getTime()%></span>
                        </div>
                    </div>
                <%
                    } else {
                %>
                    <div class="msg-l">
                        <div class="msg-c">
                            <p><%=m.getMessage()%></p>
                            <span><%=m.getTime()%></span>
                        </div>
                    </div>
                <%          }
                        }
                    }
                %>
            </div>

            <div class="card bg-theme-<%=theme%>">
                <div class="card-body">
                    <form method="post" action="/send-message" class="d-flex align-items-center justify-content-between">
                        <div class="form-group" style="width: 60%; margin-bottom: 0">
                            <input name="message" type="text" class="form-control bg-theme-<%=theme%>" placeholder="Message" required>
                        </div>
                        <input type="hidden" name="user" value="<%=chat.viewPoint(user).getId()%>">
                        <input type="hidden" name="sender" value="<%=user.getId()%>">
                        <button type="submit" class="btn btn-outline-primary" style="width: 30%">Send</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script>
        $('#chat').scrollTop($('#chat')[0].scrollHeight);
    </script>
    <%@include file="../includes/footer.jsp"%>
</div>

</body>
</html>
