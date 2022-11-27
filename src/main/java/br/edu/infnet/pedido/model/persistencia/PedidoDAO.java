package br.edu.infnet.pedido.model.persistencia;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.pedido.model.entidade.Cliente;
import br.edu.infnet.pedido.model.entidade.Pedido;

public class PedidoDAO extends JdbcDAO<Pedido> {

	@Override
	public Boolean salvar(Pedido pedido) {
		String sql = "insert into pedido (codigo, data, cliente_cod) values (null, ?, ?)";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setDate(1, Date.valueOf(pedido.getData()));
			pstm.setLong(2, pedido.getCliente().getCodigo());
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean atualizar(Pedido pedido) {
		String sql = "update pedido set data = ?, cliente_cod = ? where codigo = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setDate(1, Date.valueOf(pedido.getData()));
			pstm.setLong(2, pedido.getCliente().getCodigo());
			pstm.setLong(3, pedido.getCodigo());
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean deletar(Pedido pedido) {
		String sql = "delete from pedido where codigo = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, pedido.getCodigo());
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Pedido obter(Long codigo) {
		String sql = "select * from pedido where codigo = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, codigo);
			rs = pstm.executeQuery();
			if (rs.next()) {
				LocalDate data = rs.getDate("data").toLocalDate();
				Cliente cliente = new ClienteDAO().obter(rs.getLong("cliente_cod"));
				return new Pedido(codigo, data, cliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
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
	}*/

	@Override
	public List<Pedido> listarTodos() {
		String sql = "select * from pedido";
		List<Pedido> pedidos = new ArrayList<>();
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()) {
				Long codigo = rs.getLong("codigo");
				LocalDate data = rs.getDate("data").toLocalDate();
				Cliente cliente = new ClienteDAO().obter(rs.getLong("cliente_cod"));
				pedidos.add(new Pedido(codigo, data, cliente));
			}
			return pedidos;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
