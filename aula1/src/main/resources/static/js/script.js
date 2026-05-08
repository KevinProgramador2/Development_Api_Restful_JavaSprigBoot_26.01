async function buscarMensagens() {
    try {
        const response = await fetch("/aulas");
        const data = await response.text();
        document.getElementById('resutaldo-get').innerHTML = data;
    } catch (erro) {
        alert("Erro ao buscar a mensagem no front!" + erro)
    }
}