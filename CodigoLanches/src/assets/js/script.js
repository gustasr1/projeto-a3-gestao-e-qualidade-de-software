class ContentLoader {
    constructor(headerUrl, footerUrl) {
        this.headerUrl = headerUrl;
        this.footerUrl = footerUrl;
    }
  
    loadContent(elementId, url) {
        fetch(url)
        .then(response => response.text())
        .then(data => {
            document.getElementById(elementId).innerHTML = data;
        })
        .catch(error => console.error('Erro ao carregar o conteúdo:', error));
    }
  
    load() {
        this.loadContent('header', this.headerUrl);
        this.loadContent('footer', this.footerUrl);
    }
}
  
const loader = new ContentLoader("../partials/header.html", "../partials/footer.html");
loader.load();

const container = document.getElementById('container');

document.getElementById('signUp').addEventListener('click', () => {
    container.classList.add('right-panel-active');
});

document.getElementById('signIn').addEventListener('click', () => {
    container.classList.remove('right-panel-active');
});

function toggleDetalhes(card) {
    card.classList.toggle('expanded');
}

function alterarQuantidade(button, delta) {
    const input = button.parentElement.querySelector('input');
    let valorAtual = parseInt(input.value);
    valorAtual = isNaN(valorAtual) ? 0 : valorAtual;
    let novoValor = valorAtual + delta;
    if (novoValor < 0) novoValor = 0;
    input.value = novoValor;
}
