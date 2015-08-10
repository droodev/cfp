package pl.edu.agh.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import pl.edu.agh.model.Journal;
import pl.edu.agh.model.Paper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PaperPDFPrinter {
    private static final Font TITLE_FONT = FontFactory.getFont(FontFactory.TIMES, 30);
    private static final Font SUBTITLE_FONT = FontFactory.getFont(FontFactory.TIMES_BOLD, 15);
    private static final Font TEXT_FONT = FontFactory.getFont(FontFactory.TIMES);

    public File getDocument(Journal journal, Paper paper){
        Document document = new Document();
        File f = new File("test.pdf");
        // step 2
        try {
            PdfWriter.getInstance(document, new FileOutputStream(f));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // step 3
        document.open();
        // step 4

        Paragraph journalName = new Paragraph(journal.getName(), TITLE_FONT);
        journalName.setAlignment(Chunk.ALIGN_CENTER);
        Paragraph consentTitle = new Paragraph("ConsentToPublish", SUBTITLE_FONT);
        consentTitle.setAlignment(Chunk.ALIGN_CENTER);
        Paragraph consent= new Paragraph(journal.getConsentToPublish(), TEXT_FONT);
        Paragraph paperTitle= new Paragraph("Paper title: " + paper.getName(), TEXT_FONT);
        Paragraph authors= new Paragraph("Authors: AuthorA, AuthorB, AuthrC", TEXT_FONT);
        Paragraph sign= new Paragraph("Date, signature of corresponding Author: DREW, 2.05.2015", TEXT_FONT);
        Paragraph corresponing = new Paragraph(paper.getAuthors().iterator().next().getCorrespondencyData().getCity());
        try {
            document.add(journalName);
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
        // step 5
        document.close();

        return f;
    }
}
