<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./Assets/css/Style.css">
        <title>Bank</title>
        
    </head>
    <body>
        <%
            String id = null;
            
            if(session.getAttribute("id")==null){
                response.sendRedirect("index.jsp");
            }else{
                id = (String) session.getAttribute("id");
            }
            
            String userName = null;
            String userBalance = null;
            String sessionID = null;
            Cookie[] cookies = request.getCookies();
            
            if(cookies != null){
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals("name")) userName = cookie.getValue();
                    if(cookie.getName().equals("balance")) userBalance = cookie.getValue();
                    if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();                  
                }
            }
            
            System.out.println("ID DA SESSÃO: "+ sessionID);
            
            
            
        %>
        
        <header>
            <div class="header">
                <a href="./index.jsp" class="logo">Bank</a>
                <p>Olá, <%= userName%>!</p>               
                <div class="header-right">
                    <a class="active" href=>Configurações</a>
                    <form id="logoutForm" method="POST" action="logout"><a id="logout">Sair</a></form>          
                </div>
            </div>
        </header>
        
        <main>
            <div class="card">
                <p>Número da conta: <%=id%></p>
                <h2 class="card-title">Saldo: R$ <%= userBalance%></h2>
                <h4 class="card-title">Saldo mensal:</h4>
                <p class="card-text">Recebido: R$ 800,00</p>
                <p class="card-text">Transferido: R$ 900,00</p>
                <p class="card-text">Total: R$ -100,00</p>
                <button type="button" id="OpnFormTransferBtn" onclick="show()" >Fazer Transferência</button>   
               
                
            </div>
            
            <div class="form-container" id="form-container" hidden>
                <button type="button" onclick="closeForm()" class="btn-clsfm"><h1>X</h1></button> 
                <form method="post" action="transfer">
                    <p>Valor: </p>
                    <input name="value" type="number">
                    <p>Nº Conta Origem:</p>
                    <input name="origin" type="number" value="<%= request.getAttribute("id")%>" readonly>
                    <p>N° Conta Destino: </p>
                    <input name="acc" type="number">
                    <p>Senha:</p>
                    <input name="password" type="password"><br>
                    <button type="submit" class="btn-subfm">Enviar</button> 
                </form>
            </div>
        </main>
        
           
        <script src="./Assets/Js/shwHidTrans.js"></script>
        <script>
            let logout = document.getElementById("logout");
        
        
            logout.onclick = function(){
                document.getElementById("logoutForm").submit();
            }
            
        </script>
    </body>
</html>
