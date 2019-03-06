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
import java.util.List;

public class UserAction extends ActionSupport {

    User user =new User();

    public User getUser() {
        return user;
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

    public String login(){
        HttpServletRequest request = ServletActionContext.getRequest();
        String account = request.getParameter("account");
        String password = request.getParameter("password");

        User user=new User();
        user.setAccount(account);
        user.setPassword(password);
        User user1 = service.login(user);
        ActionContext.getContext().getSession().put("user",user1);
        return LOGIN;
    }



}
