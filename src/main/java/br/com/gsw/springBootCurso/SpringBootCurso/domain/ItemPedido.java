package br.com.gsw.springBootCurso.SpringBootCurso.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;


@Entity
public class ItemPedido implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();
	
	private double desconto;
	private Integer quantidade;
	private double preco;
	public ItemPedido(Pedido pedido,Produto produto, double desconto, Integer quantidade, double preco) {
		super();
		this.id.setPedido(pedido);
		this.id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
	
	
	public ItemPedido() {
		super();
	}



	public ItemPedidoPK getId() {
		return id;
	}
	public void setId(ItemPedidoPK id) {
		this.id = id;
	}
	public double getDesconto() {
		return desconto;
	}
	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	


	public double getSubTotal(){
		return (preco-desconto)*quantidade;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	public void setPedido(Pedido pedido) {
		this.id.setPedido( pedido );
	}

	public Produto getProduto() {
		return id.getProduto();
	}
	@JsonIgnore
	public Pedido getPedido() {
		return this.id.getPedido();
	}



	public void setProduto(Produto produto) {
		id.setProduto(produto);
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale( "pt","BR" ) );
		StringBuilder builder = new StringBuilder();
		builder.append( getProduto().getNome() );
		builder.append( ", Qte: " );
		builder.append(getQuantidade() );
		builder.append( ", Preço unitário: " );
		builder.append( nf.format( getProduto().getPreco()  ));
		builder.append( ", Subtotal: " );
		builder.append( nf.format( getSubTotal() ));
		builder.append( "\n" );
		return builder.toString();
	}
}
