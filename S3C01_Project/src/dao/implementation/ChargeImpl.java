package dao.implementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.ChargeDAO;
import dao.entities.Charge;
import db_connection.DatabaseConnection;
import exception.ExceptionStorageHandler;

/**
 * Implémentation de l'interface {@link ChargeDAO} pour gérer les opérations sur les entités "Charge".
 */
public class ChargeImpl implements ChargeDAO {
    
    private Connection connection; // Connexion à la base de données
    private String stringUnknown = "Unknown";
    
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
        String query = "SELECT Id_Charge, Date_Charge, Id_Contrat_Location FROM db1_sae.Charges WHERE Id_Charge = ?";
        
        try {
            // Préparation de la requête SQL avec l'ID de la charge
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            result = statement.executeQuery();
            
            // Si un résultat est trouvé, création de l'entité Charge
            if (result.next()) {
                return createEntities(result);
            } 
        } catch (Exception e) {
            ExceptionStorageHandler.logException(e, connection);
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
        String query = "SELECT Id_Charge, Date_Charge, Id_Contrat_Location FROM db1_sae.Charges";
        
        try {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            
            while (result.next()) {
                Charge charge = createEntities(result);
                charges.add(charge);
            } 
        } catch (Exception e) {
            ExceptionStorageHandler.logException(e, connection);
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
        String query = "INSERT INTO db1_sae.Charges(Date_Charge) VALUES (?)";

        try {
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, entity.getDateCharge());
            
            if (statement.executeUpdate() > 0) {
                ResultSet result = statement.getGeneratedKeys();
                if (result.next()) {
                    int id = result.getInt(1);
                    entity.setIdCharge(id);
                }
            }
        } catch (Exception e) {
            ExceptionStorageHandler.logException(e, connection);
            e.printStackTrace();
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
        String query = "UPDATE db1_sae.Charges SET Date_Charge = ? WHERE Id_Charge = ?";
        
        try {
            statement = connection.prepareStatement(query);
            statement.setDate(1, entity.getDateCharge());
            statement.setLong(2, entity.getIdCharge());
            statement.executeUpdate();
        } catch (Exception e) {
            ExceptionStorageHandler.logException(e, connection);
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
        String query = "DELETE FROM db1_sae.Charges WHERE Id_Charge = ?";
        
        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            ExceptionStorageHandler.logException(e, connection);
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
        
        charge.setIdCharge(result.getInt("Id_Charge"));
        charge.setDateCharge(result.getDate("Date_Charge"));
        
        return charge;  // Retourne l'entité Charge construite
    }

	@Override
	public void insertFK(int idContratLocation, int idCharges) {
	    PreparedStatement statement = null;
	    String query = "UPDATE db1_sae.Charges SET Id_Contrat_Location = ? WHERE Id_Charge = ?";
	    
	    try {
	        statement = connection.prepareStatement(query);
	        statement.setInt(1, idContratLocation);
	        statement.setInt(2, idCharges);
	        statement.executeUpdate();        
	        
	    }catch (Exception e) {
	        ExceptionStorageHandler.logException(e, connection);
	        e.printStackTrace();
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
					fillCell(2,8,result, cell);

					arrayRes.add(cell);
				}
			}
		} catch (Exception e) {
			ExceptionStorageHandler.logException(e, connection);
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
					fillCell(2,8,result, cell);
					arrayRes.add(cell);
				}
			}
		} catch (Exception e) {
			ExceptionStorageHandler.logException(e, connection);
		}finally {
			DatabaseConnection.closeResult(result);
			DatabaseConnection.closeStatement(statement);
		}
		return arrayRes;
	}

	@Override
	public List<List<String>> procFactureFromCharge(int inputYear, int idBien) {
		CallableStatement statement = null;
		ResultSet result = null;
		String query = "{CALL db1_sae.get_factureFromCharge(?,?)}";
		List<List<String>> arrayRes = new ArrayList<>();
		
		try {
			statement = connection.prepareCall(query);
			
			statement.setInt(1, inputYear);
			statement.setInt(2, idBien);
			if(statement.execute()) {
				result = statement.getResultSet();
				while(result.next()) {
					ArrayList<String> cell = new ArrayList<>();
					fillCell(1,8,result, cell);
					arrayRes.add(cell);
				}
			}
		} catch (Exception e) {
			ExceptionStorageHandler.logException(e, connection);
		}finally {
			DatabaseConnection.closeResult(result);
			DatabaseConnection.closeStatement(statement);
		}
		return arrayRes;
	}

	private void fillCell(int start, int end, ResultSet result, ArrayList<String> cell) throws SQLException {
		for (int i = start; i <= end; i++) {
		    cell.add(result.getString(i) != null ? result.getString(i) : stringUnknown);
		}
	}

	@Override
	public List<List<String>> procYearFromFacture(int idBien) {
		CallableStatement statement = null;
		ResultSet result = null;
		String query = "{CALL db1_sae.get_yearFromChargeByBien(?)}";
		List<List<String>> arrayRes = new ArrayList<>();
		
		try {
			statement = connection.prepareCall(query);
			
			statement.setInt(1, idBien);
			if(statement.execute()) {
				result = statement.getResultSet();
				while(result.next()) {
					ArrayList<String> cell = new ArrayList<>();
					fillCell(1,2,result, cell);
					arrayRes.add(cell);
				}
			}
		} catch (Exception e) {
			ExceptionStorageHandler.logException(e, connection);
		}finally {
			DatabaseConnection.closeResult(result);
			DatabaseConnection.closeStatement(statement);
		}
		return arrayRes;
	}

	@Override
	public List<List<String>> procFactureFromBien(int idBien) {
		CallableStatement statement = null;
		ResultSet result = null;
		String query = "{CALL db1_sae.get_factureFromBien(?)}";
		List<List<String>> arrayRes = new ArrayList<>();
		
		try {
			statement = connection.prepareCall(query);
			
			statement.setInt(1, idBien);
			if(statement.execute()) {
				result = statement.getResultSet();
				while(result.next()) {
					ArrayList<String> cell = new ArrayList<>();
					fillCell(1,8,result, cell);
					arrayRes.add(cell);
				}
			}
		} catch (Exception e) {
			ExceptionStorageHandler.logException(e, connection);
		}finally {
			DatabaseConnection.closeResult(result);
			DatabaseConnection.closeStatement(statement);
		}
		return arrayRes;
	}

	@Override
	public int procIdCharge(int idCl, int year) {
	    CallableStatement statement = null;
	    ResultSet result = null;
	    String query = "{CALL db1_sae.get_idChargeFromYearAndIdCL(?,?)}";
	    int res = -1; // Initialize with a default value

	    try {
	        statement = connection.prepareCall(query);
	        statement.setInt(1, idCl);
	        statement.setInt(2, year);

	        if (statement.execute()) {
	            result = statement.getResultSet();
	            if (result.next()) { // Check if there is at least one row in the result
	                res = result.getInt(1); // Get the first column value
	            }
	        }
	    } catch (Exception e) {
	        ExceptionStorageHandler.logException(e, connection);
	    } finally {
	        DatabaseConnection.closeResult(result);
	        DatabaseConnection.closeStatement(statement);
	    }

	    return res;
	}

	@Override
	public int progGetIdClFromIdBien(int idBien) {
	    CallableStatement statement = null;
	    ResultSet result = null;
	    String query = "{CALL db1_sae.get_clIdFromIdBien(?)}";
	    int res = -1; // Initialize with a default value

	    try {
	        statement = connection.prepareCall(query);
	        statement.setInt(1, idBien);

	        if (statement.execute()) {
	            result = statement.getResultSet();
	            if (result.next()) { // Check if there is at least one row in the result
	                res = result.getInt(1); // Get the first column value
	            }
	        }
	    } catch (Exception e) {
	        ExceptionStorageHandler.logException(e, connection);
	    } finally {
	        DatabaseConnection.closeResult(result);
	        DatabaseConnection.closeStatement(statement);
	    }

	    return res;
	}

}