import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Genero implements Serializable {

	private String sigla;
	private String nome;
	private List<Midia> midiasGenero = null;
	
	
	public Genero(String sigla, String nome) {
		this.sigla = sigla;
		this.nome = nome;
		midiasGenero = new ArrayList<>();
	}

	public String getSigla() {
		return sigla;
	}

	public String getNome() {
		return nome;
	}
	
	public void addMidiaGen(Midia m)
	{
		midiasGenero.add(m);
	}
	
}
