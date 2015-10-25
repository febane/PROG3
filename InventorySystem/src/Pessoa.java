import java.io.Serializable;


public class Pessoa implements Comparable<Pessoa>, Serializable {
	
	private int codigo;
	private String nome;

	public Pessoa(int codigo, String nome) {
		//super();
		this.codigo = codigo;
		this.nome = nome;
	}
	
	public Pessoa(){
		
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public int compareTo(Pessoa o) {
		// TODO Auto-generated method stub
		return Integer.parseInt(this.nome) - Integer.parseInt(o.nome);
	}

}
