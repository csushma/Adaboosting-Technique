
public class RealClassifier
{
	String Name;
	double range;
	String symbol;
	double G;
	double possitiveright=0.0;double negativeright=0.0;double possitivewrong=0.0;double negativewrong=0.0;
	int posright =0; int negright=0; int poswrong=0; int negwrong=0;
	boolean[] correctness;
	int[] predictedoutput;
	double[] probability;
	double weightpos;
	double weightneg;
	double Z;

	public RealClassifier(int excount) {
		this.Name = null;
		this.range = 0.0;
		this.symbol = null;
		this.G=0.0;
		this.correctness = new boolean[excount];
		this.predictedoutput= new int[excount];
	}

	public boolean[] getCorrectness() {
		return correctness;
	}

	public void setCorrectness(boolean[] correctness) {
		this.correctness = correctness;
	}

	public int[] getPredictedoutput()
	{
		return predictedoutput;
	}

	public void setPredictedoutput(int[] predictedoutput) 
	{
		this.predictedoutput = predictedoutput;
	}

	/*
	 * public classifier(String name,double range,String symbol,int error,double
	 * epsilon) { this.Name=name; this.range=range; this.symbol=symbol;
	 * this.error=error; this.epsilon =epsilon; }
	 */
	public String getName() {
		return Name;
	}

	public void setName(String x) {
		this.Name = x;
	}

	public double getrange() {
		return range;
	}

	public void setrange(double y) {
		this.range = y;
	}

	public String getSign() {
		return symbol;
	}

	public void setSign(String sign) {
		this.symbol = sign;
	}

	public double getG() {
		return G;
	}

	public void setG(double g) {
		G = g;
	}

	public double getPossitiveright() {
		return possitiveright;
	}

	public void setPossitiveright(double possitiveright) {
		this.possitiveright = possitiveright;
	}

	public double getNegativeright() {
		return negativeright;
	}

	public void setNegativeright(double negativeright) {
		this.negativeright = negativeright;
	}

	public double getPossitivewrong() {
		return possitivewrong;
	}

	public void setPossitivewrong(double possitivewrong) {
		this.possitivewrong = possitivewrong;
	}

	public double getNegativewrong() {
		return negativewrong;
	}

	public void setNegativewrong(double negativewrong) {
		this.negativewrong = negativewrong;
	}

	public int getPosright() {
		return posright;
	}

	public void setPosright(int posright) {
		this.posright = posright;
	}

	public int getNegright() {
		return negright;
	}

	public void setNegright(int negright) {
		this.negright = negright;
	}

	public int getPoswrong() {
		return poswrong;
	}

	public void setPoswrong(int poswrong) {
		this.poswrong = poswrong;
	}

	public int getNegwrong() {
		return negwrong;
	}

	public void setNegwrong(int negwrong) {
		this.negwrong = negwrong;
	}

	public double[] getProbability() {
		return probability;
	}

	public void setProbability(double[] probability) {
		this.probability = probability;
	}

	public double getWeightpos() {
		return weightpos;
	}

	public void setWeightpos(double weightpos) {
		this.weightpos = weightpos;
	}

	public double getWeightneg() {
		return weightneg;
	}

	public void setWeightneg(double weightneg) {
		this.weightneg = weightneg;
	}

	public double getZ() {
		return Z;
	}

	public void setZ(double z) {
		Z = z;
	}

	


}
