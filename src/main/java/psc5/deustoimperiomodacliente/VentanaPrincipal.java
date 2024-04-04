package psc5.deustoimperiomodacliente;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import psc5.deustoimperiomodacliente.gui.ventanaAdministrador;


public class VentanaPrincipal extends JFrame{
    
    protected JLabel correo1;
    protected JTextField correo;
    protected JLabel contrasena1;
    protected JPasswordField contrasena;
    protected JButton iniciarsesion;
    protected JButton registrar;

	public VentanaPrincipal() {
        Container cp = this.getContentPane();
        JPanel center = new JPanel();
        cp.add(center, BorderLayout.CENTER);

        center.setBackground(new Color(200, 200, 200));
    
        correo1 = new JLabel("Correo:");
        correo1.setHorizontalAlignment(SwingConstants.CENTER);
        correo1.setFont(new Font("Arial", Font.BOLD, 14));
        correo = new JTextField(20);
        correo.setFont(new Font("Arial", Font.PLAIN, 14));

        contrasena1 = new JLabel("Contrasena:");
        contrasena1.setHorizontalAlignment(SwingConstants.CENTER);
        contrasena1.setFont(new Font("Arial", Font.BOLD, 14));
        contrasena = new JPasswordField(20);
        contrasena.setFont(new Font("Arial", Font.PLAIN, 14));

        iniciarsesion = new JButton("Iniciar Sesion");
        iniciarsesion.setFont(new Font("Arial", Font.BOLD, 14));
        iniciarsesion.setBackground(new Color(140, 170, 255));
        iniciarsesion.setForeground(Color.WHITE);
        
        registrar = new JButton("Registrar");
        registrar.setFont(new Font("Arial", Font.BOLD, 14));
        registrar.setBackground(new Color(140, 170, 255));
        registrar.setForeground(Color.WHITE);

        registrar.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				JTextField correo = new JTextField(30);
				JTextField nombre = new JTextField(30);
				JTextField contrasena = new JTextField(30);
				JTextField dni = new JTextField(30);
                JPanel reg = new JPanel();
				reg.setLayout(new GridLayout(1,3));
				
				JComboBox<String> treg = new JComboBox<String>();
				treg.addItem("Usuario");
				treg.addItem("Administrador");
				reg.add(new JLabel());
				reg.add(treg);
				reg.add(new JLabel());
				
				JComponent[] inputs = new JComponent[] {
						new JLabel("CORREO: "),
						correo,
						new JLabel("NOMBRE: "),
						nombre,
						new JLabel("CONTRASENA: "),
						contrasena,
						new JLabel("DNI"),
						dni,
                        new JLabel("REGISTRARSE COMO "),
						reg
					};
				
				int result = JOptionPane.showConfirmDialog(null, inputs, 
						"REGISTRO", 
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE);
				
			//	if (result == JOptionPane.OK_OPTION) {
			//		if (correo.getText() != "" && nombre.getText() != "" && contrasena.getText() != "") {
			//			if (Integer.parseInt(rep.getValue().toString()) != 0 || Integer.parseInt(max.getValue().toString()) != 0
			//					|| Integer.parseInt(altura.getValue().toString()) != 0 || Integer.parseInt(peso.getValue().toString()) != 0) {
			//				controller.registro(nombre.getText(), correo.getText(), fecha.getDate(), treg.getSelectedItem().toString(), Integer.parseInt(altura.toString()), Integer.parseInt(peso.toString()), Integer.parseInt(max.toString()), Integer.parseInt(rep.toString()),  contrasena.getText());
			//			} else {
			//				controller.registro(nombre.getText(), correo.getText(), fecha.getDate(),  treg.getSelectedItem().toString(), 0, 0, 0, 0,  contrasena.getText());
			//			}
			//		}
			//	}
					
				
			}
		});

        center.setLayout(new GridLayout(7, 2, 10, 10));
        center.setBorder(new LineBorder(Color.BLACK, 1));

        center.add(correo1);
        center.add(correo);
        center.add(contrasena1);
        center.add(contrasena);
        center.add(new JLabel()); 
        center.add(iniciarsesion);
        center.add(registrar);

        
        this.setSize(400, 300);
        setLocationRelativeTo(null);

    }

    public static void main(String[] args) {
        var ctx = new SpringApplicationBuilder(VentanaPrincipal.class)
            .headless(false).web(WebApplicationType.NONE).run(args);

        EventQueue.invokeLater(() -> {

            var ex = ctx.getBean(VentanaPrincipal.class);
            ex.setVisible(true);
    });
}

}
