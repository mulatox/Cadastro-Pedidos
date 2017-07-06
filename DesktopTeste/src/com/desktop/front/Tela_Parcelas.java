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
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import com.desktop.database.ClienteDao;
import com.desktop.database.ParcelaDao;
import com.desktop.database.VendaDao;
import com.desktop.front.Autocomplete.CommitAction;
import com.desktop.model.Cliente;
import com.desktop.model.Parcela;
import com.desktop.model.Venda;
import com.towel.bean.Formatter;
import com.towel.bind.Binder;
import com.towel.bind.annotation.AnnotatedBinder;
import com.towel.bind.annotation.Bindable;
import com.towel.bind.annotation.Form;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;

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
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Tela_Parcelas extends JFrame {

	private static final String SALVAR = "salvar";
	private static final String ALTERAR = "alterar";
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblTelefone;
	private JButton btnSalvar;
	private JPanel panel_1;
	public static ArrayList<Parcela> parcelas;

	private JFormattedTextField textField_4;

	private Binder binder;

	private int CodigoCliente;

	// Indica se esta no modo de salvar ou de alterar cliente
	public static String tipoTela = "";
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_Parcelas frame = new Tela_Parcelas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Tela_Parcelas(Cliente cliente) {
		this();
		binder.updateView(cliente);
		CodigoCliente = cliente.getCodigo();
		tipoTela = ALTERAR;

	}

	/**
	 * Create the frame.
	 */
	public Tela_Parcelas() {

		tipoTela = SALVAR;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 681, 569);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "QUITAR PARCELAS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));

		/*
		 * // Without this, cursor always leaves text field
		 * textField_3.setFocusTraversalKeysEnabled(false); // Our words to
		 * complete ArrayList keywords = new ArrayList<String>(5);
		 * keywords.add("fortaleza"); keywords.add("caucaia");
		 * keywords.add("fortin"); keywords.add("pecem"); Autocomplete
		 * autoComplete = new Autocomplete(textField_3, keywords);
		 * textField_3.getDocument().addDocumentListener(autoComplete);
		 * 
		 * // Maps the tab key to the commit action, which finishes the
		 * autocomplete // when given a suggestion
		 * textField_3.getInputMap().put(KeyStroke.getKeyStroke("TAB"),
		 * "commit"); textField_3.getActionMap().put("commit", autoComplete.new
		 * CommitAction());
		 */

		lblTelefone = new JLabel("PEDIDO");
		lblTelefone.setBounds(25, 66, 64, 15);
		lblTelefone.setFont(new Font("Tahoma", Font.BOLD, 12));

		// MaskFormatter foneMask = null;
		// foneMask = new MaskFormatter("(##)#########");
		NumberFormat longFormat = NumberFormat.getIntegerInstance();
		longFormat.setGroupingUsed(false);
		NumberFormatter numberFormatter = new NumberFormatter(longFormat);
		numberFormatter.setValueClass(Long.class); //optional, ensures you will always get a long value
		numberFormatter.setAllowsInvalid(false); //this is the key!!
		numberFormatter.setMinimum(0l);
		textField_4 = new JFormattedTextField(numberFormatter);
		textField_4.setBounds(105, 59, 147, 25);
		textField_4.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_4.setColumns(10);

		MaskFormatter dataMask = null;
		try {
			dataMask = new MaskFormatter("##/##/####");

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		MaskFormatter cpfMask = null;
		try {
			cpfMask = new MaskFormatter("###.###.###-##");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		panel_1 = new JPanel();
		panel_1.setBorder(null);

		DocumentFilter filter = new UppercaseDocumentFilter();
		((AbstractDocument) textField_4.getDocument()).setDocumentFilter(filter);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 645, Short.MAX_VALUE)
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 57, Short.MAX_VALUE)
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "LISTA DE PARCELAS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGap(0, 564, Short.MAX_VALUE)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGap(0, 238, Short.MAX_VALUE)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		AnnotationResolver resolver = new AnnotationResolver(Parcela.class);
		ObjectTableModel<Parcela> tableModel = new ObjectTableModel<Parcela>(resolver,
				"alias,valor,vencimento");
		ParcelaDao dao = new ParcelaDao();
		parcelas = dao.listar();
		tableModel.setData(parcelas);
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		panel_2.setLayout(gl_panel_2);
		btnSalvar = new JButton("SALVAR");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		btnSalvar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 564, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(218)
									.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)))
							.addGap(393)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 645, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 624, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(224)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnSalvar)))
					.addContainerGap())
		);
		panel.setLayout(null);
		panel.add(lblTelefone);
		panel.add(textField_4);
		contentPane.setLayout(gl_contentPane);
		HashSet conj = new HashSet(panel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
		conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
		panel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
		binder = new AnnotatedBinder(this);
	}

	private void salvarParcela() {
		Parcela parcela = new Parcela();
		binder.updateModel(parcela);
		if (validado(parcela)) {
			ParcelaDao dao = new ParcelaDao();
			Object[] options = { "CONFIRMAR", "CANCELAR" };
			int resposta = JOptionPane.showOptionDialog(null,
					"Confirma a quitação da parcela  " + parcela.getAlias() + " ?", "Atenção", JOptionPane.DEFAULT_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (resposta == 0) {
					dao.atualizar(parcela);
				contentPane.setVisible(false);
				dispose();
			}

		}

	}

	public boolean validado(Parcela parcelas) {

		return true;
	}
}
