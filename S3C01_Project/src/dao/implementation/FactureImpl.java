package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.FactureDAO;
import dao.entities.Facture;
import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

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
        String query = "SELECT * FROM db1_sae.Facture WHERE reference_facture = ?";
        
        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            result = statement.executeQuery();
            
            if (result.next()) {
                return createEntities(result);
            }
        } catch (SQLException e) {
            ExceptionStorageHandler.LogException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
        return null;
    }


    /**
     * Recherche tous les factures
     * 
     * @return Liste des factures
     */
    @Override
    public List<Facture> findAll() {
    	List<Facture> facts = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Facture";
        
        try {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            
            while (result.next()) {
                Facture acte = createEntities(result);
                facts.add(acte);
            } 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return facts;
    }

    /**
     * Insère une nouvelle facture dans la base de données (fonctionnalité à implémenter).
     * 
     * @param entity L'entité Facture à insérer.
     */
    @Override
    public void insert(Facture facture) {
        PreparedStatement statement = null;
        String query = "INSERT INTO db1_sae.Facture (reference_facture, type_facture, date_facture, montant_facture, moyen_paiement, montantNonDeductible, reduction) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try {
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, facture.getReference_facture());
            statement.setString(2, facture.getType_facture());
            statement.setDate(3, facture.getDate_facture());
            statement.setBigDecimal(4, facture.getMontant_facture());
            statement.setString(5, facture.getMoyen_paiement());
            statement.setBigDecimal(6, facture.getMontantNonDeductible());
            statement.setBigDecimal(7, facture.getReduction());
            
            statement.executeUpdate();
            
            // Récupérer la clé générée (si l'ID est auto-incrémenté)
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                facture.setReference_facture(generatedKeys.getString(1));  // ou autre champ ID
            }
        } catch (SQLException e) {
            ExceptionStorageHandler.LogException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }


    /**
     * Met à jour une facture existante dans la base de données (fonctionnalité à implémenter).
     * 
     * @param entity L'entité Facture à mettre à jour.
     */
    @Override
    public void update(Facture entity) {
        PreparedStatement statement = null;
        String query = "UPDATE db1_sae.Facture SET type_facture = ?, date_facture = ?, montant_facture = ?, moyen_paiement = ?, montantNonDeductible = ?, Reduction = ? WHERE reference_facture = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, entity.getType_facture());
            statement.setDate(2, entity.getDate_facture());
            statement.setBigDecimal(3, entity.getMontant_facture());
            statement.setString(4, entity.getMoyen_paiement());
            statement.setBigDecimal(5, entity.getMontantNonDeductible());
            statement.setBigDecimal(6, entity.getReduction());
            statement.setString(7, entity.getReference_facture());

            statement.executeUpdate();
        } catch (Exception e) {
            ExceptionStorageHandler.LogException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }

    
    @Override
    public void deleteById(long id) {
        PreparedStatement statement = null;
        String query = "DELETE FROM db1_sae.Facture WHERE Reference_facture = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);  // Ici on passe un long
            statement.executeUpdate();
        } catch (Exception e) {
            ExceptionStorageHandler.LogException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
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
        facture.setMontantNonDeductible(result.getBigDecimal("montantNonDeductible"));
        facture.setReduction(result.getBigDecimal("Reduction"));
        return facture;
    }
    
    /**
     * Associe un id_Bien à une facture existante.
     *
     * @param referenceFacture La référence de la facture à laquelle associer le bien.
     * @param idBien La clé étrangère à associer (id_Bien).
     */
    @Override
    public void insertFK(String referenceFacture, int idBien) {
        PreparedStatement statement = null;
        String query = "UPDATE db1_sae.Facture SET Id_Bien = ? WHERE Reference_facture = ?";

        try {
            // Préparation de la requête SQL pour mettre à jour la clé étrangère
            statement = connection.prepareStatement(query);
            statement.setLong(1, idBien); // Clé étrangère (id_Bien)
            statement.setString(2, referenceFacture); // Clé primaire (Reference_facture)

            // Exécution de la mise à jour
            if (statement.executeUpdate() > 0) {
                System.out.println("id_Bien associé avec succès à la facture : " + referenceFacture);
            } else {
                System.out.println("Aucune facture trouvée avec la référence : " + referenceFacture);
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Integrity constraint violation: " + e.getMessage());
            ExceptionStorageHandler.LogException(e, connection);
        } catch (SQLException e) {
            // Gestion des exceptions SQL
            ExceptionStorageHandler.LogException(e, connection);
        } finally {
            // Fermeture des ressources
            DatabaseConnection.closeStatement(statement);
        }
    }
}
