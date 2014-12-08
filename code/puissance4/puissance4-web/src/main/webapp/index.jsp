<%@ page import = "org.dmetzler.isen.puissance4.web.WebHelper" %>
<%@ page import = "org.dmetzler.isen.puissance4.core.*" %>
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


   <div class="header segment">
        <div class="container">
            <h2 class="ui dividing header">Puissance 4</h2>
            <div class="introduction">
                <p>Simple puissance4 app that makes use of JEE servlets</p>
            </div>
        </div>
    </div>

    <div class="main container">
        <div id="board" class="ui seven column padded grid">

          <%
          	Puissance4Game game = WebHelper.getGame(request);
          	for (int i = 0; i < game.getColumnsNumber(); i++) {
          %>
	          <a href="?playcol=<%=i%>" class="blue column">
	          	  <% for (int j = game.getRowsNumber()-1; j >= 0; j--) {
	          	  	    ChipColour chip = game.getCell(i,j);
	          	  	    String colour = chip == null ? "" : chip == ChipColour.RED ? "red" : "yellow";
	          	  %>
	              	<div class="massive circular ui icon <%=colour %> button"></div>
	              <% }
          		  %>
	          </a>
          <% }
          %>
        </div>
    </div>




</body>
</html>