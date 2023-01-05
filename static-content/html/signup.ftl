<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Signup Template for Bootstrap</title>
    <link href="/static/css/style1.css" rel="stylesheet">
    <script type="text/javascript" src="jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/static/js/script.js"></script>
</head>
<body>
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