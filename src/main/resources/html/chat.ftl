<!DOCTYPE html>
<html lang="ru-ua">
<style>
    body {
        background: #e8d7d3; /* Old browsers */
        background: -moz-linear-gradient(top,  #e8d7d3 2%, #c9e0d3 10%, #e8deda 72%, #e8d7d3 100%); /* FF3.6-15 */
        background: -webkit-linear-gradient(top,  #e8d7d3 2%,#c9e0d3 10%,#e8deda 72%,#e8d7d3 100%); /* Chrome10-25,Safari5.1-6 */
        background: linear-gradient(to bottom,  #e8d7d3 2%,#c9e0d3 10%,#e8deda 72%,#e8d7d3 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
        filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#e8d7d3', endColorstr='#e8d7d3',GradientType=0 ); /* IE6-9 */">
    }

    /*chat*/

    .danuas {
        list-style: none;
        display: inline-block;
        padding: 0;
        margin-top: 10px;
    }

    .danuas li {
        display: inline;
        text-align: center;
    }

    .danuas a {
        float: left;
        display: block;
        font-size: 14px;
        text-decoration: none;
        padding: 5px 12px;
        color: #fbf8f8;
        margin-left: -1px;
        border: 1px solid transparent;
        line-height: 1.5;
    }

    .danuas a.deystvuyus {
        cursor: default;
    }

    .adiotran-slyatsiya a {
        margin: 0 5px;
        padding: 0;
        width: 30px;
        height: 30px;
        line-height: 30px;
        -moz-border-radius: 100%;
        -webkit-border-radius: 100%;
        border-radius: 100%;
        background-color: #4CAF50;
        box-shadow: 0 4px #999;
    }

    .adiotran-slyatsiya a.prev {
        -moz-border-radius: 50px 0 0 50px;
        -webkit-border-radius: 50px;
        border-radius: 50px 0 0 50px;
        width: 100px;
    }

    .adiotran-slyatsiya a.next {
        -moz-border-radius: 0 50px 50px 0;
        -webkit-border-radius: 0;
        border-radius: 0 50px 50px 0;
        width: 100px;
    }

    .adiotran-slyatsiya a:hover {
        background-color: #3e8e41;
    }

    .adiotran-slyatsiya a.deystvuyus {
        background-color: #3e8e41;
    }

    /*like-page*/

    .btn-group button {
        display: inline-block;
        padding: 10px 25px;
        font-size: 15px;
        cursor: pointer;
        text-align: center;
        text-decoration: none;
        outline: none;
        color: #fff;
        background-color: #4CAF50;
        border: none;
        border-radius: 15px;
        box-shadow: 0 4px #999;
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
    .btn-group button:hover {
        background-color: #3e8e41;
    }

    .line-chat{
        line-height: 20px;
    }
    .line-message {
        font-size: 20px;
    }
</style>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <#--    <link rel="icon" href="img/favicon.ico">-->

    <title>Chat</title>
    <link class="cssdeck" rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <!-- Bootstrap core CSS -->


</head>
<body style="/* Permalink - use to edit and share this gradient: https://colorzilla.com/gradient-editor/#e8d7d3+2,c9e0d3+10,e8deda+72,e8d7d3+100 */
background: #e8d7d3; /* Old browsers */
background: -moz-linear-gradient(top,  #e8d7d3 2%, #c9e0d3 10%, #e8deda 72%, #e8d7d3 100%); /* FF3.6-15 */
background: -webkit-linear-gradient(top,  #e8d7d3 2%,#c9e0d3 10%,#e8deda 72%,#e8d7d3 100%); /* Chrome10-25,Safari5.1-6 */
background: linear-gradient(to bottom,  #e8d7d3 2%,#c9e0d3 10%,#e8deda 72%,#e8d7d3 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#e8d7d3', endColorstr='#e8d7d3',GradientType=0 ); /* IE6-9 */">

<img class="img-circle" width="100px" height="100px"
     src="${currentUserPhotoLink}"/>
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
                                <td class="line-chat"  width="75">
                                    <p class="line-message">${line.name}</p>
                                </td>
                                <td class="line-chat"  width="300">
                                    <p class="line-message">${line.message}</p>
                                </td>
                            </tr>
                        </table>
                    </#list>

                </div>
                <div class="col-md-12 p-2 msg-box border border-primary">
                    <div class="row">
                        <div class="btn-group">
                            <form action="" method="POST">
                                <input  name="message" type="text" class="border-0" width=100px placeholder=" Send message"/>
                                <input  name="id" type="hidden" value="${userId}"/>
                                <button type="submit" formmethod="post" formaction="/messages/${userId}">Submit</button>
                                <button formaction="/logout" formmethod="get">Logout</button>
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