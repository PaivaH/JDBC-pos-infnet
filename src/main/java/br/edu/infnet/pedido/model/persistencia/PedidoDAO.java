package br.edu.infnet.pedido.model.persistencia;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import br.edu.infnet.pedido.model.entidade.Cliente;
import br.edu.infnet.pedido.model.entidade.Pedido;
import br.edu.infnet.pedido.model.entidade.Produto;

public class PedidoDAO extends JdbcDAO<Pedido> {

	@Override
	public Boolean salvar(Pedido obj) {
		String sql = "insert into pedido (codigo, data, cliente_cod) values (null, ?, ?)";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setDate(1, Date.valueOf(obj.getData()));
			pstm.setLong(2, obj.getCliente().getCodigo());
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean atualizar(Pedido obj) {
		String sql = "update pedido set data = ?, cliente_cod = ? where codigo = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setDate(1, Date.valueOf(obj.getData()));
			pstm.setLong(2, obj.getCliente().getCodigo());
			pstm.setLong(3, obj.getNumero());
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean deletar(Pedido obj) {
		String sql = "delete from pedido where codigo = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, obj.getNumero());
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Pedido obter(Long codigo) {
		String sql = "select p.codigo, p.data, c.nome, pr.descricao, pr.preco "
				+ " from pedido p "
				+ "	join cliente c"
				+ "	join itens_pedido i"
				+ "	join produto pr"
				+ "	on p.cliente_cod = c.codigo"
				+ "	and p.codigo = i.pedido_cod"
				+ "	and pr.codigo = i.produto_cod"
				+ "where p.codigo = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, codigo);
			rs = pstm.executeQuery();
			if (rs.next()) {
				LocalDate data = rs.getDate("data").toLocalDate();
				String nome = rs.getString("nome");
				String descricao = rs.getString("descricao");
				Double preco = rs.getDouble("preco");
				Produto produto = new Produto(descricao, preco);
				return new Pedido(codigo, data, new Cliente(nome));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Pedido> listarTodos() {
		String sql = "select p.codigo, p.data, c.nome, pr.descricao, pr.preco"
				+ " from pedido p "
				+ "	join cliente c"
				+ "	join itens_pedido i"
				+ "	join produto pr"
				+ "	on p.cliente_cod = c.codigo"
				+ "	and p.codigo = i.pedido_cod"
				+ "	and pr.codigo = i.produto_cod";
		Map<Long, Pedido> pedidos = new TreeMap<>();
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				Long codigo = rs.getLong("codigo");
				LocalDate data = rs.getDate("data").toLocalDate();
				String nome = rs.getString("nome");
				String descricao = rs.getString("descricao");
				Double preco = rs.getDouble("preco");
				Pedido pedido = null;
				if (pedidos.get(codigo) == null) {
					pedido = new Pedido(codigo, data, new Cliente(nome));
					pedido.setProdutos(new ArrayList<>());
					pedidos.put(codigo, pedido);
				}
				Produto produto = new Produto(null, descricao, preco);
				pedido = pedidos.get(codigo);
				pedido.getProdutos().add(produto);
			}
			return new ArrayList<Pedido>(pedidos.values());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
