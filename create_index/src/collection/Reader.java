/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection;

//import org.apache.tika.*;
import java.io.*;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 *
 * @author javi
 */
public class Reader {
    
    public Document readDoc(int id, File file) throws IOException, TikaException, SAXException{
        return new Document(id, parserDoc(file));
    }
    
    public String parserDoc(File file) throws IOException, TikaException, SAXException{
        
        //Leemos el documento
        InputStream input = null;
        
        try{
            input = new FileInputStream(file);
        }catch(FileNotFoundException e){}        
        
        //Extraemos información y lo parseamos
        Metadata metadata = new Metadata(); //Metadatos
        ContentHandler ch = new BodyContentHandler(-1); //ContentHandler
        ParseContext parseContext = new ParseContext(); //Para modificar el comportamiento  de ContentHandler

        AutoDetectParser parser = new AutoDetectParser(); //Detectamos el tipo de archivo          

        //Parseamos el stream
         try{ 
             parser.parse(input, ch, metadata, parseContext);
         }
         catch (IOException e){}
         
         return ch.toString(); //Devolvemos un string con los tokens;
    }
}
