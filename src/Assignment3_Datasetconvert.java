import java.io.BufferedReader;  
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;  
public class Assignment3_Datasetconvert
{  
public static void main(String[] args)   
{  
String line = "";  
String splitBy = ",";  
try   
{  
//parsing a CSV file into BufferedReader class constructor  
BufferedReader br = new BufferedReader(new FileReader("Mud_Weight.csv"));
br.readLine();
br.readLine();
FileWriter fw = new FileWriter("Mud_Weight_PI.csv");
PrintWriter out = new PrintWriter(fw);
while ((line = br.readLine()) != null)   //returns a Boolean value  
{ 
	
String[] DATA = line.split(splitBy);    // use comma as separator  
System.out.println(Double.parseDouble(DATA[0])*Math.PI);
System.out.println(Double.parseDouble(DATA[1])*Math.PI);
out.print((Double.toString(Double.parseDouble(DATA[0])*Math.PI)));
out.print(",");
out.print((Double.toString(Double.parseDouble(DATA[1])*Math.PI)));
out.print("\r\n");

}  
//Flush the output to the file
out.flush();
    
//Close the Print Writer
out.close();
    
//Close the File Writer
fw.close(); 
}   
catch (IOException e)   
{  
e.printStackTrace();  
}  
}  
}