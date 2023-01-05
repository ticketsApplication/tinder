<!doctype html>
<html lang="ru-ua">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <link rel="icon" href="img/favicon.ico"/>

    <title>Login Template for Bootstrap</title>

</head>
<body style="/* Permalink - use to edit and share this gradient: https://colorzilla.com/gradient-editor/#e8d7d3+2,c9e0d3+10,e8deda+72,e8d7d3+100 */
background: #e8d7d3; /* Old browsers */
background: -moz-linear-gradient(top,  #e8d7d3 2%, #c9e0d3 10%, #e8deda 72%, #e8d7d3 100%); /* FF3.6-15 */
background: -webkit-linear-gradient(top,  #e8d7d3 2%,#c9e0d3 10%,#e8deda 72%,#e8d7d3 100%); /* Chrome10-25,Safari5.1-6 */
background: linear-gradient(to bottom,  #e8d7d3 2%,#c9e0d3 10%,#e8deda 72%,#e8d7d3 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#e8d7d3', endColorstr='#e8d7d3',GradientType=0 ); /* IE6-9 */">

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