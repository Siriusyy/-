package com.yang.test;

import com.yang.domain.FileInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Test {

    public static void main(String[] args){
        Test test =new Test();
        test.demo2();
    }


    public void demo(){
    ClassPathXmlApplicationContext factory =new ClassPathXmlApplicationContext("ApplicationContext.xml");
        MyAspect myaspect = (MyAspect) factory.getBean("myaspect");
        myaspect.save();
    }

    public void demo2(){

        Configuration cfg=new Configuration().configure();
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        List<FileInfo> list = session.createQuery("from FileInfo ").list();

        tx.commit();





    }

    public void demo3(){

    }

}
