package br.com.casadocodigo.loja.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Pedido implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private double valor;
	private Calendar data;
	private ArrayList<Produto> produtos;
	
	public Pedido() {}

	public Pedido(int id, double valor, Calendar data, ArrayList<Produto> produtos) {
		super();
		this.id = id;
		this.valor = valor;
		this.data = data;
		this.produtos = produtos;
	}

	public int getId() {
		return id;
	}

	public double getValor() {
		return valor;
	}

	public String getData() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(data.getTime());
	}

	public String getProdutos() {
		String titulos = "";
		for(Produto produto : produtos){
			titulos += produto.getTitulo() + ", ";
		}
		
		// Remove a virgula e o espaço no final da String
		titulos = titulos.substring(0, titulos.length() - 2);
		
		return titulos;
	}
	
	@Override
	public String toString() {
		return "[ID: " + this.id + ", VALOR: " + this.valor + ", DATA: " + this.data + ", PRODUTOS: " + this.produtos + "]";
	}
}
