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
	public void inicializar(){
        pedidoItem = new PedidoItem();
        this.pedidoItemDAO = new PedidoItemDAO();

        Pedido pedido = new PedidoDAO().obter(1L);
        pedidoItem.setPedido(pedido);

        Produto produto = new ProdutoDAO().obter(1L);
        pedidoItem.setProduto(produto);
        System.out.println(pedidoItem);
	}

    @Test
    public void testSalvar() {
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
