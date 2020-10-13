<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="db.Post" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Aralasu | Post Details</title>
    <%@include file="../includes/head.jsp"%>
</head>
<body class="body-<%=theme%>">

<div class="wrap" style="width: 100%; min-height: 100%; display: flex; flex-direction: column; overflow: hidden">
    <%@include file="../includes/navbarAuthorized.jsp"%>
    <%@include file="../includes/menu.jsp"%>

    <div id="main" class="container mt-5 pt-5 pb-5" style="flex: 1 1 auto">
        <div class="col-6 offset-3">
            <%
                Post post = (Post)request.getAttribute("post");
                if (post != null) {
                    if (post.getUser().getId() == user.getId()) {
            %>
            <div class="d-flex justify-content-between">
                <button class="btn btn-outline-primary text-center" data-toggle="modal" data-target="#editModal" style="width: 45%">
                    <i class="fas fa-edit mr-2"></i> Edit
                </button>
                <button class="btn btn-outline-danger text-center" data-toggle="modal" data-target="#deleteModal" style="width: 45%">
                    <i class="fas fa-trash-alt mr-2"></i> Delete
                </button>
            </div>
            <%@include file="../includes/modals/deletePostModal.jsp"%>
            <%@include file="../includes/modals/editPostModal.jsp"%>
            <%
                    }
            %>
            <div class="card mt-4 bg-theme-<%=theme%>">
                <div class="card-header d-flex align-items-center">
                    <img class="img-thumbnail rounded-circle" src="<%=post.getUser().getPictureUrl()%>" alt="Avatar" style="width: 50px; height: 50px; padding: 0; border: 0">
                    <div class="d-flex flex-column ml-3">
                        <a href="#" class="text-primary"><%=post.getUser().getFullName()%></a>
                        <p style="margin: 0; font-size: 14px"><%=post.getDate()%></p>
                    </div>
                </div>
                <div class="card-body">
                    <h5 class="card-title"><%=post.getTitle()%></h5>
                    <p class="card-text">
                        <%=post.getShortContent()%>
                        <%=post.getContent()%>
                    </p>
                </div>
            </div>
            <%
                }
            %>

        </div>
    </div>

    <%@include file="../includes/footer.jsp"%>
</div>

</body>
</html>
