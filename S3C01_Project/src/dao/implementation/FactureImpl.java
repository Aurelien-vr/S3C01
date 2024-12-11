package dao.implementation;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.FactureDAO;
import dao.entities.Acte_cautionnement;
import dao.entities.Facture;

/**
 * Implémentation de l'interface {@link FactureDAO} pour gérer les opérations sur les entités "Facture".
 */
public class FactureImpl implements FactureDAO {

    private Connection connection; // Connexion à la base de données

    /**
     * Constructeur de la classe FactureImpl.
     * 
     * @param connection La connexion à la base de données.
     */
    public FactureImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Recherche une facture par sa référence.
     * 
     * @param reference La référence de la facture à rechercher.
     * @return L'entité {@link Facture} si trouvée, sinon {@code null}.
     */
    @Override
    public Facture findOne(long id) {
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Facture WHERE Reference_facture = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            result = statement.executeQuery();

            if (result.next()) {
                return createEntities(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Affichage de l'exception pour le débogage
        } finally {
            try {
                // Fermeture des ressources
                if (result != null) result.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null; // Retourner null si aucune facture n'est trouvée
    }

    /**
     * Recherche toutes les factures (fonctionnalité à implémenter).
     * 
     * @return Liste des factures ou {@code null} si non implémentée.
     */
    @Override
    public List<Facture> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Insère une nouvelle facture dans la base de données (fonctionnalité à implémenter).
     * 
     * @param entity L'entité Facture à insérer.
     */
    @Override
    public void insert(Facture entity) {
        // TODO Auto-generated method stub
    }

    /**
     * Met à jour une facture existante dans la base de données (fonctionnalité à implémenter).
     * 
     * @param entity L'entité Facture à mettre à jour.
     */
    @Override
    public void update(Facture entity) {
        // TODO Auto-generated method stub
    }

    /**
     * Supprime une facture de la base de données (fonctionnalité à implémenter).
     * 
     * @param entity L'entité Facture à supprimer.
     */
    @Override
    public void delete(Facture entity) {
        // TODO Auto-generated method stub
    }
    
    @Override
    public void deleteById(Facture entity) {
        // TODO Auto-generated method stub
    }

    /**
     * Crée une entité {@link Facture} à partir des résultats d'une requête SQL.
     * 
     * @param result Le {@link ResultSet} contenant les données de la facture.
     * @return L'entité Facture construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public Facture createEntities(ResultSet result) throws SQLException {
        // Création de l'entité Facture à partir des données du ResultSet
        Facture facture = new Facture();
        facture.setReference_facture(result.getString("Reference_facture"));
        facture.setType_facture(result.getString("Type_facture"));
        facture.setDate_facture(result.getDate("Date_facture"));
        facture.setMontant_facture(result.getBigDecimal("Montant_facture"));
        facture.setMoyen_paiement(result.getString("Moyen_paiement"));
        facture.setId_bien(result.getInt("Id_bien"));
        return facture;
    }
}
