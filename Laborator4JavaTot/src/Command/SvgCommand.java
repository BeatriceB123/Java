package Command;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static Tools.Tools.pathToFile;
import static Tools.Tools.readFile;

public class SvgCommand extends Command {
    private final int n =4;
    private int[][] adjacencyMatrix = new int [n][n]; //luam doar prima matrice din catalog aici

    public SvgCommand ( Catalog catalog ) {
        super (catalog);
        init();
    }

    private void init()
    {
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                adjacencyMatrix[i][j] = adjacencyMatrix[j][i] = 1;
            //un K4;

        /*for (int j = 0; j < n; j++)
            for (int i=0; i < n; i++)
                adjacencyMatrix[i][j]=0;

            if ( catalog.getGraphList ().size () > 0 ) {

            if ( catalog.getGraphList ().get (0).getName ().equals ("K4") ) {
                for (int i = 0; i < n; i++)
                    for (int j = i + 1; j < n; j++)
                        adjacencyMatrix[i][j] = adjacencyMatrix[j][i] = 1;
            }
        } else {
            for (int i = 0; i < n; i++)
                for (int j = i + 1; j < n; j++)
                    adjacencyMatrix[i][j] = 1; // (orientat)
        }*/
    }

    @Override
    public void runCommand ( List <String> arguments )
            throws WrongCommandArguments, IOException {
        if ( arguments.size () != 1 )
            throw new WrongCommandArguments ("One argument for svg command");
        init ();
        String pathToTemplate = pathToFile + "templateSvg.txt";
        String content = readFile (pathToTemplate, StandardCharsets.UTF_8);

        double coordX=250;
        double coordY=250;
        double raza = 200;
        content = content.replaceAll("coordX", String.valueOf (coordX));
        content = content.replaceAll("coordY", String.valueOf (coordY));
        content = content.replaceAll("raza", String.valueOf (raza));

        String nameOfFile = "paginaSvg.html"; //arguments.get(0);
        BufferedWriter writer = new BufferedWriter (new FileWriter (pathToFile + nameOfFile));

        //StringBuilder toWrite = new StringBuilder();

        double[] coordXPunct = new double[n];
        double[] coordYPunct = new double[n];

        for(int i=0; i<n; i++)
        {
            coordXPunct[i] = coordX + raza * Math.cos(i*360/(double)n);
            coordYPunct[i] = coordY - raza * Math.sin(i*360/(double)n);
            content = content.replace("TOBEREPLACED",
                   "<circle cx=\"" + Double.toString(coordXPunct[i])
                                   + "\" cy=\"" + Double.toString(coordYPunct[i])
                                   + "\" r=\"10\"  fill=\"red\" />\n"
                                   + "TOBEREPLACED");
        }
       // content = content.replaceAll ("TOBEREPLACED", "");


        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
            {
                System.out.println (adjacencyMatrix[i][j]);
                if(adjacencyMatrix[i][j] == 1)
                {
                    //System.out.println ("DF");
                    content = content.replace("TOBEREPLACED",
                   "<line x1=\"" + Double.toString(coordXPunct[i]) +
                   " y1=\"" + Double.toString(coordYPunct[i]) + "\"" +
                   " x2=\"" + Double.toString(coordXPunct[j]) + "\"" +
                   " y2=\"" + Double.toString(coordYPunct[j]) + "\"" + "style=\"stroke:rgb(255,0,0);stroke-width:5\" />"
                    + "TOBEREPLACED" );
                }
            }

        content = content.replaceAll ("TOBEREPLACED", "");
        System.out.println (content + "*");



        /*
        for(int i=0; i<matrix.length-1; i++)
        {


            for(int j=i+1; j<matrix.length; j++)
            {
                if(matrix[i][j]==1)
                {
                    double x1 = intialX - 400*Math.sin(i*360/(matrix.length));
                    double x2 = intialX - 400*Math.sin(j*360/(matrix.length));
                    double y1= initialY+400*(1-Math.cos(i*360/(matrix.length)));
                    double y2= initialY+400*(1-Math.cos(j*360/(matrix.length)));

                    toWrite.append("\n <line x1=\"");
                    toWrite.append(Double.toString(x1));
                    toWrite.append("\" y1=\"");
                    toWrite.append(Double.toString(y1));
                    toWrite.append("\" x2=\"");
                    toWrite.append(Double.toString(x2));
                    toWrite.append("\" y2=\"");
                    toWrite.append(Double.toString(y2));
                    toWrite.append("\" style=\"stroke:rgb(0,0,255);stroke-width:2\" />\n");
                }}}
        toWrite.append("\n<\\svg>");
        */
        writer.write (content);
        writer.close ();
    }
}
