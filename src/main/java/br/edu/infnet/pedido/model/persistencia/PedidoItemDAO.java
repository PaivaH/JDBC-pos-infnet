package br.edu.infnet.pedido.model.persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.pedido.model.entidade.Pedido;
import br.edu.infnet.pedido.model.entidade.PedidoItem;
import br.edu.infnet.pedido.model.entidade.Produto;

public class PedidoItemDAO extends JdbcDAO<PedidoItem> {

	@Override
	public Boolean salvar(PedidoItem pedidoItem) {
		String sql = "insert into itens_pedido(pedido_cod, produto_cod) values (?, ?)";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, pedidoItem.getPedido().getCodigo());
			pstm.setLong(2, pedidoItem.getProduto().getCodigo());
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Esse metodo não pode acontencer, o pedido só pode adicionar ou remover.
	 */
	@Override
	public Boolean atualizar(PedidoItem pedidoItem) {
		/*
		 * String sql = "update itens_pedido set produto_cod = ? where codigo = ?";
		 * try {
		 * pstm = con.prepareStatement(sql);
		 * pstm.setLong(1, pedidoItem.getProduto().getCodigo());
		 * pstm.setLong(2, pedidoItem.getCodigo());
		 * return pstm.executeUpdate() > 0;
		 * } catch (SQLException e) {
		 * e.printStackTrace();
		 * }
		 */
		return null;
	}

	@Override
	public Boolean deletar(PedidoItem pedidoItem) {
		String sql = "delete from itens_pedido where codigo = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, pedidoItem.getCodigo());
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PedidoItem obter(Long codigo) {
		String sql = "select * from itens_pedido where codigo = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, codigo);
			rs = pstm.executeQuery();
			if (rs.next()) {
				Pedido pedido_cod = new PedidoDAO().obter(rs.getLong("pedido_cod"));
				Produto produto_cod = new ProdutoDAO().obter(rs.getLong("produto_cod"));
				return new PedidoItem(codigo, pedido_cod, produto_cod);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<PedidoItem> listarTodos() {
		String sql = "select * from itens_pedido";
		List<PedidoItem> pedidoItems = new ArrayList<>();
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				Long codigo = rs.getLong("codigo");
				Pedido pedido_cod = new PedidoDAO().obter(rs.getLong("pedido_cod"));
				Produto produto_cod = new ProdutoDAO().obter(rs.getLong("produto_cod"));

				pedidoItems.add(
						new PedidoItem(
								codigo,
								pedido_cod,
								produto_cod));
			}
			return pedidoItems;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Produto> listarTodos(Long codigoPedido) {
		String sql = "select i.* "
				+ " from itens_pedido i"
				+ " join produto p "
				+ "on p.codigo = i.produto_cod"
				+ " where pedido_cod = ?";
		List<Produto> pedidoItems = new ArrayList<>();
		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, codigoPedido);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Produto produto = new ProdutoDAO().obter(rs.getLong("produto_cod"));
				pedidoItems.add(produto);
			}
			return pedidoItems;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
