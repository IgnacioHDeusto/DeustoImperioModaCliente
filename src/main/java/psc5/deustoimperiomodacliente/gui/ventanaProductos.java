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
    private JButton botonAgregar, botonEliminar, botonEditar, backButton;

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
        backButton = new JButton("Atrás");

        // Agregar botones al panel
        JPanel panelBotones = new JPanel();

        
        panelBotones.add(backButton);
        if (VentanaPrincipal.admin) {
            panelBotones.add(botonAgregar);
            panelBotones.add(botonEliminar);
            panelBotones.add(botonEditar);
        }
        
        botonEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Verificar si se ha seleccionado una fila en la tabla
                int filaSeleccionada = tablaProductos.getSelectedRow();
                if (filaSeleccionada == -1) {
                    // No se ha seleccionado ninguna fila, mostrar un mensaje de advertencia
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un artículo para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Obtener los datos del artículo seleccionado
                Integer id = (Integer) tablaProductos.getValueAt(filaSeleccionada, 0);
                String nombre = (String) tablaProductos.getValueAt(filaSeleccionada, 1);
                String categoria = (String) tablaProductos.getValueAt(filaSeleccionada, 5);
                String descripcion = (String) tablaProductos.getValueAt(filaSeleccionada, 2);
                double precio = (double) tablaProductos.getValueAt(filaSeleccionada, 3);
                String tamaño = (String) tablaProductos.getValueAt(filaSeleccionada, 4);

                // Crear componentes para editar el artículo
                JTextField nombreField = new JTextField(nombre);
                JComboBox<String> categoriaCombo = new JComboBox<>();
                // Agregar opciones al combo de categoría y seleccionar la categoría actual
                categoriaCombo.addItem("RopaDeportiva");
                categoriaCombo.addItem("CalzadoDeportivo");
                categoriaCombo.addItem("Calzado");
                categoriaCombo.addItem("Ropa");
                categoriaCombo.addItem("Accesorios");
                categoriaCombo.addItem("RopaInterior");
                categoriaCombo.setSelectedItem(categoria);
                JTextField descripcionField = new JTextField(descripcion);
                JSpinner precioSpinner = new JSpinner(new SpinnerNumberModel(precio, 0, 500, 1));
                JTextField tamañoField = new JTextField(tamaño);

                // Crear el JComponent con los componentes necesarios
                JComponent[] inputs = new JComponent[] {
                    new JLabel("Nombre: "),
                    nombreField,
                    new JLabel("Categoría: "),
                    categoriaCombo,
                    new JLabel("Descripción: "),
                    descripcionField,
                    new JLabel("Precio: "),
                    precioSpinner,
                    new JLabel("Tamaño: "),
                    tamañoField
                };

                // Mostrar el JComponent para editar el artículo
                int result = JOptionPane.showConfirmDialog(null, inputs, "Editar Artículo", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    final HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://127.0.0.1:8080/articulo/update?id=" + id + "&nombre=" + nombreField.getText() +"&desc=" + descripcionField.getText() + "&categoria=" + categoriaCombo.getSelectedItem().toString() + "&precio=" + precioSpinner.getValue() + "&tam="+tamañoField.getText())).build();
                    try {
                        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    } catch (IOException | InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        });
        
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaPrincipal.vprod.setVisible(false);
                if (VentanaPrincipal.admin) {
                    VentanaPrincipal.va.setVisible(true);
                } else{
                    VentanaPrincipal.vp.setVisible(true);
                }
                
            }
        });

        botonEliminar.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {

                int filaSeleccionada = tablaProductos.getSelectedRow();
                if (filaSeleccionada == -1) {
                    // No se ha seleccionado ninguna fila, mostrar un mensaje de advertencia
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un artículo para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Integer id = (Integer) tablaProductos.getValueAt(filaSeleccionada, 0);

				if (filaSeleccionada != -1) {
					final HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://127.0.0.1:8080/articulo/borrar?id=" + id)).build();
                    try {
                        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                        getProductos();
                    } catch (IOException | InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
				}
				
				
			}
		});

        botonAgregar.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				JTextField nombre_art = new JTextField(30);
				JComboBox<String> categoriaCombo = new JComboBox<String>();
				categoriaCombo.addItem("RopaDeportiva");
				categoriaCombo.addItem("CalzadoDeportivo");
				categoriaCombo.addItem("Calzado");
				categoriaCombo.addItem("Ropa");
				categoriaCombo.addItem("Accesorios");
				categoriaCombo.addItem("RopaInterior");
				JTextField desc_art = new JTextField(30);
				JSpinner precio = new JSpinner(new SpinnerNumberModel(0, 0, 500, 1));
				JTextField tamaño = new JTextField(30);
				
				JComponent[] inputs = new JComponent[] {
						new JLabel("NOMBRE: "),
						nombre_art,
						new JLabel("CATEGORIA: "),
						categoriaCombo,
						new JLabel("DESCRIPCION: "),
						desc_art,
						new JLabel("PRECIO: "),
						precio,
						new JLabel("TAMAÑO: "),
						tamaño,
						
					};
				int result = JOptionPane.showConfirmDialog(null, inputs, 
						"AGREGAR ARTICULO", 
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE);
				
				if (result == JOptionPane.OK_OPTION) {
					final HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://127.0.0.1:8080/articulo/crear?nombre=" + nombre_art.getText() +"&desc=" + desc_art.getText() + "&categoria=" + categoriaCombo.getSelectedItem().toString() + "&precio=" + precio.getValue() + "&tam="+tamaño.getText())).build();
                    try {
                        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                        getProductos();
                    } catch (IOException | InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
				}
				
				
			}
		});

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
            DefaultTableModel model = (DefaultTableModel) tablaProductos.getModel();
            model.setRowCount(0);
            articulos.stream().forEach(articulo -> {
                ((DefaultTableModel) tablaProductos.getModel()).addRow(new Object[] {articulo.getId(), articulo.getNombre(), articulo.getDescripcion(), articulo.getPrecio(), articulo.getTamano(), articulo.getCategoria()});
            });
            this.tablaProductos.setModel((DefaultTableModel) tablaProductos.getModel());
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
