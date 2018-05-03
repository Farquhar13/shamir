<%-- 
    Document   : Submit
    Created on : Apr 24, 2018, 3:59:53 PM
    Author     : Danielle Fieseler
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


    <script language="JavaScript">

        <%@page import="com.codahale.shamir.Driver" %>


        function tryShare(){

            var share = document.getElementsByName("userShare").value;
            var num = document.getElementsByName("shareNum").value;
            if(share == null){
                alert("Please enter a share")
            } else {
                //This is where the JSP has to call the java code
                <%
                    String num = request.getParameter("shareNum");
                    String share = request.getParameter("userShare");
                    String stringFromJava = Driver.addShare(num, share);
                %>
                alert(stringFromJava);
                out.println(stringFromJava);

            }
            alert("Someone pressed the try share button");
        }



    </script>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Shamir Secret Sharing Site</h1>


        <p align="center" font-size="50">Welcome To Invisible Inc.</p><br>

        <form1 method="get" action="http://localHost:8080/ShamirSecretShare2018/index/Submit.jsp">
            Enter your secret share:<br>
            <input type="text" name="userShare"><br>
            Enter the number of your share:
            <input type="text" name="shareNum"><br>
            <button onclick="tryShare()">Try a share</button><input type="reset">
        </form1>



    </body>
</html>