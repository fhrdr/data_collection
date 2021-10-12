package com.example.data_collection.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Entity;
import java.util.Date;

/**
* Create by Generate...
* Date: Tue Oct 12 16:40:55 CST 2021
* 
* @author fhrdr
*/
@Entity
@Table(name = "t_student_station")
public class StudentStation {

	/**
	 * ID
	 */
	@Column(name = "ss_id" )
	private Long ssId;
	/**
	 * 岗位ID
	 */
	@Column(name = "st_id" )
	private Long stId;
	/**
	 * 学生ID
	 */
	@Column(name = "s_id" )
	private Long sId;
	/**
	 * 记录状态
	 */
	@Column(name = "ss_status" )
	private Boolean ssStatus;
	/**
	 * 记录时间
	 */
	@Column(name = "ss_time" )
	private Date ssTime;


  public Long getSsId() {
    return ssId;
  }

  public void setSsId(Long ssId) {
    this.ssId = ssId;
  }


  public Long getStId() {
    return stId;
  }

  public void setStId(Long stId) {
    this.stId = stId;
  }


  public Long getSId() {
    return sId;
  }

  public void setSId(Long sId) {
    this.sId = sId;
  }


  public Boolean getSsStatus() {
    return ssStatus;
  }

  public void setSsStatus(Boolean ssStatus) {
    this.ssStatus = ssStatus;
  }


  public Date getSsTime() {
    return ssTime;
  }

  public void setSsTime(Date ssTime) {
    this.ssTime = ssTime;
  }

}
