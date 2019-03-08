package com.yang.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yang.domain.FileInfo;
import com.yang.domain.User;
import com.yang.service.FService;
import com.yang.service.impl.FServiceImpl;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.HttpParameters;
import org.apache.struts2.dispatcher.Parameter;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class UserAction extends ActionSupport {

    User user = new User();

    public User getUser() {
        return user;
    }


    private InputStream inputStream;         //输出流用于输出返回的提示信息

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }


    private FService service;

    public FService getService() {
        return service;
    }

    public void setService(FService service) {
        this.service = service;
    }

    @Override
    public String execute() throws Exception {

        return SUCCESS;
    }

    public String login() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String account = request.getParameter("account");
        String password = request.getParameter("password");

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        User user1 = service.login(user);
        ActionContext.getContext().getSession().put("user", user1);
        return LOGIN;
    }

    public String list() {


        List <User> list = service.getUserList();

        ActionContext.getContext().getValueStack().set("list", list);

        return SUCCESS;
    }

    public String addUser() {

        if (service.insertUser(user)) {

            inputStream = new ByteArrayInputStream("<script>alert('添加成功!');window.location.href='users.jsp'</script>".getBytes());
        } else {
            inputStream = new ByteArrayInputStream("<script>alert('添加失败!');window.location.href='users.jsp'</script>".getBytes());
        }

        return SUCCESS;
    }

    public String delUser() {

        String message="";

        if (service.deleteUser(user)) {
            message="删除成功";

        }else {
            message="删除失败";

        }
        ActionContext.getContext().getValueStack().set("message",message);

        return SUCCESS;
    }


}
