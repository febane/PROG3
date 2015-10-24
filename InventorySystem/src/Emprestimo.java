import java.util.Date;

public class Emprestimo {
	
	private Midia midia;
	private Pessoa tomador;
	private Date emprestimo;
	private Date devolucao;
	
	public Emprestimo(Midia midia, Pessoa tomador, Date emprestimo,
			Date devolucao) {
		this.midia = midia;
		this.tomador = tomador;
		this.emprestimo = emprestimo;
		this.devolucao = devolucao;
	}

	public Midia getMidia() {
		return midia;
	}

	public Pessoa getTomador() {
		return tomador;
	}

	public Date getEmprestimo() {
		return emprestimo;
	}

	public Date getDevolucao() {
		return devolucao;
	}

}
