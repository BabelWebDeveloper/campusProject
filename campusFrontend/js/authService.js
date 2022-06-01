const btn = document.querySelector('#login__button')
btn.onclick = () => {
    const email = document.getElementById('login__emailid').value;
    const password = document.getElementById('login__password').value;
    console.log(email, password)

    login(email,password)
}

const login = (email, password) => {
    fetch('http://localhost:8080/api/auth', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            email: email,
            passw: password
        })
    })
        .then( async result => {
            const data = await result.json();
            const userId = data.id;

            sessionStorage.setItem('id', userId);
            console.log(data);
        } )
        .catch( err => {
            alert('Error! Check logs');
            console.log(err);
        });
}

// login("@boks.com","124")