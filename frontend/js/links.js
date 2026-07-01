
async function createLink (){

    const link = document.getElementById("orignalLink").value;
    const date = document.getElementById("expiresDays").value;

    const response = await request("POST", "/api/shortlink/createlink", { originalUrl: link, expiresInDays: parseInt(date) });

    if (response.ok) {
        const data = await response.json();
        alert("Lien créé : " + data.shortCode);
    }

}

async function getMyLinks(){

    const response = await request("GET", "/api/shortlink");
    if (response.ok) {
        const links = await response.json();
        links.forEach(link => {
            console.log(link.shortCode, link.originalUrl, link.clicks);
        });
    }else{
        alert("message erreur :" + response.statusText);
    }
}


async function deleteLink (id){
    const response = await request("DELETE", `/api/shortlink/delete/${id}`);

    if (response.ok) {
        alert("Lien supprimé !");
        getMyLinks();
    }else {
        alert("Erreur lors de la suppression");
    }
}