package psc5.deustoimperiomodacliente.gui;

import java.net.http.HttpClient;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class VentanaPedido extends JFrame {
    private VentanaPrincipal vp;
    private HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2).build();
    private JTable tablaEnvios;

    public VentanaPedido() {
        this.setLayout(new BorderLayout());

        // Nombres de las columnas
        String[] columnNames = {"Envío", "Estado", "DNI"};

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
                        c.setBackground(new Color(107, 249, 88));
                    } else if ("Devuelto".equals(estado)){
                        c.setBackground(new Color(255,0,0));
                    } else {
                        c.setBackground(getBackground());
                    }
                }

                return c;
            }
        };

        // Llamar a getEnvios() para cargar los envíos en la tabla
        String dni = VentanaPrincipal.vp.getDniUsuario();
        
        getEnvio(dni);
        

        JButton btnAtras = new JButton("ATRAS");
        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaPrincipal.ve.setVisible(false);
                VentanaPrincipal.va.setVisible(true);
            }
        });

        JButton botonDevolver = new JButton("Devolver");
        botonDevolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaEnvios.getSelectedRow();
                if (selectedRow != -1) {
                    JDialog jdialogDevolver = new JDialog(VentanaPedido.this, "Devolver Pedido", true);
                    jdialogDevolver.setLayout(new BorderLayout());

                    // Crear las opciones de devolución
                    JPanel panelOpciones = new JPanel();
                    panelOpciones.setLayout(new BoxLayout(panelOpciones, BoxLayout.Y_AXIS));
                    JRadioButton opcion1 = new JRadioButton("No es de mi talla");
                    JRadioButton opcion2 = new JRadioButton("No me gusta como me queda");
                    JRadioButton opcion3 = new JRadioButton("Tiene algún desperfecto");

                    // Agrupar los botones de radio
                    ButtonGroup group = new ButtonGroup();
                    group.add(opcion1);
                    group.add(opcion2);
                    group.add(opcion3);

                    panelOpciones.add(opcion1);
                    panelOpciones.add(opcion2);
                    panelOpciones.add(opcion3);

                    // Añadir caja de texto para otras razones
                    JTextArea cajaTexto = new JTextArea(5, 20);
                    cajaTexto.setLineWrap(true);
                    cajaTexto.setWrapStyleWord(true);
                    JScrollPane scrollPane = new JScrollPane(cajaTexto);

                    panelOpciones.add(new JLabel("Otras razones:"));
                    panelOpciones.add(scrollPane);

                    jdialogDevolver.add(panelOpciones, BorderLayout.CENTER);

                    // Botón para confirmar la devolución
                    JButton btnConfirmar = new JButton("Confirmar devolución");
                    btnConfirmar.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            tablaEnvios.getModel().setValueAt("Devuelto", selectedRow, 1);
                            updateEstado(tablaEnvios.getModel().getValueAt(selectedRow, 2).toString(), "Devuelto",Integer.parseInt(tablaEnvios.getModel().getValueAt(selectedRow, 0).toString()));
                            // Lógica para procesar la devolución
                            if (opcion1.isSelected()) {
                                System.out.println("Devolución seleccionada: No es de mi talla");
                            } else if (opcion2.isSelected()) {
                                System.out.println("Devolución seleccionada: No me gusta como me queda");
                            } else if (opcion3.isSelected()) {
                                System.out.println("Devolución seleccionada: Tiene algún desperfecto");
                            }

                            String otrasRazones = cajaTexto.getText();
                            if (!otrasRazones.isEmpty()) {
                                System.out.println("Otras razones para la devolución: " + otrasRazones);
                            }
                            jdialogDevolver.dispose();
                        }
                    });

                    jdialogDevolver.add(btnConfirmar, BorderLayout.SOUTH);
                    jdialogDevolver.setSize(300, 300);
                    jdialogDevolver.setLocationRelativeTo(VentanaPedido.this);
                    jdialogDevolver.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(VentanaPedido.this, "Seleccione un pedido para devolver.", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.add(btnAtras);
        panelBotones.add(botonDevolver);

        this.add(panelBotones, BorderLayout.SOUTH);
        JScrollPane scrollPane = new JScrollPane(tablaEnvios);
        this.add(scrollPane, BorderLayout.CENTER);

        this.setSize(800, 600);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void getEnvio(String dniUsuario) {
        final HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://127.0.0.1:8080/pedido/all")).build();
        System.out.println(dniUsuario + "dni");
        try {
            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            List<Pedido> envios = convertirObjeto(response.body(), new TypeReference<List<Pedido>>() {});
            DefaultTableModel model = (DefaultTableModel) tablaEnvios.getModel();
            model.setRowCount(0);
            // Filtrar los pedidos por el DNI del usuario
            List<Pedido> enviosDelUsuario = envios.stream()
                    .filter(envio -> envio.getUsuario().getDni().equals(dniUsuario))
                    .collect(Collectors.toList());

            enviosDelUsuario.stream().forEach(envio -> {
                ((DefaultTableModel) tablaEnvios.getModel()).addRow(new Object[]{envio.getId(), envio.getEstado().toString(), envio.getUsuario().getDni()});
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

    
    public void updateEstado(String dni, String estado, int idPedido) {
        final HttpRequest request = HttpRequest.newBuilder()
            .PUT(HttpRequest.BodyPublishers.noBody())
            .uri(URI.create("http://127.0.0.1:8080/pedido/update?dni=" + dni + "&estado=" + estado + "&id=" + idPedido))
            .build();
        try {
            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // Puedes procesar la respuesta aquí...
        } catch (IOException | InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
