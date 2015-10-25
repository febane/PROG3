import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class Main {
	
	public static void main(String[] args) throws IOException {
		List<Pessoa> p = null;
		Map<String,Genero> m = null;
		boolean ro;
		int idx;
	
		if(isIn(args, "--write-only")!=-1){
			
			//TODO
			
		}
		else{
			
			if(isIn(args, "--read-only")!=-1)
				ro = true;
			else
				ro = false;
			
			idx = isIn(args, "-g");
			if(idx!=-1){
				
				m = FileIO.readGenero(new File(".").getCanonicalPath()+"/src/generos.csv");
				
			}
			
			idx = isIn(args, "-p");
			if(idx!=-1){
				
				p = FileIO.readPessoa(new File(".").getCanonicalPath()+"/src/pessoas.csv");
				
			}
			
			idx = isIn(args, "-m");
			if(idx!=-1){
				
				//TODO
				FileIO.readMidia(new File(".").getCanonicalPath()+"/src/midias.csv",p,m);
				
			}
			
			idx = isIn(args, "-e");
			if(idx!=-1){
				
				//TODO
				
			}
			
			if(ro){
				
				//TODO
				
			}
			else{
				
				//TODO
				
			}
			
		}
		
	}
	
	public static int isIn(String[] args, String param){
		
		int i;
		
		for(i=0;i<args.length;i++){
			
			if(param.equals(args[i]))
				return i;
			
		}
		
		return -1;
		
	}
	
}
