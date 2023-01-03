<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
<#--    <link rel="icon" href="img/favicon.ico">-->

    <title>Chat</title>
    <link class="cssdeck" rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="static/css/style.css" rel="stylesheet">
    <!-- Custom styles for this template -->

</head>
<body>
<img class="img-circle" width="100px" height="100px"
     src="${mainUserPhotoLink}"/>
<div class="container">
    <div class="row">
        <div class="chat-main col-6 offset-3">
            <div class="col-md-12 chat-header">
                <div class="row header-one text-white p-1">
                    <div class="col-md-6 name pl-2">
                        <i class="fa fa-comment"></i>
                        <h5 class="ml-1 mb-0">${mainUserName}</h5>
                    </div>

                    <#list messageList as line>
                        <table>

                        <tr>

                        <tr class="line-chat">

                            <td class="line-chat" height="10" width="50">
                                <h4>${line.name}</i></h4>
                            </td>
                            <td class="line-chat" height="10" width="100">
                                <h6>${line.message}</h6>
                            </td>
                        </tr>
                            </table>
                    </#list>

                </div>
                <div class="col-md-12 p-2 msg-box border border-primary">
                    <div class="row">
                        <div class="col-md-7 pl-0">
                            <form action="" method="POST">
                            <input  name="message" type="text" class="border-0" placeholder=" Send message"/>
                                <input  name="id" type="hidden" value="${userId}"/>
                            <button type="submit" formmethod="post" formaction="/messages/${userId}">Submit</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</ul>
<ul class="danuas adiotran-slyatsiya">
    <li><a href="#" class="prev">
        <i class="fa fa-chevron-left"></i>
        Previous
    </a>
    </li>
    <li><a href="#">1</a></li>
    <li><a href="#" class="next"> Next
    <i class="fa fa-chevron-right"></i>
    </a></li>
</ul>
<br>
</body>
</html>