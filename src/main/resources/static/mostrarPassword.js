
const buttonPasswords = document.getElementById("buttonPasswords");
const password1 = document.getElementById("password1");
const password2 = document.getElementById("password2");
const actualPassword = document.getElementById("actualPassword");

buttonPasswords.addEventListener('click', () => {
    password1.classList.remove("d-none");
    password2.classList.remove("d-none");
    actualPassword.classList.remove("d-none");
});