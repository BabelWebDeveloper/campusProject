const retrieveProduct = () => {
    return new Promise( (resolve, reject) => {
        fetch('http://localhost:8080/api/product/' + '24')
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
            wrapper.innerHTML += `
            <div class="productWrapper__image">
                <img class="productWrapper__image--img" src="img/products/${product.id}.jpg" alt="">
            </div>
            <div class="productWrapper__details" id="productWrapper__details">
                <h2>${product.name}</h2>
                <p>${product.description}</p>
                <p>${product.price}</p>
                <p class="productWrapper__details--button" role="button">Dodaj do koszyka</p>
            </div>
            `
    } );