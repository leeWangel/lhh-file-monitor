package com.lhh.file.model;

	import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="logs")
public class Logs implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator="paymentableGenerator")
	@GenericGenerator(name="paymentableGenerator",strategy="uuid")
	@Column(name="idStr")
	private String idStr;
	@Column(name="f_type_ot")
	private String f_type_ot;
	@Column(name="f_type_ft")
	private String f_type_ft;
	@Column(name="content")
	private String content;
	@Column(name="insert_time")
	private Date insert_time;
	@Column(name="use_user")
	private String use_user;
	public void setIdStr(String idStr){
		this.idStr=idStr;
	}
	public String getIdStr(){
		return idStr;
	}
	public void setF_type_ot(String f_type_ot){
		this.f_type_ot=f_type_ot;
	}
	public String getF_type_ot(){
		return f_type_ot;
	}
	public void setF_type_ft(String f_type_ft){
		this.f_type_ft=f_type_ft;
	}
	public String getF_type_ft(){
		return f_type_ft;
	}
	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}
	public void setInsert_time(Date insert_time){
		this.insert_time=insert_time;
	}
	public Date getInsert_time(){
		return insert_time;
	}
	public void setUse_user(String use_user){
		this.use_user=use_user;
	}
	public String getUse_user(){
		return use_user;
	}
}

