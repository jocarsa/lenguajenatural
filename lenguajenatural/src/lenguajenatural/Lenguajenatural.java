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
        //Constructor en el que creamos la ventana con sus botones
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
        // Accion a ejecutar cuando pulsamos el boton
        System.out.println("Has tocado el boton");
        
        try {
            // Como Raul dice que igual hasta hay un java.sql ya dentro de Java, especifico la libreria que voy a usar
            Class.forName("com.mysql.jdbc.Driver") ;
            // Creo una conexión con la base de datos introduciendo toda la info
             Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/datoschatbot", "chatbot", "chatbot") ;
             // En la conexión creo una petición
             Statement stmt =  conn.createStatement() ;
             // Contenedor de información correspondiente a un array que contendrá el resultado de la petición
             ResultSet rs;          
             // Quiero coger lo que hay dentro del textfield para usarlo en la peticion SQL
             String contienetext = entrada.getText();
             // Ese contenedor, ya no esta vacío, es igual al resultado de la siguiente peticion
             rs = stmt.executeQuery(""
                     + "SELECT * FROM webchat_lines"
                     + " WHERE text LIKE '%"+contienetext+"%'"
                     + " LIMIT 1"
                     + "");
             int idseleccionado = 0;
             while ( rs.next() ) {  // Mientras que RS siga teniendo lineas (rows)
                 idseleccionado = rs.getInt("id");
             }
             rs = stmt.executeQuery(""
                     + "SELECT * FROM webchat_lines"
                     + " WHERE id = "+(idseleccionado+1)+""
                     + ""
                     + "");
             while ( rs.next() ) {  // Mientras que RS siga teniendo lineas (rows)
                 // Avanza el puntero a la siguiente linea de resultado
                 // Mensaje es dame la string que hay dentro de la columna text
                 String mensaje = rs.getString("text");
                 // Escribeme el contenido
                 salida.setText(mensaje);
             }
             // Cierrame la conexión
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
