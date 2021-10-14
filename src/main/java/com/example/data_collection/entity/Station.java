package com.example.data_collection.entity;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
* Create by Generate...
* Date: Tue Oct 12 16:40:55 CST 2021
* 
* @author fhrdr
*/
@Entity
@Table(name = "t_station")
public class Station {

	/**
	 * 岗位ID
	 */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long stId;
	/**
	 * 公司ID
	 */
	@Column(name = "c_id" )
	private Long cId;
	/**
	 * 岗位名称
	 */
	@Column(name = "st_name" )
	private String stName;
    /**
     * 需求人数
     */
	@Column(name = "st_need")
    private String stNeed;
	/**
	 * 职位描述
	 */
	@Column(name = "st_describe" )
	private String stDescribe;
	/**
	 * 岗位薪酬
	 */
	@Column(name = "st_pay" )
	private String stPay;
	/**
	 * 实习时长
	 */
	@Column(name = "st_duration" )
	private String stDuration;
	/**
	 * 开始时间
	 */
	@Column(name = "st_start" )
	private String stStart;
	/**
	 * 结束时间
	 */
	@Column(name = "st_end" )
	private String stEnd;


  public Long getStId() {
    return stId;
  }

  public void setStId(Long stId) {
    this.stId = stId;
  }


  public Long getCId() {
    return cId;
  }

  public void setCId(Long cId) {
    this.cId = cId;
  }


  public String getStName() {
    return stName;
  }

    public void setStName(String stName) {
    this.stName = stName;
  }

    public String getStNeed() {
        return stNeed;
    }

    public void setStNeed(String stNeed) {
        this.stNeed = stNeed;
    }

    public Long getcId() {
        return cId;
    }

    public void setcId(Long cId) {
        this.cId = cId;
    }


  public String getStDescribe() {
    return stDescribe;
  }

  public void setStDescribe(String stDescribe) {
    this.stDescribe = stDescribe;
  }


  public String getStPay() {
    return stPay;
  }

  public void setStPay(String stPay) {
    this.stPay = stPay;
  }


  public String getStDuration() {
    return stDuration;
  }

  public void setStDuration(String stDuration) {
    this.stDuration = stDuration;
  }


  public String getStStart() {
    return stStart;
  }

  public void setStStart(String stStart) {
    this.stStart = stStart;
  }


  public String getStEnd() {
    return stEnd;
  }

  public void setStEnd(String stEnd) {
    this.stEnd = stEnd;
  }
}
