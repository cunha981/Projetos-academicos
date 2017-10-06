import java.sql.Connection;
import java.sql.SQLException;

public class TesteSistema {
   public static void main(String[] args){
      // obtem conexao com o banco
      ConexaoBD bd = new ConexaoBD();
      try{
         Connection conn = bd.conectar();
         new HomeFrame(conn);
      } 
      catch (SQLException e){
         e.printStackTrace();
      }
   }
}