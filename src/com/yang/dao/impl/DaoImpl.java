package com.yang.dao.impl;


import com.yang.dao.FDao;
import com.yang.domain.FileInfo;
import com.yang.domain.OperationLog;
import com.yang.domain.User;
import com.yang.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.sql.SQLException;
import java.util.List;


public class DaoImpl extends HibernateDaoSupport implements FDao {


    /**
     * 登录，返回User对象
     *
     * @param account
     * @param password
     * @return
     */
    @Override
    public User login(String account, String password) throws SQLException {
        User user = null;

        List <User> users = (List <User>) this.getHibernateTemplate().find("from User u " +
                "where u.account = ?0 and u.password = ?1 ", account, password);


        if (users.size()>=1){
            user =users.get(0);
        }


        //System.out.println(user.toString());
        return user;
    }

    @Override
    public User getUserbyId(int id) throws SQLException {
        User user = null;

        Session session = HibernateUtils.getSession();

        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("from User u where u.id = :id ");
        query.setParameter("id", id);

        user = (User) query.uniqueResult();

        tx.commit();


        //System.out.println(user.toString());
        return user;
    }

    @Override
    public boolean insertFileInfo(FileInfo fileInfo) throws SQLException {

        Session session = HibernateUtils.getSession();

        Transaction tx = session.beginTransaction();


        session.save(fileInfo);

        tx.commit();

        return fileInfo.getId() != 0;


    }

    @Override
    public List <FileInfo> getFileList() throws SQLException {

        List <FileInfo> list = (List <FileInfo>) getHibernateTemplate().find("from FileInfo ");
        return list;

    }

    @Override
    public boolean insertLoginLog(int userId) throws SQLException {
        return false;
    }

    @Override
    public int getFileIDByName(String fileName) throws SQLException {
        return 0;
    }

    @Override
    public boolean insertOperationLog(int userID, int fileID, String type) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteFile(int fileID) throws SQLException {
        return false;
    }

    @Override
    public List <User> getUserList() throws SQLException {
        return null;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        return false;
    }

    @Override
    public List <OperationLog> getAllOperateLogs() throws SQLException {
        return null;
    }

    @Override
    public boolean exportDatabaseTool(String hostIP, String userName, String password, String savePath, String fileName, String databaseName) {
        return false;
    }

    @Override
    public boolean insertUser(User user) throws SQLException {
        return false;
    }


}