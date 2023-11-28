package activeRecord;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Personne {

    private int id;
    private String nom;
    private String prenom;

    public Personne(String nom, String prenom) {
        this.id = -1;
        this.nom = nom;
        this.prenom = prenom;
    }

    public static ArrayList<Personne> findAll() throws SQLException {
        ArrayList<Personne> listPersonne = new ArrayList<Personne>();

        Connection connect = DBConnection.getInstance().getConnection();

        System.out.println("4) Recupere les personnes de la table Personne");
        String SQLPrep = "SELECT * FROM Personne;";
        PreparedStatement prep1 = connect.prepareStatement(SQLPrep);
        prep1.execute();
        ResultSet rs = prep1.getResultSet();
        // s'il y a un resultat
        while (rs.next()) {
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            int id = rs.getInt("id");
            System.out.println("  -> (" + id + ") " + nom + ", " + prenom);
            Personne p = new Personne(nom, prenom);
            p.id = id;
            listPersonne.add(p);
        }
        return listPersonne;
    }

    public static Personne findById(int idFind) throws SQLException {
        Connection connect = DBConnection.getInstance().getConnection();

        Personne p = null;

        PreparedStatement prep = connect.prepareStatement("SELECT * FROM Personne WHERE id=?");
        prep.setInt(1, idFind);
        prep.execute();
        ResultSet rs = prep.getResultSet();
        String nom = rs.getString("nom");
        String prenom = rs.getString("prenom");
        int id = rs.getInt("id");
        System.out.println("  -> (" + id + ") " + nom + ", " + prenom);

        p = new Personne(nom, prenom);
        p.id = id;
        return p;
    }

    public static ArrayList<Personne> findByName(String nomFind) throws SQLException {
        Connection connect = DBConnection.getInstance().getConnection();

        ArrayList<Personne> listPersonne = new ArrayList<Personne>();

        PreparedStatement prep = connect.prepareStatement("SELECT * FROM Personne WHERE nom=?");
        prep.setString(1, nomFind);
        prep.execute();
        ResultSet rs = prep.getResultSet();
        while (rs.next()) {
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            int id = rs.getInt("id");
            System.out.println("  -> (" + id + ") " + nom + ", " + prenom);
            Personne p = new Personne(nom, prenom);
            p.id = id;
            listPersonne.add(p);
        }

        return listPersonne;
    }

    public static void createTable() throws SQLException {
        Connection connect = DBConnection.getInstance().getConnection();
        String createString = "CREATE TABLE Personne ( " + "ID INTEGER  AUTO_INCREMENT, "
                + "NOM varchar(40) NOT NULL, " + "PRENOM varchar(40) NOT NULL, " + "PRIMARY KEY (ID))";
        Statement stmt = connect.createStatement();
        stmt.executeUpdate(createString);
        System.out.println("1) creation table Personne\n");
    }

    public static void supTable() throws SQLException {
        Connection connect = DBConnection.getInstance().getConnection();

        String drop = "DROP TABLE Personne";
        Statement stmt = connect.createStatement();
        stmt.executeUpdate(drop);
        System.out.println("9) Supprime table Personne");
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public static void insertion(Personne p) throws SQLException {
        Connection connect = DBConnection.getInstance().getConnection();

        if (p.getId() == -1) {
            String SQLPrep = "INSERT INTO Personne (nom, prenom) VALUES (?,?);";
            PreparedStatement prep;
            // l'option RETURN_GENERATED_KEYS permet de recuperer l'id (car
            // auto-increment)
            prep = connect.prepareStatement(SQLPrep, Statement.RETURN_GENERATED_KEYS);
            prep.setString(1, p.getNom());
            prep.setString(2, p.getPrenom());
            prep.executeUpdate();
            System.out.println("2) ajout de " + p.getPrenom() + " " + p.getNom() + "\n");
        }

    }

    public 


}
