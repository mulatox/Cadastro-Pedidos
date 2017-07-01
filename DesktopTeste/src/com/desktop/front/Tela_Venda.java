package com.desktop.front;

import java.awt.AWTKeyStroke;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;

import com.desktop.database.ClienteDao;
import com.desktop.database.VendaDao;
import com.desktop.front.TelaTabbed.IntFormatter;
import com.desktop.model.Cliente;
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
import java.awt.event.WindowEvent;
import java.util.HashSet;

import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Form(Venda.class)
public class Tela_Venda extends JFrame {

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

	@Bindable(field = "cliente", formatter = IntFormatter.class)
	private JTextField textField_2;

	private JTextField textField_3;

	private JTextField textField_4;
	
	@Bindable(field = "parcelas", formatter = IntFormatter.class)
	private JTextField textField_5;
	
	private Binder binder;
	
	private int codigoVenda;
	
	
	//Indica se esta no modo de salvar ou de alterar cliente
	public static String tipoTela= "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_Venda frame = new Tela_Venda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Tela_Venda(Venda venda) {
		this();
		binder.updateView(venda);
		codigoVenda=venda.getCodigo();
		tipoTela=ALTERAR;
		
	}
	
	/**
	 * Create the frame.
	 */
	public Tela_Venda() {
		
		tipoTela=SALVAR;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 626, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Venda", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));

		lblValor = new JLabel("Valor");

		lblCliente = new JLabel("Cliente");

		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField_2.setColumns(10);

		lblEntrada = new JLabel("Entrada");

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField_3.setColumns(10);

		lblData = new JLabel("Data");

		textField_4 = new JTextField();
		textField_4.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField_4.setColumns(10);

		lblParcelas = new JLabel("Parcelas");

		textField_5 = new JTextField();
		textField_5.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField_5.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblParcelas, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 413, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblEntrada, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblValor)
										.addComponent(lblCliente))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(textField_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
										.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
											.addComponent(textField, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
											.addGap(126))
										.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE))))
							.addGap(84))
						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
							.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCliente)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblValor)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEntrada))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(lblParcelas)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblData)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(171))
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
				salvarVenda();
				
			}
		});
		btnSalvar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					salvarVenda();
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
	
	private void salvarVenda ()
	{
		Venda venda = new Venda();
		binder.updateModel(venda);
		if(validado(venda))
		{
			VendaDao dao = new VendaDao();
			Object[] options = { "Confirmar", "Cancelar" };
			int resposta =JOptionPane.showOptionDialog(null,
					"Confirma o cadastro da venda " + venda.getValor()+" ?", "Atenção",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if(resposta==0)
			{
				if(tipoTela.equals(SALVAR))
				{
					dao.inserir(venda);
				}
				else
				{
					venda.setCodigo(codigoVenda);
					dao.atualizar(venda);
				}
				TelaTabbed.carregarVendas();
				contentPane.setVisible(false);
				dispose();
			}
			
		}
		
		
	}
	
	public boolean validado(Venda venda)
	{
		if(venda.getCliente()==0)
		{
			JOptionPane.showMessageDialog(this,"Campo Cliente obrigatório","Campos Obrigatórios",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		if(venda.getValor()==0)
		{
			JOptionPane.showMessageDialog(this,"Campo Valor obrigatório","Campos Obrigatórios",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		if(venda.getParcelas()==0)
		{
			JOptionPane.showMessageDialog(this,"Campo Parcelas obrigatório","Campos Obrigatórios",JOptionPane.WARNING_MESSAGE);
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
		
		// IntFormatter sera usado para transformar a String em numero.
				public static class DoubleFormatter implements Formatter {
				    public Object format(Object obj) {
				        Double d = (Double) obj;
				        return d.toString();
				    }
				    public Object parse(Object obj) {
				        return Double.valueOf(Double.parseDouble((String) obj));
				    }
				    public String getName() {
				        return "double";
				    }
				}
}
