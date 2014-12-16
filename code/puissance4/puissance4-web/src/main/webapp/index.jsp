<%@ page import = "org.dmetzler.isen.puissance4.web.WebHelper" %>
<%@ page import = "org.dmetzler.isen.puissance4.core.*" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.0.0/semantic.min.css" />
   <link rel="stylesheet" href="css/main.css">
</head>

<body id="example" class="site">
    <div class="ui fixed inverted main menu">
        <div class="container">
            <a class="launch item"><i class="content icon"></i></a>
            <div class="title item">
                <b>Puissance 4</b>
            </div>
        </div>
    </div>


   <div id="header" class="header segment">
        <div class="container">
            <h2 class="ui dividing header">Puissance 4</h2>
            <div class="introduction">
                <p>Simple puissance4 app that makes use of JEE servlets</p>
            </div>
            <c:if test="${game.winner != null}">
              <div id="winner" class="massive circular ui icon ${game.winner.cssColor} button">WINS</div>
            </c:if>

        </div>
    </div>

    <div class="main container">
        <div id="board" class="ui seven column padded grid">

          <c:forEach items="${game.columns}" var="col">
	          <a href="?playcol=${col.index}" class="blue column">
	          	  <c:forEach items="${col.cells}" var="cell">
	              	<div class="massive circular ui icon ${cell.cssColor}  button"></div>
	              </c:forEach>
	          </a>
          </c:forEach>
        </div>


        <a href="?reset" class="ui red button" id="reset">Reset game</a>

    </div>




</body>
</html>