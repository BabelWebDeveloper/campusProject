var email = document.getElementById('login__emailid').value;
let password = document.querySelector('#login__password').value;

let btn = document.querySelector('#login__button')
btn.onclick = (email) => {
    console.log(email);
}

const login = (username, password) => {
    fetch('http://localhost:8080/api/auth', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            email: username,
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

function logging (email, password) {
    alert("Value is:" + email + password)
}