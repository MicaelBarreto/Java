package br.edu.ifcvideira.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import br.edu.ifcvideira.DAOs.*;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import br.edu.ifcvideira.Trabalho.*;
import java.sql.Timestamp;
import br.edu.ifcvideira.imgs.*;
import java.awt.Toolkit;

public class VendaView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTextField tfNomeFantasia;
	private JTextField tfCNPJ;
	private JTextField tfEndereco;
	private JTextField tfEmail;
	private JTextField tfValorT;
	private JTextField tfValorU;
	private JTextField tfNome;
	private JTextField textField_8;
	private JTextField tfBCodC;
	private JTextField tfBCNPJ;
	private JTextField tfBCodP;
	private JTextField tfBRegistro;

	Produto pr = new Produto();
	CadastroCliente cc = new CadastroCliente();
	CadastroProdutoView pp = new CadastroProdutoView();
	VendaDao vDao = new VendaDao();
	Venda vv = new Venda();
	ClienteDao clDao = new ClienteDao();
	ProdutoDao pDao = new ProdutoDao();
	private List<Object> cliente = new ArrayList<Object>();
	private List<Object> produto = new ArrayList<Object>();
	JSlider sliderQnt = new JSlider();
	private JTextField tfCodCliente;
	private JTextField tfCodProduto;
	long time = System.currentTimeMillis();
	Timestamp timestamp = new Timestamp(time);
	int aux;
	int aux1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VendaView frame = new VendaView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VendaView() {
		setTitle("Drogalizando");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\MicaelPlayer\\Desktop\\IFC\\TrabAtualizado\\Trab\\src\\br\\edu\\ifcvideira\\imgs\\Icone.jpg"));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 92, 340, 200);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				setCamposFromTabelaCliente();
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Cod", "CNPJ", "Nome", "Endereço", "Telefone", "Data", "Email", "Razão Social", "Inscrição Estadual"
			}
		));
		
		table.setFont(new Font("Open Sans", Font.PLAIN, 12));
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(420, 92, 354, 200);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				setCamposFromTabelaProduto();
			}
		});
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Cod","Nome", "Valor", "Quantidade", "Registro"
			}
		));
		table_1.setFont(new Font("Open Sans", Font.PLAIN, 12));
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblNewLabel = new JLabel("Nome Fantasia");
		lblNewLabel.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 336, 84, 21);
		contentPane.add(lblNewLabel);
		
		tfNomeFantasia = new JTextField();
		tfNomeFantasia.setEditable(false);
		tfNomeFantasia.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfNomeFantasia.setBounds(104, 336, 246, 20);
		contentPane.add(tfNomeFantasia);
		tfNomeFantasia.setColumns(10);
		
		tfCNPJ = new JTextField();
		tfCNPJ.setEditable(false);
		tfCNPJ.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfCNPJ.setColumns(10);
		tfCNPJ.setBounds(104, 368, 246, 20);
		contentPane.add(tfCNPJ);
		
		JLabel lblCnpj = new JLabel("CNPJ");
		lblCnpj.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblCnpj.setBounds(10, 368, 84, 21);
		contentPane.add(lblCnpj);
		
		tfEndereco = new JTextField();
		tfEndereco.setEditable(false);
		tfEndereco.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfEndereco.setColumns(10);
		tfEndereco.setBounds(104, 399, 246, 20);
		contentPane.add(tfEndereco);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblEndereo.setBounds(10, 399, 84, 21);
		contentPane.add(lblEndereo);
		
		tfEmail = new JTextField();
		tfEmail.setEditable(false);
		tfEmail.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfEmail.setColumns(10);
		tfEmail.setBounds(104, 430, 246, 20);
		contentPane.add(tfEmail);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblEmail.setBounds(10, 430, 84, 21);
		contentPane.add(lblEmail);
		
		tfValorT = new JTextField();
		tfValorT.setEditable(false);
		tfValorT.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfValorT.setColumns(10);
		tfValorT.setBounds(528, 429, 246, 20);
		contentPane.add(tfValorT);
		
		JLabel lblValorTotal = new JLabel("Valor Total");
		lblValorTotal.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblValorTotal.setBounds(420, 429, 98, 21);
		contentPane.add(lblValorTotal);
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblQuantidade.setBounds(420, 398, 98, 21);
		contentPane.add(lblQuantidade);
		
		tfValorU = new JTextField();
		tfValorU.setEditable(false);
		tfValorU.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfValorU.setColumns(10);
		tfValorU.setBounds(528, 367, 246, 20);
		contentPane.add(tfValorU);
		
		JLabel lblValorUnitrio = new JLabel("Valor Unit\u00E1rio");
		lblValorUnitrio.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblValorUnitrio.setBounds(420, 367, 98, 21);
		contentPane.add(lblValorUnitrio);
		
		JLabel Nome = new JLabel("Nome");
		Nome.setFont(new Font("Open Sans", Font.PLAIN, 12));
		Nome.setBounds(420, 335, 98, 21);
		contentPane.add(Nome);
		
		tfNome = new JTextField();
		tfNome.setEditable(false);
		tfNome.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfNome.setColumns(10);
		tfNome.setBounds(528, 335, 246, 20);
		contentPane.add(tfNome);
		
		textField_8 = new JTextField();
		textField_8.setEditable(false);
		textField_8.setFont(new Font("Open Sans", Font.PLAIN, 12));
		textField_8.setColumns(10);
		textField_8.setBounds(709, 399, 65, 20);
		contentPane.add(textField_8);
		
		JLabel lblCod = new JLabel("Cod");
		lblCod.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblCod.setBounds(10, 28, 84, 21);
		contentPane.add(lblCod);
		
		tfBCodC = new JTextField();
		tfBCodC.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfBCodC.setColumns(10);
		tfBCodC.setBounds(61, 28, 289, 20);
		tfBCodC.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				
				//atualizar a tabela apenas com valores correspondentes aos digitados no campo de busca por codigo
				TableRowSorter<TableModel> filtro = null;  
				DefaultTableModel model = (DefaultTableModel) table.getModel();  
				filtro = new TableRowSorter<TableModel>(model);  
				table.setRowSorter(filtro);
				
				if (tfBCodC.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {  
					filtro.setRowFilter(RowFilter.regexFilter("(?i)" +tfBCodC.getText(), 0));  
				}  
			}
		});
		contentPane.add(tfBCodC);
		
		tfBCNPJ = new JTextField();
		tfBCNPJ.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfBCNPJ.setColumns(10);
		tfBCNPJ.setBounds(61, 60, 289, 20);
		tfBCNPJ.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				
				//atualizar a tabela apenas com valores correspondentes aos digitados no campo de busca por codigo
				TableRowSorter<TableModel> filtro = null;  
				DefaultTableModel model = (DefaultTableModel) table.getModel();  
				filtro = new TableRowSorter<TableModel>(model);  
				table.setRowSorter(filtro);
				
				if (tfBCNPJ.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {  
					filtro.setRowFilter(RowFilter.regexFilter("(?i)" +tfBCNPJ.getText(), 1));  
				}  
			}
		});
		contentPane.add(tfBCNPJ);
		
		JLabel label_1 = new JLabel("CNPJ");
		label_1.setFont(new Font("Open Sans", Font.PLAIN, 12));
		label_1.setBounds(10, 60, 84, 21);
		contentPane.add(label_1);
		
		JLabel lblCod_1 = new JLabel("Cod");
		lblCod_1.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblCod_1.setBounds(420, 28, 84, 21);
		contentPane.add(lblCod_1);
		
		tfBCodP = new JTextField();
		tfBCodP.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfBCodP.setColumns(10);
		tfBCodP.setBounds(485, 28, 289, 20);
		tfBCodP.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				
				//atualizar a tabela apenas com valores correspondentes aos digitados no campo de busca por codigo
				TableRowSorter<TableModel> filtro = null;  
				DefaultTableModel model = (DefaultTableModel) table_1.getModel();  
				filtro = new TableRowSorter<TableModel>(model);  
				table_1.setRowSorter(filtro);
				
				if (tfBCodP.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {  
					filtro.setRowFilter(RowFilter.regexFilter(tfBCodP.getText(), 0));  
				}  
			}
		});
		contentPane.add(tfBCodP);
		
		tfBRegistro = new JTextField();
		tfBRegistro.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfBRegistro.setColumns(10);
		tfBRegistro.setBounds(485, 60, 289, 20);
		tfBRegistro.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				
				//atualizar a tabela apenas com valores correspondentes aos digitados no campo de busca por codigo
				TableRowSorter<TableModel> filtro = null;  
				DefaultTableModel model = (DefaultTableModel) table_1.getModel();  
				filtro = new TableRowSorter<TableModel>(model);  
				table_1.setRowSorter(filtro);
				
				if (tfBRegistro.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {  
					filtro.setRowFilter(RowFilter.regexFilter(tfBRegistro.getText(), 4));  
				}  
			}
		});
		contentPane.add(tfBRegistro);
		
		JLabel lblRegistro = new JLabel("Registro");
		lblRegistro.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblRegistro.setBounds(420, 60, 55, 21);
		contentPane.add(lblRegistro);
		
		JButton btnNewButton = new JButton("",new ImageIcon("C:\\Users\\MicaelPlayer\\Desktop\\IFC\\TrabAtualizado\\Trab\\src\\br\\edu\\ifcvideira\\imgs\\Refresh-Icon.jpg"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarTabela();
				atualizarTabelaProduto();
			}
		});
		btnNewButton.setBounds(372, 176, 25, 25);
		contentPane.add(btnNewButton);
		
		JButton btnVender = new JButton("Vender");
		btnVender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					vv.setCodCliente(Integer.parseInt(tfCodCliente.getText()));
					vv.setCodProduto(Integer.parseInt(tfCodProduto.getText()));
					vv.setQnt(Integer.parseInt(textField_8.getText()));
					vv.setValor(Double.parseDouble(tfValorT.getText()));
					vv.setData(timestamp);
					vv.setId(vDao.RetornarProximoCodigoCliente());
					
					vDao.CadastrarVenda(vv);
					
					//Alterar Quantidade no Produto
					
					pr.setCodProduto(Integer.parseInt(tfCodProduto.getText()));
					pr.setQnt(CalculaQnt());
					
					pDao.AlterarQntProduto(pr);
					
				} catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				atualizarTabela();
				atualizarTabelaProduto();
				limpar();
				
				
			}
		});
		btnVender.setFont(new Font("Open Sans", Font.PLAIN, 12));
		btnVender.setBounds(335, 496, 109, 33);
		contentPane.add(btnVender);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(39, 304, 699, 21);
		contentPane.add(separator);
		
		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblClientes.setBounds(166, 3, 55, 21);
		contentPane.add(lblClientes);
		
		JLabel lblProdutos = new JLabel("Produtos");
		lblProdutos.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblProdutos.setBounds(586, 3, 55, 21);
		contentPane.add(lblProdutos);
		
		sliderQnt.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent arg0) {
				textField_8.setText(""+sliderQnt.getValue());
				tfValorT.setText(""+CalculaValor());
			}
		});
		
		sliderQnt.setFont(new Font("Open Sans", Font.PLAIN, 12));
		sliderQnt.setBounds(528, 399, 171, 26);
		contentPane.add(sliderQnt);
		
		tfCodCliente = new JTextField();
		tfCodCliente.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfCodCliente.setEditable(false);
		tfCodCliente.setColumns(10);
		tfCodCliente.setBounds(104, 461, 40, 20);
		contentPane.add(tfCodCliente);
		
		JLabel lblCod_2 = new JLabel("Cod");
		lblCod_2.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblCod_2.setBounds(10, 461, 84, 21);
		contentPane.add(lblCod_2);
		
		tfCodProduto = new JTextField();
		tfCodProduto.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfCodProduto.setEditable(false);
		tfCodProduto.setColumns(10);
		tfCodProduto.setBounds(528, 460, 40, 20);
		contentPane.add(tfCodProduto);
		
		JLabel lblCod_3 = new JLabel("Cod");
		lblCod_3.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblCod_3.setBounds(420, 460, 84, 21);
		contentPane.add(lblCod_3);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\MicaelPlayer\\Desktop\\IFC\\TrabAtualizado\\Trab\\src\\br\\edu\\ifcvideira\\imgs\\Background1.jpg"));
		lblNewLabel_1.setBounds(0, 0, 784, 540);
		contentPane.add(lblNewLabel_1);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		mnMenu.setFont(new Font("Open Sans", Font.PLAIN, 12));
		menuBar.add(mnMenu);
		
		JMenuItem mntmPrincipal = new JMenuItem("Principal");
		mntmPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new DrogalizandoView().setVisible(true);
			}
		});
		mntmPrincipal.setFont(new Font("Open Sans", Font.PLAIN, 12));
		mnMenu.add(mntmPrincipal);
		
		JMenu mnCadastro = new JMenu("Cadastro");
		mnCadastro.setFont(new Font("Open Sans", Font.PLAIN, 12));
		menuBar.add(mnCadastro);
		
		JMenuItem mntmCliente = new JMenuItem("Cliente");
		mntmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new CadastroCliente().setVisible(true);
			}
		});
		mntmCliente.setFont(new Font("Open Sans", Font.PLAIN, 12));
		mnCadastro.add(mntmCliente);
		
		JMenuItem mntmProduto = new JMenuItem("Produto");
		mntmProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new CadastroProdutoView().setVisible(true);
			}
		});
		mntmProduto.setFont(new Font("Open Sans", Font.PLAIN, 12));
		mnCadastro.add(mntmProduto);
		
		JMenu mnVenda = new JMenu("Venda");
		mnVenda.setFont(new Font("Open Sans", Font.PLAIN, 12));
		menuBar.add(mnVenda);
		
		JMenuItem mntmVenda = new JMenuItem("Venda");
		mntmVenda.setFont(new Font("Open Sans", Font.PLAIN, 12));
		mnVenda.add(mntmVenda);
		
		JMenuItem mntmRegistros = new JMenuItem("Registros");
		mntmRegistros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new RegistroVendasView().setVisible(true);
			}
		});
		mntmRegistros.setFont(new Font("Open Sans", Font.PLAIN, 12));
		mnVenda.add(mntmRegistros);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
	}
	
	public void atualizarTabela() {
		try {
			cliente = clDao.buscarTodos();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
		for (int x=0; x!=cliente.size(); x++)
			{
				model.addRow((Object[]) cliente.get(x));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void atualizarTabelaProduto() {
		try {
			produto = pDao.buscarTodos();
			DefaultTableModel model = (DefaultTableModel) table_1.getModel();
			model.setNumRows(0);
		for (int x=0; x!=produto.size(); x++)
			{
				model.addRow((Object[]) produto.get(x));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void setCamposFromTabelaCliente() {
		tfCodCliente.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
		tfCNPJ.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
		tfNomeFantasia.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 2)));
		tfEndereco.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 3)));
		tfEmail.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 6)));
	}

	public void limpar() {
		tfNomeFantasia.setText(null);
		tfCNPJ.setText(null);
		tfEmail.setText(null);
		tfEndereco.setText(null);
		tfNome.setText(null);
		tfValorU.setText(null);
		textField_8.setText(null);
		tfCodProduto.setText(null);
		tfCodCliente.setText(null);
	}
	
	public void setCamposFromTabelaProduto() {
		tfCodProduto.setText(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 0)));
		tfNome.setText(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 1)));
		tfValorU.setText(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 2)));
		sliderQnt.setMaximum(Integer.parseInt((String)table_1.getValueAt(table_1.getSelectedRow(), 3)));
		aux = sliderQnt.getMaximum();
	}
	
	public double CalculaValor(){
		double valorT = 0;
		double valorU;
		valorU = Double.parseDouble(tfValorU.getText());
		valorT = valorU*sliderQnt.getValue();
		
		return valorT;
	}
	
	public int CalculaQnt(){
		return aux - vv.getQnt();
	}
}
