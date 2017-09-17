package com.desktop.front;

import java.awt.AWTKeyStroke;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import com.desktop.database.ClienteDao;
import com.desktop.database.ParcelaDao;
import com.desktop.database.VendaDao;
import com.desktop.front.PainelVenda.DoubleFormatter;
import com.desktop.front.Tela_Cliente.DateFormatter;
import com.desktop.model.Cliente;
import com.desktop.model.Parcela;
import com.desktop.model.Venda;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashSet;

import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import java.util.Calendar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JFormattedTextField.AbstractFormatter;

@Form(Parcela.class)
public class Tela_Parcela extends JFrame {

	private static final String SALVAR = "salvar";
	private static final String ALTERAR = "alterar";
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblValor;
	private JLabel lblCliente;
	private JLabel lblEntrada;
	private JLabel lblData;
	private JLabel lblParcelas;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JPanel panel_1;

	@Bindable(field = "valor", formatter = DoubleFormatter.class)
	private JTextField textField;

	@Bindable(field = "venda", formatter = ClienteFormatter.class)
	private JTextField textField_2;

	@Bindable(field = "venda", formatter = VendaFormatter.class)
	private static JTextField textField_3;
	
	@Bindable(field = "vencimento", formatter = DateFormatter.class)
	private JFormattedTextField textField_4;
	
	private JTextField formattedTextField;

	@Bindable(field = "alias")
	private JTextField textField_5;

	private Binder binder;

	private int codigoParcela;

	// Indica se esta no modo de salvar ou de alterar cliente
	public static String tipoTela = "";
	public static JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParcelaDao dao = new ParcelaDao();
					
					Tela_Parcela frame = new Tela_Parcela(dao.listar().get(0));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 */
	public Tela_Parcela(Parcela parcela) {

		NumberFormat intFormat = NumberFormat.getIntegerInstance();
		intFormat.setGroupingUsed(false);
		NumberFormatter numberFormatter = new NumberFormatter(intFormat);
		numberFormatter.setValueClass(Integer.class); // optional, ensures you
														// will always get a
														// long value
		numberFormatter.setAllowsInvalid(true); // this is the key!!
		numberFormatter.setMinimum(0);
		tipoTela = SALVAR;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 780, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Parcela", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));

		lblValor = new JLabel("Valor");
		lblValor.setBounds(15, 143, 31, 15);
		lblValor.setFont(new Font("Tahoma", Font.BOLD, 12));

		lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(15, 65, 42, 15);
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 12));

		textField = new JTextField();
		textField.setBounds(102, 138, 423, 25);
		textField.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setBounds(102, 60, 157, 25);
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_2.setColumns(10);

		lblEntrada = new JLabel("Pedido");
		lblEntrada.setBounds(15, 26, 56, 15);
		lblEntrada.setFont(new Font("Tahoma", Font.BOLD, 12));

		textField_3 = new JTextField();
		textField_3.setEnabled(false);
		textField_3.setBounds(102, 21, 423, 25);
		textField_3.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_3.setColumns(10);

		lblData = new JLabel("Data");
		lblData.setBounds(15, 182, 56, 15);
		lblData.setFont(new Font("Tahoma", Font.BOLD, 12));

		MaskFormatter dataMask = null;
		try {
			dataMask = new MaskFormatter("##/##/####");

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		textField_4 = new JFormattedTextField(dataMask);
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setBounds(102, 177, 143, 25);
		textField_4.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_4.setColumns(10);

		lblParcelas = new JLabel("Parcela");
		lblParcelas.setBounds(15, 104, 56, 15);
		lblParcelas.setFont(new Font("Tahoma", Font.BOLD, 12));

		textField_5 = new JFormattedTextField(numberFormatter);
		textField_5.setEnabled(false);
		textField_5.setBounds(102, 99, 423, 25);
		textField_5.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_5.setColumns(10);

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
		panel.setLayout(null);
		panel.add(lblParcelas);
		panel.add(textField_5);
		panel.add(lblValor);
		panel.add(lblCliente);
		panel.add(lblEntrada);
		panel.add(textField_3);
		panel.add(textField_2);
		panel.add(textField);
		panel.add(lblData);
		panel.add(textField_4);

		comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.setFocusable(false);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				ClienteDao dao = new ClienteDao();
				try{
					textField_2.setText(""+ ((Cliente)dao.consultar((String)e.getItem())).getCodigo());
				}
				
				catch(Exception ex)
				{
					textField_2.setText("");
				}
				
			}
		});
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 12));

		String[] listaNomes = new String[PainelCliente.clientes.size() + 1];
		listaNomes[0] = "Selecione Cliente";
		int i = 1;
		for (Cliente client : PainelCliente.clientes) {
			listaNomes[i] = client.getNome();
			i++;
		}

		comboBox.setModel(new DefaultComboBoxModel(listaNomes));
		comboBox.setBounds(269, 60, 443, 25);
		panel.add(comboBox);

		btnSalvar = new JButton("SALVAR");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salvarParcela();

			}
		});
		btnSalvar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					salvarParcela();
				}
			}
		});

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
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
		this.addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		    	if (TelaTabbed.tabbedPane.getSelectedIndex() == 0) {
					PainelCliente.table.requestFocus();
					if (PainelCliente.table.getRowCount() > 0) {
						PainelCliente.table.addRowSelectionInterval(0, 0);
					}
				}

				else if (TelaTabbed.tabbedPane.getSelectedIndex() == 1) {
					PainelVenda.table.requestFocus();
					if (PainelVenda.table.getRowCount() > 0) {
						PainelVenda.table.addRowSelectionInterval(0, 0);
					}

				}

				else if (TelaTabbed.tabbedPane.getSelectedIndex() == 3) {
					PainelCidades.table.requestFocus();
					if (PainelCidades.table.getRowCount() > 0) {
						PainelCidades.table.addRowSelectionInterval(0, 0);
					}

				}
		    }
		});
		Tela_Cliente.installEscapeCloseOperation(this);
		
		
		
		binder.updateView(parcela);
		
		codigoParcela = parcela.getCodigo();
		tipoTela = ALTERAR;
		comboBox.setSelectedItem((parcela.getVenda().getCliente().getNome()));
		
		formattedTextField = new JTextField();
		formattedTextField.setFont(new Font("Dialog", Font.PLAIN, 16));
		formattedTextField.setColumns(10);
		formattedTextField.setBounds(102, 216, 143, 25);
		panel.add(formattedTextField);
		formattedTextField.setText( parcela.getStatus()==1?"P":"D");
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStatus.setBounds(15, 218, 56, 15);
		panel.add(lblStatus);
	}

	private void salvarParcela() {
		Parcela parcela = new Parcela();
		binder.updateModel(parcela);
		parcela.setStatus(formattedTextField.getText()!=null && formattedTextField.getText().equalsIgnoreCase("P")?1:0);
			ParcelaDao dao = new ParcelaDao();
			Object[] options = { "CONFIRMAR", "CANCELAR" };
			int resposta = JOptionPane.showOptionDialog(null, "Confirma alteração da parcela " + parcela.getAlias() + " ?",
					"Atenção", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (resposta == 0) {
					parcela.setCodigo(codigoParcela);
					parcela.setVenda(dao.consultarParcela(Integer.parseInt(textField_3.getText())));
					if(formattedTextField.getText().equals("P"))
					{
						parcela.setStatus(1);
					}
					parcela = (Parcela) dao.atualizar(parcela);

				Tela_Parcelas.carregarParcelas();
				contentPane.setVisible(false);
				dispose();
			}


	}



	
	// IntFormatter sera usado para transformar a String em numero.
		public static class DoubleFormatter implements Formatter {
			public Object format(Object obj) {
				Double d = (Double) obj;
				return ((d.doubleValue() + "").replace(".", ","));
			}

			public Object parse(Object obj) {

				String doubleFormatado = (String) obj;
				doubleFormatado = doubleFormatado.replace(".", "");
				doubleFormatado = doubleFormatado.replace(",", ".");
				return new BigDecimal(Double.valueOf(Double.parseDouble(doubleFormatado))).setScale(2,
						BigDecimal.ROUND_HALF_UP).doubleValue();
			}

			public String getName() {
				return "BigDecimal";
			}
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
	
	
	// ClienteFormatter sera usado para transformar a String em Cliente.
	public static class ClienteFormatter implements Formatter {
		public Object format(Object obj) {
			Venda v = (Venda) obj;
			Cliente c = v.getCliente();
			return "" + c.getCodigo();
		}

		public Object parse(Object obj) {
			ClienteDao dao = new ClienteDao();
			Cliente cliente = dao.consultarCodigo(Integer.parseInt((String) obj));
			VendaDao daoVenda = new VendaDao();
			Venda venda =  daoVenda.consultarCodigo(Integer.parseInt((String)obj));
			venda.setCliente(cliente);
			return venda;
		}

		public String getName() {
			return "Cliente";
		}
	}
	
	// ClienteFormatter sera usado para transformar a String em Cliente.
		public static class VendaFormatter implements Formatter {
			public Object format(Object obj) {
				Venda v = (Venda) obj;
				return "" + v.getPedido();
			}

			public Object parse(Object obj) {
				VendaDao daoVenda = new VendaDao();
				Venda venda =  daoVenda.consultarCodigo(Integer.parseInt((String)obj));
				return venda;
			}

			public String getName() {
				return "Venda";
			}
		}
}
