/** */
package com.qifenqian.bms.basemanager.app.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @ClassName: TdAppEditionControl @Description: TODO(App版本控制)
 *
 * @author LvFeng
 * @date 2019年12月18日
 */
public class TdAppEditionControl implements Serializable {

  /** */
  private static final long serialVersionUID = 1L;
  /** 自增ID */
  private String id;
  /** 设备类别 QINGTING-蜻蜓 QINGWA-青蛙 */
  private String machineType;
  /** 文件路径 */
  private String fileUrl;
  /** 状态 00-可用 99-失效 */
  private String state;
  /** 版本号 */
  private String editionId;
  /** 创建人 */
  private String creator;
  /** 创建时间 */
  private Timestamp createTime;
  /** 备注 */
  private String memo;

  public String getFileUrl() {
    return fileUrl;
  }

  public void setFileUrl(String fileUrl) {
    this.fileUrl = fileUrl;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getMachineType() {
    return machineType;
  }

  public void setMachineType(String machineType) {
    this.machineType = machineType;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getEditionId() {
    return editionId;
  }

  public void setEditionId(String editionId) {
    this.editionId = editionId;
  }

  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }
}
