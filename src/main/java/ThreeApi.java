import java.io.IOException;
import java.util.Scanner;

public class ThreeApi {

	static void MainMune(){
		
		System.out.println("Please Choice one Option:");
		
		System.out.println("1. Read From Authors Api :");
		
		System.out.println("2. Read From Articals Api :");
		
		System.out.println("3. Read From Sections Api :");
		
		System.out.println("4. Insert into Authors Table :");
		
		System.out.println("5. Insert into Articals Table :");
		
		System.out.println("6. Insert into Sections Table :");
		
	}
	
	
	
	
	
	
	
	public static void main(String[] args) throws Exception {
		
		
		Scanner sc = new Scanner(System.in);
		MainMune();

		do {
			
			int select = sc.nextInt();
			switch(select) {
			
			
			case 1:
				AuthorsApi.AuthorsApi();
				break;
				
			case 2:
				ArticalsApi.ArticalsApi();
				break;
				
			case 3:
				SectionsApi.SectionsApi();
				break;
				
			case 4:
				AuthorsInsert.AuthorsInsert();
				break;	
				
			case 5:
				ArticalsInsert.ArticalsInsert();
				break;	
				
			case 6:
				SectionsInsert.SectionsInsert();
				break;		
				
				
				
		

			}	
		}
		while (true);

	}

}

 