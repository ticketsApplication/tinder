<!doctype html>
<html lang="ru-ua">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <link rel="icon" href="img/favicon.ico"/>

    <title>People list</title>
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

<div class="container">
    <div class="row">
        <div class="col-8 offset-2">
            <div class="panel panel-default user_panel">
                <div class="panel-heading">
                    <table>
                        <tr>
                            <td>
                                <h3 class="panel-title">Liked list</h3>
                            </td>
                            <td>

                                <div>
                                    <form>
                                        <button class="btn btn-light" formaction="/users" formmethod="get">Users
                                        </button>
                                        <button class="btn btn-light" formaction="/logout" formmethod="get">Logout
                                        </button>
                                    </form>
                                </div>


                            </td>
                        </tr>
                    </table>
                </div>
                <div class="panel-body">
                    <div class="table-container">
                        <table border="0">
                            <tbody>

                            <form action="" method="POST">
                                <#list user as line>
                                    <tr>
                                        <td width="0">
                                            <div class="avatar-img">
                                                <button name="id" value="${line.id}" formmethod="post"
                                                        formaction="/messages/${line.id}">
                                                    <img class="img-circle" width="200px" height="200px"
                                                         alt="Button ${line.name}" src="${line.photoLink}"/>
                                                </button>
                                            </div>
                                        </td>
                                        <td class="align-middle">
                                            <div class="liked-name">
                                                ${line.name}
                                            </div>
                                        </td>
                                    </tr>
                                </#list>
                            </form>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>