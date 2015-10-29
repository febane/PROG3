import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@SuppressWarnings("serial")
public class Pessoa implements Comparable<Pessoa>, Serializable {
	
	private int codigo;
	private String nome;
	private Set<Midia> trabalhos = null;

	public Pessoa(int codigo, String nome) {
		//super();
		this.codigo = codigo;
		this.nome = nome;
		this.trabalhos = new HashSet<>();
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
		try{
			return this.nome.compareToIgnoreCase(o.nome);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return 0;
	}

}
