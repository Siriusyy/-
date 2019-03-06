package com.yang.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yang.domain.FileInfo;
import com.yang.domain.User;
import com.yang.service.FService;
import com.yang.service.impl.FServiceImpl;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class FileAction extends ActionSupport {

    private FService service;

    public FService getService() {
        return service;
    }

    public void setService(FService service) {
        this.service = service;
    }

    private File file1; //得到上传的文件
    private String file1ContentType; //得到文件的类型
    private String file1FileName; //得到文件的名称
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public File getFile1() {
        return file1;
    }

    public void setFile1(File file1) {
        this.file1 = file1;
    }

    public String getFile1ContentType() {
        return file1ContentType;
    }

    public void setFile1ContentType(String file1ContentType) {
        this.file1ContentType = file1ContentType;
    }

    public String getFile1FileName() {
        return file1FileName;
    }

    public void setFile1FileName(String file1FileName) {
        this.file1FileName = file1FileName;
    }

    public String list(){

        List <FileInfo> list = service.getFileInfoList();

        ActionContext.getContext().getValueStack().set("list",list);

        return SUCCESS;
    }

    public String upload(){



        HttpServletRequest request = ServletActionContext.getRequest();


        System.out.println("fileName:"+this.getFile1FileName());
        System.out.println("contentType:"+this.getFile1ContentType());
        System.out.println("File:"+this.getFile1());
        System.out.println("Description:"+this.getDescription());


        try {
            FileInputStream fis=new FileInputStream(file1);
            File file =new File("D:\\文件管理系统目录");
            if(!file.exists()){
                file.mkdir();
            }
            FileOutputStream fos=new FileOutputStream("D:\\文件管理系统目录\\"+file1FileName);

            byte[] buffer =new byte[2048];

            int lenth=0;

            while ((lenth = fis.read(buffer))>0){
                fos.write(buffer,0,lenth);
                fos.flush();
            }
            fos.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user = (User) ActionContext.getContext().getSession().get("user");
        FileInfo fileInfo =new FileInfo();
        fileInfo.setDescription(description);
        fileInfo.setFilename(file1FileName);
        fileInfo.setUploaderid(user.getId());
        if( service.insertFileInfo(fileInfo)){
            return SUCCESS;
        }
        else {
            return INPUT;
        }
        //service.insertOperationLog(userID,filename,"上传")



    }
}
