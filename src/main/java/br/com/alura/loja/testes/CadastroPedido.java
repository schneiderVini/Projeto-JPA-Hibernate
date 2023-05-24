package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.*;
import br.com.alura.loja.util.JPAUtil;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroPedido {
    public static void main(String[] args) {
        PopularBancoDeDados();
        EntityManager em = JPAUtil.getEntityMananger();
        ProdutoDao pd = new ProdutoDao(em);
        ClienteDao cd = new ClienteDao(em);
        Produto produto = pd.buscarPorId(1l);
        Produto produto2 = pd.buscarPorId(39l);
        Produto produto3 = pd.buscarPorId(40l);
        Cliente cliente = cd.buscarPorId(1l);


        em.getTransaction().begin();
        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10,pedido,produto));
        pedido.adicionarItem(new ItemPedido(5,pedido,produto2));

        Pedido pedido2 = new Pedido(cliente);
        pedido2.adicionarItem(new ItemPedido(15,pedido2,produto3));

        PedidoDao pedidoDao = new PedidoDao(em);
        pedidoDao.cadastrar(pedido);
        pedidoDao.cadastrar(pedido2);

        em.getTransaction().commit();
        BigDecimal totalVendido = pedidoDao.valorTotalVendido();
        System.out.println("Total vendido: " + totalVendido);

        List<RelatorioDeVendasVo> lista = pedidoDao.relatorioDeVendas();
        lista.forEach(System.out::println);
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

        em.getTransaction().begin();
        cd.cadastrar(categoria);
        cd.cadastrar(categoria2);
        cd.cadastrar(categori3);
        pd.cadastrar(celular);
        pd.cadastrar(videogame);
        pd.cadastrar(macbook);
        clienteDao.cadastrar(cliente);
        em.getTransaction().commit();
        em.close();
    }
}
