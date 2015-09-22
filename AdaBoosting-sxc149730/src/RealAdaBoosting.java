import java.io.FileWriter;
import java.io.PrintWriter;


public class RealAdaBoosting 
{
	RealClassifier[] bestRealClassifier;
	double[][] newProbability;
	double[][] boostedclassifier;
	int[][] boostedoutput;
	int[] mistakes;
	double[] Error;
    double[] errorBound ;
	
	public void realAdaBoost(int Excount,double[] input, int[] output, double[]probability,int NoofIterations,double Epsilon)
	{
			try
			{
				bestRealClassifier = new RealClassifier[NoofIterations] ;
				newProbability = new double[NoofIterations][Excount];
				double[][] allProbability = new double[NoofIterations][Excount];
				boostedclassifier = new double[NoofIterations][Excount];
	            boostedoutput = new int[NoofIterations][Excount];
	            mistakes = new int[NoofIterations];
	            Error=new double[NoofIterations];
	            errorBound = new double[NoofIterations];
	            double[] prevBoostedclassifier = new double[Excount];
	            double[] Z = new double[NoofIterations];
				for(int t=0; t<NoofIterations;t++)
			    {
					/* Best Classifier */
					
					RealBestclassifier B1 = new RealBestclassifier();             
					bestRealClassifier[t]= B1.realAdaClassifiercal(Excount, input, output, probability, Epsilon);
	             
	             /*probability calc */
					
	              allProbability[t] = probability;
	              RealNewProbability NP1 = new RealNewProbability();
	              newProbability[t] = NP1.realNewProbabilityCalc(Excount, input, output, probability, NoofIterations, Epsilon, bestRealClassifier[t]);
	             
	              /* Boosted Classifier output*/
	              
	              double weightpos = NP1.weightpos;
	              double weightneg = NP1.weightneg;
	              Z[t] = NP1.Z;
	              int[] predictedoutput = new int[Excount];
	              predictedoutput = bestRealClassifier[t].getPredictedoutput();
	             
	              
	             for (int i=0;i<Excount;i++)
	             {
	            	 if(predictedoutput[i] == 1)
	            	 {   
	            		
	            		boostedclassifier[t][i] = prevBoostedclassifier[i] + weightpos;
	            		
	            		 if(boostedclassifier[t][i] > 0.0)
	            			 boostedoutput[t][i] =1;
	            		 else
	            			 boostedoutput[t][i] =-1;
	            	 }
	            	 else
	            	 {
	            		 
	            		 boostedclassifier[t][i] = prevBoostedclassifier[i] + weightneg;
	            		 
	            		 if(boostedclassifier[t][i] > 0.0)
	            			 boostedoutput[t][i] =1;
	            		 else
	            			 boostedoutput[t][i] =-1;
	            	 }
	            	 if(boostedoutput[t][i] != output[i])
	            	 {
	            		 mistakes[t] = mistakes[t] +1;
	            		 //Error[t] = Error[t] + probability[i];
	            	 }
	            		 
	             }
	             probability = newProbability[t];
	             prevBoostedclassifier = boostedclassifier[t];
			  }  
				
				double prevbound =0.0;
				for(int t=0; t<NoofIterations;t++)
				{
					Error[t]=(double) mistakes[t]/Excount;
					if(t>0)
					{
						errorBound[t] = prevbound * Z[t];
					}
					else
					{
						errorBound[t] = Z[t];
											
					}
					prevbound= errorBound[t];
				}
	             
	             //System.out.println(newProbability);
	             
			}//try
			catch(Exception e)
			{
				e.printStackTrace();
			}//catch
	}//func
	
	public void printoutput(int NoofIterations, int Excount)
	{
		try
		{
		 System.out.println("======= Real AdaBoosting Output========");
		 PrintWriter out = new PrintWriter(new FileWriter("output_Real.txt"));
		 out.println("======= Real AdaBoosting Output========");
		 out.println();
         for(int t=0; t<NoofIterations;t++)
         {
     	       	 
        	 out.println("For Iteration T:"+(t+1)); 
        	 out.println();
        	 out.print("Classifier: ");
        	 out.println(bestRealClassifier[t].getName()+bestRealClassifier[t].getSign()+bestRealClassifier[t].getrange());
        	 out.println();
        	 out.println("G value: "+bestRealClassifier[t].getG());
        	 out.println();
        	 out.println("C-pos: "+bestRealClassifier[t].getWeightpos()+" | "+"C-neg: "+bestRealClassifier[t].getWeightneg());
        	 out.println();
        	 out.println("Z value: "+bestRealClassifier[t].getZ());
        	 out.println();
        	 out.print("newProb(P): " );
        	 for(int i=0; i<Excount; i++)
        	 {
        		 out.print(newProbability[t][i]+" ");
        	 }
        	 out.println();
        	 out.println();
        	 out.print("fT(x): " );
        	 for(int i=0; i<Excount; i++)
        	 {
        		 out.print(boostedclassifier[t][i]+" ");
        	 }
        	 out.println();
        	 out.println();
        	 out.println("Error: "+Error[t] );
        	 out.println();
        	 out.println("Bound: "+errorBound[t]);
        	 out.println();
        	 out.println("=============================================================================");
         }
         out.close();
		}//try
		catch(Exception e)
		{
			e.printStackTrace();
		}//catch
	}//func
	
}//class
