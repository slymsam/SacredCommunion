/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sacred;

import java.awt.Frame;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Sam
 */
public class LoginForm extends javax.swing.JFrame {

    /**
     * Creates new form LoginForm
     */
    public LoginForm() {
        initComponents();
        //this.setLocationRelativeTo(null); //centres form in the windows
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
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField_Username = new javax.swing.JTextField();
        jPasswordField_Pass = new javax.swing.JPasswordField();
        jButtonLogin = new javax.swing.JButton();
        jButtonBack = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        accounttype = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));

        jLabel5.setText("jLabel5");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(31, 31, 31))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Username:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password:");

        jTextField_Username.setSelectionColor(new java.awt.Color(255, 255, 255));

        jPasswordField_Pass.setSelectionColor(new java.awt.Color(255, 255, 255));

        jButtonLogin.setBackground(new java.awt.Color(51, 0, 51));
        jButtonLogin.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jButtonLogin.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLogin.setText("Login");
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });

        jButtonBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/back button1.png"))); // NOI18N
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Account Type:");

        accounttype.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "Admin", "Seller" }));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Login Form");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextField_Username, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPasswordField_Pass, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(27, 27, 27)
                                .addComponent(accounttype, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jButtonLogin))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(accounttype, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField_Username, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField_Pass, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionPerformed
        // TODO add your handling code here:
        PreparedStatement ps;
        ResultSet rs;
        String uname = jTextField_Username.getText();
        String pass = String.valueOf(jPasswordField_Pass.getPassword());
        String accountoption = accounttype.getSelectedItem().toString();

        if (uname.equals("")||pass.equals("")||accountoption.equals("Select")){
            JOptionPane.showMessageDialog(null, "One or More Fields Empty", "Login Error",WIDTH);
        }
        else if (uname.equals("admin")&&pass.equals("root")&&accountoption.equalsIgnoreCase("admin")){
            OptionHome1 Info = new OptionHome1();
            Info.setVisible(true);
            this.dispose();
            Info.pack();
            Info.setLocationRelativeTo(null);
        }
        else{
            String query = "SELECT * FROM `login_table` WHERE `username` =? AND `password` =?";

        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            
            ps.setString(1, uname);
            ps.setString(2, pass);
            
            
            rs = ps.executeQuery();

            if (rs.next()){
                String type1 = rs.getString("usertype");
                if(accountoption.equalsIgnoreCase("Admin")&& type1.equalsIgnoreCase("admin")){
                    OptionHome1 Info = new OptionHome1();
                    Info.setVisible(true);
                    this.dispose();
                    Info.pack();
                    Info.setLocationRelativeTo(null);
                    //Info.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    //Info.jLabelUser.setText("Welcome " +uname);
                }
                if(accountoption.equalsIgnoreCase("Seller")&& type1.equalsIgnoreCase("seller")){
                    OptionHome Info = new OptionHome();
                    Info.setVisible(true);
                    this.dispose();
                    Info.pack();
                    Info.setLocationRelativeTo(null);
                    //Info.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    //Info.jLabelUser.setText("Welcome " +uname);
                }
                    
            }
            else{
                    //JOptionPane.showMessageDialog(null, "Incorrect Username or Password", "Login Failed");
                    JOptionPane.showMessageDialog(null, "Incorrect Username or Password", "Login Failed",WIDTH);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_jButtonLoginActionPerformed

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        // TODO add your handling code here:
        Home Info = new Home();
        Info.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonBackActionPerformed

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
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox accounttype;
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField_Pass;
    private javax.swing.JTextField jTextField_Username;
    // End of variables declaration//GEN-END:variables
}
