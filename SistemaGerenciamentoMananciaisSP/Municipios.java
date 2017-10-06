import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Municipios {
   private int idMunicipios;
   private String nome, nomeSistema;
   
   public Municipios(){
   }
   
   public Municipios(int idMunicipios){
      this.idMunicipios = idMunicipios;
   }
   public Municipios(int idMunicipios, String nome, String nomeSistema){
      this.idMunicipios = idMunicipios;
      this.nome = nome;
      this.nomeSistema = nomeSistema;
   }
   //métodos de acesso
   public int getIdMunicipios(){
      return idMunicipios;
   }
   public String getNome(){
      return nome;
   }
   public String getNomeSistema(){
      return nomeSistema;
   }
   //métodos modificadores
   public void setIdMunicipios(int idMunicipios){
      this.idMunicipios = idMunicipios;
   }
   public void setNome(String nome){
      this.nome = nome;
   }
   public void setNomeSistema(String nomeSistema){
      this.nomeSistema = nomeSistema;
   }
   //métodos de acesso ao BD
   //SELECT
   public ArrayList<Municipios> carregar(Connection conn){
   
      ArrayList<Municipios> lista = new ArrayList<Municipios>();
      
		String sqlSelect = "SELECT m.nome, s.nome from municipios_has_sistemas mhs inner join municipios m on mhs.municipios_idmunicipios = m.idmunicipios inner join sistemas s on mhs.sistemas_idsistemas = s.idsistemas where m.nome = '"+getNome()+"';";

		try (PreparedStatement stm = conn.prepareStatement(sqlSelect);){
         ResultSet rs = stm.executeQuery();
			while (rs.next()) {
         try{
            Municipios m = new Municipios();
            m.setNomeSistema(rs.getString("s.nome"));
				m.setNome(rs.getString("m.nome"));
            lista.add(m);
            }
            catch(Exception e){}
         }
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
      return lista;
   }
   //SELECT
   public ArrayList<Municipios> trazer(Connection conn){
   
      ArrayList<Municipios> lista = new ArrayList<Municipios>();
      
		String sqlSelect = "SELECT nome FROM municipios;";

		try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
         ResultSet rs = stm.executeQuery();
			while (rs.next()) {
            Municipios m = new Municipios();
				m.setNome(rs.getString("nome"));
            lista.add(m);
         }
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
      return lista;
   }
      public String toString(){
      return "Nome: " + getNome() + "\nNome do Sistema: " + getNomeSistema();
   }
}