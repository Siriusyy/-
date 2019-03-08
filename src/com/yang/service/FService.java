package com.yang.service;

import com.yang.domain.FileInfo;
import com.yang.domain.OperationLog;
import com.yang.domain.User;

import java.util.List;

public interface FService {
    public User login(String account, String password);
    public User getUserbyId(int id);

    public List<FileInfo> getFileInfoList();

    public boolean insertLoginLog(int userId);

    public boolean insertFileInfo(FileInfo fileInfo);

    public boolean insertOperationLog(int userID,String fileName,String type);

    public boolean deleteFile(int fileID);

    public User login(User user);

    public List<User> getUserList();

    boolean insertUser(User user);

    boolean deleteUser(User user);

    public List<OperationLog> getAllOperateLogs();

    public boolean exportDatabaseTool(String hostIP, String userName, String password, String savePath, String fileName, String databaseName);
}
