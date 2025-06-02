//Simula os elementos HTML que o script precisa pra funcionar
document.body.innerHTML = `
  <button id="cart-btn">Carrinho</button>
  <div id="cart-modal"></div>
  <div id="menu"></div>
  <div id="cart-items"></div>
  <button id="close-modal-btn">Fechar</button>
  <input id="address" />
  <div id="address-warn" class="hidden"></div>
  <div id="cart-total"></div>
  <div id="cart-count"></div>
  <button id="checkout-btn"></button>
  <span id="date-span"></span>
`;

const { addToCart, removeItemCart, checkRestaurantOpen, cart } = require('../script');

describe('Lógica do Carrinho', () => {
  beforeEach(() => {
    cart.length = 0; // Limpa o carrinho antes de cada teste
  });

  test('adiciona produto ao carrinho', () => {
    addToCart('X-Burger', 15.99);
    expect(cart).toEqual([
      { name: 'X-Burger', price: 15.99, quantity: 1 }
    ]);
  });

  test('adiciona múltiplos produtos iguais ao carrinho', () => {
    addToCart('Pizza', 30.00);
    addToCart('Pizza', 30.00);
    expect(cart).toEqual([
      { name: 'Pizza', price: 30.00, quantity: 2 }
    ]);
  });

  test('remove item do carrinho com quantidade maior que 1', () => {
    addToCart('Coca', 5.00);
    addToCart('Coca', 5.00);
    removeItemCart('Coca');
    expect(cart).toEqual([
      { name: 'Coca', price: 5.00, quantity: 1 }
    ]);
  });

  test('remove item completamente do carrinho quando quantidade for 1', () => {
    addToCart('Suco', 6.00);
    removeItemCart('Suco');
    expect(cart).toEqual([]);
  });
});

describe('Verificação de horário', () => {
  test('restaurante deve estar aberto entre 18h e 21h59', () => {
    const mockDate = new Date();
    mockDate.setHours(19);
    jest.spyOn(global, 'Date').mockImplementation(() => mockDate);
    expect(checkRestaurantOpen()).toBe(true);
  });

  test('Restaurante deve estar fechado fora do horário', () => {
    const mockDate = new Date();
    mockDate.setHours(10);
    jest.spyOn(global, 'Date').mockImplementation(() => mockDate);
    expect(checkRestaurantOpen()).toBe(false);
  });
});