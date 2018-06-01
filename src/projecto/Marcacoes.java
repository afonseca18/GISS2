package projecto;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Marcacoes extends javax.swing.JFrame {
    Calendar d = Calendar.getInstance();
    int AreaClinicaID;
    Connection con;
    Statement smt;
    ResultSet rs;
    Long[] datas = new Long[7];
    int i;
    int j;
    int ColaboradorID;
    int SalaID;
    int MaterialID;
    Date datas2;
    boolean x = false;
    boolean y = false;
    /**
     * Creates new form Marcacoes
     */
    public Marcacoes() {
        initComponents();
        Date dom = new Date();
        setTable(dom);
    }
    public Marcacoes (int AreaClinicaID,Connection con){
        initComponents();
        this.AreaClinicaID = AreaClinicaID;
        this.con = con;
        Date dom = new Date();
        setTable(dom);
        recursosCB.addActionListener(new ActionListener() {//add actionlistner to listen for change
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!x){
                    if(recursosCB.getSelectedIndex()==0){  
                    try {
                        smt = con.createStatement();
                        rs = smt.executeQuery("SELECT C.Nome FROM ColabArea A,Colaboradores C WHERE C.ColaboradoresID = A.ColaboradoresID AND A.AreasClinicasID = " + AreaClinicaID);                       
                        while(rs.next()){
                            colaboradoresCB.addItem(rs.getString(1));
                        }
                        } catch (SQLException ex) {
                            Logger.getLogger(Marcacoes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else{                 
                    resetTable();
                    try {
                        smt = con.createStatement();
                        rs = smt.executeQuery("SELECT S.Nome FROM AreasClinicas A,Salas S WHERE S.AreasClinicasID = A.AreasClinicasID AND A.AreasClinicasID = " + AreaClinicaID);
                        while(rs.next()){
                            salasCB.addItem(rs.getString(1));
                        }
                        } catch (SQLException ex) {
                            Logger.getLogger(Marcacoes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                x=true;
                setCB();
              }      
        });

    }
    public void setCB(){
            ActionListener ColaboradoresAL = new ActionListener() {//add actionlistner to listen for change
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (colaboradoresCB.getSelectedIndex()==-1){
                        
                    }
                    else{
                    try {                          
                         resetTable();
                         smt = con.createStatement();
                         rs = smt.executeQuery("SELECT C.ColaboradoresID FROM ColabArea A,Colaboradores C WHERE C.ColaboradoresID = A.ColaboradoresID AND A.AreasClinicasID = " + AreaClinicaID);
                         for(i=0;i<=colaboradoresCB.getSelectedIndex();i++){
                             rs.next();
                         }
                         ColaboradorID = rs.getInt(1);
                         for(i=0;i<7;i++){
                                for(j=0;j<24;j++){
                                smt = con.createStatement();
                                rs = smt.executeQuery("SELECT T.HoraI,T.HoraF FROM Colaboradores C,Turnos T,ColaboradoresTurnos CT\n" +
                                                      "WHERE C.ColaboradoresID=CT.ColaboradoresID\n" +
                                                      "AND CT.TurnosID = T.TurnosID\n" +
                                                      "AND C.ColaboradoresID = "+ColaboradorID +"\n" +
                                                      "AND CT.Data = "+ datas[i]);
                                while(rs.next()){
                                        if (rs.getInt(1)<=j && rs.getInt(2)>=j){
                                            jTable1.setValueAt("<html><font color='green'>Disponivel</font></html>", j, i+1);
                                        }                                       
                                    }
                                 
                                 smt = con.createStatement();
                                 rs = smt.executeQuery("SELECT I.HoraI,I.HoraF FROM Colaboradores C,Equipa E,Intervencao I\n" +
                                                        "WHERE I.IntervencaoID=E.IntervencaoID\n" +
                                                        "AND E.ColaboradoresID = C.ColaboradoresID\n" +
                                                        "AND C.ColaboradoresID ="+ColaboradorID+"\n" +
                                                        "AND I.Data = "+ datas[i]);
                                 while(rs.next()){
                                        if (rs.getInt(1)<=j && rs.getInt(2)>=j){
                                            jTable1.setValueAt("<html><font color='red'>Indisponivel</font></html>", j, i+1);
                                        }
                                    
                                 }
                                }
                         }
                     } catch (SQLException ex) {
                         Logger.getLogger(Marcacoes.class.getName()).log(Level.SEVERE, null, ex);
                     }
                    }
                }
             };               
             ActionListener MateriaisAL = new ActionListener() {//add actionlistner to listen for change
                @Override
                public void actionPerformed(ActionEvent e) {
                       if (salasCB.getSelectedIndex()==-1 || materiaisCB.getSelectedIndex()==-1){                          
                        }
                        else{
                            try {
                                 smt = con.createStatement();
                                 rs = smt.executeQuery("SELECT R.RecursosID FROM Salas S,Recursos R,RecSalas SR WHERE SR.SalasID = S.SalasID AND SR.RecursosID = R.RecursosID AND S.SalasID = "+SalaID);
                                 System.out.println("SELECT R.RecursosID FROM Salas S,Recursos R,RecSalas SR WHERE SR.SalasID = S.SalasID AND SR.RecursosID = R.RecursosID AND S.SalasID = "+SalaID);
                                 for(i=0;i<=materiaisCB.getSelectedIndex();i++){
                                     rs.next();
                                 }
                                 MaterialID = rs.getInt(1);
                                 for(i=0;i<7;i++){                                 
                                        for(j=0;j<24;j++){
                                            smt = con.createStatement();
                                            rs = smt.executeQuery("Select I.HoraI,I.HoraF From  Intervencao I\n" +
                                                                  "WHERE I.SalasID = "+SalaID+"\n" +
                                                                  "AND I.Data = "+datas[i] + " AND I.RecursoSalaID =" +MaterialID);
                                            jTable1.setValueAt("<html><font color='blue'>Disponivel</font></html>", j, i+1);
                                            while(rs.next()){
                                                if (rs.getInt(1)<=j && rs.getInt(2)>=j){
                                                    System.out.println(i);
                                                    jTable1.setValueAt("<html><font color='red'>Indisponivel</font></html>", j, i+1);
                                                }
                                            }
                                        }                                   
                                 }
                                } catch (SQLException ex) {
                                    Logger.getLogger(Marcacoes.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
             };
        ActionListener SalasAL = new ActionListener() {//add actionlistner to listen for change
                @Override
                public void actionPerformed(ActionEvent e) {
                       if (salasCB.getSelectedIndex()==-1){                          
                        }
                        else{
                            try {
                                 materiaisCB.removeActionListener(MateriaisAL);
                                 materiaisCB.removeAllItems();
                                 smt = con.createStatement();
                                 rs = smt.executeQuery("SELECT S.SalasID FROM AreasClinicas A,Salas S WHERE S.AreasClinicasID = A.AreasClinicasID AND A.AreasClinicasID = " + AreaClinicaID);
                                 for(i=0;i<=salasCB.getSelectedIndex();i++){
                                     rs.next();
                                 }
                                 SalaID = rs.getInt(1);
                                 for(i=0;i<7;i++){                                 
                                        for(j=0;j<24;j++){
                                            smt = con.createStatement();
                                            rs = smt.executeQuery("Select I.HoraI,I.HoraF From  Intervencao I\n" +
                                                                  "WHERE I.SalasID = "+SalaID+"\n" +
                                                                  "AND I.Data = "+datas[i]);
                                            jTable1.setValueAt("<html><font color='blue'>Disponivel</font></html>", j, i+1);
                                            while(rs.next()){
                                                if (rs.getInt(1)<=j && rs.getInt(2)>=j){
                                                    System.out.println(i);
                                                    jTable1.setValueAt("<html><font color='red'>Indisponivel</font></html>", j, i+1);
                                                }
                                            }
                                        }                                   
                                 }
                                smt = con.createStatement();
                                rs = smt.executeQuery("SELECT R.Nome FROM Salas S,Recursos R,RecSalas SR WHERE SR.SalasID = S.SalasID AND SR.RecursosID = R.RecursosID AND S.SalasID = "+SalaID); 
                                while (rs.next()){
                                    materiaisCB.addItem(rs.getString(1));
                                }
                                

                                } catch (SQLException ex) {
                                    Logger.getLogger(Marcacoes.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                materiaisCB.setSelectedIndex(-1);
                                materiaisCB.addActionListener(MateriaisAL);
                    }
                }
             };
             ActionListener RecursosAL = new ActionListener() {
             @Override
                public void actionPerformed(ActionEvent e) {
                    if(recursosCB.getSelectedIndex()==0){  
                            salasCB.removeActionListener(SalasAL);
                            colaboradoresCB.removeActionListener(ColaboradoresAL); 
                            materiaisCB.removeActionListener(MateriaisAL);
                            colaboradoresCB.removeAllItems();
                            salasCB.removeAllItems();
                            resetTable();
                            try {
                                smt = con.createStatement();
                                rs = smt.executeQuery("SELECT C.Nome FROM ColabArea A,Colaboradores C WHERE C.ColaboradoresID = A.ColaboradoresID AND A.AreasClinicasID = " + AreaClinicaID);                       
                                while(rs.next()){
                                    colaboradoresCB.addItem(rs.getString(1));
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(Marcacoes.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        else{ 
                            salasCB.removeActionListener(SalasAL);
                            colaboradoresCB.removeActionListener(ColaboradoresAL);
                            colaboradoresCB.removeAllItems();
                            materiaisCB.removeActionListener(MateriaisAL);
                            salasCB.removeAllItems();
                            resetTable();
                            try {
                                smt = con.createStatement();
                                rs = smt.executeQuery("SELECT S.Nome FROM AreasClinicas A,Salas S WHERE S.AreasClinicasID = A.AreasClinicasID AND A.AreasClinicasID = " + AreaClinicaID);
                                while(rs.next()){
                                    salasCB.addItem(rs.getString(1));
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(Marcacoes.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                       }
                    };
             
             recursosCB.addActionListener(RecursosAL);
             salasCB.addActionListener(SalasAL);
             materiaisCB.addActionListener(MateriaisAL);
             colaboradoresCB.addActionListener(ColaboradoresAL); 
            }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        colaboradoresTV = new javax.swing.JLabel();
        salasTV = new javax.swing.JLabel();
        salasCB = new javax.swing.JComboBox<>();
        colaboradoresCB = new javax.swing.JComboBox<>();
        recursosCB = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        materiaisCB = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Horários");
        setPreferredSize(new java.awt.Dimension(987, 610));
        setResizable(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"00:00", null, null, null, null, null, null, null},
                {"01:00", null, null, null, null, null, null, null},
                {"02:00", null, null, null, null, null, null, null},
                {"02:00", null, null, null, null, null, null, null},
                {"04:00", null, null, null, null, null, null, null},
                {"05:00", null, null, null, null, null, null, null},
                {"06:00", null, null, null, null, null, null, null},
                {"07:00", null, null, null, null, null, null, null},
                {"08:00", null, null, null, null, null, null, null},
                {"09:00", null, null, null, null, null, null, null},
                {"10:00", null, null, null, null, null, null, null},
                {"11:00", null, null, null, null, null, null, null},
                {"12:00", null, null, null, null, null, null, null},
                {"13:00", null, null, null, null, null, null, null},
                {"14:00", null, null, null, null, null, null, null},
                {"15:00", null, null, null, null, null, null, null},
                {"16:00", null, null, null, null, null, null, null},
                {"17:00", null, null, null, null, null, null, null},
                {"18:00", null, null, null, null, null, null, null},
                {"19:00", null, null, null, null, null, null, null},
                {"20:00", null, null, null, null, null, null, null},
                {"21:00", null, null, null, null, null, null, null},
                {"22:00", null, null, null, null, null, null, null},
                {"23:00", null, null, null, null, null, null, null}
            },
            new String [] {
                "Horas", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Next");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Prev");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Recursos");

        colaboradoresTV.setText("Colaboradores");

        salasTV.setText("Salas");

        colaboradoresCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colaboradoresCBActionPerformed(evt);
            }
        });

        recursosCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Humanos", "Materiais" }));

        jLabel2.setText("Materiais");

        jButton3.setText("Marcar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Voltar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 776, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addComponent(recursosCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(colaboradoresTV)
                                    .addComponent(salasTV, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(salasCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(colaboradoresCB, 0, 120, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(materiaisCB, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(colaboradoresTV)
                    .addComponent(colaboradoresCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(recursosCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(salasTV))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(salasCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(materiaisCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Date dom = new Date();
        d.add(Calendar.DAY_OF_MONTH,7);
        dom.setTime(d.getTimeInMillis());
        setTable(dom);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Date dom = new Date();
        d.add(Calendar.DAY_OF_MONTH,-7);
        dom.setTime(d.getTimeInMillis());
        setTable(dom);       
    }//GEN-LAST:event_jButton2ActionPerformed

    private void colaboradoresCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colaboradoresCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_colaboradoresCBActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (jTable1.getSelectedColumn()==-1 || jTable1.getSelectedRow()==-1){
            JOptionPane.showMessageDialog(null, "Selecione uma hora");
        }
        else{
        try {
            Marcar marcar = new Marcar(AreaClinicaID,con,datas[jTable1.getSelectedColumn()-1],jTable1.getSelectedRow());
            marcar.setVisible(true);
            this.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(Marcacoes.class.getName()).log(Level.SEVERE, null, ex);
        }
        }   
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            Aplicacoes app = new Aplicacoes(AreaClinicaID,con);
            app.setVisible(true);
            this.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(Marcacoes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(Marcacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Marcacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Marcacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Marcacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Marcacoes().setVisible(true);
            }
        });
    }
    
    
    public void setTable(Date dom){
        resetTable();
        colaboradoresCB.setSelectedIndex(-1);
        salasCB.setSelectedIndex(-1);
        Calendar c = Calendar.getInstance();
        c.setTime(dom);
        int i;
        DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
        switch (c.get(Calendar.DAY_OF_WEEK)){
            case(1):for (i=1;i<8;i++){
                        dom.setTime(c.getTimeInMillis());
                        jTable1.getTableHeader().getColumnModel().getColumn(i).setHeaderValue(sourceFormat.format(dom));
                        dom.setHours(23);
                        dom.setMinutes(59);
                        dom.setSeconds(0);
                        datas[i-1] = dom.getTime()/1000;
                        c.add(Calendar.DAY_OF_MONTH, 1);
                    }
                    break;
            case(2):c.add(Calendar.DAY_OF_MONTH, -1);
                    for (i=1;i<8;i++){
                        dom.setTime(c.getTimeInMillis());
                        jTable1.getTableHeader().getColumnModel().getColumn(i).setHeaderValue(sourceFormat.format(dom));
                        dom.setHours(23);
                        dom.setMinutes(59);
                        dom.setSeconds(0);
                        datas[i-1] = dom.getTime()/1000;
                        c.add(Calendar.DAY_OF_MONTH, 1);
                    }
                    break;
            case(3):c.add(Calendar.DAY_OF_MONTH, -2);
                    for (i=1;i<8;i++){
                        dom.setTime(c.getTimeInMillis());
                        jTable1.getTableHeader().getColumnModel().getColumn(i).setHeaderValue(sourceFormat.format(dom));
                        dom.setHours(23);
                        dom.setMinutes(59);
                        dom.setSeconds(0);
                        datas[i-1] = dom.getTime()/1000;
                        c.add(Calendar.DAY_OF_MONTH, 1);
                    }
                    break;
            case(4):c.add(Calendar.DAY_OF_MONTH, -3);
                    for (i=1;i<8;i++){
                        dom.setTime(c.getTimeInMillis());
                        jTable1.getTableHeader().getColumnModel().getColumn(i).setHeaderValue(sourceFormat.format(dom));
                        dom.setHours(23);
                        dom.setMinutes(59);
                        dom.setSeconds(0);
                        datas[i-1] = dom.getTime()/1000;
                        c.add(Calendar.DAY_OF_MONTH, 1);
                    }
                    break;
            case(5):c.add(Calendar.DAY_OF_MONTH, -4);
                    for (i=1;i<8;i++){
                        dom.setTime(c.getTimeInMillis());
                        jTable1.getTableHeader().getColumnModel().getColumn(i).setHeaderValue(sourceFormat.format(dom));
                        dom.setHours(23);
                        dom.setMinutes(59);
                        dom.setSeconds(0);
                        datas[i-1] = dom.getTime()/1000;
                        c.add(Calendar.DAY_OF_MONTH, 1);
                    }
                    break;
            case(6):c.add(Calendar.DAY_OF_MONTH, -5);
                    for (i=1;i<8;i++){
                        dom.setTime(c.getTimeInMillis());
                        jTable1.getTableHeader().getColumnModel().getColumn(i).setHeaderValue(sourceFormat.format(dom));
                        dom.setHours(23);
                        dom.setMinutes(59);
                        dom.setSeconds(0);
                        datas[i-1] = dom.getTime()/1000;
                        c.add(Calendar.DAY_OF_MONTH, 1);
                    }
                    break;
            case(7):c.add(Calendar.DAY_OF_MONTH, -6);
                    for (i=1;i<8;i++){
                        dom.setTime(c.getTimeInMillis());
                        jTable1.getTableHeader().getColumnModel().getColumn(i).setHeaderValue(sourceFormat.format(dom));
                        dom.setHours(23);
                        dom.setMinutes(59);
                        dom.setSeconds(0);
                        datas[i-1] = dom.getTime()/1000;
                        c.add(Calendar.DAY_OF_MONTH, 1);
                    }
                    break;
        }
        repaint();
    }
    
    public void resetTable (){
        for(i=0;i<7;i++){
            for(j=0;j<24;j++){
                jTable1.setValueAt("", j, i+1);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> colaboradoresCB;
    private javax.swing.JLabel colaboradoresTV;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> materiaisCB;
    private javax.swing.JComboBox<String> recursosCB;
    private javax.swing.JComboBox<String> salasCB;
    private javax.swing.JLabel salasTV;
    // End of variables declaration//GEN-END:variables
}
