package br.edu.infnet.pedido.model.entidade;

public class Produto {

	private Long codigo;
	private String descricao;
	private Double preco;

	public Produto() {
		// TODO Auto-generated constructor stub
	}

	public Produto(String descricao, Double preco) {
		super();
		this.descricao = descricao;
		this.preco = preco;
	}

	public Produto(Long codigo, String descricao, Double preco) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", descricao=" + descricao + ", preco=" + preco + "]";
	}

}
