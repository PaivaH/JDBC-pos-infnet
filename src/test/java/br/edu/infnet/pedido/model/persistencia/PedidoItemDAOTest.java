package br.edu.infnet.pedido.model.persistencia;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.edu.infnet.pedido.model.entidade.Pedido;
import br.edu.infnet.pedido.model.entidade.PedidoItem;
import br.edu.infnet.pedido.model.entidade.Produto;

public class PedidoItemDAOTest {
	private static PedidoItem pedidoItem;
    private PedidoItemDAO pedidoItemDAO;

	@Before
    @Test
	public void inicializar(){
        pedidoItem = new PedidoItem();
        this.pedidoItemDAO = new PedidoItemDAO();

        List<Pedido> pedidos = new PedidoDAO().listarTodos();
        Pedido pedido = pedidos.get(pedidos.size() -1);
        pedidoItem.setPedido(pedido);

        List<Produto> produtos = new ProdutoDAO().listarTodos();
        Produto produto = produtos.get(produtos.size() -1);
        pedidoItem.setProduto(produto);
        System.out.println(pedidoItem);

        Boolean insert = pedidoItemDAO.salvar(pedidoItem);
        Assert.assertTrue(insert);
	}

    /*/
    @Test
    public void testAtualizar() {
        PedidoItemDAO pedidoItemDAO = new PedidoItemDAO();
        PedidoItem pedidoItemUpdate = pedidoItems.get(pedidoItems.size() - 1);
        Boolean update = pedidoItemDAO.atualizar(pedidoItemUpdate);
        Assert.assertNull(update);
    }*/

    @Test
    public void testListarTodos() {
        List <PedidoItem> pedidosItens = pedidoItemDAO.listarTodos();
        Assert.assertTrue(pedidosItens.size() > 0);
    }

    @Test
    public void testObter() {
        List <PedidoItem> pedidosItens = pedidoItemDAO.listarTodos();
        PedidoItem pedidoGet = pedidosItens.get(pedidosItens.size() -1);
        Assert.assertNotNull(pedidoGet);
    }

    @Test
    public void testDeletar() {
        List <PedidoItem> pedidosItens = pedidoItemDAO.listarTodos();
        PedidoItem pedidoItemDelete = pedidosItens.get(pedidosItens.size() -1);
        System.out.println(pedidoItemDelete);
        Boolean delete = pedidoItemDAO.deletar(pedidoItemDelete);
        Assert.assertTrue(delete);
    }
}
