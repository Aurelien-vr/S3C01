package dao.implementation;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import dao.BienDAO;
import dao.entities.Bien;
import db_connection.DatabaseConnection;
import exception.ExceptionStorageHandler;

/**
 * Implémentation de l'interface {@link BienDAO} pour gérer les opérations sur les entités "Bien".
 */
public class BienImpl implements BienDAO {
    
    private Connection connection; // Connexion à la base de données
    private String stringUnknown = "Unknown";
    
    /**
     * Constructeur de la classe BienImpl.
     * 
     * @param connection La connexion à la base de données.
     */
    public BienImpl(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Recherche un bien par son identifiant.
     * 
     * @param id L'identifiant du bien à rechercher.
     * @return L'entité {@link Bien} si trouvée, sinon {@code null}.
     */
    @Override
    public Bien findOne(long id) {
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Bien WHERE Id_Bien = ?";
        
        try {
            // Préparation de la requête SQL avec l'ID du bien
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            result = statement.executeQuery();
            
            // Si un résultat est trouvé, création de l'entité Bien
            if (result.next()) {
                return createEntities(result);
            } 
        } catch (Exception e) {
			ExceptionStorageHandler.logException(e, connection);
		}finally {
			DatabaseConnection.closeStatement(statement);
		}
		return null;
    }

    /**
     * Recherche tous les biens (fonctionnalité à implémenter).
     * 
     * @return Liste des biens ou {@code null} si non implémentée.
     */
    @Override
    public List<Bien> findAll() {
    	List<Bien> biens = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Bien";
        
        try {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            
            while (result.next()) {
                Bien acte = createEntities(result);
                biens.add(acte);
            } 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
            }catch (Exception e) {
                ExceptionStorageHandler.logException(e, connection);
            } finally {
                DatabaseConnection.closeStatement(statement);
            }
        }
        return biens;
    }

    /**
     * Crée un nouveau bien dans la base de données (fonctionnalité à implémenter).
     * 
     * @param entity L'entité Bien à créer.
     */
    @Override
    public void insert(Bien entity) {
        PreparedStatement statement = null;
        String query = "INSERT INTO db1_sae.Bien(etage, adresse, ville, code_postal, superficie, nombre_de_piece, meuble, accessoire_prive, accessoire_commun, est_garage) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try {
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, entity.getEtage());
            statement.setString(2, entity.getAdresse());
            statement.setString(3, entity.getVille());
            statement.setString(4, entity.getCodePostal());
            statement.setBigDecimal(5, entity.getSuperficie());
            statement.setInt(6, entity.getNombreDePiece());
            statement.setBoolean(7, entity.isMeuble());
            statement.setString(8, entity.getAccessoirePrive());
            statement.setString(9, entity.getAccessoireCommun());
            statement.setBoolean(10, entity.isEstGarage());

            if (statement.executeUpdate() > 0) {
                ResultSet result = statement.getGeneratedKeys();
                if (result.next()) {
                    int id = result.getInt(1);
                    entity.setIdBien(id);
                }
            }
        } catch (Exception e) {
            ExceptionStorageHandler.logException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }
    

    /**
     * Met à jour un bien existant dans la base de données (fonctionnalité à implémenter).
     * 
     * @param entity L'entité Bien à mettre à jour.
     */
    public void update(Bien entity) {
        PreparedStatement statement = null;
        String query = "UPDATE db1_sae.Bien SET etage = ?, adresse = ?, ville = ?, code_postal = ?, " +
                       "superficie = ?, nombre_de_piece = ?, meuble = ?, accessoire_prive = ?, " +
                       "accessoire_commun = ?, est_garage = ? WHERE Id_Bien = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, entity.getEtage());
            statement.setString(2, entity.getAdresse());
            statement.setString(3, entity.getVille());
            statement.setString(4, entity.getCodePostal());
            statement.setBigDecimal(5, entity.getSuperficie());
            statement.setInt(6, entity.getNombreDePiece());
            statement.setBoolean(7, entity.isMeuble());
            statement.setString(8, entity.getAccessoirePrive());
            statement.setString(9, entity.getAccessoireCommun());
            statement.setBoolean(10, entity.isEstGarage());
            statement.setLong(11, entity.getIdBien()); 
            statement.executeUpdate();
        } catch (Exception e) {
            ExceptionStorageHandler.logException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }

    /**
     * Supprime un bien de la base de données (fonctionnalité à implémenter).
     * 
     * @param l'id du Bien à supprimer.
     */
    @Override
    public void deleteById(long id) {
    	PreparedStatement statement = null;
        String query = "DELETE FROM db1_sae.Bien WHERE Id_Bien = ?";
        
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
    

    
	@Override
	public List<List<String>> bienStatus() {
		CallableStatement statement = null;
		ResultSet result = null;
		String query = "{CALL db1_sae.status_page_principale()}";
		List<List<String>> arrayRes = new ArrayList<>();
		
		try {
			statement = connection.prepareCall(query);
			if(statement.execute()) {
				result = statement.getResultSet();
				while(result.next()) {
					ArrayList<String> cell = new ArrayList<>();
					for(int i = 2; i <= 11; i++) {
						 String value = result.getString(i);
		                    cell.add(value != null ? value : stringUnknown);
					}
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

    /**
     * Crée une entité {@link Bien} à partir des résultats d'une requête SQL.
     * 
     * @param result Le {@link ResultSet} contenant les données du bien.
     * @return L'entité Bien construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public Bien createEntities(ResultSet result) throws SQLException {
        // Création de l'entité Bien à partir des données du ResultSet
        Bien bien = new Bien();
        
        bien.setIdBien(result.getInt(1));
        bien.setEtage(result.getInt(2));
        bien.setAdresse(result.getString(3));
        bien.setVille(result.getString(4));
        bien.setCodePostal(result.getString(5));
        bien.setSuperficie(result.getBigDecimal(6));
        bien.setNombreDePiece(result.getInt(7));
        bien.setMeuble(result.getBoolean(8));
        bien.setAccessoirePrive(result.getString(9));
        bien.setAccessoireCommun(result.getString(10));
        bien.setEstGarage(result.getBoolean(12));
        
        return bien;  // Retourne l'entité Bien construite
    }
    
    @Override
	public String[] getAllAdresses() {
		CallableStatement statement = null;
		ResultSet result = null;
		String query = "{CALL db1_sae.get_AllAdresses()}";
		String[] factureNumbers = null;
		
		try {
			statement = connection.prepareCall(query);
			if(statement.execute()) {
				result = statement.getResultSet();
				ArrayList<String> factureList = new ArrayList<>();
	            while (result.next()) {
	            	 String address = result.getString(1);
	            	 String ville = result.getString(2);	            	 
	            	 factureList.add(address + " | " + ville);
	            }
	            factureNumbers = factureList.toArray(new String[0]);
			}
		}catch (Exception e) {
			ExceptionStorageHandler.logException(e, connection);
		}
		
		return factureNumbers;
	}

	@Override
	public List<List<String>> procPageBien() {
		CallableStatement statement = null;
		ResultSet result = null;
		String query = "{CALL db1_sae.get_biens()}";
		List<List<String>> arrayRes = new ArrayList<>();
		
		try {
			statement = connection.prepareCall(query);
			if(statement.execute()) {
				result = statement.getResultSet();
				while(result.next()) {
					ArrayList<String> cell = new ArrayList<>();
					for(int i = 1; i <= 12; i++) {
						 String value = result.getString(i);
		                    cell.add(value != null ? value : stringUnknown);
					}
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
	public String[] procGetClNotInBien() {
		CallableStatement statement = null;
		ResultSet result = null;
		String query = "{CALL db1_sae.get_ContratLocNotFkInBien()}";
		String[] bienList = null;
		
		try {
			statement = connection.prepareCall(query);
			if(statement.execute()) {
				result = statement.getResultSet();
				ArrayList<String> factureList = new ArrayList<>();
	            while (result.next()) {
	            	factureList.add(result.getString(1));
	            }
	            bienList = factureList.toArray(new String[0]);
			}
		}catch (Exception e) {
			ExceptionStorageHandler.logException(e, connection);
		}finally {
			DatabaseConnection.closeResult(result);
			DatabaseConnection.closeStatement(statement);
		}
		
		return bienList;
	}
    
	
    @Override
    public void insertFK(int id, int idContratLocation) {
        PreparedStatement statement = null;
        String query = "UPDATE db1_sae.Bien SET Id_Contrat_Location = ? WHERE Id_Bien = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, idContratLocation);
            statement.setInt(2, id);
        } catch (Exception e) {
            ExceptionStorageHandler.logException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }

	@Override
	public List<List<String>> procBienSansContrat() {
		CallableStatement statement = null;
		ResultSet result = null;
		String query = "{CALL db1_sae.get_bienSansContrat()}";
		List<List<String>> arrayRes = new ArrayList<>();
		
		try {
			statement = connection.prepareCall(query);
			if(statement.execute()) {
				result = statement.getResultSet();
				while(result.next()) {
					ArrayList<String> cell = new ArrayList<>();
					cell.add(result.getString(1) != null ? result.getString(1) : stringUnknown);
					cell.add(result.getString(2) != null ? result.getString(2): stringUnknown);
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
	public String procAdressOfFacture(String refFacture) {
		CallableStatement statement = null;
        ResultSet result = null;
        String query = "{CALL db1_sae.get_BienFromRefFacture(?)}";
        String address = stringUnknown; // Default value if no address is returned
        
        try {
            statement = connection.prepareCall(query);
            statement.setString(1, refFacture); // Set the input parameter for the stored procedure
            if (statement.execute()) {
                result = statement.getResultSet();
                if (result.next()) {
                    address = result.getString(1) != null ? result.getString(1) : stringUnknown;
                }
            }
        } catch (Exception e) {
            ExceptionStorageHandler.logException(e, connection);
        } finally {
            DatabaseConnection.closeResult(result);
            DatabaseConnection.closeStatement(statement);
        }
        return address;
	}

	@Override
	public void procDeletBienCascade(int idBien) {
		CallableStatement statement = null;
		try {
	        // Prepare the callable statement
	        String sql = "{CALL db1_sae.del_BienCascade(?)}";
	        statement = connection.prepareCall(sql);
	        
	        statement.setInt(1, idBien);
	        statement.execute();
	
	    }catch (Exception e) {
			ExceptionStorageHandler.logException(e, connection);
		}finally {
			DatabaseConnection.closeStatement(statement);
		}
	}
}
