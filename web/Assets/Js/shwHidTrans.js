let form = document.getElementById("form-container");
let printBtn = document.getElementById("print-btn");

function show(){
    form.removeAttribute("hidden");
}

function closeForm(){
    form.setAttribute("hidden", true);
}

function printProof(){
   printBtn.setAttribute("hidden", true);
   window.print();
   printBtn.removeAttribute("hidden");
}

  




