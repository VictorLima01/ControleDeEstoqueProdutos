function verificarCampos(){
    /*
        bUILD SELECT
    */
        let meusPacientes = `<option value="" selected>Selecione o produto para alteração de dados</option>`;

        $.ajax({
            type: "GET",
            url: `http://localhost:8080/api/produtos`,
            crossDomain: true,
            headers: { 
                'Content-Type': 'application/json',
                'Accept': '*/*',
            },
            dataType:"json",
            success: function(result){
                console.log(result);
                for (var key in result) {
                    var obj = result[key];
      
                    meusPacientes += `
                    <option value="${obj.id}">${obj.nome}</option>
                    `
                    
                }
                document.getElementById("idProduto").innerHTML = meusPacientes
            }
        });

}
function updateProduto(){
    let numeroLote = document.getElementById("numeroLote").value;
    let nome = document.getElementById("nome").value;

    var e = document.getElementById("idProduto");
    let idProduto =  e.value;

    let url = `http://localhost:8080/api/produtos/${idProduto}`;

    let data = {
        nome: nome,
        numeroLote: parseInt(numeroLote),
    }

    $.ajax({
        type: "PUT",
        url: url,
        crossDomain: true,
        headers: { 
            'Content-Type': 'application/json',
            'Accept': '*/*',
        },
        data: JSON.stringify(data),
        dataType: 'json',
        success: function(data) {
          console.log(data);
          document.getElementById("numeroLote").value = "";
          document.getElementById("nome").value = "";

          document.location.reload(true);
        },
        error: function () {
            console.log("error");
        }
      });

}