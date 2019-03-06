package com.yang.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    public static Session getSession(){
        Configuration cfg=new Configuration().configure();
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.getCurrentSession();
        return session;
    }
}
