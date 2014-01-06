<%@ page import="org.isen.guess.*" %>
<html>
<body>

<%

  GuessGame game = GuessNumberHelper.getGame(request);

%>

<h1>Guess a number</h1>
I'm thinking of a number between 0 and <%= game.getMaxNumber() %>, please guess it

There are <%= game.getTurnLeft() %> turns left

<% if(game.hasWon()) { %>
You win ! <a href="/restart">Restart</a>
<%
} else if(game.hasLost()) { %>
You loose ! <a href="/restart">Restart</a>
<%
} else if (game.tooHigh()) {
%>
Too high
<%} else if (game.tooLow()) { %>
Too low
<% } %>


<form method="get">
Make a guess : <input name="number" /> <input type="submit"/>
</form>

</body>
</html>