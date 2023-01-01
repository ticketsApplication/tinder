<!doctype html>
<html lang="en">
<style>
    .btn-group button {
        display: inline-block;
        padding: 10px 35px;
        font-size: 24px;
        cursor: pointer;
        text-align: center;
        text-decoration: none;
        outline: none;
        color: #fff;
        background-color: #4CAF50;
        border: none;
        border-radius: 15px;
        box-shadow: 0 9px #999;
    }

    /* Clear floats (clearfix hack) */
    .btn-group:after {
        content: "";
        clear: both;
        display: table;
    }

    .btn-group button:not(:last-child) {
        border-right: none; /* Prevent double borders */
    }

    /* Add a background color on hover */
    /*.btn-group button:hover {*/
    /*    background-color: #3e8e41;*/
    /*}*/

</style>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Users page</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="css/style.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="static/css/style1.css">
</head>
<body style="/* Permalink - use to edit and share this gradient: https://colorzilla.com/gradient-editor/#e8d7d3+2,c9e0d3+10,e8deda+72,e8d7d3+100 */
background: #e8d7d3; /* Old browsers */
background: -moz-linear-gradient(top,  #e8d7d3 2%, #c9e0d3 10%, #e8deda 72%, #e8d7d3 100%); /* FF3.6-15 */
background: -webkit-linear-gradient(top,  #e8d7d3 2%,#c9e0d3 10%,#e8deda 72%,#e8d7d3 100%); /* Chrome10-25,Safari5.1-6 */
background: linear-gradient(to bottom,  #e8d7d3 2%,#c9e0d3 10%,#e8deda 72%,#e8d7d3 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#e8d7d3', endColorstr='#e8d7d3',GradientType=0 ); /* IE6-9 */">


<div class="col-4 offset-4">
    <div class="card">
        <div class="card-body">
            <div class="row">
                    <div class="col-12 col-lg-12 col-md-12 text-center">
                        <img width="400px" height="400px" src="${user.photoLink}" alt=""
                             class="mx-auto rounded-circle img-fluid">
                        <h2 class="mb-0 text-truncated">${user.name}</h2>
                        <br>
                        <div class="btn-group">
                            <form action="" method="POST">
                            <button type="submit" name="like_status" value="${user.id}.like" formmethod="post" formaction="/users">
                                <span class="fa fa-heart"></span>
                            </button>
                            <button type="submit" name="like_status" value="${user.id}.dislike"  formmethod="post" formaction="/users">
                                <span class="fa fa-times"></span></button>
                            <button formaction="/liked" formmethod="post">Liked</button>
                            </form>
                        </div>

                        <!--/col-->
                    </div>
                    <!--/row-->
                </div>
                <!--/card-body-->
        </div>
    </div>
</div>

</body>
</html>