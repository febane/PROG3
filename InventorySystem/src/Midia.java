import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;


public abstract class Midia implements Serializable, Comparable<Midia>{
	
	private int codigo;
	private String nome;
	private char type;
	private int tamanho;
	private Genero genero;
	private boolean possui;
	private boolean consumiu;
	private boolean deseja;
	private double preco;
	
	public Midia(int codigo, String nome, int tamanho, Genero genero,
			boolean possui, boolean consumiu, boolean deseja, double preco, char type) {
		//super();
		this.codigo = codigo;
		this.nome = nome;
		this.tamanho = tamanho;
		this.genero = genero;
		this.possui = possui;
		this.consumiu = consumiu;
		this.deseja = deseja;
		this.preco = preco;
		this.type = type;
	}
	
	public Midia(){}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public int getTamanho() {
		return tamanho;
	}

	public Genero getGenero() {
		return genero;
	}

	public boolean isPossui() {
		return possui;
	}

	public boolean isConsumiu() {
		return consumiu;
	}

	public boolean isDeseja() {
		return deseja;
	}

	public double getPreco() {
		return preco;
	}

	public void setPossui(boolean possui) {
		this.possui = possui;
	}

	public void setConsumiu(boolean consumiu) {
		this.consumiu = consumiu;
	}

	public void setDeseja(boolean deseja) {
		this.deseja = deseja;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public String toString()
	{
		return "Nome: "+nome+", tipo: "+type+", preço: "+preco;
	}
	
	/**
	 * Método de critério de ordenação wishList
	 */
	public int compareTo(Midia otherMidia) {
    if (this.type < otherMidia.type) {
        return -1;
    }
    else if (this.type > otherMidia.type) {
        return 1;
    }else
    {
    	if(this.preco > otherMidia.preco)
    	{
    		return -1;
    	}else if(this.preco < otherMidia.preco)
    		return 1;
    	else
    	{
    		return this.getNome().compareTo(otherMidia.getNome());
    	}
    }
    
	}
	


	
}
