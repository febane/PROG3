import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Emprestimo implements Serializable, Comparable<Emprestimo> {
	
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

	@Override
	public int compareTo(Emprestimo arg0) {
		// TODO Auto-generated method stub
		if(this.emprestimo.before(arg0.getEmprestimo()))
			return 1;
		else if(this.emprestimo.after(arg0.getEmprestimo()))
			return -1;
		else
			return 0;
	}
	

}
