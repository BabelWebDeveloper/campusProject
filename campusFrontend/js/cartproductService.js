// Load products:
const retrieveCartproducts = () => {
    return new Promise( (resolve, reject) => {
        fetch('http://localhost:8080/api/cartproduct')
            .then( async result => {

                const data = await result.json();
                resolve(data);

            } )
            .catch( err => {
                reject(err);
            } );
    } );
}

retrieveCartproducts()
    .then( cartproducts => {
        const wrapper = document.querySelector('#cartProducts');
        const totalOutput = document.querySelector('#payment__total');
        let total = 0;
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
            console.log(cartproduct)
            console.log(total += cartproduct.product.price * cartproduct.quantity);
            totalOutput.textContent = total + " zł"
        } );

        // Input number control:
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
// =====================
    } );