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

	private Tela_Cliente telaCliente;
	private Tela_Venda telaVenda;
	private Tela_Parcelas telaParcelas;

	public static ArrayList<Cliente> clientes;
	public static ArrayList<Venda> vendas;
	
	public static JTabbedPane tabbedPane;

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
					window.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
		this.setTitle("Sistema de Pedidos");
		this.setBounds(100, 100, 781, 664);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		JPanel panel = new JPanel();
		tabbedPane.addTab("CLIENTE", null, panel, "Cadastro e Lista de Clientes");
		panel.setLayout(new BorderLayout(0, 0));

		PainelCliente panel_1 = new PainelCliente(getContentPane());
		panel.add(panel_1);

		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("PEDIDO", null, panel_6, "Cadastro e Lista de Pedidos");

		getContentPane().setLayout(new BorderLayout(0, 0));
		panel_6.setLayout(new BorderLayout(0, 0));

		PainelVenda panel_4 = new PainelVenda(getContentPane());
		panel_6.add(panel_4);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("RELATÓRIO", null, panel_2, null);
		PainelRelatorio panel_relatorio = new PainelRelatorio(getContentPane());
		panel_2.add(panel_relatorio);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("CIDADES", null, panel_3, null);
		panel_3.setLayout(new BorderLayout(0, 0));

		PainelCidades panelCidades = new PainelCidades(getContentPane());
		panel_3.add(panelCidades);

		tabbedPane.setMnemonicAt(0, KeyEvent.VK_C);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_P);
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_R);
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_I);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		this.pack();

		JMenuBar menuBar = new JMenuBar();
		JMenu mnNewMenu = new JMenu("Cliente");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Cadastrar");
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				telaCliente = new Tela_Cliente();
				telaCliente.setVisible(true);

			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenu mnVenda = new JMenu("Pedido");
		menuBar.add(mnVenda);

		JMenuItem menuItem = new JMenuItem("Cadastrar");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				telaVenda = new Tela_Venda();
				telaVenda.setVisible(true);

			}
		});
		mnVenda.add(menuItem);
		setJMenuBar(menuBar);
		
		JMenu mnParcelas = new JMenu("Parcelas");
		menuBar.add(mnParcelas);
		
		JMenuItem mntmQuitar = new JMenuItem("Quitar");
		mntmQuitar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		mntmQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				telaParcelas = new Tela_Parcelas();
				telaParcelas.setVisible(true);

			}
		});
		mnParcelas.add(mntmQuitar);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);
		
		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mntmSobre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0));
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				TelaContato telaCOntato = new TelaContato();
				telaCOntato.setVisible(true);

			}
		});
		mnAjuda.add(mntmSobre);
		
		tabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent e) {
				if (tabbedPane.getSelectedIndex() == 0) {
					PainelCliente.table.requestFocus();
					if (PainelCliente.table.getRowCount() > 0) {
						PainelCliente.table.addRowSelectionInterval(0, 0);
					}
				}

				else if (tabbedPane.getSelectedIndex() == 1) {
					PainelVenda.table.requestFocus();
					if (PainelVenda.table.getRowCount() > 0) {
						PainelVenda.table.addRowSelectionInterval(0, 0);
					}

				}

				else if (tabbedPane.getSelectedIndex() == 3) {
					PainelCidades.table.requestFocus();
					if (PainelCidades.table.getRowCount() > 0) {
						PainelCidades.table.addRowSelectionInterval(0, 0);
					}

				}
			}
		});
		PainelCliente.table.requestFocus();
		if (PainelCliente.table.getRowCount() > 0) {
			PainelCliente.table.addRowSelectionInterval(0, 0);
		}
		tabbedPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
					if(tabbedPane.getSelectedIndex()<3)
					tabbedPane.setSelectedIndex(tabbedPane.getSelectedIndex() + 1);
				}

				if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
					if(tabbedPane.getSelectedIndex()>0)
					tabbedPane.setSelectedIndex(tabbedPane.getSelectedIndex() - 1);
				}
			}
		});

	}
}
