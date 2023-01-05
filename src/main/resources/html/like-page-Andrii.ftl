<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <link rel="icon" href="img/favicon.ico">

    <title>Users page</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

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