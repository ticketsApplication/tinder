<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Login Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/style1.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/style.css">

</head>
<body>
<form action="/login" method="POST">
    <div class="container">
        <h1> Log in</h1>
        <p>Please fill out this form to enter your account.</p>
        <div class="implementation-label">
            <p>${name}</p>
        </div>
        <hr>
        <label for="inputUsername" class="sr-only">Username</label>
        <input type="text" id="inputUsername" name=username class="form-control" required autofocus>
        <br> <br>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" name=password class="form-control" required>
        <br>
        <hr>
        <button type="submit" class="login" value="login">Log in</button>
        <div class="container signin">
            <p>You don't have an account? <a href="/signup">Sign up</a>.</p>
        </div>
        <p class="mt-5 mb-3 text-muted">&copy; Tinder 2018</p>
    </div>
</form>

</body>
</html>