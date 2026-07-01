const API_URL = "http://localhost:8080";

// Récupérer le token stocké
function getToken() {
    return localStorage.getItem("token");
}

// Fonction générique pour les requêtes authentifiées
async function request(method, endpoint, body = null) {
    const options = {
        method,
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${getToken()}`
        }
    };

    if (body) options.body = JSON.stringify(body);

    const response = await fetch(API_URL + endpoint, options);
    return response;
}