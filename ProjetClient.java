import java.util.ArrayList;
import java.util.UUID;

public class Client {
    private final String id_client;  
    private String email;
    private String password;

    // Constructeur
    public Client(String email, String password) {
        this.id_client = UUID.randomUUID().toString();  // Génère un UUID unique pour chaque client
        this.email = email;
        this.password = password;
    }

    // Méthode pour afficher les informations du client
    public void afficherInfos() {
        System.out.println("ID Client: " + this.id_client);
        System.out.println("Email: " + this.email);
        System.out.println("Mot de passe: " + maskPassword(this.password));
    }

    // Méthode pour masquer le mot de passe
    private String maskPassword(String password) {
        return password.replaceAll(".", "*");
    }

    public String getId_client() {
        return this.id_client;
    }
}

class ClientList {
    private ArrayList<Client> clients;

    // Constructeur de ClientList
    public ClientList() {
        clients = new ArrayList<>();
    }

    // Méthode pour vérifier si un client existe déjà avec son ID
    public boolean checkClient(String id_client) {
        for (Client client : clients) {
            if (client.getId_client().equals(id_client)) {
                return true;  // Le client existe déjà
            }
        }
        return false;  // Le client n'existe pas
    }

    // Méthode pour ajouter un client
    public boolean addClient(Client newClient) {
        if (checkClient(newClient.getId_client())) {
            return false;  // Le client existe déjà, on ne l'ajoute pas
        } else {
            clients.add(newClient);  // Ajouter le client
            return true;
        }
    }

    // Méthode pour supprimer un client
    public boolean removeClient(String id_client) {
        for (Client client : clients) {
            if (client.getId_client().equals(id_client)) {
                clients.remove(client);  // Retirer le client
                return true;
            }
        }
        return false;  // Le client n'existe pas, donc on ne peut pas le supprimer
    }

    // Méthode pour compter le nombre de clients dans la liste
    public int countClients() {
        return clients.size();
    }

    // Méthode pour afficher tous les clients
    public void afficherTousLesClients() {
        for (Client client : clients) {
            client.afficherInfos();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Création de la liste de clients
        ClientList clientList = new ClientList();

        // Création de clients
        Client client1 = new Client("client1@email.com", "password123");
        Client client2 = new Client("client2@email.com", "password456");

        // Ajout des clients à la liste
        System.out.println("Ajout du client 1 : " + clientList.addClient(client1));  // true
        System.out.println("Ajout du client 2 : " + clientList.addClient(client2));  // true
        System.out.println("Ajout du client 1 (dupliqué) : " + clientList.addClient(client1));  // false

        // Affichage du nombre de clients
        System.out.println("Nombre de clients : " + clientList.countClients());  // 2

        // Suppression d'un client
        System.out.println("Suppression du client 2 : " + clientList.removeClient(client2.getId_client()));  // true
        System.out.println("Suppression du client 3 (inexistant) : " + clientList.removeClient("inexistant_id"));  // false

        // Affichage du nombre de clients après suppression
        System.out.println("Nombre de clients après suppression : " + clientList.countClients()); 

        // Affichage des clients restants
        clientList.afficherTousLesClients();
    }
}
