import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.Transaction.TransactionModel;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PDFUtil {

    public static void generateTransactionPDF(List<TransactionModel> transactions, String filePath) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            document.add(new Paragraph("Your Transaction Log\n\n"));
            document.add(new Paragraph("PDF Statement\n\n"));

            // Table with 6 columns (adjusted for your TransactionModel)
            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);

            // Add table header
            table.addCell("Transaction ID");
            table.addCell("Type");
            table.addCell("Account ID");
            table.addCell("From Account");
            table.addCell("To Account");
            table.addCell("Amount");
            table.addCell("Timestamp");

            // Add rows
            for (TransactionModel t : transactions) {
                table.addCell(t.getTransactionId());
                table.addCell(t.getType().name());
                table.addCell(t.getAccountId());
                table.addCell(t.getFromAccountId());
                table.addCell(t.getToAccountId());
                table.addCell(String.valueOf(t.getAmount()));
                table.addCell(t.getTimestamp());
            }

            document.add(table);
            document.close();

            System.out.println("\u001B[32mPDF generated successfully at: " + filePath);

            // Open the PDF automatically
            File pdfFile = new File(filePath);
            if (pdfFile.exists() && Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(pdfFile);
            }

        } catch (DocumentException | IOException e) {
            System.out.println("\u001B[31mError generating PDF: " + e.getMessage());
        }
    }
}
