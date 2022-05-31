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
                <a class="shopbar__item--img" role="link" value="indexProduct.html" href="indexProduct.html" onclick="location.href=this.href+'?xyz='+${product.id};return false;" id="${product.id}" style="background-image: url(img/products/${product.id}.jpg);">
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
            // xyc()
        } );
             
    } );

// ============================================

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