package calc;

public class Calc {

	//行列Aの転置
		public static double[][] Trans_Mat(double[][] A){
			double [][]B  = new double[A[0].length][A.length];
			for(int i = 0;i < A.length;i++){
				for(int j=0;j < A[0].length;j++){
					B[j][i] = A[i][j];
				}
			}
			return B;
		}

	public static double[][] reportA(int n){
		double [][]A=new double [n][n];
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(i==j){
					A[i][j]=7.0;
				}else if(i+1==j||i==j+1){
					A[i][j]=-1.0;
				}
			}
		}
		return A;
	}

	public static double[] reportB(double[][] A){
		double []x=new double[A.length];
		for(int i=0;i<A.length;i++){
			x[i]=1.0;
		}
		return Calc.matVec(A, x);
	}

	public static double[] reportX(int n){
		double []x=new double [n];
		for(int i=0;i<n;i++){
			x[i]=0;
		}
		return x;
	}

	//ベクトルの出力
	public static void printVec(double x[]){
		for(int i=0;i<x.length;i++){
			System.out.printf("%.16e\t",x[i]);
		}
		System.out.println();
	}

	//行列の出力
	public static void printMat(double A[][]){
		for(int i=0;i<A.length;i++){
			for(int j=0;j<A[0].length;j++){
				System.out.printf("%.3e\t",A[i][j]);
			}
			System.out.println();
		}
	}

	//ベクトルのコピー
	 public static double[] copyVec(double[] x){
	        double[] c = new double[x.length];
	        for(int i=0;i<x.length;i++){
	            c[i] = x[i];
	        }
	        return c;
	    }

	 //行列のコピー
	 public static double[][] copyMat(double[][] A){
	        double[][] C = new double[A.length]
	[A[0].length];
	        for(int i=0; i<A.length;i++){
	            for(int j=0; j<A[0].length; j++){
	                C[i][j] = A[i][j];
	            }
	        }
	        return C;
	    }

	//ベクトルのスカラー倍
	public static double[] scalarMultiple(double c,double x[]){
		double[]x1=copyVec(x);
		for(int i=0;i<x.length;i++){
			x1[i]=c*x1[i];
		}
		return x1;
	}

	//ベクトル同士の加算(x+y)
	public static double[] addVec(double x[],double y[]){
		if(x.length!=y.length){
			System.out.println("x.length!=y.length");
		}
		double c[]=new double[x.length];
		double[] x1=copyVec(x),y1=copyVec(y);
		for(int i=0;i<x.length;i++){
			c[i]=x1[i]+y1[i];
		}
		return c;
	}

	//ベクトル同士の減算(x-y)
	public static double[] subVec(double x[],double y[]){
		if(x.length!=y.length){
			System.out.println("x.length!=y.length");
		}
		double c[]=new double[x.length];
		double[] x1=copyVec(x),y1=copyVec(y);
		for(int i=0;i<x.length;i++){
			c[i]=x1[i]-y1[i];
		}
		return c;
	}

	//ベクトル同士の内積
	public static double innProd(double x[],double y[]){
		if(x.length!=y.length){
			System.out.println("x.length!=y.length");
		}
		double c=0;
		double[] x1=copyVec(x),y1=copyVec(y);
		for(int i=0;i<x.length;i++){
			c=c+x1[i]*y1[i];
		}
		return c;
	}

	//行列Aとベクトルxの積(Ax)
	public static double[] matVec(double A[][],double x[]){
		if(x.length!=A.length){
			System.out.println("x.length!=A.length");
		}
		double[][]A1=copyMat(A);
		double []y=new double[x.length],x1=copyVec(x);
		for(int i=0;i<x.length;i++){
			y[i]=innProd(A1[i],x1);
		}
		return y;
	}

	//行列Aとベクトルx,bに対してAx-b（残差という）を計算
	public static double[] residual(double [][]A,double x[] ,double b[]){
		if(x.length!=A.length){
			System.out.println("x.length!=A.length");
		}
		if(x.length!=b.length){
			System.out.println("x.length!=b.length");
		}
		double c[]=new double[x.length];
		double[][]A1=copyMat(A);
		double []b1=copyVec(b),x1=copyVec(x);
		for(int i=0;i<x.length;i++){
			c=subVec(matVec(A1,x1),b1);
		}

		return c;
	}

	//行列同士の演算
	public static double[][] addMat(double A[][],double B[][]){
		if(A.length!=B.length){
			System.out.println("A.length!=B.length");
		}
		if(A[0].length!=B[0].length){
			System.out.println("A[0].length!=B[0].length");
		}
		double [][] C=new double[A.length][A[0].length];
		double[][]A1=copyMat(A),B1=copyMat(B);
		for(int i=0;i<A.length;i++){
			C[i]=addVec(A1[i],B1[i]);
		}
		return C;
	}

	//行列同士の積(AB)
	public static double[][] multipleMat(double A[][],double B[][]){
		if(A[0].length!=B.length){
			System.out.println("A[0].length!=B.length");
		}
		double [][] C=new double[A.length][A[0].length];
		double[][]A1=copyMat(A),B1=copyMat(B);
		for(int i=0;i<A[0].length;i++){
			for(int j=0;j<A.length;j++){
				for(int k=0;k<A.length;k++){
					C[i][j]=A1[i][k]*B1[k][j]+C[i][j];
				}
			}
		}
		return C;
	}

	//逆行列を求める
	public static double[][] InvertMatirox(double a[][]){
		double[][]A_inv=new double[a.length][a.length];
		double[][]A=LU(a);
		System.out.println("A=");
		printMat(A);
		double[]y=new double[a.length];
		double []x=new double[a.length];
		for(int i=0;i<a.length;i++){
			double []e_i=new double[a.length];
			e_i[i]=1;
			y=Forward_sub(A,e_i);
			System.out.println("y=");
			printVec(y);
			x=Backward_sub(A,y);
			System.out.println("x=");
			printVec(x);
			for(int j=0;j<a.length;j++){
				A_inv[j][i]=x[j];
			}
		}
		return A_inv;
	}

	//ガウス消去法(ピボット無し)
	public static double[] Gaus(double[][]A,double[]b){
		double[][]A1=copyMat(A);
		double []b1=copyVec(b);
		double a;//中間変数
		for(int k=0;k<A.length-1;k++){
			for(int i=k+1;i<A.length;i++){
				a=A1[i][k]/A1[k][k];//中間変数の更新
				for(int j=k+1;j<A.length;j++){
					A1[i][j]=A1[i][j]-a*A1[k][j];//行列Ａの(i,j)成分A[i][j]を更新
				}
				b1[i]=b1[i]-a*b1[k];//ベクトルbのi成分b[i]を更新
			}
		}
		return Backward_sub(A1,b1);
	}

	//ガウス消去法（ピボットあり）
	public static double[] Pivot(double[][]A,double[]b){
		double[][]A1=copyMat(A);
		double []b1=copyVec(b);
		double a;//中間変数
		for(int k=0;k<A.length-1;k++){
			int pivot=k;
			double max=Math.abs(A[k][k]);
			for(int m=k+1;m<A.length;m++){
				if(max<Math.abs(A1[m][k])){
					max=Math.abs(A1[m][k]);
					pivot=m;
				}
			}
			if(pivot!=k){
				double[] temp1=A1[pivot];
				A1[pivot]=A1[k];
				A1[k]=temp1;
				double temp2=b1[pivot];
				b1[pivot]=b1[k];
				b1[k]=temp2;
			}
			for(int i=k+1;i<A.length;i++){
				a=A1[i][k]/A1[k][k];//中間変数の更新
				for(int j=k+1;j<A.length;j++){
					A1[i][j]=A1[i][j]-a*A1[k][j];//行列Ａの(i,j)成分A[i-1][j-1]を更新
				}
				b1[i]=b1[i]-a*b1[k];//行列bのi成分b[i-1]を更新
			}
		}
		return Backward_sub(A1,b1);
	}

	//前進代入
	public static double[]Forward_sub(double A[][],double b[]){
		double[]y=new double[b.length],b1=copyVec(b);
		double[][]A1=copyMat(A);
		double temp;
		for(int k=0;k<y.length;k++){
			temp=0;
			for(int j=0;j<k;j++){
				temp=temp+A1[k][j]*y[j];
			}
			y[k]=(b1[k]-temp)/A[k][k];
		}
		return y;
	}

	//後退代入
	public static double []Backward_sub(double A[][],double y[]){
		double c;
		double []x=new double[y.length],y1=copyVec(y);
		double[][]A1=copyMat(A);
		for(int k=A.length-1;k>=0;k--){
			c=0;
			for(int j=k+1;j<A.length;j++){
				c=c+A1[k][j]*x[j];
			}
			x[k]=(y1[k]-c)/A1[k][k];
		}
		return x;
	}

	//後退代入(コレスキー分解)
		public static double []Cholesky_Backward_sub(double A[][],double y[]){
			double c;
			double []x=new double[y.length],y1=copyVec(y);
			double[][]A1=copyMat(A);
			for(int k=A.length-1;k>=0;k--){
				c=0;
				for(int j=k+1;j<A.length;j++){
					c=c+A1[j][k]*x[j];
				}
				x[k]=(y1[k]-c)/A1[k][k];
			}
			return x;
		}

	//LU分解を行う
	public static double[][]LU(double a[][]){
		double[][]A=copyMat(a);
		double α;//中間変数
		for(int k=0;k<A.length-1;k++){
			for(int i=k+1;i<A.length;i++){
				α=A[i][k]/A[k][k];//中間変数の更新
				A[i][k]=α;
				for(int j=k+1;j<A.length;j++){
					A[i][j]=A[i][j]-α*A[k][j];//行列Ａの(i,j)成分A[i][j]を更新
				}
			}
		}
		return A;
	}

	//LU分解で解く
	public static double[]LUSolve(double a[][],double[]b){
		double[]b1=copyVec(b);
		double[][]A=copyMat(a);
		double α;//中間変数
		for(int k=0;k<A.length-1;k++){
			for(int i=k+1;i<A.length;i++){
				α=A[i][k]/A[k][k];//中間変数の更新
				A[i][k]=α;
				for(int j=k+1;j<A.length;j++){
					A[i][j]=A[i][j]-α*A[k][j];//行列Ａの(i,j)成分A[i][j]を更新
				}
			}
		}
		return Backward_sub(A,Forward_sub(A,b1));
	}

	public static double[] Jacobi(double[][]A,double []x,double[]b) {
		// TODO 自動生成されたメソッド・スタブ



		double []xtemp;
		xtemp=Calc.copyVec(x);
		double[]xtemp2=new double[x.length];

		double e=1e-8,temp;
		int M=200,m=1;
		while(true){
			for(int i=0;i<A.length;i++){
				temp=0;
				for(int j=0;j<A.length;j++){
					if(i!=j){
						temp=temp+A[i][j]*xtemp[j];//xtempをｘに変えるとGaussSeidel
					}
				}
				xtemp2[i]=(b[i]-temp)/A[i][i];
			}


			if((Calc.vecNorm2(Calc.subVec(xtemp, xtemp2)))<e){
				System.out.println("反復回数は"+m);
				return xtemp2;
			}
			if(M==m){
				System.out.println("収束しない");
				return xtemp2;
			}
			xtemp=Calc.copyVec(xtemp2);
			m++;

		}
	}

	public static double[] GaussSeidel(double[][]A,double []x,double[]b) {
		// TODO 自動生成されたメソッド・スタブ


		double []xtemp;
		xtemp=Calc.copyVec(x);
		double[]xtemp2=new double[x.length];
		xtemp2=Calc.copyVec(x);
		double e=1e-8,temp;
		int M=200,m=1;
		while(true){
			for(int i=0;i<A.length;i++){
				temp=0;
				for(int j=0;j<A.length;j++){
					if(i!=j){
						temp=temp+A[i][j]*xtemp2[j];//xtempをｘに変えるとGaussSeidel
					}
				}
				xtemp2[i]=(b[i]-temp)/A[i][i];
			}


			if((Calc.vecNorm2(Calc.subVec(xtemp, xtemp2)))<e){
				System.out.println("反復回数は"+m);
				return xtemp2;
			}
			if(M==m){
				System.out.println("収束しない");
				return xtemp2;
			}
			xtemp=Calc.copyVec(xtemp2);
			m++;

		}

	}
	//SOR法
	public static double[] SOR(double A[][],double[] x,double[] b,double w,double e,int M){
		double []xtemp1=new double[x.length];
		double []xtemp2=new double[x.length];
		xtemp1=Calc.copyVec(x);
		xtemp2=Calc.copyVec(x);
		double m=1,temp;
		while(true){
			for(int i=0;i<A.length;i++){
				temp=0;
				for(int j=0;j<A.length;j++){
					if(i!=j){
						temp=temp+A[i][j]*xtemp2[j];//xtempをｘに変えるとGaussSeidel
					}
				}
				xtemp2[i]=w*((b[i]-temp)/A[i][i])+(1-w)*xtemp1[i];
			}
			if(Calc.vecNorm2(Calc.subVec(xtemp1, xtemp2))<e){
				System.out.println(m);
				return xtemp2;
			}
			if(M==m){
				System.out.println("収束しない");
				return xtemp2;
			}
			m++;
			xtemp1=Calc.copyVec(xtemp2);
		}
	}

	//	ベクトルの1ノルムの計算
	public static double vecNorm1(double[]x){
		double norm=0;
		double[] x1=copyVec(x);
		for(int i=0;i<x.length;i++){
			norm=norm+Math.abs(x1[i]);
		}
		return norm;
	}

	//ベクトルの2ノルムの計算
	public static double vecNorm2(double[]x){
		double norm=0;
		double[] x1=copyVec(x);
		for(int i=0;i<x.length;i++){
			norm=norm+x1[i]*x1[i];
		}
		return Math.sqrt(norm);
	}

	//ベクトルのInfinityノルムの計算
	public static double vecNormInf(double[]x){
		double max=0;
		double[] x1=copyVec(x);
		for(int i=0;i<x.length;i++){
			if(max<=Math.abs(x1[i])){
				max=Math.abs(x1[i]);
			}
		}
		return max;
	}

	//行列の1ノルムの計算
	public static double matNorm1(double A[][]){
		double max=0;
		double temp;
		double[][]A1=copyMat(A);
		for(int j=0;j<A[0].length;j++){
			temp=0;
			for(int i=0;i<A.length;i++){
				temp=temp+Math.abs(A1[i][j]);
			}
			if(max<=temp){
				max=temp;
			}
		}
		return max;
	}

	//行列のInfnityノルムの計算
	public static double matNormInf(double[][]A){
		double max=0;
		double temp;
		double[][]A1=copyMat(A);
		for(int i=0;i<A.length;i++){
			temp=0;
			for(int j=0;j<A.length;j++){
				temp=temp+Math.abs(A1[i][j]);
			}
			if(max<=temp){
				max=temp;
			}
		}
		return max;
	}



}
