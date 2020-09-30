package negocios;

import java.util.Date;

public class Fornecedor {
    private String cnpj;
    private String razaosocial;
    private String nomefantasia;
    private String dataabertura;
    private Float capital;

    public Fornecedor(String cnpj, String razaosocial, String nomefantasia, String dataabertura, Float capital) {
        this.cnpj = cnpj;
        this.razaosocial = razaosocial;
        this.nomefantasia = nomefantasia;
        this.dataabertura = dataabertura;
        this.capital = capital;
    }

    public Fornecedor(){
        
    }
    
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaosocial() {
        return razaosocial;
    }

    public void setRazaosocial(String razaosocial) {
        this.razaosocial = razaosocial;
    }

    public String getNomefantasia() {
        return nomefantasia;
    }

    public void setNomefantasia(String nomefantasia) {
        this.nomefantasia = nomefantasia;
    }

    public String getDataabertura() {
        return dataabertura;
    }

    public void setDataabertura(String dataabertura) {
        this.dataabertura = dataabertura;
    }

    public Float getCapital() {
        return capital;
    }

    public void setCapital(Float capital) {
        this.capital = capital;
    }
    
    
}
