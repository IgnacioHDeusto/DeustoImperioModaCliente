package psc5.deustoimperiomodacliente.gui;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

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

public class VentanaProductos extends JFrame {
    private HttpClient client = HttpClient.newBuilder()
    .version(HttpClient.Version.HTTP_2).build();
    private static final String JLabel = null;
    private JTable tablaProductos;
    private List<Articulo> todosLosArticulos = new ArrayList<>();
    private JButton botonAgregar, botonEliminar, botonEditar, backButton, añadirCarrito;
    private JLabel labelCalzado, labelRopaDeportiva, labelCalzadoDeportivo, labelRopa, labelAccesorios, labelRopaInterior, verTodo;
    private JButton carrito;
    private List<Articulo> productosCarrito = new ArrayList<>();


    public VentanaProductos() {
        setTitle("Lista de Productos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 600);
        setLocationRelativeTo(null);
    
        // Crear tabla
        String[] columnas = {"ID", "Nombre", "Descripción", "Precio", "Tamaño", "Categoría"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    
        tablaProductos = new JTable(modeloTabla);
    
        // Cambiar la fuente de la tabla
        tablaProductos.setFont(new Font("Arial", Font.PLAIN, 14));
    
        // Crear botones
        carrito = new JButton("");
        ImageIcon carritoIcon = new ImageIcon("resources/carrito2.jpg");
//		strava.setIcon(new ImageIcon("resources/strava.png"));
		Image carritoImage = carritoIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		carrito.setIcon(new ImageIcon(carritoImage));

        botonAgregar = new JButton("Agregar");
        botonEliminar = new JButton("Eliminar");
        botonEditar = new JButton("Editar");
        backButton = new JButton("Atrás");
        añadirCarrito = new JButton("Añadir al carrito");

        labelCalzado = new JLabel("Calzado");
        labelRopaDeportiva = new JLabel("Ropa Deportiva");
        labelCalzadoDeportivo = new JLabel("Calzado Deportivo");
        labelRopa = new JLabel("Ropa");
        labelAccesorios = new JLabel("Accesorios");
        labelRopaInterior = new JLabel("Ropa Interior");
        verTodo = new JLabel("Ver Todo");

        // Agregar botones al panel
        JPanel panelBotones = new JPanel();
        JPanel panelCategoria = new JPanel();

        panelBotones.add(backButton);
        panelCategoria.add(labelCalzado);
        panelCategoria.add(labelRopaDeportiva);
        panelCategoria.add(labelCalzadoDeportivo);
        panelCategoria.add(labelRopa);
        panelCategoria.add(labelAccesorios);
        panelCategoria.add(labelRopaInterior);
        panelCategoria.add(verTodo);
        panelCategoria.add(carrito, BorderLayout.EAST);

        Font font = new Font("Arial", Font.BOLD, 12); // Definir una fuente
        Color foregroundColor = Color.WHITE; // Color del texto
        Color backgroundColor = new Color(66, 134, 244); // Color de fondo

        // Establecer estilos para cada JLabel
        Font newFont = new Font("Arial", Font.BOLD, 12); // Nueva fuente más pequeña
        Color newForegroundColor = Color.WHITE; // Nuevo color de primer plano
        Color newBackgroundColor = Color.DARK_GRAY; // Nuevo color de fondo
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2); // Nuevo borde

// Aplica los nuevos estilos a cada JLabel
        verTodo.setFont(newFont);
        verTodo.setForeground(newForegroundColor);
        verTodo.setBackground(newBackgroundColor);
        verTodo.setBorder(border);
        verTodo.setHorizontalAlignment(SwingConstants.CENTER);
        verTodo.setOpaque(true);


        labelCalzado.setFont(newFont);
        labelCalzado.setForeground(newForegroundColor);
        labelCalzado.setBackground(newBackgroundColor);
        labelCalzado.setBorder(border);
        labelCalzado.setHorizontalAlignment(SwingConstants.CENTER);
        labelCalzado.setOpaque(true);

        labelRopaDeportiva.setFont(newFont);
        labelRopaDeportiva.setForeground(newForegroundColor);
        labelRopaDeportiva.setBackground(newBackgroundColor);
        labelRopaDeportiva.setBorder(border);
        labelRopaDeportiva.setHorizontalAlignment(SwingConstants.CENTER);
        labelRopaDeportiva.setOpaque(true);

        labelCalzadoDeportivo.setFont(newFont);
        labelCalzadoDeportivo.setForeground(newForegroundColor);
        labelCalzadoDeportivo.setBackground(newBackgroundColor);
        labelCalzadoDeportivo.setBorder(border);
        labelCalzadoDeportivo.setHorizontalAlignment(SwingConstants.CENTER);
        labelCalzadoDeportivo.setOpaque(true);

        labelRopa.setFont(newFont);
        labelRopa.setForeground(newForegroundColor);
        labelRopa.setBackground(newBackgroundColor);
        labelRopa.setBorder(border);
        labelRopa.setHorizontalAlignment(SwingConstants.CENTER);
        labelRopa.setOpaque(true);

        labelAccesorios.setFont(newFont);
        labelAccesorios.setForeground(newForegroundColor);
        labelAccesorios.setBackground(newBackgroundColor);
        labelAccesorios.setBorder(border);
        labelAccesorios.setHorizontalAlignment(SwingConstants.CENTER);
        labelAccesorios.setOpaque(true);

        labelRopaInterior.setFont(newFont);
        labelRopaInterior.setForeground(newForegroundColor);
        labelRopaInterior.setBackground(newBackgroundColor);
        labelRopaInterior.setBorder(border);
        labelRopaInterior.setHorizontalAlignment(SwingConstants.CENTER);
        labelRopaInterior.setOpaque(true);

        labelCalzado.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                filtrarProductosPorCategoria("Calzado");
            }
        });
        
        labelRopaDeportiva.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                filtrarProductosPorCategoria("RopaDeportiva");
            }
        });

        labelCalzadoDeportivo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                filtrarProductosPorCategoria("CalzadoDeportivo");
            }
        });

        labelRopa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                filtrarProductosPorCategoria("Ropa");
            }
        });

        labelAccesorios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                filtrarProductosPorCategoria("Accesorios");
            }
        });

        labelRopaInterior.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                filtrarProductosPorCategoria("RopaInterior");
            }
        });

        verTodo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Llama al método getProductos para cargar todos los productos en la tabla
                getProductos();
            }
        });

        //FUNCIONALIDAD DEL BOTON CARRITO
        carrito.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cuando se hace clic en el botón "Carrito"
                VentanaCarrito vcarrito = new VentanaCarrito(productosCarrito);
                vcarrito.setVisible(true);
            }
        });

        tablaProductos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Cuando se hace clic en la tabla de productos
                int filaSeleccionada = tablaProductos.getSelectedRow();
                if (filaSeleccionada != -1) {
                    // Obtener el producto seleccionado
                    Articulo producto = todosLosArticulos.get(filaSeleccionada);
                    // Agregar el producto al carrito
                    productosCarrito.add(producto);
                }
            }
        });
    




        if (VentanaPrincipal.admin) {
            panelBotones.add(botonAgregar);
            panelBotones.add(botonEliminar);
            panelBotones.add(botonEditar);
        }

        if (!VentanaPrincipal.admin){
            panelBotones.add(añadirCarrito);
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
        getContentPane().add(panelCategoria, BorderLayout.NORTH);

    }

    private void filtrarProductosPorCategoria(String categoria) {
        DefaultTableModel modeloTabla = (DefaultTableModel) tablaProductos.getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de agregar productos filtrados
    
        if (categoria.equals("Todos")) {
            // Restaurar la tabla completa usando la copia de respaldo de todos los productos
            todosLosArticulos.forEach(articulo -> {
                modeloTabla.addRow(new Object[] {articulo.getId(), articulo.getNombre(), articulo.getDescripcion(), articulo.getPrecio(), articulo.getTamano(), articulo.getCategoria()});
            });
        } else {
            // Filtrar productos por categoría seleccionada
            todosLosArticulos.stream()
                    .filter(articulo -> articulo.getCategoria().equals(categoria))
                    .forEach(articulo -> {
                        modeloTabla.addRow(new Object[]{articulo.getId(), articulo.getNombre(), articulo.getDescripcion(), articulo.getPrecio(), articulo.getTamano(), articulo.getCategoria()});
                    });
        }
    }

    public void getProductos() { 
        final HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://127.0.0.1:8080/articulo/all")).build();
        
        try {
            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            List<Articulo> articulos = convertirObjeto(response.body(), new TypeReference<List<Articulo>>() { });
            todosLosArticulos = new ArrayList<>(articulos);
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
