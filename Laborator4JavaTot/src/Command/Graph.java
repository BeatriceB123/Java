package Command;
import Tools.*;

//The serialization interface has no methods or fields
// and serves only to identify the semantics of being serializable.
public class Graph implements java.io.Serializable {
    String name;
    String description;
    String pathToImage; //absolute path?
    String pathToTgf;   //absolute path?

    public Graph ( String name, String pathToTgf, String pathToImage ) {
        this.name = name;
        this.description = "No description available";
        try {
            setPathToImage (pathToImage);
        } catch (Exception e) {
            System.out.println (e.getMessage ());
            this.pathToImage = "";
        }

        try {
            setPathToTgf (pathToTgf);
        } catch (Exception e) {
            System.out.println (e.getMessage ());
            this.pathToTgf = "";
        }
    }

    public Graph ( String name, String description, String pathToTgf, String pathToImage ) {
        this.name = name;
        this.description = description;
        try {
            setPathToImage (pathToImage);
        } catch (Exception e) {
            System.out.println ("Nu am putut initializa complet graful: " + e.getMessage ());
            this.pathToImage = "";
        }

        try {
            setPathToTgf (pathToTgf);
        } catch (Exception e) {
            System.out.println ("Nu am putut initializa complet graful: " + e.getMessage ());
            this.pathToTgf = "";
        }
    }

    private void setPathToImage ( String pathToImage ) throws Exception {
        if ( Tools.validatePath (pathToImage, ".png") || Tools.validatePath (pathToImage, ".jpg") )
        {
            this.pathToImage = pathToImage;
            //this.pathToImage = pathToImage.replaceAll ("/", "\\"); //oare ar trebui facut asa?
        }
        else
            throw new Exception ("Invalid path to image for this graph");
    }

    private void setPathToTgf ( String pathToTgf ) throws Exception {
        if ( Tools.validatePath (pathToTgf, ".tgf") )
            this.pathToTgf = pathToTgf;
        else
            throw new Exception ("Invalid path to tgf file for this graph");
    }

    public String getName () {
        if(name.equals (""))
            return "No argument";
        else
            return name;
    }

    public String getDescription () {
        if(description.equals (""))
            return "No argument";
        else
            return description;
    }

    public String getPathToImage () {
        if(pathToImage.equals (""))
            return "No argument.png";
        else
            return pathToImage;
    }

    public String getPathToTgf () {
        if(pathToTgf.equals (""))
            return "No argument.tgf";
        else
            return pathToTgf;
    }

    @Override
    public String toString () {

        String rasp = "";
        rasp += "Graful: " + this.getName () + '\n';
        rasp += "Descriere: " + this.getDescription ()+'\n';
        rasp += "Detalii: " + this.getPathToImage() + " sau " + this.getPathToTgf() + '\n' +'\n';
        return rasp;
    }
}
