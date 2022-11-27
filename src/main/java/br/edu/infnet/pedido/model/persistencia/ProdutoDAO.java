package br.edu.infnet.pedido.model.persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.pedido.model.entidade.Produto;

public class ProdutoDAO extends JdbcDAO<Produto> {

	@Override
	public Boolean salvar(Produto obj) {
		String sql = "insert into produto(descricao, preco) values (?,?)";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, obj.getDescricao());
			pstm.setDouble(2, obj.getPreco());
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean atualizar(Produto obj) {
		String sql = "update produto set descricao = ?, preco = ? where codigo = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, obj.getDescricao());
			pstm.setDouble(2, obj.getPreco());
			pstm.setLong(3, obj.getCodigo());
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean deletar(Produto obj) {
		String sql = "delete from produto where codigo = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, obj.getCodigo());
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Produto obter(Long codigo) {
		String sql = "select * from produto where codigo = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, codigo);
			rs = pstm.executeQuery();
			if (rs.next()) {
				Long id = rs.getLong("codigo");
				String descricao = rs.getString("descricao");
				Double preco = rs.getDouble("preco");
				return new Produto(id, descricao, preco);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Produto> listarTodos() {
		String sql = "select * from produto";
		List<Produto> produtos = new ArrayList<>();
		try {
			stm  = con.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()) {
				Long id = rs.getLong("codigo");
				String descricao = rs.getString("descricao");
				Double preco = rs.getDouble("preco");
				produtos.add(new Produto(id, descricao, preco));
			}
			return produtos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
