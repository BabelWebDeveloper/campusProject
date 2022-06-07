const btn = document.querySelector('#login__button')

// const zaloguj = document.querySelector('#zaloguj')
// const wyloguj = document.querySelector('#wyloguj')

if (btn) {
    btn.onclick = () => {
        const email = document.getElementById('login__emailid').value;
        const password = document.getElementById('login__password').value;
        console.log(email, password)
    
        login(email,password)
    }
}

const login = (email, password) => {
    fetch('http://localhost:8080/api/auth', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            email: email,//customerId
            passw: password//productId
        })
    })
        .then( async result => {
            const data = await result.json();
            console.log(data)
            const userId = data.id;
            const firstName = data.first_name;
            const lastName = data.last_name;
            const address = data.address;
            const email = data.email

            console.log(firstName)
            console.log(lastName)
            console.log(address)

            sessionStorage.setItem('id', userId);
            sessionStorage.setItem('firstName', firstName)
            sessionStorage.setItem('lastName', lastName)
            sessionStorage.setItem('address', address)
            sessionStorage.setItem('email', email)
        } )
        .then (
            window.location.href = 'indexShop.html'
        )
        .catch( err => {
            alert('Error! Check logs');
            console.log(err);
        });
}