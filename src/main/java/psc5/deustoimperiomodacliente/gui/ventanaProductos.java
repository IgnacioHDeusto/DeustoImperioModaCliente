package psc5.deustoimperiomodacliente.gui;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.springframework.http.client.reactive.HttpComponentsClientHttpConnector;

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

import psc5.deustoimperiomodacliente.VentanaPrincipal;
import psc5.deustoimperiomodacliente.post.Articulo;

public class ventanaProductos extends JFrame {
    private HttpClient client = HttpClient.newBuilder()
    .version(HttpClient.Version.HTTP_2).build();
    private static final String JLabel = null;
    private JTable tablaProductos;
    private JButton botonAgregar, botonEliminar, botonEditar;

    public ventanaProductos() {
        setTitle("Lista de Productos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Crear tabla
        String[] columnas = {"ID", "Nombre", "Descripción", "Precio", "Tamaño", "Categoría"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
        tablaProductos = new JTable(modeloTabla);

        // Crear botones
        botonAgregar = new JButton("Agregar");
        botonEliminar = new JButton("Eliminar");
        botonEditar = new JButton("Editar");

        // Agregar botones al panel
        JPanel panelBotones = new JPanel();

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

    public void getProductos() { 
        final HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://127.0.0.1:8080/articulo/all")).build();
        
        try {
            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            List<Articulo> articulos = convertirObjeto(response.body(), new TypeReference<List<Articulo>>() { });
            articulos.stream().forEach(articulo -> {
                ((DefaultTableModel) tablaProductos.getModel()).addRow(new Object[] {articulo.getId(), articulo.getNombre(), articulo.getDescripcion(), articulo.getPrecio(), articulo.getTamano(), articulo.getCategoria()});
            });

            this.tablaProductos.setModel((DefaultTableModel) tablaProductos.getModel());
            System.out.println(response.body());
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
