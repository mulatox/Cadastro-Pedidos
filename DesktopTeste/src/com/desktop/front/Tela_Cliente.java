package com.desktop.front;

import java.awt.AWTKeyStroke;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import com.desktop.database.ClienteDao;
import com.desktop.front.Autocomplete.CommitAction;
import com.desktop.front.TelaTabbed.IntFormatter;
import com.desktop.model.Cliente;
import com.towel.bean.Formatter;
import com.towel.bind.Binder;
import com.towel.bind.annotation.AnnotatedBinder;
import com.towel.bind.annotation.Bindable;
import com.towel.bind.annotation.Form;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;

@Form(Cliente.class)
public class Tela_Cliente extends JFrame {

	private static final String SALVAR = "salvar";
	private static final String ALTERAR = "alterar";
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblEndereo;
	private JLabel label_1;
	private JLabel lblCidade;
	private JLabel label_4;
	private JLabel lblDataNascimento;
	private JLabel label_6;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JPanel panel_1;
	
	@Bindable(field = "endereco")
	private JTextField textField;

	@Bindable(field = "nome")
	private JTextField textField_2;

	@Bindable(field = "bairro")
	private JTextField textField_3;

	@Bindable(field = "telefone")
	private JFormattedTextField textField_4;
	
	private JFormattedTextField textField_5;
	
	@Bindable(field = "cpf")
	private JFormattedTextField textField_6;
	
	private Binder binder;
	
	private int CodigoCliente;
	
	
	//Indica se esta no modo de salvar ou de alterar cliente
	public static String tipoTela= "";
	private JLabel label;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_Cliente frame = new Tela_Cliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Tela_Cliente(Cliente cliente) {
		this();
		binder.updateView(cliente);
		CodigoCliente=cliente.getCodigo();
		tipoTela=ALTERAR;
		
	}
	
	/**
	 * Create the frame.
	 */
	public Tela_Cliente() {
		
		tipoTela=SALVAR;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 681, 569);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Cliente", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(51, 51, 51)));

		lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setBounds(15, 123, 69, 15);
		lblEndereo.setFont(new Font("Tahoma", Font.BOLD, 12));

		label_1 = new JLabel("Nome");
		label_1.setBounds(15, 46, 34, 15);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));

		textField = new JTextField();
		textField.setBounds(105, 116, 513, 25);
		textField.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(105, 38, 513, 25);
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField_2.setColumns(10);

		lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(15, 201, 56, 15);
		lblCidade.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		

		textField_3 = new JTextField();
		textField_3.setBounds(105, 194, 513, 25);
		textField_3.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField_3.setColumns(10);
		
		
		/*// Without this, cursor always leaves text field
				textField_3.setFocusTraversalKeysEnabled(false);
				// Our words to complete
				ArrayList keywords = new ArrayList<String>(5);
				        keywords.add("fortaleza");
				        keywords.add("caucaia");
				        keywords.add("fortin");
				        keywords.add("pecem");
				Autocomplete autoComplete = new Autocomplete(textField_3, keywords);
				textField_3.getDocument().addDocumentListener(autoComplete);

				// Maps the tab key to the commit action, which finishes the autocomplete
				// when given a suggestion
				textField_3.getInputMap().put(KeyStroke.getKeyStroke("TAB"), "commit");
				textField_3.getActionMap().put("commit", autoComplete.new CommitAction());*/

		label_4 = new JLabel("Telefone");
		label_4.setBounds(15, 240, 64, 15);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 12));

		MaskFormatter foneMask=null;
		try {
			foneMask = new MaskFormatter("(##)#########");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		textField_4 = new JFormattedTextField(foneMask);
		textField_4.setBounds(105, 233, 147, 25);
		textField_4.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField_4.setColumns(10);

		lblDataNascimento = new JLabel("Data Nascimento");
		lblDataNascimento.setBounds(269, 240, 103, 15);
		lblDataNascimento.setFont(new Font("Tahoma", Font.BOLD, 12));

		MaskFormatter dataMask=null;
		try {
			dataMask = new MaskFormatter("##/##/####");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		textField_5 = new JFormattedTextField(dataMask);
		textField_5.setBounds(385, 233, 147, 25);
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField_5.setColumns(10);

		label_6 = new JLabel("CPF");
		label_6.setBounds(15, 84, 56, 15);
		label_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		MaskFormatter cpfMask=null;
		try {
			cpfMask = new MaskFormatter("###.###.###-##");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		textField_6 = new JFormattedTextField(cpfMask);
		textField_6.setBounds(105, 77, 147, 25);
		textField_6.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField_6.setColumns(10);

		panel_1 = new JPanel();
		panel_1.setBorder(null);
		
		label = new JLabel("Bairro");
		label.setBounds(15, 162, 56, 15);
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		textField_1 = new JTextField();
		textField_1.setBounds(105, 155, 513, 25);
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField_1.setColumns(10);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salvarCliente();
				
			}
		});
		btnSalvar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					salvarCliente();
				}
			}
		});

		btnCancelar = new JButton("Cancelar");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addContainerGap().addComponent(btnSalvar).addGap(59)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(335, Short.MAX_VALUE)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addContainerGap().addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSalvar).addComponent(btnCancelar))
						.addContainerGap(23, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 645, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 624, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(10, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(null);
		panel.add(label_1);
		panel.add(lblEndereo);
		panel.add(label);
		panel.add(label_4);
		panel.add(label_6);
		panel.add(lblCidade);
		panel.add(textField_4);
		panel.add(lblDataNascimento);
		panel.add(textField_5);
		panel.add(textField_3);
		panel.add(textField_1);
		panel.add(textField);
		panel.add(textField_6);
		panel.add(textField_2);
		contentPane.setLayout(gl_contentPane);
		HashSet conj = new HashSet(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
		conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
		panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
		binder = new AnnotatedBinder(this);
	}
	
	private void salvarCliente ()
	{
		Cliente cliente = new Cliente();
		binder.updateModel(cliente);
		if(validado(cliente))
		{
			ClienteDao dao = new ClienteDao();
			Object[] options = { "Confirmar", "Cancelar" };
			int resposta =JOptionPane.showOptionDialog(null,
					"Confirma o cadastro do cliente " + cliente.getNome()+" ?", "Atenção",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if(resposta==0)
			{
				if(tipoTela.equals(SALVAR))
				{
					dao.inserir(cliente);
				}
				else
				{
					cliente.setCodigo(CodigoCliente);
					dao.atualizar(cliente);
				}
				PainelCliente.carregarClientes();
				contentPane.setVisible(false);
				dispose();
			}
			
		}
		
		
	}
	
	public boolean validado(Cliente cliente)
	{
		if(cliente.getNome()==null || cliente.getNome().isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Campo Nome obrigatório","Campos Obrigatórios",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		if(cliente.getEndereco()==null || cliente.getEndereco().isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Campo Logradouro obrigatório","Campos Obrigatórios",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		
		if(cliente.getTelefone()==null || cliente.getTelefone().isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Campo Telefone obrigatório","Campos Obrigatórios",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		return true;
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
}
