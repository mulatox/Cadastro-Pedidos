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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
import com.sun.xml.internal.ws.api.server.Container;
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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

@Form(Parcela.class)
public class Tela_Parcelas extends JFrame {

	private static final String SALVAR = "salvar";
	private static final String ALTERAR = "alterar";
	private JPanel contentPane;
	private JButton btnSalvar;
	public static ArrayList<Parcela> parcelas;
	public static Parcela parcelaSelecionada;

	private Tela_Parcela telaParcela;

	private int codigoParcela;

	// Indica se esta no modo de salvar ou de alterar cliente
	public static String tipoTela = "";
	public static JTable table;
	private JTextField textField;
	AnnotationResolver resolver;

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

	public Tela_Parcelas(Parcela parcela) {
		this();
		codigoParcela = parcela.getCodigo();
		tipoTela = ALTERAR;

	}

	/**
	 * Create the frame.
	 */
	public Tela_Parcelas() {

		tipoTela = SALVAR;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 778, 569);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// MaskFormatter foneMask = null;
		// foneMask = new MaskFormatter("(##)#########");
		NumberFormat longFormat = NumberFormat.getIntegerInstance();
		longFormat.setGroupingUsed(false);
		NumberFormatter numberFormatter = new NumberFormatter(longFormat);
		numberFormatter.setValueClass(Long.class); // optional, ensures you will
													// always get a long value
		numberFormatter.setAllowsInvalid(false); // this is the key!!
		numberFormatter.setMinimum(0l);

		DocumentFilter filter = new UppercaseDocumentFilter();

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "LISTA DE PARCELAS",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2
				.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup().addContainerGap()
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
								.addContainerGap()));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		resolver = new AnnotationResolver(Parcela.class);
		ObjectTableModel<Parcela> tableModel = new ObjectTableModel<Parcela>(resolver,
				"alias,valor,vencimento,venda,status");
		ParcelaDao dao = new ParcelaDao();
		parcelas = dao.listarAtivas();
		tableModel.setData(parcelas);
		table = new JTable(tableModel);
		table.setFont(new Font("Tahoma", Font.BOLD, 14));

		scrollPane.setViewportView(table);
		panel_2.setLayout(gl_panel_2);
		btnSalvar = new JButton("QUITAR");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		btnSalvar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					if (parcelaSelecionada != null) {
						parcelaSelecionada.setStatus(2);
						ParcelaDao dao = new ParcelaDao();
						dao.atualizar(parcelaSelecionada);
						carregarParcelas();

					}
				}
			}
		});

		textField = new JTextField();
		textField.getDocument().addDocumentListener(new DocumentListener() {
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
				ParcelaDao dao = new ParcelaDao();
				if (textField.getText() == null || textField.getText().trim().isEmpty()) {
					parcelas = dao.listarAtivas();
				}

				else {
					try {
						int codigoPedido = Integer.parseInt(textField.getText());
						parcelas = dao.listarAtivasPedido(codigoPedido);

					} catch (NumberFormatException ne) {
						JOptionPane.showMessageDialog(null, "Erro: Por favor digite um valor válido (Apenas números)",
								"Mensagem de Erro", JOptionPane.ERROR_MESSAGE);
					}

				}
				resolver = new AnnotationResolver(Parcela.class);
				ObjectTableModel<Parcela> tableModel = new ObjectTableModel<Parcela>(resolver,
						"alias,valor,vencimento,venda,status");
				tableModel.setData(parcelas);
				table.setModel(tableModel);
				tableModel.fireTableDataChanged();
				table.repaint();

			}
		});
		textField.setColumns(10);

		JLabel lblPedido = new JLabel("PEDIDO");
		lblPedido.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblPedido)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE,
												129, GroupLayout.PREFERRED_SIZE)
										.addGap(42).addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 108,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(47)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblPedido)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGap(204)));
		contentPane.setLayout(gl_contentPane);

		table.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				/*
				 * if (arg0.getKeyCode() == KeyEvent.VK_ENTER) { Object[]
				 * options = { "Confirmar", "Cancelar" }; int resposta =
				 * JOptionPane.showOptionDialog(null,
				 * "Deseja realmente liberar a parcela " +
				 * parcelaSelecionada.getAlias() + " ?", "Atenção",
				 * JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
				 * null, options, options[0]); if (resposta == 0) {
				 * parcelaSelecionada.setStatus(2); ParcelaDao dao = new
				 * ParcelaDao(); dao.atualizar(parcelaSelecionada);
				 * carregarParcelas(); }
				 * 
				 * }
				 */
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					telaParcela = new Tela_Parcela(parcelaSelecionada);
					telaParcela.setVisible(true);

				}

				else if (arg0.getKeyCode() == KeyEvent.VK_DELETE) {
					Object[] options = { "Confirmar", "Cancelar" };
					int resposta = JOptionPane.showOptionDialog(null,
							"Deseja realmente voltar a parcela " + parcelaSelecionada.getAlias() + " ?", "Atenção",
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if (resposta == 0) {
						parcelaSelecionada.setStatus(0);
						ParcelaDao dao = new ParcelaDao();
						dao.atualizar(parcelaSelecionada);
						carregarParcelas();
					}

				}

			}
		});

		table.requestFocus();
		table.addRowSelectionInterval(0, 0);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				int indice = table.getSelectedRow();
				if (indice != -1) {
					parcelaSelecionada = parcelas.get(indice);
				}
			}
		});

		if (parcelaSelecionada == null && parcelas != null && parcelas.size() > 0) {
			parcelaSelecionada = parcelas.get(0);
		}
		Tela_Cliente.installEscapeCloseOperation(this);

	}

	private void salvarParcela() {
		Parcela parcela = new Parcela();
		if (validado(parcela)) {
			ParcelaDao dao = new ParcelaDao();
			Object[] options = { "CONFIRMAR", "CANCELAR" };
			int resposta = JOptionPane.showOptionDialog(null,
					"Confirma a quitação da parcela  " + parcela.getAlias() + " ?", "Atenção",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (resposta == 0) {
				dao.atualizar(parcela);
				contentPane.setVisible(false);
				dispose();
			}

		}

	}

	public static void carregarParcelas() {
		AnnotationResolver resolver = new AnnotationResolver(Parcela.class);
		ObjectTableModel<Parcela> tableModel = new ObjectTableModel<Parcela>(resolver,
				"alias,valor,vencimento,venda,status");
		ParcelaDao dao = new ParcelaDao();
		parcelas = dao.listarAtivas();
		tableModel.setData(parcelas);
		table.setModel(tableModel);
		tableModel.fireTableDataChanged();
		table.repaint();
	}

	public boolean validado(Parcela parcelas) {

		return true;
	}
}
