/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universityenrollmentsystem;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;



public class UniversityTableModel extends AbstractTableModel{
    
    // TASK 04: The columnNames array needs to be modified.
    private String[] columnNames = {"Name", "Surname", "Date of Birth","Role", "Year of Birth"}; 
    private ArrayList<Person> list; 
    
    public UniversityTableModel(ArrayList<Person> personList){
        list = personList;
    }

    @Override
    public int getRowCount() {
        return list.size();   
    }

    @Override
    public int getColumnCount() {
        // TASK 04: This must be changed to reflect the new columnNames size.
        return columnNames.length;
    }

  @Override
public Object getValueAt(int rowIndex, int columnIndex) {
    Object temp = null; 
    
    if (columnIndex == 0) { 
        temp = list.get(rowIndex).getName(); 
    } 
    else if (columnIndex == 1) { 
        temp = list.get(rowIndex).getSurname(); 
    } 
    else if (columnIndex == 2) { 
        temp = list.get(rowIndex).getStringDate(); 
    } 
    else if (columnIndex == 3) {
        if(list.get(rowIndex) instanceof Student)
            temp = "Student"; 
        else if(list.get(rowIndex) instanceof Lecturer)
            temp = "Lecturer";
        else if (list.get(rowIndex) instanceof Alumni)
            temp = "Alumni";
    } 
    else if (columnIndex == 4){
        if (list.get(rowIndex).getDob() != null){
        
            temp = list.get(rowIndex).getDob().getYear();
            
        } else {
            temp = "N/A";
        }
    }
    
    return temp;  // ✅ Return at the VERY END
}
    
    // needed to show column names in JTable 
   @Override
   public String getColumnName(int col) { 
      return columnNames[col]; 
   } 
    
}

