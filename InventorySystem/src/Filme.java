import java.util.List;

public class Filme extends Midia {

	private Pessoa diretor;
	private List<Pessoa> atores;
	
	public Filme(int codigo, String nome, int tamanho, Genero genero,
			boolean possui, boolean consumiu, boolean deseja, double preco,
			Pessoa diretor, List<Pessoa> atores) {
		super(codigo, nome, tamanho, genero, possui, consumiu, deseja, preco);
		this.diretor = diretor;
		this.atores = atores;
	}
	
	public Pessoa getDiretor() {
		return diretor;
	}

	public List<Pessoa> getAtores() {
		return atores;
	}
	
}
