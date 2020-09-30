package acessodados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import negocios.Produto;

public class DadosProdutos{
    private Connection conn = null;
    //conexao com sql server
    //private String strConn = "jbdc:sqlserver//localhost;"+ "databaseName=loja;userName=sa;passworld=123456";
    
    // conexao sql workbanch/mariadb
    private String strConn = "jdbc:mariadb://localhost:3306/loja?user=root&password=";
    
    private boolean abrirConexao(){
        boolean sucesso;
        //String provedor = "com.microsoft.sqlserver.jbdc.SQLServerDriver";  **SQL SERVER**
        String provedor = "org.mariadb.jdbc.Driver";
        
        try {
            Class.forName(provedor).newInstance();
            conn = DriverManager.getConnection(strConn);
            sucesso = true;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"Nao foi possivel concluir conexão.");
            System.out.println("Nao foi possivel concluir conexão.\n" + erro.getMessage());
            sucesso = false;
        }
        return sucesso;
    }
    
    private void fecharConexao(){
        try {
            conn.close();
        } catch (Exception erro) {
            System.out.println("Erro ao desconectar do BD.\n" + erro.getMessage());
        }
    }
    
    public List consultar(){
        List produtos = new ArrayList();
        try {
            abrirConexao();
            Statement s = conn.createStatement();
            s.executeQuery("SELECT * FROM produtos");
            ResultSet rs = s.getResultSet();
            while (rs.next()) {          
                Produto p = new Produto();
                p.setCodigo(rs.getInt("codigo"));
                p.setDescricao(rs.getString("descricao"));
                p.setPreco(rs.getFloat("preco"));
                produtos.add(p);
            }
            rs.close();
            fecharConexao();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar o BD.\n" + erro.getMessage());
        }
        return produtos;
    }
    
    public Produto consultar(int codigo){
        Produto p = new Produto();
        try {
            abrirConexao();
            Statement s = conn.createStatement();
            s.executeQuery("SELECT * FROM produtos WHERE codigo = " + codigo);
            ResultSet rs = s.getResultSet();
            if(rs.next()){
                p.setCodigo(codigo);
                p.setDescricao(rs.getString("descricao"));
                p.setPreco(rs.getFloat("preco"));
            }
            rs.close();
            fecharConexao();
        }catch (Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao consultar o BD.\n" + erro.getMessage());
        }
        return p;
    }
    
    public void inserir(Produto p){
        String sql = "INSERT INTO produtos VALUES (" + p.getCodigo() + ", '"
                + p.getDescricao() + "', " + p.getPreco() + ")";
        try {
            abrirConexao();
            Statement s = conn.createStatement();
            s.executeUpdate(sql);
            fecharConexao();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel inserir.\n" + e.getMessage());
        }
    }
    
    public void alterar(Produto p){
        String sql = "UPDATE produtos SET descricao =  '" + p.getDescricao() 
                + "', Preco = " + p.getPreco() + "WHERE codigo = " + p.getCodigo();
        try {
            abrirConexao();
            Statement s = conn.createStatement();
            s.executeUpdate(sql);
            s.close();
            fecharConexao();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel alterar.\n" + e.getMessage());
        }
    }
    
    public void excluir(int codigo){
        // DELETE FROM produtos WHERE codigo = 11
        String sql = "DELETE FROM produtos WHERE codigo = " + codigo;
        try {
            abrirConexao();
            Statement s = conn.createStatement();
            s.executeUpdate(sql);
            s.close();
            fecharConexao();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel excluir.\n" + e.getMessage());
        }
    }
}
