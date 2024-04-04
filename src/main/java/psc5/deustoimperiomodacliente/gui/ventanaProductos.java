package psc5.deustoimperiomodacliente.gui;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ventanaProductos extends JFrame {
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
        panelBotones.add(botonAgregar);
        panelBotones.add(botonEliminar);
        panelBotones.add(botonEditar);

        // Agregar componentes a la ventana
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new JScrollPane(tablaProductos), BorderLayout.CENTER);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);

    }


}
