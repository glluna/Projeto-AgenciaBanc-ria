package model;

	public class Pessoa {
			
		   private String nome;
		   private String cpf;
		   private String email;
		   private int id;
		  
		   public Pessoa() {
			   
		   }
		
		
		public Pessoa(int c, String nome, String cpf, String email) {
			this.nome = nome;
			this.cpf = cpf;
			this.email = email;
			this.id = c;
		
		}
		public Pessoa(String nome) {
			this.nome = nome;
		}
		
		   
		public int getId() {
			return id;
		}
	
	
		//Os construtores são responsáveis ​​por criar instâncias da classe Pessoa. Eles inicializam 
		//os atributos nome, cpf, emaile idcom os valores fornecidos. O construtor sem configurações não realiza nenhuma inicialização adicional.
		
		public Pessoa(String nome, String cpf, String email) {
			this.nome = nome;
			this.cpf = cpf;
			this.email = email;
			
		}
		
		//Métodos de acesso (getters) e modificação 
		//(setters) para os atributos da classe. Permite obter e definir os valores dos atributos.
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getCpf() {
			return cpf;
		}
		public void setCpf(String cpf) {
			this.cpf = cpf;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
	
		 public String toString() {
			 return "\nNome: " + this.getNome() +
					 "\nCPF: " + this.getCpf() +
					 "\nEmail " + this.getEmail();
		 }
	
	
		}
	
	
