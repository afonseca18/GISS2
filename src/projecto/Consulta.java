
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


public class Consulta extends javax.swing.JFrame {
    
    int AreaClinicaID;
    Connection con;
    ResultSet rs=null;
    Statement smt=null;
    int PacienteID;
    int i;

 
    public Consulta() {
        initComponents();
    }
    
    public Consulta(int AreaClinicaID, Connection con) throws SQLException{
        initComponents();
        this.AreaClinicaID=AreaClinicaID;
        this.con=con;
        smt = con.createStatement();
        rs = smt.executeQuery("SELECT P.Nome FROM Pacientes P,PacienteArea A WHERE P.PacienteID = A.PacienteID AND A.AreasClinicasID = " + AreaClinicaID);
        while(rs.next()){
            pacienteCB.addItem(rs.getString("Nome"));
        }
        
        DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
        
        ActionListener PacienteAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pacienteCB.getSelectedIndex()!= -1){
                     try {
                        rs= smt.executeQuery("SELECT P.PacienteID,P.Nome,P.Idade FROM Pacientes P,PacienteArea A WHERE A.AreasClinicasID = " + AreaClinicaID +" AND A.PacienteID = P.PacienteID");
                        for (i =0;i<=pacienteCB.getSelectedIndex();i++){
                            rs.next();
                        }
                PacienteID = rs.getInt("PacienteID");
                rs=smt.executeQuery("SELECT T.Nome,I.Data, S.Nome, I.HoraI,I.Estado,I.HoraF, I.IntervencaoID\n"+
"                                         FROM Intervencao I,TipoIntervencao T, Salas S, AreasClinicas AC\n" +
"                                         WHERE I.TipoIntervencaoID = T.TipoIntervencaoID\n" +
"                                         AND I.PacienteID = "+PacienteID+"\n" +
"                                         AND I.SalasID= S.SalasID\n" +
                                          " AND I.AreasClinicasID = AC.AreasClinicasID" +
"                                         AND I.AreasClinicasID= "+ AreaClinicaID);
                   
                   DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
                   i=jTable2.getRowCount();
                   while(i!=0){
                       model.removeRow(0);
                       i=jTable2.getRowCount();
                   }
                   i=0;
                   DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
                   Date date = new Date();
                   while(rs.next()){
                        model.addRow(new Object[]{"IntervencaoID","Sala","Efectuada","Tipo de Intervenção", "Data"});
                        model.setValueAt(rs.getInt("IntervencaoID"),i, 0);
                        model.setValueAt(rs.getString(1),i,1);
                        model.setValueAt(rs.getString(3),i,2);
                        model.setValueAt(rs.getInt("Estado"), i, 3);
                       date.setTime(rs.getLong("Data")*1000);
                       model.setValueAt(sourceFormat.format(date) + " Horário:" + rs.getString("HoraI")+ "->" + rs.getString("HoraF"),i,4);
                       i++;
                   }
               } catch (SQLException ex) {
                   Logger.getLogger(MeiosComplementares.class.getName()).log(Level.SEVERE, null, ex);
               }
            }
          
        }};
        pacienteCB.addActionListener(PacienteAL);
        
          
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pacientetv = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        pacienteCB = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Consultas");
        setResizable(false);

        pacientetv.setText("Paciente");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Consultas");

        jButton2.setText("Voltar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Consultar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        pacienteCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pacienteCBActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Intervenção ID", "Tipo de Intervenção", "Sala", "Efectuada", "Data"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(pacientetv)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pacienteCB, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(316, 316, 316)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 372, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pacientetv)
                    .addComponent(pacienteCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(51, 51, 51))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (jTable2.getSelectedColumn()==-1 || jTable2.getSelectedRow()==-1){
            JOptionPane.showMessageDialog(null, "Selecione uma intervenção");
        }
        else{
            int linha= jTable2.getSelectedRow();
           
         
            ConsultaVer consultaVer;
            try {
                consultaVer = new ConsultaVer(Integer.valueOf(jTable2.getValueAt(linha,3).toString()),AreaClinicaID,con,pacienteCB.getSelectedItem().toString(),
                        jTable2.getValueAt(linha,1).toString(),jTable2.getValueAt(linha,2).toString(),
                        Integer.valueOf(jTable2.getValueAt(linha,0).toString()));
                consultaVer.setVisible(true);
                this.setVisible(false);
            } catch (SQLException ex) {
                Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            
         
         }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void pacienteCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pacienteCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pacienteCBActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         try {
            Aplicacoes aplicacoes = new Aplicacoes(AreaClinicaID,con);
            aplicacoes.setVisible(true);
            this.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(MeiosComplementares.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    
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
            java.util.logging.Logger.getLogger(Consulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Consulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JComboBox<String> pacienteCB;
    private javax.swing.JLabel pacientetv;
    // End of variables declaration//GEN-END:variables

}
