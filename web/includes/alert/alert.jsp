<%  if (request.getAttribute("alertErrorMsg") != null) {    %>
    <%@include file="alertError.jsp"%>
<%  } else if (request.getAttribute("alertSuccessMsg") != null) {   %>
    <%@include file="alertSuccess.jsp"%>
<%  }    %>