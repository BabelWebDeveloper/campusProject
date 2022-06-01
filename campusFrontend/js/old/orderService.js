let data = sessionStorage.getItem('id');
          console.log(data);


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

retrieveOrders()
    .then( orders => {
        const wrapper = document.querySelector('#mainOrders');
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
                    <td class="order__details--customerTotalPay">${calcTotalCost()}</td>
                  </tr>
                  <tr>
                    <td>Status:</td>
                    <td class="order__details--customerIsOrdered">${reformatStatus()}</td>
                  </tr>
                </tbody>
              </table>
            </article>
          </section>

          <hr>`
          function calcTotalCost(){
            return order.product.price * order.cartProduct.quantity;
          }
          function reformatStatus(){
            if(order.cart.ordered){
              return "Wysłano";
            } else {
              return "Nie wysłano";
            }
          }
        } );
    } );

