package org.kd.jettysample.webservice;

import javax.xml.ws.Endpoint;

public class WSApp {

    public static void main(String[] args) {
        HelloService implementor= new HelloService();
        String address="http://localhost:9000/hello";
        Endpoint.publish(address, implementor);

        System.out.println("web service started...");


    }
}
