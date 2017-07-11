package com.desktop.front;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.MaskFormatter;

import com.desktop.database.CidadeEstadoDao;
import com.desktop.database.ClienteDao;
import com.desktop.database.ParcelaDao;
import com.desktop.front.Tela_Venda.IntFormatter;
import com.desktop.model.CidadeEstado;
import com.desktop.model.Cliente;
import com.desktop.utils.CarneUtilitario;
import com.towel.bean.Formatter;
import com.towel.bind.Binder;
import com.towel.bind.annotation.AnnotatedBinder;
import com.towel.bind.annotation.Bindable;
import com.towel.bind.annotation.Form;
import com.towel.el.annotation.AnnotationResolver;
import com.towel.swing.table.ObjectTableModel;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
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
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.SwingConstants;

@Form(Cliente.class)
public class PainelRelatorio extends JPanel {
	
	private Binder binder;
	
	private Tela_Cliente telaCliente;
	public static Cliente clienteSelecionado;
	public static ArrayList<Cliente> clientes;
	private static final int INATIVO = -1;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public PainelRelatorio(final Container container) {
		
		AnnotationResolver resolver = new AnnotationResolver(Cliente.class);
		ObjectTableModel<Cliente> tableModel = new ObjectTableModel<Cliente>(resolver,
				"codigo,nome,cpf,endereco,telefone");
		ClienteDao dao = new ClienteDao();
		clientes =dao.listar();
		tableModel.setData(clientes);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "RELAT\u00D3RIO COBRAN\u00C7A", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		MaskFormatter cpfMask=null;
		try {
			cpfMask = new MaskFormatter("###.###.###-##");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		MaskFormatter foneMask=null;
		try {
			foneMask = new MaskFormatter("(##)#########");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JButton btnGerarCobrana = new JButton("GERAR COBRAN\u00C7A");
		btnGerarCobrana.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		btnGerarCobrana.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				 try {
			            HashMap map = new HashMap();
			            ParcelaDao dao = new ParcelaDao();
			            File directory = new File("./DesktopTeste/relatorios/modelo1.jasper");
			            String arquivoJasper= directory.getAbsolutePath();
			            JRDataSource ds = new JRBeanCollectionDataSource( dao.listar() );
			            JasperPrint rel = JasperFillManager.fillReport(arquivoJasper, map,ds );
			            /*
			             * Cria um JRViewer para exibir o relat�rio.
			             * Um JRViewer � uma JPanel.
			             */
			            JRViewer viewer = new JRViewer( rel );
			     
			            // cria o JFrame
			            JFrame frameRelatorio = new JFrame( "COBRAN�A" );
			     
			            // adiciona o JRViewer no JFrame
			            frameRelatorio.getContentPane().add( viewer, BorderLayout.CENTER );
			     
			            // configura o tamanho padr�o do JFrame
			            frameRelatorio.setSize( 500, 500 );
			     
			            // maximiza o JFrame para ocupar a tela toda.
			            frameRelatorio.setExtendedState( JFrame.MAXIMIZED_BOTH );
			     
			            // configura a opera��o padr�o quando o JFrame for fechado.
			            frameRelatorio.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
			     
			            // exibe o JFrame
			            frameRelatorio.setVisible( true );
			     
			        } catch (JRException e) {
			            JOptionPane.showMessageDialog(null,e.getMessage());

			}
			}
		}
		);
		
		MaskFormatter dataMask=null;
		try {
			dataMask = new MaskFormatter("##/##/####");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		binder = new AnnotatedBinder(this);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(btnGerarCobrana, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
					.addGap(371))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(29)
					.addComponent(btnGerarCobrana)
					.addContainerGap(140, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "RELAT\u00D3RIO CARN\u00CAS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 528, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(1)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(23, Short.MAX_VALUE))
		);
		
		JButton btnGerarBoleto = new JButton("GERAR CARN\u00CA");
		btnGerarBoleto.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		btnGerarBoleto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField.getText()!=null)
				{
					CarneUtilitario.imprimirCarne(Integer.parseInt(textField.getText()));
				}
				

			}
		});
		
		btnGerarBoleto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
					if(TelaTabbed.tabbedPane.getSelectedIndex()<3)
						TelaTabbed.tabbedPane.setSelectedIndex(TelaTabbed.tabbedPane.getSelectedIndex() + 1);
				}

				if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
					if(TelaTabbed.tabbedPane.getSelectedIndex()>0)
						TelaTabbed.tabbedPane.setSelectedIndex(TelaTabbed.tabbedPane.getSelectedIndex() - 1);
				}
			}
		});
		
		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.BOLD, 16));
		textField.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnGerarBoleto, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(227, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(36, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGerarBoleto, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(44))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		
		if(clienteSelecionado==null && clientes!=null && clientes.size()>0)
		{
			clienteSelecionado=clientes.get(0);
			binder.updateView(clienteSelecionado);
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
	
	// IntFormatter sera usado para transformar o codigo da cidade em codigo da cidada junto com o nome da cidade.
		public static class CidadeFormatter implements Formatter {
			public Object format(Object obj) {
				CidadeEstado d = (CidadeEstado) obj;
				return d.getCidade()+"-"+d.getEstado();
			}

			public Object parse(Object obj) {
				int codigo = Integer.parseInt((String)obj);
				CidadeEstadoDao dao = new CidadeEstadoDao();
				return dao.consultarCodigo(codigo);
			}

			public String getName() {
				return "String";
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