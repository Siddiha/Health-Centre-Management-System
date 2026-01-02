/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universityenrollmentsystem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class UniversityTableGUI extends JFrame {
    
    JTable myTable;
    UniversityTableModel tableModel;
    ArrayList<Person> list;
    
    // contructor
    public UniversityTableGUI(ArrayList<Person> list){
        
        //set the title
        this.setTitle("People in University Enrollment System");
        
        // initialise and instantiate the instance variable 
        this.list = list;
        tableModel = new UniversityTableModel(list);
        myTable =  new JTable(tableModel);
        
        // set the size of the frame 
        setBounds(20,20,900,600); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     
        // sorting
        myTable.setAutoCreateRowSorter(true);
      
        // add the table to the panel
        JScrollPane scrollPane = new JScrollPane(myTable); 
        scrollPane.setPreferredSize(new Dimension(380,280)); 

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        // Statistics button
        JButton statisticsButton = new JButton("Statistics");

        JButton resetButton = new JButton("Reset Table");
        
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (tableModel.getRowCount() == list.size()){
                    JOptionPane.showConfirmDialog(UniversitytableGui.this, "table already shows the full list");
                }else{
                    tableModel = new UniversityTableModel(list);
                    myTable.setModel(tableModel);
                    JOptionPane.showMessageDialog(UniversityTableGUI.this, "table reset successfully");
                }
            }
        });




        // add components to frame
        add(scrollPane, BorderLayout.CENTER); 
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
}