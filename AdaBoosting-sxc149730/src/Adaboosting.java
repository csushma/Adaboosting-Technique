import java.io.File;

import java.util.Scanner;



public class Adaboosting 
{
	 public static void main(String[] args) 
     {
		 
	        File file1 = new File("input.txt");
	        try 
	        {
	            Scanner Inputfile = new Scanner(file1);
	            
	            /*Copying data from the  input file */
	            
	            int NoofIterations = Integer.parseInt(Inputfile.next());
	            int Excount = Integer.parseInt(Inputfile.next());
	            double Epsilon = Double.parseDouble(Inputfile.next());
	            double[] input = new double[Excount];
	            int[] output = new int[Excount];
	            double[] probability = new double[Excount];
	            
	            for(int i=0; i<Excount; i++)
	            {
	            	input[i] = Double.parseDouble(Inputfile.next());
	            }
	            for(int i=0; i<Excount; i++)
	            {
	            	output[i] = Integer.parseInt(Inputfile.next());
	            }
	            for(int i=0; i<Excount; i++)
	            {
	            	probability[i] = Double.parseDouble(Inputfile.next());
	            }
	            
	            Inputfile.close();
	            
	            /*Binary Adaboosting*/
	            BinaryAdaBoosting BA1 = new BinaryAdaBoosting();
	            BA1.binaryAdaboost(Excount, input, output, probability, NoofIterations);
	            BA1.printoutput(NoofIterations,Excount);
	             
	             /* Real Adaboosting */
	             
	             RealAdaBoosting RA1 = new RealAdaBoosting();
	             RA1.realAdaBoost(Excount, input, output, probability, NoofIterations, Epsilon);
	             RA1.printoutput(NoofIterations,Excount);
	             
	              
	             
       
	        }//try
	        catch (Exception e)
	        {
	        	e.printStackTrace();
	        }
	        
	        
     }//main
}//class
