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

import br.edu.ifcvideira.DAOs.ClienteDao;
import br.edu.ifcvideira.Trabalho.Cliente;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
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
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import br.edu.ifcvideira.imgs.*;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class CadastroCliente extends JFrame {

	private JPanel contentPane;
	private JTextField tfNomeFantasia;
	private JTextField tfCNPJ;
	private JTextField tfRazaoSocial;
	private JTextField tfInscricaoEstadual;
	private JTextField tfTel;
	private JTextField tfEmail;
	private JTextField TfEndereco;
	private JTextField tfData;
	private JButton btnLimpar;
	private JTextField tfBCod;
	private JTextField tfBCNPJ;
	private JTable table;
	private List<Object> cliente = new ArrayList<Object>();
	ClienteDao clDao = new ClienteDao();
	Cliente cc = new Cliente();
	private JTextField tfCod;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroCliente frame = new CadastroCliente();
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
	public CadastroCliente() {
		setTitle("Drogalizando");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\MicaelPlayer\\Desktop\\IFC\\TrabAtualizado\\Trab\\src\\br\\edu\\ifcvideira\\imgs\\Icone.jpg"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		long time = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(time);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		mnMenu.setFont(new Font("Open Sans", Font.PLAIN, 12));
		menuBar.add(mnMenu);
		
		JMenuItem mntmPrincipal = new JMenuItem("Principal");
		mntmPrincipal.setFont(new Font("Open Sans", Font.PLAIN, 12));
		mntmPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		JMenu mmVenda = new JMenu("Venda");
		mmVenda.setFont(new Font("Open Sans", Font.PLAIN, 12));
		menuBar.add(mmVenda);
		
		JMenuItem mnVenda1 = new JMenuItem("Venda");
		mnVenda1.setFont(new Font("Open Sans", Font.PLAIN, 12));
		mnVenda1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new VendaView().setVisible(true);
			}
		});
		mmVenda.add(mnVenda1);
		
		JMenuItem mntmRegistros = new JMenuItem("Registros");
		mntmRegistros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new RegistroVendasView().setVisible(true);
			}
		});
		mntmRegistros.setFont(new Font("Open Sans", Font.PLAIN, 12));
		mmVenda.add(mntmRegistros);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCa = new JLabel("Cadastro de Cliente");
		lblCa.setBounds(309, 11, 110, 14);
		lblCa.setFont(new Font("Open Sans", Font.PLAIN, 12));
		contentPane.add(lblCa);
		
		JLabel lblNomeFantasia = new JLabel("Nome Fantasia");
		lblNomeFantasia.setBounds(10, 52, 97, 14);
		lblNomeFantasia.setFont(new Font("Open Sans", Font.PLAIN, 12));
		contentPane.add(lblNomeFantasia);
		
		tfNomeFantasia = new JTextField();
		tfNomeFantasia.setBounds(117, 49, 320, 20);
		tfNomeFantasia.setFont(new Font("Open Sans", Font.PLAIN, 12));
		contentPane.add(tfNomeFantasia);
		tfNomeFantasia.setColumns(10);
		
		JLabel lblCnpj = new JLabel("CNPJ\r\n");
		lblCnpj.setBounds(10, 80, 97, 14);
		lblCnpj.setFont(new Font("Open Sans", Font.PLAIN, 12));
		contentPane.add(lblCnpj);
		
		JLabel lblRazoSocial = new JLabel("Raz\u00E3o Social\r\n");
		lblRazoSocial.setBounds(10, 107, 97, 14);
		lblRazoSocial.setFont(new Font("Open Sans", Font.PLAIN, 12));
		contentPane.add(lblRazoSocial);
		
		JLabel lblInscrioEstadual = new JLabel("Inscri\u00E7\u00E3o Estadual\r\n");
		lblInscrioEstadual.setBounds(10, 132, 97, 14);
		lblInscrioEstadual.setFont(new Font("Open Sans", Font.PLAIN, 12));
		contentPane.add(lblInscrioEstadual);
		
		JLabel lblTelefone = new JLabel("Telefone\r\n");
		lblTelefone.setBounds(10, 157, 97, 14);
		lblTelefone.setFont(new Font("Open Sans", Font.PLAIN, 12));
		contentPane.add(lblTelefone);
		
		tfCNPJ = new JTextField();
		tfCNPJ.setBounds(117, 77, 320, 20);
		tfCNPJ.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfCNPJ.setColumns(10);
		contentPane.add(tfCNPJ);
		
		tfRazaoSocial = new JTextField();
		tfRazaoSocial.setBounds(117, 104, 320, 20);
		tfRazaoSocial.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfRazaoSocial.setColumns(10);
		contentPane.add(tfRazaoSocial);
		
		tfInscricaoEstadual = new JTextField();
		tfInscricaoEstadual.setBounds(117, 129, 320, 20);
		tfInscricaoEstadual.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfInscricaoEstadual.setColumns(10);
		contentPane.add(tfInscricaoEstadual);
		
		tfTel = new JTextField();
		tfTel.setBounds(117, 154, 320, 20);
		tfTel.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfTel.setColumns(10);
		contentPane.add(tfTel);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 182, 97, 14);
		lblEmail.setFont(new Font("Open Sans", Font.PLAIN, 12));
		contentPane.add(lblEmail);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setBounds(10, 207, 97, 14);
		lblEndereo.setFont(new Font("Open Sans", Font.PLAIN, 12));
		contentPane.add(lblEndereo);
		
		JLabel lblDataDeInscrio = new JLabel("Data de Inscri\u00E7\u00E3o");
		lblDataDeInscrio.setBounds(10, 232, 97, 14);
		lblDataDeInscrio.setFont(new Font("Open Sans", Font.PLAIN, 12));
		contentPane.add(lblDataDeInscrio);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(117, 179, 320, 20);
		tfEmail.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfEmail.setColumns(10);
		contentPane.add(tfEmail);
		
		TfEndereco = new JTextField();
		TfEndereco.setBounds(117, 204, 320, 20);
		TfEndereco.setFont(new Font("Open Sans", Font.PLAIN, 12));
		TfEndereco.setColumns(10);
		contentPane.add(TfEndereco);
		
		tfData = new JTextField();
		tfData.setBounds(117, 229, 320, 20);
		tfData.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfData.setEditable(false);
		tfData.setColumns(10);
		contentPane.add(tfData);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(456, 98, 281, 23);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				cc.setCod(clDao.RetornarProximoCodigoCliente());
				cc.setNomeFantasia(tfNomeFantasia.getText());
				cc.setCnpj(tfCNPJ.getText());
				cc.setRazaoSocial(tfRazaoSocial.getText());
				cc.setInscricaoEstadual(tfInscricaoEstadual.getText());
				cc.setEmail(tfEmail.getText());
				cc.setEndereco(TfEndereco.getText());
				cc.setTelefone(tfTel.getText());
				cc.setDataInscricao(timestamp);
				clDao.CadastrarCliente(cc);
				
				} catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
				atualizarTabela();
				limpar();
			}
		});
		btnCadastrar.setFont(new Font("Open Sans", Font.PLAIN, 12));
		contentPane.add(btnCadastrar);
		
		JButton btnAlterar = new JButton("Alterar");
				btnAlterar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (table.getSelectedRow() != -1){
							try {
								  
								//atribuição dos valores dos campos para o objeto cliente
								cc.setCod(Integer.parseInt(tfCod.getText()));
								cc.setNomeFantasia(tfNomeFantasia.getText());
								cc.setCnpj(tfCNPJ.getText());
								cc.setRazaoSocial(tfRazaoSocial.getText());
								cc.setInscricaoEstadual(tfInscricaoEstadual.getText());
								cc.setEmail(tfEmail.getText());
								cc.setEndereco(TfEndereco.getText());
								cc.setTelefone(tfTel.getText());
								cc.setDataInscricao(timestamp);
								// chamada do método de alteração na classe Dao
								clDao.AlterarCliente(cc);
								
				
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage());
							}
							atualizarTabela();
							limpar();
						}
						
						else{
							JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada");
						}
					}
				});
		btnAlterar.setBounds(456, 123, 281, 23);
		btnAlterar.setFont(new Font("Open Sans", Font.PLAIN, 12));
		contentPane.add(btnAlterar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1){
					Object[] options3 = {"Excluir", "Cancelar"};
					if(JOptionPane.showOptionDialog(null, "Tem certeza que deseja excluir o registro:\n>   " 
							+ table.getValueAt(table.getSelectedRow(), 0) + "   -   "
							+ table.getValueAt(table.getSelectedRow(), 1), null,
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options3, options3[0]) == 0){
						try {
						
							//atribuição do valor do campo código para o objeto cliente
							cc.setCod(Integer.parseInt(tfCod.getText()));
							
							// chamada do método de exclusão na classe Dao passando como parâmetro o código do cliente para ser excluído
							clDao.deletarCliente(cc);
							
						
							atualizarTabela();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada");
				}
			}
		});
		btnExcluir.setBounds(456, 148, 281, 23);
		btnExcluir.setFont(new Font("Open Sans", Font.PLAIN, 12));
		contentPane.add(btnExcluir);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(456, 173, 281, 23);
		btnLimpar.setFont(new Font("Open Sans", Font.PLAIN, 12));
		contentPane.add(btnLimpar);
		
		JLabel lblBusca = new JLabel("Busca");
		lblBusca.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblBusca.setBounds(346, 270, 110, 14);
		contentPane.add(lblBusca);
		
		tfBCod = new JTextField();
		tfBCod.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfBCod.setColumns(10);
		tfBCod.setBounds(224, 301, 320, 20);
		tfBCod.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				
				//atualizar a tabela apenas com valores correspondentes aos digitados no campo de busca por codigo
				TableRowSorter<TableModel> filtro = null;  
				DefaultTableModel model = (DefaultTableModel) table.getModel();  
				filtro = new TableRowSorter<TableModel>(model);  
				table.setRowSorter(filtro);
				
				if (tfBCod.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {  
					filtro.setRowFilter(RowFilter.regexFilter("(?i)" +tfBCod.getText(), 0));  
				}  
			}
		});
		contentPane.add(tfBCod);
		
		JLabel lblCod_1 = new JLabel("Cod");
		lblCod_1.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblCod_1.setBounds(117, 304, 97, 14);
		contentPane.add(lblCod_1);
		
		JLabel label_1 = new JLabel("CNPJ\r\n");
		label_1.setFont(new Font("Open Sans", Font.PLAIN, 12));
		label_1.setBounds(117, 332, 97, 14);
		contentPane.add(label_1);
		
		tfBCNPJ = new JTextField();
		tfBCNPJ.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfBCNPJ.setColumns(10);
		tfBCNPJ.setBounds(224, 329, 320, 20);
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 368, 764, 161);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				setCamposFromTabela();
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Cod", "CNPJ", "Nome", "Endereço", "Telefone", "Data", "Email", "Razão Social", "Inscrição Estadual"
			}
		));
		
		JLabel lblCod = new JLabel("Cod");
		lblCod.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblCod.setBounds(457, 232, 35, 14);
		contentPane.add(lblCod);
		
		tfCod = new JTextField();
		tfCod.setFont(new Font("Open Sans", Font.PLAIN, 12));
		tfCod.setEditable(false);
		tfCod.setColumns(10);
		tfCod.setBounds(494, 229, 60, 20);
		contentPane.add(tfCod);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("C:\\Users\\MicaelPlayer\\Desktop\\IFC\\TrabAtualizado\\Trab\\src\\br\\edu\\ifcvideira\\imgs\\Refresh-Icon.jpg"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarTabela();
			}
		});
		button.setBounds(571, 311, 25, 25);
		contentPane.add(button);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(60, 265, 660, 231);
		contentPane.add(separator);
		
		JLabel lblEuExisto = new JLabel("Eu Existo");
		lblEuExisto.setIcon(new ImageIcon("C:\\Users\\MicaelPlayer\\Desktop\\IFC\\TrabAtualizado\\Trab\\src\\br\\edu\\ifcvideira\\imgs\\Background1.jpg"));
		lblEuExisto.setBounds(0, 0, 784, 540);
		contentPane.add(lblEuExisto);
	}
	
	public void sair() {
		System.exit(0);
	}

	public void setCamposFromTabela() {
		tfCod.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
		tfCNPJ.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
		tfNomeFantasia.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 2)));
		TfEndereco.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 3)));
		tfTel.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 4)));
		tfData.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 5)));
		tfEmail.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 6)));
		tfRazaoSocial.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 7)));
		tfInscricaoEstadual.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 8)));
	}

	public void limpar() {
		tfNomeFantasia.setText(null);
		tfCNPJ.setText(null);
		tfRazaoSocial.setText(null);
		tfInscricaoEstadual.setText(null);
		tfEmail.setText(null);
		TfEndereco.setText(null);
		tfTel.setText(null);
		tfData.setText(null);
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
}
