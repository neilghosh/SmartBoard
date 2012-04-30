import com.thoughtworks.xstream.*;
import org.xmlpull.v1.*;
class Sedeserial
{
 public static void serialize(java.util.ArrayList pages)
 {
    XStream xstream = new XStream();
    xstream.alias("shape1", Shape.class);
    xstream.alias("point", java.awt.Point.class);
    xstream.aliasField("Vertices",Shape.class,"vertex");

    
    String xml="";
   /* for(int i=0;i<objects.size();i++)
    {
     xml = xml + xstream.toXML(objects.get(i));
     
    }*/
    xml = xstream.toXML(pages);
    
    //System.out.println(xml);
    SaveFile.writeToFile(xml, "./shapes.xml");
 }
    public static java.util.ArrayList deserialize(String filename)
  {
    String xml=SaveFile.getFileContent(filename);
    System.out.print(xml);
    XStream xstream = new XStream();
    xstream.alias("shape1", Shape.class);
    xstream.alias("point", java.awt.Point.class);
    xstream.aliasField("Vertices",Shape.class,"vertex");
    
    java.util.ArrayList pages = (java.util.ArrayList)(xstream.fromXML(xml));
    
    return(pages); 
  }

}
