<%--
  Created by IntelliJ IDEA.
  User: Napha
  Date: 31.01.2017
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Contact page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link href="/bootstrap/css/welcomePage.css" rel="stylesheet">
</head>

<body>

<div class="container">
  <nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">Logo</a>
      </div>
      <div class="collapse navbar-collapse" id="myNavbar">
        <ul class="nav navbar-nav navbar-right">
          <li><a href="/">HOME</a></li>
          <li><a href="#">ABOUT SERVICE</a></li>
        </ul>
      </div>
    </div>
  </nav>
</div>

<div class="container-fluid bg-grey">
  <h2 class="text-center">CONTACT</h2>
  <br>
  <div class="row">
    <p>Contact us and we'll get back to you as soon as possible.</p>
    <p><span class="glyphicon glyphicon-map-marker logo-small"></span> Киев, Украина</p>
    <p><span class="glyphicon glyphicon-phone logo-small"></span> (044)499-69-69</p>
    <p><span class="glyphicon glyphicon-envelope logo-small"></span> Печенежская,32</p>
  </div>
</div>
<div id="googleMap" style="height:400px;width:100%;"></div>
<script src="http://maps.googleapis.com/maps/api/js"></script>
<script>
  var myCenter = new google.maps.LatLng(50.465456, 30.487827);

  function initialize() {
    var mapProp = {
      center:myCenter,
      zoom:12,
      scrollwheel:false,
      draggable:false,
      mapTypeId:google.maps.MapTypeId.ROADMAP
    };

    var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);

    var marker = new google.maps.Marker({
      position:myCenter,
    });

    marker.setMap(map);
  }

  google.maps.event.addDomListener(window, 'load', initialize);
</script>

</body>
</html>
