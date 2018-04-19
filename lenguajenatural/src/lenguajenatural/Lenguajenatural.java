/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lenguajenatural;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

    

public class Lenguajenatural extends JFrame {
        private JLabel rotulo;
        private JTextField entrada;
        private JLabel salida;
        private JButton boton;
    
     public Lenguajenatural(){
         rotulo = new JLabel("Chatbot Raulero");
         rotulo.setBounds(30,50,150,20);
        add(rotulo);
        
        entrada = new JTextField();
        entrada.setBounds(30,80,150,20);
        add(entrada);
        
        boton = new JButton("Envia");
        boton.setBounds(30,100,150,20);
        add(boton);
        boton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActionPerformed(evt);
            }
        });
        
        salida = new JLabel("salida");
         salida.setBounds(30,150,150,20);
        add(salida);
     }
     
     private void botonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        System.out.println("Has tocado el boton");
        
        try {
            Class.forName("com.mysql.jdbc.Driver") ;
             Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/chatbot", "chatbot", "chatbot") ;
             Statement stmt =  conn.createStatement() ;
             ResultSet rs;

             rs = stmt.executeQuery("SELECT * FROM webchat_lines LIMIT 1");
             while ( rs.next() ) {
                 String mensaje = rs.getString("text");
                 salida.setText(mensaje);
             }
             conn.close();
         } catch (Exception e) {
             System.err.println("Got an exception! ");
             System.err.println(e.getMessage());
         }

    }  
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
         Lenguajenatural ventana = new Lenguajenatural();
        ventana.setBounds(50, 50, 300, 400);
        ventana.setVisible(true);
        
        // Voy a comprobar que puedo hacer cosas con el boton
    }
    
}
