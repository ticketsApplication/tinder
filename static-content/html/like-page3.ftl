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
    <link href="css/style.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="col-4 offset-4">
    <div class="card">
        <div class="card-body">
            <div class="row">
                <div class="col-12 col-lg-12 col-md-12 text-center">
                    <img src=${name} alt=""
                         class="mx-auto rounded-circle img-fluid">
                    <h2 class="mb-0 text-truncated">${url}</h2>
                    <br>
                    <div class="btn-group">
                        <form action="/users" method="POST">
                            <button class="btn btn-outline-danger btn-block"><span class="fa fa-heart"></span>Yes</button>
                            <button class="btn btn-outline-danger btn-block" formaction="/users" formmethod="post"><span class="fa fa-times"></span>No</button>
                            <button class="btn btn-outline-danger btn-block" formaction="/liked" formmethod="post">Liked</button>
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