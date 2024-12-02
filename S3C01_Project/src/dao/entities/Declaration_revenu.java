package dao.entities;

import java.math.BigDecimal;
import java.util.Date;

public class Declaration_revenu {

    private int id_declaration_revenu;
    private Date date_acquisition;
    private int locataires;
    private BigDecimal recette_immeuble;
    private int id_bien;

    public int getId_declaration_revenu() {
        return id_declaration_revenu;
    }

    public void setId_declaration_revenu(int id_declaration_revenu) {
        this.id_declaration_revenu = id_declaration_revenu;
    }

    public Date getDate_acquisition() {
        return date_acquisition;
    }

    public void setDate_acquisition(Date date_acquisition) {
        this.date_acquisition = date_acquisition;
    }

    public int getLocataires() {
        return locataires;
    }

    public void setLocataires(int locataires) {
        this.locataires = locataires;
    }

    public BigDecimal getRecette_immeuble() {
        return recette_immeuble;
    }

    public void setRecette_immeuble(BigDecimal recette_immeuble) {
        this.recette_immeuble = recette_immeuble;
    }

    public int getId_bien() {
        return id_bien;
    }

    public void setId_bien(int id_bien) {
        this.id_bien = id_bien;
    }

    @Override
    public String toString() {
        return "Declaration_revenu{" +
               "id_declaration_revenu=" + id_declaration_revenu +
               ", date_acquisition=" + (date_acquisition != null ? date_acquisition : "N/A") +
               ", locataires=" + locataires +
               ", recette_immeuble=" + (recette_immeuble != null ? recette_immeuble : "N/A") +
               ", id_bien=" + id_bien +
               '}';
    }
}
