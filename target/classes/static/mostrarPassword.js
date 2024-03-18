
const buttonPasswords = document.getElementById("buttonPasswords");
const passwordContainer1 = document.getElementById("passwordContainer1");
const passwordContainer2 = document.getElementById("passwordContainer2");
const actualPassword = document.getElementById("actualPassword");

buttonPasswords.addEventListener('click', () => {
    passwordContainer1.classList.remove("d-none");
    passwordContainer2.classList.remove("d-none");
    actualPassword.classList.remove("d-none");
});