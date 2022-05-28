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
        products.forEach( product => {
            wrapper.innerHTML += `
            <article class="shopbar__item">
                <a href="indexProduct.html" class="shopbar__item--img" role="link" href="indexProduct.html" style="background-image: url(img/products/${product.id}.jpg);">
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
        } );
    } );