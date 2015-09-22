import java.util.*;

public class NewProbability
{
	double epsilon;
    double alpha =0.0;
    double Qright=0.0;
    double Qwrong=0.0;
    double Z =0.0;
    double[] newProbability;
	public double[] newProbabilityCalc(classifier bestclassifier,int excount,double[] inputdata,int[] output,double[] probability)
	{
		double [] preNormalizedProb = new double[excount];	
		boolean[] correct = new boolean[excount];
		newProbability = new double[excount];
		epsilon = bestclassifier.getepsilon();
		correct = bestclassifier.getCorrectness();
		alpha = (Math.log((1-epsilon)/epsilon))/2;
	    Qright = Math.exp((-alpha));
	    Qwrong= Math.exp(alpha);
	    for (int i=0;i<excount;i++)
	    {
	    	if(correct[i]==true)
	    		preNormalizedProb[i]= (probability[i] * Qright);
	    	else
	    		preNormalizedProb[i]= (probability[i] * Qwrong);
	    }
	    
	    for (int i=0;i<excount;i++)
	    {
	    	Z=Z+preNormalizedProb[i];
	    }
	    for (int i=0;i<excount;i++)
	    {
	    	newProbability[i] = preNormalizedProb[i]/Z;
	    }
	    
		return newProbability;
	}

}
