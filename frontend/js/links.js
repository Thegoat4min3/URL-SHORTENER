
async function createLink (){

    const link = document.getElementById("orignalLink").value;
    const date = document.getElementById("expiresDays").value;

    const response = await request("POST", "/api/shortlink/createlink", { originalUrl: link, expiresInDays: parseInt(date) });

    if (response.ok) {
        const data = await response.json();
        alert("Lien créé : " + data.shortCode);
    }

}

let currentPage = 0;
const pageSize = 6;
let totalPages = 0;

async function getMyLinks(){

    const response = await request("GET", `/api/shortlink?page=${currentPage}&size=${pageSize}`);
    if (response.ok) {
        const data = await response.json();

        const links = data.content;
        totalPages = data.totalPages;

        const container = document.getElementById("linksContainer");
        container.innerHTML = "";

        let cpt = 0;
        let totalClicks = 0;
        let totalExpiring =0;

        links.forEach(link => {

            cpt++;
            totalClicks += link.clicks;

            const expiration = new Date(link.expiresAt);
            const maintenant = new Date();

            const diffHeures = (expiration - maintenant) / (1000 * 60 * 60);

            if (diffHeures > 0 && diffHeures < 24) {
                totalExpiring++;
            }

            container.innerHTML += `
                <div class="bg-[#233143] px-5 py-4 border border-gray-700 rounded-lg flex flex-col gap-3 relative hover:scale-105 transition duration-300">
                    <span class="absolute top-3 right-3 bg-[#8083FF] text-white text-xs px-3 py-1 rounded-full font-semibold">
                        ${link.clicks} clicks
                    </span>
                    <p class="text-[#A0A3FF] font-semibold mt-[30px]">
                        <a href="http://localhost:8080/r/${link.shortCode}" target="_blank">http://localhost:8080/r/${link.shortCode}</a>
                    </p>
                    <p class="text-gray-400 text-sm truncate">${link.originalUrl}</p>
                    <div class="flex justify-between items-center mt-2">
                        <p class="text-gray-500 text-sm">
                            <i class="fa-regular fa-clock mr-1"></i>Expires: ${link.expiresAt.substring(0, 10)}
                        </p>
                        <p class="text-red-500 text-sm cursor-pointer hover:text-red-400 transition" onclick="deleteLink(${link.id})">
                            <i class="fa-solid fa-trash-can mr-1"></i>Delete
                        </p>
                    </div>
                </div>
            `;

        });

        document.getElementById("page").textContent =
            `${currentPage + 1} / ${totalPages}`;

        document.getElementById("expiring").textContent = totalExpiring;
        document.getElementById("total-clicks").textContent = totalClicks;
        document.getElementById("total-links").textContent = data.totalElements;
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


function nextPage() {

    if (currentPage < totalPages - 1) {
        currentPage++;
        getMyLinks();
    }

}

function previousPage() {

    if (currentPage > 0) {
        currentPage--;
        getMyLinks();
    }

}

