import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StaffManagementGUI extends JFrame {
    private JTable staffTable;
    private WestminsterHealthCenterManager manager;
    private JLabel statusLabel;
    
    public StaffManagementGUI(ArrayList<StaffMember> staffList, WestminsterHealthCenterManager manager) {
        this.manager = manager;
        initializeGUI();
        refreshTable();
    }
    
    private void initializeGUI() {
        setTitle("Westminster Health Centre - Staff Management System");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        
        // Create main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Create table with column names
        String[] columnNames = {
            "ID", "Name", "Surname", "Role", "Phone", "DOB", "Special Details"
        };
        
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        
        staffTable = new JTable(tableModel);
        staffTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        staffTable.setRowHeight(25);
        staffTable.setFont(new Font("Arial", Font.PLAIN, 12));
        
        // Set column widths
        staffTable.getColumnModel().getColumn(0).setPreferredWidth(80);
        staffTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        staffTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        staffTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        staffTable.getColumnModel().getColumn(4).setPreferredWidth(120);
        staffTable.getColumnModel().getColumn(5).setPreferredWidth(100);
        staffTable.getColumnModel().getColumn(6).setPreferredWidth(300);
        
        JScrollPane scrollPane = new JScrollPane(staffTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Staff List"));
        
        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton addButton = createStyledButton("➕ Add Staff", Color.GREEN);
        JButton removeButton = createStyledButton("❌ Remove", Color.RED);
        JButton searchButton = createStyledButton("🔍 Search", Color.BLUE);
        JButton statsButton = createStyledButton("📊 Statistics", Color.ORANGE);
        JButton refreshButton = createStyledButton("🔄 Refresh", Color.CYAN);
        JButton saveButton = createStyledButton("💾 Save", Color.MAGENTA);
        JButton loadButton = createStyledButton("📂 Load", Color.PINK);
        JButton exitButton = createStyledButton("🚪 Exit", Color.GRAY);
        
        // Add action listeners
        addButton.addActionListener(e -> addStaffMember());
        removeButton.addActionListener(e -> removeSelectedStaff());
        searchButton.addActionListener(e -> searchStaff());
        statsButton.addActionListener(e -> showStatistics());
        refreshButton.addActionListener(e -> refreshTable());
        saveButton.addActionListener(e -> saveToFile());
        loadButton.addActionListener(e -> loadFromFile());
        exitButton.addActionListener(e -> exitApplication());


        // Add buttons to panel
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(statsButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(exitButton);
        
        // Create status bar
        statusLabel = new JLabel(" Ready");
        statusLabel.setBorder(BorderFactory.createEtchedBorder());
        
        // Add components to main panel
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(statusLabel, BorderLayout.NORTH);
        
        // Add main panel to frame
        add(mainPanel);
        
        // Set application icon
        setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
    }
    
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        return button;
    }
    
    private void refreshTable() {
        DefaultTableModel model = (DefaultTableModel) staffTable.getModel();
        model.setRowCount(0);
        
        for (StaffMember member : manager.getStaffMembersList()) {
            Object[] row = new Object[7];
            row[0] = member.getStaffId();
            row[1] = member.getName();
            row[2] = member.getSurName();
            row[3] = member.getRole();
            row[4] = member.getPhoneNo();
            row[5] = member.getDob();
            
            if (member instanceof Doctor) {
                Doctor doc = (Doctor) member;
                row[6] = String.format("Licence: %s | Specialization: %s | Consultations/Week: %d",
                    doc.getLicenceNumber(), doc.getSpecialization(), doc.getNumberOfConsultationPerWeek());
            } else if (member instanceof Receptionist) {
                Receptionist rec = (Receptionist) member;
                row[6] = String.format("Desk: %d | Hours/Week: %d",
                    rec.getDeskNumber(), rec.getHoursPerWeek());
            } else {
                row[6] = "N/A";
            }
            model.addRow(row);
        }
        statusLabel.setText(" Total Staff: " + manager.getStaffMembersList().size());
    }
    

    private void addStaffMember() {
        JDialog dialog = new JDialog(this, "Add New Staff Member", true);
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Doctor", "Receptionist"});
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField surnameField = new JTextField();
        JTextField dobField = new JTextField();
        JTextField phoneField = new JTextField();
        
        JTextField specField = new JTextField();
        JTextField licenceField = new JTextField();
        JSpinner consultSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        
        JTextField deskField = new JTextField();
        JSpinner hoursSpinner = new JSpinner(new SpinnerNumberModel(40, 1, 80, 1));
        
        panel.add(new JLabel("Staff Type:"));
        panel.add(typeCombo);
        panel.add(new JLabel("Staff ID:"));
        panel.add(idField);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Surname:"));
        panel.add(surnameField);
        panel.add(new JLabel("Date of Birth (YYYY-MM-DD):"));
        panel.add(dobField);
        panel.add(new JLabel("Phone Number:"));
        panel.add(phoneField);
        panel.add(new JLabel("Specialization:"));
        panel.add(specField);
        panel.add(new JLabel("Licence Number:"));
        panel.add(licenceField);
        panel.add(new JLabel("Consultations/Week:"));
        panel.add(consultSpinner);
        panel.add(new JLabel("Desk Number:"));
        panel.add(deskField);
        panel.add(new JLabel("Hours/Week:"));
        panel.add(hoursSpinner);
        
        typeCombo.addActionListener(e -> {
            boolean isDoctor = typeCombo.getSelectedItem().equals("Doctor");
            specField.setEnabled(isDoctor);
            licenceField.setEnabled(isDoctor);
            consultSpinner.setEnabled(isDoctor);
            deskField.setEnabled(!isDoctor);
            hoursSpinner.setEnabled(!isDoctor);
        });
        
        typeCombo.setSelectedIndex(0);
        
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        
        saveButton.addActionListener(e -> {
            try {
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                String surname = surnameField.getText().trim();
                String dob = dobField.getText().trim();
                String phone = phoneField.getText().trim();
                
                // Validate inputs
                if (!Validator.isValidStaffId(id)) {
                    JOptionPane.showMessageDialog(dialog, "Invalid Staff ID!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (!Validator.isValidName(name) || !Validator.isValidName(surname)) {
                    JOptionPane.showMessageDialog(dialog, "Invalid name or surname!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (!Validator.isValidDOB(dob)) {
                    JOptionPane.showMessageDialog(dialog, "Invalid Date of Birth!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (!Validator.isValidPhone(phone)) {
                    JOptionPane.showMessageDialog(dialog, "Invalid Phone Number!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                StaffMember newMember;
                String type = (String) typeCombo.getSelectedItem();
                
                if (type.equals("Doctor")) {
                    String specialization = specField.getText().trim();
                    String licence = licenceField.getText().trim();
                    int consultations = (int) consultSpinner.getValue();
                    
                    if (!Validator.isValidSpecialization(specialization)) {
                        JOptionPane.showMessageDialog(dialog, "Invalid Specialization!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    if (!Validator.isValidLicenceNumber(licence)) {
                        JOptionPane.showMessageDialog(dialog, "Invalid Licence Number!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    newMember = new Doctor(name, surname, dob, phone, id, 
                                          licence, specialization, consultations);
                } else {
                    int desk;
                    try {
                        desk = Integer.parseInt(deskField.getText().trim());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(dialog, "Invalid Desk Number!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    int hours = (int) hoursSpinner.getValue();
                    
                    if (!Validator.isValidDeskNumber(desk)) {
                        JOptionPane.showMessageDialog(dialog, "Invalid Desk Number (1-999)!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    newMember = new Receptionist(name, surname, dob, phone, id, 
                                                desk, hours);
                }
                
                manager.addStaffMember(newMember);
                refreshTable();
                JOptionPane.showMessageDialog(dialog, "Staff member added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        cancelButton.addActionListener(e -> dialog.dispose());
        
        dialog.setVisible(true);
    }
    
    private void removeSelectedStaff() {
        int selectedRow = staffTable.getSelectedRow();
        if (selectedRow >= 0) {
            String staffId = (String) staffTable.getValueAt(selectedRow, 0);
            String staffName = (String) staffTable.getValueAt(selectedRow, 1);
            
            int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to remove " + staffName + " (ID: " + staffId + ")?",
                "Confirm Removal", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                // Find and remove the staff member
                StaffMember toRemove = null;
                for (StaffMember member : manager.getStaffMembersList()) {
                    if (member.getStaffId().equals(staffId)) {
                        toRemove = member;
                        break;
                    }
                }
                
                if (toRemove != null) {
                    manager.removeStaffMember(toRemove);
                    refreshTable();
                    JOptionPane.showMessageDialog(this, "Staff member removed successfully!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a staff member to remove.", 
                                         "No Selection", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void searchStaff() {
        String searchId = JOptionPane.showInputDialog(this, "Enter Staff ID to search:");
        if (searchId != null && !searchId.trim().isEmpty()) {
            searchId = searchId.trim();
            
            for (int i = 0; i < staffTable.getRowCount(); i++) {
                if (staffTable.getValueAt(i, 0).equals(searchId)) {
                    staffTable.setRowSelectionInterval(i, i);
                    staffTable.scrollRectToVisible(staffTable.getCellRect(i, 0, true));
                    JOptionPane.showMessageDialog(this, "Staff member found!");
                    return;
                }
            }
            
            JOptionPane.showMessageDialog(this, "Staff member not found!", "Not Found", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void showStatistics() {
        int total = manager.getStaffMembersList().size();
        int doctors = 0;
        int receptionists = 0;
        
        for (StaffMember member : manager.getStaffMembersList()) {
            if (member instanceof Doctor) {
                doctors++;
            } else if (member instanceof Receptionist) {
                receptionists++;
            }
        }
        
        String message = String.format(
            "Total Staff: %d\n" +
            "Doctors: %d (%.1f%%)\n" +
            "Receptionists: %d (%.1f%%)",
            total,
            doctors, total > 0 ? (double) doctors / total * 100 : 0,
            receptionists, total > 0 ? (double) receptionists / total * 100 : 0
        );
        
        JOptionPane.showMessageDialog(this, message, "Statistics", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void saveToFile() {
        try {
            manager.saveToFile();
            JOptionPane.showMessageDialog(this, "Data saved successfully!", "Success", 
                                         JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage(), 
                                         "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadFromFile() {
        try {
            manager.loadFromFile();
            refreshTable();
            JOptionPane.showMessageDialog(this, "Data loaded successfully!", "Success", 
                                         JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading file: " + e.getMessage(), 
                                         "Load Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void exitApplication() {
        int confirm = JOptionPane.showConfirmDialog(this, "Save before exiting?", 
                                                   "Confirm Exit", JOptionPane.YES_NO_CANCEL_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            saveToFile();
            dispose();
        } else if (confirm == JOptionPane.NO_OPTION) {
            dispose();
        }
        // Cancel - do nothing
    }
}