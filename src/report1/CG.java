package report1;

import calc.Calc;

public class CG {
	public static double[] CGalg(double[] x,double[] b,double[][]A){
		//時間計測開始
		long startTime = System.nanoTime();
		//初期値の設定
		double[] r = Calc.subVec(b,Calc.matVec(A,x));
		double[] p = Calc.copyVec(r);
		double e =0.1;
		int count = 1;
		while(true){

			double [] w = Calc.matVec(A, p);
			double α = Calc.vecNorm2(r) * Calc.vecNorm2(r) / Calc.innProd(p, w);
			x = Calc.addVec(x, Calc.scalarMultiple(α, p));
			double[]  r_pre = Calc.copyVec(r);
			r = Calc.subVec(r, Calc.scalarMultiple(α, w));

			//収束判定
			if(Calc.vecNorm2(r) <= e * Calc.vecNorm2(b)){
				long endTime = System.nanoTime();
				System.out.println("開始時刻：" + startTime + " ナノ秒");
		        System.out.println("終了時刻：" + endTime + " ナノ秒");
		        System.out.println("処理時間：" + (endTime - startTime) + " ナノ秒");
				System.out.println("反復回数は" + count + "回");
				return x;
			}

			if(count == x.length){
			long endTime = System.nanoTime();
			System.out.println("開始時刻：" + startTime + " ナノ秒");
	        System.out.println("終了時刻：" + endTime + " ナノ秒");
	        System.out.println("処理時間：" + (endTime - startTime) + " ナノ秒");
			System.out.println("収束しません");
			return x;
		}

			double β = (Calc.vecNorm2(r) * Calc.vecNorm2(r)) / (Calc.vecNorm2(r_pre) * Calc.vecNorm2(r_pre));
			p = Calc.addVec(r, Calc.scalarMultiple(β, p));
			count++;
		}
	}

	public static double[] CG_pre_alg(double[] x,double[] b,double[][]A,double[][] M){
		//時間計測開始
		long startTime = System.nanoTime();
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
				long endTime = System.nanoTime();
				System.out.println("開始時刻：" + startTime + " ナノ秒");
		        System.out.println("終了時刻：" + endTime + " ナノ秒");
		        System.out.println("処理時間：" + (endTime - startTime) + " ナノ秒");
				System.out.println("反復回数" + count + "回で収束");
				double[] x2 = new double[x.length + 4];
				for(int i=0;i<x2.length;i++){
					if(i==0){
						x2[i] = startTime;
					}else if(i == 1){
						x2[i] = endTime;
					}else if(i == 2){
						x2[i] = endTime - startTime;
					}else if(i == 3){
						x2[i] = count;
					}else{
						x2[i] = x[i-4];
					}
				}
				return x2;
			}

			if(count == A.length){
			long endTime = System.nanoTime();
			System.out.println("開始時刻：" + startTime + " ナノ秒");
	        System.out.println("終了時刻：" + endTime + " ナノ秒");
	        System.out.println("処理時間：" + (endTime - startTime) + " ナノ秒");
			System.out.println("収束しません");
			double[] x2 = new double[x.length + 4];
			for(int i=0;i<x2.length;i++){
				if(i==0){
					x2[i] = startTime;
				}else if(i == 1){
					x2[i] = endTime;
				}else if(i == 2){
					x2[i] = endTime - startTime;
				}else if(i == 3){
					x2[i] = count;
				}else{
					x2[i] = x[i-4];
				}
			}
			return x2;
		}

			double[]z_pre = Calc.copyVec(z);
			z = Calc.matVec(Calc.InvertMatirox(M), r);
			double β = Calc.innProd(r, z) / Calc.innProd(r_pre, z_pre);
			p = Calc.addVec(z, Calc.scalarMultiple(β, p));
			count++;
		}
	}

}
