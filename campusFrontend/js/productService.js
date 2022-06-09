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

// REQUIRED SESSION STORAGE
const data = sessionStorage.getItem('id');
console.log('Customer id: ' + data);

const firstName = sessionStorage.getItem('firstName');
const lastName = sessionStorage.getItem('lastName');
const address = sessionStorage.getItem('address');
const email = sessionStorage.getItem('email')
console.log(firstName)

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
        const mainHmtl = document.getElementsByTagName('main');
  
        const innerItems = (product) => {
          wrapper.innerHTML = ``;
          console.log(product)
          if (data !== null) {
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
          } else {
            wrapper.innerHTML += `
            <div class="productWrapper__image">
                <img class="productWrapper__image--img" src="img/products/${product.id}.jpg" alt="">
            </div>
            <div class="productWrapper__details" id="productWrapper__details">
                <h2>${product.name}</h2>
                <p>${product.description}</p>
                <p>${product.price} zł</p>
            </div>
          `
          }
          
        }
        if (wrapper) {
          if (product == null) {
            wrapper.innerHTML = `
            <h2>Nie ma takiego produktu</h2>`
          } else {
            innerItems(product);
          }
        } 
        else {
          const location = window.location = "indexShop.html";
        }
        
    })
        return false;  
  };

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
    if (data !== null) {
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
    } else {
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
              </div>
            </div>
        </article>
        `
      } );
    }
    
  }
      
} );

// ===========================
// ===========================

// HEADER - LOGGED/LOGOUT
const zaloguj = document.querySelector('#zaloguj')
const wyloguj = document.querySelector('#wyloguj')

const btnLogOut = document.querySelector('#logout__button')
const customerLogged = document.querySelector('#customerLogged');
const ordersNavTo = document.querySelector('#ordersNavTo');

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
  ordersNavTo.style.display = 'none'
} 

if (data !== null) {
  zaloguj.style.display = 'none'
  wyloguj.style.display = 'flex'
  ordersNavTo.style.display = 'flex'
}

// ===========================
// ===========================

// ZAMÓWIENIA:
const retrieveOrders = () => {
  return new Promise( (resolve, reject) => {
      fetch('http://localhost:8080/api/cart/ordered-orders2?id=' + data)
          .then( async result => {
  
              const data = await result.json();
              resolve(data);
              console.log(data)
          } )
          .catch( err => {
              reject(err);
          } );
  });
}

const reformatStatus = (order) => {
  if(order){
    return "Zapłacono";
  } else {
    return "Nie wysłano";
  }
}

const orderTitle = (order) => {
  ordersWrapper.innerHTML += `
              <section class="order__invoice">
                <h2>Zamówienie  ${order.id}</h2>
                <h4>Zamówione produkty:</h4>
              </section>
            `
}

const orderProducts = (cartproduct) => {
  ordersWrapper.innerHTML += `
              <section class="order">

                <article class="order__items">
                  <table>
                    <tbody>
                      <tr>
                        <td>Nazwa produktu:</td>
                        <td class="order__items--productName">${cartproduct.product.name}</td>
                      </tr>
                      <tr>
                        <td>Cena jednostkowa:</td>
                        <td class="order__items--productPrice">${cartproduct.product.price} zł</td>
                      </tr>
                      <tr>
                        <td>Ilość sztuk:</td>
                        <td class="order__items--productQuantity">${cartproduct.quantity}</td>
                      </tr>
                    </tbody>
                  </table>
                </article>

              </section>
            `
}

const orderDetails = (order, totalCost) => {
  ordersWrapper.innerHTML += `
          <section class="order__items orderSpace">
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
                <!--<tr>
                  <td>Data płatności:</td>
                  <td class="order__details--customerPaymentDate"></td>
                </tr>-->
                <tr>
                  <td>Zapłacono:</td>
                  <td class="order__details--customerTotalPay">${totalCost} zł</td>
                </tr>
                <tr>
                  <td>Status:</td>
                  <td class="order__details--customerIsOrdered">${reformatStatus(order)}</td>
                </tr>
              </tbody>
            </table>
          </section>
          <hr>
  `
}

const orderItem = (order) => {
  let total = 0
  order.cartProductList.forEach(cartproduct => {
  
    ordersWrapper.innerHTML += `
        <section class="order">
          <article class="order__items">
          <img class="ordersImg" src="img/products/${cartproduct.product.id}.jpg" alt="">
            <table>
              <tbody>
                <tr>
                  <td>Nazwa produktu:</td>
                  <td class="order__items--productName">${cartproduct.product.name}</td>
                </tr>
                <tr>
                  <td>Cena jednostkowa:</td>
                  <td class="order__items--productPrice">${cartproduct.product.price} zł</td>
                </tr>
                <tr>
                  <td>Ilość sztuk:</td>
                  <td class="order__items--productQuantity">${cartproduct.quantity}</td>
                </tr>
              </tbody>
            </table>
          </article>
          &nbsp;

        </section>
      `
      total += cartproduct.quantity * cartproduct.product.price
  })
  orderDetails(order,total)
}

const ordersWrapper = document.querySelector('#mainOrders');

if (ordersWrapper) {
  if (data !== null) {
    retrieveOrders()
    .then( orders => {
    
          orders.forEach( order => {
            orderTitle(order)
            orderItem(order);
          });
        
    } );
  } else {
    ordersWrapper.innerHTML = `<h3>Zaloguj się!</h3>`
  }
}
  

// ===========================
// ===========================

// INDEX PRODUCT STRONA PRODUKTU: 
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

const wrapper2 = document.querySelector('#productWrapper');
if (wrapper2) {
  if (data) {
    if (window.location.href.length > 50) {
      retrieveProduct()
      .then( product => {
          
          if (wrapper2) {
            wrapper2.innerHTML += `
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
  } else {
    if (window.location.href.length > 50) {
      retrieveProduct()
      .then( product => {
          const wrapper = document.querySelector('#productWrapper');
          if (wrapper2) {
            wrapper2.innerHTML += `
            <div class="productWrapper__image">
                <img class="productWrapper__image--img" src="img/products/${product.id}.jpg" alt="">
            </div>
            <div class="productWrapper__details" id="productWrapper__details">
                <h2>${product.name}</h2>
                <p>${product.description}</p>
                <p>${product.price} zł</p>
            </div>
            `
          }
      } );
    }
  }
}


// ===========================
// ===========================

// CART PRODUCT (koszyk):
const cartMain = document.querySelector('#cartProductMain')
const wrapper = document.querySelector('#cartProducts');
const paymentDetails = document.querySelector('#payment');
let totalOutput = 0;
let totalQuantity = 0;

const retrieveCartproducts = () => {
  return new Promise( (resolve, reject) => {
      fetch('http://localhost:8080/api/cart/not-ordered-orders?id=' + data)
          .then( async result => {

              const data = await result.json();
              resolve(data);
              return data;
          } )
          // .then((resolve) => {
          //   console.log(resolve)
          // })
          .then(()=> {
            document.querySelector('#payment__payButton').addEventListener('click', ()=> {
              document.querySelector('.modal-content-confirm-order').style.display = "flex";
              document.head.appendChild(document.createElement("style")).innerHTML = ".cartProductMain:before {display: inline-block;}";
            })
          })
          .then(()=> {
            document.querySelector('#modalCancel').addEventListener('click', ()=> {
              document.querySelector('.modal-content-confirm-order').style.display = "none";
              document.head.appendChild(document.createElement("style")).innerHTML = ".cartProductMain:before {display: none;}"
            })
          })
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

if (wrapper) {
  if (data !== null) {
    retrieveCartproducts()
    .then( notOrdered => {
        if (notOrdered.length > 0) {
          
          let item = [];
          

          let items = [];
          
          // let totalOutput = 0;
  
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
                          <td><p role="button" class="cartProduct__details--deleteItem" onclick="window.addEventListener('click', deleteProduct);" id="${products.cartProduct.id}">Usuń z koszyka</p></td>
                      </tr>
                      </tbody>
                  </table>
                  </div>
              </article>
  
              <hr class="cartProductHr">
              `;
              
              totalOutput = calculateTotalCart(products.product.price, products.cartProduct.quantity) + ' zł';
              totalQuantity += products.cartProduct.quantity;

            })
            

            item.items = notOrdered
            item.totalCost = totalOutput

            items.push(item);
  
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
                    <td>${address}</td>
                  </tr>
                  <!--<tr>
                    <td class="payment__td">E-mail:</td>
                    <td class="payment__space"></td>
                    <td>${email}</td>
                  </tr>-->
                </tbody>
              </table>
          
              <p>Koszt całkowity:</p>
              <output class="payment__total" id="payment__total">${totalOutput}</output>
              <p role="button" class="payment__payButton" id="payment__payButton">Przejdź do płatności</p>
              <p><a href="regulamin.html">Regulamin zwrotów</a></p>
              <p><a href="dostawa.html">Informacje o dostawie</a></p>
            </aside>
            `
            quantityinputControl()
            return item

        } else {
          wrapper.innerHTML = `Brak produktów w koszyku!`
        }
    })
    .then((resolve) => {
      console.log(resolve)
      showModalDetails(resolve)
    })
  } else {
    wrapper.innerHTML += `<h3>Zaloguj się!</h3>`
  }
}

// modal zamówienia
const showModalDetails = (array) => {
  const items = array.items;
  items.forEach(item => {
    console.log(item)
    document.querySelector('#modal-details').innerHTML += 
  `
    <p>${item.product.name} x ${item.cartProduct.quantity}</p>
  `
  })
  document.querySelector('#modal-details').innerHTML += 
  `
    <p>W sumie do zapłaty : ${array.totalCost}</p>
  `
}

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
          document.querySelector('.modal-content-confirm-order').style.display = "flex",
          document.head.appendChild(document.createElement("style")).innerHTML = ".cartProductMain:before {display: none;}",
          window.location = "productsPaid.html"
        )
        .catch( err => {
            console.log(err);
        });
  })
  
}

// Koszyk count:
const cartCount = document.querySelector('.header__wrapper--logo--count');
if (cartCount) {
  let quantity = sessionStorage.getItem('totalQ');
  console.log(quantity)
}

// KOSZYK przyciski plus minus:
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

        const cartProductId = buttonClicked.parentElement.id;
        console.log(cartProductId);
        incrementCartProduct(cartProductId);

        window.location.reload();
        // paymentReload()
    })
  }
  
  for(let i = 0; i < decrementBtn.length; i++){
      const button = decrementBtn[i];
      button.addEventListener('click', event => {
  
          const buttonClicked = event.target;
          const input = buttonClicked.parentElement.children[1].children[0];
          const inputvalue = input.value;
          const newValue = parseInt(inputvalue) - 1;

          const cartProductId = buttonClicked.parentElement.id;
          console.log(cartProductId);
          decrementCartProduct(cartProductId);
  
          if (newValue >= 1) {
              input.value = newValue;
          } else {
              input.value = 1;
          }

          window.location.reload();
      })
  }
}

// Zwiększ ilość produktu w koszyku:
const incrementCartProduct = (cartProductId) => {
  fetch('http://localhost:8080/api/cartproduct/increment/' + cartProductId, {
      method: 'PUT',
      headers: {
          'Content-Type': 'application/json'
      }
  })
      .then( async result => {
          const data = await result.json();
          const userId = data.id;
          
      } )
      .catch( err => {
          console.log(err);
      });
}

// Zmniejsz ilość produktu w koszyku:
const decrementCartProduct = (cartProductId) => {
  fetch('http://localhost:8080/api/cartproduct/decrement/' + cartProductId, {
      method: 'PUT',
      headers: {
          'Content-Type': 'application/json'
      }
  })
      .then( async result => {
          const data = await result.json();
          const userId = data.id;
          
      } )
      .catch( err => {
          console.log(err);
      });
}

// Dodaj do koszyka:
const modal = document.querySelector('.modal-content-product');
const modalExit = document.querySelector('.modalClose');
const styleElem = document.head.appendChild(document.createElement("style"));
const shopMain  = document.querySelector('.shopMain')


const addToCart = (target) => {
  cartProduct(data,target.srcElement.id)
}

const exitModal = () => {
  modal.style.display = "none";
  styleElem.innerHTML = ".shopMain:before {display: none;}";
}

let quantity = 0;

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
    } )
    .then(() => {
      modal.style.display = "flex";
      styleElem.innerHTML = ".shopMain:before {display: inline-block;}";
    })
    .catch( err => {
        console.log(err);
    });
}

// Usuń produkt z koszyka (decrement):
const deleteProduct = (target) => {
  const productId = target.srcElement.id;
  window.location.reload();
  console.log(productId)
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
        console.log(data)
      })
      .catch( err => {
          console.log(err);
      });
  }



// REJESTRACJA:

const registerUser = (first_nameIn, last_nameIn, emailIn, addressIn, passwordIn) => {
  fetch('http://localhost:8080/api/customer/createCustomer', {
      method: 'POST',
      headers: {
          'Content-Type': 'application/json'
      },
      body: JSON.stringify({
          first_name: first_nameIn,
          last_name: last_nameIn,
          email: emailIn,
          address: addressIn,
          password: passwordIn
      })
  })
      .then( async result => {
          const data = await result.json();
          const userId = data.id;
          window.location = "logowanie.html"
          console.log("ddd");
      } )
      .catch( err => {
          console.log(err);
      });
  }

let tc = (a) => {
  let x = document.querySelector(a).value;
  return x;
}

let msgb = (a) => {
  let x = document.querySelector(".emsgb").innerHTML=a;
  return x;
}

let size = (a) => {
  let x = document.querySelector(a).value.length;
  console.log(x)
  return x;
}

let signupRegister = () => {
  const first_name = tc(".fname");
  const last_name = tc(".lname");
  const email = tc(".email");
  const address = tc(".address")
  const password = tc("#pwd");
  const confirmPassword = tc("#conpwd");

  if (first_name==null || first_name=="") {
    msgb("Please Enter First name");
    return false;
  }
  if (last_name==null || last_name=="") {
    msgb("Please Enter Last name");
    return false;
  }
  if (email==null || email=="") {
    msgb("Please Enter Email");
    return false;
  }
  if (address==null || address=="") {
    msgb("Please Enter Adress");
    return false;
  }
  if (size("#pwd") <= 3) {
    msgb("Minimum Password length 3");
    return false;
  }
  if (password==null || password=="") {
    msgb("Please Enter Password");
    return false;
  }
  if (password != confirmPassword) {
    msgb("Password Miss match");
    return false;
  }

  registerUser(first_name, last_name, email, address, confirmPassword);
}