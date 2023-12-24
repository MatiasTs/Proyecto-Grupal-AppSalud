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


