/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package adsfinalproject;

import adsfinalproject.DBConnection;
import adsfinalproject.startUp;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.CallableStatement;


/**
 *
 * @author kleir
 */
public class CustomerDashboard extends javax.swing.JFrame {
    ImageIcon darkIcon, homed,dish, order, pay, about, rice, bf, nd, dr, sn, di, ti, dei;
    ImageIcon lightIcon, homel,dishd, orderd, payd, aboutd,riced, bfd, ndd, drd, snd, dd, td, ded;
    boolean cartClicked = false;
    String orderType = "";
    String selectedPayment = "";
    DefaultTableModel cartModel;
    int selectedRowIndex = -1;
    double cartTotal = 0;
    String status = "";
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CustomerDashboard.class.getName());

    /**
     * Creates new form CustomerDashboard
     */
    public CustomerDashboard() {
                initComponents();
                textAmount.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    calculateChange();
                }
            });

            textAmount2.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    calculateChangeCC();
                }
            });
                tblCurrentOrders.addMouseListener(new java.awt.event.MouseAdapter() {
                    
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        int row = tblCurrentOrders.getSelectedRow();
                        if (selectedRowIndex == row) {
                            tblCurrentOrders.clearSelection();
                            selectedRowIndex = -1;
                        } else {
                            selectedRowIndex = row;
                        }
                    }
                });
                DefaultTableModel currentOrderModel = new DefaultTableModel(
                        new Object[]{"Name", "Qty", "Price", "Total"}, 0
                ) {
                    @Override
                    
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tblCurrentOrders.setModel(currentOrderModel);
                cartModel = new DefaultTableModel(
                        new Object[]{"Name", "Price", "Qty", "Total"}, 0
                ) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tblCart.setModel(cartModel);
                

        setSize(1318, 847);
        setLocationRelativeTo(null);        
        menuRice.setBorder(null);
        btnCart.setIcon(darkIcon);
        btnCart.setVisible(true);
        pnlCart.setVisible(false);
        
        //==//
        btnDish.setContentAreaFilled(false);
        btnHome.setContentAreaFilled(false);
        btnCart.setContentAreaFilled(false);
        btnAbout.setContentAreaFilled(false);
        btnOrder.setContentAreaFilled(false);
        btnPayment.setContentAreaFilled(false);
        btnRice.setContentAreaFilled(false);
        btnNoodles.setContentAreaFilled(false);
        btnBreakfast.setContentAreaFilled(false);
        btnSnacks.setContentAreaFilled(false);
        btnDrinks.setContentAreaFilled(false);
        btnDelivery.setContentAreaFilled(false);
        btnDine.setContentAreaFilled(false);
        btnTakeOut.setContentAreaFilled(false);
        
        
        //-----//
        jPanel1.setLayout(new CardLayout());
        
        jPanel1.add(pnlWelcome, "home");
        jPanel1.add(pnlDish, "dish");
        jPanel1.add(pnlOrders, "orders");
        jPanel1.add(pnlPayment, "pay");
        jPanel1.add(pnlAbout, "about");
        
        CardLayout cl = (CardLayout)(jPanel1.getLayout());
        cl.show(jPanel1, "home");
 
       
        
        //====//
        darkIcon = new ImageIcon(getClass().getResource("/design/dAdd.png"));
        lightIcon = new ImageIcon(getClass().getResource("/design/lAdd.png"));
        homed = new ImageIcon(getClass().getResource("/design/homel.png"));
        homel = new ImageIcon(getClass().getResource("/design/homec.png"));
        dish = new ImageIcon(getClass().getResource("/design/lDish.png"));
        dishd = new ImageIcon(getClass().getResource("/design/Ddish.png"));
        order = new ImageIcon(getClass().getResource("/design/LOrder.png"));
        orderd = new ImageIcon(getClass().getResource("/design/dOrder.png"));
        pay = new ImageIcon(getClass().getResource("/design/lPAy.png"));
        payd = new ImageIcon(getClass().getResource("/design/dPay.png"));
        about = new ImageIcon(getClass().getResource("/design/lAbout.png"));
        aboutd = new ImageIcon(getClass().getResource("/design/dAbout.png"));
        rice = new ImageIcon(getClass().getResource("/design/Arice.png"));
        riced = new ImageIcon(getClass().getResource("/design/btnRice.png"));
        nd = new ImageIcon(getClass().getResource("/design/Apasta.png"));
        ndd = new ImageIcon(getClass().getResource("/design/btnNoodles.png"));
        sn = new ImageIcon(getClass().getResource("/design/Asnacks.png"));
        snd = new ImageIcon(getClass().getResource("/design/btnSnacks.png"));
        bf = new ImageIcon(getClass().getResource("/design/Abf.png"));
        bfd = new ImageIcon(getClass().getResource("/design/btnBf.png"));
        dr = new ImageIcon(getClass().getResource("/design/Adrinks.png"));
        drd = new ImageIcon(getClass().getResource("/design/btnDrinks.png"));
        di = new ImageIcon(getClass().getResource("/design/Dine-in.png"));
        dd = new ImageIcon(getClass().getResource("/design/dineind.png"));
        ti = new ImageIcon(getClass().getResource("/design/Take-out.png"));
        td = new ImageIcon(getClass().getResource("/design/totD.png"));
        dei = new ImageIcon(getClass().getResource("/design/Delivery.png"));
        ded = new ImageIcon(getClass().getResource("/design/deld.png"));
        
    }
    
    private void setActive(String page){
        btnHome.setIcon(homed);
        btnDish.setIcon(dish);
        btnOrder.setIcon(order);
        btnPayment.setIcon(pay);
        btnAbout.setIcon(about);
       
        
        if(page.equals("home")){
            btnHome.setIcon(homel);
        }else if (page.equals("dish")){
            btnDish.setIcon(dishd);
        }else if (page.equals("orders")){
            btnOrder.setIcon(orderd);
        }else if (page.equals("pay")){
            btnPayment.setIcon(payd);
        }else if (page.equals("about")){
            btnAbout.setIcon(aboutd);
        }
            CardLayout cl = (CardLayout)(jPanel1.getLayout());
            cl.show(jPanel1, "home");
    
    }
    private void setCategory(String cat){
        
            btnRice.setIcon(rice);
            btnNoodles.setIcon(nd);
            btnBreakfast.setIcon(bf);
            btnSnacks.setIcon(sn);
            btnDrinks.setIcon(dr);

            if(cat.equals("rice")){
                btnRice.setIcon(riced);
            }else if (cat.equals("noodles")){
                btnNoodles.setIcon(ndd);
            }else if (cat.equals("bf")){
                btnBreakfast.setIcon(bfd);
            }else if (cat.equals("snacks")){
                btnSnacks.setIcon(snd);
            }else if (cat.equals("drinks")){
                btnDrinks.setIcon(drd);
        CardLayout cl = (CardLayout)(Dish.getLayout());
        cl.show(Dish, cat);
            }
    }
        private void setOrderType(JButton select){
            btnDine.setIcon(di);
            btnTakeOut.setIcon(ti);
            btnDelivery.setIcon(dei);
            
            if(select == btnDine){
                btnDine.setIcon(dd);
                orderType = "Dine-in";
            }else if(select == btnTakeOut){
                btnTakeOut.setIcon(td);
                orderType = "Take-out";
            }else if(select == btnDelivery){
                btnDelivery.setIcon(ded);
                orderType = "Delivery";
            }
        }
        
        private void addToCart(String itemName, double price) {
            int qty = 1;
            boolean found = false;
            
            for (int i = 0; i < cartModel.getRowCount(); i++) {
                String existingItem = cartModel.getValueAt(i, 0).toString();
                
                if (existingItem.equals(itemName)) {
                    int confirm = JOptionPane.showConfirmDialog(
                            this,
                            itemName + " is already in cart.\nAdd more quantity?",
                            "Confirm",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (confirm == JOptionPane.YES_OPTION) {
                        int existingQty = Integer.parseInt(cartModel.getValueAt(i, 2).toString());
                        existingQty++;
                        
                        cartModel.setValueAt(existingQty, i, 2);
                        cartModel.setValueAt(existingQty * price, i, 3);
                    }
                    found = true;
                    break;
                }
            }
            
            if (!found) {
                cartModel.addRow(new Object[]{
                    itemName, price, qty, price
                });
                
                JOptionPane.showMessageDialog(
                        this,
                        itemName + " added to cart!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
            updateCartTotal();
        }
        
        private void updateCartTotal() {
            cartTotal = 0;

                for (int i = 0; i < cartModel.getRowCount(); i++) {
                    Object val = cartModel.getValueAt(i, 3);
                    if (val != null) cartTotal += Double.parseDouble(val.toString());
                }

                lblTotal.setText(String.format("%.2f", cartTotal));
            }
        private void populateOrderTables() {
        DefaultTableModel currentOrderModel = (DefaultTableModel) tblCurrentOrders.getModel();

        currentOrderModel.setRowCount(0);

        DefaultTableModel summaryModel = new DefaultTableModel(
            new Object[]{"Name", "Qty", "Price", "Total"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (int i = 0; i < cartModel.getRowCount(); i++) {
            Object name = cartModel.getValueAt(i, 0);
            Object price = cartModel.getValueAt(i, 1);
            Object qty = cartModel.getValueAt(i, 2);
            Object total = cartModel.getValueAt(i, 3);

            currentOrderModel.addRow(new Object[]{name, qty, price, total});
            summaryModel.addRow(new Object[]{name, qty, price, total});
        }

        tblSummary.setModel(summaryModel);
    }
        private boolean validateOrderAndPayment() {
        if (orderType.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select an order type!");
            return false;
        }

        if (selectedPayment.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a payment method!");
            return false;
        }

        JOptionPane.showMessageDialog(this,
            "Order Type: " + orderType + "\nPayment Method: " + selectedPayment,
            "Confirm Order",
            JOptionPane.INFORMATION_MESSAGE
        );

        return true;
    }
        private void setupCustomerFields() {
            if (orderType.equals("Delivery")) {
                textAddress.setEnabled(true); 
            } else {
                textAddress.setEnabled(false); 
                textAddress.setText("");
            }
        }
        
        private void setupOrderDetails() {
            java.time.LocalDateTime now = java.time.LocalDateTime.now();
            java.time.format.DateTimeFormatter formatter =
                    java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");

            String formattedDate = now.format(formatter);
            String orderNum = "ORD" + System.currentTimeMillis();

            lblDateTime.setText(formattedDate);
            lblOrderNum.setText(orderNum);

            lblDateTime1.setText(formattedDate);
            lblDateTime2.setText(formattedDate);

            lblOrderNum1.setText(orderNum);
            lblOrderNum2.setText(orderNum);

            String total = lblTotal.getText();

            lblTotal3.setText(total);
            lblTotal2.setText(total);
            lblTotal3.setText(total);

            DefaultTableModel model = (DefaultTableModel) tblSummary.getModel();

            tblSummary.setModel(model);
            tblSummary2.setModel(model);
            tblSummery3.setModel(model);
    }
        private void refreshOrderTables() {
            DefaultTableModel currentModel = (DefaultTableModel) tblCurrentOrders.getModel();
            DefaultTableModel summaryModel = (DefaultTableModel) tblSummary.getModel();
            
            currentModel.setRowCount(0);
            summaryModel.setRowCount(0);
            
            for (int i = 0; i < cartModel.getRowCount(); i++) {
                Object name = cartModel.getValueAt(i, 0);
                Object price = cartModel.getValueAt(i, 1);
                Object qty = cartModel.getValueAt(i, 2);
                Object total = cartModel.getValueAt(i, 3);
                
                currentModel.addRow(new Object[]{name, qty, price, total});
                summaryModel.addRow(new Object[]{name, qty, price, total});
        }
        }
        private void calculateChange() {
            try {

                if (textAmount.getText().trim().isEmpty()) {
                    lblChange.setText("0.00");
                    lblChange1.setText("0.00");
                    return;
                }

                double total = Double.parseDouble(lblTotal.getText().trim());
                double paid = Double.parseDouble(textAmount.getText().trim());

                double change = paid - total;

                if (change < 0) {
                    change = 0;
                }

                setChangeLabels(change);

            } catch (NumberFormatException e) {
                lblChange.setText("0.00");
                lblChange1.setText("0.00");
            }
        }
        private void calculateChangeCC() {
            try {

                if (textAmount2.getText().trim().isEmpty()) {
                    lblChange.setText("0.00");
                    lblChange1.setText("0.00");
                    return;
                }

                double total = Double.parseDouble(lblTotal.getText().trim());
                double paid = Double.parseDouble(textAmount2.getText().trim());

                double change = paid - total;

                if (change < 0) {
                    change = 0;
                }

                setChangeLabels(change);

            } catch (NumberFormatException e) {
                lblChange.setText("0.00");
                lblChange1.setText("0.00");
            }
        }
        
 
    public void saveCustomerContactNo(String username, String contactNo) {
    try {
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE users SET contact_no = ?, status = ? WHERE username = ?";
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, contactNo);
        pst.setString(2, "Active");
        pst.setString(3, username);

        int rowsUpdated = pst.executeUpdate();

        pst.close();
        conn.close();

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error saving customer info: " + e.getMessage());
    }

}
    private void insertPayment(String paymentType) {
    try {
        Connection conn = DBConnection.getConnection();
        
        String getOrder = "SELECT MAX(order_id) FROM tblorder";
        PreparedStatement pstOrder = conn.prepareStatement(getOrder);
        ResultSet rs = pstOrder.executeQuery();

        int orderId = 0;
        if (rs.next()) orderId = rs.getInt(1);

        String totalStr = lblTotal.getText().replace("₱","").trim();
        double totalAmount = Double.parseDouble(totalStr);

        String status = paymentType.equals("CASH") ? "PENDING" : "PAID";

        String sql = "INSERT INTO tblpayment (order_id, total_amount, payment_date, payment_type, status) "
                   + "VALUES (?, ?, NOW(), ?, ?)";

        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, orderId);
        pst.setDouble(2, totalAmount);
        pst.setString(3, paymentType);
        pst.setString(4, status);

        pst.executeUpdate();

        pst.close();
        conn.close();

        JOptionPane.showMessageDialog(this, "Payment Recorded!");

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Payment Error: " + e.getMessage());
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

        jPanel3 = new javax.swing.JPanel();
        btnLogout = new javax.swing.JButton();
        btnAbout = new javax.swing.JButton();
        btnPayment = new javax.swing.JButton();
        btnOrder = new javax.swing.JButton();
        btnDish = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        pnlAbout = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        pnlWelcome = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pnlPayment = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        lblEPayMethod = new javax.swing.JLabel();
        lblChange1 = new javax.swing.JLabel();
        lblTotal2 = new javax.swing.JLabel();
        lblOrderNum2 = new javax.swing.JLabel();
        lblOrderStat2 = new javax.swing.JLabel();
        lblDateTime2 = new javax.swing.JLabel();
        textAmount = new javax.swing.JTextField();
        textRefNum = new javax.swing.JTextField();
        textAccName = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblSummary2 = new javax.swing.JTable();
        btneWalletPay = new javax.swing.JButton();
        jLabel48 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSummery3 = new javax.swing.JTable();
        lblCcPay = new javax.swing.JLabel();
        lblChange = new javax.swing.JLabel();
        lblTotal3 = new javax.swing.JLabel();
        lblOrderNum1 = new javax.swing.JLabel();
        lblOrderStat1 = new javax.swing.JLabel();
        lblDateTime1 = new javax.swing.JLabel();
        textAmount2 = new javax.swing.JTextField();
        textCCV = new javax.swing.JTextField();
        textExDate = new javax.swing.JTextField();
        textCardNum = new javax.swing.JTextField();
        textCardName = new javax.swing.JTextField();
        btnCcPay = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        pnlOrders = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSummary = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCurrentOrders = new javax.swing.JTable();
        lblTotal = new javax.swing.JLabel();
        lblOrderStat = new javax.swing.JLabel();
        lblDateTime = new javax.swing.JLabel();
        lblOrderNum = new javax.swing.JLabel();
        textAddress = new javax.swing.JTextField();
        txtContactNo = new javax.swing.JTextField();
        textUserName = new javax.swing.JTextField();
        btnProceedPayment = new javax.swing.JButton();
        lblOrderType = new javax.swing.JLabel();
        lblPayMethod = new javax.swing.JLabel();
        btnAddQuantity = new javax.swing.JButton();
        btnRemoveOrder = new javax.swing.JButton();
        btnSubQuantity = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        pnlDish = new javax.swing.JPanel();
        btnCart = new javax.swing.JButton();
        pnlCart = new javax.swing.JPanel();
        btnPlaceOrder = new javax.swing.JButton();
        btnEWallet = new javax.swing.JButton();
        btnCash = new javax.swing.JButton();
        btnCC = new javax.swing.JButton();
        btnDelivery = new javax.swing.JButton();
        btnTakeOut = new javax.swing.JButton();
        btnDine = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblCart = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        Dish = new javax.swing.JPanel();
        snacks = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();
        ss43 = new javax.swing.JPanel();
        jButton36 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        lblShanghai = new javax.swing.JLabel();
        ss42 = new javax.swing.JPanel();
        jButton37 = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        lblCue = new javax.swing.JLabel();
        ss41 = new javax.swing.JPanel();
        jButton38 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        lblTogue = new javax.swing.JLabel();
        ss40 = new javax.swing.JPanel();
        jButton39 = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        lblSiomai = new javax.swing.JLabel();
        ss39 = new javax.swing.JPanel();
        jButton40 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        lblTuron = new javax.swing.JLabel();
        ss38 = new javax.swing.JPanel();
        jButton41 = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        lblBurger = new javax.swing.JLabel();
        drinks = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        ss44 = new javax.swing.JPanel();
        jButton29 = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        ss45 = new javax.swing.JPanel();
        jButton30 = new javax.swing.JButton();
        jLabel57 = new javax.swing.JLabel();
        lblSprite = new javax.swing.JLabel();
        ss46 = new javax.swing.JPanel();
        jButton31 = new javax.swing.JButton();
        jLabel58 = new javax.swing.JLabel();
        lblCoke = new javax.swing.JLabel();
        ss47 = new javax.swing.JPanel();
        jButton32 = new javax.swing.JButton();
        jLabel59 = new javax.swing.JLabel();
        lblRoyal = new javax.swing.JLabel();
        ss48 = new javax.swing.JPanel();
        jButton33 = new javax.swing.JButton();
        jLabel60 = new javax.swing.JLabel();
        lblBigcoke = new javax.swing.JLabel();
        ss49 = new javax.swing.JPanel();
        jButton34 = new javax.swing.JButton();
        jLabel61 = new javax.swing.JLabel();
        lblBigcokezero = new javax.swing.JLabel();
        ss50 = new javax.swing.JPanel();
        jButton35 = new javax.swing.JButton();
        jLabel62 = new javax.swing.JLabel();
        lblbigroyal = new javax.swing.JLabel();
        noodles = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        ss30 = new javax.swing.JPanel();
        jButton23 = new javax.swing.JButton();
        jLabel63 = new javax.swing.JLabel();
        lblCalamansi = new javax.swing.JLabel();
        ss29 = new javax.swing.JPanel();
        jButton24 = new javax.swing.JButton();
        jLabel64 = new javax.swing.JLabel();
        lblSpicy = new javax.swing.JLabel();
        ss28 = new javax.swing.JPanel();
        jButton25 = new javax.swing.JButton();
        jLabel65 = new javax.swing.JLabel();
        lblChilimansi = new javax.swing.JLabel();
        ss27 = new javax.swing.JPanel();
        jButton26 = new javax.swing.JButton();
        jLabel66 = new javax.swing.JLabel();
        lblspag = new javax.swing.JLabel();
        ss26 = new javax.swing.JPanel();
        jButton27 = new javax.swing.JButton();
        jLabel67 = new javax.swing.JLabel();
        lblpancit = new javax.swing.JLabel();
        ss25 = new javax.swing.JPanel();
        jButton28 = new javax.swing.JButton();
        jLabel68 = new javax.swing.JLabel();
        lblpalabok = new javax.swing.JLabel();
        bfMeals = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        ss22 = new javax.swing.JPanel();
        ss23 = new javax.swing.JPanel();
        jButton17 = new javax.swing.JButton();
        jLabel69 = new javax.swing.JLabel();
        lblGarlic = new javax.swing.JLabel();
        ss20 = new javax.swing.JPanel();
        jButton18 = new javax.swing.JButton();
        jLabel70 = new javax.swing.JLabel();
        lblEgg = new javax.swing.JLabel();
        ss19 = new javax.swing.JPanel();
        jButton19 = new javax.swing.JButton();
        jLabel71 = new javax.swing.JLabel();
        lblDaing = new javax.swing.JLabel();
        ss18 = new javax.swing.JPanel();
        jButton20 = new javax.swing.JButton();
        jLabel72 = new javax.swing.JLabel();
        lblHotdog = new javax.swing.JLabel();
        ss17 = new javax.swing.JPanel();
        jButton21 = new javax.swing.JButton();
        jLabel73 = new javax.swing.JLabel();
        lblSweet = new javax.swing.JLabel();
        ss16 = new javax.swing.JPanel();
        jButton22 = new javax.swing.JButton();
        jLabel74 = new javax.swing.JLabel();
        lblHungarian = new javax.swing.JLabel();
        menuRice = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        ss15 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel75 = new javax.swing.JLabel();
        lblsweetsourtilapia = new javax.swing.JLabel();
        ss14 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel76 = new javax.swing.JLabel();
        lbltilapia = new javax.swing.JLabel();
        ss13 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel77 = new javax.swing.JLabel();
        lblNilagangManok = new javax.swing.JLabel();
        ss12 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel78 = new javax.swing.JLabel();
        lblBeefsinigang = new javax.swing.JLabel();
        ss11 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jLabel79 = new javax.swing.JLabel();
        lblLechon = new javax.swing.JLabel();
        ss10 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jLabel80 = new javax.swing.JLabel();
        lblBCaldereta = new javax.swing.JLabel();
        ss9 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel81 = new javax.swing.JLabel();
        lblPorksinigang = new javax.swing.JLabel();
        ss8 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jLabel82 = new javax.swing.JLabel();
        lblKarekare = new javax.swing.JLabel();
        ss7 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jLabel83 = new javax.swing.JLabel();
        lblMenudo = new javax.swing.JLabel();
        ss6 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jLabel84 = new javax.swing.JLabel();
        lblBicol = new javax.swing.JLabel();
        ss5 = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jLabel85 = new javax.swing.JLabel();
        lblAsado = new javax.swing.JLabel();
        ss4 = new javax.swing.JPanel();
        jButton12 = new javax.swing.JButton();
        jLabel86 = new javax.swing.JLabel();
        lblFriedChiken = new javax.swing.JLabel();
        ss3 = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();
        jLabel87 = new javax.swing.JLabel();
        lblBeefSteak = new javax.swing.JLabel();
        ss2 = new javax.swing.JPanel();
        jButton14 = new javax.swing.JButton();
        jLabel88 = new javax.swing.JLabel();
        lblTokwatBaboy = new javax.swing.JLabel();
        ss1 = new javax.swing.JPanel();
        jButton15 = new javax.swing.JButton();
        jLabel89 = new javax.swing.JLabel();
        lblbopis = new javax.swing.JLabel();
        ss = new javax.swing.JPanel();
        jButton16 = new javax.swing.JButton();
        jLabel90 = new javax.swing.JLabel();
        lblSarciado = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnDrinks = new javax.swing.JButton();
        btnSnacks = new javax.swing.JButton();
        btnBreakfast = new javax.swing.JButton();
        btnNoodles = new javax.swing.JButton();
        btnRice = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(23, 29, 37));
        jPanel3.setPreferredSize(new java.awt.Dimension(1080, 3));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLogout.setText("-");
        btnLogout.setBorder(null);
        btnLogout.setBorderPainted(false);
        btnLogout.setContentAreaFilled(false);
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        jPanel3.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1016, 6, 30, 30));

        btnAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/lAbout.png"))); // NOI18N
        btnAbout.setBorder(null);
        btnAbout.setBorderPainted(false);
        btnAbout.setContentAreaFilled(false);
        btnAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAboutActionPerformed(evt);
            }
        });
        jPanel3.add(btnAbout, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, -10, 90, 60));

        btnPayment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/lPay.png"))); // NOI18N
        btnPayment.setBorder(null);
        btnPayment.setBorderPainted(false);
        btnPayment.setContentAreaFilled(false);
        btnPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaymentActionPerformed(evt);
            }
        });
        jPanel3.add(btnPayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, 80, 40));

        btnOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Lorder.png"))); // NOI18N
        btnOrder.setBorder(null);
        btnOrder.setBorderPainted(false);
        btnOrder.setContentAreaFilled(false);
        btnOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderActionPerformed(evt);
            }
        });
        jPanel3.add(btnOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, 60, 40));

        btnDish.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/lDish.png"))); // NOI18N
        btnDish.setBorder(null);
        btnDish.setBorderPainted(false);
        btnDish.setContentAreaFilled(false);
        btnDish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDishActionPerformed(evt);
            }
        });
        jPanel3.add(btnDish, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 60, 40));

        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/homel.png"))); // NOI18N
        btnHome.setBorder(null);
        btnHome.setBorderPainted(false);
        btnHome.setContentAreaFilled(false);
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        jPanel3.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, -20, 60, 80));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/menu.png"))); // NOI18N
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-220, -22, 1320, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 1060, 50));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1340, 835));
        jPanel1.setPreferredSize(new java.awt.Dimension(1300, 800));
        jPanel1.setLayout(new java.awt.CardLayout());

        pnlAbout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/About.png"))); // NOI18N
        jLabel50.setToolTipText("");
        pnlAbout.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 800));

        jPanel1.add(pnlAbout, "about");

        pnlWelcome.setPreferredSize(new java.awt.Dimension(1300, 800));
        pnlWelcome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Customer Dashboard.png"))); // NOI18N
        jLabel2.setToolTipText("");
        pnlWelcome.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 800));

        jPanel1.add(pnlWelcome, "home");

        pnlPayment.setBackground(new java.awt.Color(255, 255, 255));
        pnlPayment.setLayout(new java.awt.CardLayout());

        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblEPayMethod.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblEPayMethod.setForeground(new java.awt.Color(0, 0, 0));
        lblEPayMethod.setText("payment method");
        jPanel11.add(lblEPayMethod, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 200, 50));

        lblChange1.setFont(new java.awt.Font("SimSun-ExtB", 1, 12)); // NOI18N
        lblChange1.setForeground(new java.awt.Color(0, 0, 0));
        lblChange1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblChange1.setText("-");
        jPanel11.add(lblChange1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 690, 100, 40));

        lblTotal2.setFont(new java.awt.Font("SimSun-ExtB", 1, 12)); // NOI18N
        lblTotal2.setForeground(new java.awt.Color(0, 0, 0));
        lblTotal2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTotal2.setText("-");
        jPanel11.add(lblTotal2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 680, 100, 30));

        lblOrderNum2.setFont(new java.awt.Font("SimSun-ExtB", 1, 12)); // NOI18N
        lblOrderNum2.setForeground(new java.awt.Color(0, 0, 0));
        lblOrderNum2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblOrderNum2.setText("-");
        jPanel11.add(lblOrderNum2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 370, 90, 30));

        lblOrderStat2.setFont(new java.awt.Font("SimSun-ExtB", 1, 12)); // NOI18N
        lblOrderStat2.setForeground(new java.awt.Color(0, 0, 0));
        lblOrderStat2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblOrderStat2.setText("-");
        jPanel11.add(lblOrderStat2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 380, 100, 50));

        lblDateTime2.setFont(new java.awt.Font("SimSun-ExtB", 1, 12)); // NOI18N
        lblDateTime2.setForeground(new java.awt.Color(0, 0, 0));
        lblDateTime2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDateTime2.setText("date & time");
        jPanel11.add(lblDateTime2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, 330, 20));

        textAmount.setBackground(new java.awt.Color(255, 255, 255));
        textAmount.setForeground(new java.awt.Color(0, 0, 0));
        textAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textAmountActionPerformed(evt);
            }
        });
        jPanel11.add(textAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 550, 130, 30));

        textRefNum.setBackground(new java.awt.Color(255, 255, 255));
        textRefNum.setForeground(new java.awt.Color(0, 0, 0));
        textRefNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textRefNumActionPerformed(evt);
            }
        });
        jPanel11.add(textRefNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 470, 390, 30));

        textAccName.setBackground(new java.awt.Color(255, 255, 255));
        textAccName.setForeground(new java.awt.Color(0, 0, 0));
        textAccName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textAccNameActionPerformed(evt);
            }
        });
        jPanel11.add(textAccName, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 390, 390, 30));

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

        jPanel11.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 440, 330, 220));

        btneWalletPay.setText("-");
        btneWalletPay.setBorder(null);
        btneWalletPay.setBorderPainted(false);
        btneWalletPay.setContentAreaFilled(false);
        btneWalletPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneWalletPayActionPerformed(evt);
            }
        });
        jPanel11.add(btneWalletPay, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 710, 420, 60));

        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/e-wallet Payment.png"))); // NOI18N
        jPanel11.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pnlPayment.add(jPanel11, "ewallet");

        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));

        tblSummery3.setBackground(new java.awt.Color(255, 255, 255));
        tblSummery3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tblSummery3);

        jPanel10.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 440, 330, 220));

        lblCcPay.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCcPay.setForeground(new java.awt.Color(0, 0, 0));
        lblCcPay.setText("payment method");
        jPanel10.add(lblCcPay, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 200, 50));

        lblChange.setFont(new java.awt.Font("SimSun-ExtB", 1, 12)); // NOI18N
        lblChange.setForeground(new java.awt.Color(0, 0, 0));
        lblChange.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblChange.setText("-");
        jPanel10.add(lblChange, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 690, 100, 40));

        lblTotal3.setFont(new java.awt.Font("SimSun-ExtB", 1, 12)); // NOI18N
        lblTotal3.setForeground(new java.awt.Color(0, 0, 0));
        lblTotal3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTotal3.setText("-");
        jPanel10.add(lblTotal3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 680, 100, 30));

        lblOrderNum1.setFont(new java.awt.Font("SimSun-ExtB", 1, 12)); // NOI18N
        lblOrderNum1.setForeground(new java.awt.Color(0, 0, 0));
        lblOrderNum1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblOrderNum1.setText("-");
        jPanel10.add(lblOrderNum1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 370, 90, 30));

        lblOrderStat1.setFont(new java.awt.Font("SimSun-ExtB", 1, 12)); // NOI18N
        lblOrderStat1.setForeground(new java.awt.Color(0, 0, 0));
        lblOrderStat1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblOrderStat1.setText("-");
        jPanel10.add(lblOrderStat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 380, 100, 50));

        lblDateTime1.setFont(new java.awt.Font("SimSun-ExtB", 1, 12)); // NOI18N
        lblDateTime1.setForeground(new java.awt.Color(0, 0, 0));
        lblDateTime1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDateTime1.setText("date & time");
        jPanel10.add(lblDateTime1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, 330, 20));

        textAmount2.setBackground(new java.awt.Color(255, 255, 255));
        textAmount2.setForeground(new java.awt.Color(0, 0, 0));
        textAmount2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textAmount2ActionPerformed(evt);
            }
        });
        jPanel10.add(textAmount2, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 640, 130, 30));

        textCCV.setBackground(new java.awt.Color(255, 255, 255));
        textCCV.setForeground(new java.awt.Color(0, 0, 0));
        textCCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCCVActionPerformed(evt);
            }
        });
        jPanel10.add(textCCV, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 570, 130, 30));

        textExDate.setBackground(new java.awt.Color(255, 255, 255));
        textExDate.setForeground(new java.awt.Color(0, 0, 0));
        textExDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textExDateActionPerformed(evt);
            }
        });
        jPanel10.add(textExDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 568, 130, 30));

        textCardNum.setBackground(new java.awt.Color(255, 255, 255));
        textCardNum.setForeground(new java.awt.Color(0, 0, 0));
        textCardNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCardNumActionPerformed(evt);
            }
        });
        jPanel10.add(textCardNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 500, 390, 30));

        textCardName.setBackground(new java.awt.Color(255, 255, 255));
        textCardName.setForeground(new java.awt.Color(0, 0, 0));
        textCardName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCardNameActionPerformed(evt);
            }
        });
        jPanel10.add(textCardName, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 430, 390, 30));

        btnCcPay.setText("-");
        btnCcPay.setBorder(null);
        btnCcPay.setBorderPainted(false);
        btnCcPay.setContentAreaFilled(false);
        btnCcPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCcPayActionPerformed(evt);
            }
        });
        jPanel10.add(btnCcPay, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 713, 420, 60));

        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/CC Payment.png"))); // NOI18N
        jPanel10.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pnlPayment.add(jPanel10, "cc");

        jPanel1.add(pnlPayment, "pay");

        pnlOrders.setBackground(new java.awt.Color(255, 255, 255));
        pnlOrders.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

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
        jScrollPane2.setViewportView(tblSummary);

        pnlOrders.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 350, 330, 230));

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));

        tblCurrentOrders.setBackground(new java.awt.Color(255, 255, 255));
        tblCurrentOrders.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tblCurrentOrders);

        pnlOrders.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 790, 270));

        lblTotal.setFont(new java.awt.Font("SimSun-ExtB", 1, 12)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(0, 0, 0));
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTotal.setText("-");
        pnlOrders.add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 590, 100, 40));

        lblOrderStat.setFont(new java.awt.Font("SimSun-ExtB", 1, 12)); // NOI18N
        lblOrderStat.setForeground(new java.awt.Color(0, 0, 0));
        lblOrderStat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblOrderStat.setText("-");
        pnlOrders.add(lblOrderStat, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 300, 100, 50));

        lblDateTime.setFont(new java.awt.Font("SimSun-ExtB", 1, 12)); // NOI18N
        lblDateTime.setForeground(new java.awt.Color(0, 0, 0));
        lblDateTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDateTime.setText("date & time");
        pnlOrders.add(lblDateTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 250, 310, -1));

        lblOrderNum.setFont(new java.awt.Font("SimSun-ExtB", 1, 12)); // NOI18N
        lblOrderNum.setForeground(new java.awt.Color(0, 0, 0));
        lblOrderNum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblOrderNum.setText("-");
        pnlOrders.add(lblOrderNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 280, 90, 40));

        textAddress.setBackground(new java.awt.Color(255, 255, 255));
        textAddress.setForeground(new java.awt.Color(0, 0, 0));
        textAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textAddressActionPerformed(evt);
            }
        });
        pnlOrders.add(textAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 600, 410, 30));

        txtContactNo.setBackground(new java.awt.Color(255, 255, 255));
        txtContactNo.setForeground(new java.awt.Color(0, 0, 0));
        txtContactNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContactNoActionPerformed(evt);
            }
        });
        pnlOrders.add(txtContactNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 560, 410, 30));

        textUserName.setBackground(new java.awt.Color(255, 255, 255));
        textUserName.setForeground(new java.awt.Color(0, 0, 0));
        pnlOrders.add(textUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 520, 410, 30));

        btnProceedPayment.setText("-");
        btnProceedPayment.setBorder(null);
        btnProceedPayment.setBorderPainted(false);
        btnProceedPayment.setContentAreaFilled(false);
        btnProceedPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProceedPaymentActionPerformed(evt);
            }
        });
        pnlOrders.add(btnProceedPayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 710, 420, 60));

        lblOrderType.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblOrderType.setForeground(new java.awt.Color(0, 0, 0));
        lblOrderType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOrderType.setText("-");
        pnlOrders.add(lblOrderType, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 710, 210, 60));

        lblPayMethod.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblPayMethod.setForeground(new java.awt.Color(0, 0, 0));
        lblPayMethod.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPayMethod.setText("-");
        pnlOrders.add(lblPayMethod, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 710, 200, 60));

        btnAddQuantity.setBackground(new java.awt.Color(255, 255, 255));
        btnAddQuantity.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/trash.png"))); // NOI18N
        btnAddQuantity.setBorder(null);
        btnAddQuantity.setBorderPainted(false);
        btnAddQuantity.setContentAreaFilled(false);
        btnAddQuantity.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAddQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddQuantityActionPerformed(evt);
            }
        });
        pnlOrders.add(btnAddQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 90, 60, 40));

        btnRemoveOrder.setBackground(new java.awt.Color(255, 255, 255));
        btnRemoveOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/trash.png"))); // NOI18N
        btnRemoveOrder.setBorder(null);
        btnRemoveOrder.setBorderPainted(false);
        btnRemoveOrder.setContentAreaFilled(false);
        btnRemoveOrder.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRemoveOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveOrderActionPerformed(evt);
            }
        });
        pnlOrders.add(btnRemoveOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(741, 93, 60, 40));

        btnSubQuantity.setBackground(new java.awt.Color(255, 255, 255));
        btnSubQuantity.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/trash.png"))); // NOI18N
        btnSubQuantity.setBorder(null);
        btnSubQuantity.setBorderPainted(false);
        btnSubQuantity.setContentAreaFilled(false);
        btnSubQuantity.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSubQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubQuantityActionPerformed(evt);
            }
        });
        pnlOrders.add(btnSubQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 100, 60, 40));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/currentorder.png"))); // NOI18N
        pnlOrders.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 800));

        jPanel1.add(pnlOrders, "orders");

        pnlDish.setBackground(new java.awt.Color(255, 255, 255));
        pnlDish.setPreferredSize(new java.awt.Dimension(1300, 800));
        pnlDish.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/lAdd.png"))); // NOI18N
        btnCart.setBorder(null);
        btnCart.setBorderPainted(false);
        btnCart.setContentAreaFilled(false);
        btnCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCartActionPerformed(evt);
            }
        });
        pnlDish.add(btnCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 100, 80));

        pnlCart.setBackground(new java.awt.Color(255, 255, 255));
        pnlCart.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnPlaceOrder.setText("-");
        btnPlaceOrder.setBorder(null);
        btnPlaceOrder.setBorderPainted(false);
        btnPlaceOrder.setContentAreaFilled(false);
        btnPlaceOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlaceOrderActionPerformed(evt);
            }
        });
        pnlCart.add(btnPlaceOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 720, 210, 40));

        btnEWallet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dwallet.png"))); // NOI18N
        btnEWallet.setBorder(null);
        btnEWallet.setBorderPainted(false);
        btnEWallet.setContentAreaFilled(false);
        btnEWallet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEWalletActionPerformed(evt);
            }
        });
        pnlCart.add(btnEWallet, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 620, 90, 50));

        btnCash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/cash.png"))); // NOI18N
        btnCash.setBorder(null);
        btnCash.setBorderPainted(false);
        btnCash.setContentAreaFilled(false);
        btnCash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCashActionPerformed(evt);
            }
        });
        pnlCart.add(btnCash, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 620, 90, 50));

        btnCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/cc.png"))); // NOI18N
        btnCC.setBorder(null);
        btnCC.setBorderPainted(false);
        btnCC.setContentAreaFilled(false);
        btnCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCCActionPerformed(evt);
            }
        });
        pnlCart.add(btnCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 620, 100, 50));

        btnDelivery.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Delivery.png"))); // NOI18N
        btnDelivery.setBorder(null);
        btnDelivery.setBorderPainted(false);
        btnDelivery.setContentAreaFilled(false);
        btnDelivery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeliveryActionPerformed(evt);
            }
        });
        pnlCart.add(btnDelivery, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 100, 50));

        btnTakeOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Take-Out.png"))); // NOI18N
        btnTakeOut.setBorder(null);
        btnTakeOut.setBorderPainted(false);
        btnTakeOut.setContentAreaFilled(false);
        btnTakeOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTakeOutActionPerformed(evt);
            }
        });
        pnlCart.add(btnTakeOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 100, 50));

        btnDine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Dine-in.png"))); // NOI18N
        btnDine.setBorder(null);
        btnDine.setBorderPainted(false);
        btnDine.setContentAreaFilled(false);
        btnDine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDineActionPerformed(evt);
            }
        });
        pnlCart.add(btnDine, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 100, 50));

        jScrollPane6.setBackground(new java.awt.Color(255, 255, 255));

        tblCart.setBackground(new java.awt.Color(255, 255, 255));
        tblCart.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(tblCart);

        pnlCart.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 260, 400));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/cart panel.png"))); // NOI18N
        pnlCart.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 800));

        pnlDish.add(pnlCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(964, 0, -1, 800));

        Dish.setBackground(new java.awt.Color(255, 255, 255));
        Dish.setLayout(new java.awt.CardLayout());

        snacks.setBackground(new java.awt.Color(255, 255, 255));
        snacks.setBorder(null);
        snacks.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        snacks.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new java.awt.GridLayout(0, 3, 20, 20));

        ss43.setBackground(new java.awt.Color(255, 255, 255));
        ss43.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton36.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton36.setForeground(new java.awt.Color(0, 0, 0));
        jButton36.setText("+");
        jButton36.setBorder(null);
        jButton36.setBorderPainted(false);
        jButton36.setContentAreaFilled(false);
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });
        ss43.add(jButton36, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel34.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("₱ 35");
        ss43.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 60, 40));

        lblShanghai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Snack 1.png"))); // NOI18N
        lblShanghai.setToolTipText("Crispy spring rolls with savory filling.");
        lblShanghai.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ss43.add(lblShanghai, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel8.add(ss43);

        ss42.setBackground(new java.awt.Color(255, 255, 255));
        ss42.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton37.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton37.setForeground(new java.awt.Color(0, 0, 0));
        jButton37.setText("+");
        jButton37.setBorder(null);
        jButton37.setBorderPainted(false);
        jButton37.setContentAreaFilled(false);
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });
        ss42.add(jButton37, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel35.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("₱ 25");
        ss42.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 60, 40));

        lblCue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Snack 2.png"))); // NOI18N
        lblCue.setToolTipText("Deep-fried banana coated in caramelized sugar.");
        ss42.add(lblCue, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel8.add(ss42);

        ss41.setBackground(new java.awt.Color(255, 255, 255));
        ss41.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton38.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton38.setForeground(new java.awt.Color(0, 0, 0));
        jButton38.setText("+");
        jButton38.setBorder(null);
        jButton38.setBorderPainted(false);
        jButton38.setContentAreaFilled(false);
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });
        ss41.add(jButton38, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel36.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("₱ 20");
        ss41.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 60, 40));

        lblTogue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Snack 3.png"))); // NOI18N
        lblTogue.setToolTipText("Fried spring rolls with bean sprouts.");
        ss41.add(lblTogue, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel8.add(ss41);

        ss40.setBackground(new java.awt.Color(255, 255, 255));
        ss40.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton39.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton39.setForeground(new java.awt.Color(0, 0, 0));
        jButton39.setText("+");
        jButton39.setBorder(null);
        jButton39.setBorderPainted(false);
        jButton39.setContentAreaFilled(false);
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });
        ss40.add(jButton39, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel37.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("₱ 20");
        ss40.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 60, 40));

        lblSiomai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Snack 4.png"))); // NOI18N
        lblSiomai.setToolTipText("Steamed dumplings with dipping sauce.");
        ss40.add(lblSiomai, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel8.add(ss40);

        ss39.setBackground(new java.awt.Color(255, 255, 255));
        ss39.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton40.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton40.setForeground(new java.awt.Color(0, 0, 0));
        jButton40.setText("+");
        jButton40.setBorder(null);
        jButton40.setBorderPainted(false);
        jButton40.setContentAreaFilled(false);
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });
        ss39.add(jButton40, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel38.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("₱ 30");
        ss39.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 60, 40));

        lblTuron.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Snack 5.png"))); // NOI18N
        lblTuron.setToolTipText(" Fried banana roll with caramelized sugar.");
        ss39.add(lblTuron, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel8.add(ss39);

        ss38.setBackground(new java.awt.Color(255, 255, 255));
        ss38.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton41.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton41.setForeground(new java.awt.Color(0, 0, 0));
        jButton41.setText("+");
        jButton41.setBorder(null);
        jButton41.setBorderPainted(false);
        jButton41.setContentAreaFilled(false);
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });
        ss38.add(jButton41, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel39.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("₱ 35");
        ss38.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 60, 40));

        lblBurger.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Snack 6.png"))); // NOI18N
        lblBurger.setToolTipText("Juicy burger with soft bun.");
        ss38.add(lblBurger, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel8.add(ss38);

        snacks.setViewportView(jPanel8);

        Dish.add(snacks, "snacks");

        drinks.setBackground(new java.awt.Color(255, 255, 255));
        drinks.setBorder(null);
        drinks.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.GridLayout(0, 3, 20, 20));

        ss44.setBackground(new java.awt.Color(255, 255, 255));
        ss44.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton29.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton29.setForeground(new java.awt.Color(0, 0, 0));
        jButton29.setText("+");
        jButton29.setBorder(null);
        jButton29.setBorderPainted(false);
        jButton29.setContentAreaFilled(false);
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });
        ss44.add(jButton29, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel40.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("₱ 20");
        ss44.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 60, 40));

        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/drinks 1.png"))); // NOI18N
        jLabel49.setToolTipText("Refreshing and clean water.");
        ss44.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel7.add(ss44);

        ss45.setBackground(new java.awt.Color(255, 255, 255));
        ss45.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton30.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton30.setForeground(new java.awt.Color(0, 0, 0));
        jButton30.setText("+");
        jButton30.setBorder(null);
        jButton30.setBorderPainted(false);
        jButton30.setContentAreaFilled(false);
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });
        ss45.add(jButton30, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel57.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("₱ 25");
        ss45.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 60, 40));

        lblSprite.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/drinks 2.png"))); // NOI18N
        lblSprite.setToolTipText("Lemon-lime soda.");
        ss45.add(lblSprite, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel7.add(ss45);

        ss46.setBackground(new java.awt.Color(255, 255, 255));
        ss46.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton31.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton31.setForeground(new java.awt.Color(0, 0, 0));
        jButton31.setText("+");
        jButton31.setBorder(null);
        jButton31.setBorderPainted(false);
        jButton31.setContentAreaFilled(false);
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });
        ss46.add(jButton31, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel58.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("₱ 25");
        ss46.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 60, 40));

        lblCoke.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/drinks 3.png"))); // NOI18N
        lblCoke.setToolTipText("Classic cola with a sweet, fizzy taste.");
        ss46.add(lblCoke, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel7.add(ss46);

        ss47.setBackground(new java.awt.Color(255, 255, 255));
        ss47.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton32.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton32.setForeground(new java.awt.Color(0, 0, 0));
        jButton32.setText("+");
        jButton32.setBorder(null);
        jButton32.setBorderPainted(false);
        jButton32.setContentAreaFilled(false);
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });
        ss47.add(jButton32, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel59.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("₱ 25");
        ss47.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 60, 40));

        lblRoyal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/drinks 4.png"))); // NOI18N
        lblRoyal.setToolTipText("Orange sode, sweet and citrusy.");
        ss47.add(lblRoyal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel7.add(ss47);

        ss48.setBackground(new java.awt.Color(255, 255, 255));
        ss48.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton33.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton33.setForeground(new java.awt.Color(0, 0, 0));
        jButton33.setText("+");
        jButton33.setBorder(null);
        jButton33.setBorderPainted(false);
        jButton33.setContentAreaFilled(false);
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });
        ss48.add(jButton33, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel60.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setText("₱ 75");
        ss48.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 60, 40));

        lblBigcoke.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/drinks 5.png"))); // NOI18N
        lblBigcoke.setToolTipText("Bigger serving of classic cola");
        ss48.add(lblBigcoke, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel7.add(ss48);

        ss49.setBackground(new java.awt.Color(255, 255, 255));
        ss49.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton34.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton34.setForeground(new java.awt.Color(0, 0, 0));
        jButton34.setText("+");
        jButton34.setBorder(null);
        jButton34.setBorderPainted(false);
        jButton34.setContentAreaFilled(false);
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });
        ss49.add(jButton34, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel61.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setText("₱ 85");
        ss49.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 60, 40));

        lblBigcokezero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/drinks 6.png"))); // NOI18N
        lblBigcokezero.setToolTipText("Zero sugar cola.");
        ss49.add(lblBigcokezero, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel7.add(ss49);

        ss50.setBackground(new java.awt.Color(255, 255, 255));
        ss50.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton35.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton35.setForeground(new java.awt.Color(0, 0, 0));
        jButton35.setText("+");
        jButton35.setBorder(null);
        jButton35.setBorderPainted(false);
        jButton35.setContentAreaFilled(false);
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });
        ss50.add(jButton35, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel62.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setText("₱ 75");
        ss50.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 60, 40));

        lblbigroyal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/drinks 7.png"))); // NOI18N
        lblbigroyal.setToolTipText("Bigger serving of sweet orange soda.");
        ss50.add(lblbigroyal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel7.add(ss50);

        drinks.setViewportView(jPanel7);

        Dish.add(drinks, "drinks");

        noodles.setBackground(new java.awt.Color(255, 255, 255));
        noodles.setBorder(null);
        noodles.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        noodles.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new java.awt.GridLayout(0, 3, 20, 20));

        ss30.setBackground(new java.awt.Color(255, 255, 255));
        ss30.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton23.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton23.setForeground(new java.awt.Color(0, 0, 0));
        jButton23.setText("+");
        jButton23.setBorder(null);
        jButton23.setBorderPainted(false);
        jButton23.setContentAreaFilled(false);
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        ss30.add(jButton23, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel63.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("₱ 30");
        ss30.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 60, 40));

        lblCalamansi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish 29.png"))); // NOI18N
        lblCalamansi.setToolTipText("Instant noodles with calamansi flavor.");
        ss30.add(lblCalamansi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel6.add(ss30);

        ss29.setBackground(new java.awt.Color(255, 255, 255));
        ss29.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton24.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton24.setForeground(new java.awt.Color(0, 0, 0));
        jButton24.setText("+");
        jButton24.setBorder(null);
        jButton24.setBorderPainted(false);
        jButton24.setContentAreaFilled(false);
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        ss29.add(jButton24, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel64.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("₱ 30");
        ss29.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 60, 40));

        lblSpicy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish 28.png"))); // NOI18N
        lblSpicy.setToolTipText("Instant noodles with spicy flavor.");
        ss29.add(lblSpicy, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel6.add(ss29);

        ss28.setBackground(new java.awt.Color(255, 255, 255));
        ss28.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton25.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton25.setForeground(new java.awt.Color(0, 0, 0));
        jButton25.setText("+");
        jButton25.setBorder(null);
        jButton25.setBorderPainted(false);
        jButton25.setContentAreaFilled(false);
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        ss28.add(jButton25, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel65.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("₱ 30");
        ss28.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 60, 40));

        lblChilimansi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish 27.png"))); // NOI18N
        lblChilimansi.setToolTipText("Instant noodles with chili and calamansi flavor.");
        ss28.add(lblChilimansi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel6.add(ss28);

        ss27.setBackground(new java.awt.Color(255, 255, 255));
        ss27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton26.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton26.setForeground(new java.awt.Color(0, 0, 0));
        jButton26.setText("+");
        jButton26.setBorder(null);
        jButton26.setBorderPainted(false);
        jButton26.setContentAreaFilled(false);
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        ss27.add(jButton26, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel66.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("₱ 45");
        ss27.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 60, 40));

        lblspag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish 26.png"))); // NOI18N
        lblspag.setToolTipText("Sweet-style Filipino spaghetti with sauce.");
        ss27.add(lblspag, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel6.add(ss27);

        ss26.setBackground(new java.awt.Color(255, 255, 255));
        ss26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton27.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton27.setForeground(new java.awt.Color(0, 0, 0));
        jButton27.setText("+");
        jButton27.setBorder(null);
        jButton27.setBorderPainted(false);
        jButton27.setContentAreaFilled(false);
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });
        ss26.add(jButton27, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel67.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("₱ 30");
        ss26.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 60, 40));

        lblpancit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish 25.png"))); // NOI18N
        lblpancit.setToolTipText(" Stir-fried noodles with vegetables and meat.");
        ss26.add(lblpancit, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel6.add(ss26);

        ss25.setBackground(new java.awt.Color(255, 255, 255));
        ss25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton28.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton28.setForeground(new java.awt.Color(0, 0, 0));
        jButton28.setText("+");
        jButton28.setBorder(null);
        jButton28.setBorderPainted(false);
        jButton28.setContentAreaFilled(false);
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });
        ss25.add(jButton28, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel68.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("₱ 35");
        ss25.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, 60, 40));

        lblpalabok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish 24.png"))); // NOI18N
        lblpalabok.setToolTipText("Rice noodles with rich shrimp sauce and toppings.");
        ss25.add(lblpalabok, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel6.add(ss25);

        noodles.setViewportView(jPanel6);

        Dish.add(noodles, "noodles");

        bfMeals.setBackground(new java.awt.Color(255, 255, 255));
        bfMeals.setBorder(null);
        bfMeals.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        bfMeals.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.GridLayout(0, 3, 20, 20));

        ss22.setBackground(new java.awt.Color(255, 255, 255));

        ss23.setBackground(new java.awt.Color(255, 255, 255));
        ss23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton17.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton17.setForeground(new java.awt.Color(0, 0, 0));
        jButton17.setText("+");
        jButton17.setBorder(null);
        jButton17.setBorderPainted(false);
        jButton17.setContentAreaFilled(false);
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        ss23.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel69.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("₱ 55");
        ss23.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 60, 40));

        lblGarlic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish 23.png"))); // NOI18N
        lblGarlic.setToolTipText("Savory garlic sausage, rich and flavorful.");
        ss23.add(lblGarlic, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout ss22Layout = new javax.swing.GroupLayout(ss22);
        ss22.setLayout(ss22Layout);
        ss22Layout.setHorizontalGroup(
            ss22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ss22Layout.createSequentialGroup()
                .addGap(0, 18, Short.MAX_VALUE)
                .addComponent(ss23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 19, Short.MAX_VALUE))
        );
        ss22Layout.setVerticalGroup(
            ss22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ss22Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ss23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.add(ss22);

        ss20.setBackground(new java.awt.Color(255, 255, 255));
        ss20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton18.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton18.setForeground(new java.awt.Color(0, 0, 0));
        jButton18.setText("+");
        jButton18.setBorder(null);
        jButton18.setBorderPainted(false);
        jButton18.setContentAreaFilled(false);
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        ss20.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel70.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("₱ 30");
        ss20.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 60, 40));

        lblEgg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish 21.png"))); // NOI18N
        lblEgg.setToolTipText(" Simple fried egg served with rice.");
        ss20.add(lblEgg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel4.add(ss20);

        ss19.setBackground(new java.awt.Color(255, 255, 255));
        ss19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton19.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton19.setForeground(new java.awt.Color(0, 0, 0));
        jButton19.setText("+");
        jButton19.setBorder(null);
        jButton19.setBorderPainted(false);
        jButton19.setContentAreaFilled(false);
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        ss19.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel71.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("₱ 60");
        ss19.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 60, 40));

        lblDaing.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish 20.png"))); // NOI18N
        lblDaing.setToolTipText("Crispy marinated dried fish.");
        ss19.add(lblDaing, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel4.add(ss19);

        ss18.setBackground(new java.awt.Color(255, 255, 255));
        ss18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton20.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton20.setForeground(new java.awt.Color(0, 0, 0));
        jButton20.setText("+");
        jButton20.setBorder(null);
        jButton20.setBorderPainted(false);
        jButton20.setContentAreaFilled(false);
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        ss18.add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel72.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("₱ 40");
        ss18.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, 60, 40));

        lblHotdog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish 19.png"))); // NOI18N
        lblHotdog.setToolTipText("Classic juicy hotdog meal.");
        ss18.add(lblHotdog, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, -1));

        jPanel4.add(ss18);

        ss17.setBackground(new java.awt.Color(255, 255, 255));
        ss17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton21.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton21.setForeground(new java.awt.Color(0, 0, 0));
        jButton21.setText("+");
        jButton21.setBorder(null);
        jButton21.setBorderPainted(false);
        jButton21.setContentAreaFilled(false);
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        ss17.add(jButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel73.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("₱ 30");
        ss17.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 60, 40));

        lblSweet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish 18.png"))); // NOI18N
        lblSweet.setToolTipText("Sweet Filipino sausage, rich in flavor.");
        ss17.add(lblSweet, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel4.add(ss17);

        ss16.setBackground(new java.awt.Color(255, 255, 255));
        ss16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton22.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton22.setForeground(new java.awt.Color(0, 0, 0));
        jButton22.setText("+");
        jButton22.setBorder(null);
        jButton22.setBorderPainted(false);
        jButton22.setContentAreaFilled(false);
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        ss16.add(jButton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel74.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("₱ 70");
        ss16.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 60, 40));

        lblHungarian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish 17.png"))); // NOI18N
        lblHungarian.setToolTipText("Juicy and flavorful sausage with rice.");
        ss16.add(lblHungarian, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel4.add(ss16);

        bfMeals.setViewportView(jPanel4);

        Dish.add(bfMeals, "bf");

        menuRice.setBackground(new java.awt.Color(255, 255, 255));
        menuRice.setBorder(null);
        menuRice.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        menuRice.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1030, 1900));
        jPanel2.setLayout(new java.awt.GridLayout(0, 3, 20, 20));

        ss15.setBackground(new java.awt.Color(255, 255, 255));
        ss15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("+");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        ss15.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel75.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("₱ 55");
        ss15.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 60, 50));

        lblsweetsourtilapia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish.png"))); // NOI18N
        lblsweetsourtilapia.setToolTipText("Crispy tilapia with tangy sweet and sour sauce.");
        ss15.add(lblsweetsourtilapia, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2.add(ss15);

        ss14.setBackground(new java.awt.Color(255, 255, 255));
        ss14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("+");
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        ss14.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel76.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("₱ 45");
        ss14.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 50, 40));

        lbltilapia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish1.png"))); // NOI18N
        lbltilapia.setToolTipText("Golden fried tilapia, crispy and satisfying.");
        ss14.add(lbltilapia, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2.add(ss14);

        ss13.setBackground(new java.awt.Color(255, 255, 255));
        ss13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setText("+");
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        ss13.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel77.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("₱ 60");
        ss13.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 50, 50));

        lblNilagangManok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish9.png"))); // NOI18N
        lblNilagangManok.setToolTipText("Light chicken soup with vegetables.");
        ss13.add(lblNilagangManok, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2.add(ss13);

        ss12.setBackground(new java.awt.Color(255, 255, 255));
        ss12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 0, 0));
        jButton4.setText("+");
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        ss12.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel78.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("₱ 70");
        ss12.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 50, 40));

        lblBeefsinigang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish8.png"))); // NOI18N
        lblBeefsinigang.setToolTipText(" Sour beef soup with fresh vegetables.");
        ss12.add(lblBeefsinigang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2.add(ss12);

        ss11.setBackground(new java.awt.Color(255, 255, 255));
        ss11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 0, 0));
        jButton5.setText("+");
        jButton5.setBorder(null);
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        ss11.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel79.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("₱ 60");
        ss11.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 50, 40));

        lblLechon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish7.png"))); // NOI18N
        lblLechon.setToolTipText("Crispy deep-fried pork belly.");
        ss11.add(lblLechon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2.add(ss11);

        ss10.setBackground(new java.awt.Color(255, 255, 255));
        ss10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 0, 0));
        jButton6.setText("+");
        jButton6.setBorder(null);
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        ss10.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel80.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("₱ 60");
        ss10.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 50, 40));

        lblBCaldereta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish6.png"))); // NOI18N
        lblBCaldereta.setToolTipText("Rich beef stew with tomato sauce and spices.");
        ss10.add(lblBCaldereta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2.add(ss10);

        ss9.setBackground(new java.awt.Color(255, 255, 255));
        ss9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton7.setForeground(new java.awt.Color(0, 0, 0));
        jButton7.setText("+");
        jButton7.setBorder(null);
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        ss9.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel81.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("₱ 65");
        ss9.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 60, 40));

        lblPorksinigang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish5.png"))); // NOI18N
        lblPorksinigang.setToolTipText("Sour tamarind soup with tender pork and vegetables.");
        ss9.add(lblPorksinigang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2.add(ss9);

        ss8.setBackground(new java.awt.Color(255, 255, 255));
        ss8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton8.setForeground(new java.awt.Color(0, 0, 0));
        jButton8.setText("+");
        jButton8.setBorder(null);
        jButton8.setBorderPainted(false);
        jButton8.setContentAreaFilled(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        ss8.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel82.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("₱ 65");
        ss8.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 50, 40));

        lblKarekare.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish4.png"))); // NOI18N
        lblKarekare.setToolTipText("Peanut-based stew with tender meat and veggies. ");
        ss8.add(lblKarekare, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2.add(ss8);

        ss7.setBackground(new java.awt.Color(255, 255, 255));
        ss7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton9.setForeground(new java.awt.Color(0, 0, 0));
        jButton9.setText("+");
        jButton9.setBorder(null);
        jButton9.setBorderPainted(false);
        jButton9.setContentAreaFilled(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        ss7.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel83.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("₱ 70");
        ss7.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 50, 40));

        lblMenudo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish3.png"))); // NOI18N
        lblMenudo.setToolTipText("Pork stew with tomato sauce and vegetables.");
        ss7.add(lblMenudo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2.add(ss7);

        ss6.setBackground(new java.awt.Color(255, 255, 255));
        ss6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton10.setForeground(new java.awt.Color(0, 0, 0));
        jButton10.setText("+");
        jButton10.setBorder(null);
        jButton10.setBorderPainted(false);
        jButton10.setContentAreaFilled(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        ss6.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel84.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("₱ 75");
        ss6.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 50, 40));

        lblBicol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish2.png"))); // NOI18N
        lblBicol.setToolTipText("Spicy pork in creamy coconut milk sauce.");
        ss6.add(lblBicol, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2.add(ss6);

        ss5.setBackground(new java.awt.Color(255, 255, 255));
        ss5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton11.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton11.setForeground(new java.awt.Color(0, 0, 0));
        jButton11.setText("+");
        jButton11.setBorder(null);
        jButton11.setBorderPainted(false);
        jButton11.setContentAreaFilled(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        ss5.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel85.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("₱ 55");
        ss5.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, 50, 40));

        lblAsado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish15.png"))); // NOI18N
        lblAsado.setToolTipText("Sweet and savory chicken.");
        ss5.add(lblAsado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2.add(ss5);

        ss4.setBackground(new java.awt.Color(255, 255, 255));
        ss4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton12.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton12.setForeground(new java.awt.Color(0, 0, 0));
        jButton12.setText("+");
        jButton12.setBorder(null);
        jButton12.setBorderPainted(false);
        jButton12.setContentAreaFilled(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        ss4.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel86.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("₱ 50");
        ss4.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 50, 40));

        lblFriedChiken.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish14.png"))); // NOI18N
        lblFriedChiken.setToolTipText("Crispy and juicy fried chicken. ");
        ss4.add(lblFriedChiken, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2.add(ss4);

        ss3.setBackground(new java.awt.Color(255, 255, 255));
        ss3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton13.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton13.setForeground(new java.awt.Color(0, 0, 0));
        jButton13.setText("+");
        jButton13.setBorder(null);
        jButton13.setBorderPainted(false);
        jButton13.setContentAreaFilled(false);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        ss3.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel87.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("₱ 65");
        ss3.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 50, 40));

        lblBeefSteak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish13.png"))); // NOI18N
        lblBeefSteak.setToolTipText("Tender beef in soy-calamansi sauce with onions.");
        ss3.add(lblBeefSteak, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2.add(ss3);

        ss2.setBackground(new java.awt.Color(255, 255, 255));
        ss2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton14.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton14.setForeground(new java.awt.Color(0, 0, 0));
        jButton14.setText("+");
        jButton14.setBorder(null);
        jButton14.setBorderPainted(false);
        jButton14.setContentAreaFilled(false);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        ss2.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel88.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("₱ 65");
        ss2.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 50, 40));

        lblTokwatBaboy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish12.png"))); // NOI18N
        lblTokwatBaboy.setToolTipText(" Fried tofu with pork in savory sauce.");
        ss2.add(lblTokwatBaboy, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2.add(ss2);

        ss1.setBackground(new java.awt.Color(255, 255, 255));
        ss1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton15.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton15.setForeground(new java.awt.Color(0, 0, 0));
        jButton15.setText("+");
        jButton15.setBorder(null);
        jButton15.setBorderPainted(false);
        jButton15.setContentAreaFilled(false);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        ss1.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel89.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("₱ 55");
        ss1.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 50, 40));

        lblbopis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish11.png"))); // NOI18N
        lblbopis.setToolTipText("Spicy minced pork dish with bold flavors.");
        ss1.add(lblbopis, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2.add(ss1);

        ss.setBackground(new java.awt.Color(255, 255, 255));
        ss.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton16.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jButton16.setForeground(new java.awt.Color(0, 0, 0));
        jButton16.setText("+");
        jButton16.setBorder(null);
        jButton16.setBorderPainted(false);
        jButton16.setContentAreaFilled(false);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        ss.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 50, 50));

        jLabel90.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("₱ 50");
        ss.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 50, 40));

        lblSarciado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish10.png"))); // NOI18N
        lblSarciado.setToolTipText("Fried tilapia topped with savory tomato and egg sauce.");
        ss.add(lblSarciado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel2.add(ss);

        menuRice.setViewportView(jPanel2);

        Dish.add(menuRice, "rice");

        pnlDish.add(Dish, new org.netbeans.lib.awtextra.AbsoluteConstraints(251, 98, 1020, 670));

        jPanel5.setBackground(new java.awt.Color(229, 223, 223));
        jPanel5.setOpaque(false);
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnDrinks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/btnDrinks.png"))); // NOI18N
        btnDrinks.setBorder(null);
        btnDrinks.setBorderPainted(false);
        btnDrinks.setContentAreaFilled(false);
        btnDrinks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDrinksActionPerformed(evt);
            }
        });
        jPanel5.add(btnDrinks, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 210, 90));

        btnSnacks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/btnSnacks.png"))); // NOI18N
        btnSnacks.setBorder(null);
        btnSnacks.setBorderPainted(false);
        btnSnacks.setContentAreaFilled(false);
        btnSnacks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSnacksActionPerformed(evt);
            }
        });
        jPanel5.add(btnSnacks, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 210, 90));

        btnBreakfast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/btnBf.png"))); // NOI18N
        btnBreakfast.setBorder(null);
        btnBreakfast.setBorderPainted(false);
        btnBreakfast.setContentAreaFilled(false);
        btnBreakfast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBreakfastActionPerformed(evt);
            }
        });
        jPanel5.add(btnBreakfast, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 210, 100));

        btnNoodles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/btnNoodles.png"))); // NOI18N
        btnNoodles.setBorder(null);
        btnNoodles.setBorderPainted(false);
        btnNoodles.setContentAreaFilled(false);
        btnNoodles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoodlesActionPerformed(evt);
            }
        });
        jPanel5.add(btnNoodles, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 210, 90));

        btnRice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/btnRice.png"))); // NOI18N
        btnRice.setBorder(null);
        btnRice.setBorderPainted(false);
        btnRice.setContentAreaFilled(false);
        btnRice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRiceActionPerformed(evt);
            }
        });
        jPanel5.add(btnRice, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 210, 90));

        pnlDish.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 220, 570));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Customer Dish.png"))); // NOI18N
        pnlDish.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1310, 800));

        jPanel1.add(pnlDish, "dish");

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1320, 800));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCartActionPerformed
        btnCart.setIcon(darkIcon);
        if(cartClicked){
            btnCart.setIcon(lightIcon);
            pnlCart.setVisible(true);
            cartClicked = false;
        }else{
            btnCart.setIcon(darkIcon);
            pnlCart.setVisible(false);
            cartClicked = true;
        }
       
    }//GEN-LAST:event_btnCartActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        setActive("home");
        CardLayout cl = (CardLayout)(jPanel1.getLayout());
            cl.show(jPanel1, "home");
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnDishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDishActionPerformed
        setActive("dish");
        CardLayout cl = (CardLayout)(jPanel1.getLayout());
            cl.show(jPanel1, "dish");
    }//GEN-LAST:event_btnDishActionPerformed

    private void btnOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderActionPerformed
        setActive("order");
        CardLayout cl = (CardLayout)(jPanel1.getLayout());
            cl.show(jPanel1, "orders");
            
    }//GEN-LAST:event_btnOrderActionPerformed

    private void btnPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaymentActionPerformed
       setActive("pay");
        CardLayout cl = (CardLayout)(jPanel1.getLayout());
            cl.show(jPanel1, "pay");
    }//GEN-LAST:event_btnPaymentActionPerformed

    private void btnAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAboutActionPerformed
        setActive("about");
        CardLayout cl = (CardLayout)(jPanel1.getLayout());
            cl.show(jPanel1, "about");
    }//GEN-LAST:event_btnAboutActionPerformed

    private void btnBreakfastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBreakfastActionPerformed
        setCategory("bf");
        CardLayout cl = (CardLayout)(Dish.getLayout());
        cl.show(Dish, "bf");
    }//GEN-LAST:event_btnBreakfastActionPerformed

    private void btnRiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRiceActionPerformed
        setCategory("rice");
        CardLayout cl = (CardLayout)(Dish.getLayout());
        cl.show(Dish, "rice");
    }//GEN-LAST:event_btnRiceActionPerformed

    private void btnDineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDineActionPerformed
        setOrderType(btnDine);
        lblOrderType.setText("Dine-in");
    }//GEN-LAST:event_btnDineActionPerformed

    private void btnTakeOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTakeOutActionPerformed
        setOrderType(btnTakeOut);
        lblOrderType.setText("Take-out");
    }//GEN-LAST:event_btnTakeOutActionPerformed

    private void btnDeliveryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeliveryActionPerformed
        setOrderType(btnDelivery);
        lblOrderType.setText("Delivery");
    }//GEN-LAST:event_btnDeliveryActionPerformed

    private void btnNoodlesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoodlesActionPerformed
        setCategory("nd");
        CardLayout cl = (CardLayout)(Dish.getLayout());
        cl.show(Dish, "noodles");
        btnNoodles.setIcon(ndd);
    }//GEN-LAST:event_btnNoodlesActionPerformed

    private void btnSnacksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSnacksActionPerformed
        setCategory("sn");
        CardLayout cl = (CardLayout)(Dish.getLayout());
        cl.show(Dish, "snacks");
        btnSnacks.setIcon(snd);
    }//GEN-LAST:event_btnSnacksActionPerformed

    private void btnDrinksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDrinksActionPerformed
        setCategory("dr");
        CardLayout cl = (CardLayout)(Dish.getLayout());
        cl.show(Dish, "drinks");
        btnDrinks.setIcon(drd);
    }//GEN-LAST:event_btnDrinksActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        startUp log = new startUp();
        log.setVisible(true);
        log.pack();
        log.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void textCardNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCardNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCardNameActionPerformed

    private void textCardNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCardNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCardNumActionPerformed

    private void textExDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textExDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textExDateActionPerformed

    private void textCCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCCVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCCVActionPerformed

    private void textAmount2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textAmount2ActionPerformed
        // TODO add your handling code here:
        calculateChangeCC();
    }//GEN-LAST:event_textAmount2ActionPerformed

    private void textAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textAmountActionPerformed
        // TODO add your handling code here:
        calculateChange();
    }//GEN-LAST:event_textAmountActionPerformed

    private void textRefNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textRefNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textRefNumActionPerformed

    private void textAccNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textAccNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textAccNameActionPerformed

    private void btnCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCashActionPerformed
        selectedPayment = "cash";
        lblPayMethod.setText("Cash");
        btnCash.setBorder(BorderFactory.createLineBorder(Color.red, 2));
    }//GEN-LAST:event_btnCashActionPerformed

    private void btnCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCCActionPerformed
        selectedPayment = "credit card";
        lblPayMethod.setText("Credit Card");
        btnCC.setBorder(BorderFactory.createLineBorder(Color.red, 2));
         CardLayout cl = (CardLayout)(pnlPayment.getLayout());
            cl.show(pnlPayment, "cc");
    }//GEN-LAST:event_btnCCActionPerformed

    private void btnEWalletActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEWalletActionPerformed
        selectedPayment = "e-wallet";
        lblPayMethod.setText("e-Wallet");
        btnEWallet.setBorder(BorderFactory.createLineBorder(Color.red, 2));
        CardLayout cl = (CardLayout)(pnlPayment.getLayout());
            cl.show(pnlPayment, "ewallet");
    }//GEN-LAST:event_btnEWalletActionPerformed

    private void btnPlaceOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlaceOrderActionPerformed
       if (!validateOrderAndPayment()) return;
       refreshOrderTables();
       setupCustomerFields();
       setupOrderDetails();
       CardLayout cl = (CardLayout)(jPanel1.getLayout());
       cl.show(jPanel1, "orders");
       lblOrderStat.setText("PENDING");
       

    }//GEN-LAST:event_btnPlaceOrderActionPerformed

    private void btnProceedPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProceedPaymentActionPerformed
        lblOrderStat1.setText("PENDING");
        lblOrderStat2.setText("PENDING");

        String name = textUserName.getText().trim();
        System.out.println("Customer Name: " + textUserName);
        String contact = txtContactNo.getText().trim();
        String address = textAddress.getText().trim();
        if (name.isEmpty() || contact.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in Name and Contact!");
            return;
        }
        if (!name.matches("[a-zA-Z ]+")) {
            JOptionPane.showMessageDialog(this, "Name must contain letters only!");
            return;
        }
        if (!contact.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Contact must contain numbers only!");
            return;
        }

        if (orderType.equals("Delivery") && address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in Address for delivery!");
            return;
        }

        if (selectedPayment == null || selectedPayment.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a payment method first!");
            return;
        }
        
        saveCustomerInfo(name, contact);

        if (selectedPayment.equals("cash")) {
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Confirm cash payment?",
                "Cash Payment",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                insertPayment("CASH");
                lblOrderStat.setText("PREPARING");

                if (orderType.equals("Delivery")) {
                    JOptionPane.showMessageDialog(this, "Thank you for ordering!");
                } else {
                    JOptionPane.showMessageDialog(this, "Please pay at the counter.");
                }

                CardLayout cl = (CardLayout)(jPanel1.getLayout());
                cl.show(jPanel1, "orders");
            }

        } 
        else {
            CardLayout cl = (CardLayout)(jPanel1.getLayout());
            cl.show(jPanel1, "pay");
        }
        try {
    Connection con = DBConnection.getConnection();
    
    String customerName = textUserName.getText().trim();
    
    String totalStr = lblTotal.getText().replace("₱","").trim();
    double totalAmount = Double.parseDouble(totalStr);
    
    String status = "PENDING";
    String orderTypeVal = orderType;

    java.time.LocalDateTime now = java.time.LocalDateTime.now();
    String orderDate = now.toString();

    String orderNumber = "ORD" + System.currentTimeMillis(); 
    
    String sql = "INSERT INTO orders (order_number, customer_name, total_amount, order_type, status, order_date) VALUES (?,?,?,?,?,?)";

    PreparedStatement pst = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);

    pst.setString(1, orderNumber);
    pst.setString(2, customerName);
    pst.setDouble(3, totalAmount);
    pst.setString(4, orderTypeVal);
    pst.setString(5, status);
    pst.setString(6, orderDate);

    pst.executeUpdate();

    java.sql.ResultSet rs = pst.getGeneratedKeys();
    int orderID = 0;

    if (rs.next()) {
        orderID = rs.getInt(1);
    }
    
    DefaultTableModel model = (DefaultTableModel) tblCart.getModel();

    for (int i = 0; i < model.getRowCount(); i++) {

    String productName = model.getValueAt(i, 0).toString().trim();
    String priceStr = model.getValueAt(i, 1).toString().replace("₱","").trim();
    double price = Double.parseDouble(priceStr);

    int qty = Integer.parseInt(model.getValueAt(i, 2).toString().trim());
    
    int productId = 0;

    String getProduct = "SELECT product_id FROM products WHERE TRIM(name) = TRIM(?)";
    PreparedStatement pstGet = con.prepareStatement(getProduct);
    pstGet.setString(1, productName);
    ResultSet rsProd = pstGet.executeQuery();

    if (rsProd.next()) {
        productId = rsProd.getInt("product_id");
    }

    rsProd.close();
    pstGet.close();

    if (productId == 0) {
        System.out.println("Product not found: " + productName);
        continue;
    }

    String sqlItem = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
    PreparedStatement pstItem = con.prepareStatement(sqlItem);

    pstItem.setInt(1, orderID);
    pstItem.setInt(2, productId);
    pstItem.setInt(3, qty);
    pstItem.setDouble(4, price);

    pstItem.executeUpdate();
    pstItem.close();
    
    CallableStatement csStock = con.prepareCall("{CALL reduceStock(?,?)}");

    csStock.setInt(1, productId);
    csStock.setInt(2, qty);

    csStock.execute();
    csStock.close();
}
    String sql2 = "INSERT INTO tblorder (order_id, customer_name, total_amount, order_type, status, order_date) VALUES (?,?,?,?,?,?)";

    PreparedStatement pst2 = con.prepareStatement(sql2);
    pst2.setInt(1, orderID);
    pst2.setString(2, customerName);
    pst2.setDouble(3, totalAmount);
    pst2.setString(4, orderTypeVal);
    pst2.setString(5, status);
    pst2.setString(6, orderDate);

    pst2.executeUpdate();

    lblOrderNum.setText(orderNumber);

} catch (Exception e) {
    e.printStackTrace();
}
    }
            public void saveCustomerInfo(String username, String contactNo) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "UPDATE users SET contact_no = ?, status = ? WHERE username = ?";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, contactNo);
            pst.setString(2, "Active");
            pst.setString(3, username);

            pst.executeUpdate();

            pst.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving customer info: " + e.getMessage());
        }
    
        
    }//GEN-LAST:event_btnProceedPaymentActionPerformed

    private void btneWalletPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneWalletPayActionPerformed
        String accName = textAccName.getText().trim();
        String refNum = textRefNum.getText().trim();
        String amount = textAmount.getText().trim();

        if (accName.isEmpty() || refNum.isEmpty() || amount.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all E-Wallet details!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Confirm E-Wallet payment?",
            "Confirm Payment",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            calculateChange();
            insertPayment("E-WALLET");
            try {
        Connection con = DBConnection.getConnection();

        String sql = "UPDATE orders SET status='PAID' WHERE order_number=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, lblOrderNum.getText());
        pst.executeUpdate();
        
        String sql2 = "UPDATE tblorder SET status='PAID' WHERE order_id = (SELECT order_id FROM orders WHERE order_number=?)";
        PreparedStatement pst2 = con.prepareStatement(sql2);
        pst2.setString(1, lblOrderNum.getText());
        pst2.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
            lblOrderStat2.setText("PREPARING");
            JOptionPane.showMessageDialog(this, "Payment Successful!");
    }//GEN-LAST:event_btneWalletPayActionPerformed
    }
    private void btnCcPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCcPayActionPerformed
        String name = textCardName.getText().trim();
        String cardNum = textCardNum.getText().trim();
        String exDate = textExDate.getText().trim();
        String ccv = textCCV.getText().trim();
        String amount = textAmount2.getText().trim();

        if (name.isEmpty() || cardNum.isEmpty() || exDate.isEmpty() || ccv.isEmpty() || amount.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all Credit Card details!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Confirm Credit Card payment?",
            "Confirm Payment",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            calculateChangeCC(); 
            insertPayment("CREDIT CARD");
            try {
        Connection con = DBConnection.getConnection();

        String sql = "UPDATE orders SET status='PAID' WHERE order_number=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, lblOrderNum.getText());
        pst.executeUpdate();
        
        String sql2 = "UPDATE tblorder SET status='PAID' WHERE order_id = (SELECT order_id FROM orders WHERE order_number=?)";
        PreparedStatement pst2 = con.prepareStatement(sql2);
        pst2.setString(1, lblOrderNum.getText());
        pst2.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
            lblOrderStat1.setText("PREPARING");
            JOptionPane.showMessageDialog(this, "Payment Successful!");
    }//GEN-LAST:event_btnCcPayActionPerformed
    }
    private void textAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textAddressActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        // TODO add your handling code here:
        addToCart("Shanghai", 50.00);
    
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        addToCart("Sweet & Sour Tilapia w/Rice", 55.00);
     
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        addToCart("Fried Tilapia w/Rice", 45.00);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        addToCart("Nilagang Manok w/Rice", 60.00);
       
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        addToCart("Beef Sinigang w/Rice", 70.00);
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        addToCart("Lechon Kawali w/Rice", 60.00);
         
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        addToCart("Beef Caldereta w/Rice", 60.00);
         
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        addToCart("Pork Sinigang w/Rice", 65.00);
       
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        addToCart("Kare-Kare w/Rice", 65.00);
         
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        addToCart("Menudo w/Rice", 70.00);
  
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        addToCart("Bicol Express w/Rice", 75.00);
         
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        addToCart("Chicken Asado w/Rice", 55.00);
     
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        addToCart("Fried Chicken w/Rice", 50.00);
        
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        addToCart("Beef Steak w/Rice", 65.00);
      
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        addToCart("Tokwa't Baboy w/Rice", 65.00);
 
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        addToCart("Bopis w/Rice", 55.00);
        
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        addToCart("Sarciadong Tilapia w/Rice", 50.00);
       
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
        addToCart("Pancit Canton Calamansi", 30.00);
   
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
        addToCart("Pancit Canton Spicy", 30.00);

    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
        addToCart("Pancit Canton Chilimansi", 30.00);
       
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
        addToCart("Spaghetti", 45.00);
       
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
        addToCart("Pancit", 30.00);

    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
        addToCart("Palabok", 35.00);
  
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        addToCart("Garlic Longganisa w/Rice & Egg", 55.00);
      
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        addToCart("Egg w/Rice", 30.00);
    
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        addToCart("Daing w/Rice & Egg", 60.00);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
        addToCart("Hatdog w/Rice & Egg", 40.00);
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
        addToCart("Sweet Longganisa w/Rice & Egg", 30.00);
       
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        addToCart("Hungarian w/Rice & Egg", 70.00);
       
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        // TODO add your handling code here:
        addToCart("Banana Cue", 25.00);
       
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        // TODO add your handling code here:
        addToCart("Lumpiang Toge", 20.00);
       
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        // TODO add your handling code here:
        addToCart("Siomai", 20.00);
      
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        // TODO add your handling code here:
        addToCart("Turon", 30.00);
       
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        // TODO add your handling code here:
        addToCart("Burger", 35.00);
         
    }//GEN-LAST:event_jButton41ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:
        addToCart("Bottled Water", 20.00);
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        // TODO add your handling code here:
        addToCart("Sprite", 25.00);
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        // TODO add your handling code here:
        addToCart("Coke", 25.00);
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // TODO add your handling code here:
        addToCart("Royal", 25.00);
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        // TODO add your handling code here:
        addToCart("Big Coca Cola", 75.00);
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        // TODO add your handling code here:
        addToCart("Big Coke Zero", 85.00);
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        // TODO add your handling code here:
        addToCart("Big Royal", 75.00);
    }//GEN-LAST:event_jButton35ActionPerformed

    private void btnAddQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddQuantityActionPerformed
        // TODO add your handling code here:
        if (selectedRowIndex == -1) {
        JOptionPane.showMessageDialog(this, "Select an order first!");
        return;
    }

        String itemName = tblCurrentOrders.getValueAt(selectedRowIndex, 0).toString();

        for (int i = 0; i < cartModel.getRowCount(); i++) {
            if (cartModel.getValueAt(i, 0).toString().equals(itemName)) {

            int qty = Integer.parseInt(cartModel.getValueAt(i, 2).toString());
            double price = Double.parseDouble(cartModel.getValueAt(i, 1).toString());

            qty++;

            cartModel.setValueAt(qty, i, 2);
            cartModel.setValueAt(qty * price, i, 3);
            break;
        }
    }

        refreshOrderTables();
        updateCartTotal();

    }//GEN-LAST:event_btnAddQuantityActionPerformed

    private void btnSubQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubQuantityActionPerformed
        // TODO add your handling code here:
        if (selectedRowIndex == -1) {
        JOptionPane.showMessageDialog(this, "Select an order first!");
        return;
    }

        String itemName = tblCurrentOrders.getValueAt(selectedRowIndex, 0).toString();

        for (int i = 0; i < cartModel.getRowCount(); i++) {
            if (cartModel.getValueAt(i, 0).toString().equals(itemName)) {

            int qty = Integer.parseInt(cartModel.getValueAt(i, 2).toString());

            if (qty <= 1) {
                JOptionPane.showMessageDialog(this, "Minimum quantity is 1!");
                return;
            }

            double price = Double.parseDouble(cartModel.getValueAt(i, 1).toString());

            qty--;

            cartModel.setValueAt(qty, i, 2);
            cartModel.setValueAt(qty * price, i, 3);
            break;
        }
    }
        refreshOrderTables();
        updateCartTotal();
    }//GEN-LAST:event_btnSubQuantityActionPerformed

    private void btnRemoveOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveOrderActionPerformed
        // TODO add your handling code here:
        if (selectedRowIndex == -1) {
        JOptionPane.showMessageDialog(this, "Select an order first!");
        return;
        }
        String itemName = tblCurrentOrders.getValueAt(selectedRowIndex, 0).toString();
        for (int i = 0; i < cartModel.getRowCount(); i++) {
            if (cartModel.getValueAt(i, 0).toString().equals(itemName)) {
                cartModel.removeRow(i);
                break;
        }
    }
        selectedRowIndex = -1;
        refreshOrderTables();
        updateCartTotal();
}
        private void setChangeLabels(double change) {
    String formatted = String.format("%.2f", change);
    lblChange.setText(formatted);
    lblChange1.setText(formatted);

    }//GEN-LAST:event_btnRemoveOrderActionPerformed

    private void txtContactNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContactNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContactNoActionPerformed
    
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
        java.awt.EventQueue.invokeLater(() -> new CustomerDashboard().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Dish;
    private javax.swing.JScrollPane bfMeals;
    private javax.swing.JButton btnAbout;
    private javax.swing.JButton btnAddQuantity;
    private javax.swing.JButton btnBreakfast;
    private javax.swing.JButton btnCC;
    private javax.swing.JButton btnCart;
    private javax.swing.JButton btnCash;
    private javax.swing.JButton btnCcPay;
    private javax.swing.JButton btnDelivery;
    private javax.swing.JButton btnDine;
    private javax.swing.JButton btnDish;
    private javax.swing.JButton btnDrinks;
    private javax.swing.JButton btnEWallet;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnNoodles;
    private javax.swing.JButton btnOrder;
    private javax.swing.JButton btnPayment;
    private javax.swing.JButton btnPlaceOrder;
    private javax.swing.JButton btnProceedPayment;
    private javax.swing.JButton btnRemoveOrder;
    private javax.swing.JButton btnRice;
    private javax.swing.JButton btnSnacks;
    private javax.swing.JButton btnSubQuantity;
    private javax.swing.JButton btnTakeOut;
    private javax.swing.JButton btneWalletPay;
    private javax.swing.JScrollPane drinks;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblAsado;
    private javax.swing.JLabel lblBCaldereta;
    private javax.swing.JLabel lblBeefSteak;
    private javax.swing.JLabel lblBeefsinigang;
    private javax.swing.JLabel lblBicol;
    private javax.swing.JLabel lblBigcoke;
    private javax.swing.JLabel lblBigcokezero;
    private javax.swing.JLabel lblBurger;
    private javax.swing.JLabel lblCalamansi;
    private javax.swing.JLabel lblCcPay;
    private javax.swing.JLabel lblChange;
    private javax.swing.JLabel lblChange1;
    private javax.swing.JLabel lblChilimansi;
    private javax.swing.JLabel lblCoke;
    private javax.swing.JLabel lblCue;
    private javax.swing.JLabel lblDaing;
    private javax.swing.JLabel lblDateTime;
    private javax.swing.JLabel lblDateTime1;
    private javax.swing.JLabel lblDateTime2;
    private javax.swing.JLabel lblEPayMethod;
    private javax.swing.JLabel lblEgg;
    private javax.swing.JLabel lblFriedChiken;
    private javax.swing.JLabel lblGarlic;
    private javax.swing.JLabel lblHotdog;
    private javax.swing.JLabel lblHungarian;
    private javax.swing.JLabel lblKarekare;
    private javax.swing.JLabel lblLechon;
    private javax.swing.JLabel lblMenudo;
    private javax.swing.JLabel lblNilagangManok;
    private javax.swing.JLabel lblOrderNum;
    private javax.swing.JLabel lblOrderNum1;
    private javax.swing.JLabel lblOrderNum2;
    private javax.swing.JLabel lblOrderStat;
    private javax.swing.JLabel lblOrderStat1;
    private javax.swing.JLabel lblOrderStat2;
    private javax.swing.JLabel lblOrderType;
    private javax.swing.JLabel lblPayMethod;
    private javax.swing.JLabel lblPorksinigang;
    private javax.swing.JLabel lblRoyal;
    private javax.swing.JLabel lblSarciado;
    private javax.swing.JLabel lblShanghai;
    private javax.swing.JLabel lblSiomai;
    private javax.swing.JLabel lblSpicy;
    private javax.swing.JLabel lblSprite;
    private javax.swing.JLabel lblSweet;
    private javax.swing.JLabel lblTogue;
    private javax.swing.JLabel lblTokwatBaboy;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotal2;
    private javax.swing.JLabel lblTotal3;
    private javax.swing.JLabel lblTuron;
    private javax.swing.JLabel lblbigroyal;
    private javax.swing.JLabel lblbopis;
    private javax.swing.JLabel lblpalabok;
    private javax.swing.JLabel lblpancit;
    private javax.swing.JLabel lblspag;
    private javax.swing.JLabel lblsweetsourtilapia;
    private javax.swing.JLabel lbltilapia;
    private javax.swing.JScrollPane menuRice;
    private javax.swing.JScrollPane noodles;
    private javax.swing.JPanel pnlAbout;
    private javax.swing.JPanel pnlCart;
    private javax.swing.JPanel pnlDish;
    private javax.swing.JPanel pnlOrders;
    private javax.swing.JPanel pnlPayment;
    private javax.swing.JPanel pnlWelcome;
    private javax.swing.JScrollPane snacks;
    private javax.swing.JPanel ss;
    private javax.swing.JPanel ss1;
    private javax.swing.JPanel ss10;
    private javax.swing.JPanel ss11;
    private javax.swing.JPanel ss12;
    private javax.swing.JPanel ss13;
    private javax.swing.JPanel ss14;
    private javax.swing.JPanel ss15;
    private javax.swing.JPanel ss16;
    private javax.swing.JPanel ss17;
    private javax.swing.JPanel ss18;
    private javax.swing.JPanel ss19;
    private javax.swing.JPanel ss2;
    private javax.swing.JPanel ss20;
    private javax.swing.JPanel ss22;
    private javax.swing.JPanel ss23;
    private javax.swing.JPanel ss25;
    private javax.swing.JPanel ss26;
    private javax.swing.JPanel ss27;
    private javax.swing.JPanel ss28;
    private javax.swing.JPanel ss29;
    private javax.swing.JPanel ss3;
    private javax.swing.JPanel ss30;
    private javax.swing.JPanel ss38;
    private javax.swing.JPanel ss39;
    private javax.swing.JPanel ss4;
    private javax.swing.JPanel ss40;
    private javax.swing.JPanel ss41;
    private javax.swing.JPanel ss42;
    private javax.swing.JPanel ss43;
    private javax.swing.JPanel ss44;
    private javax.swing.JPanel ss45;
    private javax.swing.JPanel ss46;
    private javax.swing.JPanel ss47;
    private javax.swing.JPanel ss48;
    private javax.swing.JPanel ss49;
    private javax.swing.JPanel ss5;
    private javax.swing.JPanel ss50;
    private javax.swing.JPanel ss6;
    private javax.swing.JPanel ss7;
    private javax.swing.JPanel ss8;
    private javax.swing.JPanel ss9;
    private javax.swing.JTable tblCart;
    private javax.swing.JTable tblCurrentOrders;
    private javax.swing.JTable tblSummary;
    private javax.swing.JTable tblSummary2;
    private javax.swing.JTable tblSummery3;
    private javax.swing.JTextField textAccName;
    private javax.swing.JTextField textAddress;
    private javax.swing.JTextField textAmount;
    private javax.swing.JTextField textAmount2;
    private javax.swing.JTextField textCCV;
    private javax.swing.JTextField textCardName;
    private javax.swing.JTextField textCardNum;
    private javax.swing.JTextField textExDate;
    private javax.swing.JTextField textRefNum;
    private javax.swing.JTextField textUserName;
    private javax.swing.JTextField txtContactNo;
    // End of variables declaration//GEN-END:variables
}
