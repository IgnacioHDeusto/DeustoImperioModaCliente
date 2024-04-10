package psc5.deustoimperiomodacliente.gui;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.event.*;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Vector;

import psc5.deustoimperiomodacliente.VentanaPrincipal;
import psc5.deustoimperiomodacliente.controller.ArticuloController;

public class ventanaProductos extends JFrame {
    private static final String JLabel = null;
    private JTable tablaProductos;
    private JButton botonAgregar, botonEliminar, botonEditar;
    private JTable tablaArticulos;
    private DefaultTableModel modeloDatosArticulos;
    private int mouseRowArticulo = -1;

    public ventanaProductos() {
        setTitle("Lista de Productos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        initTables();

        // Crear tabla
        /* 
        String[] columnas = {"ID", "Nombre", "Descripción", "Precio", "Stock"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
        tablaProductos = new JTable(modeloTabla);
*/

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
        /*JPanel panelInfo = new JPanel();

        panelInfo.setLayout(new GridLayout(1, 2));
        panelInfo.add(new JLabel("  Iñaki Prieto Barturen"));
        JPanel cerrarS = new JPanel();
        cerrarS.setLayout(new BorderLayout());
        JButton ShutDown = new JButton();
        
        ShutDown.setBackground(new Color(238, 238, 238));
        ShutDown.setBorder(null);
        ShutDown.setIcon(new ImageIcon("resources/cerrarSesion1.png"));
        cerrarS.add(ShutDown, BorderLayout.EAST);
        panelInfo.add(cerrarS);
        
        ShutDown.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                VentanaPrincipal.vprod.setVisible(fa);
                VentanaPrincipal.vp.setVisible(true);
            }

        });*/


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
					try {
						if (nombre_art.getText() != "" && Integer.parseInt(precio.getValue().toString()) != 0) {
							//ArticuloController.agregarArticulo(nombre_art.getText(), categoriaCombo.getSelectedItem().toString() ,Integer.parseInt(precio.getValue().toString()) , desc_art.getText(), tamaño.getText()  , main.token );
							//loadDatos(ArticuloController);
						}
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
				}
				
				
			}
		});

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


    private void initTables() {
		Vector<String> cabeceraArticulos = new Vector<String>(Arrays.asList( "NOMBRE", "CATEGORIA", "DESCRIPCION","PRECIO","TAMAÑO"));

		this.modeloDatosArticulos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraArticulos);
		this.tablaArticulos = new JTable(this.modeloDatosArticulos);
		
		final TableCellRenderer cellRenderer = (table, value, isSelected, hasFocus, row, column) -> {
			JLabel result = new JLabel();
			if (value != null) {
				result = new JLabel(value.toString());
			}
			
			
			result.setBackground(Color.white);
						
			if (isSelected ||  row == mouseRowArticulo) {
				
				result.setBackground(new Color(255,128,0));	
			}
			
			
			
			result.setFont(new Font("Arial", Font.PLAIN, 14));
			result.setHorizontalAlignment(SwingConstants.CENTER);
			
			result.setOpaque(true);
			
			return result;
		};
		
		this.tablaArticulos = new JTable(this.modeloDatosArticulos) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		this.tablaArticulos.setDefaultRenderer(Object.class, cellRenderer);
	
}
	/* 
	private void loadDatos(ArticuloController articuloController) {
		
		this.modeloDatosArticulos.setRowCount(0);
		try {
			if (articuloController.getEntrenamientos(main.token) != null) {
				articuloController.getEntrenamientos(main.token).forEach(d->{
					modeloDatosArticulos.addRow(new Object[] {});
				});
			}
			articuloController.getEntrenamientos(main.token).forEach(d->{
				modeloDatosArticulos.addRow(new Object[] {});
			});
		} catch (RemoteException e) {
			e.printStackTrace();
		};
	}
*/
}
