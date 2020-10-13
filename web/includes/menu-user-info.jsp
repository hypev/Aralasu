<div class="avatar bg-theme-<%=theme%>">
    <img src="<%=userProfile.getPictureUrl()%>" alt="Avatar">
</div>
<div class="card mt-5 bg-theme-<%=theme%>">
    <ul class="list-group list-group-flush">
        <li class="list-group-item font-weight-bold bg-theme-<%=theme%>">
            <%=userProfile.getFullName()%>, <%=userProfile.getAge()%> years
        </li>

        <% if (user.getId().equals(userProfile.getId())) { %>
        <li class="list-group-item font-weight-bold bg-theme-<%=theme%>">
            <a class="text-primary" href="/my-profile"><i class="fas fa-id-card mr-2"></i> My Profile</a>
        </li>

        <li class="list-group-item font-weight-bold bg-theme-<%=theme%>">
            <a class="text-primary" href="/settings"><i class="fas fa-cogs mr-2"></i> Settings</a>
        </li>
        <% } else { %>
        <% String status = DBManager.getStatusBetween(user, userProfile); %>
        <li class="list-group-item font-weight-bold bg-theme-<%=theme%>">
            <% if (status.equals("friends")) { %>
                <form action="/friend-remove" method="post" class="d-flex align-items-center">
                    <input type="hidden" name="friendId" value="<%=userProfile.getId()%>">
                    <button class="text-primary" style="background-color:transparent;border: none; outline: none;"><i class="fas fa-minus-circle mr-1"></i> Remove form Friends</button>
                </form>
            <% } else if(status.equals("request")) { %>
                <form action="/friend-request" method="post" class="d-flex align-items-center">
                    <input type="hidden" name="requesterId" value="<%=userProfile.getId()%>">
                    <button class="text-primary" name="Confirm" style="background-color:transparent;border: none; outline: none;"><i class="fas fa-plus-circle mr-1"></i> Confirm</button>
                    <button class="text-primary ml-4" name="Reject" style="background-color:transparent;border: none; outline: none;"><i class="fas fa-trash-alt mr-1"></i> Reject</button>
                </form>
            <% } else if(status.equals("sent")) { %>
                <button class="text-primary" style="background-color:transparent;border: none; outline: none;" disabled><i class="fas fa-check mr-1"></i> Request Sent</button>
            <% } else { %>
                <form action="/friend-add" method="post" class="d-flex align-items-center">
                    <input type="hidden" name="userId" value="<%=userProfile.getId()%>">
                    <button class="text-primary" style="background-color:transparent;border: none; outline: none;"><i class="fas fa-plus-circle mr-1"></i> Add to Friends</button>
                </form>
            <% } %>
        </li>
        <li class="list-group-item font-weight-bold bg-theme-<%=theme%>">
            <button onclick="sendMessageTo(<%=userProfile.getId()%>, `<%=userProfile.getFullName()%>`)" class="text-primary" data-toggle="modal" data-target="#sendMessage" style="background-color:transparent;border: none; outline: none;">
                <i class="fab fa-telegram-plane mr-2"></i> Send Message
            </button>
        </li>
        <% } %>

        <li class="list-group-item font-weight-bold bg-theme-<%=theme%>">
            <a class="text-danger" href="/logout"><i class="fas fa-sign-out-alt mr-2"></i>Logout</a>
        </li>
    </ul>
</div>