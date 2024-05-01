package psc5.deustoimperiomodacliente.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.net.http.HttpClient;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import psc5.deustoimperiomodacliente.VentanaPrincipal;

import javax.swing.JScrollPane;

public class VentanaEnvio extends JFrame{
    private HttpClient client = HttpClient.newBuilder()
    .version(HttpClient.Version.HTTP_2).build();
    private JTable tablaEnvios;

    public VentanaEnvio() {
        this.setLayout(new BorderLayout());
        // Datos de ejemplo para la tabla
        String[][] data = {
            {"Envío 1", "Preparacion"},
            {"Envío 2", "Reparto"},
            {"Envío 3", "Recibido"},
            // Agrega más filas según sea necesario
        };

        // Nombres de las columnas
        String[] columnNames = {"Envío", "Estado"};

        // Crear la tabla
        JTable tablaEnvios = new JTable(data, columnNames) {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
            Component c = super.prepareRenderer(renderer, row, column);

            // Asegúrate de que el color solo se cambie para las filas no seleccionadas
            if (!isRowSelected(row)) {
                // Obtén el estado del envío para esta fila
                String estado = getModel().getValueAt(row, 1).toString();

                // Cambia el color de fondo de la fila en función del estado
                if ("Preparacion".equals(estado)) {
                    c.setBackground(new Color(255, 180, 79));
                } else if ("Reparto".equals(estado)) {
                    c.setBackground(new Color(110, 176, 246));
                } else if ("Recibido".equals(estado)) {
                    c.setBackground(new Color(107, 249, 88 ));
                } else {
                    c.setBackground(getBackground());
                }
            }

            return c;
            }
        };

        JButton btnAtras = new JButton("ATRAS");
        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaPrincipal.ve.setVisible(false);
                VentanaPrincipal.va.setVisible(true);
            }
        });

        this.add(btnAtras, BorderLayout.SOUTH);
        JScrollPane scrollPane = new JScrollPane(tablaEnvios);
        this.add(scrollPane, BorderLayout.CENTER);

        this.add(scrollPane);

        this.setSize(800, 600);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}