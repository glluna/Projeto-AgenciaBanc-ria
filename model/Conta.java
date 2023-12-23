package model;

import javax.swing.JOptionPane;

import utilitários.Utils;

public class Conta {

	
	private Integer numeroConta;
	private Pessoa pessoa;
	private Double saldo = 0.0;
	public Conta(Integer numeroConta, Pessoa pessoa, Double saldo) {
		this.numeroConta = numeroConta;
		this.pessoa = pessoa;
		this.saldo = saldo;
		
	}
	
	public Conta(Pessoa pessoa, Double saldo) {
		this.pessoa = pessoa;
		this.saldo = saldo;
	}
	
    public Conta(Pessoa p) {
    	this.pessoa = p;
    }
	public int getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	@Override
	public String toString() {
		return "Conta [numeroConta=" + numeroConta + ", pessoa=" + pessoa + ", saldo=" + saldo + "]";
	}
	
	public void depositar(double valor) {
		if(valor > 0) {
			setSaldo(getSaldo() + valor);
			JOptionPane.showMessageDialog(null, "Seu depósito foi realizado com sucesso!");
		}else {
			JOptionPane.showMessageDialog(null, "Não foi possível realizar o depósito!");
			
		}
	}
	
	public boolean sacar(double valor) {
		if(valor > 0 && this.getSaldo() >= valor) {
			setSaldo(getSaldo() - valor);
			JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!");
			return false;
		}else {
			
			JOptionPane.showMessageDialog(null, "Não foi possível realizar o saque!");
			return true;
		}
	}
	
	public boolean transferencia(Conta contaParaDeposito, double valor) {
		if(valor > 0 && this.getSaldo() >= valor) {
			setSaldo(getSaldo() - valor);
			
			contaParaDeposito.saldo = contaParaDeposito.getSaldo() + valor;
			JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!");
			return false;
		}else {
			JOptionPane.showConfirmDialog(null, "Não foi possível realizar a transferência!");
			return true;
		}
		
	}
	
	

}


