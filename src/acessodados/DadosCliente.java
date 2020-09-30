package acessodados;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import negocios.Cliente;

public class DadosCliente extends Dados{
  public List consultar() {
    List clientes = new ArrayList();
    try {
      abrirConexao();
      Statement s = conn.createStatement();
      s.executeQuery("SELECT * FROM cliente");
      ResultSet rs = s.getResultSet();
      while (rs.next()) {
        Cliente c = new Cliente();
        c.setCpf(rs.getString("cpf"));
        c.setNome(rs.getString("nome"));
        c.setTelefone(rs.getString("telefone"));
        clientes.add(c);
      }
      rs.close();
      fecharConexao();
    }
    catch (Exception erro) {
      System.out.println("ERRO ao consultar o BD!\n" + erro.getMessage());  
    }
    return clientes;
  } 
  
  public Cliente consultar(String cpf) {
    Cliente c = new Cliente();
    try {
      abrirConexao();
      Statement s = conn.createStatement();
      s.executeQuery("SELECT * FROM cliente WHERE cpf = '" + cpf + "'");
      ResultSet rs = s.getResultSet();
      if (rs.next()) {
        c.setCpf(cpf);
        c.setNome(rs.getString("nome"));
        c.setTelefone(rs.getString("telefone"));
      }
      rs.close();
      fecharConexao();
    }
    catch (Exception erro) {
      System.out.println("ERRO ao consultar o BD!\n" + erro.getMessage());  
    }
    return c;
  }
  
  public void inserir(Cliente c) {
    // INSERT INTO cliente VALUES ('111.111.111-11', 'José', '(11) 3434-3434')
    String sql = "INSERT INTO cliente VALUES ('" + c.getCpf() + 
     "', '" + c.getNome() + "', '" + c.getTelefone() + "')";
    try {
      abrirConexao();
      Statement s = conn.createStatement();
      s.executeUpdate(sql);
      s.close();
      fecharConexao();
    }
    catch (Exception e) {
      System.out.println("Erro: Não foi possível inserir o registro\n" +
        e.getMessage());  
    }
  }
  
  public void alterar(Cliente c) {
    String sql = "UPDATE cliente SET nome = '" + c.getNome() + 
      "', telefone = '" + c.getTelefone() + "' WHERE cpf = '" + c.getCpf() + "'";
    try {
      abrirConexao();
      Statement s = conn.createStatement();
      s.executeUpdate(sql);
      s.close();
      fecharConexao();
    }
    catch (Exception e) {
      System.out.println("Erro: Não foi possível alterar o registro\n" +
        e.getMessage());  
    }
  }
  
  public void alterar(String cpf, Cliente c) {
    String sql = "UPDATE cliente SET cpf = '" + c.getCpf() + 
        "', nome = '" + c.getNome() + 
        "', telefone = '" + c.getTelefone() + 
        "' WHERE cpf = '" + cpf + "'";
    try {
      abrirConexao();
      Statement s = conn.createStatement();
      s.executeUpdate(sql);
      s.close();
      fecharConexao();
    }
    catch (Exception e) {
      System.out.println("Erro: Não foi possível alterar o registro\n" +
        e.getMessage());  
    }
  }
  
  public void excluir(String cpf) {
    String sql = "DELETE FROM cliente WHERE cpf = '" + cpf + "'";
    try {
      abrirConexao();
      Statement s = conn.createStatement();
      s.executeUpdate(sql);
      s.close();
      fecharConexao();
    }
    catch (Exception e) {
      System.out.println("Erro: Não foi possível excluir o registro\n" +
        e.getMessage());  
    }
  }
}
