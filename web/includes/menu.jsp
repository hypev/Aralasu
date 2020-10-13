<%@ page import="db.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="db.DBManager" %>
<%
    User user = (User)request.getAttribute("user");
    User userProfile = (User)request.getAttribute("user-info");
    ArrayList<User> usersBirthday = DBManager.getLastUsersByBirthDateOrder(5);
%>
<%@include file="modals/sendMessageModal.jsp"%>
<div class="mt-5 pt-5" style="position: fixed; width: 100%; top: 0">
    <div class="container">
        <div class="row">
            <div class="col-3">
                <%@include file="menu-user-info.jsp"%>
            </div>
            <div class="col-3 offset-6">
                <%@include file="menu-aside.jsp"%>
            </div>

        </div>
    </div>
</div>