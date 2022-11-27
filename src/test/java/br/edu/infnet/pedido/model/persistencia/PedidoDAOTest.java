package br.edu.infnet.pedido.model.persistencia;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.edu.infnet.pedido.model.entidade.Cliente;
import br.edu.infnet.pedido.model.entidade.Pedido;

public class PedidoDAOTest {
	private static Pedido pedido;
	private PedidoDAO pedidoDAO;

	@Before
	@Test
	public void inicializar(){
		this.pedidoDAO = new PedidoDAO();
		Cliente cliente = new ClienteDAO().obter(1L);
		LocalDate date = LocalDate.of(2022, 12, 11);
		pedido = new Pedido(date, cliente);

		boolean insert = pedidoDAO.salvar(pedido);
		Assert.assertTrue(insert);
	}

	@Test
	public void testAtualizar() {
		List<Pedido> lista = pedidoDAO.listarTodos();
		Pedido pedidoUpdate = lista.get(lista.size() -1);
		pedidoUpdate.setData(LocalDate.of(2022, 10, 05));
		Boolean update = pedidoDAO.atualizar(pedidoUpdate);
		Assert.assertTrue(update);
	}

	@Test
	public void testObterpedido() {
		List<Pedido> lista = pedidoDAO.listarTodos();
		Pedido pedidoUpdate = lista.get(lista.size() -1);
		Pedido query = pedidoDAO.obter(pedidoUpdate.getCodigo());
		query.setProdutos(new PedidoItemDAO().listarTodos(1L));
		System.out.println(query);
		Assert.assertNotNull(query);
	}
	
	@Test
	public void testListaPedidos() {
		List<Pedido> lista = pedidoDAO.listarTodos();
		System.out.println(lista); 
		Assert.assertTrue(lista.size() > 0);
	}

	@Test
	public void testDeletar() {
		List<Pedido> lista = pedidoDAO.listarTodos();
		Pedido pedidoDeletar = lista.get(lista.size() -1);
		Boolean update = pedidoDAO.deletar(pedidoDeletar);
		Assert.assertTrue(update);
	}

}
