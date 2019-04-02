<!DOCTYPE html>
<html lang="en">
<head>
    <title>PharmHelper</title>
    <%@ page contentType="text/html; charset=UTF-8" %>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="mobile-web-app-capable" content="yes">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css"
          integrity="sha384-OHBBOqpYHNsIqQy8hL1U+8OXf9hH6QRxi0+EODezv82DfnZoV7qoHAZDwMwEJvSw" crossorigin="anonymous">
    <link rel="icon" href="../img/logo.png">
    <link href="../css/Style.css" rel="stylesheet">
    <link href="../css/Contact.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Coming Soon' rel='stylesheet'>
    <link href="https://fonts.googleapis.com/css?family=Dosis|Gloria+Hallelujah|Poiret+One" rel="stylesheet">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <style>
    </style>
</head>
<body>

<div class="container bg-grey" style="padding: 40px 40px;">
    <div class="row">
        <div class="col-sm-4">
            <img class="img-thumbnail" src="../img/logo.png" alt="Logo" width="150" height="150" class="shadow"
                 style="box-shadow: 0 4px 20px 0 rgba(0,0,0,0.2);">
        </div>
        <div class="col-sm-6">
            <center>
                <h3 style="margin-bottom: 0.20px; margin-top: 15px; margin">PharmHelper</h3>
                <hr id="mobilehide" style=" width:15em;">
                <hr id="mobileshow" style=" width:11em;">
            </center>
        </div>
    </div>
</div>

<div class="container">
    <nav class="navbar navbar-expand-lg navbar-light" style="background-color:#4CAF50">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02"
                aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">

                <a class="nav-link" href="index.html" style="color: #fff;">Главная</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Products.html" style="color: #fff;">Аптеки</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false" style="color: #fff;">Сервисы</a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="#">Сервис 1</a>
                        <a class="dropdown-item" href="#">Сервис 2</a>
                        <a class="dropdown-item" href="#">Сервис 3</a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Contact Us.html" style="color: #fff;">Обратная связь</a>
                </li>
                <li class="nav-item">
                    <!--  <a class="nav-link" href="About Us.html" style="color: #fff;">About Us</a>-->
                </li>

            </ul>
            <form class="form-inline my-2 my-lg-0" role="search" method="get" action="http://localhost:8080/medicine">
                <input class="form-control mr-sm-2" type="text" placeholder="" name="medicine">
                <button class="btn btn-outline-light my-2 my-sm-0" type="submit">Поиск</button>
                <!-- <input type="hidden" name="sitesearch" value="http://reneshbedre.com/"/>-->
            </form>
        </div>
    </nav>
</div>

<div>
    <h3 style="margin-bottom: 0.20px; margin-top: 15px; margin: 15px" align="center">Результаты поиска:</h3>
    <div class="tabs">
        <input id="tab1" type="radio" name="tabs" checked>
        <label for="tab1" title="По цене">По цене</label>

        <input id="tab2" type="radio" name="tabs">
        <label for="tab2" title="По местоположению">По местоположению</label>

        <section id="content-tab1">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>№</th>
                    <th>Название</th>
                    <th>Контакты</th>
                    <th>Стоимость</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${pharmaciesResult.size() == 0}">
                    <h3>Ничего не найдено!</h3>
                </c:if>
                <c:set var="pharmaciesResult" value="${pharmaciesResult}" scope="session"/>
                    <c:forEach items="${pharmaciesResult}" var="pharmacy" varStatus="loop">
                    <tr>
                        <td> ${loop.index+1}</td>
                        <!-- NEW -->
                        <td><a href="./PharmacyInf.jsp?id=${loop.index}">${pharmacy.getPharmacyName()}</a></td>
                        <td>
                <p>${pharmacy.getAdress()}</p>
                <p>${pharmacy.getPhone()}</p>
                </td>
                <td>${pharmacy.getTotal()}</td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </section>
        <section id="content-tab2">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>№</th>
                    <th>Название</th>
                    <th>Контакты</th>
                    <th>Стоимость</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </section>
    </div>
</div>
<br>

<footer>
    <div class="container" style="background-color:#4CAF50; color:#fff;">
        <br>
        <div class="row">
            <div class="col-sm-4" id="mobilehide" style="margin-top:30px;">
                <h6 style="text-align:center; font-size:24px;">© PharmHelper, 2019</h6>
            </div>
            <div class="col-sm-4" id="mobileshow">
                <h6 style="text-align:center; font-size:24px; margin-top:20px">PharmHelper</h6>
                <h6 style="opacity:0.4; text-align:center; font-size:20px;"><i>Сервис поиска лекарств</i></h6>
            </div>

            <div class="col-sm-4" style=" margin-bottom:10px;">
                <center>
                    <a href="index.html" style="margin-right: 15px;font-size:19px; color:#fff;">Главная</a><br>
                    <a href="Products.html" style="margin-right: 15px; font-size:19px; color:#fff;">Аптеки</a><br>
                    <a href="#" data-toggle="collapse" data-target="#demo"
                       style="margin-right: 15px; color:#fff; font-size:19px;">Сервисы</a>
                    <div id="demo" class="collapse">

                        <a href="#" style="font-size:17px; color:#fff;"> Сервис 1</a><br>
                        <a href="#" style="font-size:17px; color:#fff;"> Сервис 2</a><br>
                        <a href="#" style="font-size:17px; color:#fff;"> Сервис 3</a><br>

                    </div>
                    <br>
                    <a href="Contact Us.html" style="margin-right: 15px; color:#fff; font-size:19px;">Обратная связь</a><br>
                    <!--<a href="About Us.html" style="margin-right: 15px;  color:#fff;  font-size:19px;">About Us</a><br>-->
                </center>
            </div>
            <hr id="mobileshow" style="margin-top:0px; margin-bottom:0px;">
            <div class="col-sm-4" style="margin-top:15px;">
            </div>
        </div>
    </div>
    <br>
</footer>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
        integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
        integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
        crossorigin="anonymous"></script>

</body>
</html>
