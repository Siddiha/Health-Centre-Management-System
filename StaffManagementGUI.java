import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffManagementGUI extends JFrame {
    private JTable staffTable;
    private StaffTableModel tableModel;
    private ArrayList<StaffMember> staffList;
    private WestminsterHealthCenterManager manager;
    
    public StaffManagementGUI(ArrayList<StaffMember> staffList) {
        this.staffList = staffList;
        this.manager = new WestminsterHealthCenterManager(100);
        initializeGUI();
    }
    
    private void initializeGUI() {
        setTitle("Westminster Health Centre - Staff Management");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        
        tableModel = new StaffTableModel(staffList);
        staffTable = new JTable(tableModel);
        staffTable.setAutoCreateRowSorter(true);
        
        staffTable.getColumnModel().getColumn(0).setPreferredWidth(80);
        staffTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        staffTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        staffTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        staffTable.getColumnModel().getColumn(4).setPreferredWidth(120);
        staffTable.getColumnModel().getColumn(5).setPreferredWidth(100);
        staffTable.getColumnModel().getColumn(6).setPreferredWidth(250);
        
        JScrollPane scrollPane = new JScrollPane(staffTable);
        
        JButton addButton = new JButton("Add Staff");
        JButton removeButton = new JButton("Remove Selected");
        JButton statsButton = new JButton("Show Statistics");
        JButton refreshButton = new JButton("Refresh");
        JButton saveButton = new JButton("Save to File");
        JButton exitButton = new JButton("Exit");
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(statsButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(exitButton);
        
        addButton.addActionListener(e -> addStaffMember());
        removeButton.addActionListener(e -> removeSelectedStaff());
        statsButton.addActionListener(e -> showStatistics());
        refreshButton.addActionListener(e -> refreshTable());
        saveButton.addActionListener(e -> saveToFile());
        exitButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                "Save before exiting?", "Confirm Exit", JOptionPane.YES_NO_CANCEL_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                saveToFile();
                dispose();
            } else if (confirm == JOptionPane.NO_OPTION) {
                dispose();
            }
        });
        
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void addStaffMember() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        
        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Doctor", "Receptionist"});
        JTextField nameField = new JTextField();
        JTextField surnameField = new JTextField();
        JTextField dobField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField idField = new JTextField();
        JTextField specField = new JTextField(); // For doctor
        JTextField licenceField = new JTextField(); // For doctor
        JTextField consultField = new JTextField(); // For doctor
        JTextField deskField = new JTextField(); // For receptionist
        JTextField hoursField = new JTextField(); // For receptionist
        
        panel.add(new JLabel("Staff Type:"));
        panel.add(typeCombo);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Surname:"));
        panel.add(surnameField);
        panel.add(new JLabel("Date of Birth (YYYY-MM-DD):"));
        panel.add(dobField);
        panel.add(new JLabel("Phone Number:"));
        panel.add(phoneField);
        panel.add(new JLabel("Staff ID:"));
        panel.add(idField);
        
        ActionListener typeListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isDoctor = typeCombo.getSelectedItem().equals("Doctor");
                specField.setVisible(isDoctor);
                licenceField.setVisible(isDoctor);
                consultField.setVisible(isDoctor);
                deskField.setVisible(!isDoctor);
                hoursField.setVisible(!isDoctor);
            }
        };
        
        typeCombo.addActionListener(typeListener);
        
        JPanel doctorPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        doctorPanel.add(new JLabel("Specialization:"));
        doctorPanel.add(specField);
        doctorPanel.add(new JLabel("Licence Number:"));
        doctorPanel.add(licenceField);
        doctorPanel.add(new JLabel("Consultations/Week:"));
        doctorPanel.add(consultField);
        
        JPanel receptionistPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        receptionistPanel.add(new JLabel("Desk Number:"));
        receptionistPanel.add(deskField);
        receptionistPanel.add(new JLabel("Hours/Week:"));
        receptionistPanel.add(hoursField);
        
        panel.add(doctorPanel);
        panel.add(receptionistPanel);
        
        typeListener.actionPerformed(null); // Set initial visibility
        
        int result = JOptionPane.showConfirmDialog(this, panel, 
            "Add New Staff Member", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText().trim();
                String surname = surnameField.getText().trim();
                String dob = dobField.getText().trim();
                String phone = phoneField.getText().trim();
                String id = idField.getText().trim();
                
                // Basic validation
                if (name.isEmpty() || surname.isEmpty() || id.isEmpty()) {
                    JOptionPane.showMessageDialog(this, 
                        "Name, Surname and ID are required fields!", 
                        "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                StaffMember newMember;
                String type = (String) typeCombo.getSelectedItem();
                
                if (type.equals("Doctor")) {
                    String specialization = specField.getText().trim();
                    String licence = licenceField.getText().trim();
                    int consultations = Integer.parseInt(consultField.getText().trim());
                    
                    newMember = new Doctor(name, surname, dob, phone, id, 
                                          licence, specialization, consultations);
                } else {
                    int desk = Integer.parseInt(deskField.getText().trim());
                    int hours = Integer.parseInt(hoursField.getText().trim());
                    
                    newMember = new Receptionist(name, surname, dob, phone, id, 
                                                desk, hours);
                }
                
                staffList.add(newMember);
                tableModel.fireTableDataChanged();
                JOptionPane.showMessageDialog(this, 
                    "Staff member added successfully!", 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                    
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, 
                    "Please enter valid numbers for numeric fields!", 
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, 
                    "Error adding staff member: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
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
            staffList.size() > 0 ? (double) doctorCount / staffList.size() * 100 : 0,
            staffList.size() > 0 ? (double) receptionistCount / staffList.size() * 100 : 0
        );
        
        JOptionPane.showMessageDialog(this, message, "Staff Statistics", 
                                     JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void refreshTable() {
        tableModel.fireTableDataChanged();
        JOptionPane.showMessageDialog(this, "Table refreshed!");
    }
    
    private void saveToFile() {
        try {
            manager.saveToFile();
            JOptionPane.showMessageDialog(this, "Data saved successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error saving file: " + e.getMessage(),
                "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}