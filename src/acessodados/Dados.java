package acessodados;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dados {
  protected Connection conn = null;
  // String de Conexão para o SQL Server
  // private String strConn = "jdbc:sqlserver://localhost;" +
  //   "databaseName=loja;userName=sa;password=clvo1268";
  // String de Conexao para o MySQL (MariaDB)
  protected String strConn = "jdbc:mariadb://localhost:3306/loja?" +
    "user=root&password=";
  
  protected boolean abrirConexao() {
    boolean sucesso;
    // Provedor para o SQL Server
    // String provedor = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    // Provedor para o MySQL (MariaDB)
    String provedor = "org.mariadb.jdbc.Driver";
    try { 
      Class.forName(provedor).newInstance();
      conn = DriverManager.getConnection(strConn);
      sucesso = true;
    }
    catch (Exception erro) {
      System.out.println("ERRO ao conectar ao BD!\n" + erro.getMessage());
      sucesso = false;
    }
    return sucesso;
  }
  
  protected void fecharConexao() {
    try {
      conn.close();
    }
    catch (Exception erro) {
      System.out.println("ERRO ao desconectar do BD!\n" + erro.getMessage());
    }
  }    
}
