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

@Form(Cliente.class)
public class Tela_Cliente extends JFrame {

	private static final String SALVAR = "salvar";
	private static final String ALTERAR = "alterar";
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblEndereo;
	private JLabel label_1;
	private JLabel label_3;
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
		setBounds(100, 100, 626, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Cliente", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(51, 51, 51)));

		lblEndereo = new JLabel("Endereço");

		label_1 = new JLabel("Nome");

		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField_2.setColumns(10);

		label_3 = new JLabel("Bairro");
		
		

		textField_3 = new JTextField();
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

		MaskFormatter foneMask=null;
		try {
			foneMask = new MaskFormatter("(##)#########");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		textField_4 = new JFormattedTextField(foneMask);
		textField_4.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField_4.setColumns(10);

		lblDataNascimento = new JLabel("Data Nascimento");

		MaskFormatter dataMask=null;
		try {
			dataMask = new MaskFormatter("##/##/####");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		textField_5 = new JFormattedTextField(dataMask);
		textField_5.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField_5.setColumns(10);

		label_6 = new JLabel("CPF");
		
		MaskFormatter cpfMask=null;
		try {
			cpfMask = new MaskFormatter("###.###.###-##");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		textField_6 = new JFormattedTextField(cpfMask);
		textField_6.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField_6.setColumns(10);
		
	
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1)
								.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEndereo))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
								.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
							.addGap(2147482842))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblDataNascimento)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_6)
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEndereo))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(74)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDataNascimento)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(133))
		);
		panel.setLayout(gl_panel);

		panel_1 = new JPanel();
		panel_1.setBorder(null);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup().addGap(12)
												.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE))
										.addComponent(panel, GroupLayout.PREFERRED_SIZE, 602, Short.MAX_VALUE))
								.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE).addContainerGap()));

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
