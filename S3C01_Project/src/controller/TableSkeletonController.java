package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.json.ParseException;

import dao.BienDAO;
import dao.DAOFactory;
import exception.ExceptionStorageHandler;
import view.TableSkeleton;

public abstract class TableSkeletonController {

    protected TableSkeleton view = new TableSkeleton();
    protected DefaultTableModel modelTable;

    public TableSkeletonController() {
        this.modelTable = new DefaultTableModel();
    }

    abstract void fillTable();
    
    public static String transformDate(String inputDate) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            java.util.Date date = inputFormat.parse(inputDate);
            return outputFormat.format(date);
        } catch (ParseException | java.text.ParseException e) {
            ExceptionStorageHandler.LogException(e, null);
            return null;
        }
    }

}