package com.company;


import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.Iterator;


public class Main {

    public static Document parse() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read("11b_2.xml");
        return document;
    }

    public static void bar(Document document) throws DocumentException {
        int carsCount=0;
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
                    double pomoc =  Double.valueOf( attribute.getValue());
                    carsCount += (int) pomoc;
                }
            }


        }
        System.out.println(carsCount);


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
