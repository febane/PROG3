public abstract class Midia {
	
	private int codigo;
	private String nome;
	private int tamanho;
	private Genero genero;
	private boolean possui;
	private boolean consumiu;
	private boolean deseja;
	private double preco;
	
	public Midia(int codigo, String nome, int tamanho, Genero genero,
			boolean possui, boolean consumiu, boolean deseja, double preco) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.tamanho = tamanho;
		this.genero = genero;
		this.possui = possui;
		this.consumiu = consumiu;
		this.deseja = deseja;
		this.preco = preco;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public int getTamanho() {
		return tamanho;
	}

	public Genero getGenero() {
		return genero;
	}

	public boolean isPossui() {
		return possui;
	}

	public boolean isConsumiu() {
		return consumiu;
	}

	public boolean isDeseja() {
		return deseja;
	}

	public double getPreco() {
		return preco;
	}

	public void setPossui(boolean possui) {
		this.possui = possui;
	}

	public void setConsumiu(boolean consumiu) {
		this.consumiu = consumiu;
	}

	public void setDeseja(boolean deseja) {
		this.deseja = deseja;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
}