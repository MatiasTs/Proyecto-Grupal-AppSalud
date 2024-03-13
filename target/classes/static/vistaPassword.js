const imagePassword = document.getElementById("imagePassword"),
        password = document.getElementById("password");


const imagePassword2 = document.getElementById("imagePassword2"),
        password2 = document.getElementById("password2");

imagePassword.addEventListener('click', () => {
    mostrarOcultarImagen(password, imagePassword);
});

if(imagePassword2 !== null) {
    imagePassword2.addEventListener('click', () => {
        mostrarOcultarImagen(password2, imagePassword2);
    });
}


function mostrarOcultarImagen(password, image){
    if(password.type === "password"){
        password.type = "text";
        image.src = "/images/ocultar-blanco.png";
        image.classList.add("masPequenia");
    }else{
        password.type = "password";
        image.src = "/images/mostrar.jpg";
        image.classList.remove("masPequenia");
    }
};

