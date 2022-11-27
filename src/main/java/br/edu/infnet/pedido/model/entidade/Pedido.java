package br.edu.infnet.pedido.model.entidade;

import java.time.LocalDate;
import java.util.List;

public class Pedido {

	private Long codigo;
	
	private LocalDate data;
	
	private Cliente cliente;
	
	private List<Produto> produtos;

	public Pedido() {
	}
	
	public Pedido(Long codigo, LocalDate data, Cliente cliente) {
		super();
		this.codigo = codigo;
		this.data = data;
		this.cliente = cliente;
	}


	public Pedido(LocalDate data, Cliente cliente) {
		super();
		this.data = data;
		this.cliente = cliente;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public String toString() {
		return "Pedido [codigo=" + codigo + ", data=" + data + ", cliente=" + cliente + ", produtos=" + produtos + "]";
	}
	
	
}
