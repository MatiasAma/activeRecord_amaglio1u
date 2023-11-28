import activeRecord.Personne;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPersonne {

    @BeforeEach
    public void avant() throws SQLException {
        Personne.createTable();
        new Personne("Spielberg", "Steven").save(); // id 1
        new Personne("Scott", "Ridley").save(); // id 2
        new Personne("Kubrick", "Stanley").save(); // id 3
        new Personne("Spielberg", "George").save(); // id 4
    }

    @AfterEach
    public void apres() throws SQLException {
        Personne.supTable();
    }

    @Test
    /**
     * test constructeur Personne
     */
    public void testConstruct() {
        Personne p = new Personne("toto", "tot");
        assertEquals(p.getId(), -1, "objet pas dans la base");
    }

    @Test
    /**
     * test find avec un nom existant
     * @throws SQLException
     */
    public void testFindByNameExiste() throws SQLException {
        ArrayList<Personne> p = Personne.findByName("Kubrick");

        assertEquals(1, p.size(), "une seule reponse");
        Personne pers = p.get(0);
        assertEquals(pers.getNom(), "Kubrick");
        assertEquals(pers.getPrenom(), "Stanley");
    }

    @Test
    /**
     * test find avec un nom dans deux personnes
     * @throws SQLException
     */
    public void testFindByName2Existe() throws SQLException {
        ArrayList<Personne> p = Personne.findByName("Spielberg");

        assertEquals(2, p.size(), "deux reponses dans personne");
        Personne pers = p.get(0);
        assertEquals(pers.getNom(), "Spielberg");
        pers = p.get(1);
        assertEquals(pers.getNom(), "Spielberg");
    }


    @Test
    /**
     * test findName avec personne n ayant le nom
     * @throws SQLException
     */
    public void testFindByNameNon() throws SQLException {
        ArrayList<Personne> p = Personne.findByName("ee");
        assertEquals(0, p.size(), "pas de reponse");
    }

    @Test
    /**
     * test findall de Personne
     * @throws SQLException
     */
    public void testFindAll() throws SQLException {
        ArrayList<Personne> p = Personne.findAll();
        assertEquals(4, p.size());
    }
}
