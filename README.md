#  URL Shortener

Application web de raccourcissement d'URL permettant de générer des liens courts et faciles à partager, avec gestion des redirections côté serveur.

##  Description

Ce projet permet à un utilisateur de soumettre une URL longue et d'obtenir en retour un lien court unique. Lorsqu'un visiteur clique sur ce lien court, il est automatiquement redirigé vers l'URL d'origine grâce à une logique de redirection gérée par le back-end.

##  Fonctionnalités

- Génération d'un code court unique pour chaque URL soumise
- Redirection automatique vers l'URL d'origine
- Interface simple et intuitive pour créer un lien
- <Ajoutez ici si applicable : suivi des statistiques de clics, gestion des liens expirés, copie rapide du lien, etc.>

## Technologies utilisées

**Back-end**
- Java
- Spring Boot
- Spring Web (API REST)
- MySQL 

**Front-end**
- HTML5
- CSS3

##  Architecture

```
url-shortener/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/          # Code back-end Spring Boot (contrôleurs, services, entités)
│   │   │   └── resources/     # Configuration (application.properties)
│   └── pom.xml                # Dépendances Maven
│
├── frontend/
│   ├── page/                  # Pages HTML
│   ├── style/                 # Feuilles de style CSS
│   ├── js/                    # Scripts JavaScript
│
└── README.md
```


##  Installation et lancement

### Prérequis
- Java 17 (ou version utilisée)
- Maven
- MySql

### Étapes

```bash
# Cloner le dépôt
git clone https://github.com/Thegoat4min3/url-shortener.git
cd url-shortener

# Lancer l'application avec Maven
mvn spring-boot:run
```

L'application est ensuite accessible sur : `http://localhost:8080`

##  Utilisation

1. Ouvrir l'application dans le navigateur
2. Coller l'URL longue à raccourcir dans le champ prévu
3. Cliquer sur "Raccourcir"
4. Récupérer le lien court généré et le partager

##  Améliorations futures

- Ajout de statistiques de clics par lien
- Système d'expiration des liens
- Authentification pour gérer ses propres liens
- Déploiement en ligne (Render / Railway)

## Auteur

**Fanny Mamadou Amine**
Étudiante en développement d'applications — PIGIER Côte d'Ivoire
fannymamadouamine18072006@gmail.com
