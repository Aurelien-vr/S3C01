package dao.implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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
        String query = "SELECT * FROM db1_sae.Facture WHERE Reference_facture = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            result = statement.executeQuery();

            if (result.next()) {
                return createEntities(result);
            }
        }   catch (Exception e) {
   			ExceptionStorageHandler.LogException(e, connection);
   		}finally {
   			DatabaseConnection.closeStatement(statement);
   			DatabaseConnection.closeResult(result);
   		}

        return null;
    }

    /**
     * Recherche toutes les factures (fonctionnalité à implémenter).
     * 
     * @return Liste des factures ou {@code null} si non implémentée.
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
            }catch (Exception e) {
       			ExceptionStorageHandler.LogException(e, connection);
       		}finally {
       			DatabaseConnection.closeStatement(statement);
       			DatabaseConnection.closeResult(result);
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
    public void insert(Facture entity) {
    	PreparedStatement statement = null;
    	String query = "INSERT INTO db1_sae.Facture(reference_facture, type_facture , date_facture , montant_facture, moyen_paiement) VALUES (?,?,?,?,?)";
   		
   		try {
   			statement = connection.prepareStatement(query);
   			statement.setString(1,entity.getReference_facture());
    		statement.setString(2, entity.getType_facture());
    		statement.setDate(3, entity.getDate_facture());
    		statement.setBigDecimal(4, entity.getMontant_facture());
    		statement.setString(5, entity.getMoyen_paiement());
    			
    			
    		if(statement.executeUpdate()>0) {
    			System.out.println("User inserted");
    		}
   		}catch (java.sql.SQLIntegrityConstraintViolationException e) {
            if ("23000".equals(e.getSQLState()) && e.getErrorCode() == 1062) {
                entity.setReference_facture("ERROR CODE 1062");
             }
         }  catch (Exception e) {
   			ExceptionStorageHandler.LogException(e, connection);
   		}finally {
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
        String query = "UPDATE db1_sae.Facture SET type_facture = ?, date_facture = ?, montant_facture = ?, moyen_paiement = ?  WHERE reference_facture = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, entity.getType_facture());
            statement.setDate(2, entity.getDate_facture());
            statement.setBigDecimal(3, entity.getMontant_facture());
            statement.setString(4, entity.getMoyen_paiement());
            statement.setString(5, entity.getReference_facture());

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
            statement.setLong(1, id);
        } catch (Exception e) {
			ExceptionStorageHandler.LogException(e, connection);
		}
		
		finally {
			DatabaseConnection.closeStatement(statement);
		}
    }
    
    
    @Override
    public void deleteByRef(String ref) {
    	PreparedStatement statement = null;
        String query = "DELETE FROM db1_sae.Facture WHERE Reference_facture = ?";
        
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, ref);
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
        facture.setType_facture(result.getString("Type_facture"));
        facture.setDate_facture(result.getDate("Date_facture"));
        facture.setMontant_facture(result.getBigDecimal("Montant_facture"));
        facture.setMoyen_paiement(result.getString("Moyen_paiement"));
        return facture;
    }

	@Override
	public String[] get_numFacture() {
		CallableStatement statement = null;
		ResultSet result = null;
		String query = "{CALL db1_sae.get_numFacture()}";
		String[] factureNumbers = null;
		
		try {
			statement = connection.prepareCall(query);
			if(statement.execute()) {
				result = statement.getResultSet();
				ArrayList<String> factureList = new ArrayList<>();
	            while (result.next()) {
	                factureList.add(result.getString("Reference_facture"));}
	            factureNumbers = factureList.toArray(new String[0]);
			}
		}catch (Exception e) {
			ExceptionStorageHandler.LogException(e, connection);
		}
		
		return factureNumbers;
	}
	
	@Override
	public void insertFK(int idBien, String refFacture) {
	    PreparedStatement statement = null;
	    String query = "UPDATE db1_sae.Facture SET Id_Bien = ? WHERE Reference_facture = ?";
	    
	    try {
	        statement = connection.prepareStatement(query);
	        statement.setString(2, refFacture);
	        statement.setInt(1, idBien);
	        
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
	public List<List<String>> procGet_factures() {
		CallableStatement statement = null;
		ResultSet result = null;
		String query = "{CALL db1_sae.get_factures()}";
		List<List<String>> arrayRes = new ArrayList<>();
		
		try {
			statement = connection.prepareCall(query);
			if(statement.execute()) {
				result = statement.getResultSet();
				while(result.next()) {
					ArrayList<String> cell = new ArrayList<>();
					for(int i = 1; i <= 6; i++) {
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
	
}
