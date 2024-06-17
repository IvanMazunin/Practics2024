package crud.exporters;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import crud.service.dto.UserDto;
import lombok.Data;

@Data
public class FilesExporter {

	File currDir = new File("..\\..\\..\\..\\..\\..\\export");
	String path;

	public FilesExporter(){
		if(!currDir.exists())
			currDir.mkdir();
		path = currDir.getAbsolutePath();
	}

	public String setFileName(String extension, String prefix){
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
		String timeStamp = dateFormat.format(new Date());
		String fileName = prefix + timeStamp + extension;
		return fileName;
	}

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	public void exportToExcel(List<UserDto> list) throws IOException{
		workbook = new XSSFWorkbook();
		String fileName = setFileName(".xlsx", "Users_");

		writeHeaderLine();
		writeDataLine(list);
		
		System.out.println(path);
		String fileLocation = path.substring(0, path.length()) + "\\" + fileName;

		FileOutputStream outputStream = new FileOutputStream(fileLocation);
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}

	private void writeHeaderLine(){
		sheet = workbook.createSheet("Users");
		XSSFRow row = sheet.createRow(0);
		XSSFCellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		int columnIndex = 0;
		createCell(row, columnIndex++, "ID", style);
		createCell(row, columnIndex++, "firstName", style);
		createCell(row, columnIndex++, "lastName", style);
		createCell(row, columnIndex++, "email", style);
		createCell(row, columnIndex++, "position", style);
	}

	private void createCell(XSSFRow row, int columnIndex, Object value, CellStyle style){
		XSSFCell cell = row.createCell(columnIndex);
		sheet.autoSizeColumn(columnIndex);
		if(value instanceof Long)
			cell.setCellValue((Long)value);
		else
			cell.setCellValue((String)value);
		cell.setCellStyle(style);
	}

	private void writeDataLine(List<UserDto> users){
		int rowIndex = 1;

		XSSFCellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);
		for(UserDto user : users){
			XSSFRow row = sheet.createRow(rowIndex++);
			int columnIndex = 0;
			createCell(row, columnIndex++, user.getId(), style);
			createCell(row, columnIndex++, user.getFirstName(), style);
			createCell(row, columnIndex++, user.getLastName(), style);
			createCell(row, columnIndex++, user.getEmail(), style);
			createCell(row, columnIndex++, user.getPosition(), style);
		}
	}
}
