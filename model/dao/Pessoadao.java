package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Pessoa;

public class Pessoadao {
 Connection conn;
 
 public Pessoadao(Connection con) {
	 this.conn = con;
 }
 
 public void insert(Pessoa p) {
	 PreparedStatement st = null;
	 try {
		 st = conn.prepareStatement("INSERT INTO pessoa( nome, cpf, email) VALUES "
		 		+ "(?,?,?)");
		 st.setString(1, p.getNome());
		 st.setString(2, p.getCpf());
		 st.setString(3, p.getEmail());
		 st.executeUpdate();
	 } catch (SQLException e) {
		 e.printStackTrace();
	 }
	 
 }
 
 public Pessoa select(int id) {
	PreparedStatement st = null;//Guarda um comando sql
	ResultSet re = null;//guarda um valor retornado do banco
	try {
		st = conn.prepareStatement(" SELECT * FROM pessoa WHERE counter = ?");
		st.setInt(1, id);
		re = st.executeQuery();
		if(re.next()) {
			Pessoa p = new Pessoa(re.getInt("counter"), re.getString("nome"),re.getString("cpf"),re.getString("email"));
			return p;
		}
	} catch(SQLException e) {
		e.printStackTrace();
		
	}
	return null;
 } 
 public void update(Pessoa p, int id) {
	 PreparedStatement st = null;
	 try {
		 st = conn.prepareStatement("UPDATE pessoa SET nome = ?, cpf = ?, email = ? WHERE counter = ?");
		 st.setInt(4, id);
		 st.setString(1, p.getNome());
		 st.setString(2, p.getCpf());
		 st.setString(3, p.getEmail());
		 st.executeUpdate();
		 
	 } catch(SQLException e) {
		 e.printStackTrace();
	 }
 }
 
 public void delete(int id) {
	 PreparedStatement st = null;
	 try {
		 st = conn.prepareStatement("DELETE FROM pessoa WHERE counter = ?");
		 st.setInt(1, id);
		 st.executeUpdate();
	 } catch(SQLException e) {
		 e.printStackTrace();
		 
	 }
 }
 
 public Pessoa select(String cpf) {
	 PreparedStatement st = null;
	 ResultSet rs = null;
	 try {
		 st = conn.prepareStatement("SELECT * FROM pessoa WHERE cpf = ?");
		 st.setString(1, cpf);
		 rs = st.executeQuery();
		 if(rs.next()) {
			 int id = rs.getInt("counter");
			 String nome = rs.getString("nome");
			 String cpfbanco= rs.getString("cpf");
			 String emailbanco = rs.getString("email");
			 
			 
			 Pessoa p = new Pessoa(id, nome, cpfbanco, emailbanco);
			 return p;
		 }
		 return null;
	 } catch(SQLException e){
		 e.printStackTrace();
		 
	 }
	 return null;
	 
 }
}
