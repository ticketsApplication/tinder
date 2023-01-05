<!doctype html>
<html lang="en">
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

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <!-- Custom styles for this template -->

    <link rel="stylesheet" href="static/css/style1.css">

</head>
<body>

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
                            <button type="submit" class="btn btn-success" name="like_status" value="${user.id}.like" formmethod="post" formaction="/users">
                                <span class="fa fa-heart"></span>
                            </button>
                            <button type="submit" class="btn btn-danger" name="like_status" value="${user.id}.dislike"  formmethod="post" formaction="/users">
                                <span class="fa fa-times"></span></button>
                            <button formaction="/liked" formmethod="post">Liked</button>
                            <button formaction="/logout" formmethod="get">Logout</button>
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