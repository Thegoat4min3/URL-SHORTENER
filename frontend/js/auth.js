
async function login(){

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    const response = await request("POST", "/api/auth/login", { email, password });

    if (response.ok) {
        const token = await response.text();
        localStorage.setItem("token", token);
        window.location.href = "dashboard.html"; // redirige
    } else {
        alert("Email ou mot de passe incorrect");
    }
}

async function register(){

    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    const response = await request("POST", "/api/auth/register", { name , email, password });

    if (response.ok) {
        const token = await response.text();
        localStorage.setItem("token", token);
        window.location.href = "dashboard.html";
    }else{
        alert("Cet email est déjà utilisé ou une erreur s'est produite");
    }
}


async function logout(){

    localStorage.removeItem("token");
    window.location.href = "login.html";
}