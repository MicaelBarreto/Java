package br.edu.ifcvideira.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

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

import br.edu.ifcvideira.DAOs.ProdutoDao;
import br.edu.ifcvideira.Trabalho.Produto;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import br.edu.ifcvideira.imgs.*;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class CadastroProdutoView extends JFrame {

	private JPanel contentPane;
	private JTextField tfProduto;
	private JTextField tfRegistro;
	private JTextField tfValor;
	private JTextField tfQnt;
	private JTextField tfBNome;
	private JTextField tfBRegistro;
	private JTable table;
	private List<Object> produto = new ArrayList<Object>();
	ProdutoDao pDao = new ProdutoDao();
	Produto cc = new Produto();
	private JTextField tfCod;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroProdutoView frame = new CadastroProdutoView();
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
	public CadastroProdutoView() {
		setTitle("Drogalizando");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\MicaelPlayer\\Desktop\\IFC\\TrabAtualizado\\Trab\\src\\br\\edu\\ifcvideira\\imgs\\Icone.jpg"));
		tfCod = new JTextField();
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadastroDeProduto = new JLabel("Cadastro de Produto");
		lblCadastroDeProduto.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblCadastroDeProduto.setBounds(315, 11, 294, 14);
		contentPane.add(lblCadastroDeProduto);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblNome.setBounds(25, 48, 65, 14);
		contentPane.add(lblNome);
		
		JLabel lblRegistroMs = new JLabel("Registro M.S.");
		lblRegistroMs.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblRegistroMs.setBounds(25, 72, 82, 17);
		contentPane.add(lblRegistroMs);
		
		JLabel lblValorUnitrio = new JLabel("Valor Unit\u00E1rio");
		lblValorUnitrio.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblValorUnitrio.setBounds(25, 97, 82, 14);
		contentPane.add(lblValorUnitrio);
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblQuantidade.setBounds(25, 122, 65, 14);
		contentPane.add(lblQuantidade);
		
		tfProduto = new JTextField();
		tfProduto.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfProduto.setBounds(117, 45, 340, 20);
		contentPane.add(tfProduto);
		tfProduto.setColumns(10);
		
		tfRegistro = new JTextField();
		tfRegistro.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfRegistro.setColumns(10);
		tfRegistro.setBounds(117, 69, 340, 20);
		contentPane.add(tfRegistro);
		
		tfValor = new JTextField();
		tfValor.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfValor.setColumns(10);
		tfValor.setBounds(117, 94, 340, 20);
		contentPane.add(tfValor);
		
		tfQnt = new JTextField();
		tfQnt.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfQnt.setColumns(10);
		tfQnt.setBounds(117, 119, 340, 20);
		contentPane.add(tfQnt);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					cc.setNomeProduto(tfProduto.getText());
					cc.setQnt(Integer.parseInt(tfQnt.getText()));
					cc.setValor(Double.parseDouble(tfValor.getText()));
					cc.setRegistroMS(tfRegistro.getText());
					cc.setCodProduto(pDao.RetornarProximoCodigoProduto());
					
					pDao.CadastrarProduto(cc);
					
				} catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				atualizarTabela();
			}
		});
		btnCadastrar.setFont(new Font("Open Sans", Font.PLAIN, 12));
		btnCadastrar.setBounds(494, 44, 227, 23);
		contentPane.add(btnCadastrar);
		
		JButton bntAlterar = new JButton("Alterar");
		bntAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1){
					try {
						  
						//atribuição dos valores dos campos para o objeto cliente
						//cc.setCodProduto(Integer.parseInt(tfCod.getText()));
						cc.setNomeProduto(tfProduto.getText());
						cc.setQnt(Integer.parseInt(tfQnt.getText()));
						cc.setValor(Double.parseDouble(tfValor.getText()));
						cc.setRegistroMS(tfRegistro.getText());
						cc.setCodProduto(Integer.parseInt(tfCod.getText()));
						// chamada do método de alteração na classe Dao
						pDao.AlterarProduto(cc);
						
		
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					atualizarTabela();
					limpar();
				}
				
				else{
					JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada");
				}
				atualizarTabela();
			}
		});
		bntAlterar.setFont(new Font("Open Sans", Font.PLAIN, 12));
		bntAlterar.setBounds(494, 69, 227, 23);
		contentPane.add(bntAlterar);
		
		JButton bntExcluir = new JButton("Excluir");
		bntExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1){
					Object[] options3 = {"Excluir", "Cancelar"};
					if(JOptionPane.showOptionDialog(null, "Tem certeza que deseja excluir o registro:\n>   " 
							+ table.getValueAt(table.getSelectedRow(), 0) + "   -   "
							+ table.getValueAt(table.getSelectedRow(), 1), null,
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options3, options3[0]) == 0){
						try {
						
							//atribuição do valor do campo código para o objeto cliente
							cc.setCodProduto(Integer.parseInt(tfCod.getText()));
							
							// chamada do método de exclusão na classe Dao passando como parâmetro o código do cliente para ser excluído
							pDao.deletarCliente(cc);
							
						
							atualizarTabela();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada");
				}
				atualizarTabela();
				limpar();
			}
		});
		bntExcluir.setFont(new Font("Open Sans", Font.PLAIN, 12));
		bntExcluir.setBounds(494, 93, 227, 23);
		contentPane.add(bntExcluir);
		
		JButton bntLimpar = new JButton("Limpar");
		bntLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		bntLimpar.setFont(new Font("Open Sans", Font.PLAIN, 12));
		bntLimpar.setBounds(494, 118, 227, 23);
		contentPane.add(bntLimpar);
		
		JLabel lblBusca = new JLabel("Busca");
		lblBusca.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblBusca.setBounds(345, 196, 294, 14);
		contentPane.add(lblBusca);
		
		JLabel lblCod_1 = new JLabel("Cod");
		lblCod_1.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblCod_1.setBounds(117, 224, 65, 14);
		contentPane.add(lblCod_1);
		
		tfBNome = new JTextField();
		tfBNome.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfBNome.setColumns(10);
		tfBNome.setBounds(209, 221, 340, 20);
		tfBNome.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				
				//atualizar a tabela apenas com valores correspondentes aos digitados no campo de busca por codigo
				TableRowSorter<TableModel> filtro = null;  
				DefaultTableModel model = (DefaultTableModel) table.getModel();  
				filtro = new TableRowSorter<TableModel>(model);  
				table.setRowSorter(filtro);
				
				if (tfBNome.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {  
					filtro.setRowFilter(RowFilter.regexFilter(tfBNome.getText(), 0));  
				}  
			}
		});
		contentPane.add(tfBNome);
		
		JLabel label_1 = new JLabel("Registro M.S.");
		label_1.setFont(new Font("Open Sans", Font.PLAIN, 12));
		label_1.setBounds(117, 248, 82, 17);
		contentPane.add(label_1);
		JScrollPane scrollPane = new JScrollPane();
		
		tfBRegistro = new JTextField();
		tfBRegistro.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfBRegistro.setColumns(10);
		tfBRegistro.setBounds(209, 245, 340, 20);
		tfBRegistro.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				
				//atualizar a tabela apenas com valores correspondentes aos digitados no campo de busca por codigo
				TableRowSorter<TableModel> filtro = null;  
				DefaultTableModel model = (DefaultTableModel) table.getModel();  
				filtro = new TableRowSorter<TableModel>(model);  
				table.setRowSorter(filtro);
				
				if (tfBRegistro.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {  
					filtro.setRowFilter(RowFilter.regexFilter(tfBRegistro.getText(), 4));  
				}  
			}
		});
		contentPane.add(tfBRegistro);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("C:\\Users\\MicaelPlayer\\Desktop\\IFC\\TrabAtualizado\\Trab\\src\\br\\edu\\ifcvideira\\imgs\\Refresh-Icon.jpg"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarTabela();
			}
		});
		button.setBounds(559, 228, 25, 25);
		contentPane.add(button);
		scrollPane.setViewportView(table);
	
		scrollPane.setBounds(25, 287, 724, 242);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				setCamposFromTabela();
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Cod","Nome", "Valor", "Quantidade", "Registro"
			}
		));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(66, 187, 644, 236);
		contentPane.add(separator);
		
		JLabel lblCod = new JLabel("Cod");
		lblCod.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblCod.setBounds(25, 146, 65, 14);
		contentPane.add(lblCod);
		
		tfCod.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfCod.setEditable(false);
		tfCod.setColumns(10);
		tfCod.setBounds(117, 143, 82, 20);
		contentPane.add(tfCod);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\MicaelPlayer\\Desktop\\IFC\\TrabAtualizado\\Trab\\src\\br\\edu\\ifcvideira\\imgs\\Background1.jpg"));
		lblNewLabel.setBounds(0, 0, 784, 540);
		contentPane.add(lblNewLabel);
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
		mntmProduto.setFont(new Font("Open Sans", Font.PLAIN, 12));
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
	public void sair() {
		System.exit(0);
	}

	public void setCamposFromTabela() {
		tfCod.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
		tfProduto.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
		tfValor.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 2)));
		tfQnt.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 3)));
		tfRegistro.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 4)));	
	}

	public void limpar() {
		tfProduto.setText(null);
		tfRegistro.setText(null);
		tfQnt.setText(null);
		tfValor.setText(null);
		tfCod.setText(null);
	}

	public void atualizarTabela() {
		try {
			produto = pDao.buscarTodos();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
		for (int x=0; x!=produto.size(); x++)
			{
				model.addRow((Object[]) produto.get(x));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
