function buildProducts(){
    let url = `http://localhost:8080/api/produtos`;
    let meusProdutos = "";

    $.ajax({
        type: "GET",
        url: url,
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
                let alocado = obj.alocado;
                let textAlocado = "";

                if(alocado){
                    textAlocado = "Produto não disponível"
                    meusProdutos += `
                <div class="gallery">
                <div class="content">
                    <img src="assets/produto-sem-imagem.png">
                    <h3>${obj.nome}</h3>
                    <p>${obj.numeroLote}</p>
                    <h6>${textAlocado}</h6>
                    <ul>
                        <li><i class=""></i></li>
                        <li><i class=""></i></li>
                        <li><i class=""></i></li>
                        <li><i class=""></i></li>
                        <li><i class=""></i></li>
                    </ul>
                    <button style="background-color: grey;" class="buy-1" disabled>Alocar Produto</button>
                </div>
            </div>
                `
                }else{
                    textAlocado = "Produto disponível"
                    meusProdutos += `
                <div class="gallery">
                <div class="content">
                    <img src="assets/produto-sem-imagem.png">
                    <h3>${obj.nome}</h3>
                    <p>${obj.numeroLote}</p>
                    <h6>${textAlocado}</h6>
                    <ul>
                        <li><i class=""></i></li>
                        <li><i class=""></i></li>
                        <li><i class=""></i></li>
                        <li><i class=""></i></li>
                        <li><i class=""></i></li>
                    </ul>
                    <button class="buy-1" onclick="alocarProduto(${obj.id})">Alocar Produto</button>
                </div>
            </div>
                `
                }
                console.log(obj);
                
            }
            document.getElementById("produtos").innerHTML = meusProdutos
        }
    });
}

function alocarProduto(idProduto){

}