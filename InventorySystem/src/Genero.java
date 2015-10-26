import java.io.Serializable;


public class Genero implements Serializable {

	private String sigla;
	private String nome;

	
	
	public Genero(String sigla, String nome) {
		this.sigla = sigla;
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public String getNome() {
		return nome;
	}
	
}
