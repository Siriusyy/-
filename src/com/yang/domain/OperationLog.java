package com.yang.domain;

import java.util.Date;

public class OperationLog {
	private int id;
	private int userid;
	private int fileid;
	private String type;
	private Date time;

	@Override
	public String toString() {
		return "OperationLog{" +
				"id=" + id +
				", userid=" + userid +
				", fileid=" + fileid +
				", type='" + type + '\'' +
				", time=" + time +
				'}';
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getFileid() {
		return fileid;
	}

	public void setFileid(int fileid) {
		this.fileid = fileid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
