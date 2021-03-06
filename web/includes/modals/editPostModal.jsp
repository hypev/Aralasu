<div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg bg-theme-<%=theme%>">
        <div class="modal-content border-0">
            <div class="modal-header bg-theme-<%=theme%>">
                <h5 class="modal-title" id="exampleModalLabel">Add New Post</h5>
                <button type="button" class="close bg-theme-<%=theme%>" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body bg-theme-<%=theme%>">
                <form method="post" action="/post-edit" id="addPostForm">
                    <div class="form-group">
                        <label for="titleInput">Title:</label>
                        <input name="title" value='<%=post.getTitle()%>' type="text" class="form-control bg-theme-<%=theme%>" id="titleInput" >
                    </div>
                    <div class="form-group">
                        <label for="shortContentInput">Short Content:</label>
                        <textarea name="shortContent" id="shortContentInput">
                            <%=post.getShortContent()%>
                        </textarea>
                    </div>
                    <div class="form-group">
                        <label for="contentInput">Content:</label>
                        <textarea name="content" id="contentInput">
                            <%=post.getContent()%>
                        </textarea>
                    </div>
                    <input type="hidden" name="id" value="<%=post.getId()%>">
                </form>
            </div>
            <div class="modal-footer bg-theme-<%=theme%>">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button class="btn btn-success" form="addPostForm">Save</button>
            </div>
        </div>
    </div>
</div>