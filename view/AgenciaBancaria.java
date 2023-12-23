package view;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.SelectableChannel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Controller.DB;
import model.Conta;
import model.Pessoa;
import model.dao.Contadao;
import model.dao.Pessoadao;

public class AgenciaBancaria {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AgenciaBancariaFrame());
    }
}

class AgenciaBancariaFrame extends JFrame {
    private JButton btnCriarConta, btnDepositar, btnSacar, btnTransferir, btnListarContas;
    Contadao cd = new Contadao(DB.getConnection());
    Pessoadao pd = new Pessoadao(DB.getConnection());
   
    public AgenciaBancariaFrame() {
        setTitle("BANCO 24HR");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setResizable(false);

        ImagemDeFundoPanel painelComImagemDeFundo = new ImagemDeFundoPanel("src\\Img\\img.png");

        // Use BorderLayout para organizar o painel com imagem de fundo
        setLayout(new BorderLayout());
        add(painelComImagemDeFundo, BorderLayout.CENTER);

        // Não precisa mais do GridLayout, pois estamos usando setBounds para posicionar os botões

        // Adicione os botões ao painel com imagem de fundo
        btnCriarConta = new JButton("Criar Conta");
        btnDepositar = new JButton("Depositar");
        btnSacar = new JButton("Sacar");
        btnTransferir = new JButton("Transferir");
        btnListarContas = new JButton("Listar Contas");

        // Defina as posições e tamanhos absolutos dos botões usando setBounds
        btnCriarConta.setBounds(50, 50, 150, 50);
        btnDepositar.setBounds(50, 120, 150, 50);
        btnSacar.setBounds(50, 190, 150, 50);
        btnTransferir.setBounds(50, 260, 150, 50);
        btnListarContas.setBounds(50, 330, 150, 50);
        
        
        btnListarContas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evento) {
			JOptionPane.showMessageDialog(null, cd.selectAll() );
				
			}
		});
        
        btnCriarConta.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evento) {
        		String nome = JOptionPane.showInputDialog("Digite o nome do titular da conta: ");
        		String cpf = JOptionPane.showInputDialog("Digite o cpf do titular da conta(formato: xxxxxxxxxxx): ");
        		String email = JOptionPane.showInputDialog("Digite o email do titular da conta: ");
        		
        		if(pd.select(cpf) != null) {
        			JOptionPane.showMessageDialog(null, "Cpf já cadastrado");
        		}else {
        			Pessoa p = new Pessoa (nome, cpf, email);
        			pd.insert(p);
        			p = pd.select(cpf);
        			JOptionPane.showMessageDialog(null, "Pessoa cadastrada: "+ p.getId());
        			double saldo = Double.parseDouble(JOptionPane.showInputDialog("Digite um saldo inicial: "));
        			Conta c = new Conta(p, saldo);
        			cd.insert(c);
        			c = cd.selectbypessoa(p.getId());
        			JOptionPane.showMessageDialog(null,"Conta criada com sucesso!: "+ c.getNumeroConta());
        		}
        	}
  
        });
        btnDepositar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evento) {
        		Integer id = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da conta: "));
        		double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite um valor para depositar: "));
        		Conta c = cd.select(id);
        		c.depositar(valor);
        		cd.update(c, id);
        		JOptionPane.showMessageDialog(null, "depósito realizado com sucesso!");
        	}
        });
        btnSacar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evento) {
        		Integer id = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da conta: "));
        		double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite um valor para sacar: "));
        		Conta c = cd.select(id);
        		c.sacar(valor);
        		cd.update(c, id);
        		JOptionPane.showMessageDialog(null, "saque realizado com sucesso!");
        	}
        });
       
        btnTransferir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evento) {
        		Integer idalvo = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da conta receptora: "));
        	   
        	    Integer idfonte = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da conta fonte: "));
        	   Double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite um valor para trânsferencia: "));
        	 
        	   cd.transferir(idfonte, idalvo, valor);
        	   JOptionPane.showMessageDialog(null, "Valor tranferido com sucesso!");
        	}
        });
        
        
        
        
        
        
        
        // Adicione os botões ao painel
        painelComImagemDeFundo.add(btnCriarConta);
        painelComImagemDeFundo.add(btnDepositar);
        painelComImagemDeFundo.add(btnSacar);
        painelComImagemDeFundo.add(btnTransferir);
        painelComImagemDeFundo.add(btnListarContas);

        // Tornar a janela visível
        setVisible(true);
    }
}

class ImagemDeFundoPanel extends JPanel { 
    private Image imagemDeFundo;

    public ImagemDeFundoPanel(String caminhoImagem) {
        imagemDeFundo = new ImageIcon(caminhoImagem).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagemDeFundo, 0, 0, getWidth(), getHeight(), this);
    }
}
   