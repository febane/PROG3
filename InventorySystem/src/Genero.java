import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@SuppressWarnings("serial")
public class Genero implements Serializable, Comparable<Genero> {

	private String sigla;
	private String nome;
	private Set<Midia> midiasGenero = null;
	
	
	public Genero(String sigla, String nome) {
		this.sigla = sigla;
		this.nome = nome;
		midiasGenero = new HashSet<>();//ArrayList<>();
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
	
	public int qtdGenero()
	{
		return midiasGenero.size();
	}

	@Override
	public int compareTo(Genero o) {
		// TODO Auto-generated method stub
		if(this.midiasGenero.size() > o.midiasGenero.size())
		{
			return -1;
		}else if(this.midiasGenero.size() < o.midiasGenero.size())
		{
			return 1;
		}else {
			return this.getNome().compareToIgnoreCase(o.getNome());
		}
	}
	
}
