<%--
  Created by IntelliJ IDEA.
  User: Grupa 1
  Date: 5/29/2023
  Time: 6:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
</head>
<body>

<section class="login-block">
    <div class="container">
        <div class="row">
            <div class="col-md-4 login-sec">
                <!-- Title of the login page -->
                <h2 class="text-center">Login Now</h2>

                <!-- Form of the login -->
                <form action="authenticate" method="post" class="login-form">
                    <div class="form-group">
                        <label for="username" class="text-uppercase">Username</label>
                        <input id="username" name="username" type="text" class="form-control" placeholder="">
                    </div>
                    <div class="form-group">
                        <label for="password" class="text-uppercase">Password</label>
                        <input id="password" name="password" type="password" class="form-control" placeholder="">
                    </div>

                    <div style="margin-bottom: 20px">
                        <button type="submit" class="btn  btn-info btn-lg btn-block">Login</button>
                    </div>

                    <div class="form-group">
                        <p><%=request.getAttribute("message") != null ? request.getAttribute("message") : ""%>
                        </p>
                    </div>
                    <a href="registration" class="btn btn-link">
                        You want to become member ?
                    </a>
                </form>
            </div>

            <!-- Picture of the login page -->
            <div class="col-md-8 banner-sec">
                <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                    <ol class="carousel-indicators">
                        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                    </ol>
                    <div class="carousel-inner" role="listbox">
                        <div class="carousel-item active">
                            <img class="d-block img-fluid"
                                 src="http://static.everypixel.com/ep-pixabay/0511/7823/3298/70909/5117823329870909994-happy_birthday.jpg"
                                 alt="First slide"/>
                        </div>
                    </div>
                    <div class="carousel-inner" role="listbox">
                        <div class="carousel-item">
                            <img class="d-block img-fluid"
                                 src="https://thumbs.dreamstime.com/z/colorful-happy-birthday-cupcakes-candles-spelling-148323072.jpg"
                                 alt="First slide"/>
                        </div>
                    </div>
                    <div class="carousel-inner" role="listbox">
                        <div class="carousel-item">
                            <img class="d-block img-fluid"
                                 src="https://thumbs.dreamstime.com/z/happy-birthday-cupcake-glitter-colorful-background-candle-light-happy-birthday-cupcake-glitter-colorful-background-159872612.jpg"
                                 alt="First slide"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

</body>
</html>
