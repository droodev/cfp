package pl.edu.agh.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import pl.edu.agh.model.Author;
import pl.edu.agh.model.Journal;
import pl.edu.agh.model.Paper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

public class PaperPDFPrinter {
    private static final Font TITLE_FONT = FontFactory.getFont(FontFactory.TIMES, 30);
    private static final Font SUBTITLE_FONT = FontFactory.getFont(FontFactory.TIMES_BOLD, 15);
    private static final Font TEXT_FONT = FontFactory.getFont(FontFactory.TIMES,11);
    private static final Font SMALL_SUBTITLE_FONT = FontFactory.getFont(FontFactory.TIMES_BOLD,11);
    private static final Font TABLE_HEADER_FONT = FontFactory.getFont(FontFactory.TIMES,10);
    private static final Chunk TAB = new Chunk(new VerticalPositionMark(), 50, true);


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

        try {

            constructPageTitle(journal, document);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(createConsentTitle());
            document.add(Chunk.NEWLINE);
            document.add(createConsentParagraph(journal.getConsentToPublish()));
            document.add(Chunk.NEWLINE);
            document.add(createPaperTitleParagraph(paper.getName()));
            document.add(createAuthorsParagraph(paper.getAuthors()));
            document.add(createCorrespondigAuthorParagraph(paper.getAuthors()));
            document.add(createSignParagraph());

            document.newPage();

            constructPageTitle(journal, document);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(createAppendixTitleParagraph());
            document.add(Chunk.NEWLINE);
            document.add(createPaperTitleParagraph(paper.getName()));
            document.add(Chunk.NEWLINE);
            document.add(createContributionParagraph(paper));
            document.add(Chunk.NEWLINE);
            document.add(createFinancialDisclosureParagraph(paper.getFinancialDisclosure()));


        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close();
        return f;
    }

    private Paragraph createAppendixTitleParagraph(){
        Paragraph appendixTitle = new Paragraph("Consent to Publish Appendix", SUBTITLE_FONT);
        appendixTitle.setAlignment(Chunk.ALIGN_CENTER);
        return appendixTitle;
    }

    private Paragraph createConsentTitle(){
        Paragraph consentTitle = new Paragraph("Consent to publish", SUBTITLE_FONT);
        consentTitle.setAlignment(Chunk.ALIGN_CENTER);
        return consentTitle;
    }

    private Paragraph createConsentParagraph(String consentToPublish){
        Paragraph consentParagraph= new Paragraph(consentToPublish, TEXT_FONT);
        return consentParagraph;
    }

    private Paragraph createFinancialDisclosureParagraph(String financialDisclosure){
        Paragraph financialParagraph = new Paragraph("Financial disclosure", SMALL_SUBTITLE_FONT);
        financialParagraph.add(Chunk.NEWLINE);
        Chunk financialDislosureChunk = new Chunk(financialDisclosure, TEXT_FONT);
        financialParagraph.add(financialDislosureChunk);
        return financialParagraph;
    }

    private Paragraph createCorrespondigAuthorParagraph(Collection<Author> authors){
        Paragraph correspondingParagraph = new Paragraph("Corresponding author", SMALL_SUBTITLE_FONT);
        correspondingParagraph.add(Chunk.NEWLINE);
        Author cAuthor = authors.iterator().next();
        Chunk namePart = new Chunk(String.format("Name: %s %s\n",
                cAuthor.getName(), cAuthor.getSurname()), TEXT_FONT);
        Chunk affiliationPart = new Chunk(String.format("Affiliation: %s\n",cAuthor.getAffiliation()), TEXT_FONT);
        Chunk addressPart = new Chunk(String.format("Address: %s %s, %s %s\n",cAuthor.getCorrespondencyData().getStreetName(),
                cAuthor.getCorrespondencyData().getStreetNumber(), cAuthor.getCorrespondencyData().getCity(),
                cAuthor.getCorrespondencyData().getPostalCode()), TEXT_FONT);
        Chunk emailPart = new Chunk(String.format("E-mail: %s", cAuthor.getCorrespondencyData().getEmailAddress()), TEXT_FONT);
        correspondingParagraph.add(TAB);
        correspondingParagraph.add(namePart);
        correspondingParagraph.add(TAB);
        correspondingParagraph.add(affiliationPart);
        correspondingParagraph.add(TAB);
        correspondingParagraph.add(addressPart);
        correspondingParagraph.add(TAB);
        correspondingParagraph.add(emailPart);
        return correspondingParagraph;
    }

    private Paragraph createPaperTitleParagraph(String paperName) {
        Paragraph paperTitleParagraph = new Paragraph("Paper title: ", SMALL_SUBTITLE_FONT);
        paperTitleParagraph.add(new Chunk(paperName, TEXT_FONT));
        return paperTitleParagraph;
    }

    private Paragraph createSignParagraph() {
        Paragraph signParagraph= new Paragraph("Date, signature of corresponding author: ", SMALL_SUBTITLE_FONT);
        signParagraph.add(new Chunk("DREW, 2.05.2015", TEXT_FONT));
        return signParagraph;
    }

    private Paragraph createAuthorsParagraph(Collection<Author> authors){
        Paragraph authorsParagraph= new Paragraph("Authors: ", SMALL_SUBTITLE_FONT);
        StringBuilder builder = new StringBuilder();
        for(Author a: authors){
            builder.append(String.format("%s %s, ", a.getName(), a.getSurname()));
        }
        authorsParagraph.add(new Chunk(builder.substring(0,builder.length()-2), TEXT_FONT));
        return authorsParagraph;
    }

    private void constructPageTitle(Journal journal, Document document) throws DocumentException {
        Paragraph journalName = new Paragraph(journal.getName(), TITLE_FONT);
        journalName.setAlignment(Chunk.ALIGN_CENTER);
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

    private Paragraph createContributionParagraph(Paper paper) throws DocumentException {
        Paragraph contributionParagraph= new Paragraph("Contribution of the authors", SMALL_SUBTITLE_FONT);
        contributionParagraph.add(Chunk.NEWLINE);
        contributionParagraph.add(createContributionTable(paper));
        return contributionParagraph;
    }

    private PdfPTable createContributionTable(Paper paper) throws DocumentException {
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
