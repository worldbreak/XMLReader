import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;


public class XMLReader {



    public static Document parse() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read("11b_3.xml");
        return document;
    }

    public static void bar(Document document) throws DocumentException {
        int carsCount=0;
            try {
                FileWriter outFilePositions = new FileWriter("histogram.txt");
                PrintWriter outPositions = new PrintWriter(outFilePositions);
                FileWriter outFileGNU = new FileWriter("gnuplot.txt");
                PrintWriter outGNU = new PrintWriter(outFileGNU);

            Element root = document.getRootElement();

            // iterate through child elements of root
            for ( Iterator i = root.elementIterator(); i.hasNext(); ) {
                Element element = (Element) i.next();
                // do something
            }

            // iterate through child elements of root with element name "foo"
            for ( Iterator i = root.elementIterator( "interval" ); i.hasNext(); ) {
                Element foo = (Element) i.next();
                for ( Iterator j = foo.attributeIterator(); j.hasNext(); ) {
                    Attribute attribute = (Attribute) j.next();
                    String name = attribute.getName();
                    if (name.equals("nVehContrib")){
                        double pomoc = Double.valueOf( attribute.getValue());
                        carsCount += (int) pomoc;
                        outPositions.println(attribute.getValue());
                    }
                }


            }
            outGNU.println("set terminal pdf");
            outGNU.println("set output 'out.pdf'");
            outGNU.println("set xrange [0:1440]");
            outGNU.println("set yrange [0:50]");
            outGNU.println("set style data histogram");
            outGNU.println("set style histogram cluster gap 1");
            outGNU.println("set style fill solid border -1");
            outGNU.println("set boxwidth 0.9");
            outGNU.println("set xtic rotate by -45 scale 0");
            outGNU.println("plot 'histogram.txt' using 1");
            outGNU.close();
            outPositions.close();


            System.out.println(carsCount);
        } catch (Exception e) {
                System.out.println("Error creating file.");
        }

    }

    public static void initialize(String gnuFileName) {


    }

    public static void go() throws DocumentException {
           Document document = parse();
           bar(document);
    }


    public static void main(String[] args) throws DocumentException {
        go();
	// write your code here
    }
}
