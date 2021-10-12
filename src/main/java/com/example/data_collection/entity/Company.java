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
@Table(name = "t_company")
public class Company {

	/**
	 * 公司ID
	 */
	@Column(name = "c_id" )
	private Long cId;
	/**
	 * 公司名称
	 */
	@Column(name = "c_name" )
	private String cName;
	/**
	 * 公司人数
	 */
	@Column(name = "c_people_num" )
	private Long cPeopleNum;
	/**
	 * 公司性质
	 */
	@Column(name = "c_nature" )
	private String cNature;
	/**
	 * 公司简介
	 */
	@Column(name = "c_introduction" )
	private String cIntroduction;
	/**
	 * 公司地址
	 */
	@Column(name = "c_address" )
	private String cAddress;


  public Long getCId() {
    return cId;
  }

  public void setCId(Long cId) {
    this.cId = cId;
  }


  public String getCName() {
    return cName;
  }

  public void setCName(String cName) {
    this.cName = cName;
  }


  public Long getCPeopleNum() {
    return cPeopleNum;
  }

  public void setCPeopleNum(Long cPeopleNum) {
    this.cPeopleNum = cPeopleNum;
  }


  public String getCNature() {
    return cNature;
  }

  public void setCNature(String cNature) {
    this.cNature = cNature;
  }


  public String getCIntroduction() {
    return cIntroduction;
  }

  public void setCIntroduction(String cIntroduction) {
    this.cIntroduction = cIntroduction;
  }


  public String getCAddress() {
    return cAddress;
  }

  public void setCAddress(String cAddress) {
    this.cAddress = cAddress;
  }

}
