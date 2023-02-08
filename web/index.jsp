<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Bank</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./Assets/css/PaginaInicial.css">
    </head>
    <body>
        <header>
            <ul>               
                <li class="title"><p>Área do Cliente</p></li>
                <li><a href="/BankingTransfer/LoginAdmin.jsp">Sou Gerente</a></li>
            </ul>
        </header>
        <main>
            <div class="card-form">
                <select id="selectForm" onchange="showForm()">               
                    <option value="Entrar">Entar</option>
                    <option value="Cadastrar">Cadastrar</option>
                </select>
                <form action="createUser" method="POST" id="entrar" >
                    <input type="text" name="formOpt" value="1" hidden>
                    <input type="text" name="cpf" placeholder="cpf"><br>
                    <input type="password" name="password" placeholder="senha"><br>
                    <% 
                        String msg = (String)request.getAttribute("msg");
                        if(msg != null){
                        out.print("<p>"+msg+"</p>");                     
                    }                  
                    %>
                    
                    <button type="submit">Entrar</button>
                </form>
                <form action="createUser" method="POST" hidden id="cadastrar">
                    <input type="text" name="formOpt" value="2" hidden>
                    <input type="text" name="name" placeholder="nome"><br>                  
                    <input type="text" name="cpf" placeholder="cpf"><br>
                    <input type="password" name="password" placeholder="senha"><br>
                    <button type="submit">Cadastrar</button>
                </form>
            </div>
        </main>
        
        
        <script>
            
            let formEntrar = document.getElementById("entrar");
            let formCadas = document.getElementById("cadastrar");
            
            
            function showForm(){
                let selectForm =  document.getElementById("selectForm").value;
                
                if(selectForm === "Entrar"){
                    formEntrar.removeAttribute("hidden");
                    formCadas.setAttribute("hidden", true);
                }else if(selectForm === "Cadastrar"){
                    formEntrar.setAttribute("hidden",true);
                    formCadas.removeAttribute("hidden");
                }
            }                 
        </script>
    </body>
</html>
