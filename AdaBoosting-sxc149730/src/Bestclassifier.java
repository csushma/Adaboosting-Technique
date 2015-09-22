
import java.util.*;

public class Bestclassifier
{
	int error1;
	int error2;
	double epsilon1 = 0.0;
	double epsilon2=0.0;
	double minepsilon=60000.0000;
	double minG=600000.0000;
	//boolean[] correctness;
	classifier bestclassifier;
	classifier[] Allclassfiers;  // size need to calculated excount+1
	
    public classifier classifiercal(int excount,double[] inputdata,int[] outputdata, double[] probability)
    {
    	try
    	{
    		Allclassfiers = new classifier[excount+1];
    		
    		//String str="hsgfh";
    		for(int i=0;i<=excount;i++)
    		{   
    			boolean[] tmpcorrectness1 = new boolean[excount];
    			boolean[] tmpcorrectness2 = new boolean[excount];
    			int[] predictedoutput1 = new int[excount];
    			int[] predictedoutput2 = new int[excount];
    			double range=0.0;
    			double G1=0.0;
    			double G2=0.0;		
    			if(i==0)
    				range = inputdata[i]-0.5;
    			else if(i< excount)
    				range = (inputdata[i-1] + inputdata[i])/2;
    			else 
    				range = inputdata[i-1]+0.5;
    			
    			error1=0;
    			error2=0;
    			epsilon1=0.0;
    			epsilon2=0.0;
    			for (int y=0;y<excount;y++)
    			{
    				tmpcorrectness1[y] = true;
    				tmpcorrectness2[y] = true;
    				predictedoutput1[y]=outputdata[y];
    				predictedoutput2[y]=outputdata[y];
    				if(inputdata[y]>range && outputdata[y]==1)
    				{
    					error1 = error1+1;
    					epsilon1 = epsilon1+probability[y]; 
    					tmpcorrectness1[y] = false; 
    					predictedoutput1[y]=-1;
    				}
    				if(inputdata[y]<range && outputdata[y]==-1)
    				{
    					error1 = error1+1;
    					epsilon1 = epsilon1+probability[y];
    					tmpcorrectness1[y] = false;
    					predictedoutput1[y]=1;
    				}
        		 
    				if(inputdata[y]>range && outputdata[y]==-1)
    				{
    					error2 = error2+1;
    					epsilon2 = epsilon2+probability[y];
    					tmpcorrectness2[y] = false;
    					predictedoutput2[y]=1;
    				}
    				if(inputdata[y]<range && outputdata[y]==1)
    				{
    					error2 = error2+1;
    					epsilon2 = epsilon2+probability[y];
    					tmpcorrectness2[y] = false;
    					predictedoutput2[y]=-1;
    				}
        		 
    			}
    			if (epsilon1<=epsilon2)
    			{
    				classifier C1 = new classifier(excount);
    				C1.setName("H"+(i+1));//name need to be automated
    				C1.setrange(range);
    				C1.setSign("<");
    				C1.setepsilon(epsilon1);
    				C1.seterror(error1);
    				C1.setCorrectness(tmpcorrectness1);
    				C1.setPredictedoutput(predictedoutput1);
    				Allclassfiers[i] = C1;
    				if(minepsilon > epsilon1)
    				{
    					minepsilon = epsilon1;
    					bestclassifier = C1;
    				}
    			}
    			else
    			{
    				classifier C1 = new classifier(excount);
    				C1.setName("H"+(i+1));//name need to be automated
    				C1.setrange(range);
    				C1.setSign(">"); 
    				C1.setepsilon(epsilon2);
    				C1.seterror(error2);
    				C1.setCorrectness(tmpcorrectness2);
    				C1.setPredictedoutput(predictedoutput2);
    				Allclassfiers[i] = C1;
    				if(minepsilon > epsilon2)
    				{
    					minepsilon = epsilon2;
    					bestclassifier = C1;
    				}
    			}
    			
        	    
        	  
    		}
    		
    		//return bestclassifier;
    	}//try	
    		catch (Exception e)
        	{
    			e.printStackTrace();
        	}
    	return bestclassifier;
    	
    }//func
    
    
}//class
