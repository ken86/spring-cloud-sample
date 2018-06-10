package org.kd.ms.aop.annotation;


public class ProceedService {

    @PrintStartEnd
    public void service() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
