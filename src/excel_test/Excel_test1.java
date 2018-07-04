package excel_test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_test1 {

	public static void main(String[] args) {
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

        // エクセルファイルの作成
        try {

            // ワークブックの作成
            workBook = new XSSFWorkbook();

            // シートの設定
            sheet = workBook.createSheet();
            workBook.setSheetName(0, "九九の表");
            sheet = workBook.getSheet("九九の表");

            // 初期行の作成
            XSSFRow row = sheet.createRow(2);

            // 「タイトル」のセルスタイル設定
            XSSFCellStyle titleCellStyle = workBook.createCellStyle();
            XSSFCell cell = row.createCell(7);
            XSSFFont titleFont = workBook.createFont();
            titleFont.setFontName("ＭＳ ゴシック");
            titleFont.setFontHeightInPoints((short)36);
            titleFont.setUnderline(XSSFFont.U_SINGLE);
            titleCellStyle.setFont(titleFont);
            cell.setCellStyle(titleCellStyle);

            // セルに「タイトル」を設定
            cell.setCellValue("九九の表");

            // 「表のヘッダ」のセルスタイル設定
            XSSFCellStyle headerCellStyle = workBook.createCellStyle();
            XSSFFont headerFont = workBook.createFont();
            headerFont.setFontName("ＭＳ ゴシック");
            headerFont.setFontHeightInPoints((short)25);
            headerCellStyle.setFont(headerFont);
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
            headerCellStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.index);
            headerCellStyle.setBorderTop(BorderStyle.MEDIUM);
            headerCellStyle.setBorderBottom(BorderStyle.MEDIUM);
            headerCellStyle.setBorderRight(BorderStyle.MEDIUM);
            headerCellStyle.setBorderLeft(BorderStyle.MEDIUM);

            // セルに「表のヘッダ」を設定
            row = sheet.createRow(5);
            // 横
            for(int i = 3 , j = 0; i < 13 ; i++, j++) {
                cell = row.createCell(i);
                cell.setCellStyle(headerCellStyle);
                if(i == 3) {
                    cell.setCellValue("");
                }else {
                    cell.setCellValue(j);
                }
            }
            // 縦
            for(int i = 6 , j = 1 ; i < 15 ; i++, j++) {
                row = sheet.createRow(i);
                cell = row.createCell(3);
                cell.setCellStyle(headerCellStyle);
                cell.setCellValue(j);
            }

            // 「計算結果」のセルスタイル設定
            XSSFCellStyle resultCellStyle = workBook.createCellStyle();
            XSSFFont resultFont = workBook.createFont();
            resultFont.setFontName("ＭＳ ゴシック");
            resultFont.setFontHeightInPoints((short)25);
            resultCellStyle.setFont(resultFont);
            resultCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            resultCellStyle.setAlignment(HorizontalAlignment.CENTER);
            resultCellStyle.setFillForegroundColor(IndexedColors.WHITE.index);
            resultCellStyle.setBorderTop(BorderStyle.MEDIUM);
            resultCellStyle.setBorderBottom(BorderStyle.MEDIUM);
            resultCellStyle.setBorderRight(BorderStyle.MEDIUM);
            resultCellStyle.setBorderLeft(BorderStyle.MEDIUM);

            // セルに「表のヘッダ」を設定
            double num1 = 0;
            double num2 = 0;
            double result = 0;
            for(int i = 6 ; i < 15 ; i++) {
                for(int j = 4 ; j < 13 ; j++) {
                    // 九九の計算
                    num1 = sheet.getRow(5).getCell(j).getNumericCellValue();
                    num2 = sheet.getRow(i).getCell(3).getNumericCellValue();
                    result = num1 * num2;

                    row = sheet.getRow(i);
                    cell = row.createCell(j);
                    cell.setCellStyle(resultCellStyle);
                    cell.setCellValue(result);
                }
            }

            // エクセルファイルを出力
            try {

                // 現在の日付を取得
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

                //ファイルパス・ファイル名の指定
                outPutFilePath = "C:/Users/jinno/Desktop/Test/";
                outPutFileName = "kuku_" + dateFormat.format(date).toString() +  ".xlsx";

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
