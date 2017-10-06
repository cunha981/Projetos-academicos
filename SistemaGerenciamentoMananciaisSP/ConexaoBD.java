import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

   static {
      try {
         //Carrega o driver MySQL
         Class.forName("com.mysql.jdbc.Driver");
      } 
      catch (ClassNotFoundException e) {
         throw new RuntimeException(e);
      }
   }
   public Connection conectar() throws SQLException {
      String servidor = "localhost";
      String porta = "3306";
      String database = "niveis_abastecimento";
      String usuario = "alunos";
      String senha = "alunos";
      //abrir uma conexão através do método getConnection
      return DriverManager
         	.getConnection("jdbc:mysql://"+servidor+":"+porta+
            "/"+database+"?user="+usuario+"&password="+senha);
   }
}