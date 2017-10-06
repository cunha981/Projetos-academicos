import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JPasswordField; 
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AtualizarLogin extends JFrame implements ActionListener{

   private JLabel lblTitulo, lblUsuario, lblSenha, lblRodape;
   private JButton btnEntrar, btnVoltar, btnHome;
   private JTextField txtUsuario; 
   private ImageIcon voltar, home;
   private JPanel painel1, painel2, painel3, painel4, painel5, painel6, painel7;
   private JPasswordField txtSenha;
   private Connection conn;
 
   public AtualizarLogin(Connection conn){
      super("Sistema de Gerenciamento de Mananciais - Atualizar (Login)");
      
      this.conn = conn;
      
      voltar = new ImageIcon("imagens/voltar.png");  
      btnVoltar = new JButton(voltar);
      btnVoltar.setPreferredSize(new Dimension(32, 32));
      btnVoltar.setOpaque(false);
      btnVoltar.setContentAreaFilled(false);
      btnVoltar.setBorderPainted(false);
      home = new ImageIcon("imagens/home.png");   
      btnHome = new JButton(home);
      btnHome.setPreferredSize(new Dimension(32, 32));
      btnHome.setOpaque(false);
      btnHome.setContentAreaFilled(false);
      btnHome.setBorderPainted(false);
      btnEntrar = new JButton("Entrar");
      btnEntrar.setPreferredSize(new Dimension(100, 30));
      
      lblTitulo = new JLabel("Atualizar");
      lblTitulo.setFont(new Font("Arial", Font.BOLD, 25));
      lblTitulo.setForeground(Color.blue);
      lblUsuario = new JLabel("Usuário: ");
      lblUsuario.setForeground(Color.black);
      lblSenha = new JLabel("Senha: ");
      lblSenha.setForeground(Color.black);  
      lblRodape = new JLabel("Sistema de Gerenciamento de Mananciais - SEM CHAVE NÃO COMPILA (USJT) ©2016");
      
      txtUsuario = new JTextField(15);
      txtSenha = new JPasswordField(15);
       
      Container tela = getContentPane();
      tela.setBackground(Color.white);
      tela.setLayout(new GridLayout(6,1));
      
      painel1 = new JPanel(new FlowLayout(0));
      painel2 = new JPanel(new FlowLayout(1));
      painel3 = new JPanel(new FlowLayout(1));
      painel4 = new JPanel(new FlowLayout(1));
      painel5 = new JPanel(new FlowLayout(1));
      painel6 = new JPanel(new FlowLayout(1));
      painel7 = new JPanel(new BorderLayout());
      
      painel1.setBackground(Color.white);
      painel2.setBackground(Color.white);
      painel3.setBackground(Color.white);
      painel4.setBackground(Color.white);
      painel5.setBackground(Color.white);
      painel6.setBackground(Color.white);
      painel7.setBackground(Color.white);
      
      painel1.add(btnVoltar);
      painel1.add(btnHome);
      painel2.add(lblTitulo);
      painel3.add(lblUsuario);
      painel3.add(txtUsuario);
      painel4.add(lblSenha);
      painel4.add(txtSenha);
      painel5.add(btnEntrar);
      painel6.add(lblRodape);
      painel7.add(painel6, BorderLayout.SOUTH);
       
      tela.add(painel1);
      tela.add(painel2);
      tela.add(painel3);
      tela.add(painel4);
      tela.add(painel5);
      tela.add(painel7);
      
      btnHome.addActionListener(this);
      btnVoltar.addActionListener(this); 
      btnEntrar.addActionListener(this);
   
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(800,600);
      setVisible(true);
      setLocationRelativeTo(null);
      setResizable(false);
   }
    
      
   public void actionPerformed(ActionEvent e){
         
      if (e.getSource() == btnHome){
         this.dispose();
         HomeFrame telaInicial = new HomeFrame(conn);
      }
      if (e.getSource() == btnVoltar){
         this.dispose();
         HomeFrame telaInicial = new HomeFrame(conn);
      } 
      if(e.getSource() == btnEntrar){  
         if(txtUsuario.getText().equals("admin") && new String(txtSenha.getPassword()).equals("admin123")){
            this.setVisible(false);
            Atualizar atualizar = new Atualizar(conn);
         }
         else{
            JOptionPane.showMessageDialog(null, "***** Usuário ou Senha Inválidos *****");
         }
      }         
   }   
}