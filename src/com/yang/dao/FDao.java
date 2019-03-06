package com.yang.dao;


import com.yang.domain.FileInfo;
import com.yang.domain.OperationLog;
import com.yang.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface FDao {
    public User login(String account, String password) throws SQLException;
    public User getUserbyId(int id) throws SQLException;
    public boolean insertFileInfo(FileInfo fileInfo) throws SQLException;
    public List<FileInfo> getFileList() throws SQLException;
    public boolean insertLoginLog(int userId) throws SQLException;

    public int getFileIDByName(String fileName) throws SQLException;
    public boolean insertOperationLog(int userID, int fileID, String type) throws SQLException;
    public boolean deleteFile(int fileID) throws SQLException;
    public List <User> getUserList() throws SQLException;
    public boolean insertUser(User user) throws SQLException;
    public boolean deleteUser(int id) throws SQLException;
    public List<OperationLog> getAllOperateLogs() throws SQLException;
    public boolean exportDatabaseTool(String hostIP, String userName, String password, String savePath, String fileName, String databaseName);
}
