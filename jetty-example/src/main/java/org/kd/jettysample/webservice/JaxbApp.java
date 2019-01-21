package org.kd.jettysample.webservice;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class JaxbApp {

//    public static void main(String[] args) throws JAXBException, IOException {
//        JAXBContext jaxbContext = JAXBContext.newInstance(BookDTO.class);
//        SchemaOutputResolver sor = new SchemaOutputResolver() {
//            @Override
//            public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
//                File file = new File(suggestedFileName);
//                StreamResult result = new StreamResult(file);
//                result.setSystemId(file.toURI().toURL().toString());
//                return result;
//
//            }
//        };
//        jaxbContext.generateSchema(sor);
//
//
//    }

    public static final String XML_CONTENT = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><book><bookId>1</bookId><bookName>Java</bookName><price>0.0</price><author>abc</author></book>";

    public static void main(String[] args) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(BookDTO.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        InputStream in = new ByteArrayInputStream(XML_CONTENT.getBytes());

        BookDTO b = (BookDTO) unmarshaller.unmarshal(in);
        System.out.println(b);
    }

//    public static void main(String[] args) throws JAXBException {
//        JAXBContext jaxbContext = JAXBContext.newInstance(BookDTO.class);
//        BookDTO bookDTO = new BookDTO();
//        bookDTO.setBookId(1L);
//        bookDTO.setBookName("Java");
//
//        Marshaller marshaller = jaxbContext.createMarshaller();
//        marshaller.marshal(bookDTO, System.out);
//    }
}
