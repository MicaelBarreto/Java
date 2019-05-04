package br.edu.ifcvideira.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import br.edu.ifcvideira.imgs.*;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class DrogalizandoView extends JFrame {

	private JPanel contentPane;
	CadastroProdutoView cp = new CadastroProdutoView();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DrogalizandoView frame = new DrogalizandoView();
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
	public DrogalizandoView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\MicaelPlayer\\Desktop\\IFC\\TrabAtualizado\\Trab\\src\\br\\edu\\ifcvideira\\imgs\\Icone.jpg"));
		setTitle("Drogalizando");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setBackground(Color.WHITE);
		contentPane.setLayout(null);
		
		JButton btnCadastro = new JButton("Cadastro de Cliente");
		btnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new CadastroCliente().setVisible(true);
			}
		});
		btnCadastro.setFont(new Font("Open Sans", Font.PLAIN, 12));
		btnCadastro.setBounds(10, 56, 182, 49);
		contentPane.add(btnCadastro);
		
		JButton bntProduto = new JButton("Cadastro de Produto");
		bntProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new CadastroProdutoView().setVisible(true);
			}
		});
		bntProduto.setFont(new Font("Open Sans", Font.PLAIN, 12));
		bntProduto.setBounds(10, 116, 182, 49);
		contentPane.add(bntProduto);
		
		JButton bntVenda = new JButton("Venda");
		bntVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new VendaView().setVisible(true);
			}
		});
		bntVenda.setFont(new Font("Open Sans", Font.PLAIN, 12));
		bntVenda.setBounds(10, 176, 182, 49);
		contentPane.add(bntVenda);
		
		JButton btnRegistrosDeVenda = new JButton("Registros de Venda");
		btnRegistrosDeVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new RegistroVendasView().setVisible(true);
			}
		});
		btnRegistrosDeVenda.setFont(new Font("Open Sans", Font.PLAIN, 12));
		btnRegistrosDeVenda.setBounds(10, 236, 182, 49);
		contentPane.add(btnRegistrosDeVenda);
		
		JLabel lblMenu = new JLabel("MENU");
		lblMenu.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblMenu.setBounds(369, 11, 46, 14);
		contentPane.add(lblMenu);
		
		JLabel lblEuTbm = new JLabel("");
		lblEuTbm.setIcon(new ImageIcon("C:\\Users\\MicaelPlayer\\Desktop\\IFC\\TrabAtualizado\\Trab\\src\\br\\edu\\ifcvideira\\imgs\\drogalizando.jpg"));
		lblEuTbm.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lblEuTbm.setBounds(230, 56, 544, 494);
		contentPane.add(lblEuTbm);
		
		JLabel lbLogo = new JLabel("");
		lbLogo.setIcon(new ImageIcon("C:\\Users\\MicaelPlayer\\Desktop\\IFC\\TrabAtualizado\\Trab\\src\\br\\edu\\ifcvideira\\imgs\\Background1.jpg"));
		lbLogo.setFont(new Font("Open Sans", Font.PLAIN, 12));
		lbLogo.setBounds(0, 1, 784, 560);
		contentPane.add(lbLogo);
	}
}
