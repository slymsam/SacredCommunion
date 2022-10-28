/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sacred;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sam
 */
public class UserUpdates extends javax.swing.JFrame {

    /**
     * Creates new form RegisterForm
     */
    public UserUpdates() {
        initComponents();
        this.setLocationRelativeTo(null); //centres form in the windows
        showTableData();
    }
    
    DefaultTableModel model = new DefaultTableModel ();
    
    
    public boolean checkInputs(){
        if (
              jTextField_FN.getText().equals("")
            ||jTextField_LN.getText().equals("")
            ||jTextField_UN.getText().equals("")
            ||jPasswordField_PASS.getPassword().equals("")
            ||jComboBox_UT.getSelectedItem().equals("Select")){
            
            return false;
        }
        else{
            return true;
        }
        
    }
    
    public void showData(){
        model = (DefaultTableModel)tablepanel.getModel();
        int index = tablepanel.getSelectedRow();
        jTextField_ID.setText(model.getValueAt(index, 0).toString());
        jTextField_FN.setText(model.getValueAt(index, 1).toString());
        jTextField_LN.setText(model.getValueAt(index, 2).toString());
        jTextField_UN.setText(model.getValueAt(index, 3).toString());
        String uaccounttype = model.getValueAt(index, 4).toString();
        
        if (uaccounttype.equalsIgnoreCase("Admin")){
            jComboBox_UT.setSelectedIndex(1);
        }
        if (uaccounttype.equalsIgnoreCase("Seller")){
            jComboBox_UT.setSelectedIndex(2);
        }
    }
    
//    public void showTableData(){
//        try {
//            Connection con = MyConnection.getConnection();
//            Statement st = con.createStatement();
//            String query = "SELECT * FROM login_table";
//            ResultSet rs = st.executeQuery(query);
//            
//            while(rs.next()){
//                String id = String.valueOf(rs.getInt("id"));
//                String fname = rs.getString("firstname");
//                String lname = rs.getString("lastname");
//                String uname = rs.getString("username");
//                String utype = rs.getString("usertype");
//                
//                String tableData[] = {id, fname, lname, uname, utype};
//                model = (DefaultTableModel)tablepanel.getModel();
//                
//                model.addRow(tableData);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(UserUpdates.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public void showTableData(){
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("ID");
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Username");
        model.addColumn("User Type");
        
        try
        {
            Connection con = MyConnection.getConnection();
            String sql = "SELECT * FROM `login_table`";
            Statement S = con.createStatement();
            ResultSet R = S.executeQuery(sql);
            
            while(R.next())
            {
                model.addRow(new Object[]{
                    R.getString(1),
                    R.getString(2),
                    R.getString(3),
                    R.getString(4),
                    R.getString(6),
                    
                        
                });
            }
            tablepanel.setModel(model);
        }
        catch(Exception e){
        System.out.println(e);
        }
    }
    
    
    public void deleteRecord(){
       int row = tablepanel.getSelectedRow();
       if(row!=-1)
       {
           String id = tablepanel.getValueAt(row, 0).toString();
           String sql = "DELETE FROM login_table WHERE id='"+id+"'";
           
           String resetno = "ALTER TABLE login_table DROP id";
           String consecutivenumbers="ALTER TABLE login_table ADD id INT NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST";
           
           try {
               MyConnection.getConnection().createStatement().execute(sql);
               MyConnection.getConnection().createStatement().execute(resetno);
               MyConnection.getConnection().createStatement().execute(consecutivenumbers);
               
               JOptionPane.showMessageDialog(this, "SUCCESS");
               showTableData();
           } catch (SQLException ex) {
               JOptionPane.showMessageDialog(this, "ERROR");
           }
           
       }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButtonBack = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField_FN = new javax.swing.JTextField();
        jButtonClear = new javax.swing.JButton();
        jButton_add = new javax.swing.JButton();
        jPasswordField_PASS = new javax.swing.JPasswordField();
        jButton_UPDATE = new javax.swing.JButton();
        jButton_DELETE = new javax.swing.JButton();
        jTextField_UN = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField_LN = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBox_UT = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField_REPASS = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        jTextField_ID = new javax.swing.JTextField();
        jlogout = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablepanel = new javax.swing.JTable();
        previous = new javax.swing.JButton();
        first = new javax.swing.JButton();
        next = new javax.swing.JButton();
        last = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));
        jPanel1.setPreferredSize(new java.awt.Dimension(390, 524));

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Registration Form");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/back button1.png"))); // NOI18N
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("First Name:");

        jButtonClear.setBackground(new java.awt.Color(255, 0, 0));
        jButtonClear.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jButtonClear.setText("Clear");
        jButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearActionPerformed(evt);
            }
        });

        jButton_add.setBackground(new java.awt.Color(51, 0, 51));
        jButton_add.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jButton_add.setText("Add");
        jButton_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addActionPerformed(evt);
            }
        });

        jButton_UPDATE.setBackground(new java.awt.Color(51, 0, 51));
        jButton_UPDATE.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jButton_UPDATE.setText("Update");
        jButton_UPDATE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_UPDATEActionPerformed(evt);
            }
        });

        jButton_DELETE.setBackground(new java.awt.Color(51, 0, 51));
        jButton_DELETE.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jButton_DELETE.setText("Delete");
        jButton_DELETE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DELETEActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Password:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Choose Username:");

        jComboBox_UT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "Admin", "Seller" }));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("User Type:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Retype Password:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Last Name:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("ID:");

        jTextField_ID.setEnabled(false);

        jlogout.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jlogout.setText("Log Out!");
        jlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(4, 4, 4)
                            .addComponent(jTextField_UN, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(53, 53, 53)
                            .addComponent(jLabel5)
                            .addGap(4, 4, 4)
                            .addComponent(jPasswordField_PASS, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(5, 5, 5)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel8)
                                .addComponent(jLabel6))
                            .addGap(4, 4, 4)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPasswordField_REPASS, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox_UT, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jButton_add, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_UPDATE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButtonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton_DELETE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(47, 47, 47)
                            .addComponent(jLabel3)
                            .addGap(4, 4, 4)
                            .addComponent(jTextField_LN, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField_FN, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jlogout)))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_FN, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_LN, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel3)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel4))
                    .addComponent(jTextField_UN, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel5))
                    .addComponent(jPasswordField_PASS, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel6))
                    .addComponent(jPasswordField_REPASS, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox_UT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_add, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_UPDATE, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_DELETE, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        tablepanel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "First Name", "Last Name", "Username", "User Type"
            }
        ));
        tablepanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablepanelMouseClicked(evt);
            }
        });
        tablepanel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablepanelKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tablepanel);

        previous.setBackground(new java.awt.Color(51, 0, 51));
        previous.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        previous.setText("Previous");
        previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousActionPerformed(evt);
            }
        });

        first.setBackground(new java.awt.Color(51, 0, 51));
        first.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        first.setText("First");
        first.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstActionPerformed(evt);
            }
        });

        next.setBackground(new java.awt.Color(255, 0, 0));
        next.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        next.setText("Next");
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });

        last.setBackground(new java.awt.Color(51, 0, 51));
        last.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        last.setText("Last");
        last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(first)
                        .addGap(67, 67, 67)
                        .addComponent(previous)
                        .addGap(61, 61, 61)
                        .addComponent(next)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(last))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(first, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(previous, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(last, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 881, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
        // TODO add your handling code here:
        jTextField_ID.setText("");
        jTextField_FN.setText("");
        jTextField_LN.setText("");
        jTextField_UN.setText("");
        jPasswordField_PASS.setText("");
        jPasswordField_REPASS.setText("");
        jComboBox_UT.setSelectedItem("Select");
        
    }//GEN-LAST:event_jButtonClearActionPerformed

    private void jButton_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addActionPerformed
        // TODO add your handling code here:
        String fname = jTextField_FN.getText();
        String lname = jTextField_LN.getText();
        String uname = jTextField_UN.getText();
        String pass = String.valueOf(jPasswordField_PASS.getPassword());
        String re_pass = String.valueOf(jPasswordField_REPASS.getPassword());
        String account = jComboBox_UT.getSelectedItem().toString().toLowerCase();
        //String address = jTextArea_ADDRESS.getText();
        
        
        if (fname.equals("")){
            JOptionPane.showMessageDialog(null, "Enter Firstname");
        }
        else if (lname.equals("")){
            JOptionPane.showMessageDialog(null, "Enter Lastname");
        }
        else if (uname.equals("")){
            JOptionPane.showMessageDialog(null, "Enter Username");
        }
        else if (checkUsername(uname)){
            JOptionPane.showMessageDialog(null, "This Username has been taken");
        }
        else if (pass.equals("")){
            JOptionPane.showMessageDialog(null, "Enter Password");
        }
        else if (!pass.equals(re_pass)){
            JOptionPane.showMessageDialog(null, "Password does not match");
        }
        else if (account.equals("Select")){
            JOptionPane.showMessageDialog(null, "Select an Account Type");
        }
        else{
        PreparedStatement ps;
        String query = "INSERT INTO `login_table`(`firstname`, `lastname`, `username`, `password`, `usertype`) VALUES (?,?,?,?,?)";
        
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            
            
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, uname);
            ps.setString(4, pass);
            ps.setString(5, account);
            
            model = (DefaultTableModel)tablepanel.getModel();
            
            model.addRow(new Object[]{
                fname,
                lname,
                uname,
                account
            });
            
            if (ps.executeUpdate()>0)
            {
                JOptionPane.showMessageDialog(null, "A NEW USER ACCOUNT CREATED");
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserUpdates.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_jButton_addActionPerformed

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        // TODO add your handling code here:
        OptionHome1 Info = new OptionHome1();
        Info.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonBackActionPerformed

    private void jButton_UPDATEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_UPDATEActionPerformed
        // TODO add your handling code here:
        String id = jTextField_ID.getText();
        String fname = jTextField_FN.getText();
        String lname = jTextField_LN.getText();
        String uname = jTextField_UN.getText();
        String pass = String.valueOf(jPasswordField_PASS.getPassword());
        String re_pass = String.valueOf(jPasswordField_REPASS.getPassword());
        String account = jComboBox_UT.getSelectedItem().toString().toLowerCase();
        
        
        
        
        if (checkInputs() & jTextField_ID.getText() != null){
            if (checkUsername(uname)){
                    JOptionPane.showMessageDialog(null, "This Username has been taken");
                }
            
            else{
                PreparedStatement ps;
                String query = "UPDATE login_table SET firstname =?, lastname =?, username =?, password=?, usertype=? WHERE id ="+id;

                try {
                    ps = MyConnection.getConnection().prepareStatement(query);



                    ps.setString(1, fname);
                    ps.setString(2, lname);
                    ps.setString(3, uname);
                    ps.setString(4, pass);
                    ps.setString(5, account);

                    DefaultTableModel model = (DefaultTableModel)tablepanel.getModel();

                    model.setValueAt(fname, tablepanel.getSelectedRow(), 1);
                    model.setValueAt(lname, tablepanel.getSelectedRow(), 2);
                    model.setValueAt(uname, tablepanel.getSelectedRow(), 3);
                    model.setValueAt(account, tablepanel.getSelectedRow(), 4);


                    if (ps.executeUpdate()>0)
                    {
                        JOptionPane.showMessageDialog(null, "USER ACCOUNT UPDATED");
                    }


                } catch (SQLException ex) {
                    Logger.getLogger(UserUpdates.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }//GEN-LAST:event_jButton_UPDATEActionPerformed

    private void jButton_DELETEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DELETEActionPerformed
        // TODO add your handling code here:
        deleteRecord();
        
//        if (jTextField_ID.getText().isEmpty()){
//            JOptionPane.showMessageDialog(this, "Select a Valid Product ID");
//        }
//        else{
//            PreparedStatement ps;
//            ResultSet rs;
//            
//            try{
//            String id = jTextField_ID.getText();
//            String query = "DELETE FROM login_table WHERE id ="+id;
//            ps = MyConnection.getConnection().prepareStatement(query);
//            
//            model = (DefaultTableModel)tablepanel.getModel();
//            int SelectedRowIndex = tablepanel.getSelectedRow();
//            model.removeRow(SelectedRowIndex);
//            
//                if (ps.executeUpdate()>0)
//                {
//                    JOptionPane.showMessageDialog(null, "USER ACCOUNT DELETED");
//                }
//                     
//            }catch (SQLException ex) {
//            Logger.getLogger(UserUpdates.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//        }
    }//GEN-LAST:event_jButton_DELETEActionPerformed

    private void firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstActionPerformed
        // TODO add your handling code here:
        int pos = 0;
        model = (DefaultTableModel)tablepanel.getModel();
        int cSR = tablepanel.getSelectedRow();
        if(cSR > 0){
            tablepanel.getSelectionModel().setSelectionInterval(pos, pos);
            
            jTextField_ID.setText(model.getValueAt(pos, 0).toString());
            jTextField_FN.setText(model.getValueAt(pos, 1).toString());
            jTextField_LN.setText(model.getValueAt(pos, 2).toString());
            jTextField_UN.setText(model.getValueAt(pos, 3).toString());
            
            String uaccounttype = model.getValueAt(pos, 4).toString();
        
            if (uaccounttype.equalsIgnoreCase("Admin")){
                jComboBox_UT.setSelectedIndex(1);
            }
            if (uaccounttype.equalsIgnoreCase("Seller")){
                jComboBox_UT.setSelectedIndex(2);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "You have reached the top");
        }
    }//GEN-LAST:event_firstActionPerformed

    private void previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousActionPerformed
        // TODO add your handling code here:
        model = (DefaultTableModel)tablepanel.getModel();
        int cSR = tablepanel.getSelectedRow();
        if (cSR > 0){
            
            tablepanel.getSelectionModel().setSelectionInterval(cSR-1, cSR-1);
            
            jTextField_ID.setText(model.getValueAt(cSR-1, 0).toString());
            jTextField_FN.setText(model.getValueAt(cSR-1, 1).toString());
            jTextField_LN.setText(model.getValueAt(cSR-1, 2).toString());
            jTextField_UN.setText(model.getValueAt(cSR-1, 3).toString());
            
            String uaccounttype = model.getValueAt(cSR-1, 4).toString();
        
            if (uaccounttype.equalsIgnoreCase("Admin")){
                jComboBox_UT.setSelectedIndex(1);
            }
            if (uaccounttype.equalsIgnoreCase("Seller")){
                jComboBox_UT.setSelectedIndex(2);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "You have reached the top");
        }
    }//GEN-LAST:event_previousActionPerformed

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        // TODO add your handling code here:
        model = (DefaultTableModel)tablepanel.getModel();
        int cSR = tablepanel.getSelectedRow();
        if (cSR < tablepanel.getRowCount()-1){
            tablepanel.getSelectionModel().setSelectionInterval(cSR+1, cSR+1);
            //int index = tablepanel.getSelectedRow();
            jTextField_ID.setText(model.getValueAt(cSR+1, 0).toString());
            jTextField_FN.setText(model.getValueAt(cSR+1, 1).toString());
            jTextField_LN.setText(model.getValueAt(cSR+1, 2).toString());
            jTextField_UN.setText(model.getValueAt(cSR+1, 3).toString());
            
            String uaccounttype = model.getValueAt(cSR+1, 4).toString();
        
            if (uaccounttype.equalsIgnoreCase("Admin")){
                jComboBox_UT.setSelectedIndex(1);
            }
            if (uaccounttype.equalsIgnoreCase("Seller")){
                jComboBox_UT.setSelectedIndex(2);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "You have reached the bottom");
        }
    }//GEN-LAST:event_nextActionPerformed

    private void lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastActionPerformed
        // TODO add your handling code here:
        int pos = tablepanel.getRowCount()-1;
        model = (DefaultTableModel)tablepanel.getModel();
        int cSR = tablepanel.getSelectedRow();
        if(cSR < tablepanel.getRowCount()-1){
            tablepanel.getSelectionModel().setSelectionInterval(pos, pos);
            
            jTextField_ID.setText(model.getValueAt(pos, 0).toString());
            jTextField_FN.setText(model.getValueAt(pos, 1).toString());
            jTextField_LN.setText(model.getValueAt(pos, 2).toString());
            jTextField_UN.setText(model.getValueAt(pos, 3).toString());
            
            String uaccounttype = model.getValueAt(pos, 4).toString();
        
            if (uaccounttype.equalsIgnoreCase("Admin")){
                jComboBox_UT.setSelectedIndex(1);
            }
            if (uaccounttype.equalsIgnoreCase("Seller")){
                jComboBox_UT.setSelectedIndex(2);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "You have reached the bottom");
        }
    }//GEN-LAST:event_lastActionPerformed
    
    private void tablepanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablepanelMouseClicked
        // TODO add your handling code here:
        showData();
        
    }//GEN-LAST:event_tablepanelMouseClicked

    private void tablepanelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablepanelKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN){
            showData();
        }
    }//GEN-LAST:event_tablepanelKeyReleased

    private void jlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlogoutActionPerformed
        // TODO add your handling code here:
        LoginForm Info = new LoginForm();
        Info.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jlogoutActionPerformed

    //function to check if the username already exist
public boolean checkUsername(String username)
{
	PreparedStatement ps;
        ResultSet rs;
        boolean checkUser = false;

        String query = "SELECT * FROM `login_table` WHERE `username` =?";

        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setString(1, username);
            
            rs = ps.executeQuery();

            if (rs.next()){
                checkUser = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserUpdates.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checkUser;
            

}
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserUpdates.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserUpdates.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserUpdates.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserUpdates.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserUpdates().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton first;
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButton_DELETE;
    private javax.swing.JButton jButton_UPDATE;
    private javax.swing.JButton jButton_add;
    private javax.swing.JComboBox jComboBox_UT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField jPasswordField_PASS;
    private javax.swing.JPasswordField jPasswordField_REPASS;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField_FN;
    private javax.swing.JTextField jTextField_ID;
    private javax.swing.JTextField jTextField_LN;
    private javax.swing.JTextField jTextField_UN;
    private javax.swing.JButton jlogout;
    private javax.swing.JButton last;
    private javax.swing.JButton next;
    private javax.swing.JButton previous;
    private javax.swing.JTable tablepanel;
    // End of variables declaration//GEN-END:variables
}
