package dao.implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.ContratLocationDAO;
import dao.entities.ContratLocation;
import db_connection.DatabaseConnection;
import exception.ExceptionStorageHandler;

public class ContratLocationImpl implements ContratLocationDAO {

    private Connection connection; // Connexion à la base de données

    /**
     * Constructeur de la classe Contrat_locationImpl.
     *
     * @param connection La connexion à la base de données.
     */
    public ContratLocationImpl(Connection connection) {
        this.connection = connection;
    }

	@Override
	public ContratLocation findOne(long id) {
		PreparedStatement statement = null;
		ResultSet result = null;
		String query = "SELECT Id_Contrat_location, Montant_loyer, Date_debut, Date_fin, Modalite_chauffage, Modalite_eau_chaude_sanitaire, Date_versement FROM db1_sae.Contrat_location WHERE Id_Contrat_location = ?";
		
		try {
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			result = statement.executeQuery();
			
			if(result.next()) {
				return createEntities(result);
			}	
		}
		
		catch (Exception e) {
			ExceptionStorageHandler.logException(e, connection);
		}finally {
			DatabaseConnection.closeStatement(statement);
		}
		return null;
	}


	@Override
	public void insert(ContratLocation entity) {
	    PreparedStatement statement = null;
	    String query = "INSERT INTO db1_sae.Contrat_location (montant_loyer, date_debut, date_fin, modalite_chauffage, modalite_eau_chaude_sanitaire, date_versement) VALUES (?, ?, ?, ?, ?, ?)";

	    try {
	        statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        statement.setInt(1, entity.getMontantLoyer());
	        statement.setDate(2, entity.getDateDebut());
	        statement.setDate(3, entity.getDateFin());
	        statement.setString(4, entity.getModaliteChauffage());
	        statement.setString(5, entity.getModaliteEauChaudeSanitaire());
	        statement.setDate(6, entity.getDateVersement());

	        if (statement.executeUpdate() > 0) {
                ResultSet result = statement.getGeneratedKeys();
                if (result.next()) {
                    int id = result.getInt(1);
                    entity.setNumeroLocation(id);
                }
            }
	    } catch (Exception e) {
	        e.printStackTrace();
	        ExceptionStorageHandler.logException(e, connection);
	    } finally {
	        DatabaseConnection.closeStatement(statement);
	    }
	}




    /**
     * Recherche tous les contrats de location (fonctionnalité à implémenter).
     *
     * @return Liste des contrats de location.
     */
    @Override
    public List<ContratLocation> findAll() {
    	List<ContratLocation> contrats = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT Id_Contrat_location, Montant_loyer, Date_debut, Date_fin, Modalite_chauffage, Modalite_eau_chaude_sanitaire, Date_versement FROM db1_sae.Contrat_location";
        
        try {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            
            while (result.next()) {
                ContratLocation acte = createEntities(result);
                contrats.add(acte);
            } 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
            } catch (Exception e) {
    			ExceptionStorageHandler.logException(e, connection);
    		}finally {
    			DatabaseConnection.closeResult(result);
    			DatabaseConnection.closeStatement(statement);
    		}
        }
        
        return contrats;
    }

    @Override
	public void deleteById(long id) {
		PreparedStatement statement = null;
        String query = "DELETE FROM db1_sae.Contrat_location WHERE Id_Contrat_location = ?";
        
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
     * Met à jour un contrat de location existant dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Contrat_location à mettre à jour.
     */
	@Override
	public void update(ContratLocation entity) {
	    PreparedStatement statement = null;
	    String query = "UPDATE db1_sae.Contrat_location SET Montant_loyer = ?, Date_debut = ?, Date_fin = ?, Modalite_chauffage = ?, Modalite_eau_chaude_sanitaire = ?, Date_versement = ? WHERE Id_Contrat_location = ?";
	    
	    try {
	        statement = connection.prepareStatement(query);
	        statement.setInt(1, entity.getMontantLoyer());
	        statement.setDate(2, entity.getDateDebut());
	        statement.setDate(3, entity.getDateFin());
	        statement.setString(4, entity.getModaliteChauffage());
	        statement.setString(5, entity.getModaliteEauChaudeSanitaire());
	        statement.setDate(6, entity.getDateVersement());
	        statement.setLong(7, entity.getNumeroLocation());
	        statement.executeUpdate();
	    } catch (Exception e) {
	        ExceptionStorageHandler.logException(e, connection);
	    } finally {
	        DatabaseConnection.closeStatement(statement);
	    }
	}


	@Override
	public List<List<String>> procPageContratLocation() {
		CallableStatement statement = null;
		ResultSet result = null;
		String query = "{CALL db1_sae.get_contratLocations()}";
		List<List<String>> arrayRes = new ArrayList<>();
		
		try {
			statement = connection.prepareCall(query);
			if(statement.execute()) {
				result = statement.getResultSet();
				while(result.next()) {
					ArrayList<String> cell = new ArrayList<>();				            
		            addToCell(result, cell);
		            arrayRes.add(cell);
        }}
		} catch (Exception e) {
			ExceptionStorageHandler.logException(e, connection);
		}finally {
			DatabaseConnection.closeResult(result);
			DatabaseConnection.closeStatement(statement);
		}
		return arrayRes;
	}

	@Override
	public List<List<String>> procPageContratLocationActif() {
		CallableStatement statement = null;
		ResultSet result = null;
		String query = "{CALL db1_sae.get_contratLocationsActif()}";
		List<List<String>> arrayRes = new ArrayList<>();
		
		try {
		    statement = connection.prepareCall(query);
		    if (statement.execute()) {
		        result = statement.getResultSet();
		        while (result.next()) {
		        	ArrayList<String> cell = new ArrayList<>();		            
		            addToCell(result, cell);
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

	private void addToCell(ResultSet result, ArrayList<String> cell) throws SQLException {
		String unknownValue = "Unknown";
		cell.add(result.getString(1) != null ? result.getString(1) : unknownValue);
		cell.add(result.getString(2) != null ? result.getString(2) : unknownValue);
		cell.add(result.getString(3) != null ? result.getString(3) : unknownValue);
		String first = result.getString(4);
		String second = result.getString(5);
		cell.add((first != null ? first : "") + "\n" + (second != null ? second : ""));
		cell.add(result.getString(6) != null ? result.getString(6) : unknownValue);
		cell.add(result.getString(7) != null ? result.getString(7) : unknownValue);
		cell.add(result.getString(8) != null ? result.getString(8) : unknownValue);
		cell.add(result.getString(9) != null ? result.getString(9) : unknownValue);
		cell.add(result.getString(10) != null ? result.getString(10) : unknownValue);
		cell.add(result.getString(11) != null ? result.getString(11) : unknownValue);
		cell.add(result.getString(12) != null ? result.getString(12) : unknownValue);
	}

	@Override
	public ContratLocation createEntities(ResultSet result) throws SQLException {
		ContratLocation contratLocation = new ContratLocation();
		
		contratLocation.setNumeroLocation(1);
		contratLocation.setMontantLoyer(result.getInt(2));
		contratLocation.setDateDebut(result.getDate(3));
		contratLocation.setDateFin(result.getDate(4));
		contratLocation.setModaliteChauffage(result.getString(5));
		contratLocation.setModaliteEauChaudeSanitaire(result.getString(6));
		contratLocation.setDateVersement(result.getDate(7));
		
		return contratLocation;
	}

	@Override
	public void procRemoveFkBienLocation(int fkToRm) {
		CallableStatement statement = null;
	    try {
	        String sql = "{CALL db1_sae.rm_fkBienLocataire(?)}";
	        statement = connection.prepareCall(sql);
	        statement.setInt(1, fkToRm);
	        statement.execute();
	        
	} catch (Exception e) {
		ExceptionStorageHandler.logException(e, connection);
	}finally {
		DatabaseConnection.closeStatement(statement);
	}
	    }

	@Override
	public void procUpdateFkBienLocation(int fkToUpdate, int newFk, int locataireKey) {
		 CallableStatement statement = null;
		    try {
		        // Prepare the callable statement
		        String sql = "{CALL db1_sae.update_fkBienLocataire(?, ?, ?)}";
		        statement = connection.prepareCall(sql);
		        
		        // Set the parameters
		        statement.setInt(1, fkToUpdate);
		        statement.setInt(2, newFk);
		        statement.setInt(3, locataireKey);
		        // Execute the callable statement
		        statement.execute();
		    }catch (Exception e) {
				ExceptionStorageHandler.logException(e, connection);
				e.printStackTrace();
			}finally {
				DatabaseConnection.closeStatement(statement);
			}
	}

	@Override
	public List<List<String>> procContratLocationDisponible() {
		CallableStatement statement = null;
		ResultSet result = null;
		String query = "{CALL db1_sae.get_ContratLocNotFkInBien()}";
		List<List<String>> arrayRes = new ArrayList<>();
		
		try {
			statement = connection.prepareCall(query);
			if(statement.execute()) {
				result = statement.getResultSet();
				while(result.next()) {
					ArrayList<String> cell = new ArrayList<>();
					cell.add(result.getString(1));
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
	public void procCascadeDelete(int idCL) {
		CallableStatement statement = null;
		try {
	        // Prepare the callable statement
	        String sql = "{CALL db1_sae.del_ContratLocationCascade(?)}";
	        statement = connection.prepareCall(sql);
	        
	        statement.setInt(1, idCL);

	        statement.execute();
	        
	    }catch (Exception e) {
			ExceptionStorageHandler.logException(e, connection);
		}finally {
			DatabaseConnection.closeStatement(statement);
		}
	}
	
	
	
}
