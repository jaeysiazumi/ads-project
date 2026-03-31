/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package adsfinalproject;

import java.awt.CardLayout;
import javax.swing.ImageIcon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import com.toedter.calendar.JDateChooser;

/**
 *
 * @author kleir
 */
public class AdminDashboard extends javax.swing.JFrame {
    
    ImageIcon darkd, darku, darko, darkp, darks, darkr;
    ImageIcon dash, user, ord, pr, sup, rep ;
    private Connection conn;
    JDateChooser dateChooser;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AdminDashboard.class.getName());

    /**
     * Creates new form AdminDashboard
     */
    
    public AdminDashboard() {
        initComponents(); 
        loadOrderStatusFilter(); 
        loadOrdersTable("All", "");
        cmbStatus1.addActionListener(e -> {

        Object selectedItem = cmbStatus1.getSelectedItem();
        if (selectedItem == null) {
            return; // stop if nothing selected
        }

        String status = selectedItem.toString();
        String search = txtSearchOrder.getText();

        loadOrdersTable(status, search);
    });

        dateChooser = new JDateChooser();
        pnlReports.add(dateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 150, 30));
        updateTotalOrdersLabel();
        updateTotalCustomers(); 
        updateTotalSalesLabel();
        loadDashboardTable();
        loadOrdersTable("All", "");
        
        btnOrders.addActionListener(e -> {
            loadOrdersTable("All", ""); 
        });
    
        tblDashboard.getModel().addTableModelListener(e -> {

            int row = e.getFirstRow();
            int column = e.getColumn();

            if(column == 4){ 

                int orderID = Integer.parseInt(tblDashboard.getValueAt(row,0).toString());
                String newStatus = tblDashboard.getValueAt(row,4).toString();

                try{
                    Connection con = DBConnection.getConnection();

                    String sql1 = "UPDATE orders SET status=? WHERE order_id=?";
                    PreparedStatement pst1 = con.prepareStatement(sql1);
                    pst1.setString(1,newStatus);
                    pst1.setInt(2,orderID);
                    pst1.executeUpdate();

                    String sql2 = "UPDATE tblorder SET status=? WHERE order_id=?";
                    PreparedStatement pst2 = con.prepareStatement(sql2);
                    pst2.setString(1,newStatus);
                    pst2.setInt(2,orderID);
                    pst2.executeUpdate();

                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        updateStatusFilterOptions();
        refreshUsersTable();
        refreshSuppliersTable();
        
        loadDashboardOrders();
        loadStatusFilter();
        loadProductsTable("", "All");
        loadSuppliers("", "All");
        txtProductID.setEditable(false);
        txtName.setEditable(false);
        txtDescription.setEditable(false);
        txtCategory.setEditable(false);
        txtPrice.setEditable(false);
        txtSupplier.setEditable(false);
        txtStatus.setEditable(false);
        txtDateAdded.setEditable(false);
        txtExpirationDate.setEditable(false);
        txtStock.setEditable(false);
       

        
        new javax.swing.Timer(5000, e -> {
            loadDashboardTable(); 
            updatePendingTransactions();
            updateCompletedTransactions();
            updateTotalOrders();
            updateTotalCustomers();
            updateTotalSalesLabel();
            updateStatusFilterOptions();
            refreshUsersTable();

            }).start();
            setSize(1318, 847);
            jPanel1.setVisible(true);
            setLocationRelativeTo(null);
        
        
        //----//
        darkd = new ImageIcon(getClass().getResource("/design/darkd.png"));
        darku = new ImageIcon(getClass().getResource("/design/darku.png"));
        darko = new ImageIcon(getClass().getResource("/design/darko.png"));
        darkp = new ImageIcon(getClass().getResource("/design/darkp.png"));
        darks = new ImageIcon(getClass().getResource("/design/darks.png"));
        darkr = new ImageIcon(getClass().getResource("/design/darkr.png"));
        dash = new ImageIcon(getClass().getResource("/design/Dashboard.png"));
        user = new ImageIcon(getClass().getResource("/design/users.png"));
        ord = new ImageIcon(getClass().getResource("/design/AAorder.png"));
        pr = new ImageIcon(getClass().getResource("/design/products.png"));
        sup = new ImageIcon(getClass().getResource("/design/suppliers.png"));
        rep = new ImageIcon(getClass().getResource("/design/reportstxt.png"));
        
          //-----//
        jPanel2.setLayout(new CardLayout());
        
        jPanel2.add(pnlDashboard, "dashboard");
        jPanel2.add(pnlUsers, "users");
        jPanel2.add(pnlOrder, "order");
        jPanel2.add(pnlProduct, "product");
        jPanel2.add(pnlSuppliers, "supplier");
        jPanel2.add(pnlReports, "reports");
        
        CardLayout cl = (CardLayout)(jPanel2.getLayout());
        cl.show(jPanel2, "dashboard");
        updatePendingTransactions();
            updateCompletedTransactions();
       
    }
     private void setActive(String page){
        btnDashboard.setIcon(dash);
        btnUsers.setIcon(user);
        btnOrders.setIcon(ord);
        btnProducts.setIcon(pr);
        btnSupplier.setIcon(sup);
        btnReports.setIcon(rep);
       
        
        if(page.equals("dashboard")){
            btnDashboard.setIcon(darkd);
        }else if (page.equals("users")){
            btnUsers.setIcon(darku);
        }else if (page.equals("order")){
            btnOrders.setIcon(darko);
        }else if (page.equals("product")){
            btnProducts.setIcon(darkp);
        }else if (page.equals("supplier")){
            btnSupplier.setIcon(darks);
        }else if (page.equals("reports")){
            btnReports.setIcon(darkr);
        }
            CardLayout cl = (CardLayout)(jPanel2.getLayout());
            cl.show(jPanel2, "dashboard");
     }
     public void updateTotalOrders() {
        String sql = "SELECT COUNT(*) AS total FROM orders WHERE status IN ('PENDING','PAID')";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                lblTotalOrd.setText(rs.getString(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
            lblTotalOrd.setText("0");
        }
    }

     private void updateTotalOrdersLabel() {
        try {
            Connection con = DBConnection.getConnection();
            if (con == null) {
                System.out.println("Connection failed!");
                return;
            }
            
            String sql = "SELECT COUNT(*) AS total FROM orders WHERE status IN ('PENDING','PAID')";
            PreparedStatement pst = con.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int totalPending = rs.getInt("total");
                lblTotalOrd.setText(String.valueOf(totalPending));
            }

            rs.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
            lblTotalOrd.setText("0");
        }
    }
      public void updateTotalCustomers() {
        String sql = "SELECT COUNT(DISTINCT customer_name) AS totalCustomers "
                   + "FROM orders WHERE status='COMPLETED'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                lblTotalCustomer.setText(rs.getString("totalCustomers"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            lblTotalCustomer.setText("0");
        }
    }
     public void updateTotalSalesLabel() {
        String sql = "SELECT IFNULL(SUM(total_amount),0) AS totalSales "
                   + "FROM orders WHERE status='COMPLETED'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                double totalSales = rs.getDouble("totalSales");
                lblTotalSales.setText("₱ " + String.format("%.2f", totalSales));
            }

        } catch (Exception e) {
            e.printStackTrace();
            lblTotalSales.setText("₱ 0.00");
        }
    }
    
     
     public void loadDashboardTable() {
        String sql = "SELECT order_id, customer_name, total_amount, order_type, status, order_date FROM orders";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            DefaultTableModel model = (DefaultTableModel) tblDashboard.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("order_id"),
                    rs.getString("customer_name"),
                    rs.getDouble("total_amount"),
                    rs.getString("order_type"),
                    rs.getString("status"),
                    rs.getString("order_date")
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading dashboard table: " + e.getMessage());
        }
         }
     
    public void loadUsersTable(String role, String searchText, String statusFilter) {
    DefaultTableModel model = (DefaultTableModel) tblCustomer.getModel();
    model.setRowCount(0);

    String sql = "SELECT id, username, email, contact_no, status, role FROM users WHERE 1=1";

    if (role != null && !role.isEmpty()) {
        sql += " AND role = ?";
    }

    if (!searchText.isEmpty()) {
        sql += " AND (username LIKE ? OR email LIKE ?)";
    }

    if (!statusFilter.equals("All")) {
        sql += " AND status = ?";
    }

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pst = conn.prepareStatement(sql)) {

        int index = 1;

        if (role != null && !role.isEmpty()) {
            pst.setString(index++, role);
        }

        if (!searchText.isEmpty()) {
            pst.setString(index++, "%" + searchText + "%");
            pst.setString(index++, "%" + searchText + "%");
        }

        if (!statusFilter.equals("All")) {
            pst.setString(index++, statusFilter);
        }

        System.out.println("SQL: " + sql);
        System.out.println("Role: " + role);
        System.out.println("Search: " + searchText);
        System.out.println("Status: " + statusFilter);

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            System.out.println("Found user: " + rs.getString("username"));

            Object[] row = new Object[] {
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("contact_no"),
                rs.getString("status")
            };
            model.addRow(row);
        }
        rs.close();
        pst.close();
        conn.close();


    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error loading users table: " + e.getMessage());
    }

}
    public void refreshUsersTable() {
    String searchText = txtSearchUsers.getText().trim();

    Object selectedItem = cmbStatusFilterr.getSelectedItem();
    String statusFilter = (selectedItem != null) ? selectedItem.toString() : "All";

    int selectedTab = tabbedpane.getSelectedIndex();

    System.out.println("Selected Tab: " + selectedTab);
    System.out.println("Search Text: " + searchText);
    System.out.println("Status Filter: " + statusFilter);

    if (selectedTab == 0) {
        loadUsersTable("customer", searchText, statusFilter);
    } else if (selectedTab == 1) {
        loadUsersTable("staff", searchText, statusFilter);
    }
}
    
    public void updateStatusFilterOptions() {
    cmbStatusFilterr.removeAllItems();
    cmbStatusFilterr.addItem("All");

    int selectedTab = tabbedpane.getSelectedIndex();

    if (selectedTab == 0) { // Customer
        cmbStatusFilterr.addItem("Active");
        cmbStatusFilterr.addItem("Inactive");
    } else if (selectedTab == 1) { // Staff
        cmbStatusFilterr.addItem("Active");
        cmbStatusFilterr.addItem("Inactive");
        cmbStatusFilterr.addItem("In Duty");
    }

    cmbStatusFilterr.setSelectedIndex(0);

}
    
    
    
    private void loadStatusFilter() {
    cmbStatusFilter.removeAllItems();
    cmbStatusFilter.addItem("Status");
    cmbStatusFilter.addItem("Available");
    cmbStatusFilter.addItem("Out of Stock");
    cmbStatusFilter.addItem("Discontinued");

}
    private void loadProductsTable(String search, String statusFilter) {
    DefaultTableModel model = (DefaultTableModel) tblProducts.getModel();
    model.setRowCount(0);

    String sql = "SELECT * FROM products WHERE (name LIKE ? OR category LIKE ? OR supplier LIKE ?)";
    if (!statusFilter.equals("All")) {
        sql += " AND status = ?";
    }
    sql += " ORDER BY product_id DESC";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pst = conn.prepareStatement(sql)) {

        pst.setString(1, "%" + search + "%");
        pst.setString(2, "%" + search + "%");
        pst.setString(3, "%" + search + "%");
        if (!statusFilter.equals("All")) {
            pst.setString(4, statusFilter);
        }

        try (ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("product_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("category"),
                    rs.getInt("stock"),
                    rs.getDouble("price"),
                    rs.getString("supplier"),
                    rs.getString("status"),
                    rs.getDate("date_added"),
                    rs.getDate("expiration_date")
                });
            }
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error loading products: " + e.getMessage());
    }
}
    private void refreshProductsTable() {
    String searchText = txtSearch.getText().trim();

    Object selectedItem = cmbStatusFilter.getSelectedItem();
    String statusFilter = (selectedItem != null) ? selectedItem.toString() : "All";

    loadProductsTable(searchText, statusFilter);
}
        private void loadDashboardOrders() {
            try {
                Connection conn = DBConnection.getConnection();

                String sql = "SELECT order_id, customer_name, total_amount, order_type, status, order_date FROM orders";
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();

                DefaultTableModel model = (DefaultTableModel) tblDashboard.getModel();
                model.setRowCount(0); // clear table

                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("order_id"),
                        rs.getString("customer_name"),
                        rs.getDouble("total_amount"),
                        rs.getString("order_type"),
                        rs.getString("status"),
                        rs.getString("order_date")
                    });
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
                }
        public void updatePendingTransactions() {
            String sql = "SELECT COUNT(*) AS totalPending FROM orders WHERE status='PENDING' OR status='PAID'";

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement pst = con.prepareStatement(sql);
                 ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {
                    lblTransPend.setText(String.valueOf(rs.getInt("totalPending")));
                }

            } catch (Exception e) {
                e.printStackTrace();
                lblTransPend.setText("0");
            }
        }
        public void updateCompletedTransactions() {
            String sql = "SELECT COUNT(*) AS totalCompleted FROM orders WHERE TRIM(UPPER(status))='COMPLETED'";

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement pst = con.prepareStatement(sql);
                 ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {
                    int totalCompleted = rs.getInt("totalCompleted");
                    System.out.println("Completed orders: " + totalCompleted); 
                    lblTransComp.setText(String.valueOf(totalCompleted));
                } else {
                    lblTransComp.setText("0");
                }

            } catch (Exception e) {
                e.printStackTrace();
                lblTransComp.setText("0");
            }
        }
        public void loadOrdersTable(String statusFilter, String searchText) {
    try {
        Connection con = DBConnection.getConnection();

        String sql = "SELECT order_id, customer_name, order_date, total_amount, order_type, status "
                   + "FROM tblorder WHERE customer_name LIKE ?";

        if (!statusFilter.equalsIgnoreCase("All")) {
            sql += " AND TRIM(UPPER(status)) = ?";
        }

        sql += " ORDER BY order_id DESC";

        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, "%" + searchText + "%");

        if (!statusFilter.equalsIgnoreCase("All")) {
            pst.setString(2, statusFilter.toUpperCase());
        }

        ResultSet rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
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
        e.printStackTrace();
    }
}
        private void loadOrderStatusFilter() {
        cmbStatus1.removeAllItems();
        cmbStatus1.addItem("All");
        cmbStatus1.addItem("Pending");
        cmbStatus1.addItem("Preparing");
        cmbStatus1.addItem("Completed");
        cmbStatus1.setSelectedIndex(0);
    }

        
     public void loadSuppliers(String searchText, String statusFilter) {
    try {
        Connection con = DBConnection.getConnection();

        String sql = "SELECT * FROM suppliers WHERE name LIKE ?";

        if (!statusFilter.equals("All")) {
            sql += " AND status = ?";
        }

        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, "%" + searchText + "%");

        if (!statusFilter.equals("All")) {
            pst.setString(2, statusFilter);
        }

        ResultSet rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) tblSuppliers.getModel();
        model.setRowCount(0);

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("supplier_id"),
                rs.getString("name"),
                rs.getString("contact_person"),
                rs.getString("contact_no"),
                rs.getString("email"),
                rs.getString("status"),
                rs.getString("created_at")
            });
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public void refreshSuppliersTable() {
    String searchText = txtSearchSupplier.getText().trim();

    Object selectedItem = cmbStatus.getSelectedItem();
    String statusFilter = (selectedItem != null) ? selectedItem.toString() : "All";

    if (statusFilter.equals("Status")) {
        statusFilter = "All";
    }

    loadSuppliers(searchText, statusFilter);
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
        btnReports = new javax.swing.JButton();
        btnSupplier = new javax.swing.JButton();
        btnProducts = new javax.swing.JButton();
        btnOrders = new javax.swing.JButton();
        btnDashboard = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        pnlReports = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        tblPaneProduct = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        tblPaneCustomer = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        pnlSuppliers = new javax.swing.JPanel();
        pnlAddSuppliers = new javax.swing.JPanel();
        txtContactNo = new javax.swing.JTextField();
        txtContactPerson = new javax.swing.JTextField();
        txtNamee = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnCancel = new javax.swing.JButton();
        btnAddSupplier = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        pnlUsers2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblSuppliers = new javax.swing.JTable();
        cmbStatus = new javax.swing.JComboBox<>();
        txtSearchSupplier = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        btnDeletee = new javax.swing.JButton();
        pnlOrder = new javax.swing.JPanel();
        pnlverufy = new javax.swing.JPanel();
        btnOrdCanc = new javax.swing.JButton();
        btnOrdConf = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        pnlView = new javax.swing.JPanel();
        btnOrdCanc1 = new javax.swing.JButton();
        btnOrdConf1 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnOrdView = new javax.swing.JButton();
        btnOrdDel1 = new javax.swing.JButton();
        btnOrdEdit2 = new javax.swing.JButton();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();
        btnOrdView1 = new javax.swing.JButton();
        btnOrdDel2 = new javax.swing.JButton();
        btnOrdEdit3 = new javax.swing.JButton();
        cmbStatus1 = new javax.swing.JComboBox<>();
        txtSearchOrder = new javax.swing.JTextArea();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblOrder = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        btnOrdVerifyPay1 = new javax.swing.JButton();
        btnOrdDel3 = new javax.swing.JButton();
        btnOrdEdit4 = new javax.swing.JButton();
        jComboBox8 = new javax.swing.JComboBox<>();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable11 = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pnlUsers = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        cmbStatusFilterr = new javax.swing.JComboBox<>();
        txtSearchUsers = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblCustomer = new javax.swing.JTable();
        btnCusDel = new javax.swing.JButton();
        btnCusEdit = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        pnlAddStaff = new javax.swing.JPanel();
        txtAddSupCP1 = new javax.swing.JTextField();
        txtAddName2 = new javax.swing.JTextField();
        txtAddSupEm1 = new javax.swing.JTextField();
        btnAddCancel2 = new javax.swing.JButton();
        btnAddSave2 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        pnlUsers1 = new javax.swing.JPanel();
        btnStaffEdit = new javax.swing.JButton();
        btnStaffDel = new javax.swing.JButton();
        btnStaffAdd = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblStaff = new javax.swing.JTable();
        jComboBox4 = new javax.swing.JComboBox<>();
        tabbedpane = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jTextField5 = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        btnCusDel1 = new javax.swing.JButton();
        btnCusEdit1 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jTextField6 = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblStaffs = new javax.swing.JTable();
        btnStaffAdd1 = new javax.swing.JButton();
        btnStaffDel1 = new javax.swing.JButton();
        btnStaffEdit1 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        pnlDashboard = new javax.swing.JPanel();
        lblLowStiock = new javax.swing.JLabel();
        lblTotalCustomer = new javax.swing.JLabel();
        lblTotalSales = new javax.swing.JLabel();
        lblTransPend = new javax.swing.JLabel();
        lblTotalOrd = new javax.swing.JLabel();
        lblTransComp = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDashboard = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        pnlProduct = new javax.swing.JPanel();
        pnlAddPr = new javax.swing.JPanel();
        txtAddName = new javax.swing.JTextField();
        txtAddCategory = new javax.swing.JTextField();
        txtxAddDescrip = new javax.swing.JTextField();
        txtAddPrice = new javax.swing.JTextField();
        txtAddStock = new javax.swing.JTextField();
        btnAddCancel = new javax.swing.JButton();
        btnAddSave = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        pnlRestock = new javax.swing.JPanel();
        txtExpirationDate = new javax.swing.JTextField();
        txtDateAdded = new javax.swing.JTextField();
        txtSupplier = new javax.swing.JTextField();
        txtStatus = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        txtStock = new javax.swing.JTextField();
        txtCategory = new javax.swing.JTextField();
        txtDescription = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtProductID = new javax.swing.JTextField();
        btnBack = new javax.swing.JButton();
        btnSaveRestock = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        btnDelete = new javax.swing.JButton();
        btnRestock = new javax.swing.JButton();
        btnAddProduct = new javax.swing.JButton();
        btnPrUpdate1 = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();
        cmbStatusFilter = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
        jPanel1.add(btnUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 360, 70));

        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/logout.png"))); // NOI18N
        btnLogout.setBorder(null);
        btnLogout.setBorderPainted(false);
        btnLogout.setContentAreaFilled(false);
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 700, 300, 50));

        btnReports.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/reportstxt.png"))); // NOI18N
        btnReports.setBorder(null);
        btnReports.setBorderPainted(false);
        btnReports.setContentAreaFilled(false);
        btnReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportsActionPerformed(evt);
            }
        });
        jPanel1.add(btnReports, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 520, 300, 70));

        btnSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/suppliers.png"))); // NOI18N
        btnSupplier.setBorder(null);
        btnSupplier.setBorderPainted(false);
        btnSupplier.setContentAreaFilled(false);
        btnSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierActionPerformed(evt);
            }
        });
        jPanel1.add(btnSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, 300, 80));

        btnProducts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/products.png"))); // NOI18N
        btnProducts.setBorder(null);
        btnProducts.setBorderPainted(false);
        btnProducts.setContentAreaFilled(false);
        btnProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductsActionPerformed(evt);
            }
        });
        jPanel1.add(btnProducts, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 300, 70));

        btnOrders.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/AAorder.png"))); // NOI18N
        btnOrders.setBorder(null);
        btnOrders.setBorderPainted(false);
        btnOrders.setContentAreaFilled(false);
        btnOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdersActionPerformed(evt);
            }
        });
        jPanel1.add(btnOrders, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 320, 80));

        btnDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Dashboard.png"))); // NOI18N
        btnDashboard.setBorder(null);
        btnDashboard.setBorderPainted(false);
        btnDashboard.setContentAreaFilled(false);
        btnDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDashboardActionPerformed(evt);
            }
        });
        jPanel1.add(btnDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 300, 70));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/admindasd.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 780));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -3, 250, 850));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.CardLayout());

        pnlReports.setBackground(new java.awt.Color(255, 255, 255));
        pnlReports.setPreferredSize(new java.awt.Dimension(1300, 800));
        pnlReports.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane4.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane4.setTabPlacement(javax.swing.JTabbedPane.RIGHT);
        jTabbedPane4.setToolTipText("");
        jTabbedPane4.setFont(new java.awt.Font("Segoe UI Historic", 0, 8)); // NOI18N

        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblPaneProduct.setBackground(new java.awt.Color(255, 255, 255));

        jTable6.setBackground(new java.awt.Color(255, 255, 255));
        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Name", "Quantity Sold", "Total Sales", "Date"
            }
        ));
        tblPaneProduct.setViewportView(jTable6);

        jPanel8.add(tblPaneProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(312, 207, 930, 500));

        tblPaneCustomer.setBackground(new java.awt.Color(255, 255, 255));

        jTable3.setBackground(new java.awt.Color(255, 255, 255));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Name", "Total Order", "Total Items Ordered", "Amount Spent", "Date"
            }
        ));
        tblPaneCustomer.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(3).setHeaderValue("Amount Spent");
        }

        jPanel8.add(tblPaneCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(312, 207, 930, 500));

        jDateChooser1.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser1.setDateFormatString("dd/MM/yyyy");
        jDateChooser1.setMaxSelectableDate(new java.util.Date(253370739717000L));
        jPanel8.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 130, 180, 30));

        jComboBox1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Customer", "Product" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel8.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, 140, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/rreports.png"))); // NOI18N
        jPanel8.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1277, -1));

        jTabbedPane4.addTab("S", jPanel8);

        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));

        jTable4.setBackground(new java.awt.Color(255, 255, 255));
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Product Name", "Stock Quantity", "Status"
            }
        ));
        jScrollPane4.setViewportView(jTable4);

        jPanel9.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(312, 167, 930, 540));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/InventoryReports.png"))); // NOI18N
        jPanel9.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, -1));

        jTabbedPane4.addTab("I", jPanel9);

        pnlReports.add(jTabbedPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1310, 800));

        jPanel2.add(pnlReports, "reports");
        pnlReports.getAccessibleContext().setAccessibleName("");

        pnlSuppliers.setBackground(new java.awt.Color(255, 255, 255));
        pnlSuppliers.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlAddSuppliers.setBackground(new java.awt.Color(255, 255, 255));
        pnlAddSuppliers.setPreferredSize(new java.awt.Dimension(510, 680));
        pnlAddSuppliers.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtContactNo.setBackground(new java.awt.Color(255, 255, 255));
        txtContactNo.setForeground(new java.awt.Color(0, 0, 0));
        txtContactNo.setBorder(null);
        pnlAddSuppliers.add(txtContactNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 350, 30));

        txtContactPerson.setBackground(new java.awt.Color(255, 255, 255));
        txtContactPerson.setForeground(new java.awt.Color(0, 0, 0));
        txtContactPerson.setBorder(null);
        pnlAddSuppliers.add(txtContactPerson, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 370, 30));

        txtNamee.setBackground(new java.awt.Color(255, 255, 255));
        txtNamee.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        txtNamee.setForeground(new java.awt.Color(0, 0, 0));
        txtNamee.setBorder(null);
        txtNamee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameeActionPerformed(evt);
            }
        });
        pnlAddSuppliers.add(txtNamee, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 360, 30));

        txtEmail.setBackground(new java.awt.Color(255, 255, 255));
        txtEmail.setForeground(new java.awt.Color(0, 0, 0));
        txtEmail.setBorder(null);
        pnlAddSuppliers.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 430, 360, 30));

        btnCancel.setText("-");
        btnCancel.setBorder(null);
        btnCancel.setBorderPainted(false);
        btnCancel.setContentAreaFilled(false);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        pnlAddSuppliers.add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 510, 80, 40));

        btnAddSupplier.setText("-");
        btnAddSupplier.setBorder(null);
        btnAddSupplier.setBorderPainted(false);
        btnAddSupplier.setContentAreaFilled(false);
        btnAddSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSupplierActionPerformed(evt);
            }
        });
        pnlAddSuppliers.add(btnAddSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 510, 80, 40));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/AddSupplier.png"))); // NOI18N
        pnlAddSuppliers.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pnlSuppliers.add(pnlAddSuppliers, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, -1, -1));

        pnlUsers2.setBackground(new java.awt.Color(255, 255, 255));
        pnlUsers2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));

        tblSuppliers.setBackground(new java.awt.Color(255, 255, 255));
        tblSuppliers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Contact Person", "Contact No.", "Email", "Status", "Date Added"
            }
        ));
        jScrollPane5.setViewportView(tblSuppliers);
        if (tblSuppliers.getColumnModel().getColumnCount() > 0) {
            tblSuppliers.getColumnModel().getColumn(4).setResizable(false);
        }

        pnlUsers2.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(312, 167, 930, 500));

        cmbStatus.setBackground(new java.awt.Color(255, 255, 255));
        cmbStatus.setForeground(new java.awt.Color(51, 51, 51));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Status", "Active", "Inactive" }));
        cmbStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStatusActionPerformed(evt);
            }
        });
        pnlUsers2.add(cmbStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 112, 150, 30));

        txtSearchSupplier.setBackground(new java.awt.Color(255, 255, 255));
        txtSearchSupplier.setForeground(new java.awt.Color(0, 0, 0));
        txtSearchSupplier.setBorder(null);
        txtSearchSupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchSupplierKeyReleased(evt);
            }
        });
        pnlUsers2.add(txtSearchSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 112, 380, 20));

        btnSave.setBackground(new java.awt.Color(255, 255, 255));
        btnSave.setText("-");
        btnSave.setBorder(null);
        btnSave.setBorderPainted(false);
        btnSave.setContentAreaFilled(false);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        pnlUsers2.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 740, 110, 30));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Supplier.png"))); // NOI18N
        pnlUsers2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1320, -1));

        btnEdit.setBackground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("-");
        btnEdit.setBorder(null);
        btnEdit.setBorderPainted(false);
        btnEdit.setContentAreaFilled(false);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        pnlUsers2.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 740, 70, 30));

        btnDeletee.setBackground(new java.awt.Color(255, 255, 255));
        btnDeletee.setText("-");
        btnDeletee.setBorder(null);
        btnDeletee.setBorderPainted(false);
        btnDeletee.setContentAreaFilled(false);
        btnDeletee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteeActionPerformed(evt);
            }
        });
        pnlUsers2.add(btnDeletee, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 740, 80, 30));

        pnlSuppliers.add(pnlUsers2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2.add(pnlSuppliers, "suppliers");

        pnlOrder.setBackground(new java.awt.Color(255, 255, 255));
        pnlOrder.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlverufy.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnOrdCanc.setText("-");
        btnOrdCanc.setBorder(null);
        btnOrdCanc.setBorderPainted(false);
        btnOrdCanc.setContentAreaFilled(false);
        btnOrdCanc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdCancActionPerformed(evt);
            }
        });
        pnlverufy.add(btnOrdCanc, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 440, 90, 30));

        btnOrdConf.setText("-");
        btnOrdConf.setBorder(null);
        btnOrdConf.setBorderPainted(false);
        btnOrdConf.setContentAreaFilled(false);
        pnlverufy.add(btnOrdConf, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 440, 90, 30));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/apayment.png"))); // NOI18N
        pnlverufy.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 6, 380, 500));

        pnlOrder.add(pnlverufy, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 130, 390, 510));

        pnlView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnOrdCanc1.setText("-");
        btnOrdCanc1.setBorder(null);
        btnOrdCanc1.setBorderPainted(false);
        btnOrdCanc1.setContentAreaFilled(false);
        btnOrdCanc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdCanc1ActionPerformed(evt);
            }
        });
        pnlView.add(btnOrdCanc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 440, 90, 30));

        btnOrdConf1.setText("-");
        btnOrdConf1.setBorder(null);
        btnOrdConf1.setBorderPainted(false);
        btnOrdConf1.setContentAreaFilled(false);
        pnlView.add(btnOrdConf1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 440, 90, 30));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/aorder.png"))); // NOI18N
        pnlView.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 6, 380, 500));

        pnlOrder.add(pnlView, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 130, 390, 510));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnOrdView.setBackground(new java.awt.Color(255, 255, 255));
        btnOrdView.setText("-");
        btnOrdView.setBorder(null);
        btnOrdView.setBorderPainted(false);
        btnOrdView.setContentAreaFilled(false);
        btnOrdView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdViewActionPerformed(evt);
            }
        });
        jPanel4.add(btnOrdView, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 740, 130, 30));

        btnOrdDel1.setBackground(new java.awt.Color(255, 255, 255));
        btnOrdDel1.setText("-");
        btnOrdDel1.setBorder(null);
        btnOrdDel1.setBorderPainted(false);
        btnOrdDel1.setContentAreaFilled(false);
        jPanel4.add(btnOrdDel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 740, 70, 30));

        btnOrdEdit2.setBackground(new java.awt.Color(255, 255, 255));
        btnOrdEdit2.setText("-");
        btnOrdEdit2.setBorder(null);
        btnOrdEdit2.setBorderPainted(false);
        btnOrdEdit2.setContentAreaFilled(false);
        jPanel4.add(btnOrdEdit2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 740, 70, 30));

        jTabbedPane5.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane5.setTabPlacement(javax.swing.JTabbedPane.RIGHT);
        jTabbedPane5.setToolTipText("");
        jTabbedPane5.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N

        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnOrdView1.setBackground(new java.awt.Color(255, 255, 255));
        btnOrdView1.setText("-");
        btnOrdView1.setBorder(null);
        btnOrdView1.setBorderPainted(false);
        btnOrdView1.setContentAreaFilled(false);
        btnOrdView1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdView1ActionPerformed(evt);
            }
        });
        jPanel12.add(btnOrdView1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 740, 130, 30));

        btnOrdDel2.setBackground(new java.awt.Color(255, 255, 255));
        btnOrdDel2.setText("-");
        btnOrdDel2.setBorder(null);
        btnOrdDel2.setBorderPainted(false);
        btnOrdDel2.setContentAreaFilled(false);
        jPanel12.add(btnOrdDel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 740, 70, 30));

        btnOrdEdit3.setBackground(new java.awt.Color(255, 255, 255));
        btnOrdEdit3.setText("-");
        btnOrdEdit3.setBorder(null);
        btnOrdEdit3.setBorderPainted(false);
        btnOrdEdit3.setContentAreaFilled(false);
        jPanel12.add(btnOrdEdit3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 740, 70, 30));

        cmbStatus1.setBackground(new java.awt.Color(255, 255, 255));
        cmbStatus1.setForeground(new java.awt.Color(51, 51, 51));
        cmbStatus1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Status", "Pending", "Preparing", "Completed" }));
        jPanel12.add(cmbStatus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 112, 190, 30));

        txtSearchOrder.setBackground(new java.awt.Color(255, 255, 255));
        txtSearchOrder.setColumns(20);
        txtSearchOrder.setForeground(new java.awt.Color(0, 0, 0));
        txtSearchOrder.setRows(5);
        txtSearchOrder.setBorder(null);
        txtSearchOrder.setMinimumSize(new java.awt.Dimension(1, 10));
        txtSearchOrder.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchOrderKeyReleased(evt);
            }
        });
        jPanel12.add(txtSearchOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 113, 380, 20));

        jScrollPane12.setBackground(new java.awt.Color(255, 255, 255));

        tblOrder.setBackground(new java.awt.Color(255, 255, 255));
        tblOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Customer", "Date", "Total", "Order Type", "Status"
            }
        ));
        jScrollPane12.setViewportView(tblOrder);
        if (tblOrder.getColumnModel().getColumnCount() > 0) {
            tblOrder.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel12.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(312, 167, 930, 500));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/orderdash.png"))); // NOI18N
        jPanel12.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1277, -1));

        jTabbedPane5.addTab("O", jPanel12);

        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnOrdVerifyPay1.setBackground(new java.awt.Color(255, 255, 255));
        btnOrdVerifyPay1.setText("-");
        btnOrdVerifyPay1.setBorder(null);
        btnOrdVerifyPay1.setBorderPainted(false);
        btnOrdVerifyPay1.setContentAreaFilled(false);
        btnOrdVerifyPay1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdVerifyPay1ActionPerformed(evt);
            }
        });
        jPanel13.add(btnOrdVerifyPay1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 740, 130, 30));

        btnOrdDel3.setBackground(new java.awt.Color(255, 255, 255));
        btnOrdDel3.setText("-");
        btnOrdDel3.setBorder(null);
        btnOrdDel3.setBorderPainted(false);
        btnOrdDel3.setContentAreaFilled(false);
        jPanel13.add(btnOrdDel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 740, 70, 30));

        btnOrdEdit4.setBackground(new java.awt.Color(255, 255, 255));
        btnOrdEdit4.setText("-");
        btnOrdEdit4.setBorder(null);
        btnOrdEdit4.setBorderPainted(false);
        btnOrdEdit4.setContentAreaFilled(false);
        jPanel13.add(btnOrdEdit4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 740, 70, 30));

        jComboBox8.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox8.setForeground(new java.awt.Color(51, 51, 51));
        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Status", "Pending", "Preparing", "Completed" }));
        jPanel13.add(jComboBox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 112, 190, 30));

        jScrollPane11.setBackground(new java.awt.Color(255, 255, 255));

        jTable11.setBackground(new java.awt.Color(255, 255, 255));
        jTable11.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Order ID", "Amount", "Date", "Type", "Reference No.", "Status"
            }
        ));
        jScrollPane11.setViewportView(jTable11);
        if (jTable11.getColumnModel().getColumnCount() > 0) {
            jTable11.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel13.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(312, 167, 930, 500));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Payment.png"))); // NOI18N
        jPanel13.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1330, -1));

        jTabbedPane5.addTab("P", jPanel13);

        jPanel4.add(jTabbedPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1330, 800));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/orderdash.png"))); // NOI18N
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1277, -1));

        pnlOrder.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2.add(pnlOrder, "order");

        pnlUsers.setBackground(new java.awt.Color(255, 255, 255));
        pnlUsers.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.RIGHT);
        jTabbedPane2.setToolTipText("");
        jTabbedPane2.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmbStatusFilterr.setBackground(new java.awt.Color(255, 255, 255));
        cmbStatusFilterr.setForeground(new java.awt.Color(51, 51, 51));
        cmbStatusFilterr.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Status:", "Active", "Inactive" }));
        cmbStatusFilterr.setBorder(null);
        cmbStatusFilterr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStatusFilterrActionPerformed(evt);
            }
        });
        jPanel5.add(cmbStatusFilterr, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 110, 240, 30));

        txtSearchUsers.setBackground(new java.awt.Color(255, 255, 255));
        txtSearchUsers.setForeground(new java.awt.Color(0, 0, 0));
        txtSearchUsers.setBorder(null);
        txtSearchUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchUsersActionPerformed(evt);
            }
        });
        txtSearchUsers.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchUsersKeyReleased(evt);
            }
        });
        jPanel5.add(txtSearchUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 112, 370, 20));

        jScrollPane7.setBackground(new java.awt.Color(255, 255, 255));

        tblCustomer.setBackground(new java.awt.Color(255, 255, 255));
        tblCustomer.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(tblCustomer);

        jPanel5.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(312, 167, 930, 500));

        btnCusDel.setBackground(new java.awt.Color(255, 255, 255));
        btnCusDel.setText("-");
        btnCusDel.setBorder(null);
        btnCusDel.setBorderPainted(false);
        btnCusDel.setContentAreaFilled(false);
        jPanel5.add(btnCusDel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 740, 70, 30));

        btnCusEdit.setBackground(new java.awt.Color(255, 255, 255));
        btnCusEdit.setText("-");
        btnCusEdit.setBorder(null);
        btnCusEdit.setBorderPainted(false);
        btnCusEdit.setContentAreaFilled(false);
        jPanel5.add(btnCusEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 740, 70, 30));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Usersimnida.png"))); // NOI18N
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1277, -1));

        jTabbedPane2.addTab("C", jPanel5);

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlAddStaff.setBackground(new java.awt.Color(255, 255, 255));
        pnlAddStaff.setPreferredSize(new java.awt.Dimension(510, 680));
        pnlAddStaff.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtAddSupCP1.setBackground(new java.awt.Color(255, 255, 255));
        txtAddSupCP1.setForeground(new java.awt.Color(0, 0, 0));
        txtAddSupCP1.setBorder(null);
        pnlAddStaff.add(txtAddSupCP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 360, 30));

        txtAddName2.setBackground(new java.awt.Color(255, 255, 255));
        txtAddName2.setForeground(new java.awt.Color(0, 0, 0));
        txtAddName2.setBorder(null);
        pnlAddStaff.add(txtAddName2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, 350, 30));

        txtAddSupEm1.setBackground(new java.awt.Color(255, 255, 255));
        txtAddSupEm1.setForeground(new java.awt.Color(0, 0, 0));
        txtAddSupEm1.setBorder(null);
        pnlAddStaff.add(txtAddSupEm1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 360, 30));

        btnAddCancel2.setText("-");
        btnAddCancel2.setBorder(null);
        btnAddCancel2.setBorderPainted(false);
        btnAddCancel2.setContentAreaFilled(false);
        btnAddCancel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCancel2ActionPerformed(evt);
            }
        });
        pnlAddStaff.add(btnAddCancel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 510, 90, 50));

        btnAddSave2.setText("-");
        btnAddSave2.setBorder(null);
        btnAddSave2.setBorderPainted(false);
        btnAddSave2.setContentAreaFilled(false);
        pnlAddStaff.add(btnAddSave2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 510, 90, 40));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/AddStaff.png"))); // NOI18N
        pnlAddStaff.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 500, 680));

        jPanel6.add(pnlAddStaff, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, -1, -1));

        pnlUsers1.setBackground(new java.awt.Color(255, 255, 255));
        pnlUsers1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnStaffEdit.setBackground(new java.awt.Color(255, 255, 255));
        btnStaffEdit.setText("-");
        btnStaffEdit.setBorder(null);
        btnStaffEdit.setBorderPainted(false);
        btnStaffEdit.setContentAreaFilled(false);
        pnlUsers1.add(btnStaffEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 740, 70, 30));

        btnStaffDel.setBackground(new java.awt.Color(255, 255, 255));
        btnStaffDel.setText("-");
        btnStaffDel.setBorder(null);
        btnStaffDel.setBorderPainted(false);
        btnStaffDel.setContentAreaFilled(false);
        pnlUsers1.add(btnStaffDel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 740, 70, 30));

        btnStaffAdd.setBackground(new java.awt.Color(255, 255, 255));
        btnStaffAdd.setText("-");
        btnStaffAdd.setBorder(null);
        btnStaffAdd.setBorderPainted(false);
        btnStaffAdd.setContentAreaFilled(false);
        btnStaffAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStaffAddActionPerformed(evt);
            }
        });
        pnlUsers1.add(btnStaffAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 740, 150, 40));

        jTextField4.setBackground(new java.awt.Color(255, 255, 255));
        jTextField4.setForeground(new java.awt.Color(0, 0, 0));
        jTextField4.setBorder(null);
        pnlUsers1.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 112, 370, 20));

        jScrollPane8.setBackground(new java.awt.Color(255, 255, 255));

        tblStaff.setBackground(new java.awt.Color(255, 255, 255));
        tblStaff.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane8.setViewportView(tblStaff);

        pnlUsers1.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(312, 167, 930, 500));

        jComboBox4.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox4.setForeground(new java.awt.Color(51, 51, 51));
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Status", "Active", "On Duty", "Inactive" }));
        jComboBox4.setBorder(null);
        pnlUsers1.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 110, 240, 30));

        tabbedpane.setBackground(new java.awt.Color(255, 255, 255));
        tabbedpane.setTabPlacement(javax.swing.JTabbedPane.RIGHT);
        tabbedpane.setToolTipText("");
        tabbedpane.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        tabbedpane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabbedpaneStateChanged(evt);
            }
        });

        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox5.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox5.setForeground(new java.awt.Color(51, 51, 51));
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox5.setBorder(null);
        jPanel10.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 110, 240, 30));

        jTextField5.setBackground(new java.awt.Color(255, 255, 255));
        jTextField5.setForeground(new java.awt.Color(0, 0, 0));
        jTextField5.setBorder(null);
        jPanel10.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 112, 370, 20));

        jScrollPane9.setBackground(new java.awt.Color(255, 255, 255));

        jTable9.setBackground(new java.awt.Color(255, 255, 255));
        jTable9.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Contact No.", "Status"
            }
        ));
        jScrollPane9.setViewportView(jTable9);

        jPanel10.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(312, 167, 930, 500));

        btnCusDel1.setBackground(new java.awt.Color(255, 255, 255));
        btnCusDel1.setText("-");
        btnCusDel1.setBorder(null);
        btnCusDel1.setBorderPainted(false);
        btnCusDel1.setContentAreaFilled(false);
        jPanel10.add(btnCusDel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 740, 70, 30));

        btnCusEdit1.setBackground(new java.awt.Color(255, 255, 255));
        btnCusEdit1.setText("-");
        btnCusEdit1.setBorder(null);
        btnCusEdit1.setBorderPainted(false);
        btnCusEdit1.setContentAreaFilled(false);
        jPanel10.add(btnCusEdit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 740, 70, 30));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Usersimnida.png"))); // NOI18N
        jPanel10.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1277, -1));

        tabbedpane.addTab("C", jPanel10);

        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox6.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox6.setForeground(new java.awt.Color(51, 51, 51));
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Status", "Active", "On Duty", "Inactive" }));
        jComboBox6.setBorder(null);
        jPanel11.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 110, 240, 30));

        jTextField6.setBackground(new java.awt.Color(255, 255, 255));
        jTextField6.setForeground(new java.awt.Color(0, 0, 0));
        jTextField6.setBorder(null);
        jPanel11.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 112, 370, 20));

        jScrollPane10.setBackground(new java.awt.Color(255, 255, 255));

        tblStaffs.setBackground(new java.awt.Color(255, 255, 255));
        tblStaffs.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane10.setViewportView(tblStaffs);

        jPanel11.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(312, 167, 930, 500));

        btnStaffAdd1.setBackground(new java.awt.Color(255, 255, 255));
        btnStaffAdd1.setText("-");
        btnStaffAdd1.setBorder(null);
        btnStaffAdd1.setBorderPainted(false);
        btnStaffAdd1.setContentAreaFilled(false);
        btnStaffAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStaffAdd1ActionPerformed(evt);
            }
        });
        jPanel11.add(btnStaffAdd1, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 740, 150, 40));

        btnStaffDel1.setBackground(new java.awt.Color(255, 255, 255));
        btnStaffDel1.setText("-");
        btnStaffDel1.setBorder(null);
        btnStaffDel1.setBorderPainted(false);
        btnStaffDel1.setContentAreaFilled(false);
        jPanel11.add(btnStaffDel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 740, 70, 30));

        btnStaffEdit1.setBackground(new java.awt.Color(255, 255, 255));
        btnStaffEdit1.setText("-");
        btnStaffEdit1.setBorder(null);
        btnStaffEdit1.setBorderPainted(false);
        btnStaffEdit1.setContentAreaFilled(false);
        jPanel11.add(btnStaffEdit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 740, 70, 30));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Userdash.png"))); // NOI18N
        jPanel11.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1330, -1));

        tabbedpane.addTab("S", jPanel11);

        pnlUsers1.add(tabbedpane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1330, 800));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Userdash.png"))); // NOI18N
        pnlUsers1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1330, -1));

        jPanel6.add(pnlUsers1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jTabbedPane2.addTab("S", jPanel6);

        pnlUsers.add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1330, 800));

        jPanel2.add(pnlUsers, "users");

        pnlDashboard.setBackground(new java.awt.Color(255, 255, 255));
        pnlDashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLowStiock.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblLowStiock.setForeground(new java.awt.Color(255, 255, 255));
        lblLowStiock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLowStiock.setText("-");
        pnlDashboard.add(lblLowStiock, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 140, 70, -1));

        lblTotalCustomer.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTotalCustomer.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalCustomer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalCustomer.setText("-");
        pnlDashboard.add(lblTotalCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 140, 70, -1));

        lblTotalSales.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTotalSales.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalSales.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalSales.setText("-");
        pnlDashboard.add(lblTotalSales, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 140, 100, -1));

        lblTransPend.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTransPend.setForeground(new java.awt.Color(0, 0, 0));
        lblTransPend.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTransPend.setText("-");
        pnlDashboard.add(lblTransPend, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 700, 100, 60));

        lblTotalOrd.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTotalOrd.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalOrd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalOrd.setText("-");
        pnlDashboard.add(lblTotalOrd, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, 40, -1));

        lblTransComp.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTransComp.setForeground(new java.awt.Color(0, 0, 0));
        lblTransComp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTransComp.setText("-");
        pnlDashboard.add(lblTransComp, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 700, 90, 70));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);

        tblDashboard.setBackground(new java.awt.Color(255, 255, 255));
        tblDashboard.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Order ID", "Customer", "Total", "Order Type", "Status", "Date"
            }
        ));
        jScrollPane2.setViewportView(tblDashboard);

        pnlDashboard.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, 930, 320));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/A.png"))); // NOI18N
        pnlDashboard.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1320, 810));

        jPanel2.add(pnlDashboard, "dashboard");

        pnlProduct.setBackground(new java.awt.Color(255, 255, 255));
        pnlProduct.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlAddPr.setBackground(new java.awt.Color(255, 255, 255));
        pnlAddPr.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtAddName.setBackground(new java.awt.Color(255, 255, 255));
        txtAddName.setForeground(new java.awt.Color(0, 0, 0));
        txtAddName.setBorder(null);
        pnlAddPr.add(txtAddName, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 380, 30));

        txtAddCategory.setBackground(new java.awt.Color(255, 255, 255));
        txtAddCategory.setForeground(new java.awt.Color(0, 0, 0));
        txtAddCategory.setBorder(null);
        pnlAddPr.add(txtAddCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 380, 30));

        txtxAddDescrip.setBackground(new java.awt.Color(255, 255, 255));
        txtxAddDescrip.setForeground(new java.awt.Color(0, 0, 0));
        txtxAddDescrip.setBorder(null);
        txtxAddDescrip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtxAddDescripActionPerformed(evt);
            }
        });
        pnlAddPr.add(txtxAddDescrip, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 390, 90));

        txtAddPrice.setBackground(new java.awt.Color(255, 255, 255));
        txtAddPrice.setForeground(new java.awt.Color(0, 0, 0));
        txtAddPrice.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAddPrice.setBorder(null);
        pnlAddPr.add(txtAddPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 460, 130, 30));

        txtAddStock.setBackground(new java.awt.Color(255, 255, 255));
        txtAddStock.setForeground(new java.awt.Color(0, 0, 0));
        txtAddStock.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAddStock.setBorder(null);
        pnlAddPr.add(txtAddStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 460, 140, 30));

        btnAddCancel.setText("-");
        btnAddCancel.setBorder(null);
        btnAddCancel.setBorderPainted(false);
        btnAddCancel.setContentAreaFilled(false);
        btnAddCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCancelActionPerformed(evt);
            }
        });
        pnlAddPr.add(btnAddCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 560, 80, 40));

        btnAddSave.setText("-");
        btnAddSave.setBorder(null);
        btnAddSave.setBorderPainted(false);
        btnAddSave.setContentAreaFilled(false);
        pnlAddPr.add(btnAddSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 560, 80, 40));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Add Product.png"))); // NOI18N
        pnlAddPr.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, -4, 510, 690));

        pnlProduct.add(pnlAddPr, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, 510, 680));

        pnlRestock.setBackground(new java.awt.Color(255, 255, 255));
        pnlRestock.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtExpirationDate.setBackground(new java.awt.Color(255, 255, 255));
        txtExpirationDate.setForeground(new java.awt.Color(0, 0, 0));
        txtExpirationDate.setBorder(null);
        pnlRestock.add(txtExpirationDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 500, 370, -1));

        txtDateAdded.setBackground(new java.awt.Color(255, 255, 255));
        txtDateAdded.setForeground(new java.awt.Color(0, 0, 0));
        txtDateAdded.setBorder(null);
        pnlRestock.add(txtDateAdded, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 410, 370, 20));

        txtSupplier.setBackground(new java.awt.Color(255, 255, 255));
        txtSupplier.setForeground(new java.awt.Color(0, 0, 0));
        txtSupplier.setBorder(null);
        pnlRestock.add(txtSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 240, 370, 20));

        txtStatus.setBackground(new java.awt.Color(255, 255, 255));
        txtStatus.setForeground(new java.awt.Color(0, 0, 0));
        txtStatus.setBorder(null);
        pnlRestock.add(txtStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 330, 370, -1));

        txtPrice.setBackground(new java.awt.Color(255, 255, 255));
        txtPrice.setForeground(new java.awt.Color(0, 0, 0));
        txtPrice.setBorder(null);
        pnlRestock.add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 160, 370, 20));

        jTextField12.setBackground(new java.awt.Color(255, 255, 255));
        jTextField12.setForeground(new java.awt.Color(0, 0, 0));
        jTextField12.setBorder(null);
        pnlRestock.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 240, 370, 20));

        txtStock.setBackground(new java.awt.Color(255, 255, 255));
        txtStock.setForeground(new java.awt.Color(0, 0, 0));
        txtStock.setBorder(null);
        pnlRestock.add(txtStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 580, 370, -1));

        txtCategory.setBackground(new java.awt.Color(255, 255, 255));
        txtCategory.setForeground(new java.awt.Color(0, 0, 0));
        txtCategory.setBorder(null);
        pnlRestock.add(txtCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 500, 370, -1));

        txtDescription.setBackground(new java.awt.Color(255, 255, 255));
        txtDescription.setForeground(new java.awt.Color(0, 0, 0));
        txtDescription.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDescription.setBorder(null);
        pnlRestock.add(txtDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 330, 370, 100));

        txtName.setBackground(new java.awt.Color(255, 255, 255));
        txtName.setForeground(new java.awt.Color(0, 0, 0));
        txtName.setBorder(null);
        pnlRestock.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 370, 20));

        txtProductID.setBackground(new java.awt.Color(255, 255, 255));
        txtProductID.setForeground(new java.awt.Color(0, 0, 0));
        txtProductID.setBorder(null);
        pnlRestock.add(txtProductID, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 370, -1));

        btnBack.setText("-");
        btnBack.setBorderPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        pnlRestock.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 740, 130, 30));

        btnSaveRestock.setText("-");
        btnSaveRestock.setBorderPainted(false);
        btnSaveRestock.setContentAreaFilled(false);
        btnSaveRestock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveRestockActionPerformed(evt);
            }
        });
        pnlRestock.add(btnSaveRestock, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 743, 130, 30));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Restock.png"))); // NOI18N
        jLabel21.setToolTipText("");
        pnlRestock.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, -1, -1));

        pnlProduct.add(pnlRestock, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 800));

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnDelete.setBackground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("-");
        btnDelete.setBorder(null);
        btnDelete.setBorderPainted(false);
        btnDelete.setContentAreaFilled(false);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel7.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 740, 80, 30));

        btnRestock.setBackground(new java.awt.Color(255, 255, 255));
        btnRestock.setText("-");
        btnRestock.setBorder(null);
        btnRestock.setBorderPainted(false);
        btnRestock.setContentAreaFilled(false);
        btnRestock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestockActionPerformed(evt);
            }
        });
        jPanel7.add(btnRestock, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 740, 80, 30));

        btnAddProduct.setBackground(new java.awt.Color(255, 255, 255));
        btnAddProduct.setText("-");
        btnAddProduct.setBorder(null);
        btnAddProduct.setBorderPainted(false);
        btnAddProduct.setContentAreaFilled(false);
        btnAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProductActionPerformed(evt);
            }
        });
        jPanel7.add(btnAddProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 740, 160, 30));

        btnPrUpdate1.setBackground(new java.awt.Color(255, 255, 255));
        btnPrUpdate1.setText("-");
        btnPrUpdate1.setBorder(null);
        btnPrUpdate1.setBorderPainted(false);
        btnPrUpdate1.setContentAreaFilled(false);
        jPanel7.add(btnPrUpdate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 740, 80, 30));

        txtSearch.setBackground(new java.awt.Color(255, 255, 255));
        txtSearch.setBorder(null);
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });
        jPanel7.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 116, 380, 20));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);

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
        jScrollPane1.setViewportView(tblProducts);

        jPanel7.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 930, 500));

        cmbStatusFilter.setBackground(new java.awt.Color(255, 255, 255));
        cmbStatusFilter.setForeground(new java.awt.Color(51, 51, 51));
        cmbStatusFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Status:", "Available", "Out of Stock", "Discontinued" }));
        cmbStatusFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStatusFilterActionPerformed(evt);
            }
        });
        jPanel7.add(cmbStatusFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 110, 210, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/AdProduct.png"))); // NOI18N
        jPanel7.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1320, -1));

        pnlProduct.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1310, 790));

        jPanel2.add(pnlProduct, "product");

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 1, 1320, 840));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
       setActive("dashboard");
       loadDashboardOrders();
        CardLayout cl = (CardLayout)(jPanel2.getLayout());
            cl.show(jPanel2, "dashboard");
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsersActionPerformed
        setActive("users");
        CardLayout cl = (CardLayout)(jPanel2.getLayout());
            cl.show(jPanel2, "users");
    }//GEN-LAST:event_btnUsersActionPerformed

    private void btnOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdersActionPerformed
        setActive("order");
        pnlverufy.setVisible(false);
        pnlView.setVisible(false);
        CardLayout cl = (CardLayout)(jPanel2.getLayout());
            cl.show(jPanel2, "order");
    }//GEN-LAST:event_btnOrdersActionPerformed

    private void btnProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductsActionPerformed
        setActive("product");
        pnlAddPr.setVisible(false);
        pnlRestock.setVisible(false);
        CardLayout cl = (CardLayout)(jPanel2.getLayout());
            cl.show(jPanel2, "product");
        
    }//GEN-LAST:event_btnProductsActionPerformed

    private void btnSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierActionPerformed
        setActive("supplier");
        pnlAddSuppliers.setVisible(false);
        CardLayout cl = (CardLayout)(jPanel2.getLayout());
            cl.show(jPanel2, "supplier");
    }//GEN-LAST:event_btnSupplierActionPerformed
    private static final System.Logger LOG = System.getLogger(AdminDashboard.class.getName());

    private void btnReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportsActionPerformed
        setActive("reports");
        CardLayout cl = (CardLayout)(jPanel2.getLayout());
            cl.show(jPanel2, "reports");
    }//GEN-LAST:event_btnReportsActionPerformed

    private void btnAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProductActionPerformed
        pnlAddPr.setVisible(true);
        pnlProduct.setVisible(false);
        pnlRestock.setVisible(false);
    }//GEN-LAST:event_btnAddProductActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
       startUp log = new startUp();
        log.setVisible(true);
        log.pack();
        log.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void txtxAddDescripActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtxAddDescripActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtxAddDescripActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
       pnlAddSuppliers.setVisible(true);
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnStaffAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStaffAddActionPerformed
       pnlAddStaff.setVisible(true);
    }//GEN-LAST:event_btnStaffAddActionPerformed

    private void btnAddCancel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCancel2ActionPerformed
        pnlUsers.setVisible(true);
        pnlAddStaff.setVisible(false);
    }//GEN-LAST:event_btnAddCancel2ActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        pnlSuppliers.setVisible(true);
        pnlAddSuppliers.setVisible(false);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnAddCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCancelActionPerformed
        pnlAddPr.setVisible(false);
        pnlProduct.setVisible(true);
    }//GEN-LAST:event_btnAddCancelActionPerformed

    private void btnOrdCancActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdCancActionPerformed
        pnlOrder.setVisible(true);
        pnlverufy.setVisible(false);
    }//GEN-LAST:event_btnOrdCancActionPerformed

    private void btnOrdCanc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdCanc1ActionPerformed
        pnlView.setVisible(false);
        pnlOrder.setVisible(true);
    }//GEN-LAST:event_btnOrdCanc1ActionPerformed

    private void btnStaffAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStaffAdd1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStaffAdd1ActionPerformed

    private void txtNameeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameeActionPerformed

    private void btnOrdVerifyPay1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdVerifyPay1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOrdVerifyPay1ActionPerformed

    private void btnOrdView1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdView1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOrdView1ActionPerformed

    private void btnOrdViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdViewActionPerformed
        pnlView.setVisible(true);
    }//GEN-LAST:event_btnOrdViewActionPerformed

    private void btnRestockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestockActionPerformed

        int selectedRow = tblProducts.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a product first.");
        return;
    }

    txtProductID.setText(tblProducts.getValueAt(selectedRow, 0).toString());
    txtName.setText(tblProducts.getValueAt(selectedRow, 1).toString());
    txtDescription.setText(tblProducts.getValueAt(selectedRow, 2).toString());
    txtCategory.setText(tblProducts.getValueAt(selectedRow, 3).toString());
    txtStock.setText(tblProducts.getValueAt(selectedRow, 4).toString());
    txtPrice.setText(tblProducts.getValueAt(selectedRow, 5).toString());
    txtSupplier.setText(tblProducts.getValueAt(selectedRow, 6).toString());
    txtStatus.setText(tblProducts.getValueAt(selectedRow, 7).toString());
    txtDateAdded.setText(tblProducts.getValueAt(selectedRow, 8).toString());
    txtExpirationDate.setText(tblProducts.getValueAt(selectedRow, 9).toString());

    txtAddStock.setText("");

    // restockPanel.setVisible(true);
    // productsPanel.setVisible(false);

        pnlRestock.setVisible(true);
        jPanel7.setVisible(false);
        jPanel1.setVisible(false);
    }//GEN-LAST:event_btnRestockActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        pnlRestock.setVisible(false);
        jPanel1.setVisible(true);
        jPanel7.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        refreshProductsTable();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void cmbStatusFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStatusFilterActionPerformed
        // TODO add your handling code here:
        refreshProductsTable();

    }//GEN-LAST:event_cmbStatusFilterActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int selectedRow = tblProducts.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a product first.");
        return;
    }

    int productId = Integer.parseInt(tblProducts.getValueAt(selectedRow, 0).toString());
    String productName = tblProducts.getValueAt(selectedRow, 1).toString();

    int confirm = JOptionPane.showConfirmDialog(
        this,
        "Mark \"" + productName + "\" as Discontinued?",
        "Confirm",
        JOptionPane.YES_NO_OPTION
    );

    if (confirm == JOptionPane.YES_OPTION) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "UPDATE products SET stock = 0, status = 'Discontinued' WHERE product_id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, productId);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Product marked as Discontinued.");
            refreshProductsTable();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSaveRestockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveRestockActionPerformed
        // TODO add your handling code here:

    try {
        if (conn == null) {
            JOptionPane.showMessageDialog(this, "Database not connected!");
            return;
        }

        String ProductId = txtProductID.getText(); 

        String selectSQL = "SELECT stock FROM items WHERE order_id = ?";
        PreparedStatement pstSelect = conn.prepareStatement(selectSQL);
        pstSelect.setString(1, ProductId);
        ResultSet rs = pstSelect.executeQuery();

        if (rs.next()) {
            int currentStock = rs.getInt("stock");
            int newStock = currentStock + 1; 

            String updateSQL = "UPDATE items SET stock = ? WHERE order_id = ?";
            PreparedStatement pstUpdate = conn.prepareStatement(updateSQL);
            pstUpdate.setInt(1, newStock);
            pstUpdate.setString(2, ProductId);
            pstUpdate.executeUpdate();

            txtStock.setText(String.valueOf(newStock));
            JOptionPane.showMessageDialog(this, "Stock successfully added!");
        } else {
            JOptionPane.showMessageDialog(this, "Item not found!");
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
    }

        
        

    }//GEN-LAST:event_btnSaveRestockActionPerformed

    private void tabbedpaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabbedpaneStateChanged
        // TODO add your handling code here:
        updateStatusFilterOptions();
        refreshUsersTable();

    }//GEN-LAST:event_tabbedpaneStateChanged

    private void txtSearchUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchUsersActionPerformed
        // TODO add your handling code here:
        refreshUsersTable();
    }//GEN-LAST:event_txtSearchUsersActionPerformed

    private void txtSearchUsersKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchUsersKeyReleased
        // TODO add your handling code here:
        refreshUsersTable();
    }//GEN-LAST:event_txtSearchUsersKeyReleased

    private void cmbStatusFilterrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStatusFilterrActionPerformed
        // TODO add your handling code here:
       refreshUsersTable();
    }//GEN-LAST:event_cmbStatusFilterrActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String selected = jComboBox1.getSelectedItem().toString();
        
        if (selected.equals("Customer")){
            tblPaneCustomer.setVisible(true);
            tblPaneProduct.setVisible(false);
        }else if (selected.equals("Product")){
            tblPaneProduct.setVisible(true);
            tblPaneCustomer.setVisible(false);
    }//GEN-LAST:event_jComboBox1ActionPerformed
    }
    private void btnAddSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSupplierActionPerformed
        // TODO add your handling code here:
    String name = txtNamee.getText();
    String contactPerson = txtContactPerson.getText();
    String contactNo = txtContactNo.getText();
    String email = txtEmail.getText();

    try {
        Connection con = DBConnection.getConnection();
        String sql = "INSERT INTO suppliers (name, contact_person, contact_no, email) VALUES (?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, name);
        pst.setString(2, contactPerson);
        pst.setString(3, contactNo);
        pst.setString(4, email);

        pst.executeUpdate();

        loadSuppliers("", "All");
        JOptionPane.showMessageDialog(this, "Supplier Added!");


    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
     
    }//GEN-LAST:event_btnAddSupplierActionPerformed

    private void cmbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStatusActionPerformed
        // TODO add your handling code here:
        refreshSuppliersTable();
    }//GEN-LAST:event_cmbStatusActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:

    int row = tblSuppliers.getSelectedRow();

    if (row == -1) {
        JOptionPane.showMessageDialog(null, "Select a supplier first!");
        return;
    }

    SupplierDialog dialog = new SupplierDialog(this, true);

    dialog.setSupplierData(
        Integer.parseInt(tblSuppliers.getValueAt(row, 0).toString()),
        tblSuppliers.getValueAt(row, 1).toString(),
        tblSuppliers.getValueAt(row, 2).toString(),
        tblSuppliers.getValueAt(row, 3).toString(),
        tblSuppliers.getValueAt(row, 4).toString(),
        tblSuppliers.getValueAt(row, 5).toString()
    );

    dialog.setVisible(true);

    refreshSuppliersTable();

        
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteeActionPerformed
        // TODO add your handling code here:
 

    int row = tblSuppliers.getSelectedRow();

    if (row == -1) {
        JOptionPane.showMessageDialog(null, "Please select a supplier first!");
        return;
    }

    int supplierID = Integer.parseInt(tblSuppliers.getValueAt(row, 0).toString());

    try {

        int confirm = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to delete this supplier?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {

            Connection con = DBConnection.getConnection();

            String sql = "DELETE FROM suppliers WHERE supplier_id=?";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, supplierID);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Supplier deleted successfully!");

            refreshSuppliersTable();
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    }//GEN-LAST:event_btnDeleteeActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearchOrderKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchOrderKeyReleased
        // TODO add your handling code here:
        String search = txtSearchOrder.getText();
        Object selectedItem = cmbStatus1.getSelectedItem();
        String status = selectedItem == null ? "All" : selectedItem.toString();

        loadOrdersTable(status, search);
    }//GEN-LAST:event_txtSearchOrderKeyReleased

    private void txtSearchSupplierKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchSupplierKeyReleased
        // TODO add your handling code here:
        refreshSuppliersTable();
    }//GEN-LAST:event_txtSearchSupplierKeyReleased
    
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
        java.awt.EventQueue.invokeLater(() -> new AdminDashboard().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCancel;
    private javax.swing.JButton btnAddCancel2;
    private javax.swing.JButton btnAddProduct;
    private javax.swing.JButton btnAddSave;
    private javax.swing.JButton btnAddSave2;
    private javax.swing.JButton btnAddSupplier;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCusDel;
    private javax.swing.JButton btnCusDel1;
    private javax.swing.JButton btnCusEdit;
    private javax.swing.JButton btnCusEdit1;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDeletee;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnOrdCanc;
    private javax.swing.JButton btnOrdCanc1;
    private javax.swing.JButton btnOrdConf;
    private javax.swing.JButton btnOrdConf1;
    private javax.swing.JButton btnOrdDel1;
    private javax.swing.JButton btnOrdDel2;
    private javax.swing.JButton btnOrdDel3;
    private javax.swing.JButton btnOrdEdit2;
    private javax.swing.JButton btnOrdEdit3;
    private javax.swing.JButton btnOrdEdit4;
    private javax.swing.JButton btnOrdVerifyPay1;
    private javax.swing.JButton btnOrdView;
    private javax.swing.JButton btnOrdView1;
    private javax.swing.JButton btnOrders;
    private javax.swing.JButton btnPrUpdate1;
    private javax.swing.JButton btnProducts;
    private javax.swing.JButton btnReports;
    private javax.swing.JButton btnRestock;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSaveRestock;
    private javax.swing.JButton btnStaffAdd;
    private javax.swing.JButton btnStaffAdd1;
    private javax.swing.JButton btnStaffDel;
    private javax.swing.JButton btnStaffDel1;
    private javax.swing.JButton btnStaffEdit;
    private javax.swing.JButton btnStaffEdit1;
    private javax.swing.JButton btnSupplier;
    private javax.swing.JButton btnUsers;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JComboBox<String> cmbStatus1;
    private javax.swing.JComboBox<String> cmbStatusFilter;
    private javax.swing.JComboBox<String> cmbStatusFilterr;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox8;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTable jTable11;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JLabel lblLowStiock;
    private javax.swing.JLabel lblTotalCustomer;
    private javax.swing.JLabel lblTotalOrd;
    private javax.swing.JLabel lblTotalSales;
    private javax.swing.JLabel lblTransComp;
    private javax.swing.JLabel lblTransPend;
    private javax.swing.JPanel pnlAddPr;
    private javax.swing.JPanel pnlAddStaff;
    private javax.swing.JPanel pnlAddSuppliers;
    private javax.swing.JPanel pnlDashboard;
    private javax.swing.JPanel pnlOrder;
    private javax.swing.JPanel pnlProduct;
    private javax.swing.JPanel pnlReports;
    private javax.swing.JPanel pnlRestock;
    private javax.swing.JPanel pnlSuppliers;
    private javax.swing.JPanel pnlUsers;
    private javax.swing.JPanel pnlUsers1;
    private javax.swing.JPanel pnlUsers2;
    private javax.swing.JPanel pnlView;
    private javax.swing.JPanel pnlverufy;
    private javax.swing.JTabbedPane tabbedpane;
    private javax.swing.JTable tblCustomer;
    private javax.swing.JTable tblDashboard;
    private javax.swing.JTable tblOrder;
    private javax.swing.JScrollPane tblPaneCustomer;
    private javax.swing.JScrollPane tblPaneProduct;
    private javax.swing.JTable tblProducts;
    private javax.swing.JTable tblStaff;
    private javax.swing.JTable tblStaffs;
    private javax.swing.JTable tblSuppliers;
    private javax.swing.JTextField txtAddCategory;
    private javax.swing.JTextField txtAddName;
    private javax.swing.JTextField txtAddName2;
    private javax.swing.JTextField txtAddPrice;
    private javax.swing.JTextField txtAddStock;
    private javax.swing.JTextField txtAddSupCP1;
    private javax.swing.JTextField txtAddSupEm1;
    private javax.swing.JTextField txtCategory;
    private javax.swing.JTextField txtContactNo;
    private javax.swing.JTextField txtContactPerson;
    private javax.swing.JTextField txtDateAdded;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtExpirationDate;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNamee;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtProductID;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextArea txtSearchOrder;
    private javax.swing.JTextField txtSearchSupplier;
    private javax.swing.JTextField txtSearchUsers;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtSupplier;
    private javax.swing.JTextField txtxAddDescrip;
    // End of variables declaration//GEN-END:variables
}
