package psc5.deustoimperiomodacliente.gui;

import psc5.deustoimperiomodacliente.post.Articulo;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ventanaCarrito extends JFrame{
    
    private List<Articulo> productosCarrito;
    private JTable tablaCarrito;
    private JButton pagarButton = new JButton("Finalizar y Pagar");
    JButton eliminarButton = new JButton("Eliminar");
    JPanel buttonPanel = new JPanel();
    DefaultTableModel modeloTabla;

    public ventanaCarrito(List<Articulo> productosCarrito) {
        this.productosCarrito = productosCarrito;

        setTitle("Carrito de Compras");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        // Crear tabla para mostrar los productos del carrito
        String[] columnas = {"ID", "Nombre", "Descripción", "Precio", "Tamaño", "Categoría"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
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

        // Añadir un ActionListener al botón "Eliminar"
        eliminarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        // Obtener la fila seleccionada
        int selectedRow = tablaCarrito.getSelectedRow();

        // Si hay una fila seleccionada, eliminarla
        if (selectedRow != -1) {
            modeloTabla.removeRow(selectedRow);
        }
    }
        });

    pagarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Crear un nuevo JDialog
            JDialog dialog = new JDialog();
            dialog.setModal(true);
            dialog.setSize(300, 225);
            dialog.setLocationRelativeTo(null);
            
            // Crear un nuevo JButton
            JButton confirmarButton = new JButton("Confirmar e imprimir ticket");
            confirmarButton.setSize(100, 50);

            // Añadir un ActionListener a confirmarButton
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar la lógica para imprimir el ticket
                imprimirTicket();
                dialog.setVisible(false);
            }
        });

            // Añadir el JButton al JDialog
            dialog.add(confirmarButton);

            // Mostrar el JDialog
            dialog.setVisible(true);
    }
    });

        // Agregar la tabla a la ventana
        buttonPanel.add(eliminarButton);
        buttonPanel.add(pagarButton);
        getContentPane().add(tablaCarrito);
        getContentPane().add(new JScrollPane(tablaCarrito), BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    public void imprimirTicket() {
        try {
            PrintWriter writer = new PrintWriter("ticket.txt", "UTF-8");

            double total = 0.0;
            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                String nombreArticulo = modeloTabla.getValueAt(i, 1).toString();
                
                int cantidad = 1;
                
                double precio = 0.0;
                if (isNumeric(modeloTabla.getValueAt(i, 3).toString())) {
                    precio = Double.parseDouble(modeloTabla.getValueAt(i, 3).toString());
                    total += precio;
                }
        
                // Escribir los detalles del artículo en el archivo
                writer.println("Articulo " + (i+1));
                writer.println("Nombre: " + nombreArticulo);
                writer.println("Cantidad: " + cantidad);
                writer.println("Precio: " + precio);
                writer.println("-------------------------");
            }

            writer.println("Total: " + total);

            // Obtener la fecha y hora actual
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // Escribir la fecha y hora en el archivo
            writer.println("Fecha y hora: " + now.format(formatter));

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}