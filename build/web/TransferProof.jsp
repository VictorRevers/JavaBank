
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Date;"%>
<%@page import="java.text.SimpleDateFormat" %>
        
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comprovante</title>
    </head>
    <body>
        <% 
            java.util.Date Data = new java.util.Date();
                String strData = new SimpleDateFormat("dd/MM/yyyy").format(Data);
        %>
        <div class="transferProof">
            <h1>COMPROVANTE DE TRANSFERÊNCIA:</h1>
            <ul>
                <li>Valor: R$ <%= request.getAttribute("value")%></li>
                <li>Origem: Nome:Fulano Conta: <%= request.getAttribute("origin")%></li>
                <li>Destino: Nome:Ciclano Conta: <%= request.getAttribute("destination")%></li>
                <li>Data: <%= strData %> </li>                               
            </ul>
            <button type="button" id="print-btn" onclick="printProof()"  >Imprimir</button>
        </div>
        
            <script src="./Assets/Js/shwHidTrans.js"></script>
    </body>
</html>
