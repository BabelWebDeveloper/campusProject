let data = sessionStorage.getItem('id');
          console.log('Customer id: ' + data);
// CATEGORIES:
const retrieveCategories = () => {
  return new Promise( (resolve, reject) => {
      fetch('http://localhost:8080/api/category/categories')
          .then( async result => {

              const data = await result.json();
              resolve(data);
              console.log(data)

          } )
          .catch( err => {
              reject(err);
          } );
  } );
}
// retrieveCategories()

// INDEX SHOP:
const retrieveProducts = () => {
    return new Promise( (resolve, reject) => {
        fetch('http://localhost:8080/api/product')
            .then( async result => {

                const data = await result.json();
                resolve(data);

            } )
            .catch( err => {
                reject(err);
            } );
    } );
}

retrieveProducts()
    .then( products => {
        const wrapper = document.querySelector('#shopbar');
        if (wrapper) {
          products.forEach( product => {
            wrapper.innerHTML += `
            <article class="shopbar__item">
                <a class="shopbar__item--img" role="link" value="indexProduct.html" href="indexProduct.html" onclick="location.href=this.href+'?productID='+${product.id};return false;" id="${product.id}" style="background-image: url(img/products/${product.id}.jpg);">
                </a>

                <div class="shopbar__item--details">
                  <p class="shopbar__item--details--name">${product.name}</p>
                  <p class="shopbar__item--details--desc">${product.description}</p>
  
                  <form class="shopbar__item--details--cartPriceBar" onsubmit="return checkCart()">
                    <p class="shopbar__item--details--cartPriceBar-price">${product.price} zł</p>
                    <input type="submit" class="shopbar__item--details--cartPriceBar-button" value="Dodaj do koszyka"></input>
                  </form>
                </div>
            </article>
            `
        } );
        }
        
             
    } );
  
// Dodaj do koszyka:
// ++++++++++++++++++++
const checkCart = () => {
  return new Promise( (resolve, reject) => {
      fetch('http://localhost:8080/api/cart/checkCart?id=' + data)
          .then( async result => {
              const cartData = await result.json();
              resolve(cartData);
              console.log('cartId: ' + cartData.id)
              return cartData.id;
          } )
          .catch( err => {
              reject(err);
          } );
  } );
}

const checkCartproductId = (cartId) => {
  return new Promise( (resolve, reject) => {
      fetch('http://localhost:8080/api/cartproduct/retrieve?id=' + cartId)
          .then( async result => {

              const data = await result.json();
              resolve(data);
              console.log('cartproductId: ' + data.id)
          } )
          .catch( err => {
              reject(err);
          } );
  } );
}

const cartId = checkCart()
  .then(data => {
    checkCartproductId(data.id);
  })
// DOTĄD GIT


// const cartproductId = checkCartproductId(cartId);//return cartproductId or null [X]
const productId = () => {};//return productId
const addToCartproduct = (productId) => {};

const createCartProduct = (cartId,productId) => {};//quantity domyślnie na 1
const createCart = () => {}//return cartId

const addToCart = (productId) => {
  if (cartId != null) { //sprawdzanie czy customer ma cart
    if (cartproductId(cartId) != null) { //jeśli ma cart (not null) sprawdza czy ma cartproduct (gdy dodaje drugi produkt), return cartproductID
      addToCartproduct(productId); //jeśli ma cartproduct to dodaje product do cartproduct
    } else { //jeśli nie ma cartproduct wtedy:
      createCartProduct(cartId, productId); //tworzy nowy cartproduct, return cartproductId
    }
  } else { //jeśli nie ma cart wtedy:
    createCart();//tworzy nowy cart, return cartId
    createCartProduct(cartId);//tworzy nowy cartproduct, return cartproductID
    addToCartproduct(productId);//dodaje product do cartprouct
  }
}

// ++++++++++++++++++++

// ============================================

// WYSZUKIWANIE:
const retrieveProductByName = (name) => {
    url = 'http://localhost:8080/api/product/search?name=' + name;
    return new Promise( (resolve, reject) => {
        fetch(url)
            .then( async result => {

                const data = await result.json();
                resolve(data);
            } )
            .catch( err => {
                reject(err);
            } );
    } );
}
const search = () => {
    const searchInput = document.querySelector('#search_input');
    const searchPhrase = searchInput.value;
    retrieveProductByName(searchPhrase)
    .then( product => {
        const wrapper = document.querySelector('#shopbar');
        if (product == null) {
            alert("Nie ma takiego produktu.")
        } else {
            wrapper.innerHTML = ``;
            console.log(product)
            wrapper.innerHTML += `
            <article class="shopbar__item">
                <a class="shopbar__item--img" role="link" value="indexProduct.html" href="indexProduct.html" id="${product.id}" style="background-image: url(img/products/${product.id}.jpg);">
                </a>

                <div class="shopbar__item--details">
                  <p class="shopbar__item--details--name">${product.name}</p>
                  <p class="shopbar__item--details--desc">${product.description}</p>
  
                  <div class="shopbar__item--details--cartPriceBar">
                    <p class="shopbar__item--details--cartPriceBar-price">${product.price} zł</p>
                    <p role="button" class="shopbar__item--details--cartPriceBar-button">Dodaj do koszyka</p>
                  </div>
                </div>
            </article>
            `
        }
    })
        return false;  
    };


// ===========================
// ZAMÓWIENIA:

const retrieveOrders = () => {
    return new Promise( (resolve, reject) => {
        fetch('http://localhost:8080/api/payment/orders?id=' + data)
            .then( async result => {

                const data = await result.json();
                resolve(data);

            } )
            .catch( err => {
                reject(err);
            } );
    } );
}

const calcTotalCost = (order) => {
  return order.product.price * order.cartProduct.quantity;
}

const reformatStatus = (order) => {
  if(order.cart.ordered){
    return "Wysłano";
  } else {
    return "Nie wysłano";
  }
}

retrieveOrders()
    .then( orders => {
        const wrapper = document.querySelector('#mainOrders');

        if (wrapper) {
          orders.forEach( order => {
            wrapper.innerHTML += `
            <section class="order">
            <h2>Zamówienie:</h2>
            <article class="order__invoice">
                <p>Faktura:</p>
                <output>PV01_23/05/22</output>
            </article>

            <article class="order__items">
              <table>
                <caption>Zamówione produkty:</caption>
                <tbody>
                  <tr>
                    <td>Nazwa produktu:</td>
                    <td class="order__items--productName">${order.product.name}</td>
                  </tr>
                  <tr>
                    <td>Cena jednostkowa:</td>
                    <td class="order__items--productPrice">${order.product.price} zł</td>
                  </tr>
                  <tr>
                    <td>Ilość sztuk:</td>
                    <td class="order__items--productQuantity">${order.cartProduct.quantity}</td>
                  </tr>
                </tbody>
              </table>
            </article>

            <article class="order__details">
              <table>
                <caption>Dla:</caption>
                <tbody>
                  <tr>
                    <td>Imię i nazwisko:</td>
                    <td class="order__details--customerName">${order.customer.first_name} ${order.customer.last_name}</td>
                  </tr>
                  <tr>
                    <td>Adres:</td>
                    <td class="order__details--customerAddress">${order.customer.address}</td>
                  </tr>
                  <tr>
                    <td>E-mail:</td>
                    <td class="order__details--customerEmail">${order.customer.email}</td>
                  </tr>
                  <tr>
                    <td>Data płatności:</td>
                    <td class="order__details--customerPaymentDate">${order.date}</td>
                  </tr>
                  <tr>
                    <td>Zapłacono:</td>
                    <td class="order__details--customerTotalPay">${calcTotalCost(order)}</td>
                  </tr>
                  <tr>
                    <td>Status:</td>
                    <td class="order__details--customerIsOrdered">${reformatStatus(order)}</td>
                  </tr>
                </tbody>
              </table>
            </article>
          </section>

          <hr>`
          
        } );
        }
        
    } );

// ===========================
// KOSZYK:

const quantityinputControl = () => {
  const incrementBtn = document.getElementsByClassName('incr');
  const decrementBtn = document.getElementsByClassName('decr');

  for(let i = 0; i < incrementBtn.length; i++){
    const button = incrementBtn[i];
    button.addEventListener('click', event => {

        const buttonClicked = event.target;
        const input = buttonClicked.parentElement.children[1].children[0];
        const inputvalue = input.value;
        const newValue = parseInt(inputvalue) + 1;
        input.value = newValue;
    })
}

for(let i = 0; i < decrementBtn.length; i++){
    const button = decrementBtn[i];
    button.addEventListener('click', event => {

        const buttonClicked = event.target;
        const input = buttonClicked.parentElement.children[1].children[0];
        const inputvalue = input.value;
        const newValue = parseInt(inputvalue) - 1;

        if (newValue >= 1) {
            input.value = newValue;
        } else {
            input.value = 1;
        }
    })
}
}

// Load products:
const retrieveCartproducts = () => {
  return new Promise( (resolve, reject) => {
      fetch('http://localhost:8080/api/cartproduct/cart?id=' + data)
          .then( async result => {

              const data = await result.json();
              resolve(data);

          } )
          .catch( err => {
              reject(err);
          } );
  } );
}

let total = 0;

const calculateTotalCart = (price, quantity) => {
  
  total += price * quantity;
  console.log(total)
  return total.toFixed(2);
}

retrieveCartproducts()
  .then( cartproducts => {
      const wrapper = document.querySelector('#cartProducts');
      const totalOutput = document.querySelector('#payment__total');

      if (wrapper) {
        cartproducts.forEach( cartproduct => {
          wrapper.innerHTML += `
          <article class="cartProduct">
              <div class="cartProduct__image">
              <img src="img/products/${cartproduct.product.id}.jpg" class="cartProduct__image--img" alt="">
              </div>

              <div class="cartProduct__details">
              <table>
                  <tbody>
                  <tr>
                      <td>Nazwa produktu:</td>
                  </tr>
                  <tr>
                      <td class="cartProduct__details--productName">${cartproduct.product.name}</td>
                  </tr>
                  <tr>
                      <td>Cena jednostkowa:</td>
                  </tr>
                  <tr>
                      <td class="cartProduct__details--productName">${cartproduct.product.price} zł</td>
                  </tr>
                  <tr>
                      <td class="cartProduct__details--productName">Ilość: ${cartproduct.quantity}</td>
                  </tr>
                  </tbody>
              </table>

              <table>
                  <tbody>
                  <tr>
                      <td>Ilość:</td>
                  </tr>
                  <tr class="input-container">
                      <td class="decr" role="button"> - </td>
                      <td class="cartProduct__details--productQuantityInput inputJs"><input type="number" min="1" value="1"></td>
                      <td class="incr" role="button"> + </td>
                  </tr>
                  <tr>
                      <td><p role="button" class="cartProduct__details--deleteItem">Usuń z koszyka</p></td>
                  </tr>
                  </tbody>
              </table>
              </div>
          </article>

        <hr class="cartProductHr">
          `;
          totalOutput.textContent = calculateTotalCart(cartproduct.product.price, cartproduct.quantity) + ' zł';
      } )
      quantityinputControl()
      }
      
  } );

// ===========================
// STRONA PRODUKTU:

let id = window.location.href.slice(-2);
// console.log(id)

const retrieveProduct = () => {
    return new Promise( (resolve, reject) => {
        fetch('http://localhost:8080/api/product/' + id)
            .then( async result => {

                const data = await result.json();
                resolve(data);

            } )
            .catch( err => {
                reject(err);
            } );
    } );
}

retrieveProduct()
    .then( product => {
        const wrapper = document.querySelector('#productWrapper');
        if (wrapper) {
          wrapper.innerHTML += `
          <div class="productWrapper__image">
              <img class="productWrapper__image--img" src="img/products/${product.id}.jpg" alt="">
          </div>
          <div class="productWrapper__details" id="productWrapper__details">
              <h2>${product.name}</h2>
              <p>${product.description}</p>
              <p>${product.price} zł</p>
              <p class="productWrapper__details--button" role="button">Dodaj do koszyka</p>
          </div>
          `
        }
    } );