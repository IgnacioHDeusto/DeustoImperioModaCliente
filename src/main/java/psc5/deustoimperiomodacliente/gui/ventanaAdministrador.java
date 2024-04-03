package psc5.deustoimperiomodacliente.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

public class ventanaAdministrador extends JFrame{
    protected JButton anyadirProducto;
    protected JButton borrarProducto;
    protected JButton editarProducto;
    protected JButton gestionarCuentas;

    public ventanaAdministrador() {
        setSize(700,900);
        setLocationRelativeTo(null);
        Container cp = this.getContentPane();
        JPanel north = new JPanel();
        JPanel south = new JPanel();
        cp.add(north, BorderLayout.NORTH);
        cp.add(south, BorderLayout.SOUTH);

        anyadirProducto = new JButton("Add Product");
        borrarProducto = new JButton("Delete Product");
        editarProducto = new JButton("Edit Product");
        gestionarCuentas = new JButton("Manage Accounts");

        anyadirProducto.setBackground(new Color(140, 170, 255));
        anyadirProducto.setBorder(new LineBorder(Color.BLACK));
        borrarProducto.setBackground(new Color(140, 170, 255));
        borrarProducto.setBorder(new LineBorder(Color.BLACK));
        editarProducto.setBackground(new Color(140, 170, 255));
        editarProducto.setBorder(new LineBorder(Color.BLACK));
        gestionarCuentas.setBackground(new Color(140, 170, 255));
        gestionarCuentas.setBorder(new LineBorder(Color.BLACK));

        north.setLayout(new GridLayout(1,2));
        south.setLayout(new GridLayout(1,2));
        
        north.add(anyadirProducto);
        north.add(borrarProducto);
        south.add(editarProducto);
        south.add(gestionarCuentas);

    }
}
