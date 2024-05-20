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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import psc5.deustoimperiomodacliente.VentanaPrincipal;
import psc5.deustoimperiomodacliente.post.Articulo;
import psc5.deustoimperiomodacliente.post.Pedido;

import javax.swing.JScrollPane;

public class VentanaPedido extends JFrame{
    private VentanaPrincipal vp;
    private HttpClient client = HttpClient.newBuilder()
    .version(HttpClient.Version.HTTP_2).build();
    private JTable tablaEnvios;

    public VentanaPedido() {
        this.setLayout(new BorderLayout());
    
        // Nombres de las columnas
        String[] columnNames = {"Envío", "Estado"};
    
        // Crear el modelo de la tabla
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    
        // Crear la tabla con el modelo
        tablaEnvios = new JTable(model) {
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
    
        // Llamar a getEnvios() para cargar los envíos en la tabla
        getEnvio(vp.getDniUsuario());
    
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
    
        this.setSize(800, 600);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void getEnvio(String dniUsuario) {
    final HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://127.0.0.1:8080/pedido/all")).build();
    System.out.println(dniUsuario);
    try {
        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        List<Pedido> envios = convertirObjeto(response.body(), new TypeReference<List<Pedido>>() { });
        DefaultTableModel model = (DefaultTableModel) tablaEnvios.getModel();
        model.setRowCount(0);
        // Filtrar los pedidos por el DNI del usuario
        List<Pedido> enviosDelUsuario = envios.stream()
            .filter(envio -> envio.getUsuario().getDni().equals(dniUsuario))
            .collect(Collectors.toList());
            

        enviosDelUsuario.stream().forEach(envio -> {
            ((DefaultTableModel) tablaEnvios.getModel()).addRow(new Object[] {envio.getUsuario().getCorreo(), envio.getEstado().toString()});
        });
        this.tablaEnvios.setModel((DefaultTableModel) tablaEnvios.getModel());

    } catch (IOException | InterruptedException e) {
        e.printStackTrace();
    }
}

    final ObjectMapper mapper = new ObjectMapper();
    public <T> T convertirObjeto(final String json, final TypeReference<T> reference) {
        try {
           return this.mapper.readValue(json, reference);
        } catch (IOException e) {
            e.printStackTrace();
            {
                return null;
            }
        }
}
}