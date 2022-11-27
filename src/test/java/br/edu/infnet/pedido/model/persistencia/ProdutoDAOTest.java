package br.edu.infnet.pedido.model.persistencia;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.edu.infnet.pedido.model.entidade.Produto;

public class ProdutoDAOTest {
	private Produto produto;

	@Before
	public void inicializar(){
		produto = new Produto(1l,"Produto de teste", 66.6D);
	}

	@Test
	public void testInsertProduto() {
		ProdutoDAO produtIdao = new ProdutoDAO();
		boolean insert = produtIdao.salvar(produto);
		Assert.assertTrue(insert);
	}
	
	@Test
	public void testUpdateProduto() {
		ProdutoDAO produtIdao = new ProdutoDAO();
		boolean query = produtIdao.atualizar(produto);
		Assert.assertTrue(query);
	}

	public void testDeletar() {
		ProdutoDAO produtIdao = new ProdutoDAO();
		boolean query = produtIdao.deletar(produto);
		Assert.assertTrue(query);
	}

	@Test
	public void testObterProduto() {
		ProdutoDAO produtIdao = new ProdutoDAO();
		Produto prod = produtIdao.obter(produto.getCodigo());
		Assert.assertNotNull(prod);
	}
	
	@Test
	public void testObterListaProdutos() {
		ProdutoDAO produtIdao = new ProdutoDAO();
		List<Produto> produtos = produtIdao.listarTodos();
		Assert.assertTrue(produtos.size() > 0);
	}
}
