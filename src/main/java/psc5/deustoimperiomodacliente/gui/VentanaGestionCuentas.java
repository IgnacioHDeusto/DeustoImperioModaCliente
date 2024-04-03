package psc5.deustoimperiomodacliente.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class VentanaGestionCuentas extends JFrame{
    private JLabel Cuentas;
    private DefaultTableModel modeloDatosCuentas;
    protected JTable tablaCuentas;
    private JScrollPane scrollPaneCuentas;
    private JButton backButton;
    private JButton deleteButton;
    
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

    }
