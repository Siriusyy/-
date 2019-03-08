package com.yang.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yang.domain.FileInfo;
import com.yang.domain.User;
import com.yang.service.FService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

public class FileAction extends ActionSupport {

    private FService service;                 //services层

    public FService getService() {
        return service;
    }

    public void setService(FService service) {
        this.service = service;
    }

    private File file1;                      //得到上传的文件
    private String file1ContentType;         //得到文件的类型
    private String file1FileName;            //得到文件的名称
    private String description;              //描述

    private InputStream inputStream;         //输出流用于输出返回的提示信息

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

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

    public String list() {

        List <FileInfo> list = service.getFileInfoList();

        ActionContext.getContext().getValueStack().set("list", list);

        return SUCCESS;
    }

    public String upload() {


        HttpServletRequest request = ServletActionContext.getRequest();


        System.out.println("fileName:" + this.getFile1FileName());
        System.out.println("contentType:" + this.getFile1ContentType());
        System.out.println("File:" + this.getFile1());
        System.out.println("Description:" + this.getDescription());

        User user = (User) ActionContext.getContext().getSession().get("user");
        FileInfo fileInfo = new FileInfo();
        fileInfo.setDescription(description);
        fileInfo.setFilename(file1FileName);
        fileInfo.setUploaderid(user.getId());
        if (!service.insertFileInfo(fileInfo)) {
            return INPUT;
        }


        try {
            FileInputStream fis = new FileInputStream(file1);
            File file = new File("D:\\文件管理系统目录");
            if (!file.exists()) {
                file.mkdir();
            }
            FileOutputStream fos = new FileOutputStream("D:\\文件管理系统目录\\" + file1FileName);

            byte[] buffer = new byte[2048];

            int lenth = 0;

            while ((lenth = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, lenth);
                fos.flush();
            }
            fos.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        service.insertOperationLog(user.getId(), fileInfo.getFilename(), "上传");


        return SUCCESS;


    }

    public String delete() {


        String message="";

        HttpServletRequest request = ServletActionContext.getRequest();
        String file = request.getParameter("file");
        JSONArray jsonArray = JSONArray.fromObject(file);
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jObject = jsonArray.getJSONObject(i);
                int fileid = jObject.getInt("id");
                String filename = jObject.getString("name");
                //Date date=new Date(System.currentTimeMillis());
                String filePath = "D:\\文件管理系统目录\\" + filename;
                File f = new File(filePath);
                if (f.exists() && f.isFile()) {
                    f.delete();
                }

                User user = (User) ActionContext.getContext().getSession().get("user");
                if (service.deleteFile(fileid)) {

                    message = "数据删除成功";

                    if (service.insertOperationLog(user.getId(), filename, "删除")) {
                        System.out.println("插入操作记录成功");
                    } else {
                        System.out.println("插入操作记录失败");
                    }
                } else {

                    message = "数据删除失败";
                }


            }
        } else {
            System.out.println("jsonarray为空");
        }

        try {
            inputStream = new ByteArrayInputStream(message.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }

    public String download(){

        System.out.println("download执行了.....");

        String id = ServletActionContext.getRequest().getParameter("id");
        String name = ServletActionContext.getRequest().getParameter("name");
        try {
            //inputStream=ServletActionContext.getServletContext().getResourceAsStream("D:\\文件管理系统目录\\" + name);
            inputStream=new FileInputStream("D:\\文件管理系统目录\\" + name);
            file1FileName=new String(name.getBytes(),"iso8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }

}
