import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
/**
 * CLASSE RESPONSÁVEL PELA LEITURA E ESCRITA DE ARQUIVOS
 * 
 * @author Igor Ventorim (IVentorim) e Fernando Neto(Febane)
 *
 */
public class FileIO {
	
	private Scanner scanner;
	/**
	 * 
	 * @param generosFile - Nome do arquivo que contem a lista de generos para leitura
	 * @return ArrayLIst de generos guardado na memória
	 * @throws FileNotFoundException
	 */
	public Map<String,Genero> readGenero(String generosFile) throws FileNotFoundException
	{
		//List<Genero> listGenero = new ArrayList<>();
		Map<String,Genero> mapGenero = new HashMap<>();
		
		scanner = new Scanner(new FileReader(generosFile));
		scanner = scanner.useDelimiter(";\\n");
		
		System.out.println(scanner.next());
		System.out.println(scanner.next());	
		
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
	public List<Pessoa> readPessoa(String pessoaFile) throws FileNotFoundException
	{
		List<Pessoa> listPessoa = new ArrayList<>();
		
		scanner = new Scanner(new FileReader(pessoaFile));
		scanner = scanner.useDelimiter(";\\n");
		
		System.out.println(scanner.next());
		System.out.println(scanner.next());

		while(scanner.hasNext())
		{
			int cod = scanner.nextInt();
			String nome = scanner.next();
			System.out.println(cod+":"+nome);
			listPessoa.add(new Pessoa(cod,nome));
		}
		scanner.close();
		return listPessoa;
	}
	
	/**
	 * 
	 * @param midiaFile
	 * @param listPessoas
	 * @param listGenero
	 * @return
	 * @throws FileNotFoundException 
	 */
	public List<Midia> readMidia(String midiaFile,List<Pessoa> listPessoas, Map<String,Genero> mapGenero) throws FileNotFoundException
	{
		List<Midia> listMidia = new ArrayList<>();
		
		scanner = new Scanner(new FileReader(midiaFile));
		scanner = scanner.useDelimiter(";\\n");
		
		// LOOP para descartar a linha de descrição do arquivo
		for(int i = 0; i < 13; i++)
			System.out.println(scanner.next());
				
		while(scanner.hasNext())
		{
			int codigo = scanner.nextInt();
			String nome = scanner.next();
			char type = scanner.next().charAt(0);
			Pessoa diretor  = listPessoas.get(scanner.nextInt()-1);
			//Pessoa autor = listPessoas.get(scanner.nextInt()-1);
			int tamanho = scanner.nextInt();
			Genero gnr = mapGenero.get(scanner.next());
			String serie = scanner.next();
			int temporada = scanner.nextInt();
			boolean possui = scanner.next().equals("x");
			boolean consumiu = scanner.next().equals("x");
			boolean deseja = scanner.next().equals("x");
			double preco = scanner.nextDouble();
			
			switch(type)
			{
				case 'L':	listMidia.add(new Livro(codigo,nome,tamanho,gnr,possui,consumiu,deseja,preco, listPessoas));
					break;
				case 'F':	listMidia.add(new Filme(codigo,nome,tamanho,gnr,possui,consumiu,deseja,preco,diretor,listPessoas));
					break;
				case 'S':	listMidia.add(new Serie(codigo,nome,tamanho,gnr,possui,consumiu,deseja,preco,listPessoas,temporada,serie));
					break;
				default: System.out.println("Este tipo de midia não pode ser cadastrado!");
			}
			
		}
		
		for (Midia midia : listMidia) {
			System.out.println(midia);
		}
		
		
		return listMidia;
	}

}
