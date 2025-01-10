package dao.implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.LocataireDAO;
import dao.entities.Locataire;
import dbConnection.DatabaseConnection;
import exception.ExceptionStorageHandler;

/**
 * Implémentation de l'interface {@link LocataireDAO} pour gérer les opérations sur les entités "Locataire".
 */
public class LocataireImpl implements LocataireDAO {

    private Connection connection; // Connexion à la base de données

    /**
     * Constructeur de la classe LocataireImpl.
     *
     * @param connection La connexion à la base de données.
     */
    public LocataireImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Recherche un locataire par son identifiant.
     *
     * @param id L'identifiant du locataire.
     * @return L'entité {@link Locataire} si trouvée, sinon {@code null}.
     */
    @Override
    public Locataire findOne(long id) {
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Locataire WHERE id_locataire = ?";

        try {
            // Préparation de la requête SQL avec l'identifiant du locataire
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            result = statement.executeQuery();

            // Si un résultat est trouvé, création de l'entité Locataire
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
            } catch (Exception e) {
                ExceptionStorageHandler.LogException(e, connection);
            } finally {
                DatabaseConnection.closeStatement(statement);
                DatabaseConnection.closeResult(result);
            }
        }

        return null; // Si aucun locataire n'est trouvé, retour de null
    }

    /**
     * Recherche tous les locataires (fonctionnalité à implémenter).
     *
     * @return Liste des locataires ou {@code null} si non implémentée.
     */
    @Override
    public List<Locataire> findAll() {
    	List<Locataire> locs = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM db1_sae.Locataire";
        
        try {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            
            while (result.next()) {
                Locataire acte = createEntities(result);
                locs.add(acte);
            } 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
            } catch (Exception e) {
                ExceptionStorageHandler.LogException(e, connection);
            } finally {
                DatabaseConnection.closeStatement(statement);
                DatabaseConnection.closeResult(result);
            }
        }
        
        return locs;
    }


    /**
     * Crée un nouveau locataire dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Locataire à créer.
     */
    @Override
    public void insert(Locataire entity) {
        PreparedStatement statement = null;
        String query = "INSERT INTO db1_sae.Locataire(nom, prenom, date_de_naissance, iban) VALUES (?,?,?,?)";

        try {
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getNom());
            statement.setString(2, entity.getPrenom());
            statement.setDate(3, entity.getDate_de_naissance());
            statement.setString(4, entity.getIban());

            if (statement.executeUpdate() > 0) {
                ResultSet result = statement.getGeneratedKeys();
                if (result.next()) {
                    int id = result.getInt(1);
                    entity.setId_locataire(id);
                }
                System.out.println("Locataire inserted");
            }
        } catch (Exception e) {
            ExceptionStorageHandler.LogException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }

    /**
     * Met à jour un locataire existant dans la base de données (fonctionnalité à implémenter).
     *
     * @param entity L'entité Locataire à mettre à jour.
     */
    @Override
    public void update(Locataire entity) {
        PreparedStatement statement = null;
        String query = "UPDATE db1_sae.Locataire SET nom = ?, prenom = ?, date_de_naissance = ?, iban = ? WHERE id_locataire = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, entity.getNom());
            statement.setString(2, entity.getPrenom());
            statement.setDate(3, entity.getDate_de_naissance());
            statement.setString(4, entity.getIban());
            statement.setLong(5, entity.getId_locataire());

            statement.executeUpdate();
        } catch (Exception e) {
            ExceptionStorageHandler.LogException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }


    /**
     * Supprime un locataire par son identifiant (fonctionnalité à implémenter).
     *
     * @param id L'identifiant du locataire à supprimer.
     */
    @Override
    public void deleteById(long id) {
    	PreparedStatement statement = null;
        String query = "DELETE FROM db1_sae.Locataire WHERE Id_Locataire = ?";
        
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
     * Crée une entité {@link Locataire} à partir des résultats d'une requête SQL.
     *
     * @param result Le {@link ResultSet} contenant les données du locataire.
     * @return L'entité Locataire construite.
     * @throws SQLException Si une erreur SQL se produit lors de la lecture des données.
     */
    @Override
    public Locataire createEntities(ResultSet result) throws SQLException {
        // Création de l'entité Locataire à partir des données du ResultSet
        Locataire locataire = new Locataire();
        locataire.setNom(result.getString("nom"));
        locataire.setPrenom(result.getString("prenom"));
        locataire.setDate_de_naissance(result.getDate("date_de_naissance"));
        locataire.setIban(result.getString("iban"));
        return locataire; // Retourne l'entité Locataire construite
    }
    
    @Override
    public void insertFK(int idLocataire, int idContratLocation) {
        PreparedStatement statement = null;
        String query = "UPDATE db1_sae.Locataire SET Id_Contrat_Location = ? WHERE Id_Locataire = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, idContratLocation);
            statement.setInt(2, idLocataire);

            if (statement.executeUpdate() > 0) {
                System.out.println("FK inserted");
            }
        } catch (Exception e) {
            ExceptionStorageHandler.LogException(e, connection);
        } finally {
            DatabaseConnection.closeStatement(statement);
        }
    }

	@Override
	public List<List<String>> procGetLocataires() {
		CallableStatement statement = null;
		ResultSet result = null;
		String query = "{CALL db1_sae.get_locataires()}";
		List<List<String>> arrayRes = new ArrayList<>();
		try {
			statement = connection.prepareCall(query);
			if(statement.execute()) {
				result = statement.getResultSet();
				while(result.next()) {
					insertCell(result, arrayRes);
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
	public List<List<String>> procGetLocatairesActifs() {
		CallableStatement statement = null;
		ResultSet result = null;
		String query = "{CALL db1_sae.get_locatairesActif()}";
		List<List<String>> arrayRes = new ArrayList<>();
		try {
			statement = connection.prepareCall(query);
			if(statement.execute()) {
				result = statement.getResultSet();
				while(result.next()) {
					insertCell(result, arrayRes);
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

	private void insertCell(ResultSet result, List<List<String>> arrayRes) throws SQLException {
		String unknownString = "Unknown";
		ArrayList<String> cell = new ArrayList<>();
		cell.add(result.getString(1) != null ? result.getString(1) : unknownString);
		cell.add(result.getString(2) + result.getString(3) != null ? result.getString(2) + result.getString(3) : unknownString);
		cell.add(result.getString(4) != null ? result.getString(4) : "Actuellement locataires d'aucun bien");
		cell.add(result.getString(5) != null ? result.getString(5) : unknownString);
		cell.add(result.getString(6) != null ? result.getString(6) : unknownString);

		arrayRes.add(cell);
	}

	@Override
	public List<List<String>> procLocataireSansContrat() {
		CallableStatement statement = null;
		ResultSet result = null;
		String query = "{CALL db1_sae.get_locataireSansContrat()}";
		List<List<String>> arrayRes = new ArrayList<>();
		
		try {
			statement = connection.prepareCall(query);
			if(statement.execute()) {
				result = statement.getResultSet();
				while(result.next()) {
					ArrayList<String> cell = new ArrayList<>();
					String value = result.getString(1) +" "+ result.getString(2);
					cell.add(value != null ? value : "Unknown");
					cell.add(result.getString(3) != null ? result.getString(3): "Unknown");
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
