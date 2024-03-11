const imagePassword = document.getElementById("imagePassword"),
        password = document.getElementById("password");

imagePassword.addEventListener('click', () => {
    if(password.type === "password"){
        password.type = "text";
        imagePassword.src = "/images/ocultar-blanco.png";
        imagePassword.classList.add("masPequenia");
    }else{
        password.type = "password";
        imagePassword.src = "/images/mostrar.jpg";
        imagePassword.classList.remove("masPequenia");
    }
});

const imagePassword2 = document.getElementById("imagePassword2"),
        password2 = document.getElementById("password2");

imagePassword2.addEventListener('click', () => {
    if(password2.type === "password"){
        password2.type = "text";
        imagePassword2.src = "/images/ocultar-blanco.png";
        imagePassword2.classList.add("masPequenia");
    }else{
        password2.type = "password";
        imagePassword2.src = "/images/mostrar.jpg";
        imagePassword2.classList.remove("masPequenia");
    }
});


