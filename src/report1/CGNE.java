package report1;

import calc.Calc;

public class CGNE {
	public static double[] CGNE_method(double[][] A ,double [] x,double[]b){
		double e = 0.1;
		double[] r = Calc.subVec(b, Calc.matVec(A, x));
		double[] p = Calc.matVec(Calc.Trans_Mat(A), r);
		while(true){
			double α = Calc.innProd(r, r) / Calc.innProd(p, p);
			x = Calc.addVec(x, Calc.scalarMultiple(α, x));
			double[] r_pre = Calc.copyVec(r);
			r = Calc.subVec(r, Calc.scalarMultiple(α, Calc.matVec(A, p)));

			//収束判定
			if(Calc.vecNorm2(r) <= e * Calc.vecNorm2(b)){
				return x;
			}

			double β = Calc.innProd(r, r) / Calc.innProd(r_pre, r_pre);
			p = Calc.addVec(Calc.matVec(Calc.Trans_Mat(A), r), Calc.scalarMultiple(β, p));


		}
	}

}
