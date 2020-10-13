<style>
    body {
        height: 100vh;
    }
    p, p * {
        background-color: transparent !important;
    }
    .card-text img, p img {
        width: 100%;
    }
    ::-webkit-scrollbar {
        width: 3px;
        background-color: rgba(0, 0, 0, 0);
        opacity: 0;
    }
    ::-webkit-scrollbar-thumb {
        border-radius: 10px;
        background-color: #202020 !important;
    }
    .bg-theme-dark {
        background-color: #202020 !important;
        color: #fff !important;
        border-color: #474747 !important;
    }
    .body-dark {
        background-color: #181818 !important;
        color: #fff !important;
    }
    .body-dark ::-webkit-scrollbar-thumb {
        background-color: #fff !important;
    }
    .avatar {
        width: 100%;
        height: 250px;
        overflow: hidden;
        position: relative;
        background-color: #fff;
        border-radius: 5px;
    }
    .avatar img {
        position: absolute;
        width: 95%;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
    }
    .msg * {
        color: black !important;
    }
    .msg.bg-theme-dark * {
        color: white !important;
    }
    .msg a:hover {
        text-decoration: none;
    }
    .msg-l, .msg-r {
        padding: 2px 20px;
        display: flex;
    }
    .msg-r {
        justify-content: flex-end;
    }
    .msg-l .msg-c {
        background-color: #00a8ff;
        color: white !important;
    }
    .msg-r .msg-c {
        background-color: #9c88ff;
        color: white !important;
    }
    .card.bg-theme-dark .msg-l .msg-c {
        background-color: #34495e;
    }
    .card.bg-theme-dark .msg-r .msg-c {
        background-color: #273c75;
    }
    .msg-c {
        padding: 10px 20px;
        padding-right: 43px;
        max-width: 50%;
        border-radius: 5px;
        position: relative;
    }
    .msg-c p {
        margin: 0;
        padding: 0;
    }
    .msg-c span {
        font-size: 10px;
        color: lightgrey;
        position: absolute;
        bottom: 5px;
        right: 10px;
    }
    .msg-new {
        display: flex;
        justify-content: center;
        align-items: center;
        margin: 20px 0;
        text-align: center;
    }
    .msg-new p {
        line-height: 1;
        margin: 0;
        padding: 5px 5px;
        border-radius: 20px;
        background-color: #007bff !important;
        color: white !important;
        width: 30%;
    }
    .msg-d {
        text-align: center;
        font-size: 14px;
        margin: 10px 0;
        padding: 0;
        margin: 0;
    }
</style>