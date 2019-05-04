package br.edu.ifcvideira.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import br.edu.ifcvideira.DAOs.*;
import br.edu.ifcvideira.view.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import br.edu.ifcvideira.imgs.*;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class RegistroVendasView extends JFrame {

	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfCodCliente;
	private JTextField tfCodProduto;
	private JTable table;
	private List<Object> venda = new ArrayList<Object>();
	VendaDao vDao = new VendaDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroVendasView frame = new RegistroVendasView();
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
	public RegistroVendasView() {
		setTitle("Drogalizando");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\MicaelPlayer\\Desktop\\IFC\\TrabAtualizado\\Trab\\src\\br\\edu\\ifcvideira\\imgs\\Icone.jpg"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		mnMenu.setFont(new Font("Open Sans", Font.PLAIN, 12));
		menuBar.add(mnMenu);
		
		JMenuItem mntmPrincipal = new JMenuItem("Principal");
		mntmPrincipal.setFont(new Font("Open Sans", Font.PLAIN, 12));
		mntmPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new DrogalizandoView().setVisible(true);
			}
		});
		mnMenu.add(mntmPrincipal);
		
		JMenu mnCadastro = new JMenu("Cadastro");
		mnCadastro.setFont(new Font("Open Sans", Font.PLAIN, 12));
		menuBar.add(mnCadastro);
		
		JMenuItem mntmCliente = new JMenuItem("Cliente");
		mntmCliente.setFont(new Font("Open Sans", Font.PLAIN, 12));
		mntmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new CadastroCliente().setVisible(true);
			}
		});
		mnCadastro.add(mntmCliente);
		
		JMenuItem mntmProduto = new JMenuItem("Produto");
		mntmProduto.setFont(new Font("Open Sans", Font.PLAIN, 12));
		mntmProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new CadastroProdutoView().setVisible(true);
			}
		});
		mnCadastro.add(mntmProduto);
		
		JMenu mnVenda = new JMenu("Venda");
		mnVenda.setFont(new Font("Open Sans", Font.PLAIN, 12));
		menuBar.add(mnVenda);
		
		JMenuItem mntmVenda = new JMenuItem("Venda");
		mntmVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new VendaView().setVisible(true);
			}
		});
		mntmVenda.setFont(new Font("Open Sans", Font.PLAIN, 12));
		mnVenda.add(mntmVenda);
		
		JMenuItem mntmRegistros = new JMenuItem("Registros");
		mntmRegistros.setFont(new Font("Open Sans", Font.PLAIN, 12));
		mnVenda.add(mntmRegistros);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegistrosDeVendas = new JLabel("Registros de Vendas");
		lblRegistrosDeVendas.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblRegistrosDeVendas.setBounds(327, 11, 129, 23);
		contentPane.add(lblRegistrosDeVendas);
		
		JLabel lblId = new JLabel("Id");
		lblId.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblId.setBounds(51, 56, 77, 14);
		contentPane.add(lblId);
		
		tfId = new JTextField();
		tfId.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfId.setBounds(138, 53, 86, 20);
		tfId.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				
				//atualizar a tabela apenas com valores correspondentes aos digitados no campo de busca por codigo
				TableRowSorter<TableModel> filtro = null;  
				DefaultTableModel model = (DefaultTableModel) table.getModel();  
				filtro = new TableRowSorter<TableModel>(model);  
				table.setRowSorter(filtro);
				
				if (tfId.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {  
					filtro.setRowFilter(RowFilter.regexFilter("(?i)" +tfId.getText(), 2));  
				}  
			}
		});
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		JLabel lblCodcliente = new JLabel("CodCliente");
		lblCodcliente.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblCodcliente.setBounds(51, 87, 77, 14);
		contentPane.add(lblCodcliente);
		
		tfCodCliente = new JTextField();
		tfCodCliente.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfCodCliente.setColumns(10);
		tfCodCliente.setBounds(138, 84, 86, 20);
		tfCodCliente.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				
				//atualizar a tabela apenas com valores correspondentes aos digitados no campo de busca por codigo
				TableRowSorter<TableModel> filtro = null;  
				DefaultTableModel model = (DefaultTableModel) table.getModel();  
				filtro = new TableRowSorter<TableModel>(model);  
				table.setRowSorter(filtro);
				
				if (tfCodCliente.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {  
					filtro.setRowFilter(RowFilter.regexFilter("(?i)" +tfCodCliente.getText(), 0));  
				}  
			}
		});
		contentPane.add(tfCodCliente);
		
		JLabel lblCodproduto = new JLabel("CodProduto");
		lblCodproduto.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblCodproduto.setBounds(51, 118, 77, 14);
		contentPane.add(lblCodproduto);
		
		tfCodProduto = new JTextField();
		tfCodProduto.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfCodProduto.setColumns(10);
		tfCodProduto.setBounds(138, 115, 86, 20);
		tfCodProduto.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				
				//atualizar a tabela apenas com valores correspondentes aos digitados no campo de busca por codigo
				TableRowSorter<TableModel> filtro = null;  
				DefaultTableModel model = (DefaultTableModel) table.getModel();  
				filtro = new TableRowSorter<TableModel>(model);  
				table.setRowSorter(filtro);
				
				if (tfCodProduto.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {  
					filtro.setRowFilter(RowFilter.regexFilter("(?i)" +tfCodProduto.getText(), 1));  
				}  
			}
		});
		contentPane.add(tfCodProduto);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\MicaelPlayer\\Desktop\\IFC\\TrabAtualizado\\Trab\\src\\br\\edu\\ifcvideira\\imgs\\Refresh-Icon.jpg"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarTabela();
			}
		});
		btnNewButton.setBounds(234, 76, 25, 25);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 168, 680, 361);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CodCliente","CodProduto", "IdVenda", "Quantidade", "Valor", "Data"
			}
		));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\MicaelPlayer\\Desktop\\IFC\\TrabAtualizado\\Trab\\src\\br\\edu\\ifcvideira\\imgs\\Background1.jpg"));
		lblNewLabel.setBounds(0, 0, 784, 540);
		contentPane.add(lblNewLabel);
	}
	
	public void atualizarTabela() {
		try {
			venda = vDao.buscarTodos();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
		for (int x=0; x!=venda.size(); x++)
			{
				model.addRow((Object[]) venda.get(x));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
