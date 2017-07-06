package com.desktop.front;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.MaskFormatter;

import com.desktop.database.ClienteDao;
import com.desktop.database.ParcelaDao;
import com.desktop.database.VendaDao;
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
import java.awt.Container;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

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
import javax.swing.SwingConstants;

@Form(Venda.class)
public class PainelVenda extends JPanel {

	@Bindable(field = "pedido", formatter = IntFormatter.class)
	private JTextField textField;

	@Bindable(field = "cliente", formatter = IntFormatter.class)
	private JTextField textField_1;

	@Bindable(field = "valor", formatter = DoubleFormatter.class)
	private JTextField textField_2;

	@Bindable(field = "data", formatter = DateFormatter.class)
	JFormattedTextField formattedTextField_2;

	private Binder binder;

	private Tela_Venda telaVenda;
	public static Venda vendaSelecionada;
	public static ArrayList<Venda> vendas;
	public static JTable table;
	private static final int INATIVO = -1;

	@Bindable(field = "parcelas", formatter = IntFormatter.class)
	private JTextField textField_3;

	/**
	 * Create the panel.
	 */
	public PainelVenda(final Container container) {

		JButton button = new JButton("CADASTRAR");
		button.setFont(new Font("Tahoma", Font.BOLD, 12));

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				telaVenda = new Tela_Venda();
				telaVenda.setVisible(true);

			}
		});

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "LISTA DE PEDIDOS", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(51, 51, 51)));

		JScrollPane scrollPane = new JScrollPane();

		AnnotationResolver resolver = new AnnotationResolver(Venda.class);
		ObjectTableModel<Venda> tableModel = new ObjectTableModel<Venda>(resolver,
				"pedido,cliente,valor,parcelas,data");
		VendaDao dao = new VendaDao();
		vendas = dao.listar();
		tableModel.setData(vendas);
		table = new JTable(tableModel);
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		table.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
				int indice = table.rowAtPoint(e.getPoint());
				if (indice != INATIVO) {
					vendaSelecionada = vendas.get(indice);
					binder.updateView(vendaSelecionada);
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

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "DADOS PEDIDO", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(51, 51, 51)));

		JLabel lblBairro = new JLabel("CLIENTE");
		lblBairro.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblNome = new JLabel("PEDIDO");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblBairro_1 = new JLabel("VALOR");
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
		MaskFormatter cpfMask = null;
		try {
			cpfMask = new MaskFormatter("###.###.###-##");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JLabel lblData = new JLabel("DATA");
		lblData.setFont(new Font("Tahoma", Font.BOLD, 12));
		MaskFormatter foneMask = null;
		try {
			foneMask = new MaskFormatter("(##)#########");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JButton button_1 = new JButton("ALTERAR");
		button_1.setFont(new Font("Tahoma", Font.BOLD, 12));

		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (vendaSelecionada != null) {
					telaVenda = new Tela_Venda(vendaSelecionada);
					telaVenda.setVisible(true);

				} else {
					JOptionPane.showInternalMessageDialog(container, "Nenhum pedido selecionado", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});

		JButton button_2 = new JButton("EXCLUIR");
		button_2.setFont(new Font("Tahoma", Font.BOLD, 12));

		button_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				if (vendaSelecionada != null) {
					Object[] options = { "Confirmar", "Cancelar" };
					int resposta = JOptionPane.showOptionDialog(container,
							"Deseja realmente excluir o pedido "
									+ vendaSelecionada.getPedido() + " ?",
							"Atenção", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
							options[0]);
					if (resposta == 0) {
						VendaDao dao = new VendaDao();
						vendaSelecionada.setStatus(INATIVO);
						dao.atualizar(vendaSelecionada);
						removerParcelas(vendaSelecionada);
						carregarVendas();
					}

				} else {
					JOptionPane.showInternalMessageDialog(container, "Nenhum pedido selecionado", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});

		JLabel lblCidade = new JLabel("PARCELAS");
		lblCidade.setFont(new Font("Tahoma", Font.BOLD, 12));

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_3.setEditable(false);
		textField_3.setColumns(10);

		MaskFormatter dataMask = null;
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

		binder = new AnnotatedBinder(this);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1
				.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
						.createSequentialGroup().addGap(10).addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 56,
												GroupLayout.PREFERRED_SIZE)
										.addGap(31).addComponent(textField, GroupLayout.PREFERRED_SIZE, 454,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 45,
												GroupLayout.PREFERRED_SIZE)
										.addGap(42).addComponent(formattedTextField_2, GroupLayout.PREFERRED_SIZE, 175,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 111,
												GroupLayout.PREFERRED_SIZE)
										.addGap(69).addComponent(button_2, GroupLayout.PREFERRED_SIZE, 111,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(lblCidade, GroupLayout.PREFERRED_SIZE, 69,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(textField_3, 454, 454, 454))
								.addGroup(gl_panel_1.createSequentialGroup()
										.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
												.addComponent(lblBairro_1, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblBairro, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGap(38)
										.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
												.addComponent(textField_1).addComponent(textField_2,
														GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE))))
						.addGap(452)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup().addGap(12).addComponent(lblNome,
										GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup().addGap(5).addComponent(textField,
										GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup().addGap(25).addComponent(lblBairro,
										GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup().addGap(18).addComponent(textField_1,
										GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup().addGap(18).addComponent(textField_2,
										GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup().addGap(23).addComponent(lblBairro_1,
										GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup().addGap(20).addComponent(textField_3,
										GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup().addGap(25).addComponent(lblCidade,
										GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)))
						.addGap(18)
						.addGroup(
								gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 14,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(formattedTextField_2, GroupLayout.PREFERRED_SIZE, 21,
												GroupLayout.PREFERRED_SIZE))
						.addGap(51).addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addComponent(button_1)
								.addComponent(button_2))));
		panel_1.setLayout(gl_panel_1);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(20).addComponent(button,
						GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(Alignment.LEADING,
										groupLayout.createSequentialGroup().addContainerGap().addComponent(panel, 0, 0,
												Short.MAX_VALUE))
								.addGroup(Alignment.LEADING,
										groupLayout.createSequentialGroup().addGap(12).addComponent(panel_1,
												GroupLayout.PREFERRED_SIZE, 562, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(446, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(1)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE).addGap(50)
						.addComponent(button)));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addContainerGap()
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
								.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_INSERT) {
					telaVenda = new Tela_Venda();
					telaVenda.setVisible(true);
				}
			}
		});

		table.requestFocus();
		table.changeSelection(0, 0, false, false);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				int indice = table.getSelectedRow();
				if (indice != INATIVO) {
					vendaSelecionada = vendas.get(indice);
					binder.updateView(vendaSelecionada);
					Cliente clienteTeste = new Cliente();
					binder.updateModel(clienteTeste);
					System.out.println(clienteTeste.getNome());
				}
			}
		});

	}

	public static void carregarVendas() {
		AnnotationResolver resolver = new AnnotationResolver(Venda.class);
		ObjectTableModel<Venda> tableModel = new ObjectTableModel<Venda>(resolver,
				"pedido,cliente,valor,parcelas,data");
		VendaDao dao = new VendaDao();
		vendas = dao.listar();
		tableModel.setData(vendas);
		table.setModel(tableModel);
		tableModel.fireTableDataChanged();
		table.repaint();
	}

	public static void removerParcelas(Venda venda)
	{
		ParcelaDao dao = new ParcelaDao();
		for(Parcela parcela:dao.listar(venda.getPedido()))
		{
			dao.remover(parcela);
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

	// IntFormatter sera usado para transformar a String em numero.
	public static class DoubleFormatter implements Formatter {
		public Object format(Object obj) {
			Double d = (Double) obj;
			return d.toString().replace(".", ",");
		}

		public Object parse(Object obj) {
			
			String doubleFormatado=(String) obj;
			doubleFormatado=doubleFormatado.replace(".", "");
			doubleFormatado=doubleFormatado.replace(",", ".");
			return Double.valueOf(Double.parseDouble(doubleFormatado));
		}

		public String getName() {
			return "double";
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
