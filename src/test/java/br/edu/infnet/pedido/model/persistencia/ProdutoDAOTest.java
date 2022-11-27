package br.edu.infnet.pedido.model.persistencia;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.edu.infnet.pedido.model.entidade.Produto;

public class ProdutoDAOTest {
	private Produto produto;

	@Before
	@Test
	public void testSalvar(){
		produto = new Produto("Produto de teste", 66.6D);
		ProdutoDAO produtIdao = new ProdutoDAO();
		boolean insert = produtIdao.salvar(produto);
		Assert.assertTrue(insert);
	}
	
	@Test
	public void testAtualizar() {
		ProdutoDAO produtIdao = new ProdutoDAO();
		List<Produto> produtos = produtIdao.listarTodos();
		Produto produtoUpdate = produtos.get(produtos.size() -1);
		produtoUpdate.setDescricao("Produto update");
		produtoUpdate.setPreco(55D);
		boolean update = produtIdao.atualizar(produtoUpdate);
		Assert.assertTrue(update);
	}

	@Test
	public void testObter() {
		ProdutoDAO produtIdao = new ProdutoDAO();
		List<Produto> produtos = produtIdao.listarTodos();
		Produto produtoGet = produtos.get(produtos.size() -1);
		Produto prod = produtIdao.obter(produtoGet.getCodigo());
		Assert.assertNotNull(prod);
	}
	
	@Test
	public void testListarTodos() {
		ProdutoDAO produtIdao = new ProdutoDAO();
		List<Produto> produtos = produtIdao.listarTodos();
		Assert.assertTrue(produtos.size() > 0);
	}

	@Test
	public void testDeletar() {
		ProdutoDAO produtIdao = new ProdutoDAO();
		List<Produto> produtos = produtIdao.listarTodos();
		Produto produtoDelete = produtos.get(produtos.size() -1);
		boolean query = produtIdao.deletar(produtoDelete);
		Assert.assertTrue(query);
	}
}
