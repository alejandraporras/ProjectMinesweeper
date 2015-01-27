package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class LoginView extends JFrame implements ActionListener{

	private final JPanel contentPane;
	private final JPasswordField passwordField;
	private final JTextField usuariField;
	JLabel lblWarning;
	JButton btnAgree;
	
	private final PresentationController presentationController;

	public LoginView(PresentationController cp) {
		setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		setTitle("Buscamines | Inicia Sessio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 478, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new BorderLayout(0, 0));
		
		JLabel lblImagenTitulo = new JLabel("");
		//lblImagenTitulo.setIcon(new ImageIcon(LoginView.class.getResource("/Presentation/pictures/titulo1.png")));
		lblImagenTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		panelNorte.add(lblImagenTitulo);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.WEST);
		GridBagLayout gbl_panelCentro = new GridBagLayout();
		gbl_panelCentro.columnWidths = new int[]{141, 39, 106, 71, 6, 0};
		gbl_panelCentro.rowHeights = new int[]{20, 0, 0, 44, 0, 0, 0};
		gbl_panelCentro.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelCentro.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelCentro.setLayout(gbl_panelCentro);
		
		JLabel lblUsuari = new JLabel("    Usuari *");
		lblUsuari.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_lblUsuari = new GridBagConstraints();
		gbc_lblUsuari.anchor = GridBagConstraints.WEST;
		gbc_lblUsuari.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuari.gridx = 0;
		gbc_lblUsuari.gridy = 1;
		panelCentro.add(lblUsuari, gbc_lblUsuari);
		
		usuariField = new JTextField();
		GridBagConstraints gbc_usuariField = new GridBagConstraints();
		gbc_usuariField.gridwidth = 2;
		gbc_usuariField.insets = new Insets(0, 0, 5, 5);
		gbc_usuariField.fill = GridBagConstraints.HORIZONTAL;
		gbc_usuariField.gridx = 1;
		gbc_usuariField.gridy = 1;
		panelCentro.add(usuariField, gbc_usuariField);
		usuariField.setColumns(10);
		
		JLabel lblPass = new JLabel("    Contrasenya *");
		lblPass.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_lblPass = new GridBagConstraints();
		gbc_lblPass.fill = GridBagConstraints.VERTICAL;
		gbc_lblPass.anchor = GridBagConstraints.WEST;
		gbc_lblPass.insets = new Insets(0, 0, 5, 5);
		gbc_lblPass.gridx = 0;
		gbc_lblPass.gridy = 2;
		panelCentro.add(lblPass, gbc_lblPass);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 2;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 2;
		panelCentro.add(passwordField, gbc_passwordField);
		
		btnAgree = new JButton(" ");
		btnAgree.setHorizontalAlignment(SwingConstants.LEADING);
		btnAgree.setIcon(new ImageIcon(LoginView.class.getResource("/presentation/pictures/acepta.png")));
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAceptar.gridx = 2;
		gbc_btnAceptar.gridy = 4;
		panelCentro.add(btnAgree, gbc_btnAceptar);
		
		JButton btnSalir = new JButton("");
		btnSalir.setIcon(new ImageIcon(LoginView.class.getResource("/presentation/pictures/sortir.png")));
		GridBagConstraints gbc_btnSalir = new GridBagConstraints();
		gbc_btnSalir.insets = new Insets(0, 0, 5, 5);
		gbc_btnSalir.gridx = 3;
		gbc_btnSalir.gridy = 4;
		panelCentro.add(btnSalir, gbc_btnSalir);
		
		JPanel panelSur = new JPanel();
		panelSur.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblWarning = new JLabel("");
		lblWarning.setForeground(Color.RED);
		lblWarning.setHorizontalAlignment(SwingConstants.LEFT);
		panelSur.add(lblWarning);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setIcon(new ImageIcon(LoginView.class.getResource("/presentation/pictures/marca.png")));
		panelSur.add(lblNewLabel);
		// para que la imagen del boton ocupe todo
		btnAgree.setBorder(null);
		btnAgree.setBorderPainted(false);
		btnAgree.setContentAreaFilled(false);
		// para que la imagen del boton ocupe todo
		btnSalir.setBorderPainted(false);
		btnSalir.setContentAreaFilled(false);
		
		
		btnAgree.addActionListener(this);
		btnSalir.addActionListener(this);
		this.presentationController = cp;	
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		lblWarning.setText("");
		// gestor de los botones
		JButton botonPressed = (JButton)e.getSource();
		
		if ( botonPressed == btnAgree) {
			String user = usuariField.getText().trim();
			String pwd = passwordField.getText().trim();
			if(user.isEmpty() || pwd.isEmpty()){
				lblWarning.setText("Falten dades a introduir");
			}
			else{
				presentationController.okLogin(user, pwd);
			}
		}
		else{
			//cerrar ventana
			this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
		}
	}

	public void setPasswordField(String password) {
		passwordField.setText(password);
	}

	public void setUsuariField(String username) {
		usuariField.setText(username);
	}
	
}
