package pl.edu.agh.services.rest;

import pl.edu.agh.managers.JournalsManager;
import pl.edu.agh.managers.PapersManager;
import pl.edu.agh.model.Journal;
import pl.edu.agh.model.Paper;
import pl.edu.agh.utils.PaperPDFPrinter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import java.io.IOException;
import java.nio.file.*;

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
    public byte[] getPaperPDF(@PathParam("id") long paperID) throws IOException {
        Paper paper = papersManager.getPaper(paperID);
        Journal journal = paper.getJournal();
        java.nio.file.Path pdfFilePath = new PaperPDFPrinter().getDocument(journal, paper);
        try {
            return Files.readAllBytes(pdfFilePath);
        } finally {
           Files.delete(pdfFilePath);
        }
    }

}
