package psc5.deustoimperiomodacliente.gui;

import psc5.deustoimperiomodacliente.post.Articulo;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.util.List;

public class ventanaCarrito extends JFrame{
    
    private List<Articulo> productosCarrito;
    private JTable tablaCarrito;

    public ventanaCarrito(List<Articulo> productosCarrito) {
        this.productosCarrito = productosCarrito;

        setTitle("Carrito de Compras");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        // Crear tabla para mostrar los productos del carrito
        String[] columnas = {"ID", "Nombre", "Descripción", "Precio", "Tamaño", "Categoría"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaCarrito = new JTable(modeloTabla);

        // Llenar la tabla con los productos del carrito
        for (Articulo articulo : productosCarrito) {
            Object[] fila = {
                articulo.getId(),
                articulo.getNombre(),
                articulo.getDescripcion(),
                articulo.getPrecio(),
                articulo.getTamano(),
                articulo.getCategoria()
            };
            modeloTabla.addRow(fila);
        }

        // Agregar la tabla a la ventana
        getContentPane().add(tablaCarrito);
        getContentPane().add(new JScrollPane(tablaCarrito), BorderLayout.CENTER);
    }
}
