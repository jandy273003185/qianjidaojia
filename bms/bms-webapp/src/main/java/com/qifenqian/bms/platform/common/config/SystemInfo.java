package com.qifenqian.bms.platform.common.config;

import java.io.Serializable;

import com.qifenqian.bms.platform.common.config.type.ReleaseType;

/**
 * 平台信息
 *
 * @project gyzb-platform-common
 * @fileName SystemInfo.java
 * @author huiquan.ma
 * @date 2015年11月18日
 * @memo
 */
public class SystemInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  /** 系统名称 */
  private String systemName;
  /** 系统版本 */
  private String systemVersion;
  /** 发布版本日期 */
  private String releaseDate;
  /** 发布类型 */
  private ReleaseType releaseType;
  /** 系统开发者 */
  private String developer;
  /** 系统使用者 */
  private String user;
  /** 系统路径 */
  private String systemUrl;

  @Override
  public String toString() {
    return "SystemInfo [systemName="
        + systemName
        + ", systemVersion="
        + systemVersion
        + ", releaseDate="
        + releaseDate
        + ", releaseType="
        + releaseType
        + ", developer="
        + developer
        + ", user="
        + user
        + ", systemUrl="
        + systemUrl
        + "]";
  }

  public String getSystemName() {
    return systemName;
  }

  public String getSystemVersion() {
    return systemVersion;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public String getDeveloper() {
    return developer;
  }

  public String getUser() {
    return user;
  }

  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }

  public void setSystemVersion(String systemVersion) {
    this.systemVersion = systemVersion;
  }

  public void setReleaseDate(String ReleaseDate) {
    this.releaseDate = ReleaseDate;
  }

  public void setDeveloper(String developer) {
    this.developer = developer;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public ReleaseType getReleaseType() {
    return releaseType;
  }

  public void setReleaseType(ReleaseType releaseType) {
    this.releaseType = releaseType;
  }

  public String getSystemUrl() {
    return systemUrl;
  }

  public void setSystemUrl(String systemUrl) {
    this.systemUrl = systemUrl;
  }
}
