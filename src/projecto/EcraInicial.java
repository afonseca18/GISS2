
package projecto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class EcraInicial extends javax.swing.JFrame {
    public Connection con;
    Statement smt = null;
    ResultSet rs = null;
    int i;
    int RegiaoID;
    int CentroHID;
    int CentroID;
    int ServicoID;
    int HospitalID;
    int AreaClinicaID;
    /**
     * Creates new form EcraInicial
     */
    public EcraInicial() throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Projecto;user=david;password=emotions24ever;");
        initComponents();
        setRegiao();
    }

    public int getAreaClinicaID() {
        return AreaClinicaID;
    }

    public void setAreaClinicaID(int AreaClinicaID) {
        this.AreaClinicaID = AreaClinicaID;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        RegiaoCB = new javax.swing.JComboBox<>();
        CentroHCB = new javax.swing.JComboBox<>();
        HospitalCB = new javax.swing.JComboBox<>();
        CentroCB = new javax.swing.JComboBox<>();
        ServicoCB = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        AreaClinicaCB = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Hospital");

        jButton1.setText("Avançar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Centro Hospitalar");

        jLabel4.setText("Serviço");

        jLabel5.setText("Centro");

        jLabel6.setText("Área Clinica");

        ServicoCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ServicoCBActionPerformed(evt);
            }
        });

        jLabel1.setText("Região");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RegiaoCB, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ServicoCB, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CentroCB, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HospitalCB, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CentroHCB, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AreaClinicaCB, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(126, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(RegiaoCB)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CentroHCB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HospitalCB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CentroCB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ServicoCB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AreaClinicaCB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (AreaClinicaCB.getItemCount()!=0){
            try {
                smt = con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(EcraInicial.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {          
                rs = smt.executeQuery("SELECT * FROM AreasClinicas WHERE ServicosID= "+ServicoID);
                for(i=0;i<=AreaClinicaCB.getSelectedIndex();i++){
                   rs.next();
                }
                AreaClinicaID = rs.getInt("AreasClinicasID");
            } catch (SQLException ex) {
                Logger.getLogger(EcraInicial.class.getName()).log(Level.SEVERE, null, ex);
            }
            Aplicacoes aplicacao;
            try {
                aplicacao = new Aplicacoes(AreaClinicaID,con);
                aplicacao.setVisible(true);
                this.setVisible(false); 
            } catch (SQLException ex) {
                Logger.getLogger(EcraInicial.class.getName()).log(Level.SEVERE, null, ex);
            }                 
        }
        else{
            JOptionPane.showMessageDialog(null, "Escolha uma Área Clinica");
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ServicoCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ServicoCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ServicoCBActionPerformed

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
            java.util.logging.Logger.getLogger(EcraInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EcraInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EcraInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EcraInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new EcraInicial().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(EcraInicial.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(EcraInicial.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public void setRegiao() throws SQLException{
        smt = con.createStatement();
        rs = smt.executeQuery("SELECT * FROM REGIOES");
        while(rs.next()){
            RegiaoCB.addItem(rs.getString(2));
        }
        ActionListener ServicoAL = new ActionListener() {//add actionlistner to listen for change
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AreaClinicaCB.removeAllItems();
                    rs = smt.executeQuery("SELECT * FROM Servicos WHERE CentrosID = "+CentroID);
                    for(i=0;i<=ServicoCB.getSelectedIndex();i++){
                        rs.next();
                    }
                    ServicoID = rs.getInt("ServicosID");
                    rs = smt.executeQuery("SELECT Nome FROM AreasClinicas WHERE ServicosID = "+ ServicoID);
                    while(rs.next()){
                        AreaClinicaCB.addItem(rs.getString("Nome"));
                    }
                }catch (SQLException ex) {
                    Logger.getLogger(EcraInicial.class.getName()).log(Level.SEVERE, null, ex);
                }              
        }};
        ActionListener CentroAL = new ActionListener() {//add actionlistner to listen for change
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AreaClinicaCB.removeAllItems();
                    ServicoCB.removeActionListener(ServicoAL);
                    ServicoCB.removeAllItems();
                    rs = smt.executeQuery("SELECT * FROM Centros WHERE HospitaisID = "+HospitalID);
                    for(i=0;i<=CentroCB.getSelectedIndex();i++){
                        rs.next();
                    }
                    CentroID = rs.getInt("CentrosID");
                    rs = smt.executeQuery("SELECT Nome FROM Servicos WHERE CentrosID = "+ CentroID);
                    while(rs.next()){
                        ServicoCB.addItem(rs.getString("Nome"));
                    }
                    ServicoCB.addActionListener(ServicoAL);
                }catch (SQLException ex) {
                    Logger.getLogger(EcraInicial.class.getName()).log(Level.SEVERE, null, ex);
                }              
        }};
        ActionListener HospitalAL = new ActionListener() {//add actionlistner to listen for change
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AreaClinicaCB.removeAllItems();
                    ServicoCB.removeActionListener(ServicoAL);
                    ServicoCB.removeAllItems();
                    CentroCB.removeActionListener(CentroAL);
                    CentroCB.removeAllItems();
                    rs = smt.executeQuery("SELECT * FROM Hospitais WHERE CentroHID = "+CentroHID);
                    for(i=0;i<=HospitalCB.getSelectedIndex();i++){
                        rs.next();
                    }
                    HospitalID = rs.getInt("HospitaisID");
                    rs = smt.executeQuery("SELECT Nome FROM Centros WHERE HospitaisID = "+ HospitalID);
                    while(rs.next()){
                        CentroCB.addItem(rs.getString("Nome"));
                    }
                    CentroCB.addActionListener(CentroAL);
                }catch (SQLException ex) {
                    Logger.getLogger(EcraInicial.class.getName()).log(Level.SEVERE, null, ex);
                }              
        }};
        ActionListener CentroHAL = new ActionListener() {//add actionlistner to listen for change
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AreaClinicaCB.removeAllItems();
                    ServicoCB.removeActionListener(ServicoAL);
                    ServicoCB.removeAllItems();
                    CentroCB.removeActionListener(CentroAL);
                    CentroCB.removeAllItems();
                    HospitalCB.removeActionListener(HospitalAL);
                    HospitalCB.removeAllItems();
                    rs = smt.executeQuery("SELECT * FROM Centro_Hospitalar WHERE RegioesID = "+RegiaoID);
                    for(i=0;i<=CentroHCB.getSelectedIndex();i++){
                        rs.next();
                    }
                    CentroHID = rs.getInt("CentroHID");
                    rs = smt.executeQuery("SELECT Nome FROM Hospitais WHERE CentroHID= "+CentroHID);
                    while(rs.next()){
                        HospitalCB.addItem(rs.getString("Nome"));
                    }
                    HospitalCB.addActionListener(HospitalAL);
                }catch (SQLException ex) {
                    Logger.getLogger(EcraInicial.class.getName()).log(Level.SEVERE, null, ex);
                }              
        }};
        ActionListener RegiaoAL = new ActionListener() {//add actionlistner to listen for change
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AreaClinicaCB.removeAllItems();
                    ServicoCB.removeActionListener(ServicoAL);
                    ServicoCB.removeAllItems();
                    CentroCB.removeActionListener(CentroAL);
                    CentroCB.removeAllItems();
                    HospitalCB.removeActionListener(HospitalAL);
                    CentroHCB.removeActionListener(CentroHAL);
                    HospitalCB.removeAllItems();
                    CentroHCB.removeAllItems();
                    rs = smt.executeQuery("SELECT * FROM REGIOES");
                    for(i=0;i<=RegiaoCB.getSelectedIndex();i++){
                        rs.next();
                    }
                    RegiaoID = rs.getInt("RegioesID");
                    rs = smt.executeQuery("SELECT Nome FROM Centro_Hospitalar WHERE RegioesID= "+RegiaoID);
                    while(rs.next()){
                        CentroHCB.addItem(rs.getString("Nome"));
                    }
                    CentroHCB.addActionListener(CentroHAL);
                    }catch (SQLException ex) {
                        Logger.getLogger(EcraInicial.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                
        }};
        RegiaoCB.addActionListener(RegiaoAL);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> AreaClinicaCB;
    private javax.swing.JComboBox<String> CentroCB;
    private javax.swing.JComboBox<String> CentroHCB;
    private javax.swing.JComboBox<String> HospitalCB;
    private javax.swing.JComboBox<String> RegiaoCB;
    private javax.swing.JComboBox<String> ServicoCB;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
