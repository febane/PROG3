import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Serie extends Midia {
	
	private List<Pessoa> atores;
	private String temporada;
	private String nome;
	private static Set<String> series = new TreeSet<>();
	

	
	public Serie(int codigo, String nome, int tamanho, Genero genero,
			boolean possui, boolean consumiu, boolean deseja, double preco,
			List<Pessoa> atores, String temporada, String nome2) {
		super(codigo, nome, tamanho, genero, possui, consumiu, deseja, preco, 'S');
		this.atores = atores;
		this.temporada = temporada;
		this.nome = nome2;
		nome = nome2;

	}
	
	public Serie(){}

	public List<Pessoa> getAtores() {
		return atores;
	}

	public String getTemporada() {
		return temporada;
	}

	public String getNomeSerie() {
		return nome;
	}
	
	public void addNewSerie(String nameSerie)
	{
		series.add(nameSerie);
	}
		
	public static Set<String> getSeries()
	{
		return series;
	}
	



}
