<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Aralasu | Settings</title>
    <%@include file="../includes/head.jsp"%>
</head>
<body class="body-<%=theme%>">

<div class="wrap" style="width: 100%; min-height: 100%; display: flex; flex-direction: column; overflow: hidden">
    <%@include file="../includes/navbarAuthorized.jsp"%>
    <%@include file="../includes/menu.jsp"%>

    <div id="main" class="container mt-5 pt-5 pb-5" style="flex: 1 1 auto">
        <div class="col-6 offset-3">
            <%@include file="../includes/alert/alert.jsp"%>
            <form class="mb-5" action="/editProfile" method="post">
                <h3 class="font-weight-bold">Edit Profile</h3>

                <div class="form-group">
                    <label for="emailInput">Email address</label>
                    <input name="email" value="<%=user.getEmail()%>" type="email" class="form-control bg-theme-<%=theme%>" id="emailInput" readonly>
                </div>

                <div class="form-group">
                    <label for="fullNameInput">Full Name</label>
                    <input name="fullName" value="<%=user.getFullName()%>" type="text" class="form-control bg-theme-<%=theme%>" id="fullNameInput" aria-describedby="fullNameHelp" required>
                    <small id="fullNameHelp" class="form-text text-muted">Change your full name</small>
                </div>

                <div class="form-group">
                    <label for="birthdateInput">Birth Date</label>
                    <input name="birthdate" value="<%=user.getBirthdayInput()%>" type="date" class="form-control bg-theme-<%=theme%>" id="birthdateInput" aria-describedby="birthdateHelp" required>
                    <small id="birthdateHelp" class="form-text text-muted">Change your birthdate</small>
                </div>

                <button type="submit" class="btn btn-primary">Update Profile</button>
            </form>

            <form class="mb-5" action="/editPicture" method="post">
                <h3 class="font-weight-bold">Edit Picture</h3>

                <div class="form-group">
                    <label for="pictureInput">URL</label>
                    <input name="pictureUrl" value="<%=user.getPictureUrl()%>" type="url" class="form-control bg-theme-<%=theme%>" id="pictureInput" aria-describedby="pictureHelp">
                    <small id="pictureHelp" class="form-text text-muted">Input url of your profile photo</small>
                </div>

                <button type="submit" class="btn btn-primary">Update URL</button>
            </form>
            <%  if (!user.getPassword().equals("")) {   %>
            <form class="mb-5" action="/editPassword" method="post">
                <h3 class="font-weight-bold">Edit Password</h3>

                <div class="form-group">
                    <label for="oldPasswordInput">Old Password</label>
                    <input name="oldPassword" type="password" class="form-control bg-theme-<%=theme%>" id="oldPasswordInput" aria-describedby="oldPasswordHelp" required>
                    <small id="oldPasswordHelp" class="form-text text-muted">Input your old password</small>
                </div>

                <div class="form-group">
                    <label for="newPasswordInput">New Password</label>
                    <input name="newPassword" type="password" class="form-control bg-theme-<%=theme%>" id="newPasswordInput" aria-describedby="newPasswordHelp" required>
                    <small id="newPasswordHelp" class="form-text text-muted">Input your new password</small>
                </div>

                <div class="form-group">
                    <label for="reNewPasswordInput">Re-New Password</label>
                    <input name="reNewPassword" type="password" class="form-control bg-theme-<%=theme%>" id="reNewPasswordInput" aria-describedby="reNewPasswordHelp" required>
                    <small id="reNewPasswordHelp" class="form-text text-muted">Re Input your new password again</small>
                </div>

                <button type="submit" class="btn btn-primary">Update Password</button>
            </form>
            <%  }   %>

        </div>
    </div>

    <%@include file="../includes/footer.jsp"%>
</div>

</body>
</html>
