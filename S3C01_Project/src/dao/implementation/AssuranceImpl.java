package dao.implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.AssuranceDAO;
import dao.entities.Assurance;
import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

/**
 * Implémentation de l'interface {@link AssuranceDAO} pour gérer les opérations sur les entités "Assurance".
 */
public class AssuranceImpl implements AssuranceDAO {

    private Connection connection; // Connexion à la base de données

    /**
     * Constructeur de la classe AssuranceImpl.
     *
     * @param connection La connexion à la base de données.
     */
    public AssuranceImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Recherche une assurance par son numéro de contrat.
     *
     * @param id Le numéro de contrat de l'assurance à rechercher.
     * @return L'entité {@link Assurance} si trouvée, sinon {@code null}.
     */
    @Override
    public Assurance findOne(long id) {
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Assurance WHERE numero_contrat = ?";

        try {
            // Préparation de la requête SQL avec le numéro de contrat
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            result = statement.executeQuery();

            // Si un résultat est trouvé, création de l'entité Assurance
            if (result.next()) {
                return createEntities(result);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Affichage de l'exception pour le débogage
        } finally {
            // Fermeture des ressources
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null; // Si aucune assurance n'est trouvée, retour de null
    }

    /**
     * Recherche toutes les assurances (fonctionnalité à implémenter).
     *
     * @return Liste des assurances ou {@code null} si non implémentée.
     */
    @Override
    public List<Assurance> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Crée une nouvelle assurance dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Assurance à créer.
     */
    @Override
    public void insert(Assurance entity) {
    	PreparedStatement statement = null;
		String query = "INSERT INTO db1_sae.Assurance(date_assurance ,prime, Protection_juridique) VALUES (?,?,?)";
		
		try {
   	        statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setDate(1, entity.getDate_assurance());
			statement.setBigDecimal(2, entity.getPrime());
			statement.setBigDecimal(3,entity.getProtection_juridique());
			
			
   		 if (statement.executeUpdate() > 0) {
	            ResultSet result = statement.getGeneratedKeys();
	            if (result.next()) {
	                int id = result.getInt(1);
	                entity.setId_bien(id);
	            }
	            System.out.println("User inserted");
	        }
		} catch (Exception e) {
			ExceptionStorageHandler.LogException(e, connection);
		}finally {
			DatabaseConnection.closeStatement(statement);
		}
    }

    /**
     * Met à jour une assurance existante dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Assurance à mettre à jour.
     */
    @Override
    public void update(Assurance entity) {
        // TODO Auto-generated method stub
    }

    /**
     * Supprime une assurance de la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Assurance à supprimer.
     */
    @Override
    public void delete(Assurance entity) {
        // TODO Auto-generated method stub
    }

    /**
     * Supprime une assurance par son numéro de contrat (fonctionnalité à implémenter).
     *
     * @param id Le numéro de contrat de l'assurance à supprimer.
     */
    @Override
    public void deleteById(long id) {
        // TODO Auto-generated method stub
    }

    /**
     * Crée une entité {@link Assurance} à partir des résultats d'une requête SQL.
     *
     * @param result Le {@link ResultSet} contenant les données de l'assurance.
     * @return L'entité Assurance construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public Assurance createEntities(ResultSet result) throws SQLException {
        // Création de l'entité Assurance à partir des données du ResultSet
        Assurance assurance = new Assurance();
        assurance.setDate_assurance(result.getDate("Date_assurance"));
        assurance.setPrime(result.getBigDecimal("Prime"));
        return assurance; // Retourne l'entité Assurance construite
    }

	@Override
	public List<List<String>> procGet_assurances() {
		CallableStatement statement = null;
		ResultSet result = null;
		String query = "{CALL db1_sae.get_assurances()}";
		List<List<String>> arrayRes = new ArrayList<>();
		
		try {
			statement = connection.prepareCall(query);
			if(statement.execute()) {
				result = statement.getResultSet();
				while(result.next()) {
					ArrayList<String> cell = new ArrayList<String>();
					for(int i = 1; i <= 4; i++) {
						 String value = result.getString(i);
		                    cell.add(value != null ? value : "Unknown");
					}
					arrayRes.add(cell);
				}
			}
		} catch (Exception e) {
			ExceptionStorageHandler.LogException(e, connection);
		}finally {
			DatabaseConnection.closeResult(result);
			DatabaseConnection.closeStatement(statement);
		}
		return arrayRes;
	}

	@Override
	public void insertFK(int selectedIdBien, int numeroContrat) {
	  PreparedStatement statement = null;
	    String query = "UPDATE db1_sae.Assurance SET Id_Bien = ? WHERE Numero_contrat = ?";
	    
	    try {
	        statement = connection.prepareStatement(query);
	        statement.setInt(1, selectedIdBien);
	        statement.setInt(2, numeroContrat);
	        if (statement.executeUpdate() > 0) {
	            System.out.println("FK inserted");
	        }
	    } catch (SQLIntegrityConstraintViolationException e) {
	        System.out.println("Integrity constraint violation: " + e.getMessage());
	        ExceptionStorageHandler.LogException(e, connection);
	    } catch (Exception e) {
	        ExceptionStorageHandler.LogException(e, connection);
	    } finally {
	        DatabaseConnection.closeStatement(statement);
	    }
	}		
	
}
