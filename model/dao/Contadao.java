package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;

import Controller.DB;
import model.Conta;
import model.Pessoa;


public class Contadao {
 Connection conn = null;
 
 //Funcionalidade: Inicializa a conexão ( conn) com o banco de dados usando
 //a conexão fornecida como parâmetro.
 
 public Contadao(Connection con) {
	 this.conn = con;
 }
 
 public void insert(Conta c) {
	 PreparedStatement pr = null;
	 try {
		 pr = conn.prepareStatement("INSERT INTO conta( saldo, pessoaId) VALUES "
			 		+ "(?,?)");
		 pr.setDouble(1, c.getSaldo());
		 pr.setInt(2, c.getPessoa().getId());
		 pr.executeUpdate();
	 } catch (SQLException e){
		 e.printStackTrace();
		 
	 }
	 
 }
 
 private Pessoa instânciaPessoa(int id) {
	try {
	 Pessoadao pd = new  Pessoadao (DB.getConnection());
	 Pessoa p = pd.select(id);
	 return p;
	} catch (Exception e){
		e.printStackTrace();
		
	}
	 return null;
 }
 
 public Conta select(int  id) {
	 PreparedStatement pr = null;
	 ResultSet  re = null;
	 try {
		 pr = conn.prepareStatement("SELECT * FROM conta WHERE numeroConta = ?");       
		 pr.setInt(1, id);
		 re = pr.executeQuery();
		 if(re.next()) {
			 Pessoa p = this.instânciaPessoa(re.getInt("pessoaId"));
			 Conta c = new Conta(re.getInt("numeroConta"),p,re.getDouble("saldo"));
			 return c;
			
			
		 }
	 } catch (SQLException e){
		 e.printStackTrace();
	 }
	return null;
 }
 
 public void update(Conta c, int Id) {
	 PreparedStatement pr = null;
	 try {
		 pr = conn.prepareStatement("UPDATE conta SET saldo = ?, pessoaId = ? WHERE numeroConta = ?");
		 pr.setInt(3, c.getNumeroConta());
		 pr.setDouble(1, c.getSaldo());
		 pr.setInt(2, c.getPessoa().getId());
		 pr.executeUpdate();
		 
	 } catch (SQLException e){
		 e.printStackTrace();
		 
	 }
 }
 
 public void delete(int id) {
	 PreparedStatement pr = null;
	 try {
		 pr = conn.prepareStatement("DELETE FROM conta WHERE numeroConta = ?");
		 pr.setInt(1, id);
		 pr.executeUpdate();
	 } catch (SQLException e) {
		 e.printStackTrace();
	 }
	 
 }
 
 public List<Conta> selectAll(){
	 PreparedStatement pr = null;
	 ResultSet re = null;
	 List<Conta> contas = new ArrayList<>();
	 try {
		 pr = conn.prepareStatement("SELECT numeroConta, saldo, pessoa.nome, pessoa.cpf, "+ "pessoa.email "+
                 "FROM conta "+
                 "JOIN pessoa ON conta.pessoaId = pessoa.counter");
		 re = pr.executeQuery();
		 while(re.next()) {
			 int nconta = re.getInt("numeroConta");
			 double saldo = re.getDouble("saldo");
			 String pessoa = re.getString("nome");
			 Pessoa p = new Pessoa(pessoa);
			 Conta c = new Conta(nconta, p, saldo);
			 contas.add(c);
		 }
		 return contas;
	 } catch(SQLException e){
		 e.printStackTrace();
		 
	 }
	 return null;
 }
 public Conta selectbypessoa(int pessoaid) {
	 PreparedStatement pr = null;
	 ResultSet  re = null;
	 try {
		 pr = conn.prepareStatement("SELECT * FROM conta WHERE pessoaId = ?");       
		 pr.setInt(1, pessoaid);
		 re = pr.executeQuery();
		 if(re.next()) {
			 Pessoa p = this.instânciaPessoa(re.getInt("pessoaId"));
			 Conta c = new Conta(re.getInt("numeroConta"),p,re.getDouble("saldo"));
			 return c;
			
			
		 }
	 } catch (SQLException e){
		 e.printStackTrace();
	 }
	return null;
 }
 public void transferir(int idfonte, int idalvo, double valor) {
	 PreparedStatement pr = null;
	 
	 try {
		 conn.setAutoCommit(false);
		 
		 pr = conn.prepareStatement("UPDATE conta SET saldo = saldo - ? WHERE numeroConta = ?");
		 pr.setDouble(1, valor);
		 pr.setInt(2, idfonte);
		 pr.executeUpdate();
		 pr = conn.prepareStatement("UPDATE conta SET saldo = saldo + ? WHERE numeroConta = ?");
		 pr.setDouble(1, valor);
		 pr.setInt(2, idalvo);
		 pr.executeUpdate();
		 conn.commit();
	 }catch (SQLException e) {
		 
		 e.printStackTrace();
	 }
 }


}


