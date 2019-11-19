/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.util.ArrayList;
import java.util.Collections;
import com.sun.org.apache.xml.internal.utils.NameSpace;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import utils.DbConnection;

/**
 *
 * @author Rishabh
 */


class Person implements Comparable<Person>{
    String name;
    double costpaid = 0;
    double moneytoget;
    ArrayList<String> mainString = new ArrayList<>();
//-------------------------------------------------------------------------------------------------------------------------- 
    Person( String namei, double costpaidi )
    {
        name = namei;
        costpaid = costpaidi;
    }

    void addcost(double money)
    {
        costpaid += money;
    }
    void calcMoneyToGet(double pool)
    {
        moneytoget = costpaid - pool ;
    }
    double getCostPaid(){
        return costpaid;
    }
    double getMoneyToGet(){
        return moneytoget;
    }

//-----------------------------------------------------------------------------------------------------------------------------

    public int compareTo(Person m)
    {
        if (this.moneytoget - m.moneytoget > 0)
            return 1;
        else if ( this.moneytoget - m.moneytoget < 0 )
            return -1;   
        else
            return 0;     
    }

    void makezero()
    {
        moneytoget = 0;
    }

    void decreaseamount(double x)
    {
        moneytoget = moneytoget - x;
    }

    String putinstring( Person p )
    {
        if( Math.abs(moneytoget) >= p.getMoneyToGet() ){
            String toreturn = "\n\n    " + this.name + " owes " + p.name + " Rs. " + p.getMoneyToGet() ;
            moneytoget += p.getMoneyToGet();
            p.makezero();
            return toreturn;
        }

        else{
            mainString.add(this.name + " owes " + p.name + " Rs. " + Math.abs(moneytoget));
            p.decreaseamount( Math.abs(moneytoget) );
            makezero();
        }
        return "";
    }
}







public class History extends javax.swing.JFrame {
    
    /**
     * Creates new form History
     */
    double [] spent = new double[4];
    String[] names = {"Rahul","Rishabh","Pranav","Pandey"};

    ArrayList<Person> arrofpeople = new ArrayList<>(4);

    
    public History() {
        initComponents();
       
        DbConnection dbConnection = new DbConnection();
        ResultSet res = dbConnection.runQuery("SELECT * FROM main");
        String date, name, paidBy, amount,finalRes = "    Date \tTitle \tPaid By \tAmount\n";
        finalRes+="----------------------------------------------------------------------------------------------\n\n";
        try {
            while (res.next()) {
                date = res.getString("date");
                name = res.getString("title");
                amount = res.getString("amount");
                paidBy = res.getString("paid_by");
                for(int i=0;i<4;i++)
                    if(paidBy.equals(names[i]))
                        spent[i]+=Integer.parseInt(amount);
                finalRes+="    " + date+"\t"+name+"\t"+paidBy+"\t"+amount+"\n\n";
            }

        } catch (SQLException ex) { 
            System.err.println(ex);
        }

        arrofpeople.add( new Person( names[0], spent[0] ) );
        arrofpeople.add( new Person( names[1], spent[1] ) );
        arrofpeople.add( new Person( names[2], spent[2] ) );
        arrofpeople.add( new Person( names[3], spent[3] ) );



        finalRes+="\n---------------------------------------------------------------------------------------------------------\n";
        
//        finalRes += "Initial list is " + arrofpeople.get(0).getCostPaid() +"  " + arrofpeople.get(1).getCostPaid() +"  " + arrofpeople.get(2).getCostPaid() +"  " + arrofpeople.get(3).getCostPaid();

        double mainpool = spent[0] + spent[1] + spent[2] + spent[3];
//        finalRes += "Main pool is " + mainpool + "\n\n";
        for(int i=0;i<4;i++)
        {
            arrofpeople.get(i).calcMoneyToGet( mainpool/4 );
        }  
        Collections.sort(arrofpeople);
        

        while(arrofpeople.get(4 - 1).getMoneyToGet() != 0  &&  
                arrofpeople.get(0).getMoneyToGet() != 0 )
        {
            finalRes += arrofpeople.get(0).putinstring( arrofpeople.get(4-1));
            Collections.sort(arrofpeople);
        } 

        arrofpeople.get(0).makezero();
        arrofpeople.get(4-1).makezero(); 

        // while(1){
        //     // finalRes += "\n\n    " + names[i] + "\tpaid" + " \t" + spent[i];
        //     finalRes += "\n\n    " ;
        // }
        JTextArea jTextArea  = new JTextArea(finalRes);
        jTextArea.setBounds(50, 50, 400, 500);
        jTextArea.setEditable(false);
        jTextArea.setRows(4);
        JScrollPane taScroll = new JScrollPane(jTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        taScroll.setBounds(50, 50, 400, 500);
        add(taScroll);
        
//        add(jTextArea);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 650));
        setPreferredSize(new java.awt.Dimension(500, 650));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel1.setText("History");

        jButton1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(418, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 482, Short.MAX_VALUE)
                .addComponent(jButton1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        hide();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(History.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(History.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(History.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(History.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new History().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
