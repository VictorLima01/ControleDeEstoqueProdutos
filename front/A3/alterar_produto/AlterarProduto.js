$('#idProduto').on('change', function() {
    var value = $(this).val();
    if(value != ""){
        $('#numeroLote').attr('disabled', false);
        $('#nome').attr('disabled', false);
        $('#funcao').attr('disabled', false);

        document.getElementById("numeroLote").style.backgroundColor = "white"
        document.getElementById("nome").style.backgroundColor = "white"
        document.getElementById("funcao").style.backgroundColor = "white"
    }else{
        $('#numeroLote').attr('disabled', true);
        $('#nome').attr('disabled', true);
        $('#funcao').attr('disabled', true);

        document.getElementById("numeroLote").style.backgroundColor = "lightgrey"
        document.getElementById("nome").style.backgroundColor = "lightgrey"
        document.getElementById("funcao").style.backgroundColor = "lightgrey"
    }
});
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
    let funcao = document.getElementById("funcao").value;

    var e = document.getElementById("idProduto");
    let idProduto =  e.value;

    let url = `http://localhost:8080/api/produtos/${idProduto}`;

    let data = {
        nome: nome,
        numeroLote: parseInt(numeroLote),
        funcao: funcao
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

          Swal.fire(
            `Sucesso!`,
            `Você criou um paciente!`,
            'success'
          )
        },
        error: function () {
            console.log("error");
        }
      });

}