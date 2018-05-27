/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Batista
 */
public class MeiosComplementares extends javax.swing.JFrame {
    Connection con;
    int AreaClinicaID;
    ResultSet rs = null;
    Statement smt = null;
    Statement smt2 = null;
    int i =0;
    int PacienteID;
    int IntervencaoID;
    
    public MeiosComplementares() {
        initComponents();
    }
    
    public MeiosComplementares(int AreaClinicaID,Connection con) throws SQLException {
        initComponents();
        this.AreaClinicaID=AreaClinicaID;
        this.con=con;
        smt = con.createStatement();
        rs= smt.executeQuery("SELECT P.Nome,P.Idade FROM Pacientes P,PacienteArea A WHERE A.AreasClinicasID = " + AreaClinicaID +" AND A.PacienteID = P.PacienteID");
        while(rs.next()){
            PacienteCB.addItem(rs.getString("Nome"));
        }
        ActionListener PacienteAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            if(PacienteCB.getSelectedIndex()!= -1){
               try {
                   rs= smt.executeQuery("SELECT P.PacienteID,P.Nome,P.Idade FROM Pacientes P,PacienteArea A WHERE A.AreasClinicasID = " + AreaClinicaID +" AND A.PacienteID = P.PacienteID");
                   for (i =0;i<=PacienteCB.getSelectedIndex();i++){
                       rs.next();
                   }
                   PacienteID = rs.getInt("PacienteID");
                   rs=smt.executeQuery("SELECT T.Nome,I.Data FROM Intervencao I,TipoIntervencao T "
                                      + "WHERE I.TipoIntervencaoID = T.TipoIntervencaoID "
                                      + "AND I.PacienteID = " +PacienteID+" "
                                      + "AND I.AreaSClinicaSID = "+ AreaClinicaID + "AND I.Estado = 0");
                   DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
                   i=jTable1.getRowCount();
                   while(i!=0){
                       model.removeRow(0);
                       i=jTable1.getRowCount();
                   }
                   i=0;
                   DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
                   Date date = new Date();
                   while(rs.next()){
                       model.addRow(new Object[]{"Paciente", "Tipo de Intervenção", "Data"});
                       model.setValueAt(PacienteCB.getItemAt(PacienteCB.getSelectedIndex()), i, 0);
                       model.setValueAt(rs.getString("Nome"), i, 1);
                       date.setTime(rs.getLong("Data")*1000);
                       model.setValueAt(sourceFormat.format(date),i,2);
                       i++;
                   }
               } catch (SQLException ex) {
                   Logger.getLogger(MeiosComplementares.class.getName()).log(Level.SEVERE, null, ex);
               }
            }
          descricaoET.setEditable(false);
          descricaoET.setText("");
        }};
        PacienteCB.addActionListener(PacienteAL);
    }

          
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        PacienteCB = new javax.swing.JComboBox<>();
        IdadeTV = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        descricaoET = new javax.swing.JTextArea();
        Adicionar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Novo Registo");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Paciente", "Tipo de Intervenção", "Data"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Paciente");

        PacienteCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PacienteCBActionPerformed(evt);
            }
        });

        jLabel2.setText("Consultas Pendentes");

        descricaoET.setEditable(false);
        descricaoET.setColumns(20);
        descricaoET.setRows(5);
        jScrollPane2.setViewportView(descricaoET);

        Adicionar.setText("Adicionar Detalhes");
        Adicionar.setToolTipText("");
        Adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdicionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(PacienteCB, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(IdadeTV, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(PacienteCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IdadeTV, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Adicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(82, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PacienteCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PacienteCBActionPerformed
    }//GEN-LAST:event_PacienteCBActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int x = jTable1.getSelectedRow();
        try {
            rs=smt.executeQuery("SELECT IntervencaoID FROM Intervencao I,TipoIntervencao T "
                    + "WHERE I.TipoIntervencaoID = T.TipoIntervencaoID "
                    + "AND I.PacienteID = " +PacienteID+" "
                            + "AND I.AreaSClinicaSID = "+ AreaClinicaID + "AND I.Estado = 0");
            for (i=0;i<=x;i++){
                rs.next();
            }
            descricaoET.setEditable(true);
            IntervencaoID=rs.getInt("IntervencaoID");
        } catch (SQLException ex) {
            Logger.getLogger(MeiosComplementares.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }//GEN-LAST:event_jTable1MouseClicked

    private void AdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdicionarActionPerformed
        if(descricaoET.isEditable()){
            try {
                smt2 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                rs = smt2.executeQuery("SELECT Descricao,Estado FROM Intervencao WHERE IntervencaoID = " + IntervencaoID);
                rs.next();
                rs.updateString("Descricao", descricaoET.getText());
                rs.updateInt("Estado", 1);
                rs.updateRow();
                JOptionPane.showMessageDialog(null, "Dados atualizados");
                MeiosComplementares meios = new MeiosComplementares(AreaClinicaID,con);
                meios.setVisible(true);
                this.setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(MeiosComplementares.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_AdicionarActionPerformed

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
            java.util.logging.Logger.getLogger(MeiosComplementares.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MeiosComplementares.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MeiosComplementares.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MeiosComplementares.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MeiosComplementares().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Adicionar;
    private javax.swing.JLabel IdadeTV;
    private javax.swing.JComboBox<String> PacienteCB;
    private javax.swing.JTextArea descricaoET;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
