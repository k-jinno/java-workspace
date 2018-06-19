package report1;

import calc.Calc;

public class Pre_CG {

	public static double[][] Jacobi_pre(double [][] A){
		double[][] M = new double[A.length][A.length];
		for (int i=0;i<A.length;i++){
			for (int j=0;j<A.length;j++){
				if(i == j){
					M[i][j] = A [i][j];
				}else{
					M[i][j] = 0;
				}
			}
		}
		return M;
	}

	public static double [][] SSOR_pre(double [][] A){
		double[][] M = new double[A.length][A.length];
		double [][]D,E,F;
		D = Calc.copyMat(M);
		E = Calc.copyMat(M);
		F = Calc.copyMat(M);
		for(int i=0;i<A.length;i++){
			for(int j=0;j<A.length;j++){
				if(i==j){
					D[i][j]=A[i][j];
				}else if(i < j){
					F[i][j] = A[i][j];
				}else{
					E[i][j] = A[i][j];
				}
			}
		}
		M = Calc.multipleMat(Calc.multipleMat(Calc.addMat(D, E), Calc.InvertMatirox(D)), Calc.addMat(D, F));
		return M;
	}

	public static double[][] IncCholesky_pre(double[][]A){
		double[][] L = new double[A.length][A.length];
		double[][] D = new double[A.length][A.length];
		double sum1;
		for(int i=0;i<A.length;i++){
			for(int j=i;j<A.length;j++){
				sum1=0;
				for(int k=0;k<i;k++){
					sum1=sum1+L[j][k]*L[i][k];
				}
				if(j==i){
					L[j][j]=Math.sqrt(A[j][j]-sum1);
				}else if(A[j][i] == 0){
					L[j][i] = 0;
				}else{
					L[j][i]=(A[j][i]-sum1)/L[i][i];
				}
				System.out.print(j + "," + i + "=");
				System.out.println(L[i][j]);
			}
			D[i][i] = 1 / L[i][i];
		}
		Calc.printMat(L);
		return Calc.multipleMat(Calc.multipleMat(L, D),Calc.Trans_Mat(L) );
	}

}
