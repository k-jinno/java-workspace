package report1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mathworks.engine.MatlabEngine;
import com.mathworks.engine.MatlabExecutionException;
import com.mathworks.engine.MatlabSyntaxException;

import calc.Calc;

public class Ex2 {

	public static void main(String[] args) throws IllegalArgumentException, IllegalStateException, InterruptedException, MatlabExecutionException, MatlabSyntaxException, ExecutionException {
		// TODO 自動生成されたメソッド・スタブ

		// ワークブック
        XSSFWorkbook workBook = null;
        // シート
        XSSFSheet sheet = null;
        // 出力ファイル
        FileOutputStream outPutFile = null;
        // 出力ファイルパス
        String outPutFilePath = null;
        // 出力ファイル名
        String outPutFileName = null;

        String[] RowTitle ={"開始時刻","終了時刻","処理時間","反復回数","出力解"};
        String[] CowTitle = {"ヤコビ前処理","SSOR","不完全コレスキー"};

		//Matlabで元の行列を作成
		MatlabEngine matEng = MatlabEngine.startMatlab();
		StringWriter output = new StringWriter();
//		matEng.eval("A = sprandsym(500,50,0.5,1);",output,null);
//	    matEng.eval("A = delsq(numgrid('S',20));",output,null);
//		matEng.eval("A = sprandsym(1000,10,0.8,1);",output,null);
		matEng.eval("A=gallery('tridiag',500,-50,50,-50);",output,null);
		System.out.println(output.toString());
		double [][]A = matEng.getVariable("A");
		double [] x = new double [A.length];
		for(int i=0;i<x.length;i++){
			x[i] = 1;
		}
		double [] b = new double [A.length];
		//Calc.printMat(A);
	   b = Calc.matVec(A, x);
	   for(int i=0;i<x.length;i++){
			x[i] = 10;
		}
	   double[][]M_1 =Pre_CG.Jacobi_pre(A);
	   double [][]M_2 = Pre_CG.SSOR_pre(A);
	   double[][]M_3 = Pre_CG.IncCholesky_pre(A);
	   double[]x_1 = Calc.copyVec(x);
	   double[]x_2 = Calc.copyVec(x);
	   double[]x_3 = Calc.copyVec(x);
	   double[]b_1 = Calc.copyVec(b);
	   double[]b_2 = Calc.copyVec(b);
	   double[]b_3 = Calc.copyVec(b);


	   System.out.println("ヤコビ前処理");
	   double []x1=CG.CG_pre_alg(x_1, b_1, A, M_1);
	   Calc.printVec(x1);
	   System.out.println("SSOR");
	   double []x2=CG.CG_pre_alg(x_2, b_2, A, M_2);
	   Calc.printVec(x2);
	   System.out.println("不完全コレスキー");
	   double []x3=CG.CG_pre_alg(x_3, b_3, A, M_3);
	   Calc.printVec(x3);




	   double[][] X={x1,x2,x3};

	   try {

           // ワークブックの作成
           workBook = new XSSFWorkbook();

           // シートの設定
           sheet = workBook.createSheet();
           workBook.setSheetName(0, "前処理の選び方による計算精度比較");
           sheet = workBook.getSheet("前処理の選び方による計算精度比較");

           // 初期行の作成
           XSSFRow row = sheet.createRow(2);

           // 「タイトル」のセルスタイル設定
           XSSFCellStyle titleCellStyle = workBook.createCellStyle();
           XSSFFont titleFont = workBook.createFont();
           titleFont.setFontName("游ゴシック");
           titleFont.setFontHeightInPoints((short)11);
           titleCellStyle.setFont(titleFont);

           for(int i=0;i<RowTitle.length;i++){
        	   XSSFCell cell = row.createCell(i+2);
               cell.setCellStyle(titleCellStyle);
               // セルに「タイトル」を設定
               cell.setCellValue(RowTitle[i]);
           }

           for(int i=0;i<CowTitle.length;i++){
        	   row = sheet.createRow(3 + i);
        	   XSSFCell cell = row.createCell(1);
        	   cell.setCellStyle(titleCellStyle);
               // セルに「タイトル」を設定
               cell.setCellValue(CowTitle[i]);
               for(int j=0;j<X[0].length;j++){
            	   cell = row.createCell(j+2);
            	   cell.setCellStyle(titleCellStyle);
            	   cell.setCellValue(X[i][j]);
               }
           }

           // エクセルファイルを出力
           try {

               // 現在の日付を取得
               Date date = new Date();
               SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

               //ファイルパス・ファイル名の指定
               outPutFilePath = "C:/Users/jinno/Desktop/Test/";
               outPutFileName = "PreCG4_" + dateFormat.format(date).toString() +  ".xlsx";

               // エクセルファイルを出力
               outPutFile = new FileOutputStream(outPutFilePath + outPutFileName);
               workBook.write(outPutFile);

               System.out.println("「" + outPutFilePath + outPutFileName + "」を出力しました。");

           }catch(IOException e) {
               System.out.println(e.toString());
           }

       }catch(Exception e) {
           System.out.println(e.toString());
       }





	}

}
