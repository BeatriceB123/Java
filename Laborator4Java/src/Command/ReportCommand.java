package Command;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import jdk.nashorn.internal.runtime.Version;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import javax.security.auth.login.Configuration;
import javax.tools.Tool;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Tools.Tools.pathToFile;

public class ReportCommand extends Command {

    public ReportCommand(Catalog catalog) {
        super(catalog);
    }

    public void runCommand( List <String> arguments)
            throws WrongCommandArguments, IOException, DocumentException
    {
        if(arguments.size ()!=1)
            throw new WrongCommandArguments ("One argument for report");
        switch (arguments.get(0).toLowerCase ()){

            case "html":
            {
                transformInHtml ();
                break;
            }
            case "velocity":
            {
                transformInVelocity();
                break;
            }
            case "pdf":
            {
                transformInPdf();
                break;
            }
            case "xls":
            {
                transformInXls();
                break;
            }
            default:
            {
                throw new WrongCommandArguments (arguments.get(0));
            }

        }
    }

    private void transformInHtml() throws IOException
    {
        BufferedWriter writer;

        String nameOfFile = "pagina.html";
        writer = new BufferedWriter(new FileWriter(pathToFile + nameOfFile));

        String textToWrite = catalog.toString().replaceAll ("\n", "<br>");
        String htmlCode = "<html>\n<head>\n</head>\n<body>\n" + textToWrite  + "\n</body>\n</html>";
        writer.write(htmlCode);
        writer.close();
    }

    private void transformInVelocity()
            throws IOException
    {
        /*  first, get and initialize an engine  */
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init();
        /*  next, get the Template  */
        Template t = velocityEngine.getTemplate("/src/GeneratedFiles/templateVelocity.vm");
        /*  create a context and add data */
        VelocityContext context = new VelocityContext();
        context.put("catalog", catalog.toString().replaceAll ("\n", "<br>"));
        /* now render the template into a StringWriter */
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        /* show the message */
        String message = writer.toString();
        System.out.println(message);
        /* put the message into a file*/
        String nameOfFile = "paginaVelocity.html";
        BufferedWriter writerFile = new BufferedWriter(new FileWriter(pathToFile + nameOfFile));
        writerFile.write(message);
        writerFile.close();
    }

    private void transformInPdf()
            throws FileNotFoundException, DocumentException
    {
        Document document = new Document();

        String nameOfFile = "pagina.pdf";
        PdfWriter.getInstance(document, new FileOutputStream(pathToFile + nameOfFile));

        document.open();

        for (int i=0; i<catalog.graphList.size(); i++)
        {
            Paragraph begin = new Paragraph ("Graful numarul " + (i+1) + " : ");
            Graph currentGraph = catalog.getGraphList ().get (i);
            String toAdd = currentGraph.toString ();
            Paragraph textToAdd = new Paragraph (toAdd);
            document.add(begin);
            document.add(textToAdd);
        }

        document.close();
    }

    private void transformInXls() throws IOException
    {
        String nameOfFile = "pagina.xls";

        HSSFWorkbook workbook = new HSSFWorkbook ();
        HSSFSheet sheet = workbook.createSheet("Excel page");

        HSSFRow rowA = sheet.createRow(0);
        HSSFCell cellA = rowA.createCell(0);
        cellA.setCellValue("Name");
        HSSFCell cell2A = rowA.createCell(1);
        cell2A.setCellValue("Path to Tgf");
        HSSFCell cell3A = rowA.createCell(2);
        cell3A.setCellValue("Path to img");
        HSSFCell cell4A = rowA.createCell(3);
        cell4A.setCellValue("Description: ");


        for (int i = 0; i < catalog.graphList.size (); i++) {
            HSSFRow row = sheet.createRow(i+1);
            HSSFCell cell = row.createCell(0);
            cell.setCellValue(catalog.graphList.get(i).getName());
            HSSFCell cell2 = row.createCell(1);
            cell2.setCellValue(catalog.graphList.get(i).getPathToTgf ());
            HSSFCell cell3 = row.createCell(2);
            cell3.setCellValue(catalog.graphList.get(i).getPathToImage ());
            HSSFCell cell4 = row.createCell(3);
            cell4.setCellValue(catalog.graphList.get(i).getDescription ());
        }

        workbook.write(new FileOutputStream (pathToFile + nameOfFile));
    }

}