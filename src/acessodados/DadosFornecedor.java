package acessodados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import negocios.Fornecedor;

public class DadosFornecedor extends Dados{
    
    public List consultar(){
        List fornecedores = new ArrayList();
        try {
            abrirConexao();
            Statement s = conn.createStatement();
            s.executeQuery("SELECT * FROM fornecedores");
            ResultSet rs = s.getResultSet();
            while (rs.next()) {
                Fornecedor f = new  Fornecedor();
                f.setCnpj(rs.getString("cnpj"));
                f.setRazaosocial(rs.getString("razaosocial"));
                f.setNomefantasia(rs.getString("nomefantasia"));
                f.setDataabertura(rs.getString("dataabertura"));
                f.setCapital(rs.getFloat("capital"));
                fornecedores.add(f);
            }
            rs.close();
            fecharConexao();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar o BD.\n" + erro.getMessage());
        }
        return fornecedores;
    }

    public Fornecedor consultar (String cnpj){
        Fornecedor f = new Fornecedor();
        try {
            abrirConexao();
            Statement s = conn.createStatement();
            s.executeQuery("SELECT * FROM fornecedores WHERE cnpj = '" + cnpj + "'");
            ResultSet rs = s.getResultSet();
            if (rs.next()) {
                f.setCnpj(cnpj);
                f.setRazaosocial(rs.getString("razaosocial"));
                f.setNomefantasia(rs.getString("nomefantasia"));
                f.setDataabertura(rs.getString("dataabertura"));
                f.setCapital(rs.getFloat("capital"));
            }
            rs.close();
            fecharConexao();
        } catch (Exception e) {
            System.out.println("ERRO ao conectar ao BD \n" + e.getMessage());;
        }
        return f;
    }

    public void inserir(Fornecedor f) {
        // INSERT INTO cliente VALUES
        String sql = "INSERT INTO fornecedores VALUES ('" + f.getCnpj() + 
            "', '" + f.getRazaosocial() + "', '" + f.getNomefantasia() +
            "', '" + f.getDataabertura() + "', '" + f.getCapital() + "')";
        try {
          abrirConexao();
          Statement s = conn.createStatement();
          s.executeUpdate(sql);
          s.close();
          fecharConexao();
        } catch (Exception e) {
          System.out.println("Erro: Não foi possível inserir o registro\n" +
            e.getMessage());  
        }
    }
    
    public void alterar(Fornecedor f) {
        String sql = "UPDATE fornecedores SET razaosocial = '" + f.getRazaosocial()+ 
            "', nomefantasia = '" + f.getNomefantasia() + "', dataabertura = '" + f.getDataabertura()+
            "', capital = '" + f.getCapital() + "' WHERE cnpj = '" + f.getCnpj()+ "'";
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
    
    public void alterar(String cnpj, Fornecedor f) {
        String sql = "UPDATE fornecedores SET cnpj = '" + f.getCnpj()+ 
            "', razaosocial = '" + f.getRazaosocial()+ 
            "', nomefantasia = '" + f.getNomefantasia()+ 
            "', dataabertura = '" + f.getDataabertura()+
            "', capital = '" + f.getCapital()+ 
            "' WHERE cnpj = '" + cnpj + "'";
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
    
    public void excluir(String cnpj) {
        String sql = "DELETE FROM fornecedores WHERE cnpj = '" + cnpj + "'";
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

