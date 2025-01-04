package dao.implementation;

import java.util.List;

import java.sql.*;
import java.util.ArrayList;
import dao.BienDAO;
import dao.entities.Bien;
import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

/**
 * Implémentation de l'interface {@link BienDAO} pour gérer les opérations sur les entités "Bien".
 */
public class BienImpl implements BienDAO {
    
    private Connection connection; // Connexion à la base de données
    
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
                Bien bien = createEntities(result);
                return bien;
            } 
        } catch (Exception e) {
			ExceptionStorageHandler.LogException(e, connection);
		}
		
		finally {
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
            } catch (SQLException e) {
                e.printStackTrace();
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
        // TODO Auto-generated method stub
    }
    

    /**
     * Met à jour un bien existant dans la base de données (fonctionnalité à implémenter).
     * 
     * @param entity L'entité Bien à mettre à jour.
     */
    @Override
    public void update(Bien entity) {
        // TODO Auto-generated method stub
    }

    /**
     * Supprime un bien de la base de données (fonctionnalité à implémenter).
     * 
     * @param l'id du Bien à supprimer.
     */
    @Override
    public void deleteById(long id) {
    	// TODO Auto-generated method stub
    	
    }
    
    @Override
    public void delete(Bien entity) {
        // TODO Auto-generated method stub
    }
    
	@Override
	public List<List<String>> BienStatus() {
		CallableStatement statement = null;
		ResultSet result = null;
		String query = "{CALL db1_sae.status_page_principale()}";
		List<List<String>> arrayRes = new ArrayList<>();
		
		try {
			statement = connection.prepareCall(query);
			if(statement.execute()) {
				result = statement.getResultSet();
				while(result.next()) {
					ArrayList<String> cell = new ArrayList<String>();
					for(int i = 2; i <= 11; i++) {
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
        
        bien.setId_bien(result.getInt(1));
        bien.setEtage(result.getInt(2));
        bien.setAdresse(result.getString(3));
        bien.setVille(result.getString(4));
        bien.setCode_postal(result.getString(5));
        bien.setSuperficie(result.getBigDecimal(6));
        bien.setNombre_de_piece(result.getInt(7));
        bien.setMeuble(result.getBoolean(8));
        bien.setAccessoire_prive(result.getString(9));
        bien.setAccessoire_commun(result.getString(10));
        bien.setEst_garage(result.getBoolean(12));
        
        return bien;  // Retourne l'entité Bien construite
    }
    
    @Override
	public String[] get_AllAdresses() {
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
			ExceptionStorageHandler.LogException(e, connection);
		}
		
		return factureNumbers;
	}
    
}
