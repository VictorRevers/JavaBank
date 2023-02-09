<%-- 
    Document   : ManagerArea
    Created on : 7 de fev. de 2023, 18:34:52
    Author     : victo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./Assets/css/managerArea.css">
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <ul>               
                <li class="title"><p>Área do Gerente</p></li>
                <li><p>Funcionário - 00/000.000</p></li>
                <li><a href="/BankingTransfer">Sair</a></li>
                <li onclick="showDiv(6)"><a>Cadastrar Gerente</a></li> 
                <li><a>Fechamento de Conta</a></li> 
                <li onclick="showDiv(4)"><a>Cadastrar Cliente</a></li>  
                <li onclick="showDiv(3)"><a>Buscar Cliente</a></li>
                <li><a>Extrato</a></li> 
                <li onclick="showDiv(1)"><a>Depósito</a></li>               
            </ul>
        </header>
        
        <div class="card-form" id="getClient">           
            <h1>Buscar cliente:</h1>
                <form action="createUser" method="POST"> 
                    <!--BUSCAR CLIENTE-->
                    <input type="text" name="cpf" placeholder="cpf"><br>                   
                    <% 
                        String msg = (String)request.getAttribute("msg");
                        if(msg != null){
                        out.print("<p>"+msg+"</p>");                     
                    }                  
                    %>                  
                    <button type="submit"><a href="/BankingTransfer/ManagerArea.jsp">Buscar</<a></a></button>
                </form>
        </div>
        <div class="card-form" id="deposit" hidden>           
            <h1>Depositar:</h1>
                <form action="deposit" method="POST"> 
                    <!--DEPOSITO-->                   
                    <input type="text" name="cpf" placeholder="cpf"><br>
                    <input type="number" name="value" placeholder="valor"><br>
                    <input type="password" name="password" placeholder="senha"><br>
                    <% 
                       String msgDeposit = (String)request.getAttribute("msgDeposit");
                        if(msg != null){
                        out.print("<p>"+msg+"</p>");                     
                    }                  
                    %>                  
                    <button type="submit">Depositar</button>
                </form>
        </div> 
        <div class="card-form" id="createClient" hidden>
            <h1>Cadastrar cliente:</h1>
                <form action="createUser" method="POST" id="cadastrar">
                    <!--CADASTRO DE CLIENTE-->
                    <input type="text" name="formOpt" value="2" hidden>
                    <input type="text" name="manager" value="mngr" hidden>
                    <input type="text" name="name" placeholder="nome"><br>                  
                    <input type="text" name="cpf" placeholder="cpf"><br>
                    <input type="password" name="password" placeholder="senha"><br>
                    <button type="submit">Cadastrar</button>
                </form>
        </div>
         <div class="card-form" id="createManager" hidden>
            <h1>Cadastrar gerente:</h1>
                <form action="manager" method="POST" id="cadastrar">
                    <!--CADASTRO DE GERENTE-->
                    <!--<input type="text" name="formOpt" value="2" hidden>-->
                    <input type="text" name="name" placeholder="nome"><br>                  
                    <input type="text" name="registration" placeholder="matricula"><br>
                    <input type="password" name="password" placeholder="senha"><br>
                    <button type="submit">Cadastrar</button>
                </form>
        </div>                        
        
    </body>
    
    <script>
        function showDiv(opt){
           let deposit = document.getElementById("deposit");
           let getClient = document.getElementById("getClient");
           let createClient = document.getElementById("createClient");
           let createManager = document.getElementById("createManager");
           
            if(opt == 1){
                deposit.removeAttribute("hidden");
                getClient.setAttribute("hidden", true);
                createClient.setAttribute("hidden", true);
                createManager.setAttribute("hidden", true);
            }
            
            //else if(opt == 2){}
            
            else if(opt == 3){
                getClient.removeAttribute("hidden");
                deposit.setAttribute("hidden", true);
                createClient.setAttribute("hidden", true);
                createManager.setAttribute("hidden", true);
            }
            
            else if(opt == 4){
                createClient.removeAttribute("hidden");
                deposit.setAttribute("hidden", true);
                getClient.setAttribute("hidden", true);
                createManager.setAttribute("hidden", true);
            }
            
            //else if(opt == 5){}
            
            else if(opt == 6){
                createManager.removeAttribute("hidden");
                deposit.setAttribute("hidden", true);
                getClient.setAttribute("hidden", true);
                createClient.setAttribute("hidden", true);
            }
        }
    </script>
</html>
