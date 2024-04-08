package psc5.deustoimperiomodacliente.gui;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

import psc5.deustoimperiomodacliente.VentanaPrincipal;

public class ventanaProductos extends JFrame {
    private static final String JLabel = null;
    private JTable tablaProductos;
    private JButton botonAgregar, botonEliminar, botonEditar;

    public ventanaProductos() {
        setTitle("Lista de Productos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Crear tabla
        String[] columnas = {"ID", "Nombre", "Descripción", "Precio", "Stock"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
        tablaProductos = new JTable(modeloTabla);

        // Llenar tabla con datos de la lista de productos
       // for (Producto producto : listaProductos) {
      //      Object[] fila = {producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.getPrecio(), producto.getStock()};
        //    modeloTabla.addRow(fila);
     //   }

        // Crear botones
        botonAgregar = new JButton("Agregar");
        botonEliminar = new JButton("Eliminar");
        botonEditar = new JButton("Editar");

        // Agregar botones al panel
        JPanel panelBotones = new JPanel();
        /*JPanel panelInfo = new JPanel();

        panelInfo.setLayout(new GridLayout(1, 2));
        panelInfo.add(new JLabel("  Iñaki Prieto Barturen"));
        JPanel cerrarS = new JPanel();
        cerrarS.setLayout(new BorderLayout());
        JButton ShutDown = new JButton();
        
        ShutDown.setBackground(new Color(238, 238, 238));
        ShutDown.setBorder(null);
        ShutDown.setIcon(new ImageIcon("resources/cerrarSesion1.png"));
        cerrarS.add(ShutDown, BorderLayout.EAST);
        panelInfo.add(cerrarS);
        
        ShutDown.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                VentanaPrincipal.vprod.setVisible(fa);
                VentanaPrincipal.vp.setVisible(true);
            }

        });*/

        if (VentanaPrincipal.admin) {
            panelBotones.add(botonAgregar);
            panelBotones.add(botonEliminar);
            panelBotones.add(botonEditar);
        }
        

        // Agregar componentes a la ventana
        getContentPane().setLayout(new BorderLayout());
        //getContentPane().add(panelInfo, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(tablaProductos), BorderLayout.CENTER);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);

    }


}
