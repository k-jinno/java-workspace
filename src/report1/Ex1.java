package report1;

import calc.Calc;

public class Ex1 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		double [][] A = {{2,1,2},{1,1,3}};
		double[][] B=Calc.Trans_Mat(A);
		Calc.printMat(B);

		double [] b = {4,7};
		double [] x= {0,3};
		Calc.printVec(CG.CGalg(x, b, A));

	}

}
