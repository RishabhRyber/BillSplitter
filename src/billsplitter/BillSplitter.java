/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billsplitter;

import Frames.EntryForm;

/**
 *
 * @author Rishabh
 */
public class BillSplitter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        EntryForm entryMain = new EntryForm();
        entryMain.setVisible(true);
        
    }
    
}




/*            public void run() {
                new History().setVisible(true);
                DbConnection dbConnection = new DbConnection();
                ResultSet res = dbConnection.runQuery("SELECT * FROM main");
                String date, name, paidBy, amount;
//                resultArea = new JTextAreaTextArea();
                try {
                    while (res.next()) {
                        date = res.getString("date");
                        name = res.getString("title");
                        paidBy = res.getString("paid_by");
                        amount = res.getString("amount");
                        resultArea.append(date+name+paidBy+amount);
                        System.out.println(resultArea.getText());
//                        resultArea.setEnabled(true);
//                        resultArea.setRows(50);
                        resultArea.validate();
                        resultArea.repaint();
                        validate();
                        repaint();
                    }
                        
                } catch (SQLException ex) {
                    Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
                }
            }


*/