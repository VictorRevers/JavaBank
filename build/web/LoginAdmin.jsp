<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./Assets/css/PaginaInicial.css">
        <title>Gerencia</title>
    </head>
    <body>
        
        <header>
            <ul>               
                <li class="title"><p>Área do Gerente</p></li>
                <li><a href="/BankingTransfer">Sou Cliente</a></li>
            </ul>
        </header>
        
        
        
        <main>
            <div class="card-form">              
                <form action="manager" method="POST">
                    <input type="text" name="loginManager" value="log" hidden>
                    <input type="text" name="registration" placeholder="matricula"><br>
                    <input type="password" name="password" placeholder="senha"><br>
                    <% 
                        String msg = (String)request.getAttribute("msg");
                        if(msg != null){
                        out.print("<p>"+msg+"</p>");                     
                    }                  
                    %>                  
                    <button type="submit">Entrar</button>
                </form>
        </main>
    </body>
</html>
