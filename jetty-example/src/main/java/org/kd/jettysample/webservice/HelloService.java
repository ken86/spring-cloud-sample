package org.kd.jettysample.webservice;

import javax.jws.WebService;

@WebService(endpointInterface = "org.kd.jettysample.webservice.IHelloService", serviceName = "Hello")
public class HelloService implements IHelloService {

    @Override
    public String hello(String str) {
        return "hello " + str;
    }

    @Override
    public BookDTO getBook(Long bookId) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookName("Java");
        bookDTO.setPrice(10.0d);
        return bookDTO;
    }

}
