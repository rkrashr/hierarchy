package rk.tech;

import java.util.ArrayList;
import java.util.Arrays;

public class Regression {
	
	private static final double INF = Double.POSITIVE_INFINITY; 
	
	protected final double[] k;
	protected final double[] e;
	
	public Regression() {
		this(INF,INF,INF,INF);
	}
	
	public Regression(double k0, double k1, double e0, double e1) {
		k = new double[2];
		e = new double[2];

		k[0] = k0;
		k[1] = k1;
		
		e[0] = e0;
		e[1] = e1;
	}

	@Override
	public String toString() {
		return "Regression [k=" + Arrays.toString(k) + ", e=" + Arrays.toString(e) + "]";
	}
	
	public static Regression fit(ArrayList<Double> x, ArrayList<Double> y, int start, int end) {
		return new Regression();
	}
	
}