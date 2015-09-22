public class classifier {
	String Name;
	double range;
	String symbol;
	int error;
	double epsilon;
	double G;
	boolean[] correctness;
	int[] predictedoutput;

	public classifier(int excount) {
		this.Name = null;
		this.range = 0.0;
		this.symbol = null;
		this.error = 0;
		this.epsilon = 0.0;
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

	public double getepsilon() {
		return epsilon;
	}

	public void setepsilon(double y) {
		this.epsilon = y;
	}

	public double getG() {
		return G;
	}

	public void setG(double g) {
		G = g;
	}

	public int geterror() {
		return error;
	}

	public void seterror(int y) {
		this.error = y;
	}

}
