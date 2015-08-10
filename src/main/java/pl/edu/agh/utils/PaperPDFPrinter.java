package pl.edu.agh.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;
import pl.edu.agh.model.Journal;
import pl.edu.agh.model.Paper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PaperPDFPrinter {
    private static final Font TITLE_FONT = FontFactory.getFont(FontFactory.TIMES, 30);
    private static final Font SUBTITLE_FONT = FontFactory.getFont(FontFactory.TIMES_BOLD, 15);
    private static final Font TEXT_FONT = FontFactory.getFont(FontFactory.TIMES);

    public File getDocument(Journal journal, Paper paper){
        Document document = new Document();
        File f = new File("test.pdf");
        try {
            PdfWriter.getInstance(document, new FileOutputStream(f));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        document.open();

        Paragraph journalName = new Paragraph(journal.getName(), TITLE_FONT);
        journalName.setAlignment(Chunk.ALIGN_CENTER);

        Paragraph consentTitle = new Paragraph("Consent to publish", SUBTITLE_FONT);
        consentTitle.setAlignment(Chunk.ALIGN_CENTER);

        Paragraph consent= new Paragraph(journal.getConsentToPublish(), TEXT_FONT);
        Paragraph paperTitle= new Paragraph("Paper title: " + paper.getName(), TEXT_FONT);
        Paragraph authors= new Paragraph("Authors: AuthorA, AuthorB, AuthrC", TEXT_FONT);
        Paragraph sign= new Paragraph("Date, signature of corresponding Author: DREW, 2.05.2015", TEXT_FONT);
        Paragraph corresponing = new Paragraph(paper.getAuthors().iterator().next().getCorrespondencyData().getCity());
        try {

            if(journal.getBase64Logo()!=null) {
                try {
                    document.add(getTitleTable(journal, journalName));
                } catch (IOException e) {
                    document.add(journalName);
                }
            } else {
                document.add(journalName);
            }


            document.add(Chunk.NEWLINE);
            document.add(consentTitle);
            document.add(Chunk.NEWLINE);

            document.add(consent);
            document.add(Chunk.NEWLINE);
            document.add(paperTitle);
            document.add(authors);
            document.add(sign);
            document.add(corresponing);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close();
        return f;
    }

    private PdfPTable getTitleTable(Journal journal, Paragraph journalName) throws IOException, DocumentException {
        Image logo = Image.getInstance(Base64.decode(journal.getBase64Logo()));
        PdfPTable table = new PdfPTable(2);
        PdfPCell imgCell = new PdfPCell(logo, true);
        imgCell.setBorder(Rectangle.NO_BORDER);
        PdfPCell textCell = new PdfPCell();
        textCell.addElement(journalName);
        textCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        textCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        textCell.setBorder(Rectangle.NO_BORDER);
        table.setWidthPercentage(100);
        table.addCell(textCell);
        table.addCell(imgCell);
        table.setWidths(new int[]{4, 1});
        return table;
    }
}
