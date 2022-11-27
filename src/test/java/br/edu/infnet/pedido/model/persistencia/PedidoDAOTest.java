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

	@Before
	public void inicializar(){
		Cliente cliente = new Cliente();
		cliente = new ClienteDAO().obter(1L);
		LocalDate date = LocalDate.of(2022, 12, 11);
		pedido = new Pedido(date, cliente);
	}

	@Test
	public void testInsertpedido() {
		PedidoDAO pedidoDAO = new PedidoDAO();
		boolean insert = pedidoDAO.salvar(pedido);
		Assert.assertTrue(insert);
	}

	@Test
	public void testObterpedido() {
		PedidoDAO pedidoDAO = new PedidoDAO();
		Pedido pedido = pedidoDAO.obter(1L);
		System.out.println(pedido);
		Assert.assertNotNull(pedido);
	}
	
	@Test
	public void testListaPedidos() {
		PedidoDAO pediIdao = new PedidoDAO();
		List<Pedido> lista = pediIdao.listarTodos();
		System.out.println(lista); 
		Assert.assertTrue(lista.size() > 0);
	}
	

}
