import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.List;
import java.util.Map;


public class Main {
	
	private static ObjectInputStream ois;
	private static ObjectOutputStream oos;

	public static void main(String[] args) throws IOException, ParseException {
		
		List<Pessoa> p = null;
		Map<String,Genero> g = null;
		List<Midia> m = null;
		List<Emprestimo> e = null;
		boolean ro;
		int idx;
	
		if(isIn(args, "--write-only")!=-1){
			
			try{
				FileInputStream fis = new FileInputStream("inventario.dat");
				ois = new ObjectInputStream(fis);
				
				g = ((Map<String, Genero>) ois.readObject());
				p = ((List<Pessoa>) ois.readObject());
				m = ((List<Midia>) ois.readObject());
				e = ((List<Emprestimo>) ois.readObject());
				
				FileIO.generatorWishList(m);
				FileIO.writeMidiaPessoas(m,p);
				FileIO.generatorEstatisticas(m, g);
				FileIO.writeEmprestimos(e);
				
			}
			catch(IOException ex){
				ex.printStackTrace();
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			
		}
		else{
			
			if(isIn(args, "--read-only")!=-1)
				ro = true;
			else
				ro = false;
			
			idx = isIn(args, "-g");
			if(idx!=-1){
				
				g = FileIO.readGenero(new File(".").getCanonicalPath()+"/"+args[idx+1]);
				
			}
			
			idx = isIn(args, "-p");
			if(idx!=-1){
				
				p = FileIO.readPessoa(new File(".").getCanonicalPath()+"/"+args[idx+1]);
				
			}
			
			idx = isIn(args, "-m");
			if(idx!=-1){
				
				m = FileIO.readMidia(new File(".").getCanonicalPath()+"/"+args[idx+1],p,g);
				
				if(!ro){
					
					FileIO.generatorWishList(m);
					FileIO.writeMidiaPessoas(m,p);
					FileIO.generatorEstatisticas(m, g);
					
				}
				
			}
			
			idx = isIn(args, "-e");
			if(idx!=-1){
				
				e = FileIO.readEmprestimos(new File(".").getCanonicalPath()+"/"+args[idx+1], m);
				
				if(!ro){
					
					FileIO.writeEmprestimos(e);
					
				}
				
			}
			
			if(ro){
				
				try{
					FileOutputStream fos = new FileOutputStream("inventario.dat");
					oos = new ObjectOutputStream(fos);
					
					oos.writeObject(g);
					oos.writeObject(p);
					oos.writeObject(m);
					oos.writeObject(e);
				}
				catch(IOException ex){
					ex.printStackTrace();
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
				
			}
			
		}
		
	}
	
	public static int isIn(String[] args, String param){
		
		int i;
		
		for(i=0;i<args.length;i++)
			if(param.equals(args[i]))
				return i;
		
		return -1;
		
	}
	
}