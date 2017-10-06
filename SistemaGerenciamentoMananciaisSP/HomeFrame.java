import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeFrame extends JFrame implements ActionListener{
   private JButton btSistemas, btMunicipios, btConf;
   private JLabel lbTitulo, lbConsulta, lbRodape, lbLogo;
   private ImageIcon conf, logo;
   private JPanel painel1, painel2, painel3, painel4, painel5, painel6;
   private Connection conn;
   
   //construtor
   public HomeFrame(Connection conn){
      //titulo do frame
      super("Sistema de Gerenciamento de Mananciais");
      
      this.conn = conn;
      
      //instanciando os botoes com suas caracteristicas
      btSistemas = new JButton("SISTEMAS");
      btSistemas.setPreferredSize(new Dimension(150, 45));//set tamanho
      btMunicipios = new JButton("MUNICÍPIOS");
      btMunicipios.setPreferredSize(new Dimension(150, 45));//set tamanho 
      conf = new ImageIcon("imagens/conf2.png");//carregando icon do btConf    
      btConf = new JButton(conf);//coloca icon no botao
      btConf.setPreferredSize(new Dimension(29, 29));//set tamanho
      btConf.setOpaque(false);
      btConf.setContentAreaFilled(false);
      btConf.setBorderPainted(false);

      //instanciando Label com suas caracteristicas
      logo = new ImageIcon("imagens/logo.png");//carregando icon do lbLogo
      lbLogo = new JLabel(logo);//coloca icon no label 
      lbTitulo = new JLabel("Sistema de Gerenciamento de Mananciais");
      lbTitulo.setFont(new Font("Arial", Font.BOLD, 20 ) );//set fonte 
      lbTitulo.setForeground(Color.blue);//set cor da fonte
      lbConsulta = new JLabel("                                                                    CONSULTAR POR:                                                                     ");
      lbConsulta.setFont(new Font("Arial", Font.BOLD, 15 ) );
      lbRodape = new JLabel("Sistema de Gerenciamento de Mananciais - SEM CHAVE NÃO COMPILA (USJT) ©2016");
      
      /*Para você adicionar um componente desse nessa "capa", você deve ter uma referência para este objeto (Container).
       Para você obter essa referência, você utiliza o método getContentPane().*/
      //configura o gerenciador de layout
      Container tela = getContentPane();
      getContentPane().setBackground(Color.CYAN);
      tela.setLayout(new GridLayout(5,1));
      
      painel1 = new JPanel(new FlowLayout(2));
      painel2 = new JPanel(new FlowLayout());
      painel3 = new JPanel(new FlowLayout());
      painel4 = new JPanel(new FlowLayout());
      painel5 = new JPanel(new FlowLayout());
      painel6 = new JPanel(new BorderLayout());
      
      /*cor de fundo*/
      painel1.setBackground(Color.white);
      painel2.setBackground(Color.white);
      painel3.setBackground(Color.white);
      painel4.setBackground(Color.white);
      painel5.setBackground(Color.white);
      painel6.setBackground(Color.white);
      
      //adicionar aos paineis
      painel1.add(btConf);
      painel2.add(lbLogo);
      painel3.add(lbTitulo);
      painel4.add(lbConsulta);
      painel4.add(btSistemas);
      painel4.add(btMunicipios);
      painel5.add(lbRodape);
      painel6.add(painel5, BorderLayout.SOUTH);
      
      //adicionar a tela
      tela.add(painel1);
      tela.add(painel2);
      tela.add(painel3);
      tela.add(painel4);
      tela.add(painel6);
      
      //esta tela é ouvinte de eventos
      btSistemas.addActionListener(this);
      btMunicipios.addActionListener(this);
      btConf.addActionListener(this);

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(800,600);
      setVisible(true);
      setLocationRelativeTo(null);
      setResizable(false);  
   }
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == btSistemas){
         this.setVisible(false);
        ConsultarSistemas consultaS = new ConsultarSistemas(conn);
      }
      if (e.getSource() == btMunicipios){
         this.setVisible(false);
         ConsultarMunicipios consultaM = new ConsultarMunicipios(conn);
      }
      if (e.getSource() == btConf){
         this.setVisible(false);
         AtualizarLogin telaLogin = new AtualizarLogin(conn);
      }
   } 
}