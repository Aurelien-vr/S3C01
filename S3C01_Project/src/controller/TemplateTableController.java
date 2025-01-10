package controller;

import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import com.formdev.flatlaf.json.ParseException;
import view.TemplateTable;

public abstract class TemplateTableController extends TemplateMenuController{

    protected TemplateTable view = new TemplateTable();
    protected DefaultTableModel modelTable;

    public TemplateTableController() {
    	super();
    }

    abstract void fillTable();
    abstract void updateFooter();
    
    public static String transformDate(String inputDate) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            java.util.Date date = inputFormat.parse(inputDate);
            return outputFormat.format(date);
        } catch (ParseException | java.text.ParseException e) {
        	e.printStackTrace();
            return null;
        }
    }

}