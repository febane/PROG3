import java.util.List;

public class Livro extends Midia {
	
	private List<Pessoa> autores;

	public Livro(int codigo, String nome, int tamanho, Genero genero,
			boolean possui, boolean consumiu, boolean deseja, double preco,
			List<Pessoa> autores) {
		super(codigo, nome, tamanho, genero, possui, consumiu, deseja, preco, 'L');
		this.autores = autores;

	}

	public List<Pessoa> getAutores() {
		return autores;
	}
	
}
