import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MyClass {
	
	public String input() {
		String option;
		Scanner sc = new Scanner(System.in);
		System.out.println("Select Option....");
		System.out.println("1) Select1");
		System.out.println("2) Select2");
		System.out.println("3) Update1");
		System.out.println("4) Update2");
		option = sc.nextLine();;
		System.out.println(option);
		if(option.equals("1")){
			return "select1";
		}
		else if(option.equals("2")){
			return "select2";
		}
		else if(option.equals("3")){
			return "update1";
		}
		else if(option.equals("4")) {
			return "update2";	
		}
		else {
			return "NV";	
		}
		
	} 

	public static void main(String[] args) {
		// Reading CSV File
		MyClass mc = new MyClass();
		String inputOption = mc.input();
				
		Scanner sc;
		String fileName = "doc/studentIds.csv";
		File file = new File(fileName);
		int iterator=0;
		String data = "";
		ArrayList<String> al = new ArrayList<>();
		try{
			FileWriter fwSelect1 = new FileWriter("doc/select1");
			FileWriter fwSelect2 = new FileWriter("doc/select2");
			sc = new Scanner(file);
			while(sc.hasNext()) {
				data = sc.next();
				if(iterator == 0) {
					iterator++;
					continue;
				}
				else {
					al.add(data);
					iterator++;
				}
			}
			
			for(String str: al) {
				//System.out.println(str);
				if(inputOption.equalsIgnoreCase("select1")) {
					System.out.println("select * from ps_student where student_id='"+str+"' and form_id=6367;");
					fwSelect1.write("select * from ps_student where student_id='"+str+"' and form_id=6367;"+"\n");
				}
				else if(inputOption.equals("select2")) {
					System.out.println("select * from ps_student_email_mapping where student_unique_id='"+str+"' and institution_id=1916;");
					fwSelect2.write("select * from ps_student_email_mapping where student_unique_id='"+str+"' and institution_id=1916;"+"\n");
				}
			}
			sc.close();
			fwSelect1.close();
			fwSelect2.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		try {
			BufferedReader br = null ;
		//	List<Integer> numbers = new ArrayList<>();
			FileWriter fwupdate1 = new FileWriter("doc/update1");
			FileWriter fwupdate2 = new FileWriter("doc/update2");
			File nFile = new File("/home/preetisaxena/Documents/1update.txt");
			FileWriter fwrite = new FileWriter(nFile);
			String line = null;
			int count = 0,flag=0;
			FileWriter fw = new FileWriter("doc/myFile");
			
			for(String element : al) {
			br = new BufferedReader(new FileReader("doc/studentTxt"));
				while((line = br.readLine()) != null) {
					String[] strNumbers = line.split("\t");
					ArrayList<String> sisId = new ArrayList<>();
					sisId.add(strNumbers[1]);
					String ss= strNumbers[1].substring(1, strNumbers[1].length());
					String str = ss+strNumbers[0]+"\n";
					//System.out.println(str+"-------------------------------------------------------------");
					/*if(strNumbers[0].contains(element)) {
						//System.out.println(strNumbers[0]+" ------ "+strNumbers[1]);
						count++;
						fw.write(count+"	"+strNumbers[0]+"	"+strNumbers[1]+"\n");
						
					}*/
					if(inputOption.equals("update1")){					
						fwupdate1.write(count+" update ps_student_email_mapping set student_unique_id='"+ss+"' where student_unique_id='"+strNumbers[0]+"' and institution_id=1916;"+"\n");
						fwupdate1.flush();
						/*count++;
						nFile.createNewFile();*/
						//fwrite.write(count+" update ps_student_email_mapping set student_unique_id='"+ss+"' where student_unique_id='"+strNumbers[0]+"' and institution_id=1916;");
						System.out.println("update ps_student_email_mapping set student_unique_id='"+ss+"' where student_unique_id='"+strNumbers[0]+"' and institution_id=1916;");
						
					}
					else if(inputOption.equals("update2")){
						System.out.println("update ps_student set student_id='"+ss+"' where student_id="+strNumbers[0]+" and form_id=6367;");
						fwupdate2.write("update ps_student set student_id='"+ss+"' where student_id="+strNumbers[0]+" and form_id=6367;"+"\n");
						fwupdate2.flush();
					}
					else if(inputOption.equals("NV")) {
						flag=1;
						break;
					}
				
				}
				if(flag==1) {
					System.out.println("Not a Valid Option. Please enter Valid Option.");
					break;
				}
			}
			
			fwupdate1.close();
			fwupdate2.close();
			fwrite.close();
			fw.close();
			br.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
