package br.edu.infnet.pedido.model.entidade;

public class PedidoItem {
    private Long codigo;
    private Pedido pedido;
    private Produto produto;

    public PedidoItem() {
    }

    public PedidoItem(Pedido pedido, Produto produto) {
        this.pedido = pedido;
        this.produto = produto;
    }

    public PedidoItem(Long codigo, Pedido pedido, Produto produto) {
        this.codigo = codigo;
        this.pedido = pedido;
        this.produto = produto;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
