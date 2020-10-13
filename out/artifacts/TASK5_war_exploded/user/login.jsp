<%@ page import="db.AuthToken" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Aralasu | Sign in</title>
    <%@include file="../includes/head.jsp"%>
</head>
<body class="body-<%=theme%>">

<div class="wrap" style="width: 100%; min-height: 100%; display: flex; flex-direction: column; overflow: hidden">
    <%@include file="../includes/navbar.jsp"%>

    <div id="main" class="container mt-5 pt-5 pb-5" style="flex: 1 1 auto">
        <h1 class="font-weight-bold text-center">Sign In</h1>

        <%@include file="../includes/alert/alert.jsp"%>

        <form class="col-6 offset-3 mt-5" action="/login" method="post">
            <div class="form-group">
                <label for="emailInput">Email address</label>
                <input name="email" type="email" class="form-control bg-theme-<%=theme%>" id="emailInput" aria-describedby="emailHelp" required>
                <small id="emailHelp" class="form-text text-muted">Input your email</small>
            </div>

            <div class="form-group">
                <label for="passwordInput">Password</label>
                <input name="password" type="password" class="form-control bg-theme-<%=theme%>" id="passwordInput" aria-describedby="passwordHelp" required minlength="1">
                <small id="passwordHelp" class="form-text text-muted">Input your password</small>
            </div>

            <div class="form-group form-check">
                <input name="remember" value="checked" type="checkbox" class="form-check-input" id="rememberCheck">
                <label class="form-check-label" for="rememberCheck">Remember me</label>
            </div>

            <button type="submit" class="btn btn-primary">Submit</button>
            <a class="btn btn-outline-primary float-right" href="<%=(String)request.getAttribute("vkoauth")%>">Sign in by VK</a>
        </form>
    </div>
    <%@include file="../includes/footer.jsp"%>
</div>

</body>
</html>