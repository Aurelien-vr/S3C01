package dao.implementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.ChargeDAO;
import dao.entities.Charge;
import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

/**
 * Implémentation de l'interface {@link ChargeDAO} pour gérer les opérations sur les entités "Charge".
 */
public class ChargeImpl implements ChargeDAO {
    
    private Connection connection; // Connexion à la base de données
    
    /**
     * Constructeur de la classe ChargeImpl.
     * 
     * @param connection La connexion à la base de données.
     */
    public ChargeImpl(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Recherche une charge par son identifiant.
     * 
     * @param id L'identifiant de la charge à rechercher.
     * @return L'entité {@link Charge} si trouvée, sinon {@code null}.
     */
    @Override
    public Charge findOne(long id) {
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Charge WHERE Id_Charge = ?";
        
        try {
            // Préparation de la requête SQL avec l'ID de la charge
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            result = statement.executeQuery();
            
            // Si un résultat est trouvé, création de l'entité Charge
            if (result.next()) {
                Charge charge = createEntities(result);
                return charge;
            } 
        } catch (Exception e) {
            ExceptionStorageHandler.LogException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
        return null;
    }

    /**
     * Recherche toutes les charges.
     * 
     * @return Liste des charges ou {@code null} si non implémentée.
     */
    @Override
    public List<Charge> findAll() {
        List<Charge> charges = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Charge";
        
        try {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            
            while (result.next()) {
                Charge charge = createEntities(result);
                charges.add(charge);
            } 
        } catch (Exception e) {
            ExceptionStorageHandler.LogException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
        return charges;
    }

    /**
     * Crée une nouvelle charge dans la base de données.
     * 
     * @param entity L'entité Charge à créer.
     */
    @Override
    public void insert(Charge entity) {
        PreparedStatement statement = null;
        String query = "INSERT INTO db1_sae.Charge(Date_Charge) VALUES (?)";

        try {
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, entity.getDate_charge());

            if (statement.executeUpdate() > 0) {
                ResultSet result = statement.getGeneratedKeys();
                if (result.next()) {
                    int id = result.getInt(1);
                    entity.setId_charge(id);
                }
                System.out.println("Charge inserted");
            }
        } catch (Exception e) {
            ExceptionStorageHandler.LogException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }

    /**
     * Met à jour une charge existante dans la base de données.
     * 
     * @param entity L'entité Charge à mettre à jour.
     */
    @Override
    public void update(Charge entity) {
        PreparedStatement statement = null;
        String query = "UPDATE db1_sae.Charge SET Date_Charge = ? WHERE Id_Charge = ?";
        
        try {
            statement = connection.prepareStatement(query);
            statement.setDate(1, entity.getDate_charge());
            statement.setLong(2, entity.getId_charge());
            statement.executeUpdate();
        } catch (Exception e) {
            ExceptionStorageHandler.LogException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }

    /**
     * Supprime une charge de la base de données.
     * 
     * @param id L'identifiant de la charge à supprimer.
     */
    @Override
    public void deleteById(long id) {
        PreparedStatement statement = null;
        String query = "DELETE FROM db1_sae.Charge WHERE Id_Charge = ?";
        
        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            ExceptionStorageHandler.LogException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }

    /**
     * Crée une entité {@link Charge} à partir des résultats d'une requête SQL.
     * 
     * @param result Le {@link ResultSet} contenant les données de la charge.
     * @return L'entité Charge construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public Charge createEntities(ResultSet result) throws SQLException {
        // Création de l'entité Charge à partir des données du ResultSet
        Charge charge = new Charge();
        
        charge.setId_charge(result.getInt("Id_Charge"));
        charge.setDate_charge(result.getDate("Date_Charge"));
        
        return charge;  // Retourne l'entité Charge construite
    }

	@Override
	public void insertFK(int idContratLocation, int idCharges) {
	    PreparedStatement statement = null;
	    String query = "UPDATE db1_sae.Charge SET Id_Contrat_Location = ?WHERE Id_Charge = ?";
	    
	    try {
	        statement = connection.prepareStatement(query);
	        statement.setInt(1, idContratLocation);
	        statement.setInt(2, idCharges);
	        
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

	@Override
	public List<List<String>> procGetCharges() {
		CallableStatement statement = null;
		ResultSet result = null;
		String query = "{CALL db1_sae.get_charges()}";
		List<List<String>> arrayRes = new ArrayList<>();
		
		try {
			statement = connection.prepareCall(query);
			if(statement.execute()) {
				result = statement.getResultSet();
				while(result.next()) {
					ArrayList<String> cell = new ArrayList<>();
					cell.add(result.getString(2) != null ? result.getString(2) : "Unknown");
					cell.add(result.getString(3) != null ? result.getString(3): "Unknown");
					cell.add(result.getString(4) != null ? result.getString(4): "Unknown");
					cell.add(result.getString(5) != null ? result.getString(5): "Unknown");
					cell.add(result.getString(6) != null ? result.getString(6): "Unknown");
					cell.add(result.getString(7) != null ? result.getString(7): "Unknown");
					cell.add(result.getString(8) != null ? result.getString(8): "Unknown");

					arrayRes.add(cell);
					System.out.println(cell);
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
	public List<List<String>> procGetChargesActifs() {
		CallableStatement statement = null;
		ResultSet result = null;
		String query = "{CALL db1_sae.get_chargesActif()}";
		List<List<String>> arrayRes = new ArrayList<>();
		
		try {
			statement = connection.prepareCall(query);
			if(statement.execute()) {
				result = statement.getResultSet();
				while(result.next()) {
					ArrayList<String> cell = new ArrayList<>();
					cell.add(result.getString(2) != null ? result.getString(2) : "Unknown");
					cell.add(result.getString(3) != null ? result.getString(3): "Unknown");
					cell.add(result.getString(4) != null ? result.getString(4): "Unknown");
					cell.add(result.getString(5) != null ? result.getString(5): "Unknown");
					cell.add(result.getString(6) != null ? result.getString(6): "Unknown");
					cell.add(result.getString(7) != null ? result.getString(7): "Unknown");
					cell.add(result.getString(8) != null ? result.getString(8): "Unknown");

					arrayRes.add(cell);
					System.out.println(cell);
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
}