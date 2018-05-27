/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Batista
 */
public class MeiosComplementaresRegisto extends javax.swing.JFrame {

    int AreaClinicaID;
    Connection con;
    Date hoje;
    Date atual;
    ResultSet rs=null;
    Statement smt=null;
    int i;
    Long hojeL;
    String descricao ;
    int IntervencaoID ;
    int PacientID;
    int SalaID;
    Statement smt2;
    
    public MeiosComplementaresRegisto() {
        initComponents();
    }
    
    public MeiosComplementaresRegisto(int AreaClinicaID,Connection con) throws SQLException {
        initComponents();
        this.AreaClinicaID=AreaClinicaID;
        this.con=con;
        smt = con.createStatement();
        rs = smt.executeQuery("SELECT P.Nome FROM Pacientes P,PacienteArea A WHERE A.AreasClinicasID = "+ AreaClinicaID + " AND A.PacienteID = P.PacienteID");
        while (rs.next()){
            pacienteCB.addItem(rs.getString("Nome"));
        }
        rs = smt.executeQuery("SELECT Nome FROM TipoIntervencao");
        while (rs.next()){
            tipoCB.addItem(rs.getString("Nome"));
        }
        hoje = new Date();
        hoje.setHours(23);
        hoje.setMinutes(59);
        hoje.setSeconds(0);
        hojeL = hoje.getTime()/1000;
        rs = smt.executeQuery("SELECT Nome FROM TipoIntervencao");
        while (rs.next()){
            tipoCB.addItem(rs.getString("Nome"));
        }
        atual = new Date();
        rs = smt.executeQuery("SELECT DISTINCT C.Nome,E.Nome,C.ColaboradoresID FROM Colaboradores C,"
                + " Especialidades E,Turnos T,ColabArea CA,"
                + " ColaboradoresTurnos CT,ColaboradoresEspecialidades CE,AreasClinicas AC " +
                                "WHERE CA.AreasClinicasID = "+AreaClinicaID +
                                " AND CA.ColaboradoresID = C.ColaboradoresID " +
                                "AND CA.AreasClinicasID = AC.AreasClinicasID " +
                                "AND CE.ColaboradoresID = C.ColaboradoresID " +
                                "AND CE.EspecialidadesID = E.EspecialidadesID " +
                                "AND C.ColaboradoresID = CT.ColaboradoresID " +
                                "AND CT.TurnosID = T.TurnosID " +
                                "AND CT.data = "+hojeL +
                                " AND T.HoraI <= "+atual.getHours() +
                                " AND T.HoraF >= "+ atual.getHours() +
                                "AND NOT EXISTS ( SELECT ColaboradoresID FROM Equipa E,Intervencao I \n" +
"						  WHERE E.IntervencaoID=I.IntervencaoID \n" +
"						  AND(I.Data = "+hojeL + "OR T.HoraI = "+atual.getHours() +"))");
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        i=jTable1.getRowCount();
        while(i!=0){
            model.removeRow(0);
            i=jTable1.getRowCount();
        }
        i=0;
        while(rs.next()){
            model.addRow(new Object[]{"Nome", "Especialidade", "Participou","ID"});
            model.setValueAt(rs.getString(1), i, 0);
            model.setValueAt(rs.getString(2), i, 1);
            model.setValueAt(false, i, 2);
            model.setValueAt(rs.getInt(3), i, 3);
            i++;
        }
        rs = smt.executeQuery("SELECT DISTINCT S.Nome From Salas S,Intervencao I \n" +
"					   WHERE S.SalasID = I.SalasID \n" +
"					   AND S.AreasClinicasID = 5 \n" +
"					   AND ( I.Data = " +hojeL + "\n" +
"							OR HoraI <> " + atual.getHours()+")");
        while(rs.next()){
            salaCB.addItem(rs.getString("Nome"));
        }
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pacienteCB = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        descricaoET = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        tipoCB = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        salaCB = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Paciente");

        pacienteCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pacienteCBActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Especialidade", "Participou", "ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        descricaoET.setColumns(20);
        descricaoET.setRows(5);
        jScrollPane2.setViewportView(descricaoET);

        jLabel2.setText("Descrição");

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Voltar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Tipo de Intervençao");

        jLabel4.setText("Salas");

        salaCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salaCBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2)
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(pacienteCB, 0, 185, Short.MAX_VALUE)
                                    .addComponent(tipoCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(salaCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 431, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pacienteCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tipoCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(salaCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(48, 48, 48))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        descricao = descricaoET.getText();
        IntervencaoID = (tipoCB.getSelectedIndex()+1);
        try {
            rs = smt.executeQuery("SELECT P.PacienteID,P.Nome FROM Pacientes P,PacienteArea A WHERE A.AreasClinicasID = "+ AreaClinicaID + " AND A.PacienteID = P.PacienteID");
            for (i=0;i<=pacienteCB.getSelectedIndex();i++){
                rs.next();
            }
            PacientID=rs.getInt(1);
            rs = smt.executeQuery("SELECT DISTINCT S.SalasID,S.Nome From Salas S,Intervencao I \n" +
"					   WHERE S.SalasID = I.SalasID \n" +
"					   AND S.AreasClinicasID = 5 \n" +
"					   AND ( I.Data = " +hojeL + "\n" +
"							OR HoraI <> " + atual.getHours()+")");
            for (i=0;i<=salaCB.getSelectedIndex();i++){
                rs.next();
            }
            SalaID=rs.getInt("SalasID");         
            smt2=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = smt2.executeQuery("SELECT * FROM Intervencao");
            rs.moveToInsertRow();
            rs.updateInt("AreasClinicasID", AreaClinicaID);
            rs.updateInt("SalasID",SalaID);
            rs.updateInt("TipoIntervencaoID",IntervencaoID);
            rs.updateInt("Estado",1);
            rs.updateInt("PacienteID",PacientID);
            rs.updateString("Descricao",descricao);
            rs.updateInt("HoraI", atual.getHours());
            rs.updateInt("HoraF", atual.getHours());
            rs.updateLong("Data", hojeL);
            rs.insertRow();
            rs.moveToCurrentRow();
            rs = smt2.executeQuery("SELECT * FROM Intervencao ORDER BY IntervencaoID DESC");
            rs.next();
            IntervencaoID = rs.getInt("IntervencaoID");
           
            for(i=0;i<jTable1.getRowCount();i++){
                rs = smt2.executeQuery("SELECT * FROM Equipa");            
                if (jTable1.getValueAt(i,2).equals(true)){
                    System.out.println(i);
                    rs.moveToInsertRow();
                    rs.updateInt("ColaboradoresID",Integer.valueOf(jTable1.getValueAt(i, 3).toString()));
                    rs.updateInt("IntervencaoID", IntervencaoID);
                    rs.insertRow();
                    rs.moveToCurrentRow();
                }
            }           
        } catch (SQLException ex) {
            Logger.getLogger(MeiosComplementaresRegisto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void salaCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salaCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_salaCBActionPerformed

    private void pacienteCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pacienteCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pacienteCBActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            MeiosComplementares meios = new MeiosComplementares(AreaClinicaID,con);
            meios.setVisible(true);
            this.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(MeiosComplementaresRegisto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MeiosComplementaresRegisto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea descricaoET;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> pacienteCB;
    private javax.swing.JComboBox<String> salaCB;
    private javax.swing.JComboBox<String> tipoCB;
    // End of variables declaration//GEN-END:variables
}
