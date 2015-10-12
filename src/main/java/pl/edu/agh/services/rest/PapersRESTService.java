package pl.edu.agh.services.rest;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import pl.edu.agh.managers.JournalsManager;
import pl.edu.agh.managers.PapersManager;
import pl.edu.agh.model.Journal;
import pl.edu.agh.model.Paper;
import pl.edu.agh.utils.PaperPDFPrinter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Path("rest/papers")
@Stateless
public class PapersRESTService {

    @EJB
    private JournalsManager journalsManager;

    @EJB
    private PapersManager papersManager;

    @POST
    @Path("/")
    @Produces("application/json")
    @Consumes("application/json")
    public long createNewPaper(@QueryParam("journalID") long journalID, Paper paper) {
        return journalsManager.addPaper(journalID, paper);
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public String deletePaper(@PathParam("id") long paperID) {
        papersManager.removePaper(paperID);
        return "OK";
    }

    @GET
    @Path("/pdf/{id}")
    @Produces("application/pdf")
    public File getPaperPDF(@PathParam("id") long paperID) {
        Paper paper = papersManager.getPaper(paperID);
        Journal journal = paper.getJournal();
        return new PaperPDFPrinter().getDocument(journal, paper);
    }

    /*
    @GET
    @Path("/{id}/authors")
    @Produces("application/json")
    public Collection<Author> getAllAuthors(@PathParam("id") long paperID) {
        return papersManager.getAllAuthors(paperID);
    }
    */

}
