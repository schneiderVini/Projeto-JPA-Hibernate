package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.*;
import br.com.alura.loja.util.JPAUtil;
import org.hibernate.id.enhanced.PooledOptimizer;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class PerfomanceConsultas {
    public static void main(String[] args) {
        PopularBancoDeDados();
        EntityManager em = JPAUtil.getEntityMananger();
        PedidoDao pd = new PedidoDao(em);

        Pedido p = pd.buscarPedidoComCliente(1l);
        em.close();
        System.out.println(p.getCliente().getNome());

    }

    private static void PopularBancoDeDados() {
        Categoria categoria = new Categoria("CELULARES");
        Categoria categoria2 = new Categoria("INFORMATICA");
        Categoria categori3 = new Categoria("GAMES");
        Produto celular = new Produto("Galaxy s22","TOP",new BigDecimal("1000"),categoria);
        Produto videogame = new Produto("PS5","BONITO",new BigDecimal(2000),categori3);
        Produto macbook = new Produto("MAC5","CARO",new BigDecimal(5000),categoria2);
        Cliente cliente = new Cliente("Jorge","123123");

        EntityManager em = JPAUtil.getEntityMananger();
        ProdutoDao pd = new ProdutoDao(em);
        CategoriaDao cd = new CategoriaDao(em);
        ClienteDao clienteDao = new ClienteDao(em);
        PedidoDao pedidoDao = new PedidoDao(em);

        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10,pedido,celular));
        pedido.adicionarItem(new ItemPedido(5,pedido,videogame));

        Pedido pedido2 = new Pedido(cliente);
        pedido2.adicionarItem(new ItemPedido(15,pedido2,macbook));

        em.getTransaction().begin();
        cd.cadastrar(categoria);
        cd.cadastrar(categoria2);
        cd.cadastrar(categori3);
        pd.cadastrar(celular);
        pd.cadastrar(videogame);
        pd.cadastrar(macbook);
        pedidoDao.cadastrar(pedido);
        pedidoDao.cadastrar(pedido2);
        clienteDao.cadastrar(cliente);
        em.getTransaction().commit();
        em.close();
    }
}



