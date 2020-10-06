<div class="modal fade" id="postModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg bg-theme-<%=theme%>">
        <div class="modal-content border-0">
            <div class="modal-header bg-theme-<%=theme%>">
                <h5 class="modal-title" id="exampleModalLabel">Add New Post</h5>
                <button type="button" class="close bg-theme-<%=theme%>" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body bg-theme-<%=theme%>">
                <form method="post" action="/addPost" id="addPostForm">
                    <div class="form-group">
                        <label for="titleInput">Title:</label>
                        <input name="title" type="text" class="form-control bg-theme-<%=theme%>" id="titleInput" required>
                    </div>
                    <div class="form-group">
                        <label for="shortContentInput">Short Content:</label>
                        <textarea name="shortContent" id="shortContentInput"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="contentInput">Content:</label>
                        <textarea name="content" id="contentInput"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer bg-theme-<%=theme%>">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button class="btn btn-success" form="addPostForm">Add</button>
            </div>
        </div>
    </div>
</div>