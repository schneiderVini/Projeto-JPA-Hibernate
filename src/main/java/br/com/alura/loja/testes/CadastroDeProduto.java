package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.CategoriaId;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {
    public static void main(String[] args) {
        cadastrarProduto();
        cadastrarProduto();
        EntityManager em = JPAUtil.getEntityMananger();
        ProdutoDao produtoDao = new ProdutoDao(em);
        Produto p = produtoDao.buscarPorId(1l);
        System.out.println(p.getNome());
        List<Produto> produtos = produtoDao.buscarPorCategoria("CELULARES");

        produtos.forEach(p2 -> System.out.println(p2.getNome()));
        BigDecimal b = produtoDao.buscarPrecoDoProdutoComNome("Galaxy s22");
        System.out.println( "PREÇO DO PRODUTO: " + b);
    }

    private static void cadastrarProduto() {
        Categoria categoria = new
                Categoria("CELULARES");
        Produto celular = new Produto("Galaxy s22","TOP",new BigDecimal("1000"),categoria);

        EntityManager em = JPAUtil.getEntityMananger();
        ProdutoDao pd = new ProdutoDao(em);
        CategoriaDao cd = new CategoriaDao(em);

        em.getTransaction().begin();
        cd.cadastrar(categoria);
        pd.cadastrar(celular);
        em.getTransaction().commit();
        em.close();
    }

}








