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
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import com.desktop.database.Base_Dao;
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
import java.awt.event.WindowEvent;
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

	@Bindable(field = "pedido", formatter = IntFormatter.class)
	private JFormattedTextField textField_3;
	@Bindable(field = "data", formatter = DateFormatter.class)
	private JFormattedTextField textField_4;
	
	@Bindable(field = "parcelas", formatter = IntFormatter.class)
	private JFormattedTextField textField_5;
	
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
		
		NumberFormat intFormat = NumberFormat.getIntegerInstance();
		intFormat.setGroupingUsed(false);
		NumberFormatter numberFormatter = new NumberFormatter(intFormat);
		numberFormatter.setValueClass(Integer.class); //optional, ensures you will always get a long value
		numberFormatter.setAllowsInvalid(false); //this is the key!!
		numberFormatter.setMinimum(0);
		
		tipoTela=SALVAR;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 626, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Venda", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));

		lblValor = new JLabel("Valor");
		lblValor.setBounds(15, 104, 31, 15);
		lblValor.setFont(new Font("Tahoma", Font.BOLD, 12));

		lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(15, 65, 42, 15);
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 12));

		textField = new JTextField();
		textField.setBounds(102, 99, 423, 25);
		textField.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(102, 60, 423, 25);
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_2.setColumns(10);

		lblEntrada = new JLabel("Pedido");
		lblEntrada.setBounds(15, 26, 56, 15);
		lblEntrada.setFont(new Font("Tahoma", Font.BOLD, 12));

		textField_3 = new JFormattedTextField(numberFormatter);
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

		lblParcelas = new JLabel("Parcelas");
		lblParcelas.setBounds(15, 143, 56, 15);
		lblParcelas.setFont(new Font("Tahoma", Font.BOLD, 12));

		
		
		textField_5 = new JFormattedTextField(numberFormatter);
		textField_5.setBounds(102, 138, 423, 25);
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
			Object[] options = { "CONFIRMAR", "CANCELAR" };
			int resposta =JOptionPane.showOptionDialog(null,
					"Confirma o cadastro da venda " + venda.getValor()+" ?", "Atenção",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if(resposta==0)
			{
				if(tipoTela.equals(SALVAR))
				{
					venda =(Venda) dao.inserir(venda);
					gerarParcelas(venda);
				}
				else
				{
					venda.setCodigo(codigoVenda);
					venda =(Venda) dao.atualizar(venda);
					atualizarParcelas(venda);
				}
				
				PainelVenda.carregarVendas();
				contentPane.setVisible(false);
				dispose();
			}
			
		}
		
		
	}
	
	private void gerarParcelas(Venda venda)
	{
		ParcelaDao dao = new ParcelaDao();
		Parcela parcela=null;
		Calendar calendar = Calendar.getInstance();
		for(int i=0;i<venda.getParcelas();i++)
		{
			parcela = new Parcela();
			parcela.setAlias(venda.getPedido()+"/"+i+1);
			parcela.setValor(venda.getValor()/venda.getParcelas());
			parcela.setVenda(venda.getCodigo());
			calendar.setTime(venda.getData());
			calendar.add(Calendar.MONTH, 1);
			parcela.setVencimento(calendar.getTime());
			dao.inserir(parcela);
		}
		
	}
	
	private void atualizarParcelas(Venda venda)
	{
		ParcelaDao dao = new ParcelaDao();
		Parcela parcela=null;
		Calendar calendar = Calendar.getInstance();
		for(int i=0;i<venda.getParcelas();i++)
		{
			parcela = new Parcela();
			parcela.setAlias(venda.getPedido()+"/"+i+1);
			parcela.setValor(venda.getValor()/venda.getParcelas());
			parcela.setVenda(venda.getCodigo());
			calendar.setTime(venda.getData());
			calendar.add(Calendar.MONTH, 1);
			parcela.setVencimento(calendar.getTime());
			dao.atualizar(parcela);
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
		
}
