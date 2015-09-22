import java.io.FileWriter;
import java.io.PrintWriter;


public class BinaryAdaBoosting 
{
	double[][] newProbability;
    String[] boostedClassifier ;
    double[] alpha;
    int[] mistakes;
    int[][] predictedoutput;
    int[][] boostedoutput ;
    double[][] allprobability;
    //NewProbability  NP1 = new  NewProbability();
    classifier[] bestClassifier;
    double[] Z;
    double[] errorBound;
    double[] Error;
  
  public void binaryAdaboost(int Excount, double[] input, int [] output, double[] probability, int NoofIterations)
  {
	  
       try
       {
    	   	newProbability = new double[NoofIterations][Excount];
    	    boostedClassifier = new String[NoofIterations];
    	    alpha =new double[NoofIterations];
    	    mistakes =new int[NoofIterations];
    	     predictedoutput = new int[NoofIterations][Excount];
    	     boostedoutput = new int[NoofIterations][Excount];
    	     allprobability = new double[NoofIterations][Excount];
    	    //NewProbability  NP1 = new  NewProbability();
    	    bestClassifier=new classifier[NoofIterations];
    	    Z = new double[NoofIterations];
    	    errorBound = new double[NoofIterations];
    	    Error = new double[NoofIterations];
    	    for(int t=0; t<NoofIterations;t++)
	  		{
	  			Bestclassifier B1 = new Bestclassifier();
	  			bestClassifier[t] = B1.classifiercal(Excount,input,output,probability);
	  			//System.out.println(bestClassifier);  
	  			//System.out.println("alpha value: "+alpha);
	  			NewProbability  NP1 = new  NewProbability();
	  			newProbability[t]= NP1.newProbabilityCalc(bestClassifier[t],Excount,input,output,probability );	
	  			alpha[t]= NP1.alpha ;
	  			Z[t] = NP1.Z;
	  			boostedClassifier[t] = alpha[t] +"*"+bestClassifier[t].getName()+"(x"+bestClassifier[t].getSign()+ bestClassifier[t].getrange() +")";
	  			//System.out.println(boostedClassifier[t]);
    
	  			predictedoutput[t] = bestClassifier[t].getPredictedoutput();
	  			allprobability[t] = probability;
	  			probability = newProbability[t];
   	
	  		}
    	    for (int t=0;t<NoofIterations;t++)
	  		{
   	 
	  				for (int i=0; i<Excount;i++)
	  				{
	  					double tmpboostedoutput =0.0;
	  					for(int j=0; j<=t;j++)
	  					{
	  						tmpboostedoutput = tmpboostedoutput+(alpha[j]*predictedoutput[j][i]); 			 
	  					}
	  					if(tmpboostedoutput > 0.0)
	  					{
	  						boostedoutput[t][i] = 1;
	  						if(boostedoutput[t][i] != output[i])
	  							mistakes[t] = mistakes[t] +1;
	  					}
   	 
	  					else
	  					{
	  						boostedoutput[t][i] =-1;
	  						if(boostedoutput[t][i] != output[i])
	  							mistakes[t] = mistakes[t] +1;
	  					}
   	 
	  				}
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
	  	
	  	
       }//try
       catch(Exception e)
       {
    	   e.printStackTrace();
       }
              
   // System.out.println("for binary adaboosting" +newProbability);
  }//func
  
  public void printoutput(int NoofIterations, int Excount)
	{
		try
		{
		 System.out.println("======= Binary AdaBoosting Output========");
		 PrintWriter out = new PrintWriter(new FileWriter("output_Binary.txt"));
		 String classifierName;
		 out.println("======= Binary AdaBoosting Output========");
		 out.println();
       for(int t=0; t<NoofIterations;t++)
       {
    	   classifierName=""; 	 
      	 out.println("For Iteration T:"+(t+1)); 
      	 out.println();
      	 out.print("Classifier: ");
      	 out.println(bestClassifier[t].getName()+bestClassifier[t].getSign()+bestClassifier[t].getrange());
      	 out.println();
      	 out.println("Epsilon value: "+bestClassifier[t].getepsilon());
      	 out.println();
      	 out.println("Alpha: "+alpha[t]);
      	 out.println();
      	 out.println("Z value: "+Z[t]);
      	 out.println();
      	 out.print("newProb(P): " );
      	 for(int i=0; i<Excount; i++)
      	 {
      		 out.print(newProbability[t][i]+" ");
      	 }
      	 out.println();
      	 out.println();
      	 out.print("boosted classifier: ");
      	 for(int i=0;i<=t;i++)
      	 {
      	    classifierName = classifierName+"+"+boostedClassifier[i];
      	 }
      	 out.println(classifierName);
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
