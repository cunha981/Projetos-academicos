import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Sistemas {
   private int idSistemas;
   private String nome;
   private String descr;
   private double capacidade;
   
   public Sistemas() {        
   
   } 
   public Sistemas(int idSistemas) {
      this.idSistemas = idSistemas;        
   }
   public Sistemas(int idSistemas, String nome, String descr, double capacidade) {
      this.idSistemas = idSistemas;        
      this.nome = nome;
      this.capacidade = capacidade;
   }
   //métodos de acesso   
   public int getIdSistemas() {
      return idSistemas;
   }
   public String getNome() {
      return nome;
   }
   public String getDescr() {
      return descr;
   }
   public double getCapacidade() {
      return capacidade;
   }
   //métodos modificadores
   public void setIdSistemas(int idSistemas) {
      this.idSistemas = idSistemas;
   }
   public void setNome(String nome) {
      this.nome = nome;
   }
   public void setDescr(String descr) {
      this.descr = descr;
   }
   public void setCapacidade(double capacidade) {
      this.capacidade = capacidade;
   }

   //métodos de acesso ao BD
   public ArrayList<Sistemas> carregar(Connection conn) {
   
      ArrayList<Sistemas> lista = new ArrayList<Sistemas>();
      
      String sqlSelect = "SELECT idsistemas, nome, descr, capacidade FROM sistemas;";  
      //cria um objeto que representa a instrução SQL
      try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
         //Executa o comando de consulta aonde guarda os dados retornados dentro do ResultSet
         ResultSet rs = stm.executeQuery();
         //Faz a verificação de enquanto conter registros, percorre e resgata os valores
         while (rs.next()) {
            Sistemas s = new Sistemas();
            s.setIdSistemas(rs.getInt("idsistemas"));
            s.setNome(rs.getString("nome"));
            s.setDescr(rs.getString("descr"));
            s.setCapacidade(rs.getDouble("capacidade"));
            lista.add(s);
         }
      } 
      catch (SQLException e1) {
         System.out.print(e1.getStackTrace());
      }
      return lista;
   }  
   public String toString() {
      return "ID do Sistema: " + getIdSistemas() + "\nNome do Sistema: " + getNome() + "\nDescrição: " + getDescr() + 
             "\nCapacidade: " + getCapacidade();
   }
}