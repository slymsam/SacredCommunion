/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sacred;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Sam
 */
public class SalesDatabase extends javax.swing.JFrame {

    /**
     * Creates new form InsideSales
     */
    public SalesDatabase() {
        initComponents();
        showTableData();
        //this.setLocationRelativeTo(null); //centres form in the windows
    }
    
    Connection con = MyConnection.getConnection();
    PreparedStatement pst;
    
    DefaultTableModel model = new DefaultTableModel ();
    
    //SelectedRows selectedRows = new SelectedRows();
    
    
//    public void deleteRecord(){
//        PreparedStatement ps;
//        PreparedStatement ps1;
//        PreparedStatement ps2;
//        ResultSet rs;
//            
//        try{
//            model = (DefaultTableModel)tablepanel.getModel();
//            String id = (String) model.getValueAt(tablepanel.getSelectedRow(), 0);
//            String query = "DELETE FROM sales_table WHERE id ="+id;
//            String resetid = "ALTER TABLE sales_table DROP id";
//            String consecutivenumbers="ALTER TABLE sales_table ADD id INT NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST";
//            ps = MyConnection.getConnection().prepareStatement(query);
//            
//            ps1 = MyConnection.getConnection().prepareStatement(resetid);
//            ps1.executeUpdate();
//            
//            ps2 = MyConnection.getConnection().prepareStatement(consecutivenumbers);
//            ps2.executeUpdate();
//            
//            //showTableData();
//            
//            
//            int SelectedRowIndex = tablepanel.getSelectedRow();
//            model.removeRow(SelectedRowIndex);
//            
//                if (ps.executeUpdate()>0)
//                {
//                    JOptionPane.showMessageDialog(null, "ORDER DELETED");
//                }
//                     
//        }catch (SQLException ex) {
//            Logger.getLogger(UserUpdates.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public void deleteRecord(){
       int row = tablepanel.getSelectedRow();
       if(row!=-1){
           String id = tablepanel.getValueAt(row, 0).toString();
           String sql = "DELETE FROM sales_table WHERE id='"+id+"'";
           
           String resetno = "ALTER TABLE sales_table DROP id";
           String consecutivenumbers="ALTER TABLE sales_table ADD id INT NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST";
           
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
    
//    public void showTableData(){
//        try {
//            con = MyConnection.getConnection();
//            Statement st = con.createStatement();
//            String query = "SELECT * FROM sales_table";
//            ResultSet rs = st.executeQuery(query);
//            
//            while(rs.next()){
//                String id = String.valueOf(rs.getInt("id"));
//                String invoicenum = rs.getString("invoiceno");
//                String baw = rs.getString("product");
//                String packaging = rs.getString("packagetype");
//                String quantity = String.valueOf(rs.getInt("quantity"));
//                String batch = rs.getString("batchno");
//                String destination = rs.getString("destination");
//                String price = rs.getString("price");
//                String time = rs.getString("time");
//                String date = rs.getString("date");
//                String payment = rs.getString("paymenttype");
//                
//                String tableData[] = {id, invoicenum, baw, packaging, quantity, batch, destination, price, time, date, payment};
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
        //model = (DefaultTableModel)tablepanel.getModel();
        model.addColumn("ID");
        model.addColumn("Invoice No");
        model.addColumn("Product");
        model.addColumn("Package Type");
        model.addColumn("Quantity");
        model.addColumn("Batch No");
        model.addColumn("Destination");
        model.addColumn("Price (N)");
        model.addColumn("Time");
        model.addColumn("Date");
        model.addColumn("Mode of Payment");
        model.addColumn("Sales Person Approval");
        model.addColumn("Manager Approval");
        model.addColumn("Security Approval");
        
        try
        {
            Connection con = MyConnection.getConnection();
            String sql = "SELECT * FROM `sales_table`";
            Statement S = con.createStatement();
            ResultSet R = S.executeQuery(sql);
            
            while(R.next())
            {
                model.addRow(new Object[]{
                    R.getString(1),
                    R.getString(2),
                    R.getString(3),
                    R.getString(4),
                    R.getString(5),
                    R.getString(6),
                    R.getString(7),
                    R.getString(8),
                    R.getString(9),
                    R.getString(10),
                    R.getString(11),
                    R.getString(13),
                    R.getString(14),
                    R.getString(15)
                        
                });
            }
            tablepanel.setModel(model);
        }
        catch(Exception e){
        System.out.println(e);
        }
    }
    
        
    public void print(){
        
        PreparedStatement ps;
        ResultSet rs;
        
        
        try {
            model = (DefaultTableModel)tablepanel.getModel();
            String id = (String) model.getValueAt(tablepanel.getSelectedRow(), 0);
            String query = "SELECT COUNT(id) from sales_table";
            Statement st = con.createStatement();
            //String query = "SELECT * FROM sales_table";
            rs = st.executeQuery(query);
            
            
            if (rs.next()){
                
                HashMap a = new HashMap();
                a.put("invo", id);
            
            
                try {
                    JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\Sam\\Documents\\NetBeansProjects\\SacredCommunion\\src\\salesinvoice.jrxml");
                    JasperReport jreport = JasperCompileManager.compileReport(jdesign);

                    JasperPrint jprint = JasperFillManager.fillReport(jreport, a, con);

                    JasperViewer.viewReport(jprint);
                } catch (JRException ex) {
                    Logger.getLogger(SalesDatabase.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserUpdates.class.getName()).log(Level.SEVERE, null, ex);
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
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablepanel = new javax.swing.JTable();
        deletebutton = new javax.swing.JButton();
        printbutton = new javax.swing.JButton();
        backbutton = new javax.swing.JButton();
        jlogout = new javax.swing.JButton();
        viewrows = new javax.swing.JButton();

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

        tablepanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tablepanel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Invoice No", "Product", "Package Type", "Quantity", "Batch No", "Destination", "Price (N)", "Time", "Date", "Mode of Payment", "Sales Person Approval", "Manager Approval", "Security Approval"
            }
        ));
        jScrollPane1.setViewportView(tablepanel);

        deletebutton.setBackground(new java.awt.Color(0, 255, 0));
        deletebutton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        deletebutton.setForeground(new java.awt.Color(255, 255, 255));
        deletebutton.setText("Delete");
        deletebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebuttonActionPerformed(evt);
            }
        });

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

        jlogout.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jlogout.setText("Log Out!");
        jlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlogoutActionPerformed(evt);
            }
        });

        viewrows.setBackground(new java.awt.Color(0, 0, 255));
        viewrows.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        viewrows.setForeground(new java.awt.Color(255, 255, 255));
        viewrows.setText("View Selected Rows");
        viewrows.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewrowsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(backbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jlogout))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(deletebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(34, 34, 34)
                            .addComponent(printbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(35, 35, 35)
                            .addComponent(viewrows))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1011, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(backbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deletebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(printbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewrows, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
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
        OptionHome Info = new OptionHome();
        Info.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backbuttonActionPerformed

    private void deletebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebuttonActionPerformed
        // TODO add your handling code here:
        //deleteRecord();
//        DefaultTableModel viewModel = (DefaultTableModel) tablepanel.getModel();
//        DefaultTableModel printModel = new DefaultTableModel(0, viewModel.getColumnCount());
//        JTable toPrint = new JTable(printModel);
//
//        for (int row : tablepanel.getSelectedRows()) {
//            printModel.addRow((Vector)viewModel.getDataVector().get(row));
//            System.out.print(row);
//        }
//        int [] getRows = tablepanel.getSelectedRows();
//        for (int i = 0; i < getRows.length; i++){
//            System.out.println("Selected "+getRows[i]);
//        }
        
        
//        int [] getSelectedRows = tablepanel.getSelectedRows();
//        Object[] newFormRows = new Object[11];
//        for (int i = 0; i < getSelectedRows.length; i++){
//            newFormRows[0] = model.getValueAt(getSelectedRows[i], 0);
//            newFormRows[1] = model.getValueAt(getSelectedRows[i], 1);
//            newFormRows[2] = model.getValueAt(getSelectedRows[i], 2);
//            newFormRows[3] = model.getValueAt(getSelectedRows[i], 3);
//            newFormRows[4] = model.getValueAt(getSelectedRows[i], 4);
//            newFormRows[5] = model.getValueAt(getSelectedRows[i], 5);
//            newFormRows[6] = model.getValueAt(getSelectedRows[i], 6);
//            newFormRows[7] = model.getValueAt(getSelectedRows[i], 7);
//            newFormRows[8] = model.getValueAt(getSelectedRows[i], 8);
//            newFormRows[9] = model.getValueAt(getSelectedRows[i], 9);
//            newFormRows[10] = model.getValueAt(getSelectedRows[i], 10);
//            
//            selectedRows.slmodel.addRow(newFormRows);
//            //System.out.println("Selected "+getRows[i]);
//        }
//        
//        selectedRows.setVisible(true);
        
    }//GEN-LAST:event_deletebuttonActionPerformed

    private void printbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printbuttonActionPerformed
        // TODO add your handling code here:
        print();   
        
    }//GEN-LAST:event_printbuttonActionPerformed

    private void jlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlogoutActionPerformed
        // TODO add your handling code here:
        LoginForm Info = new LoginForm();
        Info.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jlogoutActionPerformed

    private void viewrowsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewrowsActionPerformed
        // TODO add your handling code here:
        model = (DefaultTableModel)tablepanel.getModel();
        int index[] = tablepanel.getSelectedRows();
        Object[] newFormRows = new Object[14];
        
        SelectedRows selectedRows = new SelectedRows();
        DefaultTableModel jmodel = (DefaultTableModel) selectedRows.jtablepanel.getModel();
        for (int i = 0; i < index.length; i++){
            newFormRows[0] = model.getValueAt(index[i], 0);
            newFormRows[1] = model.getValueAt(index[i], 1);
            newFormRows[2] = model.getValueAt(index[i], 2);
            newFormRows[3] = model.getValueAt(index[i], 3);
            newFormRows[4] = model.getValueAt(index[i], 4);
            newFormRows[5] = model.getValueAt(index[i], 5);
            newFormRows[6] = model.getValueAt(index[i], 6);
            newFormRows[7] = model.getValueAt(index[i], 7);
            newFormRows[8] = model.getValueAt(index[i], 8);
            newFormRows[9] = model.getValueAt(index[i], 9);
            newFormRows[10] = model.getValueAt(index[i], 10);
            newFormRows[11] = model.getValueAt(index[i], 11);
            newFormRows[12] = model.getValueAt(index[i], 12);
            newFormRows[13] = model.getValueAt(index[i], 13);
            
            jmodel.addRow(newFormRows);
        }
        
        selectedRows.setVisible(true);
        
        
//        int [] getSelectedRows = tablepanel.getSelectedRows();
//        
//        Object[] newFormRows = new Object[11];
//        
//        for (int i = 0; i < getSelectedRows.length; i++){
//            newFormRows[0] = model.getValueAt(getSelectedRows[i], 0);
//            newFormRows[1] = model.getValueAt(getSelectedRows[i], 1);
//            newFormRows[2] = model.getValueAt(getSelectedRows[i], 2);
//            newFormRows[3] = model.getValueAt(getSelectedRows[i], 3);
//            newFormRows[4] = model.getValueAt(getSelectedRows[i], 4);
//            newFormRows[5] = model.getValueAt(getSelectedRows[i], 5);
//            newFormRows[6] = model.getValueAt(getSelectedRows[i], 6);
//            newFormRows[7] = model.getValueAt(getSelectedRows[i], 7);
//            newFormRows[8] = model.getValueAt(getSelectedRows[i], 8);
//            newFormRows[9] = model.getValueAt(getSelectedRows[i], 9);
//            newFormRows[10] = model.getValueAt(getSelectedRows[i], 10);
//            
//            selectedRows.jmodel.addRow(newFormRows);
//            //System.out.println("Selected " +getSelectedRows[i]);
//        }
//        
//        selectedRows.setVisible(true);
    }//GEN-LAST:event_viewrowsActionPerformed

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
            java.util.logging.Logger.getLogger(SalesDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SalesDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SalesDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SalesDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SalesDatabase().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backbutton;
    private javax.swing.JButton deletebutton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jlogout;
    private javax.swing.JButton printbutton;
    private javax.swing.JTable tablepanel;
    private javax.swing.JButton viewrows;
    // End of variables declaration//GEN-END:variables
}
