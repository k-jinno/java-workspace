package report1;

import calc.Calc;

public class CG {
	public static double[] CGalg(double[] x,double[] b,double[][]A){
		//初期値の設定
		double[] r = Calc.subVec(b,Calc.matVec(A,x));
		System.out.println("初期値r=");
		Calc.printVec(r);
		double[] p = Calc.copyVec(r);
		double e =0.1;
		int count = 1;
		while(true){

			double [] w = Calc.matVec(A, p);
			System.out.println("w=");
			Calc.printVec(w);
			double α = Calc.vecNorm2(r) * Calc.vecNorm2(r) / Calc.innProd(p, w);
			x = Calc.addVec(x, Calc.scalarMultiple(α, p));
			System.out.println("x=");
			Calc.printVec(x);
			double[]  r_pre = Calc.copyVec(r);
			r = Calc.subVec(r, Calc.scalarMultiple(α, w));
			System.out.println("r=");
			Calc.printVec(r);

			//収束判定
			if(Calc.vecNorm2(r) <= e * Calc.vecNorm2(b)){
				return x;
			}

			if(count == x.length){
			System.out.println("収束しません");
			return x;
		}

			double β = (Calc.vecNorm2(r) * Calc.vecNorm2(r)) / (Calc.vecNorm2(r_pre) * Calc.vecNorm2(r_pre));
			p = Calc.addVec(r, Calc.scalarMultiple(β, p));
			count++;
		}
	}

	public static double[] CG_pre_alg(double[] x,double[] b,double[][]A,double[][] M){
		//初期値の設定

		double[] r = Calc.subVec(b,Calc.matVec(A,x));
		double [] z = Calc.matVec(Calc.InvertMatirox(M), r);
		double[] p = Calc.copyVec(z);
		double e =0.1;
		int count = 1;
		while(true){

			double [] w = Calc.matVec(A, p);
			double α = Calc.innProd(r, z)/ Calc.innProd(p, w);
			x = Calc.addVec(x, Calc.scalarMultiple(α, p));
			double[]  r_pre = Calc.copyVec(r);
			r = Calc.subVec(r, Calc.scalarMultiple(α, w));

			//収束判定
			if(Calc.vecNorm2(r) <= e * Calc.vecNorm2(b)){
				System.out.println("反復回数" + count + "回で収束");
				return x;
			}

			if(count == 100000){
			System.out.println("収束しません");
			return x;
		}

			double[]z_pre = Calc.copyVec(z);
			z = Calc.matVec(Calc.InvertMatirox(M), r);
			double β = Calc.innProd(r, z) / Calc.innProd(r_pre, z_pre);
			p = Calc.addVec(z, Calc.scalarMultiple(β, p));
			count++;
		}
	}

}
