package psc5.deustoimperiomodacliente.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import psc5.deustoimperiomodacliente.post.Usuario;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.JScrollPane;

public class VentanaGestionCuentas extends JFrame{
    private JLabel Cuentas;
    private DefaultTableModel modeloDatosCuentas;
    protected JTable tablaCuentas;
    private JScrollPane scrollPaneCuentas;
    private JButton backButton;
    private JButton deleteButton;
    private HttpClient client = HttpClient.newBuilder()
    .version(HttpClient.Version.HTTP_2).build();
    
    public VentanaGestionCuentas() {
        setTitle("Gestion Cuentas");
        setSize(700,900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configuración del JLabel
        Cuentas = new JLabel("Cuentas");
        Cuentas.setFont(new Font("Arial", Font.BOLD, 20));
        Cuentas.setHorizontalAlignment(SwingConstants.CENTER);

        // Configuración del DefaultTableModel
        modeloDatosCuentas = new DefaultTableModel();
        modeloDatosCuentas.addColumn("DNI");
        modeloDatosCuentas.addColumn("Nombre");
        modeloDatosCuentas.addColumn("Correo electrónico");
        // Aquí puedes agregar más columnas según tus necesidades

        // Configuración de la JTable
        tablaCuentas = new JTable(modeloDatosCuentas);

        // Configuración del JScrollPane para la JTable
        scrollPaneCuentas = new JScrollPane(tablaCuentas);
        
        backButton = new JButton("Atrás");
        deleteButton = new JButton("Borrar Cuenta");

        // Configuración del diseño de la ventana
        setLayout(new BorderLayout());

        // Agregar el JLabel centrado en la parte superior
        add(Cuentas, BorderLayout.NORTH);

        // Agregar la tabla con el JScrollPane en el centro
        add(scrollPaneCuentas, BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.add(backButton);
        bottomPanel.add(deleteButton);

        // Establecer tamaño preferido para los botones
        backButton.setPreferredSize(new Dimension(Short.MAX_VALUE, backButton.getPreferredSize().height));
        deleteButton.setPreferredSize(new Dimension(Short.MAX_VALUE, deleteButton.getPreferredSize().height));
        
        add(bottomPanel, BorderLayout.SOUTH);
    }
    public void getUsuarios() {
        final HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/usuario/all")).build();

        try {
            // Realizar petición al servidor
            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            List<Usuario> usuarios = convertirObjeto(response.body(), new TypeReference<List<Usuario>>() { });
            usuarios.stream().forEach(usuario -> {
                modeloDatosCuentas.addRow(new Object[] {usuario.getDni(), usuario.getNombre(), usuario.getCorreo()});
            });
            this.tablaCuentas.setModel(modeloDatosCuentas);
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
