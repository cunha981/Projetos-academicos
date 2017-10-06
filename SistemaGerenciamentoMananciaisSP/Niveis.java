import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Niveis {
   private int idNiveis;
   private double volumeDiario;
   private Date data;
   private int pkIdSistemas;
   
   public Niveis() {
   }
   public Niveis(int idNiveis) {
      this.idNiveis = idNiveis;
   }
   public Niveis(int idNiveis, double volumeDiario, Date data, int pkIdSistemas) {
      this.idNiveis = idNiveis;
      this.volumeDiario = volumeDiario;
      this.data = data;
      this.pkIdSistemas = pkIdSistemas;
   } 
   //métodos de acesso
   public int getIdNiveis() {
      return idNiveis;
   }
   public double getVolumeDiario() {
      return volumeDiario;
   }
   public Date getData() {
      return data;
   }
   public int getPkIdSistemas() {
      return pkIdSistemas;
   }
   
   //métodos modificadores
   public void setIdNiveis(int idNiveis) {
      this.idNiveis = idNiveis;
   }
   public void setVolumeDiario(double volumeDiario) {
      this.volumeDiario = volumeDiario;
   }
   public void setData(Date data) {
      this.data = data;
   }
   public void setPkIdSistemas(int pkIdSistemas) {
      this.pkIdSistemas = pkIdSistemas;
   }
   
   //métodos de acesso ao BD
   //INSERT
   public void incluir(Connection conn) {

      String sqlInsert = "INSERT INTO niveis(idniveis, volume_diario, data, sistemas_idsistemas) VALUES (?, ?, ?, ?)";
      
      try (PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
         stm.setInt(1, getIdNiveis());
         stm.setDouble(2, getVolumeDiario());
         stm.setDate(3, new java.sql.Date(getData().getTime()));
         stm.setInt(4, getPkIdSistemas());
         stm.execute();
      } 
      catch (Exception e) {
         e.printStackTrace();
         try {
            conn.rollback();
         } 
         catch (SQLException e1) {
            System.out.print(e1.getStackTrace());
         }
      }
   }
   
   //DELETE
   public void excluir(Connection conn) {
      
      String sqlDelete = "DELETE FROM niveis WHERE idniveis = ?";
      
      try (PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
         stm.setInt(1, getIdNiveis());
         stm.execute();
         setVolumeDiario(0);
         setData(null);
         setPkIdSistemas(0);
      } 
      catch (Exception e) {
         e.printStackTrace();
         try {
            conn.rollback();
         } 
         catch (SQLException e1) {
            System.out.print(e1.getStackTrace());
         }
      }
   }
   
   //UPDATE
   public void atualizar(Connection conn){
   	
      String sqlUpdate = "UPDATE niveis SET volume_diario=?, data=?, sistemas_idsistemas=? WHERE idniveis = ?";
      
      java.util.Date date = Calendar.getInstance().getTime();
      java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
      
      try (PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
         stm.setDouble(1, getVolumeDiario());
         stm.setDate(2,  new java.sql.Date(getData().getTime()));
         stm.setInt(3, getPkIdSistemas());
         stm.setInt(4, getIdNiveis());
         stm.execute();
         
      } 
      catch (Exception e) {
         e.printStackTrace();
         try {
            conn.rollback();
         } 
         catch (SQLException e1) {
            System.out.print(e1.getStackTrace());
         }
      }
   }
   //SELECT
   public static ArrayList <Niveis> trazerTodos (Sistemas sistema, Connection conn){
      
      String sqlSelect = "SELECT idniveis, volume_diario, data, sistemas_idsistemas FROM niveis WHERE sistemas_idsistemas = ? and data >= date_add(now(), interval -30 day) ORDER BY data";
   
      ArrayList <Niveis> niveis = new ArrayList <Niveis>();
      
      try (PreparedStatement stm = conn.prepareStatement(sqlSelect);){
         stm.setInt(1, sistema.getIdSistemas());
      	
         try (ResultSet rs = stm.executeQuery();) {
         	/*
         	 * este outro try é necessario pois nao dá para abrir o
         	 * resultset no anterior uma vez que antes era preciso configurar
         	 * o parâmetro via setInt; se não fosse, poderia se fazer tudo no
         	 * mesmo try
         	 */
            while(rs.next()) {  
               Niveis n = new Niveis ();
               try{
                  n.setIdNiveis(rs.getInt("idniveis"));
                  n.setVolumeDiario(rs.getDouble("volume_diario"));
                  n.setData(new java.text.SimpleDateFormat ("yyyy-mm-dd").parse(rs.getDate("data").toString()));
                  n.setPkIdSistemas(rs.getInt("sistemas_idsistemas"));
                  niveis.add(n);
               }
               catch (Exception e){ 
               }
            }
         } 
         catch (Exception e) {
            e.printStackTrace();
         }
      } 
      catch (SQLException e1) {
         System.out.print(e1.getStackTrace());
      }
      return niveis;
   }
   public static ArrayList <Niveis> trazerTodos2 (Sistemas sistema, Connection conn){
      
      String sqlSelect = "SELECT idniveis, volume_diario, data, sistemas_idsistemas FROM niveis WHERE sistemas_idsistemas = ? ORDER BY data DESC";
   
      ArrayList <Niveis> niveis = new ArrayList <Niveis>();
      
      try (PreparedStatement stm = conn.prepareStatement(sqlSelect);){
         stm.setInt(1, sistema.getIdSistemas());
      	
         try (ResultSet rs = stm.executeQuery();) {
         	/*
         	 * este outro try é necessario pois nao dá para abrir o
         	 * resultset no anterior uma vez que antes era preciso configurar
         	 * o parâmetro via setInt; se não fosse, poderia se fazer tudo no
         	 * mesmo try
         	 */
            while(rs.next()) {  
               Niveis n = new Niveis ();
               try{
                  n.setIdNiveis(rs.getInt("idniveis"));
                  n.setVolumeDiario(rs.getDouble("volume_diario"));
                  n.setData(new java.text.SimpleDateFormat ("yyyy-mm-dd").parse(rs.getDate("data").toString()));
                  n.setPkIdSistemas(rs.getInt("sistemas_idsistemas"));
                  niveis.add(n);
               }
               catch (Exception e){ 
               }
            }
         } 
         catch (Exception e) {
            e.printStackTrace();
         }
      } 
      catch (SQLException e1) {
         System.out.print(e1.getStackTrace());
      }
      return niveis;
   } 
   public String toString() {
      return "ID: " + getIdNiveis() + "\nVolume do Dia: " + getVolumeDiario() + "\nData: " + getData() + 
             "\nSistema relacionado: " + getPkIdSistemas();
   }
}