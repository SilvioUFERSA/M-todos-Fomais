package com.eletronicpoint.services;

import com.eletronicpoint.entities.Register;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PDFgenerator {

    private static Font smallFont = new Font(Font.TIMES_ROMAN, 10);
    public void generatePDF(List<Register> registers, String filePath) throws FileNotFoundException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));

        document.open();

        document.add(new Paragraph("Register Report"));
        document.add(new Paragraph("Document generated on: " + formatLocalDateTime(LocalDateTime.now()), smallFont));
        document.add(new Paragraph("\n"));

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);

        float[] columnWidths = new float[]{0.6f, 1.8f, 1.2f, 1.2f, 0.8f, 1.4f};
        try {
            table.setWidths(columnWidths);
        } catch (DocumentException e) {
            e.printStackTrace();
        }


        addTableHeader(table, "Register ID");
        addTableHeader(table, "Employee");
        addTableHeader(table, "Role");
        addTableHeader(table, "Date/Time");
        addTableHeader(table, "Type");
        addTableHeader(table, "Justification");


        for (Register register : registers) {
            addTableCell(table, String.valueOf(register.getId()));
            addTableCell(table, register.getEmployee().getName());
            addTableCell(table, register.getEmployee().getRole());
            addTableCell(table, formatLocalDateTime(register.getDateEndTime()));
            addTableCell(table, register.getType().toString());
            addTableCell(table, register.getJustification());
        }

        document.add(table);

        document.close();
    }

    private void addTableHeader(PdfPTable table, String header) {
        PdfPCell cell = new PdfPCell(new Phrase(header, smallFont));
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        table.addCell(cell);
    }

    private void addTableCell(PdfPTable table, String content) {
        table.addCell(new Phrase(content, smallFont));
    }

    private String formatLocalDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dateTime.format(formatter);
    }
}
