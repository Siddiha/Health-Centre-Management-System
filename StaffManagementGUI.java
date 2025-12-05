import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class StaffManagementGUI extends JFrame {
    private JTable staffTable;
    private StaffTableModel tableModel;
    private ArrayList<StaffMember> staffList;
    
    public StaffManagementGUI(ArrayList<StaffMember> staffList) {
        this.staffList = staffList;
        initializeGUI();
    }
    
    private void initializeGUI() {
        setTitle("Westminster Health Centre - Staff Management");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);
        
        // Create table model and table
        tableModel = new StaffTableModel(staffList);
        staffTable = new JTable(tableModel);
        staffTable.setAutoCreateRowSorter(true);
        
        // Set column widths
        staffTable.getColumnModel().getColumn(0).setPreferredWidth(80);  // ID
        staffTable.getColumnModel().getColumn(1).setPreferredWidth(100); // Name
        staffTable.getColumnModel().getColumn(2).setPreferredWidth(100); // Surname
        staffTable.getColumnModel().getColumn(3).setPreferredWidth(100); // Role
        staffTable.getColumnModel().getColumn(4).setPreferredWidth(120); // Phone
        staffTable.getColumnModel().getColumn(5).setPreferredWidth(100); // DOB
        staffTable.getColumnModel().getColumn(6).setPreferredWidth(200); // Details
        
        // Create scroll pane for table
        JScrollPane scrollPane = new JScrollPane(staffTable);
        
        // Create buttons
        JButton addButton = new JButton("Add Staff");
        JButton removeButton = new JButton("Remove Selected");
        JButton statsButton = new JButton("Show Statistics");
        JButton refreshButton = new JButton("Refresh");
        JButton exitButton = new JButton("Exit");
        
        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(statsButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(exitButton);
        
        // Add action listeners
        addButton.addActionListener(e -> addStaffMember());
        removeButton.addActionListener(e -> removeSelectedStaff());
        statsButton.addActionListener(e -> showStatistics());
        refreshButton.addActionListener(e -> refreshTable());
        exitButton.addActionListener(e -> dispose());
        
        // Add components to frame
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void addStaffMember() {
        JOptionPane.showMessageDialog(this, 
            "Add staff functionality would open a dialog to input staff details.\n" +
            "This would connect to the WestminsterHealthCenterManager add method.",
            "Add Staff", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void removeSelectedStaff() {
        int selectedRow = staffTable.getSelectedRow();
        if (selectedRow >= 0) {
            int modelRow = staffTable.convertRowIndexToModel(selectedRow);
            String staffId = (String) tableModel.getValueAt(modelRow, 0);
            String staffName = (String) tableModel.getValueAt(modelRow, 1);
            
            int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to remove " + staffName + " (ID: " + staffId + ")?",
                "Confirm Removal",
                JOptionPane.YES_NO_OPTION);
                
            if (confirm == JOptionPane.YES_OPTION) {
                // Remove from list and update table
                staffList.remove(modelRow);
                tableModel.fireTableDataChanged();
                JOptionPane.showMessageDialog(this, "Staff member removed successfully!");
            }
        } else {
            JOptionPane.showMessageDialog(this, 
                "Please select a staff member to remove.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void showStatistics() {
        long doctorCount = staffList.stream()
            .filter(s -> s instanceof Doctor)
            .count();
        long receptionistCount = staffList.stream()
            .filter(s -> s instanceof Receptionist)
            .count();
            
        String message = String.format(
            "Total Staff: %d\n" +
            "Doctors: %d\n" +
            "Receptionists: %d\n" +
            "Doctor Percentage: %.1f%%\n" +
            "Receptionist Percentage: %.1f%%",
            staffList.size(),
            doctorCount,
            receptionistCount,
            (double) doctorCount / staffList.size() * 100,
            (double) receptionistCount / staffList.size() * 100
        );
        
        JOptionPane.showMessageDialog(this, message, "Staff Statistics", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void refreshTable() {
        tableModel.fireTableDataChanged();
        JOptionPane.showMessageDialog(this, "Table refreshed!");
    }
}