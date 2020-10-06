<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog bg-theme-<%=theme%>">
        <div class="modal-content border-0">
            <div class="modal-header bg-theme-<%=theme%>">
                <h5 class="modal-title" id="exampleModalLabel">Delete Post</h5>
                <button type="button" class="close bg-theme-<%=theme%>" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body bg-theme-<%=theme%>">
                <form method="post" action="/post-delete" id="delPostForm">
                    <h5 class="modal-title">Are you sure that you want?</h5>
                    <input type="hidden" name="id" value="<%=post.getId()%>">
                </form>
            </div>
            <div class="modal-footer bg-theme-<%=theme%>">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button class="btn btn-danger" form="delPostForm">Delete</button>
            </div>
        </div>
    </div>
</div>