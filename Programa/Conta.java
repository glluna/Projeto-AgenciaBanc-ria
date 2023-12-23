package Programa;

import javax.swing.JOptionPane;

import utilitários.Utils;

public class Conta {
	
	private static int contadorDeContas = 1;
	
	private int numeroConta;
	private Pessoa pessoa;
	private Double saldo = 0.0;
	public Conta( Pessoa pessoa) {
		this.numeroConta = contadorDeContas;
		this.pessoa = pessoa;
		contadorDeContas += 1;
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
	
	public String toString() {
		return  "\nNúmero da conta: " + this.getNumeroConta() +
				"\nNome: " + this.pessoa.getNome() +
		        "\nCPF: " + this.pessoa.getCpf() +
		        "\nEmail: " +  this.pessoa.getEmail() +
		        "\nSaldo: " + Utils.doubleToString(this.getSaldo()) +
		        "\n";
		        
	}
	
	public void depositar(double valor) {
		if(valor > 0) {
			setSaldo(getSaldo() + valor);
			JOptionPane.showMessageDialog(null, "Seu depósito foi realizado com sucesso!");
		}else {
			JOptionPane.showMessageDialog(null, "Não foi possível realizar o depósito!");
			
		}
	}
	
	public void sacar(double valor) {
		if(valor > 0 && this.getSaldo() >= valor) {
			setSaldo(getSaldo() - valor);
			JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!");
		}else {
			JOptionPane.showMessageDialog(null, "Não foi possível realizar o saque!");
		}
	}
	
	public void transferir(Conta contaParaDeposito, double valor) {
		if(valor > 0 && this.getSaldo() >= valor) {
			setSaldo(getSaldo() - valor);
			
			contaParaDeposito.saldo = contaParaDeposito.getSaldo() + valor;
			JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!");
		}else {
			JOptionPane.showConfirmDialog(null, "Não foi possível realizar a transferência!");
		}
		
	}

}
