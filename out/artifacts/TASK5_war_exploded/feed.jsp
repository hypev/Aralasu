<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="db.Post" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Aralasu | Feed</title>
    <%@include file="includes/head.jsp"%>
</head>
<body class="body-<%=theme%>">

<div class="wrap" style="width: 100%; min-height: 100%; display: flex; flex-direction: column; overflow: hidden">
    <%@include file="includes/navbarAuthorized.jsp"%>
    <%@include file="includes/menu.jsp"%>
    <%@include file="includes/addPostModal.jsp"%>

    <div id="main" class="container mt-5 pt-5 pb-5" style="flex: 1 1 auto">
        <div class="col-6 offset-3">
            <%@include file="includes/alert.jsp"%>
            <button class="btn btn-outline-primary text-center" data-toggle="modal" data-target="#postModal" style="width: 100%">
                <i class="fas fa-plus-square mr-2"></i> Add new
            </button>
        <%
            ArrayList<Post> posts = (ArrayList<Post>)request.getAttribute("posts");
            if (posts != null) {
                for (Post p : posts) {
        %>
            <div class="card mt-4 bg-theme-<%=theme%>">
                <div class="card-body">
                    <h5 class="card-title"><%=p.getTitle()%></h5>
                    <p class="card-text">
                        <%=p.getShortContent()%>
                    </p>
                    <a href="/post?id=<%=p.getId()%>" class="btn btn-outline-primary">More <i class="fas fa-long-arrow-alt-right ml-2"></i></a>
                </div>
                <div class="card-footer text-muted">
                    Posted on <%=p.getDate()%> by <a href="#" class="text-primary"><%=p.getUser().getFullName()%></a>
                </div>
            </div>
        <%
                }
            }
        %>

        </div>
    </div>

    <%@include file="includes/footer.jsp"%>
</div>

</body>
</html>
