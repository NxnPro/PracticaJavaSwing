package controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.dsig.Transform;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Esta clase permite definir un metodo capas de exportar un archivo de tipo XML
 * @author Nixon
 *
 */

public class ExportarXML {
	/**
	 * Metodo capaz de realizar un fichero que guarda automaticamente dentro del proyecto el cual funciona pasando por parametro
	 * el JTable llamado tablaPaises de la clase Consultar y tambien tenemos un  parametro para indicar el nombre que tendra dicho archivo
	 * @param tb -- valor que pasa como parametro tablaPaises
	 * @param FileName -- valor que indica el nombre que tendra el archivo
	 */
	
	public void CrearXml(JTable tb, String FileName){
        try{
            String file = FileName;
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Paises");
            doc.appendChild(rootElement);
            
            int i=0;
            
            while (i<tb.getRowCount()){
                int j=0;
                Element rows = doc.createElement("Pais");
                rootElement.appendChild(rows);
                
                Attr attr = doc.createAttribute("id");
                attr.setValue((i+1)+"");
                rows.setAttributeNode(attr);
                
                while (j<tb.getColumnCount()){
                    Element element = doc.createElement(tb.getTableHeader().getColumnModel().getColumn(j).getHeaderValue()+"");
                    element.appendChild(doc.createTextNode(tb.getModel().getValueAt(i,j)+""));
                    rows.appendChild(element);
                    j++;
                }
                i++;
            }
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            DOMSource source = new DOMSource(doc);
            StreamResult result;
            
            try{
                FileOutputStream fileOutputStream = null;
                
                fileOutputStream = new FileOutputStream(new File(file+".xml"));
                
                result = new StreamResult(fileOutputStream);
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.transform(source, result);
                try{
                    fileOutputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
            
        }catch (ParserConfigurationException pce){
            pce.printStackTrace();
        } catch (TransformerException te){
            te.printStackTrace();
        }
    }
	
}	
