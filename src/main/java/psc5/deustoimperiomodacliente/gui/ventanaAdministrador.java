package psc5.deustoimperiomodacliente.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.*;

import psc5.deustoimperiomodacliente.VentanaPrincipal;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ventanaAdministrador extends JFrame{
    protected JButton gestionarProductos;
    protected JButton gestionarCuentas;
    protected JButton backButton;
    public static ventanaProductos vprod;

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
        backButton = new JButton("Atrás");

        Color buttonColor = new Color(140, 170, 255);
        gestionarProductos.setBackground(buttonColor);
        gestionarProductos.setBorder(new LineBorder(Color.BLACK));
        gestionarCuentas.setBackground(buttonColor);
        gestionarCuentas.setBorder(new LineBorder(Color.BLACK));
         

        gestionarProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaPrincipal.vprod = new ventanaProductos();
                VentanaPrincipal.va.setVisible(false);
                VentanaPrincipal.vprod.getProductos();
                VentanaPrincipal.vprod.setVisible(true);
            }
        });

        gestionarCuentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaPrincipal.vgc = new VentanaGestionCuentas();
                VentanaPrincipal.vgc.getUsuarios();
                VentanaPrincipal.va.setVisible(false);
                VentanaPrincipal.vgc.setVisible(true);
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaPrincipal.va.setVisible(false);
                VentanaPrincipal.vp.setVisible(true);
            }
        });

        getContentPane().setLayout(new BorderLayout());

        panelBotones.add(gestionarProductos);
        panelBotones.add(gestionarCuentas);

        add(panelBotones, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);

        setVisible(true);
    }


}
