package com.desktop.front;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.MaskFormatter;

import com.desktop.database.CidadeEstadoDao;
import com.desktop.database.ClienteDao;
import com.desktop.database.ParcelaDao;
import com.desktop.front.Tela_Venda.IntFormatter;
import com.desktop.model.CidadeEstado;
import com.desktop.model.Cliente;
import com.desktop.model.Parcela;
import com.towel.bean.Formatter;
import com.towel.bind.Binder;
import com.towel.bind.annotation.AnnotatedBinder;
import com.towel.bind.annotation.Bindable;
import com.towel.bind.annotation.Form;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ChangeEvent;

@Form(Cliente.class)
public class PainelCliente extends JPanel {
	
	@Bindable(field = "nome")
	private JTextField textField;
	
	@Bindable(field = "endereco")
	private JTextField textField_1;
	
	@Bindable(field = "bairro")
	private JTextField textField_2;
	
	@Bindable(field = "cpf")
	private JFormattedTextField formattedTextField;
	
	@Bindable(field = "telefone")
	JFormattedTextField formattedTextField_1;
	
	@Bindable(field = "dataNascimento", formatter = DateFormatter.class)
	JFormattedTextField formattedTextField_2;
	
	private Binder binder;
	
	static JCheckBox chckbxNewCheckBox;
	
	private Tela_Cliente telaCliente;
	public static Cliente clienteSelecionado;
	public static ArrayList<Cliente> clientes;
	public static JTable table;
	private static final int INATIVO = -1;
	
	@Bindable(field = "cidade_estado", formatter = CidadeFormatter.class)
	private JTextField textField_3;
	
	@Bindable(field = "observacao")
	private JTextField textField_4;

	private JPanel panel_1;
	private JTextField textField_5;
	/**
	 * Create the panel.
	 */
	public PainelCliente(final Container container) {
		
		JButton button = new JButton("CADASTRAR");
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				telaCliente = new Tela_Cliente();
				telaCliente.setVisible(true);

			}
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "LISTA DE CLIENTES", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JScrollPane scrollPane = new JScrollPane();
		
		final AnnotationResolver resolver = new AnnotationResolver(Cliente.class);
		ObjectTableModel<Cliente> tableModel = new ObjectTableModel<Cliente>(resolver,
				"codigo,nome,cpf,endereco,telefone");
		ClienteDao dao = new ClienteDao();
		clientes =dao.listar();
		tableModel.setData(clientes);
		table = new JTable(tableModel);
		table.setFont(new Font("Tahoma", Font.BOLD, 14));
		table.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int indice = table.rowAtPoint(e.getPoint());
					if (indice != INATIVO) {
						telaCliente = new Tela_Cliente(clienteSelecionado);
						telaCliente.setVisible(true);
					}
					
				}
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
				int indice = table.rowAtPoint(e.getPoint());
				if (indice != INATIVO) {
					clienteSelecionado = clientes.get(indice);
					binder.updateView(clienteSelecionado);
					Cliente clienteTeste = new Cliente();
					binder.updateModel(clienteTeste);
					System.out.println(clienteTeste.getNome());
				}
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}
		});
		scrollPane.setViewportView(table);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "DADOS CLIENTE", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JLabel lblBairro = new JLabel("ENDERE\u00C7O");
		lblBairro.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblNome = new JLabel("NOME");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblBairro_1 = new JLabel("BAIRRO");
		lblBairro_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField.setEditable(false);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 12));
		MaskFormatter cpfMask=null;
		try {
			cpfMask = new MaskFormatter("###.###.###-##");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		formattedTextField = new JFormattedTextField(cpfMask);
		formattedTextField.setFont(new Font("Dialog", Font.PLAIN, 16));
		formattedTextField.setEditable(false);
		formattedTextField.setColumns(10);
		
		JLabel lblTelefone = new JLabel("TELEFONE");
		lblTelefone.setFont(new Font("Tahoma", Font.BOLD, 12));
		MaskFormatter foneMask=null;
		try {
			foneMask = new MaskFormatter("(##)#########");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		formattedTextField_1 = new JFormattedTextField(foneMask);
		formattedTextField_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		formattedTextField_1.setEditable(false);
		formattedTextField_1.setColumns(10);
		
		JButton button_1 = new JButton("ALTERAR");
		button_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (clienteSelecionado != null) {
					telaCliente = new Tela_Cliente(clienteSelecionado);
					telaCliente.setVisible(true);

				} else {
					JOptionPane.showInternalMessageDialog(container, "Nenhum cliente selecionado", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		
		JButton button_2 = new JButton("EXCLUIR");
		button_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		button_2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {

				if (clienteSelecionado != null) {
					Object[] options = { "Confirmar", "Cancelar" };
					int resposta =JOptionPane.showOptionDialog(container,
							"Deseja realmente excluir o cliente " + clienteSelecionado.getNome()+" ?", "Aten��o",
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(resposta==0)
					{
						ClienteDao dao = new ClienteDao();
						clienteSelecionado.setStatus(INATIVO);
						dao.atualizar(clienteSelecionado);
						carregarClientes();
					}

				} else {
					JOptionPane.showInternalMessageDialog(container, "Nenhum cliente selecionado", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		
		JLabel lblCidade = new JLabel("CIDADE");
		lblCidade.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		
		MaskFormatter dataMask=null;
		try {
			dataMask = new MaskFormatter("##/##/####");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		formattedTextField_2 = new JFormattedTextField(dataMask);
		formattedTextField_2.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField_2.setFont(new Font("Dialog", Font.PLAIN, 16));
		formattedTextField_2.setEditable(false);
		formattedTextField_2.setColumns(10);
		
		JLabel lblDataNasc = new JLabel("DATA NASC");
		lblDataNasc.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		
		binder = new AnnotatedBinder(this);
		
		JLabel lblReferncias = new JLabel("REFER�NCIAS");
		lblReferncias.setFont(new Font("Tahoma", Font.BOLD, 12));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addGap(42)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 904, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblBairro)
							.addGap(23)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 906, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblBairro_1, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 906, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblCidade, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 906, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblCpf, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
							.addGap(31)
							.addComponent(formattedTextField, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(lblTelefone, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(formattedTextField_1, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblDataNasc, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(formattedTextField_2, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblReferncias)
							.addGap(4)
							.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 905, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
							.addGap(69)
							.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(7)
							.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(7)
							.addComponent(lblBairro, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblBairro_1, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblCidade, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblCpf, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addComponent(formattedTextField, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblTelefone, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addComponent(formattedTextField_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(3)
							.addComponent(lblDataNasc, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addComponent(formattedTextField_2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblReferncias, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(button_1)
						.addComponent(button_2)))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNewLabel = new JLabel("F1 NOVO PEDIDO  F3 NOVO CLIENTE  F5 QUITAR PARCELAS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		chckbxNewCheckBox = new JCheckBox("CLIENTES PENDENTES");
		chckbxNewCheckBox.setFocusable(false);
		chckbxNewCheckBox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(chckbxNewCheckBox.isSelected()){
					carregarClientesPendentes();
				}
				
				else{
					carregarClientes();
				}
			}
		});
		chckbxNewCheckBox.setFont(new Font("Arial", Font.BOLD, 16));
		chckbxNewCheckBox.setForeground(Color.RED);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Arial", Font.BOLD, 16));
		textField_5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {

				if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (TelaTabbed.tabbedPane.getSelectedIndex() < 3)
						TelaTabbed.tabbedPane.setSelectedIndex(TelaTabbed.tabbedPane.getSelectedIndex() + 1);
				}

				else if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
					if (TelaTabbed.tabbedPane.getSelectedIndex() > 0)
						TelaTabbed.tabbedPane.setSelectedIndex(TelaTabbed.tabbedPane.getSelectedIndex() - 1);
				}
			}
		});
		textField_5.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				atualizar();
			}

			public void removeUpdate(DocumentEvent e) {
				atualizar();
			}

			public void insertUpdate(DocumentEvent e) {
				atualizar();
			}

			public void atualizar() {
				chckbxNewCheckBox.setSelected(false);
				ClienteDao dao = new ClienteDao();
				if (textField_5.getText() == null || textField_5.getText().trim().isEmpty()) {
					carregarClientes();
				}

				else if(textField_5.getText().length()>3) {
						clientes = dao.listarNome(textField_5.getText().toUpperCase());
						AnnotationResolver resolver = new AnnotationResolver(Cliente.class);
						ObjectTableModel<Cliente> tableModel = new ObjectTableModel<Cliente>(resolver,
								"codigo,nome,cpf,endereco,bairro,telefone");
						tableModel.setData(clientes);
						table.setModel(tableModel);
						tableModel.fireTableDataChanged();
						table.repaint();
				}
				

			}
		});
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("CONSULTAR NOME");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 16));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 979, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 1372, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 1036, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(chckbxNewCheckBox)
								.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(54, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addComponent(chckbxNewCheckBox)
							.addGap(47)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel)
					.addGap(30)
					.addComponent(button))
		);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 1332, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(11)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					telaCliente = new Tela_Cliente(clienteSelecionado);
					telaCliente.setVisible(true);
					
					
				}
				
				else if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
					if(TelaTabbed.tabbedPane.getSelectedIndex()<3)
						TelaTabbed.tabbedPane.setSelectedIndex(TelaTabbed.tabbedPane.getSelectedIndex() + 1);
				}

				else if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
					if(TelaTabbed.tabbedPane.getSelectedIndex()>0)
						TelaTabbed.tabbedPane.setSelectedIndex(TelaTabbed.tabbedPane.getSelectedIndex() - 1);
				}
				
				else if (arg0.getKeyCode() == KeyEvent.VK_DELETE) {
					if (clienteSelecionado != null) {
						Object[] options = { "Confirmar", "Cancelar" };
						int resposta =JOptionPane.showOptionDialog(container,
								"Deseja realmente excluir o cliente " + clienteSelecionado.getNome()+" ?", "Aten��o",
								JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
						if(resposta==0)
						{
							ClienteDao dao = new ClienteDao();
							clienteSelecionado.setStatus(INATIVO);
							dao.atualizar(clienteSelecionado);
							carregarClientes();
						}

					} else {
						JOptionPane.showInternalMessageDialog(container, "Nenhum cliente selecionado", "Aviso",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
				
			}
		});
		
		table.requestFocus();
		if(table.getRowCount()>0)
		{
		table.addRowSelectionInterval(0,0);
		}
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			public void valueChanged(ListSelectionEvent e) {
				int indice = table.getSelectedRow();
				if (indice != INATIVO) {
					clienteSelecionado = clientes.get(indice);
					binder.updateView(clienteSelecionado);
					Cliente clienteTeste = new Cliente();
					binder.updateModel(clienteTeste);
					System.out.println(clienteTeste.getNome());
				}
			}
		});
		
		if(clienteSelecionado==null && clientes!=null && clientes.size()>0)
		{
			clienteSelecionado=clientes.get(0);
			binder.updateView(clienteSelecionado);
		}

	}
	
	public static void carregarClientes()
	{
		AnnotationResolver resolver = new AnnotationResolver(Cliente.class);
		ObjectTableModel<Cliente> tableModel = new ObjectTableModel<Cliente>(resolver,
				"codigo,nome,cpf,endereco,bairro,telefone");
		ClienteDao dao = new ClienteDao();
		clientes =dao.listar();
		tableModel.setData(clientes);
		table.setModel(tableModel);
		tableModel.fireTableDataChanged();
		table.repaint();
		chckbxNewCheckBox.setSelected(false);
	}
	
	public static void carregarClientesPendentes()
	{
		AnnotationResolver resolver = new AnnotationResolver(Cliente.class);
		ObjectTableModel<Cliente> tableModel = new ObjectTableModel<Cliente>(resolver,
				"codigo,nome,cpf,endereco,bairro,telefone");
		ClienteDao dao = new ClienteDao();
		clientes =dao.listarPendentes();
		tableModel.setData(clientes);
		table.setModel(tableModel);
		tableModel.fireTableDataChanged();
		table.repaint();
	}
	

	// IntFormatter sera usado para transformar a String em numero.
	public static class IntFormatter implements Formatter {
		public Object format(Object obj) {
			Integer d = (Integer) obj;
			return d.toString();
		}

		public Object parse(Object obj) {
			return Integer.valueOf(Integer.parseInt((String) obj));
		}

		public String getName() {
			return "int";
		}
	}
	
	// IntFormatter sera usado para transformar o codigo da cidade em codigo da cidada junto com o nome da cidade.
		public static class CidadeFormatter implements Formatter {
			public Object format(Object obj) {
				CidadeEstado d = (CidadeEstado) obj;
				return d.getCidade()+"-"+d.getEstado();
			}

			public Object parse(Object obj) {
				int codigo = Integer.parseInt((String)obj);
				CidadeEstadoDao dao = new CidadeEstadoDao();
				return dao.consultarCodigo(codigo);
			}

			public String getName() {
				return "String";
			}
		}
		
		// DateFormatter sera usado para transformar a String em data.
		public static class DateFormatter implements Formatter {
			public Object format(Object obj) {
				Date d = (Date) obj;
				SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
				return sm.format(d);
			}

			public Object parse(Object obj) {
				SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
				// Converting the String back to java.util.Date
				try {
					return sm.parse((String) obj);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "";
				}
			}

			public String getName() {
				return "int";
			}
		}
}
