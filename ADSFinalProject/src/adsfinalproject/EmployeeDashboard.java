/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package adsfinalproject;

import java.awt.CardLayout;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.Connection;

/**
 *
 * @author kleir
 */
public class EmployeeDashboard extends javax.swing.JFrame {
    ImageIcon darkd, darku, darko, darkp, darkpa, active, onduty, inactive ;
    ImageIcon dash, user, ord, pr, pay;
    String status = "";

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(EmployeeDashboard.class.getName());

    /**
     * Creates new form EmployeeDashboard
     */
    public EmployeeDashboard() {
        initComponents();
        loadUsers();
        loadOrders();
        loadDashboardTable();
        loadProductsTable("", "All");
  
        setSize(1300, 800);
        setLocationRelativeTo(null);
        
        //--//
        darkd = new ImageIcon(getClass().getResource("/design/darkd.png"));
        darku = new ImageIcon(getClass().getResource("/design/darku.png"));
        darko = new ImageIcon(getClass().getResource("/design/ABORDER.png"));
        darkp = new ImageIcon(getClass().getResource("/design/darkp.png"));
        darkpa = new ImageIcon(getClass().getResource("/design/darkpa.png"));
        dash = new ImageIcon(getClass().getResource("/design/Dashboard.png"));
        user = new ImageIcon(getClass().getResource("/design/users.png"));
        ord = new ImageIcon(getClass().getResource("/design/AAorder.png"));
        pr = new ImageIcon(getClass().getResource("/design/products.png"));
        pay = new ImageIcon(getClass().getResource("/design/paymentt.png"));
        active = new ImageIcon(getClass().getResource("/design/active.png"));
        onduty = new ImageIcon(getClass().getResource("/design/onduty.png"));
        inactive = new ImageIcon(getClass().getResource("/design/inactive.png"));
        
        //--//
        jPanel4.setLayout(new CardLayout());
        
        jPanel4.add(pnlDashboard, "dashboard");
        jPanel4.add(pnlCustomer, "users");
        jPanel4.add(pnlOrder, "orders");
        jPanel4.add(pnlProduct, "product");
        jPanel4.add(pnlPayment, "payment");
        
        CardLayout cl = (CardLayout)(jPanel4.getLayout());
        cl.show(jPanel4, "dashboard");
    }
     private void resetStatus(){
            btnActive.setIcon(null);
            btnInactive.setIcon(null);
            btnOnDuty.setIcon(null);
            
            btnActive.setText("-");
            btnInactive.setText("-");
            btnOnDuty.setText("-");
            }
        
 private void setActive(String page){
        btnDashboard.setIcon(dash);
        btnUsers.setIcon(user);
        btnOrders.setIcon(ord);
        btnProducts.setIcon(pr);
        btnPayment.setIcon(pay);
        
       
        
        if(page.equals("dashboard")){
            btnDashboard.setIcon(darkd);
        }else if (page.equals("users")){
            btnUsers.setIcon(darku);
        }else if (page.equals("orders")){
            btnOrders.setIcon(darko);
        }else if (page.equals("product")){
            btnProducts.setIcon(darkp);
        }else if (page.equals("payment")){
            btnPayment.setIcon(darkpa);
        }
            CardLayout cl = (CardLayout)(jPanel4.getLayout());
            cl.show(jPanel4, "dashboard");
     }

     public void loadUsers() {
    try {

        DefaultTableModel model = (DefaultTableModel) tblUsers.getModel();
        model.setRowCount(0);

        Connection conn = DBConnection.getConnection();

        String sql = "SELECT * FROM users WHERE role = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, "customer");

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            model.addRow(new Object[]{
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("contact_no"),
                rs.getString("status")
            });
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Load Error: " + e.getMessage());
    }

}
        public void clearFields() {
        txtCustomerName.setText("");
        txtCustomerEmail.setText("");
        txtCustomerNo.setText("");
    
    }

        public void loadOrders() {
    try {
        Connection con = DBConnection.getConnection();

        String sql = "SELECT * FROM orders";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) tblOrders.getModel();
        model.setRowCount(0);

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("order_id"),
                rs.getString("customer_name"),
                rs.getDate("order_date"),
                rs.getDouble("total_amount"),
                rs.getString("order_type"),
                rs.getString("status")
            });
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
        }
        
    public void searchOrders(String keyword) {
    try {
        Connection con = DBConnection.getConnection();

        String sql = "SELECT * FROM orders WHERE customer_name LIKE ? OR order_id LIKE ?";
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, "%" + keyword + "%");
        pst.setString(2, "%" + keyword + "%");

        ResultSet rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) tblOrders.getModel();
        model.setRowCount(0);

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("order_id"),
                rs.getString("customer_name"),
                rs.getDate("order_date"),
                rs.getDouble("total_amount"),
                rs.getString("order_type"),
                rs.getString("status")
            });
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
    }
    
    public void filterByStatus(String status) {
    try {
        Connection con = DBConnection.getConnection();

        String sql;

        if (status.equals("Default")) {
            sql = "SELECT * FROM orders";
        } else {
            sql = "SELECT * FROM orders WHERE status = ?";
        }

        PreparedStatement pst = con.prepareStatement(sql);

        if (!status.equals("Default")) {
            pst.setString(1, status);
        }

        ResultSet rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) tblOrders.getModel();
        model.setRowCount(0);

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("order_id"),
                rs.getString("customer_name"),
                rs.getDate("order_date"),
                rs.getDouble("total_amount"),
                rs.getString("order_type"),
                rs.getString("status")
            });
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
    }
   public void loadDashboardTable() {
    String sql = "SELECT order_id, customer_name, total_amount, payment_type, status, order_date FROM orders";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement pst = con.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {

        DefaultTableModel model = (DefaultTableModel) tblStaffDashboard.getModel();
        model.setRowCount(0);

        while (rs.next()) {

            model.addRow(new Object[]{
                rs.getInt("order_id"),
                rs.getString("customer_name"),
                rs.getDouble("total_amount"),
                rs.getString("payment_type"),
                rs.getString("status"),
                rs.getString("order_date")
            });
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
   }
    private void loadProductsTable(String search, String statusFilter) {

    DefaultTableModel model = (DefaultTableModel) tblProducts.getModel();
    model.setRowCount(0);

    String sql;
    boolean hasFilter = !statusFilter.equalsIgnoreCase("All");

    if (hasFilter) {
        sql = "SELECT * FROM products WHERE (name LIKE ? OR category LIKE ?) AND status = ? ORDER BY product_id DESC";
    } else {
        sql = "SELECT * FROM products WHERE (name LIKE ? OR category LIKE ?) ORDER BY product_id DESC";
    }

    try (Connection con = DBConnection.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setString(1, "%" + search + "%");
        pst.setString(2, "%" + search + "%");

        if (hasFilter) {
            pst.setString(3, statusFilter);
        }

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("product_id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getString("category"),
                rs.getInt("stock"),
                rs.getDouble("price"),
                rs.getString("supplier_id"),
                rs.getString("status"),
                rs.getDate("date_added"),
                rs.getDate("expiration_date")
            });
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
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
        btnUsers = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnPayment = new javax.swing.JButton();
        btnProducts = new javax.swing.JButton();
        btnOrders = new javax.swing.JButton();
        btnDashboard = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        pnlCustomer = new javax.swing.JPanel();
        pnlAddCustoemr = new javax.swing.JPanel();
        txtCustomerNo = new javax.swing.JTextField();
        txtCustomerEmail = new javax.swing.JTextField();
        txtCustomerName = new javax.swing.JTextField();
        btnAddCancel1 = new javax.swing.JButton();
        btnSaveCustomer = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblUsers = new javax.swing.JTable();
        btnDel = new javax.swing.JButton();
        btnAddCustomer = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        pnlPayment = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSummary = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        pnlDashboard = new javax.swing.JPanel();
        btnInactive = new javax.swing.JButton();
        btnOnDuty = new javax.swing.JButton();
        btnActive = new javax.swing.JButton();
        lblCompleted = new javax.swing.JLabel();
        lblReady = new javax.swing.JLabel();
        lblPrep = new javax.swing.JLabel();
        lblOrdersToday = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStaffDashboard = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        pnlOrder = new javax.swing.JPanel();
        cmbStatus = new javax.swing.JComboBox<>();
        txtSearchOrders = new javax.swing.JTextField();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblOrders = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        pnlCreateOrder = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSummary1 = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblSummary2 = new javax.swing.JTable();
        btnPlaceOrder = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        btnAddOrder = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        pnlProduct = new javax.swing.JPanel();
        cmbStatusProduct = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();
        txtSearchProduct = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1300, 847));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(285, 800));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnUsers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/users.png"))); // NOI18N
        btnUsers.setBorder(null);
        btnUsers.setBorderPainted(false);
        btnUsers.setContentAreaFilled(false);
        btnUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsersActionPerformed(evt);
            }
        });
        jPanel1.add(btnUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 360, 70));

        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/logout.png"))); // NOI18N
        btnLogout.setBorder(null);
        btnLogout.setBorderPainted(false);
        btnLogout.setContentAreaFilled(false);
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 730, 300, 50));

        btnPayment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/paymentt.png"))); // NOI18N
        btnPayment.setBorder(null);
        btnPayment.setBorderPainted(false);
        btnPayment.setContentAreaFilled(false);
        btnPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaymentActionPerformed(evt);
            }
        });
        jPanel1.add(btnPayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 490, 300, 80));

        btnProducts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/products.png"))); // NOI18N
        btnProducts.setBorder(null);
        btnProducts.setBorderPainted(false);
        btnProducts.setContentAreaFilled(false);
        btnProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductsActionPerformed(evt);
            }
        });
        jPanel1.add(btnProducts, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, 300, 70));

        btnOrders.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/AAorder.png"))); // NOI18N
        btnOrders.setBorder(null);
        btnOrders.setBorderPainted(false);
        btnOrders.setContentAreaFilled(false);
        btnOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdersActionPerformed(evt);
            }
        });
        jPanel1.add(btnOrders, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 320, 70));

        btnDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Dashboard.png"))); // NOI18N
        btnDashboard.setBorder(null);
        btnDashboard.setBorderPainted(false);
        btnDashboard.setContentAreaFilled(false);
        btnDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDashboardActionPerformed(evt);
            }
        });
        jPanel1.add(btnDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 300, 70));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/admindasd.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 780));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -3, 250, 850));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.CardLayout());

        pnlCustomer.setBackground(new java.awt.Color(255, 255, 255));
        pnlCustomer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlAddCustoemr.setBackground(new java.awt.Color(255, 255, 255));
        pnlAddCustoemr.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCustomerNo.setBackground(new java.awt.Color(255, 255, 255));
        txtCustomerNo.setForeground(new java.awt.Color(0, 0, 0));
        txtCustomerNo.setBorder(null);
        pnlAddCustoemr.add(txtCustomerNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 390, 350, 30));

        txtCustomerEmail.setBackground(new java.awt.Color(255, 255, 255));
        txtCustomerEmail.setForeground(new java.awt.Color(0, 0, 0));
        txtCustomerEmail.setBorder(null);
        pnlAddCustoemr.add(txtCustomerEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 370, 30));

        txtCustomerName.setBackground(new java.awt.Color(255, 255, 255));
        txtCustomerName.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        txtCustomerName.setForeground(new java.awt.Color(0, 0, 0));
        txtCustomerName.setBorder(null);
        txtCustomerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustomerNameActionPerformed(evt);
            }
        });
        pnlAddCustoemr.add(txtCustomerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 360, 30));

        btnAddCancel1.setText("-");
        btnAddCancel1.setBorder(null);
        btnAddCancel1.setBorderPainted(false);
        btnAddCancel1.setContentAreaFilled(false);
        btnAddCancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCancel1ActionPerformed(evt);
            }
        });
        pnlAddCustoemr.add(btnAddCancel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 510, 80, 40));

        btnSaveCustomer.setText("-");
        btnSaveCustomer.setBorder(null);
        btnSaveCustomer.setBorderPainted(false);
        btnSaveCustomer.setContentAreaFilled(false);
        btnSaveCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveCustomerActionPerformed(evt);
            }
        });
        pnlAddCustoemr.add(btnSaveCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 510, 80, 40));

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/AddCustomer.png"))); // NOI18N
        pnlAddCustoemr.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pnlCustomer.add(pnlAddCustoemr, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, -1, -1));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane7.setBackground(new java.awt.Color(255, 255, 255));

        tblUsers.setBackground(new java.awt.Color(255, 255, 255));
        tblUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Email", "Contact No.", "Status"
            }
        ));
        jScrollPane7.setViewportView(tblUsers);

        jPanel2.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(312, 137, 930, 530));

        btnDel.setText("-");
        btnDel.setBorder(null);
        btnDel.setBorderPainted(false);
        btnDel.setContentAreaFilled(false);
        jPanel2.add(btnDel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 740, 90, 30));

        btnAddCustomer.setText("-");
        btnAddCustomer.setBorder(null);
        btnAddCustomer.setBorderPainted(false);
        btnAddCustomer.setContentAreaFilled(false);
        btnAddCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCustomerActionPerformed(evt);
            }
        });
        jPanel2.add(btnAddCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 743, 140, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Customers.png"))); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pnlCustomer.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 800));

        jPanel4.add(pnlCustomer, "users");

        pnlPayment.setBackground(new java.awt.Color(255, 255, 255));
        pnlPayment.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("-");
        pnlPayment.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 660, 120, -1));

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("-");
        pnlPayment.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 490, 120, -1));

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("-");
        pnlPayment.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 110, 120, -1));

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("-");
        pnlPayment.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, 120, -1));

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("-");
        pnlPayment.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, 120, -1));

        jTextField8.setBackground(new java.awt.Color(255, 255, 255));
        jTextField8.setBorder(null);
        pnlPayment.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 660, 170, 30));

        jTextField7.setBackground(new java.awt.Color(255, 255, 255));
        jTextField7.setBorder(null);
        pnlPayment.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 630, 170, 20));

        jTextField6.setBackground(new java.awt.Color(255, 255, 255));
        jTextField6.setBorder(null);
        pnlPayment.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 660, 160, 30));

        jTextField5.setBackground(new java.awt.Color(255, 255, 255));
        jTextField5.setBorder(null);
        pnlPayment.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 630, 170, 20));

        jTextField4.setBackground(new java.awt.Color(255, 255, 255));
        jTextField4.setBorder(null);
        pnlPayment.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, 380, 20));

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));

        tblSummary.setBackground(new java.awt.Color(255, 255, 255));
        tblSummary.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Name", "Quantity", "Price", "Subtotal"
            }
        ));
        jScrollPane3.setViewportView(tblSummary);

        pnlPayment.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 240, 880, 220));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/PaymentWalkin.png"))); // NOI18N
        pnlPayment.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel4.add(pnlPayment, "payment");

        pnlDashboard.setBackground(new java.awt.Color(255, 255, 255));
        pnlDashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnInactive.setText("-");
        btnInactive.setBorder(null);
        btnInactive.setBorderPainted(false);
        btnInactive.setContentAreaFilled(false);
        btnInactive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInactiveActionPerformed(evt);
            }
        });
        pnlDashboard.add(btnInactive, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 80, 260, 60));

        btnOnDuty.setText("-");
        btnOnDuty.setBorder(null);
        btnOnDuty.setBorderPainted(false);
        btnOnDuty.setContentAreaFilled(false);
        btnOnDuty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOnDutyActionPerformed(evt);
            }
        });
        pnlDashboard.add(btnOnDuty, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 80, 290, 60));

        btnActive.setText("-");
        btnActive.setBorder(null);
        btnActive.setBorderPainted(false);
        btnActive.setContentAreaFilled(false);
        btnActive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActiveActionPerformed(evt);
            }
        });
        pnlDashboard.add(btnActive, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, 270, 50));

        lblCompleted.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblCompleted.setForeground(new java.awt.Color(255, 255, 255));
        lblCompleted.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCompleted.setText("-");
        pnlDashboard.add(lblCompleted, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 190, 60, 60));

        lblReady.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblReady.setForeground(new java.awt.Color(255, 255, 255));
        lblReady.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReady.setText("-");
        pnlDashboard.add(lblReady, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 190, 60, 60));

        lblPrep.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblPrep.setForeground(new java.awt.Color(255, 255, 255));
        lblPrep.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrep.setText("-");
        pnlDashboard.add(lblPrep, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 190, 60, 60));

        lblOrdersToday.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblOrdersToday.setForeground(new java.awt.Color(255, 255, 255));
        lblOrdersToday.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOrdersToday.setText("-");
        pnlDashboard.add(lblOrdersToday, new org.netbeans.lib.awtextra.AbsoluteConstraints(348, 190, 60, 60));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);

        tblStaffDashboard.setBackground(new java.awt.Color(255, 255, 255));
        tblStaffDashboard.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Order ID", "Name", "Total", "Payment Type", "Status", "Date"
            }
        ));
        jScrollPane1.setViewportView(tblStaffDashboard);

        pnlDashboard.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(312, 397, 930, 350));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Employee.png"))); // NOI18N
        pnlDashboard.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel4.add(pnlDashboard, "dashboard");

        pnlOrder.setBackground(new java.awt.Color(255, 255, 255));
        pnlOrder.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmbStatus.setBackground(new java.awt.Color(255, 255, 255));
        cmbStatus.setForeground(new java.awt.Color(51, 51, 51));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Status", "Pending", "Preparing", "Paid" }));
        cmbStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStatusActionPerformed(evt);
            }
        });
        pnlOrder.add(cmbStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 112, 190, 30));

        txtSearchOrders.setBackground(new java.awt.Color(255, 255, 255));
        txtSearchOrders.setBorder(null);
        txtSearchOrders.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchOrdersKeyReleased(evt);
            }
        });
        pnlOrder.add(txtSearchOrders, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 116, 380, 20));

        jScrollPane12.setBackground(new java.awt.Color(255, 255, 255));

        tblOrders.setBackground(new java.awt.Color(255, 255, 255));
        tblOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Date", "Total", "Order Type", "Status"
            }
        ));
        jScrollPane12.setViewportView(tblOrders);

        pnlOrder.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(312, 167, 930, 500));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/orders.png"))); // NOI18N
        pnlOrder.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 0, -1, -1));

        pnlCreateOrder.setBackground(new java.awt.Color(255, 255, 255));
        pnlCreateOrder.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));

        tblSummary1.setBackground(new java.awt.Color(255, 255, 255));
        tblSummary1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name", "Quantity", "Price"
            }
        ));
        jScrollPane4.setViewportView(tblSummary1);

        pnlCreateOrder.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 320, 270, 390));

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("-");
        pnlCreateOrder.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 680, 120, -1));

        jTextField9.setBackground(new java.awt.Color(255, 255, 255));
        jTextField9.setBorder(null);
        pnlCreateOrder.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, 380, 20));

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("-");
        pnlCreateOrder.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, 120, -1));

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("-");
        pnlCreateOrder.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 110, 120, -1));

        btnBack.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnBack.setText("X");
        btnBack.setBorder(null);
        btnBack.setBorderPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        pnlCreateOrder.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 10, 40, 30));

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));

        tblSummary2.setBackground(new java.awt.Color(255, 255, 255));
        tblSummary2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Name", "Quantity", "Price", "Subtotal"
            }
        ));
        jScrollPane5.setViewportView(tblSummary2);

        pnlCreateOrder.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 290, 620, 340));

        btnPlaceOrder.setText("-");
        btnPlaceOrder.setBorder(null);
        btnPlaceOrder.setBorderPainted(false);
        btnPlaceOrder.setContentAreaFilled(false);
        pnlCreateOrder.add(btnPlaceOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(1155, 743, 130, 30));

        jComboBox2.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox2.setForeground(new java.awt.Color(102, 102, 102));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Category:", "Rice Meals", "Breakfast Meals", "Snacks", "Noodles", "Drinks" }));
        pnlCreateOrder.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 290, 190, -1));

        btnAddOrder.setText("Add Order");
        pnlCreateOrder.add(btnAddOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 740, 100, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Create Orders.png"))); // NOI18N
        pnlCreateOrder.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, -1, -1));

        pnlOrder.add(pnlCreateOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel4.add(pnlOrder, "orders");

        pnlProduct.setBackground(new java.awt.Color(255, 255, 255));
        pnlProduct.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmbStatusProduct.setBackground(new java.awt.Color(255, 255, 255));
        cmbStatusProduct.setForeground(new java.awt.Color(51, 51, 51));
        cmbStatusProduct.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Status:", "Available", "Out of Stock", "Discontinued" }));
        cmbStatusProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStatusProductActionPerformed(evt);
            }
        });
        pnlProduct.add(cmbStatusProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 110, 210, 30));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);

        tblProducts.setBackground(new java.awt.Color(255, 255, 255));
        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Product ID", "Name", "Description", "Category", "Stock", "Price", "Supplier", "Status", "Date Add", "Expiration"
            }
        ));
        jScrollPane2.setViewportView(tblProducts);

        pnlProduct.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 930, 550));

        txtSearchProduct.setBackground(new java.awt.Color(255, 255, 255));
        txtSearchProduct.setBorder(null);
        txtSearchProduct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchProductKeyReleased(evt);
            }
        });
        pnlProduct.add(txtSearchProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 116, 380, 20));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Producttt.png"))); // NOI18N
        pnlProduct.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel4.add(pnlProduct, "product");

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 800));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsersActionPerformed
        setActive("users");
        jPanel1.setVisible(true);
        pnlAddCustoemr.setVisible(false);
        CardLayout cl = (CardLayout)(jPanel4.getLayout());
        cl.show(jPanel4, "users");
    }//GEN-LAST:event_btnUsersActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        startUp log = new startUp();
        log.setVisible(true);
        log.pack();
        log.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaymentActionPerformed
        setActive("payment");
        jPanel1.setVisible(true);
        CardLayout cl = (CardLayout)(jPanel4.getLayout());
        cl.show(jPanel4, "payment");
    }//GEN-LAST:event_btnPaymentActionPerformed

    private void btnProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductsActionPerformed
        setActive("product");
        jPanel1.setVisible(true);
        CardLayout cl = (CardLayout)(jPanel4.getLayout());
        cl.show(jPanel4, "product");

    }//GEN-LAST:event_btnProductsActionPerformed

    private void btnOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdersActionPerformed
        setActive("order");
        jPanel1.setVisible(true);
        pnlCreateOrder.setVisible(false);
        CardLayout cl = (CardLayout)(jPanel4.getLayout());
        cl.show(jPanel4, "orders");
    }//GEN-LAST:event_btnOrdersActionPerformed

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
        setActive("dashboard");
        jPanel1.setVisible(true);
        CardLayout cl = (CardLayout)(jPanel4.getLayout());
        cl.show(jPanel4, "dashboard");
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActiveActionPerformed
        resetStatus();
        btnActive.setText("");
        btnActive.setIcon(active);
        status = "Active";
        
    }//GEN-LAST:event_btnActiveActionPerformed

    private void btnOnDutyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOnDutyActionPerformed
        resetStatus();
        btnOnDuty.setText("");
        btnOnDuty.setIcon(onduty);
        status = "On Duty";
    }//GEN-LAST:event_btnOnDutyActionPerformed

    private void btnInactiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInactiveActionPerformed
        resetStatus();
        btnInactive.setText("");
        btnInactive.setIcon(inactive);
        status = "Inactive";
    }//GEN-LAST:event_btnInactiveActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        pnlCreateOrder.setVisible(false);
        pnlOrder.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    private void txtCustomerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustomerNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustomerNameActionPerformed

    private void btnAddCancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCancel1ActionPerformed
        jPanel2.setVisible(true);
        pnlAddCustoemr.setVisible(false);
    }//GEN-LAST:event_btnAddCancel1ActionPerformed

    private void btnAddCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCustomerActionPerformed
        pnlAddCustoemr.setVisible(true);
    }//GEN-LAST:event_btnAddCustomerActionPerformed

    private void btnSaveCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveCustomerActionPerformed
        // TODO add your handling code here:
     try {

    String sql = "INSERT INTO users (username, email, contact_no) VALUES (?, ?, ?)";

    Connection conn = DBConnection.getConnection();

    PreparedStatement pst = conn.prepareStatement(sql);
    pst.setString(1, txtCustomerName.getText());
    pst.setString(2, txtCustomerEmail.getText());
    pst.setString(3, txtCustomerNo.getText());

    pst.executeUpdate();

    JOptionPane.showMessageDialog(this, "Customer Added!");

    clearFields();
    loadUsers();

} catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
}
    }//GEN-LAST:event_btnSaveCustomerActionPerformed

    private void txtSearchOrdersKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchOrdersKeyReleased
        // TODO add your handling code here:
         searchOrders(txtSearchOrders.getText());
    }//GEN-LAST:event_txtSearchOrdersKeyReleased

    private void cmbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStatusActionPerformed
        // TODO add your handling code here:
        String selectedStatus = cmbStatus.getSelectedItem().toString();
        filterByStatus(selectedStatus);
        
    }//GEN-LAST:event_cmbStatusActionPerformed

    private void cmbStatusProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStatusProductActionPerformed
        // TODO add your handling code here:
        loadProductsTable(
        txtSearchProduct.getText(),
        cmbStatusProduct.getSelectedItem().toString()
         );
    }//GEN-LAST:event_cmbStatusProductActionPerformed

    private void txtSearchProductKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchProductKeyReleased
        // TODO add your handling code here:
       loadProductsTable(
        txtSearchProduct.getText(),
        cmbStatusProduct.getSelectedItem().toString()
    );
    }//GEN-LAST:event_txtSearchProductKeyReleased

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new EmployeeDashboard().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActive;
    private javax.swing.JButton btnAddCancel1;
    private javax.swing.JButton btnAddCustomer;
    private javax.swing.JButton btnAddOrder;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnInactive;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnOnDuty;
    private javax.swing.JButton btnOrders;
    private javax.swing.JButton btnPayment;
    private javax.swing.JButton btnPlaceOrder;
    private javax.swing.JButton btnProducts;
    private javax.swing.JButton btnSaveCustomer;
    private javax.swing.JButton btnUsers;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JComboBox<String> cmbStatusProduct;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lblCompleted;
    private javax.swing.JLabel lblOrdersToday;
    private javax.swing.JLabel lblPrep;
    private javax.swing.JLabel lblReady;
    private javax.swing.JPanel pnlAddCustoemr;
    private javax.swing.JPanel pnlCreateOrder;
    private javax.swing.JPanel pnlCustomer;
    private javax.swing.JPanel pnlDashboard;
    private javax.swing.JPanel pnlOrder;
    private javax.swing.JPanel pnlPayment;
    private javax.swing.JPanel pnlProduct;
    private javax.swing.JTable tblOrders;
    private javax.swing.JTable tblProducts;
    private javax.swing.JTable tblStaffDashboard;
    private javax.swing.JTable tblSummary;
    private javax.swing.JTable tblSummary1;
    private javax.swing.JTable tblSummary2;
    private javax.swing.JTable tblUsers;
    private javax.swing.JTextField txtCustomerEmail;
    private javax.swing.JTextField txtCustomerName;
    private javax.swing.JTextField txtCustomerNo;
    private javax.swing.JTextField txtSearchOrders;
    private javax.swing.JTextField txtSearchProduct;
    // End of variables declaration//GEN-END:variables
}
