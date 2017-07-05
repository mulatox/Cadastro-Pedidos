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


	private Tela_Cliente telaCliente;
	
	private Tela_Venda telaVenda;

	
	private Venda vendaSelecionada;
	
	public static ArrayList<Cliente> clientes;
	public static ArrayList<Venda> vendas;

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
		this.setTitle("Sistema de Vendas");
		this.setBounds(100, 100, 781, 664);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Cliente", null, panel, "Cadastro e Lista de Clientes");
		panel.setLayout(new BorderLayout(0, 0));
		
		PainelCliente panel_1 = new PainelCliente(getContentPane());
		panel.add(panel_1);
		
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Venda", null, panel_6, null);
		
		
		carregarVendas();
		getContentPane().setLayout(new BorderLayout(0, 0));
		panel_6.setLayout(new BorderLayout(0, 0));
		
		PainelVenda panel_4 = new PainelVenda(getContentPane());
		panel_6.add(panel_4);
		

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Relatório", null, panel_2, null);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Configuração", null, panel_3, null);

		tabbedPane.setMnemonicAt(0, KeyEvent.VK_C);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
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
		tableModel.fireTableDataChanged();
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
