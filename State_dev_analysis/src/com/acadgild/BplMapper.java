package com.acadgild;
 
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream; 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
 
public class BplMapper extends Mapper<LongWritable, Text, Text, TextPair> {
 
   private static TextPair BPL_data = new TextPair();
   
 
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
 
        try {
 
            InputStream is = new ByteArrayInputStream(value.toString().getBytes());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);
 
            doc.getDocumentElement().normalize();
            //to get aal the nodes under the tag name
            NodeList nList = doc.getElementsByTagName("row");
 
            for (int temp = 0; temp < nList.getLength(); temp++) {
 
                Node nNode = nList.item(temp);
 
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
                    Element eElement = (Element) nNode;
 
                    String State= eElement.getElementsByTagName("State_Name").item(0).getTextContent();
                    String Project_objective_BPL = eElement.getElementsByTagName("Project_Objectives_IHHL_BPL").item(0).getTextContent();
                    String Project_Performance_BPL = eElement.getElementsByTagName("Project_Performance-IHHL_BPL").item(0).getTextContent();
                    Text objective_BPL=new Text(Project_objective_BPL);
                    Text performance_BPL=new Text(Project_Performance_BPL);
                    BPL_data.set(objective_BPL, performance_BPL);
                    context.write(new Text(State),BPL_data );
 
                }
            }
        } catch (Exception e) {
        	System.out.println(e);
            //LogWriter.getInstance().WriteLog(e.getMessage());
        }
 
    }
 
}
 
