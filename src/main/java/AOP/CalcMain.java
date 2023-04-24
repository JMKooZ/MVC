package main.java.AOP;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CalcMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("exAop.xml");
        Calculator cal = (Calculator) context.getBean("proxyCal");
        Calculator ca = new Calculator();
        cal.add(100,20);
        System.out.println();
        cal.subtract(100,20);
        System.out.println();
        cal.multiply(100,20);
        System.out.println();
        cal.divide(100,20);
        System.out.println();
//        System.out.println("================================");
//        ca.add(100,20);
//        System.out.println();
//        ca.subtract(100,20);
//        System.out.println();
//        ca.multiply(100,20);
//        System.out.println();
//        ca.divide(100,20);
//        System.out.println();
    }
}
