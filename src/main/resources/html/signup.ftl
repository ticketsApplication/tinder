<!DOCTYPE html>
<html lang="ru-ua">
<head>
    <meta charset="utf-8"/>
    <title>Signup Template for Bootstrap</title>
    <link href="/static/css/style1.css" rel="stylesheet"/>
    <script type="text/javascript" src="jquery-3.3.1.min.js"></script>

</head>
<body style="/* Permalink - use to edit and share this gradient: https://colorzilla.com/gradient-editor/#e8d7d3+2,c9e0d3+10,e8deda+72,e8d7d3+100 */
background: #e8d7d3; /* Old browsers */
background: -moz-linear-gradient(top,  #e8d7d3 2%, #c9e0d3 10%, #e8deda 72%, #e8d7d3 100%); /* FF3.6-15 */
background: -webkit-linear-gradient(top,  #e8d7d3 2%,#c9e0d3 10%,#e8deda 72%,#e8d7d3 100%); /* Chrome10-25,Safari5.1-6 */
background: linear-gradient(to bottom,  #e8d7d3 2%,#c9e0d3 10%,#e8deda 72%,#e8d7d3 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#e8d7d3', endColorstr='#e8d7d3',GradientType=0 ); /* IE6-9 */">

<form action="/signup" method="POST">
    <div class="container">
        <h1>Sign up</h1>
        <p>Please fill in this form to create an account.</p>
        <div class="implementation-label">
            <p>${name}</p>
        </div>
        <hr>
        Photo: <input type="text" id="inputFile" name="file" required>
        <br> <br>
        Name: <input type="text" id="inputName" name="name" required>
        <br> <br>
        Username: <input type="text" id="inputUsername" name="username" required>
        <br> <br>
        Password: <input type="password" id="inputPassword" name="password" required><br>
        <br>
        <hr>
        <button type="submit" class="login" value="login">Sign up</button>
        <div class="container signin">
            <p>Already have an account? <a href="/login">Log in</a>.</p>
        </div>
    </div>

</form>
</body>
</html>