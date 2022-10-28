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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
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
public class InsideSales extends javax.swing.JFrame {

    /**
     * Creates new form InsideSales
     */
    public InsideSales() {
        initComponents();
        //this.setLocationRelativeTo(null); //centres form in the windows
    }
    
    
    
    int getVal;
    public void genInvoice(String passQuery){
        try {
            Statement st = con.createStatement();
            ResultSet set = st.executeQuery(passQuery);
            if (set.next()){
                getVal = Integer.parseInt(set.getString(1));
            }
        } catch (Exception e) {
        }
    }
    
    
    Connection con = MyConnection.getConnection();
    PreparedStatement pst;
    
    DefaultTableModel model = new DefaultTableModel ();
    
    public void add(){
        String baw = productname.getText();
        String packaging = (String)packagetype.getSelectedItem();
        int quantity = Integer.parseInt(qty.getValue().toString());
        String batch = batchno.getText();
        String destination = destinationfield.getText();
        String price = pricefield.getText();
        String time = timefield.getText();

        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
        String date = dateformat.format(datefield.getDate());

        String payment = (String)paymenttype.getSelectedItem();
        String description = descriptionfield.getText();


        String saler = "";
        if (salesperson.isSelected()){
            saler = "Approved";
        }
        else{
            saler = "Not Approved";
        }

        String manager = "";
        if (managerapproval.isSelected()){
            manager = "Approved";
        }
        else{
            manager = "Not Approved";
        }

        String security = "";
        if (securityapproval.isSelected()){
            security = "Approved";
        }
        else{
            security = "Not Approved";
        }


        model = (DefaultTableModel)tablepanel.getModel();
        model.addRow(new Object[]{
            baw,
            packaging,
            quantity,
            batch,
            destination,
            price,
            time,
            date,
            payment,
            description,
            saler,
            manager,
            security
        });
    }
    
    public void print(){
        
        int lastid = 0;
        
        try {
            model = (DefaultTableModel)tablepanel.getModel();
            
            genInvoice("SELECT COUNT(id)+1 from sales_table");
            String query = "INSERT INTO `sales_table`(`invoiceno`, `product`, `packagetype`, `quantity`, `batchno`, `destination`, `price`, `time`, `date`, `paymenttype`, `description`, `salesperson`, `manager`, `security`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            String baw1 = (String) tablepanel.getValueAt(0, 0);
            String packaging1 = (String) tablepanel.getValueAt(0, 1);
            int quantity1 = (int) tablepanel.getValueAt(0, 2);
            String batch1 = (String) tablepanel.getValueAt(0, 3);
            String destination1 = (String) tablepanel.getValueAt(0, 4);
            String price1 = (String) tablepanel.getValueAt(0, 5);
            String time1 = (String) tablepanel.getValueAt(0, 6);
            String date1 = (String) tablepanel.getValueAt(0, 7);
            String payment1 = (String) tablepanel.getValueAt(0, 8);
            String description1 = (String) tablepanel.getValueAt(0, 9);
            String saler1 = (String) tablepanel.getValueAt(0, 10);
            String manager1 = (String) tablepanel.getValueAt(0, 11);
            String security1 = (String) tablepanel.getValueAt(0, 12);
            
            SimpleDateFormat invoicedateformat = new SimpleDateFormat("ddMMyyyy");
            String invdate = invoicedateformat.format(datefield.getDate());
            String inv = "SC"+invdate+getVal;
            
            pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, inv);
            pst.setString(2, baw1);
            pst.setString(3, packaging1);
            pst.setInt(4, quantity1);                     //re-verify
            pst.setString(5, batch1);
            pst.setString(6, destination1);
            pst.setString(7, price1);
            pst.setString(8, time1);
            pst.setString(9, date1);
            pst.setString(10, payment1);
            pst.setString(11, description1);
            pst.setString(12, saler1);
            pst.setString(13, manager1);
            pst.setString(14, security1);
            pst.executeUpdate();
            
            ResultSet rs = pst.getGeneratedKeys();
            JOptionPane.showMessageDialog(null, "ORDER PLACED SUCCESSFULLY");
            
            if (rs.next()){
                lastid = rs.getInt(1);
                
                
                HashMap a = new HashMap();
                a.put("invo", lastid);
            
            
                try {
                    JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\Sam\\Documents\\NetBeansProjects\\SacredCommunion\\src\\salesinvoice.jrxml");
                    JasperReport jreport = JasperCompileManager.compileReport(jdesign);

                    JasperPrint jprint = JasperFillManager.fillReport(jreport, a, con);

                    JasperViewer.viewReport(jprint, false);
                } catch (JRException ex) {
                    Logger.getLogger(InsideSales.class.getName()).log(Level.SEVERE, null, ex);
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
        jPanel2 = new javax.swing.JPanel();
        productname = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablepanel = new javax.swing.JTable();
        addbutton = new javax.swing.JButton();
        printbutton = new javax.swing.JButton();
        packagetype = new javax.swing.JComboBox();
        qty = new javax.swing.JSpinner();
        batchno = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        destinationfield = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pricefield = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        timefield = new javax.swing.JTextField();
        datefield = new org.jdesktop.swingx.JXDatePicker();
        paymenttype = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        descriptionfield = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        backbutton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        salesperson = new javax.swing.JCheckBox();
        managerapproval = new javax.swing.JCheckBox();
        securityapproval = new javax.swing.JCheckBox();
        jlogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        productname.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        productname.setText("Bread and Wine");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(productname)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(productname)
                .addContainerGap(17, Short.MAX_VALUE))
        );

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                "Product", "Package Type", "Quantity", "Batch No", "Destination", "Price (N)", "Time", "Date", "Mode of Payment", "Description", "Sales Person Approval", "Manager Approval", "Security Approval"
            }
        ));
        jScrollPane1.setViewportView(tablepanel);

        addbutton.setBackground(new java.awt.Color(0, 255, 0));
        addbutton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addbutton.setForeground(new java.awt.Color(255, 255, 255));
        addbutton.setText("Add");
        addbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbuttonActionPerformed(evt);
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

        packagetype.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "Packs", "Cartons" }));

        qty.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

        batchno.setToolTipText("1,2,3");
        batchno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batchnoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Batch No:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Destination:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Price:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Description:");

        paymenttype.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "Cash", "POS", "Bank Payment" }));

        descriptionfield.setColumns(20);
        descriptionfield.setRows(5);
        jScrollPane2.setViewportView(descriptionfield);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Mode of Payment:");

        backbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/back button1.png"))); // NOI18N
        backbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backbuttonActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Package Type:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Qty:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Time:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Date:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel12.setText("Approval");

        salesperson.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        salesperson.setText("Sales Person");
        salesperson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salespersonActionPerformed(evt);
            }
        });

        managerapproval.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        managerapproval.setText("Manager");

        securityapproval.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        securityapproval.setText("Security");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(salesperson)
                    .addComponent(securityapproval)
                    .addComponent(managerapproval)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel12)
                        .addGap(13, 13, 13)))
                .addGap(88, 88, 88))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(salesperson)
                .addGap(25, 25, 25)
                .addComponent(managerapproval)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(securityapproval)
                .addGap(24, 24, 24))
        );

        jlogout.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jlogout.setText("Log Out!");
        jlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(printbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(packagetype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(34, 34, 34)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(datefield, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(batchno)
                                    .addComponent(destinationfield)
                                    .addComponent(pricefield)
                                    .addComponent(timefield)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(backbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jlogout))
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(paymenttype, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 836, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(backbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(packagetype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(batchno, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(destinationfield, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(pricefield, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(timefield, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(datefield, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(paymenttype, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(102, 102, 102))
                                    .addComponent(jScrollPane2))))))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(printbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void backbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbuttonActionPerformed
        // TODO add your handling code here:
        OptionHome Info = new OptionHome();
        Info.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backbuttonActionPerformed

    private void batchnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batchnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_batchnoActionPerformed

    private void addbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbuttonActionPerformed
        // TODO add your handling code here:
        if (productname.isSelected()){
            
            if(packagetype.getSelectedItem().equals("Select")){
                JOptionPane.showMessageDialog(null, "Select a Valid Option", "Invalid Package Type", JOptionPane.INFORMATION_MESSAGE);
            
            }
            if(qty.getValue().equals(0)){
                JOptionPane.showMessageDialog(null, "Select/Enter a Valid Figure", "Invalid Quantity", JOptionPane.INFORMATION_MESSAGE);
            
            }
            if("".equals(batchno.getText())){
                JOptionPane.showMessageDialog(null, "Batch No is Empty", "Invalid Batch No", JOptionPane.INFORMATION_MESSAGE);
            
            }
            if("".equals(destinationfield.getText())){
                JOptionPane.showMessageDialog(null, "Destination is Empty", "Invalid Destination", JOptionPane.INFORMATION_MESSAGE);
            
            }
            if("".equals(pricefield.getText())){
                JOptionPane.showMessageDialog(null, "Amount is Empty", "Invalid Amount", JOptionPane.INFORMATION_MESSAGE);
            
            }
            if("".equals(timefield.getText())){
                JOptionPane.showMessageDialog(null, "Time is Blank", "Invalid Time", JOptionPane.INFORMATION_MESSAGE);
            
            }
            if(datefield.getDate() == null){
                JOptionPane.showMessageDialog(null, "Date is Blank", "Invalid Date", JOptionPane.INFORMATION_MESSAGE);
            
            }
            if(paymenttype.getSelectedItem().equals("Select")){
                JOptionPane.showMessageDialog(null, "Select a Valid Option", "Invalid Payment Type", JOptionPane.INFORMATION_MESSAGE);
            
            }
            
            
            add();
            
        }
        else{
            JOptionPane.showMessageDialog(null, "Select a Valid Product Type", "Invalid Product Type", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_addbuttonActionPerformed

    private void salespersonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salespersonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_salespersonActionPerformed

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
            java.util.logging.Logger.getLogger(InsideSales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InsideSales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InsideSales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InsideSales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InsideSales().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addbutton;
    private javax.swing.JButton backbutton;
    private javax.swing.JTextField batchno;
    private org.jdesktop.swingx.JXDatePicker datefield;
    private javax.swing.JTextArea descriptionfield;
    private javax.swing.JTextField destinationfield;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jlogout;
    private javax.swing.JCheckBox managerapproval;
    private javax.swing.JComboBox packagetype;
    private javax.swing.JComboBox paymenttype;
    private javax.swing.JTextField pricefield;
    private javax.swing.JButton printbutton;
    private javax.swing.JCheckBox productname;
    private javax.swing.JSpinner qty;
    private javax.swing.JCheckBox salesperson;
    private javax.swing.JCheckBox securityapproval;
    private javax.swing.JTable tablepanel;
    private javax.swing.JTextField timefield;
    // End of variables declaration//GEN-END:variables
}
