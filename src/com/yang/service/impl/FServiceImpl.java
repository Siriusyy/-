package com.yang.service.impl;


import com.yang.dao.FDao;
import com.yang.dao.impl.DaoImpl;
import com.yang.domain.FileInfo;
import com.yang.domain.OperationLog;
import com.yang.domain.User;
import com.yang.service.FService;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
@Transactional
public class FServiceImpl implements FService {
    private FDao dao;

    public void setDao(FDao dao) {
        this.dao = dao;
    }

    @Override
    public User login(String account, String password) {
        try {

            return dao.login(account, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserbyId(int id) {
        try {

            return dao.getUserbyId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List <FileInfo> getFileInfoList() {
        try {
            return dao.getFileList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertLoginLog(int userId) {

        FDao dao = new DaoImpl();
        try {
            return dao.insertLoginLog(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean insertFileInfo(FileInfo fileInfo) {
        try {
            User user = getUserbyId(fileInfo.getUploaderid());
            fileInfo.setUploader(user.getNickname());
            fileInfo.setDate(new Date(System.currentTimeMillis()));
            return dao.insertFileInfo(fileInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insertOperationLog(int userID, String fileName, String type) {

        try {
            int fileID = dao.getFileIDByName(fileName);
            return dao.insertOperationLog(userID, fileID, type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteFile(int fileID) {

        try {
            return dao.deleteFile(fileID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User login(User user) {
        if (user != null) {

            return login(user.getAccount(), user.getPassword());
        } else {
            return null;
        }
    }

    @Override
    public List <User> getUserList() {

        List <User> list = null;
        try {
            list = dao.getUserList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean insertUser(User user) {

        try {
            return dao.insertUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUser(int id) {

        try {
            return dao.deleteUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List <OperationLog> getAllOperateLogs() {

        List <OperationLog> list = null;
        try {

            list = dao.getAllOperateLogs();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean exportDatabaseTool(String hostIP, String userName, String password, String savePath, String fileName, String databaseName) {
        return new DaoImpl().exportDatabaseTool(hostIP, userName, password, savePath, fileName, databaseName);
    }
}
