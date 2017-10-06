import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
//////////////////////////////////
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
//////////////////////////////////
public class Atualizar extends JFrame implements ActionListener{
   ////////////////////////
   private JTable tabela;
   private JScrollPane barra;
   private DefaultTableModel modelo = new DefaultTableModel();
   ////////////////////////
   private SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
   private Connection conn;
   private JButton btnHome, btnVoltar, btnAtualizar, btnEditar, btnExcluir;
   private JLabel lblTitulo, lblDivisoria, lblNomeSistema, lblData, lblNivel, lblNiveis, lblRodape, lblConteudoNiveis, lblTesteNiveis;
   private JTextField txtData, txtNivel;
   private ImageIcon imgHome, imgVoltar, imgDivisoria, imgSistema;
   private JComboBox cb;
   private JPanel pnlTopo, pnlBotoes, pnlLateral, pnlCombo, pnlDivisoria, pnlConteudo, pnlNome, pnlAtualiza; 
   private JPanel pnlBotaoAtualizar, pnlNiveis, pnlRodape;
   private Sistemas sistemas;
   private ArrayList<Sistemas> lista;

   public Atualizar(Connection conn){
      //titulo do frame
      super("Sistema de Gerenciamento de Mananciais - Atualizar");
      
      this.conn = conn;
      
      cb = new JComboBox();
      sistemas = new Sistemas();
      lista = sistemas.carregar(conn);
      //carrega comboBox
      for (int i = 0; i < lista.size(); i++){
         cb.addItem(lista.get(i).getNome());
      }

      //instanciando os botoes com suas caracteristicas
      imgHome = new ImageIcon("imagens/home.png");//carregando icon do btnHome
      btnHome = new JButton(imgHome);//coloca icon no botao
      btnHome.setPreferredSize(new Dimension(32, 32));//set tamanho
      btnHome.setOpaque(false);
      btnHome.setContentAreaFilled(false);
      btnHome.setBorderPainted(false);
      imgVoltar = new ImageIcon("imagens/voltar.png");//carregando icon do btnVoltar
      btnVoltar = new JButton(imgVoltar);//coloca icon no botao
      btnVoltar.setPreferredSize(new Dimension(32, 32));//set tamanho
      btnVoltar.setOpaque(false);
      btnVoltar.setContentAreaFilled(false);
      btnVoltar.setBorderPainted(false);
      //instanciando Label com suas caracteristicas 
      imgDivisoria = new ImageIcon("imagens/divisoria.png");
      lblDivisoria = new JLabel(imgDivisoria);
      lblDivisoria.setPreferredSize(new Dimension(5, 500));//set tamanho
      lblTitulo = new JLabel("Atualizar");
      lblTitulo.setFont( new Font( "Arial", Font.BOLD, 20 ) );//set fonte 
      lblTitulo.setForeground(Color.blue);//set cor da fonte
      lblNomeSistema = new JLabel("Nome do Sistema");
      lblNomeSistema.setFont( new Font("Arial", Font.BOLD, 16));
      lblData = new JLabel("Data: ");
      lblNivel = new JLabel("Nível: ");
      btnAtualizar = new JButton("Inserir");
      btnEditar = new JButton("Editar");
      btnExcluir = new JButton("Excluir");
      lblNiveis = new JLabel("Níveis");
      lblNiveis.setFont( new Font("Arial", Font.BOLD, 16));
      lblNiveis.setForeground(Color.white);
      lblRodape = new JLabel("Sistema de Gerenciamento de Mananciais - SEM CHAVE NÃO COMPILA (USJT) ©2016");
      
      //instanciando TextField
      txtData = new JTextField(8);
      txtNivel = new JTextField(6);
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
      pnlNome = new JPanel(new FlowLayout(1));
      pnlNome.setPreferredSize(new Dimension(390, 10));
      pnlAtualiza = new JPanel(new FlowLayout(1));
      pnlAtualiza.setPreferredSize(new Dimension(200, 25));
      pnlBotaoAtualizar = new JPanel(new FlowLayout());
      pnlNiveis = new JPanel(new FlowLayout(1));
      pnlNiveis.setPreferredSize(new Dimension(250, 10));
      pnlRodape = new JPanel(new FlowLayout());
      
      //cor de fundo
      pnlBotoes.setBackground(Color.white);
      pnlTopo.setBackground(Color.white);
      pnlLateral.setBackground(Color.white);
      pnlDivisoria.setBackground(Color.white);
      pnlConteudo.setBackground(Color.white);
      pnlNome.setBackground(Color.white);
      pnlBotaoAtualizar.setBackground(Color.white);
      pnlAtualiza.setBackground(Color.white);
      pnlNiveis.setBackground(Color.gray);
      pnlRodape.setBackground(Color.white);
            
      //adicionar aos paineis
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
      pnlNiveis.add(lblNiveis);
      pnlBotaoAtualizar.add(btnExcluir);      
      pnlBotaoAtualizar.add(btnAtualizar);
      pnlBotaoAtualizar.add(btnEditar);
      pnlNome.add(lblNomeSistema);
      pnlAtualiza.add(lblData);
      pnlAtualiza.add(txtData);
      pnlAtualiza.add(lblNivel);
      pnlAtualiza.add(txtNivel);
      pnlConteudo.add(pnlNome);
      pnlConteudo.add(pnlAtualiza);
      pnlConteudo.add(pnlBotaoAtualizar);
      pnlConteudo.add(pnlNiveis);
      pnlRodape.add(lblRodape);
      
      //adicionar a tela
      tela.add(pnlTopo, BorderLayout.NORTH);
      tela.add(pnlLateral, BorderLayout.WEST);
      tela.add(pnlDivisoria, BorderLayout.CENTER);
      tela.add(pnlConteudo, BorderLayout.EAST);
      tela.add(pnlRodape, BorderLayout.SOUTH);
      
      //eventos ComboBox
      cb.setSelectedIndex(-1);
      
      cb.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            cbActionPerformed(e);
         }
      });
      
      //eventos botões
      btnHome.addActionListener(this);
      btnVoltar.addActionListener(this);
      btnAtualizar.addActionListener(this);
      btnEditar.addActionListener(this);
      btnExcluir.addActionListener(this);      
      
      //ocultando na primeira exibição
      pnlBotaoAtualizar.setVisible(false);
      pnlAtualiza.setVisible(false);
      pnlNome.setVisible(false);
      pnlNiveis.setVisible(false);
      
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
      modelo.addColumn("ID");    
		modelo.addColumn("DATA");
		modelo.addColumn("NÍVEL(x10^6 m³)");
      modelo.addColumn("PERCENTUAL");
		modelo.setNumRows(0);
      }
   //////////////
   
   public void actionPerformed(ActionEvent e){
      if (e.getSource() == btnHome){
         this.dispose();
         HomeFrame telaInicial = new HomeFrame(conn);
      }
      if (e.getSource() == btnVoltar){
         this.dispose();
         AtualizarLogin atualizarLogin = new AtualizarLogin(conn);
      }
      if(e.getSource() == btnAtualizar){
         Niveis niveis = new Niveis();
         try{
            niveis.setData(sdf.parse(txtData.getText()));
         }
         catch (Exception ex){
            JOptionPane.showMessageDialog (null, "Data em formato inválido");
            return;
         }
         try{
            niveis.setVolumeDiario(Double.parseDouble(txtNivel.getText()));
         }
         catch(Exception ex){
            JOptionPane.showMessageDialog (null, "Nível inválido");
            return;
         }
         if(lblNomeSistema.getText().equals("Cantareira")){
            niveis.setPkIdSistemas(1);
         }
         
         if(lblNomeSistema.getText().equals("Alto Tietê")){
            niveis.setPkIdSistemas(2);
         }
         
         if(lblNomeSistema.getText().equals("Guarapiranga")) {
            niveis.setPkIdSistemas(3);
         }
         
         if(lblNomeSistema.getText().equals("Alto Cotia")) {
            niveis.setPkIdSistemas(4);
         }
         
         if(lblNomeSistema.getText().equals("Rio Grande")) {
            niveis.setPkIdSistemas(5);
         }
         
         if(lblNomeSistema.getText().equals("Rio Claro")) {
            niveis.setPkIdSistemas(6);
         }
         
         JOptionPane.showMessageDialog(null, "Nível cadastrado com sucesso!");
         txtData.setText("");
         txtNivel.setText("");
         
         try{
            conn.setAutoCommit(false);
            niveis.incluir(conn);
            conn.commit();
         }
         catch(Exception e1){
            e1.printStackTrace();
         }
      }/////////editar-editar
      if(e.getSource() == btnEditar){
         Niveis niveis = new Niveis();
         try{
            niveis.setData(sdf.parse(txtData.getText()));
         }
         catch (Exception ex){
            JOptionPane.showMessageDialog (null, "Data em formato inválido");
            return;
         }
         try{
            niveis.setVolumeDiario(Double.parseDouble(txtNivel.getText()));
         }
         catch(Exception ex){
            JOptionPane.showMessageDialog (null, "Nível inválido");
            return;
         }
         
         int id = Integer.parseInt(JOptionPane.showInputDialog("Insira o ID para editar."));
         niveis.setIdNiveis(id);
         
         if(lblNomeSistema.getText().equals("Cantareira")){
            niveis.setPkIdSistemas(1);
         }
         
         if(lblNomeSistema.getText().equals("Alto Tietê")){
            niveis.setPkIdSistemas(2);
         }
         
         if(lblNomeSistema.getText().equals("Guarapiranga")) {
            niveis.setPkIdSistemas(3);
         }
         
         if(lblNomeSistema.getText().equals("Alto Cotia")) {
            niveis.setPkIdSistemas(4);
         }
         
         if(lblNomeSistema.getText().equals("Rio Grande")) {
            niveis.setPkIdSistemas(5);
         }
         
         if(lblNomeSistema.getText().equals("Rio Claro")) {
            niveis.setPkIdSistemas(6);
         }
         
         JOptionPane.showMessageDialog(null, "Edição Realizada!");
         txtData.setText("");
         txtNivel.setText("");
         
         try{
            conn.setAutoCommit(false);
            niveis.atualizar(conn);
            conn.commit();
         }
         catch(Exception e1){
            e1.printStackTrace();
         }
      }///////////editar-editar
      if(e.getSource() == btnExcluir){
         Niveis niveis = new Niveis();
         try{
            int id = Integer.parseInt(JOptionPane.showInputDialog("Insira o ID para excluir."));
            niveis.setIdNiveis(id);
         }
         catch (Exception ex){
            JOptionPane.showMessageDialog (null, "Operação abortada");
            return;
         }
         JOptionPane.showMessageDialog(null, "Nível excluído com sucesso!");
         try{
            conn.setAutoCommit(false);
            niveis.excluir(conn);
            conn.commit();
         }
         catch(Exception e1){
            e1.printStackTrace();
         }
      }
   }
   
   public void cbActionPerformed(ActionEvent e) {
      pnlBotaoAtualizar.setVisible(true);
      pnlAtualiza.setVisible(true);
      pnlNome.setVisible(true);
      pnlNiveis.setVisible(true);
   
      String lista = (String)cb.getSelectedItem();
      
      if(lista.equals("Cantareira")){
         
         //removendo
         pnlBotaoAtualizar.remove(btnAtualizar);
         pnlAtualiza.remove(lblData);
         pnlAtualiza.remove(txtData);
         pnlAtualiza.remove(lblNivel);
         pnlAtualiza.remove(txtNivel);
         pnlConteudo.remove(pnlAtualiza);
         pnlConteudo.remove(pnlBotaoAtualizar);
         pnlNome.remove(lblNomeSistema);
         pnlNiveis.remove(lblNiveis);
         pnlConteudo.remove(pnlNome);         
         pnlConteudo.remove(pnlNiveis);
         
         ArrayList<Sistemas> arrayS = new ArrayList<>();
         arrayS = sistemas.carregar(conn);

         for(Sistemas sistema : arrayS){
            if(sistema.getNome().equals("Cantareira")){
               
               ArrayList <Niveis> niveis = Niveis.trazerTodos2(sistema,conn);
               modelo.setNumRows(0);
               for (Niveis n : niveis){
                  //////////
                  String x = String.format("%.02f",(n.getVolumeDiario()*100)/sistema.getCapacidade());
                  modelo.addRow(new Object[]{n.getIdNiveis(), new SimpleDateFormat ("dd/mm/yyyy ").format(n.getData()),
                      String.format("%.02f",(n.getVolumeDiario())),x+"%"});
                  //////////
               }
               lblNomeSistema = new JLabel(sistema.getNome());
               break;
            }
         }
         lblNomeSistema.setFont(new Font("Arial", Font.BOLD, 16));
         lblNiveis = new JLabel("Níveis Cantareira");
         lblNiveis.setFont(new Font("Arial", Font.BOLD, 14));
         lblNiveis.setForeground(Color.white);
         
         //re-adicionando         
         pnlNome.add(lblNomeSistema);
         pnlConteudo.add(pnlNome);
         pnlAtualiza.add(lblData);
         pnlAtualiza.add(txtData);
         pnlAtualiza.add(lblNivel);
         pnlAtualiza.add(txtNivel);
         pnlConteudo.add(pnlAtualiza);
         pnlBotaoAtualizar.add(btnAtualizar);         
         pnlConteudo.add(pnlBotaoAtualizar);
         pnlNiveis.add(lblNiveis);
         pnlConteudo.add(pnlNiveis);
         
         //atualizando
         pnlNiveis.revalidate();
         pnlNiveis.repaint();
         pnlNome.revalidate();
         pnlNome.repaint();
      }
      if(lista.equals("Alto Tietê")){
         
         //removendo
         pnlBotaoAtualizar.remove(btnAtualizar);
         pnlAtualiza.remove(lblData);
         pnlAtualiza.remove(txtData);
         pnlAtualiza.remove(lblNivel);
         pnlAtualiza.remove(txtNivel);
         pnlConteudo.remove(pnlAtualiza);
         pnlConteudo.remove(pnlBotaoAtualizar);
         pnlNome.remove(lblNomeSistema);
         pnlNiveis.remove(lblNiveis);
         pnlConteudo.remove(pnlNome);         
         pnlConteudo.remove(pnlNiveis);
         
         ArrayList<Sistemas> arrayS = new ArrayList<>();
         arrayS = sistemas.carregar(conn);

         for(Sistemas sistema : arrayS){
            if(sistema.getNome().equals("Alto Tietê")){
               
               ArrayList <Niveis> niveis = Niveis.trazerTodos2(sistema,conn);
               modelo.setNumRows(0);
               for (Niveis n : niveis){
                  //////////
                  String x = String.format("%.02f",(n.getVolumeDiario()*100)/sistema.getCapacidade());
                  modelo.addRow(new Object[]{n.getIdNiveis(), new SimpleDateFormat ("dd/mm/yyyy ").format(n.getData()),
                      String.format("%.02f",(n.getVolumeDiario())),x+"%"});
               }
               lblNomeSistema = new JLabel(sistema.getNome());
               break;
            }
         }
         //sets
         lblNomeSistema.setFont(new Font("Arial", Font.BOLD, 16));
         lblNiveis = new JLabel("Níveis Alto Tietê");
         lblNiveis.setFont(new Font("Arial", Font.BOLD, 14));
         lblNiveis.setForeground(Color.white);
         
         //re-adicionando         
         pnlNome.add(lblNomeSistema);
         pnlConteudo.add(pnlNome);
         pnlAtualiza.add(lblData);
         pnlAtualiza.add(txtData);
         pnlAtualiza.add(lblNivel);
         pnlAtualiza.add(txtNivel);
         pnlConteudo.add(pnlAtualiza);
         pnlBotaoAtualizar.add(btnAtualizar);         
         pnlConteudo.add(pnlBotaoAtualizar);
         pnlNiveis.add(lblNiveis);
         pnlConteudo.add(pnlNiveis);
         
         //atualizando
         pnlNiveis.revalidate();
         pnlNiveis.repaint();
         pnlNome.revalidate();
         pnlNome.repaint();
      }
      if(lista.equals("Guarapiranga")){
         
         //removendo
         pnlBotaoAtualizar.remove(btnAtualizar);
         pnlAtualiza.remove(lblData);
         pnlAtualiza.remove(txtData);
         pnlAtualiza.remove(lblNivel);
         pnlAtualiza.remove(txtNivel);
         pnlConteudo.remove(pnlAtualiza);
         pnlConteudo.remove(pnlBotaoAtualizar);
         pnlNome.remove(lblNomeSistema);
         pnlNiveis.remove(lblNiveis);
         pnlConteudo.remove(pnlNome);         
         pnlConteudo.remove(pnlNiveis);
         
         ArrayList<Sistemas> arrayS = new ArrayList<>();
         arrayS = sistemas.carregar(conn);

         for(Sistemas sistema : arrayS){
            if(sistema.getNome().equals("Guarapiranga")){
               
               ArrayList <Niveis> niveis = Niveis.trazerTodos2(sistema,conn);
               modelo.setNumRows(0);
               for (Niveis n : niveis){
                  //////////
                  String x = String.format("%.02f",(n.getVolumeDiario()*100)/sistema.getCapacidade());
                  modelo.addRow(new Object[]{n.getIdNiveis(), new SimpleDateFormat ("dd/mm/yyyy ").format(n.getData()),
                      String.format("%.02f",(n.getVolumeDiario())),x+"%"}); 
               }
               lblNomeSistema = new JLabel(sistema.getNome());
               break;
            }
         }
         //sets
         lblNomeSistema.setFont(new Font("Arial", Font.BOLD, 16));
         lblNiveis = new JLabel("Níveis Guarapiranga");
         lblNiveis.setFont(new Font("Arial", Font.BOLD, 14));
         lblNiveis.setForeground(Color.white);
         
         //re-adicionando         
         pnlNome.add(lblNomeSistema);
         pnlConteudo.add(pnlNome);
         pnlAtualiza.add(lblData);
         pnlAtualiza.add(txtData);
         pnlAtualiza.add(lblNivel);
         pnlAtualiza.add(txtNivel);
         pnlConteudo.add(pnlAtualiza);
         pnlBotaoAtualizar.add(btnAtualizar);         
         pnlConteudo.add(pnlBotaoAtualizar);
         pnlNiveis.add(lblNiveis);
         pnlConteudo.add(pnlNiveis);
         
         //atualizando
         pnlNiveis.revalidate();
         pnlNiveis.repaint();
         pnlNome.revalidate();
         pnlNome.repaint();
      }
      if(lista.equals("Alto Cotia")){
         
         //removendo
         pnlBotaoAtualizar.remove(btnAtualizar);
         pnlAtualiza.remove(lblData);
         pnlAtualiza.remove(txtData);
         pnlAtualiza.remove(lblNivel);
         pnlAtualiza.remove(txtNivel);
         pnlConteudo.remove(pnlAtualiza);
         pnlConteudo.remove(pnlBotaoAtualizar);
         pnlNome.remove(lblNomeSistema);
         pnlNiveis.remove(lblNiveis);
         pnlConteudo.remove(pnlNome);         
         pnlConteudo.remove(pnlNiveis);
         
         ArrayList<Sistemas> arrayS = new ArrayList<>();
         arrayS = sistemas.carregar(conn);

         for(Sistemas sistema : arrayS){
            if(sistema.getNome().equals("Alto Cotia")){
               
               ArrayList <Niveis> niveis = Niveis.trazerTodos2(sistema,conn);
               modelo.setNumRows(0);
               for (Niveis n : niveis){
                  //////////
                  String x = String.format("%.02f",(n.getVolumeDiario()*100)/sistema.getCapacidade());
                  modelo.addRow(new Object[]{n.getIdNiveis(), new SimpleDateFormat ("dd/mm/yyyy ").format(n.getData()),
                      String.format("%.02f",(n.getVolumeDiario())),x+"%"});                
               }               
               lblNomeSistema = new JLabel(sistema.getNome());
               break;
            }
         }
         //sets
         lblNomeSistema.setFont(new Font("Arial", Font.BOLD, 16));
         lblNiveis = new JLabel("Níveis Alto Cotia");
         lblNiveis.setFont(new Font("Arial", Font.BOLD, 14));
         lblNiveis.setForeground(Color.white);
         
         //re-adicionando         
         pnlNome.add(lblNomeSistema);
         pnlConteudo.add(pnlNome);
         pnlAtualiza.add(lblData);
         pnlAtualiza.add(txtData);
         pnlAtualiza.add(lblNivel);
         pnlAtualiza.add(txtNivel);
         pnlConteudo.add(pnlAtualiza);
         pnlBotaoAtualizar.add(btnAtualizar);         
         pnlConteudo.add(pnlBotaoAtualizar);
         pnlNiveis.add(lblNiveis);
         pnlConteudo.add(pnlNiveis);
         
         //atualizando
         pnlNiveis.revalidate();
         pnlNiveis.repaint();
         pnlNome.revalidate();
         pnlNome.repaint();
      }
      if(lista.equals("Rio Grande")){
         
         //removendo
         pnlBotaoAtualizar.remove(btnAtualizar);
         pnlAtualiza.remove(lblData);
         pnlAtualiza.remove(txtData);
         pnlAtualiza.remove(lblNivel);
         pnlAtualiza.remove(txtNivel);
         pnlConteudo.remove(pnlAtualiza);
         pnlConteudo.remove(pnlBotaoAtualizar);
         pnlNome.remove(lblNomeSistema);
         pnlNiveis.remove(lblNiveis);
         pnlConteudo.remove(pnlNome);         
         pnlConteudo.remove(pnlNiveis);
         
         ArrayList<Sistemas> arrayS = new ArrayList<>();
         arrayS = sistemas.carregar(conn);

         for(Sistemas sistema : arrayS){
            if(sistema.getNome().equals("Rio Grande")){
               
               ArrayList <Niveis> niveis = Niveis.trazerTodos2(sistema,conn);
               modelo.setNumRows(0);
               for (Niveis n : niveis){
                  //////////
                  String x = String.format("%.02f",(n.getVolumeDiario()*100)/sistema.getCapacidade());
                  modelo.addRow(new Object[]{n.getIdNiveis(), new SimpleDateFormat ("dd/mm/yyyy ").format(n.getData()),
                      String.format("%.02f",(n.getVolumeDiario())),x+"%"});         
               }
               lblNomeSistema = new JLabel(sistema.getNome());
               break;
            }
         }
         //sets
         lblNomeSistema.setFont(new Font("Arial", Font.BOLD, 16));
         lblNiveis = new JLabel("Níveis Rio Grande");
         lblNiveis.setFont(new Font("Arial", Font.BOLD, 14));
         lblNiveis.setForeground(Color.white);
         
         //re-adicionando         
         pnlNome.add(lblNomeSistema);
         pnlConteudo.add(pnlNome);
         pnlAtualiza.add(lblData);
         pnlAtualiza.add(txtData);
         pnlAtualiza.add(lblNivel);
         pnlAtualiza.add(txtNivel);
         pnlConteudo.add(pnlAtualiza);
         pnlBotaoAtualizar.add(btnAtualizar);         
         pnlConteudo.add(pnlBotaoAtualizar);
         pnlNiveis.add(lblNiveis);
         pnlConteudo.add(pnlNiveis);
         
         //atualizando
         pnlNiveis.revalidate();
         pnlNiveis.repaint();
         pnlNome.revalidate();
         pnlNome.repaint();
      }
      if(lista.equals("Rio Claro")){
         
         //removendo
         pnlBotaoAtualizar.remove(btnAtualizar);
         pnlAtualiza.remove(lblData);
         pnlAtualiza.remove(txtData);
         pnlAtualiza.remove(lblNivel);
         pnlAtualiza.remove(txtNivel);
         pnlConteudo.remove(pnlAtualiza);
         pnlConteudo.remove(pnlBotaoAtualizar);
         pnlNome.remove(lblNomeSistema);
         pnlNiveis.remove(lblNiveis);
         pnlConteudo.remove(pnlNome);         
         pnlConteudo.remove(pnlNiveis);
         
         ArrayList<Sistemas> arrayS = new ArrayList<>();
         arrayS = sistemas.carregar(conn);

         for(Sistemas sistema : arrayS){
            if(sistema.getNome().equals("Rio Claro")){
               
               ArrayList <Niveis> niveis = Niveis.trazerTodos2(sistema,conn);
               modelo.setNumRows(0);
               for (Niveis n : niveis){
                  //////////
                  String x = String.format("%.02f",(n.getVolumeDiario()*100)/sistema.getCapacidade());
                  modelo.addRow(new Object[]{n.getIdNiveis(), new SimpleDateFormat ("dd/mm/yyyy ").format(n.getData()),
                      String.format("%.02f",(n.getVolumeDiario())),x+"%"});                
               }
               lblNomeSistema = new JLabel(sistema.getNome());
               break;
            }
         }        
         //sets
         lblNomeSistema.setFont(new Font("Arial", Font.BOLD, 16));
         lblNiveis = new JLabel("Níveis Rio Claro");
         lblNiveis.setFont(new Font("Arial", Font.BOLD, 14));
         lblNiveis.setForeground(Color.white);
         
         //re-adicionando         
         pnlNome.add(lblNomeSistema);
         pnlConteudo.add(pnlNome);
         pnlAtualiza.add(lblData);
         pnlAtualiza.add(txtData);
         pnlAtualiza.add(lblNivel);
         pnlAtualiza.add(txtNivel);
         pnlConteudo.add(pnlAtualiza);
         pnlBotaoAtualizar.add(btnAtualizar);         
         pnlConteudo.add(pnlBotaoAtualizar);
         pnlNiveis.add(lblNiveis);
         pnlConteudo.add(pnlNiveis);
         
         //atualizando
         pnlNiveis.revalidate();
         pnlNiveis.repaint();
         pnlNome.revalidate();
         pnlNome.repaint();
      }
   }
}