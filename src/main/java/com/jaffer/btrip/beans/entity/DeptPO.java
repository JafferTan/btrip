package com.jaffer.btrip.beans.entity;

import java.util.Date;

public class DeptPO {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String corpId;

    private Long deptPid;

    private String deptName;

    private String managerIds;

    private String levelRelation;

    private String levelRelationMask;

    private String status;

    private Integer subDeptCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId == null ? null : corpId.trim();
    }

    public Long getDeptPid() {
        return deptPid;
    }

    public void setDeptPid(Long deptPid) {
        this.deptPid = deptPid;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public String getManagerIds() {
        return managerIds;
    }

    public void setManagerIds(String managerIds) {
        this.managerIds = managerIds == null ? null : managerIds.trim();
    }

    public String getLevelRelation() {
        return levelRelation;
    }

    public void setLevelRelation(String levelRelation) {
        this.levelRelation = levelRelation == null ? null : levelRelation.trim();
    }

    public String getLevelRelationMask() {
        return levelRelationMask;
    }

    public void setLevelRelationMask(String levelRelationMask) {
        this.levelRelationMask = levelRelationMask == null ? null : levelRelationMask.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getSubDeptCount() {
        return subDeptCount;
    }

    public void setSubDeptCount(Integer subDeptCount) {
        this.subDeptCount = subDeptCount;
    }
}