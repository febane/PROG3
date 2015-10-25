import java.util.Date;

public class Emprestimo {
	
	private Midia midia;
	private String tomador;
	private Date emprestimo;
	private Date devolucao;
	
	public Emprestimo(Midia midia, String tomador, Date emprestimo,
			Date devolucao) {
		this.midia = midia;
		this.tomador = tomador;
		this.emprestimo = emprestimo;
		this.devolucao = devolucao;
	}

	public Midia getMidia() {
		return midia;
	}

	public String getTomador() {
		return tomador;
	}

	public Date getEmprestimo() {
		return emprestimo;
	}

	public Date getDevolucao() {
		return devolucao;
	}

}
