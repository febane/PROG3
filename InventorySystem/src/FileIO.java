import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
/**
 * CLASSE RESPONSÁVEL PELA LEITURA E ESCRITA DE ARQUIVOS
 * 
 * @author Igor Ventorim (IVentorim) e Fernando Neto(Febane)
 *
 */
public class FileIO {
	
	private static Scanner scanner;
	/**
	 * 
	 * @param generosFile - Nome do arquivo que contem a lista de generos para leitura
	 * @return ArrayLIst de generos guardado na memória
	 * @throws FileNotFoundException
	 */
	public static Map<String,Genero> readGenero(String generosFile) throws FileNotFoundException
	{
		//List<Genero> listGenero = new ArrayList<>();
		Map<String,Genero> mapGenero = new HashMap<>();
		
		scanner = new Scanner(new FileReader(generosFile));
		scanner = scanner.useDelimiter("[;\\n]+");
		
		System.out.println(scanner.next()+":"+scanner.next());
		
		while(scanner.hasNext())
		{
			String sigla = scanner.next();
			String nome = scanner.next();
			System.out.println(sigla+":"+nome);
			//listGenero.add(new Genero(sigla,nome));
			mapGenero.put(sigla, new Genero(sigla,nome));
		}
		scanner.close();
		return mapGenero;
	}
	
	/**
	 * 
	 * @param pessoaFile - Nome do arquivo que contém a lista de pessoas para leitura
	 * @return Lista de pessoas cadastradas no sistema
	 * @throws FileNotFoundException
	 */
	public static List<Pessoa> readPessoa(String pessoaFile) throws FileNotFoundException
	{
		List<Pessoa> listPessoa = new ArrayList<>();
		
		scanner = new Scanner(new FileReader(pessoaFile));
		scanner = scanner.useDelimiter("[;\\n]+"); // EXPRESSÃO REGULAR JAVA
		
		System.out.println(scanner.next()+":"+scanner.next());

		while(scanner.hasNext())
		{
			int cod = Integer.parseInt(scanner.next());
			String nome = scanner.next();
			System.out.println(cod+":"+nome);
			listPessoa.add(new Pessoa(cod,nome));
		}
		scanner.close();
		//Collections.sort(listPessoa);		
		return listPessoa;
	}
	
	/**
	 * 
	 * @param midiaFile - Nome do arquivo que contém a lista de midias para a leitura
	 * @param listPessoas - Lista de pessoas cadastradas no sistema
	 * @param mapGenero - Lista de gêneros cadastrados no sistema
	 * @return	Lista de mídias cadastradas no sistema
	 * @throws FileNotFoundException 
	 */
	public static List<Midia> readMidia(String midiaFile,List<Pessoa> listPessoas, Map<String,Genero> mapGenero) throws FileNotFoundException
	{
		List<Midia> listMidia = new ArrayList<>();
		
		scanner = new Scanner(new FileReader(midiaFile));
		scanner = scanner.useDelimiter("[;\\n]");
		
		// LOOP para descartar a linha de descrição do arquivo
		for(int i = 0; i < 13; i++)
			System.out.print(scanner.next()+" ");
				
		while(scanner.hasNext())
		{
			Pessoa diretor = new Pessoa();
			int codigo = scanner.nextInt();
			String nome = scanner.next();
			char type = scanner.next().charAt(0);
			String dir = scanner.next();
			if(!dir.equals(""))
				diretor = listPessoas.get(Integer.parseInt(dir)-1);
			String listaAutores = scanner.next();
			List<Pessoa> elenco = listAtores(listaAutores,listPessoas);
			int tamanho = scanner.nextInt();
			Genero gnr = mapGenero.get(scanner.next());
			String serie = scanner.next();
			String temporada = scanner.next();
			boolean possui = (scanner.next()).equals("x");
			boolean consumiu = scanner.next().equals("x");
			boolean deseja = scanner.next().equals("x");
			NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
			String sPreco = scanner.next();
			Number number = 0;
			try{
				number = format.parse(sPreco);
			}
			catch(ParseException e){
				System.out.println("Erro ao ler preco");
			}
			double preco = number.doubleValue();
			
			switch(type)
			{
				case 'L':	listMidia.add(new Livro(codigo,nome,tamanho,gnr,possui,consumiu,deseja,preco,elenco));
					break;
				case 'F':	listMidia.add(new Filme(codigo,nome,tamanho,gnr,possui,consumiu,deseja,preco,diretor,elenco));
					break;
				case 'S':	listMidia.add(new Serie(codigo,nome,tamanho,gnr,possui,consumiu,deseja,preco,elenco,temporada,serie));
					break;
				default: System.out.println("Este tipo de midia não pode ser cadastrado!");
			}
			
		}
		
		for (Midia midia : listMidia) {
			System.out.println(midia);
		}
			
		return listMidia;
	}
	
	/**
	 * 
	 * @param codAtores - String com  a lista de código de autores
	 * @param l	- Lista de pessoas com os códigos de todas as pessoas cadastradas no sistema
	 * @return	- Lista de atores que participaram de um filme ou série
	 * @throws FileNotFoundException
	 */
	private static List<Pessoa> listAtores(String codAtores, List<Pessoa> l) throws FileNotFoundException
	{
		List<Pessoa> lista = new ArrayList<>();
		@SuppressWarnings("resource")
		Scanner s = new Scanner(codAtores).useDelimiter("[;\\n]+");
		
		while(s.hasNextInt())
		{
			
			lista.add(l.get(s.nextInt()-1));
		}
		
		return lista;
	}
	
	/**
	 * 
	 * @param emprestimoFile - Nome do arquivo que contém a lista de empréstimos para a leitura
	 * @param midiaList - Lista de midias cadastradas no sistema
	 * @return - Lista de empréstimos cadastrados no sistema
	 * @throws FileNotFoundException
	 * @throws ParseException
	 */
	public static List<Emprestimo> readEmprestimos(String emprestimoFile,List<Midia> midiaList) throws FileNotFoundException, ParseException
	{
		List<Emprestimo> listEmprestimo = new ArrayList<>(); 
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); 
		scanner = new Scanner(new FileReader(emprestimoFile));
		scanner = scanner.useDelimiter("[;\\n]+"); // EXPRESSÃO REGULAR JAVA
		
		System.out.println(scanner.next()+":"+scanner.next()+":"+scanner.next()+":"+scanner.next());
		
		while(scanner.hasNext())
		{
			Midia emprestada = midiaList.get(scanner.nextInt()-1);
			String nome = scanner.next();
			
			Date emprestimo = (Date)format.parse(scanner.next());
			Date devolucao = (Date)format.parse(scanner.next());
			System.out.println(emprestada.getNome()+":"+nome+":"+emprestimo+":"+devolucao);
			listEmprestimo.add(new Emprestimo(emprestada,nome,emprestimo,devolucao));
			//listGenero.add(new Genero(sigla,nome));
			//mapGenero.put(sigla, new Genero(sigla,nome));
		}
		scanner.close();
		
		return listEmprestimo;
		
	}
	
	@SuppressWarnings("deprecation")
	public static void writeEmprestimos(List<Emprestimo> e){
		
		final Date hoje = new Date("06/11/2015");
		SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
		
		BufferedWriter bw = null;
		try
		{
		    bw = new BufferedWriter( new FileWriter("3-emprestimos.csv"));
		    bw.write("Data;Tomador;Atrasado?;Dias de Atraso\n");
		    
		    for(Emprestimo emp : e){
		    	
		    	bw.write(emp.getDevolucao().toString()+";"+emp.getTomador()+";");
		    	if(emp.getDevolucao().after(hoje)){
		    		
		    		int dias = (int)(emp.getDevolucao().getTime() - hoje.getTime());
		    		
		    		bw.write("Sim;"+dias+"\n");
		    		
		    	}
		    	else{
		    		
		    		bw.write("Não;0\n");
		    		
		    	}
		    	
		    }

		}
		catch ( IOException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
		    try
		    {
		        if ( bw != null)
		        bw.close( );
		    }
		    catch ( IOException ex)
		    {
		    	ex.printStackTrace();
		    }
		}
		
	}
	
	
	
	/**
	 * Método responsável por gerar a WhishList
	 * 
	 * @param m - Lista de midias cadastradas no sistema
	 * @throws IOException
	 */
	public static void generatorWishList(List<Midia> m)
	{
		Collections.sort(m);
		
		FileWriter file;
		try {
			file = new FileWriter("4-wishlist.csv");
		
		PrintWriter saveFile = new PrintWriter(file);
		
		saveFile.println("Tipo;Mídia;Gênero;Preço");
		
		for (Midia midia : m) {
			switch(midia.getType())
			{
				case 'L':	saveFile.println("Livro;"+midia.getNome()+";"+midia.getGenero().getNome()+";R$ "+midia.getPreco());
					break;
				case 'F':	saveFile.println("Filme;"+midia.getNome()+";"+midia.getGenero().getNome()+";R$ "+midia.getPreco());
					break;
				case 'S': saveFile.println("Série;"+midia.getNome()+";"+midia.getGenero().getNome()+";R$ "+midia.getPreco());
					break;
				default:
			}
		}
		file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}