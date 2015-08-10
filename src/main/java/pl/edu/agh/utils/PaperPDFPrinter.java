package pl.edu.agh.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;
import pl.edu.agh.model.Author;
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
    private static final Font TABLE_HEADER_FONT = FontFactory.getFont(FontFactory.TIMES,10);


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
        Paragraph authorsTitle= new Paragraph("Authors:", TEXT_FONT);

        StringBuilder builder = new StringBuilder();
        for(Author a: paper.getAuthors()){
            builder.append(String.format("%s %s,", a.getName(), a.getSurname()));
        }
        Paragraph authors= new Paragraph(builder.substring(0,builder.length()-1), TEXT_FONT);


        Paragraph sign= new Paragraph("Date, signature of corresponding Author: DREW, 2.05.2015", TEXT_FONT);
        Paragraph contributionTitle= new Paragraph("Contribution of the authors", TEXT_FONT);
        Paragraph appendixTitle = new Paragraph("Consent to Publish Appendix", SUBTITLE_FONT);
        appendixTitle.setAlignment(Chunk.ALIGN_CENTER);
        Paragraph financialTitle = new Paragraph("Financial disclosure", TEXT_FONT);
        Paragraph financialDislosure = new Paragraph(paper.getFinancialDisclosure(), TEXT_FONT);
        Paragraph correspondingTitle = new Paragraph("Corresponding author:", TEXT_FONT);
        Author cAuthor = paper.getAuthors().iterator().next();
        Paragraph correspondingData = new Paragraph(String.format("Name: %s %s\nAffiliaion: %s\n Address: %s %s,%s %s\nemail: %s",
                cAuthor.getName(), cAuthor.getSurname(), cAuthor.getAffiliation(), cAuthor.getCorrespondencyData().getStreetName(), cAuthor.getCorrespondencyData().getStreetNumber(),
                cAuthor.getCorrespondencyData().getCity(), cAuthor.getCorrespondencyData().getPostalCode(), cAuthor.getCorrespondencyData().getEmailAddress()));
        try {

            createPageTitle(journal, document, journalName);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(consentTitle);
            document.add(Chunk.NEWLINE);
            document.add(consent);
            document.add(Chunk.NEWLINE);
            document.add(paperTitle);
            document.add(Chunk.NEWLINE);
            document.add(authorsTitle);
            document.add(authors);
            document.add(Chunk.NEWLINE);
            document.add(correspondingTitle);
            document.add(correspondingData);
            document.add(Chunk.NEWLINE);
            document.add(sign);

            document.newPage();

            createPageTitle(journal, document, journalName);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(appendixTitle);
            document.add(Chunk.NEWLINE);
            document.add(paperTitle);
            document.add(contributionTitle);
            document.add(Chunk.NEWLINE);
            document.add(getAuthorsTable(paper));
            document.add(financialTitle);
            document.add(financialDislosure);


        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close();
        return f;
    }

    private void createPageTitle(Journal journal, Document document, Paragraph journalName) throws DocumentException {
        if(journal.getBase64Logo()!=null) {
            try {
                document.add(getTitleTable(journal, journalName));
            } catch (IOException e) {
                document.add(journalName);
            }
        } else {
            document.add(journalName);
        }
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

    private PdfPTable getAuthorsTable(Paper paper) throws DocumentException {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{4, 4, 8, 2});
        Paragraph nameParT = new Paragraph("Name, surname", TABLE_HEADER_FONT);
        Paragraph affParT = new Paragraph("Affiliation", TABLE_HEADER_FONT);
        Paragraph contribParT = new Paragraph("Contribution to the paper", TABLE_HEADER_FONT);
        Paragraph totParT = new Paragraph("Estimated % of the total contribution", TABLE_HEADER_FONT);
        table.addCell(nameParT);
        table.addCell(affParT);
        table.addCell(contribParT);
        table.addCell(totParT);
        for(Author author: paper.getAuthors()){
            Paragraph namePar = new Paragraph(author.getName() + " " + author.getSurname(), TEXT_FONT);
            Paragraph affPar = new Paragraph(author.getAffiliation(), TEXT_FONT);
            Paragraph contribPar = new Paragraph(author.getContribution(), TEXT_FONT);
            Paragraph totPar = new Paragraph(String.format("%d", author.getContributionValue()), TEXT_FONT);
            PdfPCell nameCell = new PdfPCell(namePar);
            PdfPCell affCell = new PdfPCell(affPar);
            PdfPCell contribCell = new PdfPCell(contribPar);
            PdfPCell totCell = new PdfPCell(totPar);
            table.addCell(nameCell);
            table.addCell(affCell);
            table.addCell(contribCell);
            table.addCell(totCell);
        }
        return table;
    }
}
