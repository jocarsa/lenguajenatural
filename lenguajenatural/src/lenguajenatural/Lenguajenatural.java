/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lenguajenatural;

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
        
        salida = new JLabel("salida");
         salida.setBounds(30,150,150,20);
        add(salida);
     }
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         Lenguajenatural ventana = new Lenguajenatural();
        ventana.setBounds(50, 50, 300, 400);
        ventana.setVisible(true);
    }
    
}
