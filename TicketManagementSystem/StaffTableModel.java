import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class StaffTableModel extends AbstractTableModel {
    private ArrayList<StaffMember> staffList;
    private String[] columnNames = {"Staff ID", "Name", "Surname", "Role", "Phone", "DOB", "Details"};
    
    public StaffTableModel(ArrayList<StaffMember> staffList) {
        this.staffList = staffList;
    }
    
    @Override
    public int getRowCount() {
        return staffList.size();
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StaffMember staff = staffList.get(rowIndex);
        
        switch(columnIndex) {
            case 0: return staff.getStaffId();
            case 1: return staff.getName();
            case 2: return staff.getSurName();
            case 3: return staff.getRole();
            case 4: return staff.getPhoneNo();
            case 5: return staff.getDob();
            case 6: 
                if (staff instanceof Doctor) {
                    Doctor doc = (Doctor) staff;
                    return "Licence: " + doc.getLicenceNumber() + ", Spec: " + doc.getSpecialization();
                } else if (staff instanceof Receptionist) {
                    Receptionist rec = (Receptionist) staff;
                    return "Desk: " + rec.getDeskNumber() + ", Hours: " + rec.getHoursPerWeek();
                }
                return "";
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
}