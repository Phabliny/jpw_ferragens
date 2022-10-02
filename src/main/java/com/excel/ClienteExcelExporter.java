package com.excel;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jpw_ferragens.domain.Cliente;


@SuppressWarnings("UnusedAssignment")
public class ClienteExcelExporter {
    private final XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private final List<Cliente> listaClientes;

    public ClienteExcelExporter(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Clientes");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Nome", style);       
        createCell(row, 1, "Telefone", style);    
        createCell(row, 2, "Data_hora", style); 

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Cliente cliente : listaClientes) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            
            LocalDateTime data = cliente.getLocalDateTime();
            DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String dataFormatada = data.format(formatter);

            createCell(row, columnCount++, cliente.getNome(), style);
            createCell(row, columnCount++, cliente.getTelefone(), style);
            createCell(row, columnCount++, dataFormatada, style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
