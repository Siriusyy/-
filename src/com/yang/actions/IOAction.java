package com.yang.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yang.domain.FileInfo;
import com.yang.service.FService;

import java.util.List;

public class IOAction extends ActionSupport {

    private FService service;

    public FService getService() {
        return service;
    }

    public void setService(FService service) {
        this.service = service;
    }
    public String list(){

        List <FileInfo> list = service.getFileInfoList();

        ActionContext.getContext().getValueStack().set("list",list);

        return SUCCESS;
    }
}
