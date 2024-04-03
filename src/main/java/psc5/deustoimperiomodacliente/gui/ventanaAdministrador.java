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

public class ventanaAdministrador extends JFrame{
    protected JButton gestionarProductos;
    protected JButton gestionarCuentas;

    public ventanaAdministrador() {
        setTitle("VENTANA ADMINISTRADOR"); 
        setSize(700,900);
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

        gestionarProductos.setBackground(new Color(140, 170, 255));
        gestionarProductos.setBorder(new LineBorder(Color.BLACK));
        gestionarCuentas.setBackground(new Color(140, 170, 255));
        gestionarCuentas.setBorder(new LineBorder(Color.BLACK));

        panelBotones.add(gestionarProductos);
        panelBotones.add(gestionarCuentas);

    }
}
