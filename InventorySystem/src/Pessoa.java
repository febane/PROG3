import java.io.Serializable;
import java.text.Collator;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;


@SuppressWarnings("serial")
public class Pessoa implements Comparable<Pessoa>, Serializable {
	
	private int codigo;
	private String nome;
	private Set<Midia> trabalhos = null;

	public Pessoa(int codigo, String nome) {
		//super();
		this.codigo = codigo;
		this.nome = nome;
		this.trabalhos = new TreeSet<>();
	}
	
	public Pessoa(){}

	public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}
	
	public void addMidia(Midia m)
	{
		this.trabalhos.add(m);
	}
	
	public int qtdTrabalhos()
	{
		return this.trabalhos.size();
	}

	public Set<Midia> getTrabalhos() {
		return trabalhos;
	}
	
	@Override
	public int compareTo(Pessoa o) {
		// TODO Auto-generated method stub
		Locale br = new Locale("pt","BR");
		Collator collator = Collator.getInstance(br);
		try{
			//return this.nome.compareToIgnoreCase(o.nome);
			return collator.compare(this.getNome(), o.getNome());
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return 0;
	}

}
