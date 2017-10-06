import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
//////////////////////////////////
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
//////////////////////////////////
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultarSistemas extends JFrame{
   ////////////////////////
   private JTable tabela;
   private JScrollPane barra, sPDescricao;
   private DefaultTableModel modelo = new DefaultTableModel();
   ////////////////////////   
   private JButton btnHome, btnVoltar;
   private JLabel lblTitulo, lblFiltroInicial, lblFiltroFinal, lblDivisoria, lblNomeSistema, lblImagem, lblConteudo, lblNiveis, lblRodape, lblRelatorio;
   private ImageIcon imgHome, imgVoltar, imgDivisoria, imgSistema, imgRelatorio;
   private JComboBox cb;
   private JPanel pnlTopo, pnlBotoes, pnlLateral, pnlCombo, pnlDivisoria, pnlConteudo, pnlImagem, pnlDescr, pnlNiveis, pnlRodape, pnlMeio;
   private Sistemas sistemas;
   private ArrayList<Sistemas> lista;
   private Connection conn;
   private JTextArea txtDescricao;
      
   public ConsultarSistemas(Connection conn){
      //título do frame
      super("Sistema de Gerenciamento de Mananciais - Consultar por Sistemas");
      
      this.conn = conn;
      
      cb = new JComboBox();
      sistemas = new Sistemas();
      lista = sistemas.carregar(conn);
      //carrega comboBox
      for (int i = 0; i < lista.size(); i++){
         cb.addItem(lista.get(i).getNome()); 
      }
   
      //instanciando os botões com suas características
      imgHome = new ImageIcon("imagens/home.png");
      btnHome = new JButton(imgHome);
      btnHome.setPreferredSize(new Dimension(32, 32));//set tamanho
      btnHome.setOpaque(false);
      btnHome.setContentAreaFilled(false);
      btnHome.setBorderPainted(false);
      imgVoltar = new ImageIcon("imagens/voltar.png");
      btnVoltar = new JButton(imgVoltar);
      btnVoltar.setPreferredSize(new Dimension(32, 32));//set tamanho
      btnVoltar.setOpaque(false);
      btnVoltar.setContentAreaFilled(false);
      btnVoltar.setBorderPainted(false);
      
      //instanciando Label com suas características
      imgDivisoria = new ImageIcon("imagens/divisoria.png");
      lblDivisoria = new JLabel(imgDivisoria);
      lblDivisoria.setPreferredSize(new Dimension(5, 500));//set tamanho
      imgSistema = new ImageIcon("imagens/img_sistema.png");//carregando icon do imgSistema
      lblImagem = new JLabel(imgSistema);//coloca icon
      lblImagem.setPreferredSize(new Dimension(200, 300));
      lblRelatorio = new JLabel(imgRelatorio);//coloca icon
      lblRelatorio.setPreferredSize(new Dimension(90, 90)); 
      lblTitulo = new JLabel("Consultar (Sistemas)");
      lblTitulo.setFont( new Font( "Arial", Font.BOLD, 20 ) );//set fonte 
      lblTitulo.setForeground(Color.blue);//set cor da fonte
      lblFiltroInicial = new JLabel("Filtro: ");
      lblFiltroInicial.setFont( new Font("Arial", Font.BOLD, 15));
      lblFiltroFinal = new JLabel(" até ");
      lblFiltroFinal.setFont( new Font("Arial", Font.BOLD, 15));
      lblNomeSistema = new JLabel("Selecione o sistema ao lado para realizar a consulta");
      lblNomeSistema.setFont( new Font("Arial", Font.BOLD, 16));
      lblConteudo = new JLabel("Conteúdo do Sistema");
      lblConteudo.setFont( new Font("Arial", 0, 14));
      lblNiveis = new JLabel("Níveis");
      lblNiveis.setFont( new Font("Arial", Font.BOLD, 14));
      lblNiveis.setForeground(Color.white);
      lblRodape = new JLabel("Sistema de Gerenciamento de Mananciais - SEM CHAVE NÃO COMPILA (USJT) ©2016");
      
      //instanciando JTextField e JTextArea com suas caracteristicas
      txtDescricao = new JTextArea();
      txtDescricao.setFont(new Font("Arial", 0, 14));
      txtDescricao.setLineWrap(true);
      txtDescricao.setWrapStyleWord(true);
      sPDescricao = new JScrollPane(txtDescricao);
      sPDescricao.setBorder(null);
      sPDescricao.setOpaque(false);
   
      //////////////////
      criaJTable();
      barra = new JScrollPane(tabela);
      barra.setPreferredSize(new Dimension(588,145));  
      //////////////////
      
      /*Para você adicionar um componente desse nessa "capa", você deve ter uma referência para este objeto (Container).
       Para você obter essa referência, você utiliza o método getContentPane().*/
      
      //configura o gerenciador de layout
      Container tela = getContentPane();
      getContentPane().setBackground(Color.CYAN);
      tela.setLayout(new BorderLayout());
      
      pnlMeio = new JPanel(new FlowLayout());
      pnlTopo = new JPanel(new GridLayout(1,3));
      pnlBotoes = new JPanel(new FlowLayout(0));    
      pnlLateral = new JPanel(new FlowLayout(0));
      pnlLateral.setPreferredSize(new Dimension(280, 15));
      pnlCombo = new JPanel(new GridLayout(1,1));
      pnlCombo.setPreferredSize(new Dimension(273, 25));
      pnlDivisoria = new JPanel(new GridLayout(1,1));
      pnlDivisoria.setPreferredSize(new Dimension(5, 500));
      pnlConteudo = new JPanel(new GridLayout(4,1));
      pnlConteudo.setPreferredSize(new Dimension(590, 10));
      pnlNiveis = new JPanel(new FlowLayout(1));
      pnlRodape = new JPanel(new FlowLayout());
      
      //cor de fundo
      pnlBotoes.setBackground(Color.white);
      pnlTopo.setBackground(Color.white);
      pnlLateral.setBackground(Color.white);
      pnlDivisoria.setBackground(Color.white);
      pnlConteudo.setBackground(Color.white);
      pnlNiveis.setBackground(Color.gray);
      pnlRodape.setBackground(Color.white);
      pnlMeio.setBackground(Color.white);
            
      //adicionando aos paineis
      ////////////
      pnlNiveis.add(barra);
      ////////////
      pnlBotoes.add(btnVoltar);
      pnlBotoes.add(btnHome);
      pnlTopo.add(pnlBotoes);
      pnlTopo.add(lblTitulo);
      pnlCombo.add(cb);
      pnlLateral.add(pnlCombo);
      pnlDivisoria.add(lblDivisoria);
      pnlConteudo.add(lblNomeSistema);
      pnlRodape.add(lblRodape);
      
      //adicionando à tela
      tela.add(pnlTopo, BorderLayout.NORTH);
      tela.add(pnlLateral, BorderLayout.WEST);
      tela.add(pnlDivisoria, BorderLayout.CENTER);
      tela.add(pnlConteudo, BorderLayout.EAST);
      tela.add(pnlRodape, BorderLayout.SOUTH);
      
      //eventos
      cb.setSelectedIndex(-1);
      
      cb.addActionListener(
            new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  cbActionPerformed(e);
               }
            });
      
      btnVoltar.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent e){
                  voltarActionPerformed(e);
               }
            });
      btnHome.addActionListener(
            new ActionListener(){
               public void actionPerformed(ActionEvent e) {
                  homeActionPerformed(e);
               }
            });
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(900,700);
      setVisible(true);
      setLocationRelativeTo(null);
      setResizable(false); 
   }
   
   /* métodos */
   
   ///////////////
   private void criaJTable(){
      tabela = new JTable(modelo);
      modelo.addColumn("DATA");
      modelo.addColumn("NÍVEL(x10^6 m³)");
      modelo.addColumn("PERCENTUAL");
      modelo.setNumRows(0);
   }
   //////////////
        
   //método que configura os eventos no dropdown
   
   public void voltarActionPerformed(ActionEvent e){
      this.dispose();
      HomeFrame homeFrame = new HomeFrame(conn);
   }
   
   public void homeActionPerformed(ActionEvent e){
      this.dispose();
      HomeFrame homeFrame = new HomeFrame(conn);
   }
   
   public void cbActionPerformed(ActionEvent e){
      String lista = (String)cb.getSelectedItem();   
      
      if(lista.equals("Cantareira")){
         pnlConteudo.remove(lblNomeSistema);
         pnlMeio.remove(lblRelatorio);
         pnlMeio.remove(lblImagem);
         pnlConteudo.remove(sPDescricao);
         
         double volumeB = 0;
         
         sistemas = new Sistemas();
         ArrayList<Sistemas> arrayS = new ArrayList<>();
         arrayS = sistemas.carregar(conn);
         for(Sistemas sistema : arrayS){
            if(sistema.getNome().equals("Cantareira")){
               ArrayList <Niveis> niveis = Niveis.trazerTodos(sistema,conn);
               modelo.setNumRows(0);
               for (Niveis n : niveis){
                  //////////
                  String x = String.format("%.02f",(n.getVolumeDiario()*100)/sistema.getCapacidade());
                  modelo.addRow(new Object[]{new SimpleDateFormat ("dd/mm/yyyy ").format(n.getData()),
                        String.format("%.02f",(n.getVolumeDiario())),x+"%"});
                  //////////
                  //RELATORIO
                  volumeB += n.getVolumeDiario()/niveis.size();
                  double mediaPercentual = (volumeB*100/sistema.getCapacidade());
                  System.out.println("Media Percentual: " + mediaPercentual + "%");
               
                  double volumePercentual = (n.getVolumeDiario()*100/sistema.getCapacidade());
                  System.out.println("Volume Percentual: " + volumePercentual + "%");
                  
                  if(volumePercentual - mediaPercentual >= 5){
                     imgRelatorio = new ImageIcon("imagens/relatorioC.png");
                  }
                  if(mediaPercentual - volumePercentual >= 5){
                     imgRelatorio = new ImageIcon("imagens/relatorioD.png");
                  }
                  else{
                     imgRelatorio = new ImageIcon("imagens/relatorio.png");            
                  }
               }
               lblNomeSistema = new JLabel(sistema.getNome());
               txtDescricao = new JTextArea("\n\n" + sistema.getDescr());
               break;
            }
         }
         imgSistema = new ImageIcon("imagens/cantareira.png");
         lblNomeSistema.setFont(new Font("Arial", Font.BOLD, 16));
         lblImagem = new JLabel(imgSistema);
         lblRelatorio = new JLabel(imgRelatorio);
         txtDescricao.setFont(new Font("Arial", 0, 14));
         txtDescricao.setLineWrap(true);
         txtDescricao.setWrapStyleWord(true);
         sPDescricao = new JScrollPane(txtDescricao);
         sPDescricao.setBorder(null);
         sPDescricao.setOpaque(false);
         lblNiveis = new JLabel("Níveis Cantareira");
         lblNiveis.setFont(new Font("Arial", Font.BOLD, 14));
         lblNiveis.setForeground(Color.white);
         
         //re-adicionando
         pnlMeio.add(lblImagem);
         pnlMeio.add(lblRelatorio);
         pnlConteudo.add(lblNomeSistema);       
         pnlConteudo.add(pnlMeio);
         pnlConteudo.add(sPDescricao);
         pnlConteudo.add(pnlNiveis);
         
         //atualizando
         sPDescricao.revalidate();
         sPDescricao.repaint();
         pnlNiveis.revalidate();
         pnlNiveis.repaint();
         pnlConteudo.revalidate();
         pnlConteudo.repaint();
         pnlMeio.revalidate();
         pnlMeio.repaint();    
      }
      if(lista.equals("Alto Tietê")){
         pnlConteudo.remove(lblNomeSistema);
         pnlMeio.remove(lblRelatorio);
         pnlMeio.remove(lblImagem);
         pnlConteudo.remove(sPDescricao);
            
         double volumeB = 0;
         
         sistemas = new Sistemas();
         ArrayList<Sistemas> arrayS = new ArrayList<>();
         arrayS = sistemas.carregar(conn);
         
         for(Sistemas sistema : arrayS){
            if(sistema.getNome().equals("Alto Tietê")){
               ArrayList <Niveis> niveis = Niveis.trazerTodos(sistema,conn);
               modelo.setNumRows(0);
               for (Niveis n : niveis){
                  //////////
                  String x = String.format("%.02f",(n.getVolumeDiario()*100)/sistema.getCapacidade());
                  modelo.addRow(new Object[]{new SimpleDateFormat ("dd/mm/yyyy ").format(n.getData()),
                        String.format("%.02f",(n.getVolumeDiario())),x+"%"});
                  //////////
                  //RELATORIO
                  volumeB += n.getVolumeDiario()/niveis.size();
                  double mediaPercentual = (volumeB*100/sistema.getCapacidade());
                  System.out.println("Media Percentual: " + mediaPercentual + "%");
               
                  double volumePercentual = (n.getVolumeDiario()*100/sistema.getCapacidade());
                  System.out.println("Volume Percentual: " + volumePercentual + "%");
                    
                  if(volumePercentual - mediaPercentual >= 5){
                     imgRelatorio = new ImageIcon("imagens/relatorioC.png");
                  }
                  else if(mediaPercentual - volumePercentual >= 5){
                     imgRelatorio = new ImageIcon("imagens/relatorioD.png");
                  }
                  else{
                     imgRelatorio = new ImageIcon("imagens/relatorio.png");            
                  }
               }
               lblNomeSistema = new JLabel(sistema.getNome());
               txtDescricao = new JTextArea("\n\n" + sistema.getDescr());
               break;
            }
         }
         imgSistema = new ImageIcon("imagens/alto-tiete.png");         
         lblNomeSistema.setFont(new Font("Arial", Font.BOLD, 16));
         lblImagem = new JLabel(imgSistema);
         lblRelatorio = new JLabel(imgRelatorio);
         
         txtDescricao.setFont(new Font("Arial", 0, 14));
         txtDescricao.setLineWrap(true);
         txtDescricao.setWrapStyleWord(true);
         sPDescricao = new JScrollPane(txtDescricao);
         sPDescricao.setBorder(null);
         sPDescricao.setOpaque(false);
         lblNiveis = new JLabel("Níveis Alto Tietê");
         lblNiveis.setFont(new Font("Arial", Font.BOLD, 14));
         lblNiveis.setForeground(Color.white);
         
         //re-adicionando
         pnlMeio.add(lblImagem);
         pnlMeio.add(lblRelatorio);
         pnlConteudo.add(lblNomeSistema);       
         pnlConteudo.add(pnlMeio);
         pnlConteudo.add(sPDescricao);
         pnlConteudo.add(pnlNiveis);
         
         //atualizando
         sPDescricao.revalidate();
         sPDescricao.repaint();
         pnlNiveis.revalidate();
         pnlNiveis.repaint();
         pnlConteudo.revalidate();
         pnlConteudo.repaint();
         pnlMeio.revalidate();
         pnlMeio.repaint();
      }
   
      if(lista.equals("Guarapiranga")){
         pnlConteudo.remove(lblNomeSistema);
         pnlMeio.remove(lblRelatorio);
         pnlMeio.remove(lblImagem);
         pnlConteudo.remove(sPDescricao);
            
         double volumeB = 0;
                         
         sistemas = new Sistemas();
         ArrayList<Sistemas> arrayS = new ArrayList<>();
         arrayS = sistemas.carregar(conn);
         
         for(Sistemas sistema : arrayS){
            if(sistema.getNome().equals("Guarapiranga")){
               ArrayList <Niveis> niveis = Niveis.trazerTodos(sistema,conn);
               modelo.setNumRows(0);
               for (Niveis n : niveis){
                  //////////
                  String x = String.format("%.02f",(n.getVolumeDiario()*100)/sistema.getCapacidade());
                  modelo.addRow(new Object[]{new SimpleDateFormat ("dd/mm/yyyy ").format(n.getData()),
                        String.format("%.02f",(n.getVolumeDiario())),x+"%"});
                  //////////
                  //RELATORIO
                  volumeB += n.getVolumeDiario()/niveis.size();
                  double mediaPercentual = (volumeB*100/sistema.getCapacidade());
                  System.out.println("Media Percentual: " + mediaPercentual + "%");
               
                  double volumePercentual = (n.getVolumeDiario()*100/sistema.getCapacidade());
                  System.out.println("Volume Percentual: " + volumePercentual + "%");
                    
                  if(volumePercentual - mediaPercentual >= 5){
                     imgRelatorio = new ImageIcon("imagens/relatorioC.png");
                  }
                  else if(mediaPercentual - volumePercentual >= 5){
                     imgRelatorio = new ImageIcon("imagens/relatorioD.png");
                  }
                  else{
                     imgRelatorio = new ImageIcon("imagens/relatorio.png");            
                  }
               }
               lblNomeSistema = new JLabel(sistema.getNome());
               txtDescricao = new JTextArea("\n\n" + sistema.getDescr());
               break;
            }
         }
         imgSistema = new ImageIcon("imagens/guarapiranga.png");         
         lblNomeSistema.setFont(new Font("Arial", Font.BOLD, 16));
         lblImagem = new JLabel(imgSistema);
         lblRelatorio = new JLabel(imgRelatorio);
         
         txtDescricao.setFont(new Font("Arial", 0, 14));
         txtDescricao.setLineWrap(true);
         txtDescricao.setWrapStyleWord(true);
         sPDescricao = new JScrollPane(txtDescricao);
         sPDescricao.setBorder(null);
         sPDescricao.setOpaque(false);
         lblNiveis = new JLabel("Níveis Guarapiranga");
         lblNiveis.setFont(new Font("Arial", Font.BOLD, 14));
         lblNiveis.setForeground(Color.white);
         
         //re-adicionando
         pnlMeio.add(lblImagem);
         pnlMeio.add(lblRelatorio);
         pnlConteudo.add(lblNomeSistema);       
         pnlConteudo.add(pnlMeio);
         pnlConteudo.add(sPDescricao);
         pnlConteudo.add(pnlNiveis);
         
         //atualizando
         sPDescricao.revalidate();
         sPDescricao.repaint();
         pnlNiveis.revalidate();
         pnlNiveis.repaint();
         pnlConteudo.revalidate();
         pnlConteudo.repaint();
         pnlMeio.revalidate();
         pnlMeio.repaint();
      }
      if(lista.equals("Alto Cotia")){
         pnlConteudo.remove(lblNomeSistema);
         pnlMeio.remove(lblRelatorio);
         pnlMeio.remove(lblImagem);
         pnlConteudo.remove(sPDescricao);
            
         double volumeB = 0;
         
         sistemas = new Sistemas();         
         ArrayList<Sistemas> arrayS = new ArrayList<>();
         arrayS = sistemas.carregar(conn);
         
         for(Sistemas sistema : arrayS){
            if(sistema.getNome().equals("Alto Cotia")){
               ArrayList <Niveis> niveis = Niveis.trazerTodos(sistema,conn);
               modelo.setNumRows(0);
               for (Niveis n : niveis){
                  //////////
                  String x = String.format("%.02f",(n.getVolumeDiario()*100)/sistema.getCapacidade());
                  modelo.addRow(new Object[]{new SimpleDateFormat ("dd/mm/yyyy ").format(n.getData()),
                        String.format("%.02f",(n.getVolumeDiario())),x+"%"});
                  //////////
                  //RELATORIO
                  volumeB += n.getVolumeDiario()/niveis.size();
                  double mediaPercentual = (volumeB*100/sistema.getCapacidade());
                  System.out.println("Media Percentual: " + mediaPercentual + "%");
               
                  double volumePercentual = (n.getVolumeDiario()*100/sistema.getCapacidade());
                  System.out.println("Volume Percentual: " + volumePercentual + "%");
                    
                  if(volumePercentual - mediaPercentual >= 5){
                     imgRelatorio = new ImageIcon("imagens/relatorioC.png");
                  }
                  else if(mediaPercentual - volumePercentual >= 5){
                     imgRelatorio = new ImageIcon("imagens/relatorioD.png");
                  }
                  else{
                     imgRelatorio = new ImageIcon("imagens/relatorio.png");            
                  }
               }
               lblNomeSistema = new JLabel(sistema.getNome());
               txtDescricao = new JTextArea("\n\n" + sistema.getDescr());
               break;
            }
         }
         imgSistema = new ImageIcon("imagens/alto-cotia.png");         
         lblNomeSistema.setFont(new Font("Arial", Font.BOLD, 16));
         lblImagem = new JLabel(imgSistema);
         lblRelatorio = new JLabel(imgRelatorio);
         
         txtDescricao.setFont(new Font("Arial", 0, 14));
         txtDescricao.setLineWrap(true);
         txtDescricao.setWrapStyleWord(true);
         sPDescricao = new JScrollPane(txtDescricao);
         sPDescricao.setBorder(null);
         sPDescricao.setOpaque(false);
         lblNiveis = new JLabel("Níveis Alto Cotia");
         lblNiveis.setFont(new Font("Arial", Font.BOLD, 14));
         lblNiveis.setForeground(Color.white);
         
         //re-adicionando
         pnlMeio.add(lblImagem);
         pnlMeio.add(lblRelatorio);
         pnlConteudo.add(lblNomeSistema);       
         pnlConteudo.add(pnlMeio);
         pnlConteudo.add(sPDescricao);
         pnlConteudo.add(pnlNiveis);
         
         //atualizando
         sPDescricao.revalidate();
         sPDescricao.repaint();
         pnlNiveis.revalidate();
         pnlNiveis.repaint();
         pnlConteudo.revalidate();
         pnlConteudo.repaint();
         pnlMeio.revalidate();
         pnlMeio.repaint();
      }
      if(lista.equals("Rio Grande")){
         pnlConteudo.remove(lblNomeSistema);
         pnlMeio.remove(lblRelatorio);
         pnlMeio.remove(lblImagem);
         pnlConteudo.remove(sPDescricao);
            
         double volumeB = 0;
                         
         sistemas = new Sistemas();
         ArrayList<Sistemas> arrayS = new ArrayList<>();
         arrayS = sistemas.carregar(conn);
         
         for(Sistemas sistema : arrayS){
            if(sistema.getNome().equals("Rio Grande")){
               ArrayList <Niveis> niveis = Niveis.trazerTodos(sistema,conn);
               modelo.setNumRows(0);
               for (Niveis n : niveis){
                  //////////
                  String x = String.format("%.02f",(n.getVolumeDiario()*100)/sistema.getCapacidade());
                  modelo.addRow(new Object[]{new SimpleDateFormat ("dd/mm/yyyy ").format(n.getData()),
                        String.format("%.02f",(n.getVolumeDiario())),x+"%"});
                  //////////
                  //RELATORIO
                  volumeB += n.getVolumeDiario()/niveis.size();
                  double mediaPercentual = (volumeB*100/sistema.getCapacidade());
                  System.out.println("Media Percentual: " + mediaPercentual + "%");
               
                  double volumePercentual = (n.getVolumeDiario()*100/sistema.getCapacidade());
                  System.out.println("Volume Percentual: " + volumePercentual + "%");
                    
                  if(volumePercentual - mediaPercentual >= 5){
                     imgRelatorio = new ImageIcon("imagens/relatorioC.png");
                  }
                  else if(mediaPercentual - volumePercentual >= 5){
                     imgRelatorio = new ImageIcon("imagens/relatorioD.png");
                  }
                  else{
                     imgRelatorio = new ImageIcon("imagens/relatorio.png");            
                  }
               }
               lblNomeSistema = new JLabel(sistema.getNome());
               txtDescricao = new JTextArea("\n\n" + sistema.getDescr());
               break;
            }
         }
         imgSistema = new ImageIcon("imagens/rio-grande.png");         
         lblNomeSistema.setFont(new Font("Arial", Font.BOLD, 16));
         lblImagem = new JLabel(imgSistema);
         lblRelatorio = new JLabel(imgRelatorio);
         
         txtDescricao.setFont(new Font("Arial", 0, 14));
         txtDescricao.setLineWrap(true);
         txtDescricao.setWrapStyleWord(true);
         sPDescricao = new JScrollPane(txtDescricao);
         sPDescricao.setBorder(null);
         sPDescricao.setOpaque(false);
         lblNiveis = new JLabel("Níveis Rio Grande");
         lblNiveis.setFont(new Font("Arial", Font.BOLD, 14));
         lblNiveis.setForeground(Color.white);
         
         //re-adicionando
         pnlMeio.add(lblImagem);
         pnlMeio.add(lblRelatorio);
         pnlConteudo.add(lblNomeSistema);       
         pnlConteudo.add(pnlMeio);
         pnlConteudo.add(sPDescricao);
         pnlConteudo.add(pnlNiveis);
         
         //atualizando
         sPDescricao.revalidate();
         sPDescricao.repaint();
         pnlNiveis.revalidate();
         pnlNiveis.repaint();
         pnlConteudo.revalidate();
         pnlConteudo.repaint();
         pnlMeio.revalidate();
         pnlMeio.repaint();
      }
      if(lista.equals("Rio Claro")){
         pnlConteudo.remove(lblNomeSistema);
         pnlMeio.remove(lblRelatorio);
         pnlMeio.remove(lblImagem);
         pnlConteudo.remove(sPDescricao);
            
         double volumeB = 0;
                      
         sistemas = new Sistemas();
         ArrayList<Sistemas> arrayS = new ArrayList<>();
         arrayS = sistemas.carregar(conn);
         
         for(Sistemas sistema : arrayS){
            if(sistema.getNome().equals("Rio Claro")){
               ArrayList <Niveis> niveis = Niveis.trazerTodos(sistema,conn);
               modelo.setNumRows(0);
               for (Niveis n : niveis){
                  //////////
                  String x = String.format("%.02f",(n.getVolumeDiario()*100)/sistema.getCapacidade());
                  modelo.addRow(new Object[]{new SimpleDateFormat ("dd/mm/yyyy ").format(n.getData()),
                        String.format("%.02f",(n.getVolumeDiario())),x+"%"});
                  //////////
                  //RELATORIO
                  volumeB += n.getVolumeDiario()/niveis.size();
                  double mediaPercentual = (volumeB*100/sistema.getCapacidade());
                  System.out.println("Media Percentual: " + mediaPercentual + "%");
               
                  double volumePercentual = (n.getVolumeDiario()*100/sistema.getCapacidade());
                  System.out.println("Volume Percentual: " + volumePercentual + "%");
                    
                  if(volumePercentual - mediaPercentual >= 5){
                     imgRelatorio = new ImageIcon("imagens/relatorioC.png");
                  }
                  else if(mediaPercentual - volumePercentual >= 5){
                     imgRelatorio = new ImageIcon("imagens/relatorioD.png");
                  }
                  else{
                     imgRelatorio = new ImageIcon("imagens/relatorio.png");            
                  }
               }
               lblNomeSistema = new JLabel(sistema.getNome());
               txtDescricao = new JTextArea("\n\n" + sistema.getDescr());
               break;
            }
         }
         imgSistema = new ImageIcon("imagens/rio-claro.png");         
         lblNomeSistema.setFont(new Font("Arial", Font.BOLD, 16));
         lblImagem = new JLabel(imgSistema);
         lblRelatorio = new JLabel(imgRelatorio);
         
         txtDescricao.setFont(new Font("Arial", 0, 14));
         txtDescricao.setLineWrap(true);
         txtDescricao.setWrapStyleWord(true);
         sPDescricao = new JScrollPane(txtDescricao);
         sPDescricao.setBorder(null);
         sPDescricao.setOpaque(false);
         lblNiveis = new JLabel("Níveis Rio Claro");
         lblNiveis.setFont(new Font("Arial", Font.BOLD, 14));
         lblNiveis.setForeground(Color.white);
         
         //re-adicionando
         pnlMeio.add(lblImagem);
         pnlMeio.add(lblRelatorio);
         pnlConteudo.add(lblNomeSistema);       
         pnlConteudo.add(pnlMeio);
         pnlConteudo.add(sPDescricao);
         pnlConteudo.add(pnlNiveis);
         
         //atualizando
         sPDescricao.revalidate();
         sPDescricao.repaint();
         pnlNiveis.revalidate();
         pnlNiveis.repaint();
         pnlConteudo.revalidate();
         pnlConteudo.repaint();
         pnlMeio.revalidate();
         pnlMeio.repaint();
      }
   }
}