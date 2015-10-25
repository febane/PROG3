import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class Main {
	
	
	public static void main(String[] args) throws IOException {
		
	
		Map<String,Genero> m = FileIO.readGenero(new File(".").getCanonicalPath()+"/src/generos.csv");
		List<Pessoa> p = FileIO.readPessoa(new File(".").getCanonicalPath()+"/src/pessoas.csv");
		
		//FileIO.readMidia("/home/igor/Documentos/PROG3/InventorySystem/src/midias.csv",p,m);
		
	}
	
}
