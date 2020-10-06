<nav class="navbar navbar-expand-lg navbar-dark bg-theme-<%=theme%>" style="background-color: darkblue; position: fixed; top: 0; width: 100%; z-index: 999">
    <div class="container">
        <a class="navbar-brand font-weight-bold" href="/"><i class="fab fa-asymmetrik"></i> ARALASU.KZ</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active ml-3">
                    <a class="nav-link" href="/"><i class="fas fa-newspaper"></i> Feed</a>
                </li>
                <li class="nav-item active ml-3">
                    <a class="nav-link" href="#"><i class="fas fa-user-friends"></i> My Friends</a>
                </li>
                <li class="nav-item active ml-3">
                    <a class="nav-link" href="#"><i class="fas fa-users"></i> Groups</a>
                </li>
                <li class="nav-item active ml-3">
                    <a class="nav-link" href="/my-posts"><i class="fas fa-edit"></i> My Posts</a>
                </li>
                <li class="nav-item active ml-3">
                    <a class="nav-link" href="#"><i class="fab fa-telegram-plane"></i> Messages</a>
                </li>
                <li class="nav-item active ml-3">
                    <a class="nav-link" href="#"><i class="fas fa-images"></i> Pictures</a>
                </li>
                <li class="nav-item active ml-3">
                    <a class="nav-link" href="#"><i class="fas fa-video"></i> Videos</a>
                </li>
                <li class="nav-item active ml-3">
                    <form method="post" action="/theme" style="margin-block-end: 0">
                        <button class="btn text-white nav-link"><i class="fas fa-moon"></i> Theme</button>
                    </form>
                </li>
            </ul>
        </div>
    </div>
</nav>