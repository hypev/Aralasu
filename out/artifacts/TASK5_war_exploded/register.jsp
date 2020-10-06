<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Aralasu | Create new Account</title>
    <%@include file="includes/head.jsp"%>
</head>
<body class="body-<%=theme%>">

<div class="wrap" style="width: 100%; min-height: 100%; display: flex; flex-direction: column; overflow: hidden">
    <%@include file="includes/navbar.jsp"%>

    <div id="main" class="container mt-5 pt-5 pb-5" style="flex: 1 1 auto">
        <h1 class="font-weight-bold text-center">Create new Account</h1>

        <%@include file="includes/alert.jsp"%>

        <form class="col-6 offset-3 mt-5" action="/register" method="post">
            <div class="form-group">
                <label for="emailInput">Email address</label>
                <input name="email" type="email" class="form-control bg-theme-<%=theme%>" id="emailInput" aria-describedby="emailHelp" required>
                <small id="emailHelp" class="form-text text-muted">Input your email</small>
            </div>

            <div class="form-group">
                <label for="passwordInput">Password</label>
                <input name="password" type="password" class="form-control bg-theme-<%=theme%>" id="passwordInput" aria-describedby="passwordHelp" required>
                <small id="passwordHelp" class="form-text text-muted">Input your password</small>
            </div>

            <div class="form-group">
                <label for="rePasswordInput">Re-Password</label>
                <input name="rePassword" type="password" class="form-control bg-theme-<%=theme%>" id="rePasswordInput" aria-describedby="rePasswordHelp" required>
                <small id="rePasswordHelp" class="form-text text-muted">Re Input your password again</small>
            </div>

            <div class="form-group">
                <label for="fullNameInput">Full Name</label>
                <input name="fullName" type="text" class="form-control bg-theme-<%=theme%>" id="fullNameInput" aria-describedby="fullNameHelp" required>
                <small id="fullNameHelp" class="form-text text-muted">Input your full name</small>
            </div>

            <div class="form-group">
                <label for="birthdateInput">Birth Date</label>
                <input name="birthdate" type="date" class="form-control bg-theme-<%=theme%>" id="birthdateInput" aria-describedby="birthdateHelp" required>
                <small id="birthdateHelp" class="form-text text-muted">Input your birthdate</small>
            </div>

            <div class="form-group form-check">
                <input name="remember" type="checkbox" class="form-check-input" id="rememberCheck">
                <label class="form-check-label" for="rememberCheck">Remember me</label>
            </div>

            <button type="submit" class="btn btn-primary">Sign up</button>
        </form>
    </div>

    <%@include file="includes/footer.jsp"%>
</div>

</body>
</html>
