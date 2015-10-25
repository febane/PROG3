import java.util.List;

public class Serie extends Midia{
	
	private List<Pessoa> atores;
	private String temporada;
	private String nome;

	
	public Serie(int codigo, String nome, int tamanho, Genero genero,
			boolean possui, boolean consumiu, boolean deseja, double preco,
			List<Pessoa> atores, String temporada, String nome2) {
		super(codigo, nome, tamanho, genero, possui, consumiu, deseja, preco, 'S');
		this.atores = atores;
		this.temporada = temporada;
		nome = nome2;
	}

	public List<Pessoa> getAtores() {
		return atores;
	}

	public String getTemporada() {
		return temporada;
	}

	public String getNome() {
		return nome;
	}

}
