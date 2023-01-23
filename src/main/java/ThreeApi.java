import java.io.IOException;
import java.util.Scanner;

public class ThreeApi {

	static void MainMune(){
		
		System.out.println("Please Choice one Option:");
		
		System.out.println("1. Read From Authors Api :");
		
		
		
	}
	
	
	
	
	
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		
		Scanner sc = new Scanner(System.in);
		MainMune();

		do {
			
			int select = sc.nextInt();
			switch(select) {
			
			
			case 1:
				AuthorsApi.AuthorsApi();
				break;
		

			}	
		}
		while (true);

	}

}

 