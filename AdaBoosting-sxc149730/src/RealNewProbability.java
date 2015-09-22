
public class RealNewProbability
{
	double[] preNormalizedProb;
	double[] newProbability;
	double Z;
	double weightpos;
	double weightneg;
	public double[] realNewProbabilityCalc(int Excount,double[] input, int[] output, double[]probability,int NoofIterations,double Epsilon,RealClassifier bestclassifier)
	{
		try
		{
			 weightpos=0.0;
			 weightneg=0.0;
			 Z = 0.0;
			preNormalizedProb = new double[Excount];
			newProbability = new double[Excount];
			int[] predictoutput = new int[Excount];
			predictoutput = bestclassifier.getPredictedoutput();
			weightpos = Math.log((bestclassifier.getPossitiveright() + Epsilon) / (bestclassifier.getNegativewrong() + Epsilon)) / 2;
			weightneg = Math.log((bestclassifier.getPossitivewrong() + Epsilon) / (bestclassifier.getNegativeright() + Epsilon)) / 2;
			//Gright = Math.
			for(int i=0; i<Excount; i++)
			{
				if(predictoutput[i] == 1)
				{
					preNormalizedProb[i] = probability[i] * Math.exp(-(output[i] * weightpos));
				}
				else
					preNormalizedProb[i] = probability[i] * Math.exp(-(output[i] * weightneg));
				
				Z = Z + preNormalizedProb[i];
			}
			for(int i=0; i<Excount; i++)
			{
				newProbability[i] = preNormalizedProb[i] / Z;
			}
			bestclassifier.setWeightpos(weightpos);
			bestclassifier.setWeightneg(weightneg);
			bestclassifier.setZ(Z);
			
		}//try
		catch(Exception e)
		{
			e.printStackTrace();
		}//catch
		
		return newProbability;
	}//func

}//class
