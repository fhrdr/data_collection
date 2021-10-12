package com.example.data_collection.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Entity;

/**
* Create by Generate...
* Date: Tue Oct 12 16:40:55 CST 2021
* 
* @author fhrdr
*/
@Entity
@Table(name = "t_student")
public class Student {

	/**
	 * 学生ID
	 */
	@Column(name = "s_id" )
	private Long sId;
	/**
	 * 学号
	 */
	@Column(name = "s_number" )
	private String sNumber;
	/**
	 * 密码
	 */
	@Column(name = "s_password" )
	private String sPassword;
	/**
	 * 学院
	 */
	@Column(name = "s_college" )
	private String sCollege;
	/**
	 * 部门
	 */
	@Column(name = "s_department" )
	private String sDepartment;
	/**
	 * 姓名
	 */
	@Column(name = "s_name" )
	private String sName;
	/**
	 * 班级
	 */
	@Column(name = "s_class" )
	private String sClass;
	/**
	 * 联系方式
	 */
	@Column(name = "s_phone" )
	private String sPhone;
	/**
	 * 可选岗位数
	 */
	@Column(name = "s_choice" )
	private Long sChoice;


  public Long getSId() {
    return sId;
  }

  public void setSId(Long sId) {
    this.sId = sId;
  }


  public String getSNumber() {
    return sNumber;
  }

  public void setSNumber(String sNumber) {
    this.sNumber = sNumber;
  }


  public String getSPassword() {
    return sPassword;
  }

  public void setSPassword(String sPassword) {
    this.sPassword = sPassword;
  }


  public String getSCollege() {
    return sCollege;
  }

  public void setSCollege(String sCollege) {
    this.sCollege = sCollege;
  }


  public String getSDepartment() {
    return sDepartment;
  }

  public void setSDepartment(String sDepartment) {
    this.sDepartment = sDepartment;
  }


  public String getSName() {
    return sName;
  }

  public void setSName(String sName) {
    this.sName = sName;
  }


  public String getSClass() {
    return sClass;
  }

  public void setSClass(String sClass) {
    this.sClass = sClass;
  }


  public String getSPhone() {
    return sPhone;
  }

  public void setSPhone(String sPhone) {
    this.sPhone = sPhone;
  }


  public Long getSChoice() {
    return sChoice;
  }

  public void setSChoice(Long sChoice) {
    this.sChoice = sChoice;
  }

}
