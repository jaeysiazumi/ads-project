/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package adsfinalproject;

import java.awt.CardLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

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
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CustomerDashboard.class.getName());

    /**
     * Creates new form CustomerDashboard
     */
    public CustomerDashboard() {
        initComponents();
        setSize(1300, 800);
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
            private void setOrderDeets(String type, String payment, String dateTime, int orderNo){
               
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
        jLabel49 = new javax.swing.JLabel();
        lblChange1 = new javax.swing.JLabel();
        lblTotal2 = new javax.swing.JLabel();
        lblOrderNum2 = new javax.swing.JLabel();
        lblOrderStat2 = new javax.swing.JLabel();
        lblDateTime2 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel48 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel51 = new javax.swing.JLabel();
        lblChange = new javax.swing.JLabel();
        lblTotal1 = new javax.swing.JLabel();
        lblOrderNum1 = new javax.swing.JLabel();
        lblOrderStat1 = new javax.swing.JLabel();
        lblDateTime1 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        pnlOrders = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        lblTotal = new javax.swing.JLabel();
        lblOrderStat = new javax.swing.JLabel();
        lblDateTime = new javax.swing.JLabel();
        lblOrderNum = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
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
        jTable5 = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        Dish = new javax.swing.JPanel();
        snacks = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();
        ss43 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        ss42 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        ss41 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        ss40 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        ss39 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        ss38 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        drinks = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        ss37 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        ss36 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        ss35 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        ss34 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        ss33 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        ss32 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        ss31 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        noodles = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        ss30 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        ss29 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        ss28 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        ss27 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        ss26 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        ss25 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        bfMeals = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        ss22 = new javax.swing.JPanel();
        ss23 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        ss20 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        ss19 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        ss18 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        ss17 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        ss16 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        menuRice = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        ss15 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        ss14 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        ss13 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        ss12 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        ss11 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        ss10 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        ss9 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        ss8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        ss7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        ss6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        ss5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        ss4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        ss3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        ss2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        ss1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        ss = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnDrinks = new javax.swing.JButton();
        btnSnacks = new javax.swing.JButton();
        btnBreakfast = new javax.swing.JButton();
        btnNoodles = new javax.swing.JButton();
        btnRice = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1400, 912));
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
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-220, -22, -1, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 1050, 50));

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

        jLabel49.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("payment method");
        jPanel11.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 200, 50));

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

        jTextField11.setBackground(new java.awt.Color(255, 255, 255));
        jTextField11.setForeground(new java.awt.Color(0, 0, 0));
        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });
        jPanel11.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 550, 130, 30));

        jTextField13.setBackground(new java.awt.Color(255, 255, 255));
        jTextField13.setForeground(new java.awt.Color(0, 0, 0));
        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });
        jPanel11.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 470, 390, 30));

        jTextField14.setBackground(new java.awt.Color(255, 255, 255));
        jTextField14.setForeground(new java.awt.Color(0, 0, 0));
        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });
        jPanel11.add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 390, 390, 30));

        jButton2.setText("-");
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jPanel11.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 713, 420, 60));

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));

        jTable4.setBackground(new java.awt.Color(255, 255, 255));
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(jTable4);

        jPanel11.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 440, 330, 220));

        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/e-wallet Payment.png"))); // NOI18N
        jPanel11.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pnlPayment.add(jPanel11, "card2");

        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));

        jTable3.setBackground(new java.awt.Color(255, 255, 255));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jTable3);

        jPanel10.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 440, 330, 220));

        jLabel51.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("payment method");
        jPanel10.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 200, 50));

        lblChange.setFont(new java.awt.Font("SimSun-ExtB", 1, 12)); // NOI18N
        lblChange.setForeground(new java.awt.Color(0, 0, 0));
        lblChange.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblChange.setText("-");
        jPanel10.add(lblChange, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 690, 100, 40));

        lblTotal1.setFont(new java.awt.Font("SimSun-ExtB", 1, 12)); // NOI18N
        lblTotal1.setForeground(new java.awt.Color(0, 0, 0));
        lblTotal1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTotal1.setText("-");
        jPanel10.add(lblTotal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 680, 100, 30));

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

        jTextField9.setBackground(new java.awt.Color(255, 255, 255));
        jTextField9.setForeground(new java.awt.Color(0, 0, 0));
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });
        jPanel10.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 640, 130, 30));

        jTextField8.setBackground(new java.awt.Color(255, 255, 255));
        jTextField8.setForeground(new java.awt.Color(0, 0, 0));
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jPanel10.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 570, 130, 30));

        jTextField7.setBackground(new java.awt.Color(255, 255, 255));
        jTextField7.setForeground(new java.awt.Color(0, 0, 0));
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        jPanel10.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 568, 130, 30));

        jTextField6.setBackground(new java.awt.Color(255, 255, 255));
        jTextField6.setForeground(new java.awt.Color(0, 0, 0));
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jPanel10.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 500, 390, 30));

        jTextField5.setBackground(new java.awt.Color(255, 255, 255));
        jTextField5.setForeground(new java.awt.Color(0, 0, 0));
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jPanel10.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 430, 390, 30));

        jButton1.setText("-");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jPanel10.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 713, 420, 60));

        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/CC Payment.png"))); // NOI18N
        jPanel10.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pnlPayment.add(jPanel10, "card2");

        jPanel1.add(pnlPayment, "pay");

        pnlOrders.setBackground(new java.awt.Color(255, 255, 255));
        pnlOrders.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setBackground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable1);

        pnlOrders.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 350, 330, 230));

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));

        jTable2.setBackground(new java.awt.Color(255, 255, 255));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable2);

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

        jTextField3.setBackground(new java.awt.Color(255, 255, 255));
        jTextField3.setForeground(new java.awt.Color(0, 0, 0));
        pnlOrders.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 600, 410, 30));

        jTextField2.setBackground(new java.awt.Color(255, 255, 255));
        jTextField2.setForeground(new java.awt.Color(0, 0, 0));
        pnlOrders.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 560, 410, 30));

        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jTextField1.setForeground(new java.awt.Color(0, 0, 0));
        pnlOrders.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 520, 410, 30));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Order.png"))); // NOI18N
        pnlOrders.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 800));

        jPanel1.add(pnlOrders, "orders");

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

        jTable5.setBackground(new java.awt.Color(255, 255, 255));
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(jTable5);

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

        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Snack 6.png"))); // NOI18N

        javax.swing.GroupLayout ss43Layout = new javax.swing.GroupLayout(ss43);
        ss43.setLayout(ss43Layout);
        ss43Layout.setHorizontalGroup(
            ss43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel46)
        );
        ss43Layout.setVerticalGroup(
            ss43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel46)
        );

        jPanel8.add(ss43);

        ss42.setBackground(new java.awt.Color(255, 255, 255));

        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Snack 5.png"))); // NOI18N

        javax.swing.GroupLayout ss42Layout = new javax.swing.GroupLayout(ss42);
        ss42.setLayout(ss42Layout);
        ss42Layout.setHorizontalGroup(
            ss42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel45)
        );
        ss42Layout.setVerticalGroup(
            ss42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel45)
        );

        jPanel8.add(ss42);

        ss41.setBackground(new java.awt.Color(255, 255, 255));

        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Snack 4.png"))); // NOI18N

        javax.swing.GroupLayout ss41Layout = new javax.swing.GroupLayout(ss41);
        ss41.setLayout(ss41Layout);
        ss41Layout.setHorizontalGroup(
            ss41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel44)
        );
        ss41Layout.setVerticalGroup(
            ss41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel44)
        );

        jPanel8.add(ss41);

        ss40.setBackground(new java.awt.Color(255, 255, 255));

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Snack 3.png"))); // NOI18N

        javax.swing.GroupLayout ss40Layout = new javax.swing.GroupLayout(ss40);
        ss40.setLayout(ss40Layout);
        ss40Layout.setHorizontalGroup(
            ss40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel43)
        );
        ss40Layout.setVerticalGroup(
            ss40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel43)
        );

        jPanel8.add(ss40);

        ss39.setBackground(new java.awt.Color(255, 255, 255));

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Snack 2.png"))); // NOI18N

        javax.swing.GroupLayout ss39Layout = new javax.swing.GroupLayout(ss39);
        ss39.setLayout(ss39Layout);
        ss39Layout.setHorizontalGroup(
            ss39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel42)
        );
        ss39Layout.setVerticalGroup(
            ss39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel42)
        );

        jPanel8.add(ss39);

        ss38.setBackground(new java.awt.Color(255, 255, 255));

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/Snack 1.png"))); // NOI18N

        javax.swing.GroupLayout ss38Layout = new javax.swing.GroupLayout(ss38);
        ss38.setLayout(ss38Layout);
        ss38Layout.setHorizontalGroup(
            ss38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel41)
        );
        ss38Layout.setVerticalGroup(
            ss38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel41)
        );

        jPanel8.add(ss38);

        snacks.setViewportView(jPanel8);

        Dish.add(snacks, "snacks");

        drinks.setBackground(new java.awt.Color(255, 255, 255));
        drinks.setBorder(null);
        drinks.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.GridLayout(0, 3, 20, 20));

        ss37.setBackground(new java.awt.Color(255, 255, 255));

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/drinks 7.png"))); // NOI18N

        javax.swing.GroupLayout ss37Layout = new javax.swing.GroupLayout(ss37);
        ss37.setLayout(ss37Layout);
        ss37Layout.setHorizontalGroup(
            ss37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel40)
        );
        ss37Layout.setVerticalGroup(
            ss37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel40)
        );

        jPanel7.add(ss37);

        ss36.setBackground(new java.awt.Color(255, 255, 255));

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/drinks 6.png"))); // NOI18N

        javax.swing.GroupLayout ss36Layout = new javax.swing.GroupLayout(ss36);
        ss36.setLayout(ss36Layout);
        ss36Layout.setHorizontalGroup(
            ss36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel39)
        );
        ss36Layout.setVerticalGroup(
            ss36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel39)
        );

        jPanel7.add(ss36);

        ss35.setBackground(new java.awt.Color(255, 255, 255));

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/drinks 5.png"))); // NOI18N

        javax.swing.GroupLayout ss35Layout = new javax.swing.GroupLayout(ss35);
        ss35.setLayout(ss35Layout);
        ss35Layout.setHorizontalGroup(
            ss35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel38)
        );
        ss35Layout.setVerticalGroup(
            ss35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel38)
        );

        jPanel7.add(ss35);

        ss34.setBackground(new java.awt.Color(255, 255, 255));

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/drinks 4.png"))); // NOI18N

        javax.swing.GroupLayout ss34Layout = new javax.swing.GroupLayout(ss34);
        ss34.setLayout(ss34Layout);
        ss34Layout.setHorizontalGroup(
            ss34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel37)
        );
        ss34Layout.setVerticalGroup(
            ss34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel37)
        );

        jPanel7.add(ss34);

        ss33.setBackground(new java.awt.Color(255, 255, 255));

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/drinks 3.png"))); // NOI18N

        javax.swing.GroupLayout ss33Layout = new javax.swing.GroupLayout(ss33);
        ss33.setLayout(ss33Layout);
        ss33Layout.setHorizontalGroup(
            ss33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel36)
        );
        ss33Layout.setVerticalGroup(
            ss33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel36)
        );

        jPanel7.add(ss33);

        ss32.setBackground(new java.awt.Color(255, 255, 255));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/drinks 2.png"))); // NOI18N

        javax.swing.GroupLayout ss32Layout = new javax.swing.GroupLayout(ss32);
        ss32.setLayout(ss32Layout);
        ss32Layout.setHorizontalGroup(
            ss32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel35)
        );
        ss32Layout.setVerticalGroup(
            ss32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel35)
        );

        jPanel7.add(ss32);

        ss31.setBackground(new java.awt.Color(255, 255, 255));

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/drinks 1.png"))); // NOI18N

        javax.swing.GroupLayout ss31Layout = new javax.swing.GroupLayout(ss31);
        ss31.setLayout(ss31Layout);
        ss31Layout.setHorizontalGroup(
            ss31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel34)
        );
        ss31Layout.setVerticalGroup(
            ss31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel34)
        );

        jPanel7.add(ss31);

        drinks.setViewportView(jPanel7);

        Dish.add(drinks, "drinks");

        noodles.setBackground(new java.awt.Color(255, 255, 255));
        noodles.setBorder(null);
        noodles.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        noodles.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new java.awt.GridLayout(0, 3, 20, 20));

        ss30.setBackground(new java.awt.Color(255, 255, 255));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish 29.png"))); // NOI18N

        javax.swing.GroupLayout ss30Layout = new javax.swing.GroupLayout(ss30);
        ss30.setLayout(ss30Layout);
        ss30Layout.setHorizontalGroup(
            ss30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel33)
        );
        ss30Layout.setVerticalGroup(
            ss30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel33)
        );

        jPanel6.add(ss30);

        ss29.setBackground(new java.awt.Color(255, 255, 255));

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish 28.png"))); // NOI18N

        javax.swing.GroupLayout ss29Layout = new javax.swing.GroupLayout(ss29);
        ss29.setLayout(ss29Layout);
        ss29Layout.setHorizontalGroup(
            ss29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel32)
        );
        ss29Layout.setVerticalGroup(
            ss29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel32)
        );

        jPanel6.add(ss29);

        ss28.setBackground(new java.awt.Color(255, 255, 255));

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish 27.png"))); // NOI18N

        javax.swing.GroupLayout ss28Layout = new javax.swing.GroupLayout(ss28);
        ss28.setLayout(ss28Layout);
        ss28Layout.setHorizontalGroup(
            ss28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel31)
        );
        ss28Layout.setVerticalGroup(
            ss28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel31)
        );

        jPanel6.add(ss28);

        ss27.setBackground(new java.awt.Color(255, 255, 255));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish 26.png"))); // NOI18N

        javax.swing.GroupLayout ss27Layout = new javax.swing.GroupLayout(ss27);
        ss27.setLayout(ss27Layout);
        ss27Layout.setHorizontalGroup(
            ss27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel30)
        );
        ss27Layout.setVerticalGroup(
            ss27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel30)
        );

        jPanel6.add(ss27);

        ss26.setBackground(new java.awt.Color(255, 255, 255));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish 25.png"))); // NOI18N

        javax.swing.GroupLayout ss26Layout = new javax.swing.GroupLayout(ss26);
        ss26.setLayout(ss26Layout);
        ss26Layout.setHorizontalGroup(
            ss26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel29)
        );
        ss26Layout.setVerticalGroup(
            ss26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel29)
        );

        jPanel6.add(ss26);

        ss25.setBackground(new java.awt.Color(255, 255, 255));

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish 24.png"))); // NOI18N

        javax.swing.GroupLayout ss25Layout = new javax.swing.GroupLayout(ss25);
        ss25.setLayout(ss25Layout);
        ss25Layout.setHorizontalGroup(
            ss25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel28)
        );
        ss25Layout.setVerticalGroup(
            ss25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel28)
        );

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

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish 23.png"))); // NOI18N

        javax.swing.GroupLayout ss23Layout = new javax.swing.GroupLayout(ss23);
        ss23.setLayout(ss23Layout);
        ss23Layout.setHorizontalGroup(
            ss23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel27)
        );
        ss23Layout.setVerticalGroup(
            ss23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel27)
        );

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

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish 21.png"))); // NOI18N

        javax.swing.GroupLayout ss20Layout = new javax.swing.GroupLayout(ss20);
        ss20.setLayout(ss20Layout);
        ss20Layout.setHorizontalGroup(
            ss20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel24)
        );
        ss20Layout.setVerticalGroup(
            ss20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel24)
        );

        jPanel4.add(ss20);

        ss19.setBackground(new java.awt.Color(255, 255, 255));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish 20.png"))); // NOI18N

        javax.swing.GroupLayout ss19Layout = new javax.swing.GroupLayout(ss19);
        ss19.setLayout(ss19Layout);
        ss19Layout.setHorizontalGroup(
            ss19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23)
        );
        ss19Layout.setVerticalGroup(
            ss19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23)
        );

        jPanel4.add(ss19);

        ss18.setBackground(new java.awt.Color(255, 255, 255));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish 19.png"))); // NOI18N

        javax.swing.GroupLayout ss18Layout = new javax.swing.GroupLayout(ss18);
        ss18.setLayout(ss18Layout);
        ss18Layout.setHorizontalGroup(
            ss18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22)
        );
        ss18Layout.setVerticalGroup(
            ss18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22)
        );

        jPanel4.add(ss18);

        ss17.setBackground(new java.awt.Color(255, 255, 255));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish 18.png"))); // NOI18N

        javax.swing.GroupLayout ss17Layout = new javax.swing.GroupLayout(ss17);
        ss17.setLayout(ss17Layout);
        ss17Layout.setHorizontalGroup(
            ss17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel21)
        );
        ss17Layout.setVerticalGroup(
            ss17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel21)
        );

        jPanel4.add(ss17);

        ss16.setBackground(new java.awt.Color(255, 255, 255));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish 17.png"))); // NOI18N

        javax.swing.GroupLayout ss16Layout = new javax.swing.GroupLayout(ss16);
        ss16.setLayout(ss16Layout);
        ss16Layout.setHorizontalGroup(
            ss16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20)
        );
        ss16Layout.setVerticalGroup(
            ss16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20)
        );

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

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish.png"))); // NOI18N

        javax.swing.GroupLayout ss15Layout = new javax.swing.GroupLayout(ss15);
        ss15.setLayout(ss15Layout);
        ss15Layout.setHorizontalGroup(
            ss15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel19)
        );
        ss15Layout.setVerticalGroup(
            ss15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel19)
        );

        jPanel2.add(ss15);

        ss14.setBackground(new java.awt.Color(255, 255, 255));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish.png"))); // NOI18N

        javax.swing.GroupLayout ss14Layout = new javax.swing.GroupLayout(ss14);
        ss14.setLayout(ss14Layout);
        ss14Layout.setHorizontalGroup(
            ss14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel18)
        );
        ss14Layout.setVerticalGroup(
            ss14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel18)
        );

        jPanel2.add(ss14);

        ss13.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish.png"))); // NOI18N

        javax.swing.GroupLayout ss13Layout = new javax.swing.GroupLayout(ss13);
        ss13.setLayout(ss13Layout);
        ss13Layout.setHorizontalGroup(
            ss13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17)
        );
        ss13Layout.setVerticalGroup(
            ss13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17)
        );

        jPanel2.add(ss13);

        ss12.setBackground(new java.awt.Color(255, 255, 255));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish.png"))); // NOI18N

        javax.swing.GroupLayout ss12Layout = new javax.swing.GroupLayout(ss12);
        ss12.setLayout(ss12Layout);
        ss12Layout.setHorizontalGroup(
            ss12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16)
        );
        ss12Layout.setVerticalGroup(
            ss12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16)
        );

        jPanel2.add(ss12);

        ss11.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish.png"))); // NOI18N

        javax.swing.GroupLayout ss11Layout = new javax.swing.GroupLayout(ss11);
        ss11.setLayout(ss11Layout);
        ss11Layout.setHorizontalGroup(
            ss11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15)
        );
        ss11Layout.setVerticalGroup(
            ss11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15)
        );

        jPanel2.add(ss11);

        ss10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish.png"))); // NOI18N

        javax.swing.GroupLayout ss10Layout = new javax.swing.GroupLayout(ss10);
        ss10.setLayout(ss10Layout);
        ss10Layout.setHorizontalGroup(
            ss10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14)
        );
        ss10Layout.setVerticalGroup(
            ss10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14)
        );

        jPanel2.add(ss10);

        ss9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish.png"))); // NOI18N

        javax.swing.GroupLayout ss9Layout = new javax.swing.GroupLayout(ss9);
        ss9.setLayout(ss9Layout);
        ss9Layout.setHorizontalGroup(
            ss9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13)
        );
        ss9Layout.setVerticalGroup(
            ss9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13)
        );

        jPanel2.add(ss9);

        ss8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish.png"))); // NOI18N

        javax.swing.GroupLayout ss8Layout = new javax.swing.GroupLayout(ss8);
        ss8.setLayout(ss8Layout);
        ss8Layout.setHorizontalGroup(
            ss8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12)
        );
        ss8Layout.setVerticalGroup(
            ss8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12)
        );

        jPanel2.add(ss8);

        ss7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish.png"))); // NOI18N

        javax.swing.GroupLayout ss7Layout = new javax.swing.GroupLayout(ss7);
        ss7.setLayout(ss7Layout);
        ss7Layout.setHorizontalGroup(
            ss7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11)
        );
        ss7Layout.setVerticalGroup(
            ss7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11)
        );

        jPanel2.add(ss7);

        ss6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish.png"))); // NOI18N

        javax.swing.GroupLayout ss6Layout = new javax.swing.GroupLayout(ss6);
        ss6.setLayout(ss6Layout);
        ss6Layout.setHorizontalGroup(
            ss6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10)
        );
        ss6Layout.setVerticalGroup(
            ss6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10)
        );

        jPanel2.add(ss6);

        ss5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish.png"))); // NOI18N

        javax.swing.GroupLayout ss5Layout = new javax.swing.GroupLayout(ss5);
        ss5.setLayout(ss5Layout);
        ss5Layout.setHorizontalGroup(
            ss5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9)
        );
        ss5Layout.setVerticalGroup(
            ss5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9)
        );

        jPanel2.add(ss5);

        ss4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish.png"))); // NOI18N

        javax.swing.GroupLayout ss4Layout = new javax.swing.GroupLayout(ss4);
        ss4.setLayout(ss4Layout);
        ss4Layout.setHorizontalGroup(
            ss4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8)
        );
        ss4Layout.setVerticalGroup(
            ss4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8)
        );

        jPanel2.add(ss4);

        ss3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish.png"))); // NOI18N

        javax.swing.GroupLayout ss3Layout = new javax.swing.GroupLayout(ss3);
        ss3.setLayout(ss3Layout);
        ss3Layout.setHorizontalGroup(
            ss3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7)
        );
        ss3Layout.setVerticalGroup(
            ss3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7)
        );

        jPanel2.add(ss3);

        ss2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish.png"))); // NOI18N

        javax.swing.GroupLayout ss2Layout = new javax.swing.GroupLayout(ss2);
        ss2.setLayout(ss2Layout);
        ss2Layout.setHorizontalGroup(
            ss2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6)
        );
        ss2Layout.setVerticalGroup(
            ss2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6)
        );

        jPanel2.add(ss2);

        ss1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish.png"))); // NOI18N

        javax.swing.GroupLayout ss1Layout = new javax.swing.GroupLayout(ss1);
        ss1.setLayout(ss1Layout);
        ss1Layout.setHorizontalGroup(
            ss1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5)
        );
        ss1Layout.setVerticalGroup(
            ss1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5)
        );

        jPanel2.add(ss1);

        ss.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/dish.png"))); // NOI18N

        javax.swing.GroupLayout ssLayout = new javax.swing.GroupLayout(ss);
        ss.setLayout(ssLayout);
        ssLayout.setHorizontalGroup(
            ssLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4)
        );
        ssLayout.setVerticalGroup(
            ssLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4)
        );

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
        pnlDish.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 800));

        jPanel1.add(pnlDish, "dish");

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 800));

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
    }//GEN-LAST:event_btnDineActionPerformed

    private void btnTakeOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTakeOutActionPerformed
        setOrderType(btnTakeOut);
    }//GEN-LAST:event_btnTakeOutActionPerformed

    private void btnDeliveryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeliveryActionPerformed
        setOrderType(btnDelivery);
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

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void btnCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCashActionPerformed
        selectedPayment = "cash";
    }//GEN-LAST:event_btnCashActionPerformed

    private void btnCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCCActionPerformed
        selectedPayment = "credit card";
    }//GEN-LAST:event_btnCCActionPerformed

    private void btnEWalletActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEWalletActionPerformed
        selectedPayment = "e-wallet";
    }//GEN-LAST:event_btnEWalletActionPerformed

    private void btnPlaceOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlaceOrderActionPerformed
       
    }//GEN-LAST:event_btnPlaceOrderActionPerformed

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
    private javax.swing.JButton btnBreakfast;
    private javax.swing.JButton btnCC;
    private javax.swing.JButton btnCart;
    private javax.swing.JButton btnCash;
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
    private javax.swing.JButton btnRice;
    private javax.swing.JButton btnSnacks;
    private javax.swing.JButton btnTakeOut;
    private javax.swing.JScrollPane drinks;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lblChange;
    private javax.swing.JLabel lblChange1;
    private javax.swing.JLabel lblDateTime;
    private javax.swing.JLabel lblDateTime1;
    private javax.swing.JLabel lblDateTime2;
    private javax.swing.JLabel lblOrderNum;
    private javax.swing.JLabel lblOrderNum1;
    private javax.swing.JLabel lblOrderNum2;
    private javax.swing.JLabel lblOrderStat;
    private javax.swing.JLabel lblOrderStat1;
    private javax.swing.JLabel lblOrderStat2;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotal1;
    private javax.swing.JLabel lblTotal2;
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
    private javax.swing.JPanel ss31;
    private javax.swing.JPanel ss32;
    private javax.swing.JPanel ss33;
    private javax.swing.JPanel ss34;
    private javax.swing.JPanel ss35;
    private javax.swing.JPanel ss36;
    private javax.swing.JPanel ss37;
    private javax.swing.JPanel ss38;
    private javax.swing.JPanel ss39;
    private javax.swing.JPanel ss4;
    private javax.swing.JPanel ss40;
    private javax.swing.JPanel ss41;
    private javax.swing.JPanel ss42;
    private javax.swing.JPanel ss43;
    private javax.swing.JPanel ss5;
    private javax.swing.JPanel ss6;
    private javax.swing.JPanel ss7;
    private javax.swing.JPanel ss8;
    private javax.swing.JPanel ss9;
    // End of variables declaration//GEN-END:variables
}
