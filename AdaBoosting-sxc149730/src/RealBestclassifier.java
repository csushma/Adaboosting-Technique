



public class RealBestclassifier 
{
	
	double minG=600000.0000;
	RealClassifier bestclassifier;
	RealClassifier[] Allclassfiers;  // size need to calculated excount+1
	public RealClassifier realAdaClassifiercal(int excount,double[] inputdata,int[] outputdata, double[] probability,double epsilon)
    {
    	try
    	{
    		Allclassfiers = new RealClassifier[excount+1];
    		
    		//String str="hsgfh";
    		for(int i=0;i<=excount;i++)
    		{   
    			//System.out.print(" "+i);
    			boolean[] tmpcorrectness1 = new boolean[excount];
    			boolean[] tmpcorrectness2 = new boolean[excount];
    			int[] predictedoutput1 = new int[excount];
    			int[] predictedoutput2 = new int[excount];
    			double range=0.0;
    			if(i==0)
    				range = inputdata[i]-0.5;
    			else if(i< excount)
    				range = (inputdata[i-1] + inputdata[i])/2;
    			else 
    				range = inputdata[i-1]+0.5;
    			
    			double possitiveright1=0.0;double negativeright1=0.0;double possitivewrong1=0.0;double negativewrong1=0.0;
    			double possitiveright2=0.0;double negativeright2=0.0;double possitivewrong2=0.0;double negativewrong2=0.0;
    					
    			int posright1 =0; int negright1=0; int poswrong1=0; int negwrong1=0; 
    			int posright2 =0; int negright2=0; int poswrong2=0; int negwrong2=0; 
    			
    			double G1=0.0;
    			double G2=0.0;
    			
    			
    			for (int y=0;y<excount;y++)
    			{
    				tmpcorrectness1[y] = true;
    				tmpcorrectness2[y] = true;
    				predictedoutput1[y]=outputdata[y];
    				predictedoutput2[y]=outputdata[y];
    				
    				/* for classifier with"<" sign*/
    				if(inputdata[y]>range && outputdata[y]==1)
    				{
    					poswrong1 = poswrong1+1;
    					possitivewrong1 = possitivewrong1+probability[y]; 
    					tmpcorrectness1[y] = false; 
    					predictedoutput1[y]=-1;
    				}
    				if(inputdata[y]>range && outputdata[y]==-1)
    				{
    					negright1 = negright1+1;
    					negativeright1 = negativeright1+probability[y]; 
    				}
    				if(inputdata[y]<range && outputdata[y]==-1)
    				{
    					negwrong1 = negwrong1+1;
    					negativewrong1 = negativewrong1+probability[y];
    					tmpcorrectness1[y] = false;
    					predictedoutput1[y]=1;
    				}
    				if(inputdata[y]<range && outputdata[y]==1)
    				{
    					posright1 = posright1+1;
    					possitiveright1 = possitiveright1+probability[y];   					
    				}
    				
    				/* for classifier with">" sign*/
        		 
    				if(inputdata[y]>range && outputdata[y]==-1)
    				{
    					negwrong2 = negwrong2+1;
    					negativewrong2 = negativewrong2+probability[y];
    					tmpcorrectness2[y] = false;
    					predictedoutput2[y]=1;
    				}
    				if(inputdata[y]<range && outputdata[y]==1)
    				{
    					poswrong2 = poswrong2+1;
    					possitivewrong2 = possitivewrong2+probability[y];
    					tmpcorrectness2[y] = false;
    					predictedoutput2[y]=-1;
    				}
    				if(inputdata[y]>range && outputdata[y]==1)
    				{
    					posright2 = posright2+1;
    					possitiveright2 = possitiveright2 + probability[y];
    					
    				}
    				if(inputdata[y]<range && outputdata[y]==-1)
    				{
    					negright2 = negright2+1;
    					negativeright2 = negativeright2 + probability[y];
    					
    				}
        		 
    			}
    			
    			/* Calculating G value*/
    			
    			 G1 = Math.sqrt((possitiveright1*negativewrong1)) + Math.sqrt((negativeright1*possitivewrong1));
    			 G2 = Math.sqrt((possitiveright2*negativewrong2)) + Math.sqrt((negativeright2*possitivewrong2));
    			 
    			if (G1<=G2)
    			{
    				RealClassifier C1 = new RealClassifier(excount);
    				C1.setName("H"+(i+1));
    				C1.setrange(range);
    				C1.setSign("<");
    				C1.setG(G1);
    				C1.negativeright = negativeright1; C1.possitiveright = possitiveright1; C1.possitivewrong = possitivewrong1; C1.negativewrong = negativewrong1;
    				C1.negright=negright1; C1.posright=posright1; C1.negwrong = negwrong1; C1.poswrong = poswrong1;
    				//C1.seterror(error1);
    				C1.setCorrectness(tmpcorrectness1);
    				C1.setPredictedoutput(predictedoutput1);
    				C1.setProbability(probability);
    				Allclassfiers[i] = C1;
    				if(minG > G1)
    				{
    					minG = G1;
    					bestclassifier = C1;
    				}
    			}
    			else
    			{
    				RealClassifier C1 = new RealClassifier(excount);
    				C1.setName("H"+(i+1));//name need to be automated
    				C1.setrange(range);
    				C1.setSign(">"); 
    				C1.setG(G2);
    				//C1.seterror(error2);
    				C1.negativeright = negativeright2; C1.possitiveright = possitiveright2; C1.possitivewrong = possitivewrong2; C1.negativewrong = negativewrong2;
    				C1.negright=negright2; C1.posright=posright2; C1.negwrong = negwrong2; C1.poswrong = poswrong2;
    				C1.setCorrectness(tmpcorrectness2);
    				C1.setPredictedoutput(predictedoutput2);
    				C1.setProbability(probability);
    				Allclassfiers[i] = C1;
    				if(minG > G2)
    				{
    					minG = G2;
    					bestclassifier = C1;
    				}
    			}
    			
    			
    		}
    		
    	
    	}//try
    	catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	//System.out.println(bestclassifier);
    	return bestclassifier;
    	
    }//func
	

}
