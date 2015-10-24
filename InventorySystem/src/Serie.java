import java.util.List;

public class Serie extends Midia{
	
	private List<Pessoa> atores;
	private int numero;
	private String nome;

	
	public Serie(int codigo, String nome, int tamanho, Genero genero,
			boolean possui, boolean consumiu, boolean deseja, double preco,
			List<Pessoa> atores, int numero, String nome2) {
		super(codigo, nome, tamanho, genero, possui, consumiu, deseja, preco, 'S');
		this.atores = atores;
		this.numero = numero;
		nome = nome2;
	}

	public List<Pessoa> getAtores() {
		return atores;
	}

	public int getNumero() {
		return numero;
	}

	public String getNome() {
		return nome;
	}

}
