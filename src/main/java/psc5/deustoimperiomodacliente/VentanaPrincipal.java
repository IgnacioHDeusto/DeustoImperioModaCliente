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

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import psc5.deustoimperiomodacliente.gui.VentanaGestionCuentas;
import psc5.deustoimperiomodacliente.gui.ventanaAdministrador;
import psc5.deustoimperiomodacliente.gui.ventanaProductos;


public class VentanaPrincipal extends JFrame{
    
    public static VentanaPrincipal vp;
    public static ventanaAdministrador va; 
    public static VentanaGestionCuentas vgc; 
    public static ventanaProductos vprod;

    public static boolean admin = false;

    protected JLabel correo1;
    protected JTextField correo;
    protected JLabel contrasena1;
    protected JPasswordField contrasena;
    protected JButton iniciarsesion;
    protected JButton registrar;

    private HttpClient client = HttpClient.newBuilder()
    .version(HttpClient.Version.HTTP_2).build();

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
        
        iniciarsesion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                vprod = new ventanaProductos(); 

                if (correo.getText().equals("1") && contrasena.getText().equals("1")) {
                    vp.setVisible(false);
                    admin = true;
                    va = new ventanaAdministrador();
                    va.setVisible(true);
                } else if (correo.getText().equals("2") && contrasena.getText().equals("2")){
                    vp.setVisible(false);
                    admin = false;
                    vprod.getProductos();
                    vprod.setVisible(true);
                }

            }

        });

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
				treg.addItem("Cliente");
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

                if (result == JOptionPane.OK_OPTION) {
                    final HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://127.0.0.1:8080/usuario/crear?dni=" + dni.getText() +"&contr=" + contrasena.getText() + "&nombre=" + nombre.getText() + "&correo=" + correo.getText() + "&pedidos=null&tipoU="+treg.getSelectedItem().toString())).build();
                    try {
                        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    } catch (IOException | InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                



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

        EventQueue.invokeLater(() -> {
            vp = new VentanaPrincipal();
            vp.setVisible(true);
    });
}

}
