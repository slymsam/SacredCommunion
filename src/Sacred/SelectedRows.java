/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sacred;

import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Sam
 */
public class SelectedRows extends javax.swing.JFrame {
    

    /**
     * Creates new form InsideSales
     */
    public SelectedRows() {
        initComponents();

        //showTableData();
        //this.setLocationRelativeTo(null); //centres form in the windows
    }
    
    
    
    
    Connection con = MyConnection.getConnection();
    PreparedStatement pst;
    DefaultTableModel jmodel = new DefaultTableModel ();
    
    
    public void print2(){
        MessageFormat header = new MessageFormat("SACRED COMMUNION");
        MessageFormat footer = new MessageFormat("MONTHLY REPORT");
        try {
            jtablepanel.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (PrinterException e) {
            JOptionPane.showMessageDialog(null, "Cannot Print!"+e.getMessage());
        }
    }
    
    public void print3(){
               
        try {
            jmodel = (DefaultTableModel)jtablepanel.getModel();
            JRTableModelDataSource datasource = new JRTableModelDataSource(jmodel);
            String reportSource = "./monthlyreport.jrxml";
            //String reportSource = "./monthreport.jrxml";
            //JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\Sam\\Documents\\NetBeansProjects\\SacredCommunion\\src\\salesinvoice.jrxml");
            JasperReport jreport = JasperCompileManager.compileReport("C:\\Users\\Sam\\Documents\\NetBeansProjects\\SacredCommunion\\src\\monthlyreport.jrxml");
            
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("header", "SACRED COMMUNION");
            params.put("footer", "MONTHLY REPORT");
            
            
            JasperPrint jprint = JasperFillManager.fillReport(jreport, params, datasource);
            
            JasperViewer.viewReport(jprint, false);
            

        } catch (Exception ex) {
            ex.printStackTrace();
            //Logger.getLogger(UserUpdates.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void showTableData(){
//        jmodel = (DefaultTableModel) jtablepanel.getModel();
//        Object[] tableHeaders = new Object []{"ID", "Invoice No", "Product", "Package Type", "Quantity", "Batch No", "Destination", "Price (N)", "Time", "Date", "Mode of Payment"};
//        jmodel.setColumnIdentifiers(tableHeaders);
    }
    
//    public void deleteRecord(){
//       //int row = jmodel.getRowCount();
//       for(int i = 0; i <jtablepanel.getRowCount(); i++){
//           if (jmodel.getValueAt(i, 0).toString().equals("true")){
//            String id = jtablepanel.getValueAt(i, 0).toString();
//            String sql = "DELETE FROM sales_table WHERE id='"+id+"'";
//            try {
//                MyConnection.getConnection().createStatement().execute(sql);
// //               MyConnection.getConnection().createStatement().execute(resetno);
// //               MyConnection.getConnection().createStatement().execute(consecutivenumbers); 
//                JOptionPane.showMessageDialog(this, "SUCCESS");
//                showTableData();
//            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(this, "ERROR");
//            } 
//           }
////           String id = jtablepanel.getValueAt(row, 0).toString();
////           String sql = "DELETE FROM sales_table WHERE id='"+id+"'";
////           try {
////               MyConnection.getConnection().createStatement().execute(sql);
//////               MyConnection.getConnection().createStatement().execute(resetno);
//////               MyConnection.getConnection().createStatement().execute(consecutivenumbers); 
////               JOptionPane.showMessageDialog(this, "SUCCESS");
////               showTableData();
////           } catch (SQLException ex) {
////               JOptionPane.showMessageDialog(this, "ERROR");
////           }
//       }
//       //for(int i = row - 1; i >= 0; i--){ there's need working upon
//       //for(int i = 0; i < 10; i++){ mine deleted but didnt exit
////       for(int i = 0; i < 10; i++){
////           String id = jtablepanel.getValueAt(row, 0).toString();
////           String sql = "DELETE FROM sales_table WHERE id='"+id+"'";
////           
////           String resetno = "ALTER TABLE sales_table DROP id";
////           String consecutivenumbers="ALTER TABLE sales_table ADD id INT NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST";
////           
////           try {
////               MyConnection.getConnection().createStatement().execute(sql);
////               MyConnection.getConnection().createStatement().execute(resetno);
////               MyConnection.getConnection().createStatement().execute(consecutivenumbers);
//////               if (pst.execute()){
//////                   MyConnection.getConnection().createStatement().execute(resetno);
//////                   MyConnection.getConnection().createStatement().execute(consecutivenumbers);
//////               } 
////               JOptionPane.showMessageDialog(this, "SUCCESS");
////               showTableData();
////           } catch (SQLException ex) {
////               JOptionPane.showMessageDialog(this, "ERROR");
////           }
////           
////       }
//    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        printbutton = new javax.swing.JButton();
        backbutton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtablepanel = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jPanel3.setBackground(new java.awt.Color(255, 0, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("The Sacred Communion Sales System");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(334, 334, 334)
                .addComponent(jLabel1)
                .addContainerGap(166, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        printbutton.setBackground(new java.awt.Color(0, 0, 255));
        printbutton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        printbutton.setForeground(new java.awt.Color(255, 255, 255));
        printbutton.setText("Print");
        printbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printbuttonActionPerformed(evt);
            }
        });

        backbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/back button1.png"))); // NOI18N
        backbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backbuttonActionPerformed(evt);
            }
        });

        jtablepanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jtablepanel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Invoice No", "Product", "Package Type", "Quantity", "Batch No", "Destination", "Price (N)", "Time", "Date", "Mode of Payment", "Sales Person Approval", "Manager Approval", "Security Approval"
            }
        ));
        jScrollPane1.setViewportView(jtablepanel);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1011, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(883, 883, 883)
                        .addComponent(printbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(backbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(printbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
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

    private void backbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbuttonActionPerformed
        // TODO add your handling code here:
        SalesDatabase Info = new SalesDatabase();
        Info.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backbuttonActionPerformed

    private void printbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printbuttonActionPerformed
        // TODO add your handling code here:
        print3();
    }//GEN-LAST:event_printbuttonActionPerformed

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
            java.util.logging.Logger.getLogger(SelectedRows.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SelectedRows.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SelectedRows.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SelectedRows.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SelectedRows().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backbutton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jtablepanel;
    private javax.swing.JButton printbutton;
    // End of variables declaration//GEN-END:variables
}
