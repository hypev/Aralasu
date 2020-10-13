<div class="modal fade" id="sendMessage" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog bg-theme-<%=theme%>">
        <div class="modal-content border-0">
            <div class="modal-header bg-theme-<%=theme%>">
                <h5 class="modal-title" id="exampleModalLabel">Send Message To <span id="userName"></span></h5>
                <button type="button" class="close bg-theme-<%=theme%>" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body bg-theme-<%=theme%>">
                <form method="post" action="/send-message" id="addPostForm">
                    <div class="form-group">
                        <label for="titleInput">Message:</label>
                        <input name="sender" type="hidden" value="<%=user.getId()%>">
                        <input name="user" type="hidden" id="userId">
                        <input name="message" type="text" class="form-control bg-theme-<%=theme%>" id="titleInput" required>
                    </div>
                </form>
            </div>
            <div class="modal-footer bg-theme-<%=theme%>">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button class="btn btn-success" form="addPostForm">Send</button>
            </div>
        </div>
    </div>
</div>