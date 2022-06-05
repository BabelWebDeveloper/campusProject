// CATEGORIES:
// const retrieveCategories = () => {
//   return new Promise( (resolve, reject) => {
//       fetch('http://localhost:8080/api/category/categories')
//           .then( async result => {

//               const data = await result.json();
//               resolve(data);
//               console.log(data)

//           } )
//           .catch( err => {
//               reject(err);
//           } );
//   } );
// }
// retrieveCategories()

// ===========================
// ===========================

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
              <a class="shopbar__item--img" role="link" value="indexProduct.html" href="indexProduct.html" onclick="location.href=this.href+'?productID='+${product.id};return false;" style="background-image: url(img/products/${product.id}.jpg);">
              </a>

              <div class="shopbar__item--details">
                <p class="shopbar__item--details--name">${product.name}</p>
                <p class="shopbar__item--details--desc">${product.description}</p>

                <div class="shopbar__item--details--cartPriceBar" onsubmit="return productId()">
                  <p class="shopbar__item--details--cartPriceBar-price">${product.price} zł</p>
                  <p role="button" onclick="window.addEventListener('click', addToCart);" class="shopbar__item--details--cartPriceBar-button" id="${product.id}">Dodaj do koszyka</p>
                </div>
              </div>
          </article>
          `
      } );
      }
      
           
  } );

// ===========================
// ===========================

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
      wrapper.innerHTML = `
      <h2>Nie ma takiego produktu</h2>`
    } else {

      const innerItems = (product) => {
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

      if (wrapper) {
        innerItems(product);
      } 
      // else {() => {
      //   return new Promise( (resolve) => {
      //     window.location.href = 'indexShop.html';
      //     resolve(product);
      //   })
      //   .then(
      //     innerItems(product)
      //   )
      //   }
      // }
    }
})
    return false;  
};

// ===========================
// ===========================

// REQUIRED SESSION STORAGE
const data = sessionStorage.getItem('id');
console.log('Customer id: ' + data);

const firstName = sessionStorage.getItem('firstName');
const lastName = sessionStorage.getItem('lastName');
const address = sessionStorage.getItem('address');

// ===========================
// ===========================

// HEADER - LOGGED/LOGOUT
const zaloguj = document.querySelector('#zaloguj')
const wyloguj = document.querySelector('#wyloguj')

const btnLogOut = document.querySelector('#logout__button')
const customerLogged = document.querySelector('#customerLogged');

if (btnLogOut) {
    btnLogOut.onclick = () => {
        sessionStorage.clear();
        window.location.href = 'logowanie.html'
        console.log("Logged out")
    }
}

if (data == null) {
  zaloguj.style.display = 'flex'
  wyloguj.style.display = 'none'
} 

if (data !== null/* & customerLogged*/) {
  zaloguj.style.display = 'none'
  wyloguj.style.display = 'flex'
  // customerLogged.innerText = firstName + " " + lastName;
}


// ===========================
// ===========================

// CART PRODUCT (koszyk):
const retrieveCartproducts = () => {
return new Promise( (resolve, reject) => {
    fetch('http://localhost:8080/api/cart/not-ordered-orders?id=' + data)
        .then( async result => {

            const data = await result.json();
            resolve(data);
            // console.log(data)
        } )
        .catch( err => {
            reject(err);
        } );
} );
}

let total = 0;
const calculateTotalCart = (price, quantity) => {
  total += price * quantity;
  return total.toFixed(2);
}

const cartMain = document.querySelector('#cartProductMain')
const wrapper = document.querySelector('#cartProducts');
const paymentDetails = document.querySelector('#payment')

if (wrapper) {
  if (data !== null) {
    retrieveCartproducts()
    .then( notOrdered => {
        console.log(notOrdered)
        if (notOrdered.length > 0) {
          
          let totalOutput = 0;
  
          if (wrapper) {
            notOrdered.forEach( products => {
              wrapper.innerHTML += `
              <article class="cartProduct">
                  <div class="cartProduct__image">
                  <img src="img/products/${products.product.id}.jpg" class="cartProduct__image--img" alt="">
                  </div>
  
                  <div class="cartProduct__details">
                  <table>
                      <tbody>
                      <tr>
                          <td>Nazwa produktu:</td>
                      </tr>
                      <tr>
                          <td class="cartProduct__details--productName">${products.product.name}</td>
                      </tr>
                      <tr>
                          <td>Cena jednostkowa:</td>
                      </tr>
                      <tr>
                          <td class="cartProduct__details--productName">${products.product.price} zł</td>
                      </tr>
                      </tbody>
                  </table>
  
                  <table>
                      <tbody>
                      <tr>
                          <td>Ilość:</td>
                      </tr>
                      <tr class="input-container" id="${products.cartProduct.id}">
                          <td class="decr" role="button"> - </td>
                          <td class="cartProduct__details--productQuantityInput inputJs"><input type="number" value="${products.cartProduct.quantity}"></td>
                          <td class="incr" role="button" name="${products.cartProduct.id}"> + </td>
                      </tr>
                      <tr>
                          <td><p role="button" class="cartProduct__details--deleteItem" onclick="window.addEventListener('click', deleteProduct);">Usuń z koszyka</p></td>
                      </tr>
                      </tbody>
                  </table>
                  </div>
              </article>
  
              <hr class="cartProductHr">
              `;
              totalOutput = calculateTotalCart(products.product.price, products.cartProduct.quantity) + ' zł';
            })
  
            cartMain.innerHTML += `
            <aside class="payment" id="payment">
              <h3>Podsumowanie zamówienia:</h3>
              <table>
                <tbody>
                  <tr>
                    <td class="payment__td">Imię i nazwisko:</td>
                    <td class="payment__space"></td>
                    <td>${firstName} ${lastName}</td>
                  </tr>
                  <tr>
                    <td class="payment__td">Adres dostawy:</td>
                    <td class="payment__space"></td>
                    <td></td>
                  </tr>
                  <tr>
                    <td class="payment__td">E-mail:</td>
                    <td class="payment__space"></td>
                    <td></td>
                  </tr>
                </tbody>
              </table>
          
              <p>Koszt całkowity:</p>
              <output class="payment__total" id="payment__total">${totalOutput}</output>
              <p role="button" class="payment__payButton" id="payment__payButton" onclick="payCart()">Zapłać</p>
              <p><a href="regulamin.html">Regulamin zwrotów</a></p>
              <p><a href="dostawa.html">Informacje o dostawie</a></p>
            </aside>
            `
            quantityinputControl()
          }
        } else {
          wrapper.innerHTML = `Brak produktów w koszyku!`
        }
    });
  } else {
    wrapper.innerHTML += `<h3>Zaloguj się!</h3>`
  }
}

// ===========================
// ===========================

// ZAMÓWIENIA:
const retrieveOrders = () => {
  return new Promise( (resolve, reject) => {
      fetch('http://localhost:8080/api/cart/ordered-orders?id=' + data)
          .then( async result => {
  
              const data = await result.json();
              resolve(data);
              // console.log(data)
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
    if(order){
      return "Zapłacono";
    } else {
      return "Nie wysłano";
    }
  }

if (data !== null) {
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
}

// ===========================
// ===========================

// STRONA PRODUKTU:
let id = window.location.href.slice(-2);

const retrieveProduct = () => {
  return new Promise( (resolve, reject) => {
      fetch('http://localhost:8080/api/product/' + id)
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

if (window.location.href.length > 50) {
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
            <p class="productWrapper__details--button" role="button" onclick="window.addEventListener('click', addToCart);" id="${product.id}">Dodaj do koszyka</p>
        </div>
        `
      }
  } );
}

// ===========================
// ===========================

// KOSZYK przyciski:
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
        console.log(buttonClicked.parentElement.id)
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

// Dodaj do koszyka:
const addToCart = (target) => {//dodać czyszczenie koszyka
  cartProduct(data,target.srcElement.id)
}

const cartProduct = (customerIdin, productIdin) => {
fetch('http://localhost:8080/api/cart/createCart/createProduct', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify({
        customerId: customerIdin,//customerId
        productId: productIdin//productId
    })
})
    .then( async result => {
        const data = await result.json();
        const userId = data.id;
        
    } )
    .catch( err => {
        console.log(err);
    });
}

// Usuń produkt z koszyka (decrement):
const deleteProduct = (target) => {
  const productId = target.srcElement.id;
  window.location.reload();
  deleteCartProduct(productId);
}

const deleteCartProduct = (productId) => {
  const url = 'http://localhost:8080/api/cartproduct/' + productId;
  console.log(url);
  fetch(url, {
    method: 'DELETE'
  })
      .then( async result => {
        const data = await result.json();
        const userId = data.id;
        
      })
      .catch( err => {
          console.log(err);
      });
  }


// Zwiększ ilość produktu w koszyku:


// Zapłać za koszyk:
const payCart = () => {
  return new Promise( (resolve, reject) => {
    const url = 'http://localhost:8080/api/cart/pay?id=' + data;
    console.log(url);
    fetch(url, {
      method: 'PUT'
    })
        .then( async result => {
          const data = await result.json();
          resolve(data)
        })
        .then(
          window.location = "productsPaid.html"
        )
        .catch( err => {
            console.log(err);
        });
  })
  
  }

