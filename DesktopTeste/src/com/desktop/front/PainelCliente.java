package com.desktop.front;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import com.desktop.database.ClienteDao;
import com.desktop.model.Cliente;
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
import javax.swing.KeyStroke;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JFormattedTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.SwingConstants;

@Form(Cliente.class)
public class PainelCliente extends JPanel {
	
	@Bindable(field = "nome")
	private JTextField textField;
	
	@Bindable(field = "endereco")
	private JTextField textField_1;
	
	@Bindable(field = "bairro")
	private JTextField textField_2;
	
	@Bindable(field = "cpf")
	private JFormattedTextField formattedTextField;
	
	@Bindable(field = "telefone")
	JFormattedTextField formattedTextField_1;
	
	private Binder binder;
	
	private Tela_Cliente telaCliente;
	private Cliente clienteSelecionado;
	public static ArrayList<Cliente> clientes;
	public static JTable table;
	private static final int INATIVO = -1;
	private JTextField textField_3;

	/**
	 * Create the panel.
	 */
	public PainelCliente(final Container container) {
		
		JButton button = new JButton("CADASTRAR");
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				telaCliente = new Tela_Cliente();
				telaCliente.setVisible(true);

			}
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Lista de Clientes",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 1382, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1348, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 282, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		AnnotationResolver resolver = new AnnotationResolver(Cliente.class);
		ObjectTableModel<Cliente> tableModel = new ObjectTableModel<Cliente>(resolver,
				"codigo,nome,cpf,endereco,bairro,telefone");
		ClienteDao dao = new ClienteDao();
		clientes =dao.listar();
		tableModel.setData(clientes);
		table = new JTable(tableModel);
		table.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
				int indice = table.rowAtPoint(e.getPoint());
				if (indice != INATIVO) {
					clienteSelecionado = clientes.get(indice);
					binder.updateView(clienteSelecionado);
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
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Cliente", TitledBorder.LEADING,
						TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JLabel lblBairro = new JLabel("Endereço");
		
		JLabel label_1 = new JLabel("Nome");
		
		JLabel lblBairro_1 = new JLabel("Bairro");
		
		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField.setEditable(false);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF");
		MaskFormatter cpfMask=null;
		try {
			cpfMask = new MaskFormatter("###.###.###-##");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		formattedTextField = new JFormattedTextField(cpfMask);
		formattedTextField.setFont(new Font("Dialog", Font.PLAIN, 14));
		formattedTextField.setEditable(false);
		formattedTextField.setColumns(10);
		
		JLabel label_4 = new JLabel("Telefone");
		MaskFormatter foneMask=null;
		try {
			foneMask = new MaskFormatter("(##)#########");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		formattedTextField_1 = new JFormattedTextField(foneMask);
		formattedTextField_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		formattedTextField_1.setEditable(false);
		formattedTextField_1.setColumns(10);
		
		JButton button_1 = new JButton("ALTERAR");
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (clienteSelecionado != null) {
					telaCliente = new Tela_Cliente(clienteSelecionado);
					telaCliente.setVisible(true);

				} else {
					JOptionPane.showInternalMessageDialog(container, "Nenhum cliente selecionado", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		
		JButton button_2 = new JButton("EXCLUIR");
		
		button_2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {

				if (clienteSelecionado != null) {
					Object[] options = { "Confirmar", "Cancelar" };
					int resposta =JOptionPane.showOptionDialog(getParent(),
							"Deseja realmente excluir o cliente " + clienteSelecionado.getNome()+" ?", "Atenção",
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(resposta==0)
					{
						ClienteDao dao = new ClienteDao();
						clienteSelecionado.setStatus(INATIVO);
						dao.atualizar(clienteSelecionado);
						carregarClientes();
					}

				} else {
					JOptionPane.showInternalMessageDialog(container, "Nenhum cliente selecionado", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		
		JLabel lblCidade = new JLabel("Cidade");
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Dialog", Font.PLAIN, 14));
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		
		MaskFormatter dataMask=null;
		try {
			dataMask = new MaskFormatter("##/##/####");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JFormattedTextField formattedTextField_2 = new JFormattedTextField(dataMask);
		formattedTextField_2.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		formattedTextField_2.setEditable(false);
		formattedTextField_2.setColumns(10);
		
		JLabel lblDataNasc = new JLabel("Data Nasc");
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBairro)
								.addComponent(label_1)
								.addComponent(lblBairro_1, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 1163, Short.MAX_VALUE)
								.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 1163, Short.MAX_VALUE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 620, GroupLayout.PREFERRED_SIZE))
							.addGap(90))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(button_1)
							.addGap(18)
							.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(1142, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblCpf, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(formattedTextField, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(formattedTextField_1, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblDataNasc, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(formattedTextField_2, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblCidade, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 615, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(528, Short.MAX_VALUE))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBairro)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBairro_1))
					.addGap(20)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCidade)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(formattedTextField, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_4)
								.addComponent(formattedTextField_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCpf))
							.addGap(18)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(button_1, Alignment.TRAILING)
								.addComponent(button_2, Alignment.TRAILING)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(3)
							.addComponent(lblDataNasc))
						.addComponent(formattedTextField_2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
		);
		panel_1.setLayout(gl_panel_1);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 1370, GroupLayout.PREFERRED_SIZE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1382, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
					.addGap(12)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
					.addGap(56)
					.addComponent(button)
					.addGap(0))
		);
		setLayout(groupLayout);
		
		binder = new AnnotatedBinder(this);
		
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_INSERT) {
					telaCliente = new Tela_Cliente();
					telaCliente.setVisible(true);
				}
			}
		});

	}
	
	public static void carregarClientes()
	{
		AnnotationResolver resolver = new AnnotationResolver(Cliente.class);
		ObjectTableModel<Cliente> tableModel = new ObjectTableModel<Cliente>(resolver,
				"codigo,nome,cpf,endereco,bairro,telefone");
		ClienteDao dao = new ClienteDao();
		clientes =dao.listar();
		tableModel.setData(clientes);
		table.setModel(tableModel);
		tableModel.fireTableDataChanged();
		
		table.repaint();
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
