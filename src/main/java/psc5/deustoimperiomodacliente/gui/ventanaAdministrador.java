package psc5.deustoimperiomodacliente.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ventanaAdministrador extends JFrame{
    protected JButton gestionarProductos;
    protected JButton gestionarCuentas;
    private ventanaProductos ventanaProductos;

    public ventanaAdministrador() {
        setTitle("VENTANA ADMIN");
         setSize(900, 450);
         setLocationRelativeTo(null);
         Container cp = this.getContentPane();
         cp.setLayout(new GridBagLayout());

         GridBagConstraints gbc = new GridBagConstraints();
         gbc.fill = GridBagConstraints.BOTH;
         gbc.weightx = 1.0;
         gbc.weighty = 1.0;

         JPanel panelBotones = new JPanel(new GridLayout(1, 2));
         cp.add(panelBotones, gbc);

         gestionarProductos = new JButton("GESTIONAR PRODUCTOS");
         gestionarCuentas = new JButton("GESTIONAR CUENTAS");

         Color buttonColor = new Color(140, 170, 255);
         gestionarProductos.setBackground(buttonColor);
         gestionarProductos.setBorder(new LineBorder(Color.BLACK));
         gestionarCuentas.setBackground(buttonColor);
         gestionarCuentas.setBorder(new LineBorder(Color.BLACK));
         
         gestionarProductos.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
            	 
                 ventanaProductos.setVisible(true);
             }
         });

         panelBotones.add(gestionarProductos);
         panelBotones.add(gestionarCuentas);

         setVisible(true);
}
}
