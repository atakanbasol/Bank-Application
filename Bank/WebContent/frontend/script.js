document.getElementById('signup-form').addEventListener('submit', function(event) {
    event.preventDefault();

    const formData = new FormData(this);
    const data = {
        firstname: formData.get('firstname'),
        lastname: formData.get('lastname'),
        email: formData.get('email'),
        dob: formData.get('dob'),
        password: formData.get('password'),
        address: formData.get('address'),
        gender: formData.get('gender'),
        identificationNumber: formData.get('identificationNumber'),
        job: formData.get('job')
    };

    fetch('http://localhost:8080/Bank/api/signup', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert('Sign Up Successful');
            window.location.href = 'index.html';
        } else {
            alert('Sign Up Failed');
        }
    })
    .catch(error => console.error('Error:', error));
});

document.getElementById('signin-form').addEventListener('submit', function(event) {
    event.preventDefault();

    const formData = new FormData(this);
    const data = {
        identificationNumber: formData.get('identificationNumber'),
        password: formData.get('password')
    };

    fetch('http://localhost:8080/Bank/api/signin', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert('Sign In Successful');
            window.location.href = 'index.html';
        } else {
            alert('Sign In Failed');
        }
    })
    .catch(error => console.error('Error:', error));
});
