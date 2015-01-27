package presentation;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;

//import com.jgoodies.forms.factories.DefaultComponentFactory;

@SuppressWarnings("serial")
public class LevelsView extends JFrame implements ActionListener{

	private final JPanel contentPane;

	private final JPanel panelNivellSeleccio;
	private final JPanel panelnfoNivell;
	private final JPanel panelBotones;
	private final JLabel lblNivell;
	@SuppressWarnings("rawtypes")
	private final JList listSeleccionaNivell;
	private final JPanel panelTitulo;
	private final JPanel panelInfoNivell;
	private final JLabel lblResumNivell;
	private final JLabel lblNivell_1;
	private final JLabel lblNombreDeCaselles;
	private final JLabel lblFiles;
	private final JLabel lblColumnes;
	private final JTextField textFieldValueNivell;
	private final JTextField textFieldValueNumCaselles;
	private final JTextField textFieldValueNumFiles;
	private final JTextField textFieldValueNumCol;
	private final JLabel lblNombreDeMines;
	private final JTextField textFieldValueNomMines;
	private final JButton btnAccepta;
	private final JButton btnCancela;
	private final JButton btnUsername;

	private final PresentationController presentationController;	
	private final JPanel panelInferior;
	private final JLabel lblWarning;
	private final JLabel lblMarca;

	boolean nivellSeleccionat = false;
	String nivelEscollit;

	@SuppressWarnings("rawtypes")
	public LevelsView(PresentationController presentationController, String user) {
		setTitle("Buscamines | Seleccionar nivell");

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  // Nosotros controlamos el close
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent ev){
				Object[] options = { "Si", "No" };
				int ret= JOptionPane.showOptionDialog(null, "Segur que vols sortir?", "Ets a punt d'abandonar la partida..",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
						null, options, options[0]);
				if(ret == JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
		});	

		setBounds(100, 100, 577, 450);
		contentPane = new JPanel();
		contentPane.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		panelNivellSeleccio = new JPanel();
		contentPane.add(panelNivellSeleccio);
		GridBagLayout gbl_panelNivellSeleccio = new GridBagLayout();
		gbl_panelNivellSeleccio.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelNivellSeleccio.rowHeights = new int[]{0, 81, 12, 0};
		gbl_panelNivellSeleccio.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelNivellSeleccio.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelNivellSeleccio.setLayout(gbl_panelNivellSeleccio);

		lblNivell = new JLabel("Nivell");
		lblNivell.setFont(new Font("Luxi Sans", Font.BOLD, 14));
		GridBagConstraints gbc_lblNivell = new GridBagConstraints();
		gbc_lblNivell.anchor = GridBagConstraints.NORTH;
		gbc_lblNivell.insets = new Insets(0, 0, 5, 5);
		gbc_lblNivell.gridx = 0;
		gbc_lblNivell.gridy = 1;
		panelNivellSeleccio.add(lblNivell, gbc_lblNivell);

		listSeleccionaNivell = new JList();
		listSeleccionaNivell.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Selecciona un nivell", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		listSeleccionaNivell.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GridBagConstraints gbc_listSeleccionaNivell = new GridBagConstraints();
		gbc_listSeleccionaNivell.gridwidth = 7;
		gbc_listSeleccionaNivell.insets = new Insets(0, 0, 5, 5);
		gbc_listSeleccionaNivell.fill = GridBagConstraints.BOTH;
		gbc_listSeleccionaNivell.gridx = 1;
		gbc_listSeleccionaNivell.gridy = 1;
		panelNivellSeleccio.add(listSeleccionaNivell, gbc_listSeleccionaNivell);

		btnUsername = new JButton("");
		btnUsername.setIcon(new ImageIcon(LevelsView.class.getResource("/presentation/pictures/botonUsername.png")));
		btnUsername.setText(user);
		GridBagConstraints gbc_btnUsername = new GridBagConstraints();
		gbc_btnUsername.anchor = GridBagConstraints.NORTH;
		gbc_btnUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnUsername.insets = new Insets(0, 0, 5, 0);
		gbc_btnUsername.gridx = 13;
		gbc_btnUsername.gridy = 1;
		panelNivellSeleccio.add(btnUsername, gbc_btnUsername);

		panelnfoNivell = new JPanel();
		contentPane.add(panelnfoNivell);
		panelnfoNivell.setLayout(new BoxLayout(panelnfoNivell, BoxLayout.Y_AXIS));

		panelTitulo = new JPanel();
		panelnfoNivell.add(panelTitulo);
		GridBagLayout gbl_panelTitulo = new GridBagLayout();
		gbl_panelTitulo.columnWidths = new int[]{284, 0};
		gbl_panelTitulo.rowHeights = new int[]{46, 13, 0};
		gbl_panelTitulo.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelTitulo.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelTitulo.setLayout(gbl_panelTitulo);

		lblResumNivell = new JLabel("");
		lblResumNivell.setIcon(new ImageIcon(LevelsView.class.getResource("/presentation/pictures/ResumPartida.png")));
		GridBagConstraints gbc_lblResumNivell = new GridBagConstraints();
		gbc_lblResumNivell.insets = new Insets(0, 0, 5, 0);
		gbc_lblResumNivell.anchor = GridBagConstraints.WEST;
		gbc_lblResumNivell.gridx = 0;
		gbc_lblResumNivell.gridy = 0;
		panelTitulo.add(lblResumNivell, gbc_lblResumNivell);

		panelInfoNivell = new JPanel();
		panelnfoNivell.add(panelInfoNivell);
		GridBagLayout gbl_panelInfoNivell = new GridBagLayout();
		gbl_panelInfoNivell.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelInfoNivell.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelInfoNivell.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelInfoNivell.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelInfoNivell.setLayout(gbl_panelInfoNivell);

		lblNivell_1 = new JLabel("Nivell");
		lblNivell_1.setFont(new Font("Luxi Sans", Font.BOLD, 14));
		GridBagConstraints gbc_lblNivell_1 = new GridBagConstraints();
		gbc_lblNivell_1.anchor = GridBagConstraints.WEST;
		gbc_lblNivell_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNivell_1.gridx = 0;
		gbc_lblNivell_1.gridy = 0;
		panelInfoNivell.add(lblNivell_1, gbc_lblNivell_1);

		textFieldValueNivell = new JTextField();
		GridBagConstraints gbc_textFieldValueNivell = new GridBagConstraints();
		gbc_textFieldValueNivell.gridwidth = 2;
		gbc_textFieldValueNivell.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldValueNivell.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldValueNivell.gridx = 1;
		gbc_textFieldValueNivell.gridy = 0;
		panelInfoNivell.add(textFieldValueNivell, gbc_textFieldValueNivell);
		textFieldValueNivell.setColumns(10);

		lblNombreDeCaselles = new JLabel("Nombre de caselles");
		lblNombreDeCaselles.setFont(new Font("Luxi Sans", Font.BOLD, 14));
		GridBagConstraints gbc_lblNombreDeCaselles = new GridBagConstraints();
		gbc_lblNombreDeCaselles.anchor = GridBagConstraints.EAST;
		gbc_lblNombreDeCaselles.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreDeCaselles.gridx = 0;
		gbc_lblNombreDeCaselles.gridy = 1;
		panelInfoNivell.add(lblNombreDeCaselles, gbc_lblNombreDeCaselles);

		textFieldValueNumCaselles = new JTextField();
		GridBagConstraints gbc_textFieldValueNumCaselles = new GridBagConstraints();
		gbc_textFieldValueNumCaselles.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldValueNumCaselles.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldValueNumCaselles.gridx = 1;
		gbc_textFieldValueNumCaselles.gridy = 1;
		panelInfoNivell.add(textFieldValueNumCaselles, gbc_textFieldValueNumCaselles);
		textFieldValueNumCaselles.setColumns(10);

		lblFiles = new JLabel("Files");
		lblFiles.setFont(new Font("Luxi Sans", Font.BOLD, 14));
		GridBagConstraints gbc_lblFiles = new GridBagConstraints();
		gbc_lblFiles.insets = new Insets(0, 0, 5, 5);
		gbc_lblFiles.gridx = 2;
		gbc_lblFiles.gridy = 1;
		panelInfoNivell.add(lblFiles, gbc_lblFiles);

		textFieldValueNumFiles = new JTextField();
		GridBagConstraints gbc_textFieldValueNumFiles = new GridBagConstraints();
		gbc_textFieldValueNumFiles.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldValueNumFiles.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldValueNumFiles.gridx = 3;
		gbc_textFieldValueNumFiles.gridy = 1;
		panelInfoNivell.add(textFieldValueNumFiles, gbc_textFieldValueNumFiles);
		textFieldValueNumFiles.setColumns(10);

		lblColumnes = new JLabel("Columnes");
		lblColumnes.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		GridBagConstraints gbc_lblColumnes = new GridBagConstraints();
		gbc_lblColumnes.insets = new Insets(0, 0, 5, 5);
		gbc_lblColumnes.gridx = 5;
		gbc_lblColumnes.gridy = 1;
		panelInfoNivell.add(lblColumnes, gbc_lblColumnes);

		textFieldValueNumCol = new JTextField();
		GridBagConstraints gbc_textFieldValueNumCol = new GridBagConstraints();
		gbc_textFieldValueNumCol.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldValueNumCol.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldValueNumCol.gridx = 6;
		gbc_textFieldValueNumCol.gridy = 1;
		panelInfoNivell.add(textFieldValueNumCol, gbc_textFieldValueNumCol);
		textFieldValueNumCol.setColumns(10);

		lblNombreDeMines = new JLabel("Nombre de mines");
		lblNombreDeMines.setFont(new Font("Luxi Sans", Font.BOLD, 14));
		GridBagConstraints gbc_lblNombreDeMines = new GridBagConstraints();
		gbc_lblNombreDeMines.anchor = GridBagConstraints.WEST;
		gbc_lblNombreDeMines.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreDeMines.gridx = 0;
		gbc_lblNombreDeMines.gridy = 2;
		panelInfoNivell.add(lblNombreDeMines, gbc_lblNombreDeMines);

		textFieldValueNomMines = new JTextField();
		GridBagConstraints gbc_textFieldValueNomMines = new GridBagConstraints();
		gbc_textFieldValueNomMines.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldValueNomMines.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldValueNomMines.gridx = 1;
		gbc_textFieldValueNomMines.gridy = 2;
		panelInfoNivell.add(textFieldValueNomMines, gbc_textFieldValueNomMines);
		textFieldValueNomMines.setColumns(10);

		panelBotones = new JPanel();
		contentPane.add(panelBotones);
		GridBagLayout gbl_panelBotones = new GridBagLayout();
		gbl_panelBotones.columnWidths = new int[]{149, 90, 0, 0, 0, 0};
		gbl_panelBotones.rowHeights = new int[]{25, 0};
		gbl_panelBotones.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelBotones.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelBotones.setLayout(gbl_panelBotones);

		btnAccepta = new JButton("");
		btnAccepta.setIcon(new ImageIcon(LevelsView.class.getResource("/presentation/pictures/accepta122x48.png")));
		GridBagConstraints gbc_btnAccepta = new GridBagConstraints();
		gbc_btnAccepta.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnAccepta.insets = new Insets(0, 0, 0, 5);
		gbc_btnAccepta.gridx = 2;
		gbc_btnAccepta.gridy = 0;
		panelBotones.add(btnAccepta, gbc_btnAccepta);

		btnCancela = new JButton("");
		btnCancela.addActionListener(this);
		btnCancela.setIcon(new ImageIcon(LevelsView.class.getResource("/presentation/pictures/cancela22x48.png")));
		GridBagConstraints gbc_btnCancela = new GridBagConstraints();
		gbc_btnCancela.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnCancela.gridx = 4;
		gbc_btnCancela.gridy = 0;
		panelBotones.add(btnCancela, gbc_btnCancela);

		btnUsername.setHorizontalTextPosition(SwingConstants.CENTER); //para que el texto del boton se escriba dentro
		// para que la imagen del boton ocupe todo
		btnUsername.setBorder(new EmptyBorder(0,0,0,0)); // para que la imagen del boton ocupe todo
		btnUsername.setContentAreaFilled(false);

		btnAccepta.setHorizontalTextPosition(SwingConstants.CENTER); //para que el texto del boton se escriba dentro
		// para que la imagen del boton ocupe todo
		btnAccepta.setBorder(new EmptyBorder(0,0,0,0)); // para que la imagen del boton ocupe todo
		btnAccepta.setContentAreaFilled(false);
		btnAccepta.addActionListener(this);

		btnCancela.setHorizontalTextPosition(SwingConstants.CENTER); //para que el texto del boton se escriba dentro
		// para que la imagen del boton ocupe todo
		btnCancela.setBorder(new EmptyBorder(0,0,0,0)); // para que la imagen del boton ocupe todo
		btnCancela.setContentAreaFilled(false);

		panelInferior = new JPanel();
		panelInferior.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panelInferior);
		panelInferior.setLayout(new GridLayout(0, 2, 0, 0));

		lblWarning = new JLabel("");
		lblWarning.setHorizontalAlignment(SwingConstants.LEFT);
		lblWarning.setForeground(Color.RED);
		panelInferior.add(lblWarning);

		lblMarca = new JLabel("");
		lblMarca.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMarca.setIcon(new ImageIcon(LevelsView.class.getResource("/presentation/pictures/marca.png")));
		panelInferior.add(lblMarca);

		panelInfoNivell.setVisible(true);
		panelTitulo.setVisible(true);
		levelStatus(false);
		this.presentationController = presentationController;
	}
	public void levelStatus(boolean estado){
		lblResumNivell.setVisible(estado);
		lblNivell_1.setVisible(estado);
		lblNombreDeCaselles.setVisible(estado);
		lblFiles.setVisible(estado);
		lblColumnes.setVisible(estado);
		textFieldValueNivell.setVisible(estado);
		textFieldValueNumCaselles.setVisible(estado);
		textFieldValueNumFiles.setVisible(estado);
		textFieldValueNumCol.setVisible(estado);
		lblNombreDeMines.setVisible(estado);
		textFieldValueNomMines.setVisible(estado);
	}
	public void showInfoLevel(String nivell){
		levelStatus(true);
		textFieldValueNivell.setText(nivell);
	}

	@SuppressWarnings("unchecked")
	public void initLevels() throws Exception{
		presentationController.getLevelNames();
		listSeleccionaNivell.setListData(presentationController.getLevelNames());
		//Agregamos un listener para cada vez que se seleccione un nivel de la lista
		listSeleccionaNivell.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					nivellSeleccionat = true;
					lblWarning.setText("");
					nivelEscollit = listSeleccionaNivell.getSelectedValue().toString();
					InfoLevel infoLevel = presentationController.getDadesNivell(nivelEscollit);
					Integer rows= infoLevel.getRows();
					Integer cols = infoLevel.getColumns();
					Integer cells = rows*cols;
					Integer mines = infoLevel.getMine();
					textFieldValueNumCaselles.setText(cells.toString());
					textFieldValueNumFiles.setText(rows.toString());
					textFieldValueNumCol.setText(cols.toString());
					textFieldValueNomMines.setText(mines.toString());
					showInfoLevel(nivelEscollit);
				}
			}
		});

	}
	int getTextFieldValueNumFiles(){
		return Integer.parseInt(textFieldValueNumFiles.getText());
	}
	int getTextFieldValueNumCol(){
		return Integer.parseInt(textFieldValueNumCol.getText());
	}
	String getTexttextFieldValueNomMines(){
		return textFieldValueNomMines.getText();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		lblWarning.setText("");
		JButton botonPressed = (JButton)e.getSource();

		if ( botonPressed == btnAccepta) {
			if(nivellSeleccionat){
				presentationController.iniciarPartida(nivelEscollit);
				this.setVisible(false);	
			}
			else{
				lblWarning.setText("No has seleccionat cap nivell");
			}
		}
		else{
			//presentationController.mostraIniciSessio();
			presentationController.showLogin(true);
			this.setVisible(false);
		}

	}
}