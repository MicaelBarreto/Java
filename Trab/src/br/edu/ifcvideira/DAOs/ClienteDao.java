package br.edu.ifcvideira.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.ifcvideira.Trabalho.Cliente;
import br.edu.ifcvideira.utils.Conexao;

public class ClienteDao{
	
	public void CadastrarCliente(Cliente cl) throws SQLException, Exception{
		try{
			String sql = "INSERT INTO cliente (nome_fantasia, cnpj, telefone, data_inscricao, email, razao_social, inscricao_estadual, endereco, cod) VALUES (?,?,?,?,?,?,?,?,?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, cl.getNomeFantasia());
			sqlPrep.setString(2, cl.getCnpj());
			sqlPrep.setString(3, cl.getTelefone());
			sqlPrep.setTimestamp(4, cl.getDataInscricao());
			sqlPrep.setString(5, cl.getEmail());
			sqlPrep.setString(6, cl.getRazaoSocial());
			sqlPrep.setString(7, cl.getInscricaoEstadual());
			sqlPrep.setString(8, cl.getEndereco());
			sqlPrep.setInt(9, cl.getCod());
			sqlPrep.execute();
			
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}

	public void AlterarCliente(Cliente cl) throws Exception {
		try{
			String sql = "UPDATE cliente SET nome_fantasia=?, cnpj=?, telefone=?, data_inscricao=?, email=?, razao_social=?, inscricao_estadual=?, endereco=? WHERE cod=?";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, cl.getNomeFantasia());
			sqlPrep.setString(2, cl.getCnpj());
			sqlPrep.setString(3, cl.getTelefone());
			sqlPrep.setTimestamp(4, cl.getDataInscricao());
			sqlPrep.setString(5, cl.getEmail());
			sqlPrep.setString(6, cl.getRazaoSocial());
			sqlPrep.setString(7, cl.getInscricaoEstadual());
			sqlPrep.setString(8, cl.getEndereco());
			sqlPrep.setInt(9, cl.getCod());
			sqlPrep.execute();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void deletarCliente(Cliente cl) throws Exception{
		try{
			String sql = "DELETE FROM cliente WHERE cod=? ";
			PreparedStatement sqlPrep = (PreparedStatement) Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, cl.getCod());
			sqlPrep.execute();
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public List<Object> buscarTodos() throws SQLException, Exception{
		List<Object> cliente = new ArrayList<Object>();
		try {
			String sql = "SELECT * FROM cliente";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next())
			{
				Object[] linha = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)};
				cliente.add(linha);
			}
			state.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return cliente;
	}
	
	public int RetornarProximoCodigoCliente() throws Exception {
		try{
			String sql ="SELECT MAX(cod)+1 AS cod FROM cliente ";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			ResultSet rs = sqlPrep.executeQuery();
			if (rs.next()){
				return rs.getInt("cod");
			}else{
				return 1;
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return 1;
		}
	}
}