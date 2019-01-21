package org.kd.jettysample.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IHelloService {

    @WebMethod
    String hello(@WebParam(name="text")String text);

    @WebMethod
    BookDTO getBook(@WebParam(name="bookId")Long bookId);
}

