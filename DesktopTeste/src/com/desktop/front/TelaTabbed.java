package com.desktop.front;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.AWTKeyStroke;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.Window.Type;
import javax.swing.KeyStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.MaskFormatter;

import com.desktop.database.ClienteDao;
import com.desktop.database.VendaDao;
import com.desktop.model.Cliente;
import com.desktop.model.Venda;
import com.desktop.utils.ClienteMock;
import com.towel.bean.Formatter;
import com.towel.bind.Binder;
import com.towel.bind.annotation.AnnotatedBinder;
import com.towel.bind.annotation.Bindable;
import com.towel.bind.annotation.Form;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;

import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JFormattedTextField;

public class TelaTabbed extends JFrame {

	private static final int INATIVO = -1;
	public static JTable tableVendas;


	private Tela_Cliente telaCliente;
	
	private Tela_Venda telaVenda;

	private Cliente clienteSelecionado;
	
	private Venda vendaSelecionada;
	
	public static ArrayList<Cliente> clientes;
	public static ArrayList<Venda> vendas;
	private JTextField textField_7;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_12;
	private JTextField textField_13;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaTabbed window = new TelaTabbed();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaTabbed() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setTitle("Sistema de Vendas");
		this.setBounds(100, 100, 781, 664);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(this.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout.createSequentialGroup()
						.addContainerGap().addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 1428, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(47, Short.MAX_VALUE)
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 788, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		JPanel panel = new JPanel();
		tabbedPane.addTab("Cliente", null, panel, "Cadastro e Lista de Clientes");
		
		PainelCliente panel_1 = new PainelCliente(getContentPane());
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 1382, Short.MAX_VALUE)
					.addGap(1132))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(29)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 703, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(29, Short.MAX_VALUE))
		);


		panel.setLayout(gl_panel);
		
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Venda", null, panel_6, null);
		
		JButton button = new JButton("CADASTRAR");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaVenda = new Tela_Venda();
				telaVenda.setVisible(true);
			}
		});
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Lista de Vendas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGap(0, 1382, Short.MAX_VALUE)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 1348, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_7.setVerticalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGap(0, 282, Short.MAX_VALUE)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		tableVendas = new JTable();
		
		
		carregarVendas();
		
		tableVendas.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
				int indice = tableVendas.rowAtPoint(e.getPoint());
				if (indice != INATIVO) {
					vendaSelecionada = vendas.get(indice);
					System.out.println( vendaSelecionada.getCliente());
					selecionarVenda();
				}
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}
		});
		scrollPane_1.setViewportView(tableVendas);
		panel_7.setLayout(gl_panel_7);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Venda", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JLabel lblValor = new JLabel("Valor");
		
		JLabel lblCliente = new JLabel("Cliente");
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField_9.setEditable(false);
		textField_9.setColumns(10);
		
		JLabel lblData_1 = new JLabel("Entrada");
		
		textField_10 = new JTextField();
		textField_10.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField_10.setEditable(false);
		textField_10.setColumns(10);
		
		JLabel lblData_2 = new JLabel("Parcelas");
		
		textField_12 = new JTextField();
		textField_12.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField_12.setEditable(false);
		textField_12.setColumns(10);
		
		JLabel lblData_3 = new JLabel("Data");
		
		textField_13 = new JTextField();
		textField_13.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField_13.setEditable(false);
		textField_13.setColumns(10);
		
		JButton button_1 = new JButton("ALTERAR");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (vendaSelecionada != null) {
					telaVenda = new Tela_Venda(vendaSelecionada);
					telaVenda.setVisible(true);

				} else {
					JOptionPane.showInternalMessageDialog(getContentPane(), "Nenhuma venda selecionada", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		JButton button_2 = new JButton("EXCLUIR");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (vendaSelecionada != null) {
					Object[] options = { "Confirmar", "Cancelar" };
					int resposta =JOptionPane.showOptionDialog(null,
							"Deseja realmente excluir esta venda ?", "AtenÃ§Ã£o",
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(resposta==0)
					{
						VendaDao dao = new VendaDao();
						dao.remover(vendaSelecionada);
					}

				} else {
					JOptionPane.showInternalMessageDialog(getContentPane(), "Nenhuma venda selecionada", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		GroupLayout gl_panel_8 = new GroupLayout(panel_8);
		gl_panel_8.setHorizontalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_8.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_8.createSequentialGroup()
							.addGroup(gl_panel_8.createParallelGroup(Alignment.LEADING)
								.addComponent(lblData_1, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblValor)
								.addComponent(lblCliente))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_8.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_8.createSequentialGroup()
									.addComponent(textField_10, GroupLayout.DEFAULT_SIZE, 893, Short.MAX_VALUE)
									.addGap(282))
								.addGroup(gl_panel_8.createParallelGroup(Alignment.TRAILING)
									.addComponent(textField_9, GroupLayout.DEFAULT_SIZE, 1208, Short.MAX_VALUE)
									.addGroup(gl_panel_8.createSequentialGroup()
										.addComponent(textField_7, GroupLayout.DEFAULT_SIZE, 1049, Short.MAX_VALUE)
										.addGap(126))))
							.addGap(90))
						.addGroup(gl_panel_8.createSequentialGroup()
							.addComponent(lblData_2, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_12, GroupLayout.PREFERRED_SIZE, 386, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel_8.createSequentialGroup()
							.addComponent(lblData_3, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_13, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel_8.createSequentialGroup()
							.addComponent(button_1)
							.addGap(18)
							.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(1154, Short.MAX_VALUE))))
		);
		gl_panel_8.setVerticalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel_8.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCliente)
						.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_8.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblValor)
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_8.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblData_1))
					.addGap(18)
					.addGroup(gl_panel_8.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblData_2)
						.addComponent(textField_12, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_8.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblData_3)
						.addComponent(textField_13, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
					.addGroup(gl_panel_8.createParallelGroup(Alignment.LEADING)
						.addComponent(button_1, Alignment.TRAILING)
						.addComponent(button_2, Alignment.TRAILING))
					.addContainerGap())
		);
		panel_8.setLayout(gl_panel_8);
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGap(0, 1421, Short.MAX_VALUE)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addComponent(button)
						.addComponent(panel_7, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1382, Short.MAX_VALUE)
						.addComponent(panel_8, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGap(0, 761, Short.MAX_VALUE)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(5)
					.addComponent(panel_8, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)
					.addGap(56)
					.addComponent(button)
					.addContainerGap(99, Short.MAX_VALUE))
		);
		panel_6.setLayout(gl_panel_6);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Relatório", null, panel_2, null);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Configuração", null, panel_3, null);

		tabbedPane.setMnemonicAt(0, KeyEvent.VK_C);

		this.getContentPane().setLayout(groupLayout);
		this.pack();

		JMenuBar menuBar = new JMenuBar();
		JMenu mnNewMenu = new JMenu("Cliente");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Cadastrar");
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				telaCliente = new Tela_Cliente();
				telaCliente.setVisible(true);

			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		setJMenuBar(menuBar);
		
		JMenu mnVenda = new JMenu("Venda");
		menuBar.add(mnVenda);
		
		JMenuItem menuItem = new JMenuItem("Cadastrar");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaVenda = new Tela_Venda();
				telaVenda.setVisible(true);
			}
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mnVenda.add(menuItem);
		
		tabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
		    public void stateChanged(javax.swing.event.ChangeEvent e) {
		        if (tabbedPane.getSelectedIndex() == 0) {
		        	PainelCliente.table.requestFocus();
		        }
		        
		        else if (tabbedPane.getSelectedIndex() == 1) {
		        	
		        }
		    }
		});
		
		
	}
	
	
	public static void carregarVendas()
	{
		AnnotationResolver resolver = new AnnotationResolver(Venda.class);
		ObjectTableModel<Venda> tableModel = new ObjectTableModel<Venda>(resolver,
				"codigo,cliente,valor,parcelas,data");
		VendaDao dao = new VendaDao();
		vendas =dao.listar();
		tableModel.setData(vendas);
		tableVendas.setModel(tableModel);
		tableModel.fireTableDataChanged();
		
		tableVendas.repaint();
	}
	
	public void selecionarVenda()
	{
		textField_9.setText(""+vendaSelecionada.getCliente());
		textField_7.setText(""+vendaSelecionada.getValor());
	}
	
	
	public static String formatarString(String texto, String mascara) throws ParseException {
        MaskFormatter mf = new MaskFormatter(mascara);
        mf.setValueContainsLiteralCharacters(false);
        return mf.valueToString(texto);
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
