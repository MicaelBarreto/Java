package br.edu.ifcvideira.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import br.edu.ifcvideira.Trabalho.Produto;
import br.edu.ifcvideira.utils.Conexao;

public class ProdutoDao {
	
	public void CadastrarProduto(Produto pp) throws SQLException, Exception{
		try{
			String sql = "INSERT INTO produto (nomeProduto, valorProduto, qntProduto, registro_ms, codProduto) VALUES (?,?,?,?,?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, pp.getNomeProduto());
			sqlPrep.setDouble(2, pp.getValor());
			sqlPrep.setDouble(3, pp.getQnt());
			sqlPrep.setString(4, pp.getRegistroMS());
			sqlPrep.setInt(5, pp.getCodProduto());
			sqlPrep.execute();
			
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}

	public void AlterarProduto(Produto pp) throws Exception {
		try{
			String sql = "UPDATE produto SET nomeProduto=?, valorProduto=?, qntProduto=?, registro_ms=? WHERE codProduto=?";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, pp.getNomeProduto());
			sqlPrep.setDouble(2, pp.getValor());
			sqlPrep.setDouble(3, pp.getQnt());
			sqlPrep.setString(4, pp.getRegistroMS());
			sqlPrep.setInt(5, pp.getCodProduto());
			sqlPrep.execute();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void AlterarQntProduto(Produto pp) throws Exception {
		try{
			String sql = "UPDATE produto SET  qntProduto=? WHERE codProduto=?";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setDouble(1, pp.getQnt());
			sqlPrep.setInt(2, pp.getCodProduto());
			sqlPrep.execute();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void deletarCliente(Produto pp) throws Exception{
		try{
			String sql = "DELETE FROM produto WHERE codProduto=? ";
			PreparedStatement sqlPrep = (PreparedStatement) Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, pp.getCodProduto());
			sqlPrep.execute();
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public List<Object> buscarTodos() throws SQLException, Exception{
		List<Object> produto = new ArrayList<Object>();
		try {
			String sql = "SELECT * FROM produto";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next())
			{
				Object[] linha = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
				produto.add(linha);
			}
			state.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return produto;
	}
	
	public int RetornarProximoCodigoProduto() throws Exception {
		try{
			String sql ="SELECT MAX(codProduto)+1 AS codProduto FROM produto ";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			ResultSet rs = sqlPrep.executeQuery();
			if (rs.next()){
				return rs.getInt("codProduto");
			}else{
				return 1;
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return 1;
		}
	}

}
