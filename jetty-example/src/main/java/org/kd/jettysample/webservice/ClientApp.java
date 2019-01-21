package org.kd.jettysample.webservice;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class ClientApp {

    public static void main(String[] args) throws MalformedURLException {
        //http://localhost:9000/hello

        URL wsdlURL = new URL("http://localhost:9000/hello?wsdl");
        QName SERVICE_NAME = new QName("http://webservice.jettysample.kd.org/", "Hello");
        Service service = Service.create(wsdlURL, SERVICE_NAME);
        IHelloService client = service.getPort(IHelloService.class);
        String result = client.hello("k");
        System.out.println(result);


//        JaxWsProxyFactoryBean svr = new JaxWsProxyFactoryBean();
//
//        svr.setServiceClass(IHelloService.class);
//        svr.setAddress("http://localhost:9000/hello");
//        IHelloService hw = (IHelloService) svr.create();
//
//        BookDTO bookDTO = hw.getBook(1L);
//        System.out.println(bookDTO);
    }
}
