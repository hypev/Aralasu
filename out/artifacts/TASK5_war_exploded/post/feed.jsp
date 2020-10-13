<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="db.Post" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Aralasu | Feed</title>
    <%@include file="../includes/head.jsp"%>
</head>
<body class="body-<%=theme%>">

<div class="wrap" style="width: 100%; min-height: 100%; display: flex; flex-direction: column; overflow: hidden">
    <%@include file="../includes/navbarAuthorized.jsp"%>
    <%@include file="../includes/menu.jsp"%>
    <%@include file="../includes/modals/addPostModal.jsp"%>

    <div id="main" class="container mt-5 pt-5 pb-5" style="flex: 1 1 auto">
        <div class="col-6 offset-3">
            <% if (userProfile.getId().equals(user.getId())) { %>
                <%@include file="../includes/alert/alert.jsp"%>
                <button class="btn btn-outline-primary text-center mb-4" data-toggle="modal" data-target="#postModal" style="width: 100%">
                    <i class="fas fa-plus-square mr-2"></i> Add new
                </button>
            <% } %>
        <%
            ArrayList<Post> posts = (ArrayList<Post>)request.getAttribute("posts");
            if (posts != null) {
                for (Post p : posts) {
        %>
            <div class="card bg-theme-<%=theme%> mb-4">
                <div class="card-header d-flex align-items-center">
                    <img class="rounded-circle" src="<%=p.getUser().getPictureUrl()%>" alt="Avatar" style="width: 50px; height: 50px; padding: 0; border: 0">
                    <div class="d-flex flex-column ml-3">
                        <a href="/profile?id=<%=p.getUser().getId()%>" class="text-primary"><%=p.getUser().getFullName()%></a>
                        <p style="margin: 0; font-size: 14px"><%=p.getDate()%></p>
                    </div>
                </div>
                <div class="card-body">
                    <h5 class="card-title"><%=p.getTitle()%></h5>
                    <p class="card-text">
                        <%=p.getShortContent()%>
                    </p>
                    <a href="/post?id=<%=p.getId()%>" class="btn btn-outline-primary">More <i class="fas fa-long-arrow-alt-right ml-2"></i></a>
                </div>
            </div>
        <%
                }
            }
        %>

        </div>
    </div>

    <%@include file="../includes/footer.jsp"%>
</div>

</body>
</html>
