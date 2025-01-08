package controller;

import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import com.formdev.flatlaf.json.ParseException;
import view.TableSkeleton;

public abstract class TableSkeletonController extends PageHeaderSkeletonController{

    protected TableSkeleton view = new TableSkeleton();
    protected DefaultTableModel modelTable;

    public TableSkeletonController() {
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