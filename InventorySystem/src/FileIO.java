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
import java.util.*;
import java.util.Map.Entry;
/**
 * CLASSE RESPONSÃ�VEL PELA LEITURA E ESCRITA DE ARQUIVOS
 * 
 * @author Igor Ventorim (IVentorim) e Fernando Neto(Febane)
 *
 */
public class FileIO {
	
	private static Scanner scanner;
	/**
	 * 
	 * @param generosFile - Nome do arquivo que contem a lista de generos para leitura
	 * @return ArrayLIst de generos guardado na memÃ³ria
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
	 * @param pessoaFile - Nome do arquivo que contÃ©m a lista de pessoas para leitura
	 * @return Lista de pessoas cadastradas no sistema
	 * @throws FileNotFoundException
	 */
	public static List<Pessoa> readPessoa(String pessoaFile) throws FileNotFoundException
	{
		List<Pessoa> listPessoa = new ArrayList<>();
		
		scanner = new Scanner(new FileReader(pessoaFile));
		scanner = scanner.useDelimiter("[;\\n]+"); // EXPRESSÃƒO REGULAR JAVA
		
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
	 * @param midiaFile - Nome do arquivo que contÃ©m a lista de midias para a leitura
	 * @param listPessoas - Lista de pessoas cadastradas no sistema
	 * @param mapGenero - Lista de gÃªneros cadastrados no sistema
	 * @return	Lista de mÃ­dias cadastradas no sistema
	 * @throws FileNotFoundException 
	 */
	public static List<Midia> readMidia(String midiaFile,List<Pessoa> listPessoas, Map<String,Genero> mapGenero) throws FileNotFoundException
	{
		List<Midia> listMidia = new ArrayList<>();
		
		scanner = new Scanner(new FileReader(midiaFile));
		scanner = scanner.useDelimiter("[;\\n]");
		
		// LOOP para descartar a linha de descriÃ§Ã£o do arquivo
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
				default: System.out.println("Este tipo de midia nÃ£o pode ser cadastrado!");
			}
			
		}
		
		for (Midia midia : listMidia) {
			System.out.println(midia);
		}
			
		return listMidia;
	}
	
	/**
	 * 
	 * @param codAtores - String com  a lista de cÃ³digo de autores
	 * @param l	- Lista de pessoas com os cÃ³digos de todas as pessoas cadastradas no sistema
	 * @return	- Lista de atores que participaram de um filme ou sÃ©rie
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
	 * @param emprestimoFile - Nome do arquivo que contÃ©m a lista de emprÃ©stimos para a leitura
	 * @param midiaList - Lista de midias cadastradas no sistema
	 * @return - Lista de emprÃ©stimos cadastrados no sistema
	 * @throws FileNotFoundException
	 * @throws ParseException
	 */
	public static List<Emprestimo> readEmprestimos(String emprestimoFile,List<Midia> midiaList) throws FileNotFoundException, ParseException
	{
		List<Emprestimo> listEmprestimo = new ArrayList<>(); 
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); 
		scanner = new Scanner(new FileReader(emprestimoFile));
		scanner = scanner.useDelimiter("[;\\n]+"); // EXPRESSÃƒO REGULAR JAVA
		
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
	
	public static void writeEmprestimos(List<Emprestimo> e){
		
		Date hoje = null;
		Collections.sort(e);
		try{
			hoje = new SimpleDateFormat("dd/MM/yyyy").parse("06/11/2015");
		}
		catch(ParseException ex){
			ex.printStackTrace();
		}
		SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
		
		BufferedWriter bw = null;
		try
		{
		    bw = new BufferedWriter( new FileWriter("3-emprestimos.csv"));
		    bw.write("Data;Tomador;Atrasado?;Dias de Atraso\n");
		    
		    for(Emprestimo emp : e){
		    	
		    	String s = new SimpleDateFormat("dd/MM/yyyy").format(emp.getEmprestimo());
		    	Date dev = null;
		    	try{
		    		dev = new SimpleDateFormat("dd/MM/yyyy").parse(s);
		    	}
		    	catch(ParseException ex){
		    		ex.printStackTrace();
		    	}
		    	bw.write(s+";"+emp.getTomador()+";");
		    	if(emp.getDevolucao().after(hoje)){
		    		
		    		bw.write("Não;0\n");
		    		
		    	}
		    	else{
		    		
		    		int dias = (int)((hoje.getTime() - emp.getDevolucao().getTime())/86400000);
		    		
		    		bw.write("Sim;"+dias+"\n");
		    		
		    		
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
	 * MÃ©todo responsÃ¡vel por gerar a WhishList
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
		
		saveFile.println("Tipo;MÃ­dia;GÃªnero;PreÃ§o");
		
		for (Midia midia : m) {
			
			if(midia.isDeseja())
			{
				switch(midia.getType())
				{
					case 'L':	saveFile.println("Livro;"+midia.getNome()+";"+midia.getGenero().getNome()+";R$ "+midia.getPreco());
						break;
					case 'F':	saveFile.println("Filme;"+midia.getNome()+";"+midia.getGenero().getNome()+";R$ "+midia.getPreco());
						break;
					case 'S': saveFile.println("SÃ©rie;"+midia.getNome()+";"+midia.getGenero().getNome()+";R$ "+midia.getPreco());
						break;
					default:
				}
			}
			
			
			
		}
		file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	
	public static void generatorEstatisticas(List<Midia> m, Map<String,Genero> g)
	{
		int horasConsumidas = 0;
		int horasConsumir = 0;
		List<Serie> listSerie = new ArrayList<>();
		
		
	
		for (Midia midia : m) {
			if(midia.isConsumiu())
				horasConsumidas += midia.getTamanho();
			else
				horasConsumir += midia.getTamanho();
			
			if(midia.getType() == 'S')
				listSerie.add((Serie) midia);
		}
		
		FileWriter file;
		try {
			file = new FileWriter("1-estatisticas.txt");
			PrintWriter saveFile = new PrintWriter(file);
			saveFile.println("Horas consumidas: "+horasConsumidas+" minutos\nHoras a consumir: "+horasConsumir+" minutos\n");
			saveFile.println("Mídias por gênero:");
			
			for(Entry<String, Genero> genero: g.entrySet())
			{
				int contador = 0;
				for (Midia midia : m) {
					if(genero.getValue().getNome().equals(midia.getGenero()))
						contador++;
				}
				saveFile.println("\r"+genero.getValue().getNome()+": "+contador+"\n");
				
			}
			
			Serie comparator = new Serie();
//			Collections.sort(listSerie,comparator);
			saveFile.println("Temporadas por série:");
			for (Serie serie : listSerie) {
				int contAssistida = 0, contAssistir = 0;
				if(serie.isConsumiu())
					contAssistida++;
				else
					contAssistir++;
				
				saveFile.println("\r"+serie.getNome()+": "+contAssistida+"assistidas");
			}
			
			
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	
	}
	
	public static void writeMidiaPessoas(List<Midia> m, List<Pessoa> p){
		
		int i;
		//Collections.sort(p);
		BufferedWriter bw = null;
		
		try
		{
		    bw = new BufferedWriter( new FileWriter("2-porpessoa.csv"));
		    bw.write("Pessoa;Produção\n");
		    
		    for(Pessoa pes : p){
		    	
		    	List<String> temp = new LinkedList<String>();
		    	
		    	for(Midia mid : m){
		    		
		    		switch(mid.getType()){
		    		
		    		case 'L':
		    			for(Pessoa ptemp: ((Livro)mid).getAutores()){
		    				if(pes.getNome().equals(ptemp.getNome())){
		    					temp.add(mid.getNome());
		    					break;
		    				}
		    			}
		    			break;
		    		
		    		case 'F':
		    			if(((Filme)mid).getDiretor().getNome().equals(pes.getNome()))
		    				temp.add(mid.getNome());
		    			else
		    				for(Pessoa ptemp: ((Filme)mid).getAtores()){
			    				if(pes.getNome().equals(ptemp.getNome())){
			    					System.out.println(pes.getNome());
			    					temp.add(mid.getNome());
			    					break;
			    				}
		    				}
		    			break;
		    			
		    		case 'S':
		    			for(Pessoa ptemp: ((Serie)mid).getAtores())
		    				if(pes.getNome().equals(ptemp.getNome())){
		    					temp.add(mid.getNome());
		    					break;
		    				}
		    			break;
		    		
		    		}
		    	
		    	}
		    	
		    	Collections.sort(temp);
	    		
	    		if(temp.size()>0){
	    			bw.write(pes.getNome()+";");
	    			for(i=0;i<temp.size()-1;i++){
	    				bw.write(temp.get(i)+", ");
	    			}
	    			bw.write(temp.get(i)+"\n");
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
	

}