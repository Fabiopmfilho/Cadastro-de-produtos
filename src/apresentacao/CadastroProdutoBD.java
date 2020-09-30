package apresentacao;

import acessodados.DadosProdutos;
import java.util.Iterator;
import java.util.List;
import negocios.Produto;

public class CadastroProdutoBD {

    public static void main(String[] args) {
        /*
        Projeto em 3 camadas
        Camada de Apresentação (interface com o usuário).
        Camada de Negócios (Regras de negócios e Cálculos)
        Camada de Persistência (Acesso ao Banco de Dados... seleção, inserção, alterações, exclusão e consulta)
        */
        
        DadosProdutos dados = new DadosProdutos();
        Produto np = new Produto(3, "Cadeira", 900);
        //dados.inserir(np);
        List produtos = dados.consultar();
        Iterator item = produtos.iterator();
        while (item.hasNext()) {
            Produto p = new Produto();
            p = (Produto) item.next();
            System.out.print("Código: " + p.getCodigo() + ", ");
            System.out.print("Descrição: " + p.getDescricao() + ", ");
            System.out.println("Preço: R$" + p.getPreco());
        }
    }
    
}
