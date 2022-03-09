package com.jaffer.btrip.beans.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeptPOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeptPOExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNull() {
            addCriterion("gmt_modified is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNotNull() {
            addCriterion("gmt_modified is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedEqualTo(Date value) {
            addCriterion("gmt_modified =", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotEqualTo(Date value) {
            addCriterion("gmt_modified <>", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThan(Date value) {
            addCriterion("gmt_modified >", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_modified >=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThan(Date value) {
            addCriterion("gmt_modified <", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThanOrEqualTo(Date value) {
            addCriterion("gmt_modified <=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIn(List<Date> values) {
            addCriterion("gmt_modified in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotIn(List<Date> values) {
            addCriterion("gmt_modified not in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedBetween(Date value1, Date value2) {
            addCriterion("gmt_modified between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotBetween(Date value1, Date value2) {
            addCriterion("gmt_modified not between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andCorpIdIsNull() {
            addCriterion("corp_id is null");
            return (Criteria) this;
        }

        public Criteria andCorpIdIsNotNull() {
            addCriterion("corp_id is not null");
            return (Criteria) this;
        }

        public Criteria andCorpIdEqualTo(String value) {
            addCriterion("corp_id =", value, "corpId");
            return (Criteria) this;
        }

        public Criteria andCorpIdNotEqualTo(String value) {
            addCriterion("corp_id <>", value, "corpId");
            return (Criteria) this;
        }

        public Criteria andCorpIdGreaterThan(String value) {
            addCriterion("corp_id >", value, "corpId");
            return (Criteria) this;
        }

        public Criteria andCorpIdGreaterThanOrEqualTo(String value) {
            addCriterion("corp_id >=", value, "corpId");
            return (Criteria) this;
        }

        public Criteria andCorpIdLessThan(String value) {
            addCriterion("corp_id <", value, "corpId");
            return (Criteria) this;
        }

        public Criteria andCorpIdLessThanOrEqualTo(String value) {
            addCriterion("corp_id <=", value, "corpId");
            return (Criteria) this;
        }

        public Criteria andCorpIdLike(String value) {
            addCriterion("corp_id like", value, "corpId");
            return (Criteria) this;
        }

        public Criteria andCorpIdNotLike(String value) {
            addCriterion("corp_id not like", value, "corpId");
            return (Criteria) this;
        }

        public Criteria andCorpIdIn(List<String> values) {
            addCriterion("corp_id in", values, "corpId");
            return (Criteria) this;
        }

        public Criteria andCorpIdNotIn(List<String> values) {
            addCriterion("corp_id not in", values, "corpId");
            return (Criteria) this;
        }

        public Criteria andCorpIdBetween(String value1, String value2) {
            addCriterion("corp_id between", value1, value2, "corpId");
            return (Criteria) this;
        }

        public Criteria andCorpIdNotBetween(String value1, String value2) {
            addCriterion("corp_id not between", value1, value2, "corpId");
            return (Criteria) this;
        }

        public Criteria andDeptPidIsNull() {
            addCriterion("dept_pid is null");
            return (Criteria) this;
        }

        public Criteria andDeptPidIsNotNull() {
            addCriterion("dept_pid is not null");
            return (Criteria) this;
        }

        public Criteria andDeptPidEqualTo(Long value) {
            addCriterion("dept_pid =", value, "deptPid");
            return (Criteria) this;
        }

        public Criteria andDeptPidNotEqualTo(Long value) {
            addCriterion("dept_pid <>", value, "deptPid");
            return (Criteria) this;
        }

        public Criteria andDeptPidGreaterThan(Long value) {
            addCriterion("dept_pid >", value, "deptPid");
            return (Criteria) this;
        }

        public Criteria andDeptPidGreaterThanOrEqualTo(Long value) {
            addCriterion("dept_pid >=", value, "deptPid");
            return (Criteria) this;
        }

        public Criteria andDeptPidLessThan(Long value) {
            addCriterion("dept_pid <", value, "deptPid");
            return (Criteria) this;
        }

        public Criteria andDeptPidLessThanOrEqualTo(Long value) {
            addCriterion("dept_pid <=", value, "deptPid");
            return (Criteria) this;
        }

        public Criteria andDeptPidIn(List<Long> values) {
            addCriterion("dept_pid in", values, "deptPid");
            return (Criteria) this;
        }

        public Criteria andDeptPidNotIn(List<Long> values) {
            addCriterion("dept_pid not in", values, "deptPid");
            return (Criteria) this;
        }

        public Criteria andDeptPidBetween(Long value1, Long value2) {
            addCriterion("dept_pid between", value1, value2, "deptPid");
            return (Criteria) this;
        }

        public Criteria andDeptPidNotBetween(Long value1, Long value2) {
            addCriterion("dept_pid not between", value1, value2, "deptPid");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNull() {
            addCriterion("dept_name is null");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNotNull() {
            addCriterion("dept_name is not null");
            return (Criteria) this;
        }

        public Criteria andDeptNameEqualTo(String value) {
            addCriterion("dept_name =", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotEqualTo(String value) {
            addCriterion("dept_name <>", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThan(String value) {
            addCriterion("dept_name >", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("dept_name >=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThan(String value) {
            addCriterion("dept_name <", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThanOrEqualTo(String value) {
            addCriterion("dept_name <=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLike(String value) {
            addCriterion("dept_name like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotLike(String value) {
            addCriterion("dept_name not like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameIn(List<String> values) {
            addCriterion("dept_name in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotIn(List<String> values) {
            addCriterion("dept_name not in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameBetween(String value1, String value2) {
            addCriterion("dept_name between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotBetween(String value1, String value2) {
            addCriterion("dept_name not between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andManagerIdsIsNull() {
            addCriterion("manager_ids is null");
            return (Criteria) this;
        }

        public Criteria andManagerIdsIsNotNull() {
            addCriterion("manager_ids is not null");
            return (Criteria) this;
        }

        public Criteria andManagerIdsEqualTo(String value) {
            addCriterion("manager_ids =", value, "managerIds");
            return (Criteria) this;
        }

        public Criteria andManagerIdsNotEqualTo(String value) {
            addCriterion("manager_ids <>", value, "managerIds");
            return (Criteria) this;
        }

        public Criteria andManagerIdsGreaterThan(String value) {
            addCriterion("manager_ids >", value, "managerIds");
            return (Criteria) this;
        }

        public Criteria andManagerIdsGreaterThanOrEqualTo(String value) {
            addCriterion("manager_ids >=", value, "managerIds");
            return (Criteria) this;
        }

        public Criteria andManagerIdsLessThan(String value) {
            addCriterion("manager_ids <", value, "managerIds");
            return (Criteria) this;
        }

        public Criteria andManagerIdsLessThanOrEqualTo(String value) {
            addCriterion("manager_ids <=", value, "managerIds");
            return (Criteria) this;
        }

        public Criteria andManagerIdsLike(String value) {
            addCriterion("manager_ids like", value, "managerIds");
            return (Criteria) this;
        }

        public Criteria andManagerIdsNotLike(String value) {
            addCriterion("manager_ids not like", value, "managerIds");
            return (Criteria) this;
        }

        public Criteria andManagerIdsIn(List<String> values) {
            addCriterion("manager_ids in", values, "managerIds");
            return (Criteria) this;
        }

        public Criteria andManagerIdsNotIn(List<String> values) {
            addCriterion("manager_ids not in", values, "managerIds");
            return (Criteria) this;
        }

        public Criteria andManagerIdsBetween(String value1, String value2) {
            addCriterion("manager_ids between", value1, value2, "managerIds");
            return (Criteria) this;
        }

        public Criteria andManagerIdsNotBetween(String value1, String value2) {
            addCriterion("manager_ids not between", value1, value2, "managerIds");
            return (Criteria) this;
        }

        public Criteria andLevelRelationIsNull() {
            addCriterion("level_relation is null");
            return (Criteria) this;
        }

        public Criteria andLevelRelationIsNotNull() {
            addCriterion("level_relation is not null");
            return (Criteria) this;
        }

        public Criteria andLevelRelationEqualTo(String value) {
            addCriterion("level_relation =", value, "levelRelation");
            return (Criteria) this;
        }

        public Criteria andLevelRelationNotEqualTo(String value) {
            addCriterion("level_relation <>", value, "levelRelation");
            return (Criteria) this;
        }

        public Criteria andLevelRelationGreaterThan(String value) {
            addCriterion("level_relation >", value, "levelRelation");
            return (Criteria) this;
        }

        public Criteria andLevelRelationGreaterThanOrEqualTo(String value) {
            addCriterion("level_relation >=", value, "levelRelation");
            return (Criteria) this;
        }

        public Criteria andLevelRelationLessThan(String value) {
            addCriterion("level_relation <", value, "levelRelation");
            return (Criteria) this;
        }

        public Criteria andLevelRelationLessThanOrEqualTo(String value) {
            addCriterion("level_relation <=", value, "levelRelation");
            return (Criteria) this;
        }

        public Criteria andLevelRelationLike(String value) {
            addCriterion("level_relation like", value, "levelRelation");
            return (Criteria) this;
        }

        public Criteria andLevelRelationNotLike(String value) {
            addCriterion("level_relation not like", value, "levelRelation");
            return (Criteria) this;
        }

        public Criteria andLevelRelationIn(List<String> values) {
            addCriterion("level_relation in", values, "levelRelation");
            return (Criteria) this;
        }

        public Criteria andLevelRelationNotIn(List<String> values) {
            addCriterion("level_relation not in", values, "levelRelation");
            return (Criteria) this;
        }

        public Criteria andLevelRelationBetween(String value1, String value2) {
            addCriterion("level_relation between", value1, value2, "levelRelation");
            return (Criteria) this;
        }

        public Criteria andLevelRelationNotBetween(String value1, String value2) {
            addCriterion("level_relation not between", value1, value2, "levelRelation");
            return (Criteria) this;
        }

        public Criteria andLevelRelationMaskIsNull() {
            addCriterion("level_relation_mask is null");
            return (Criteria) this;
        }

        public Criteria andLevelRelationMaskIsNotNull() {
            addCriterion("level_relation_mask is not null");
            return (Criteria) this;
        }

        public Criteria andLevelRelationMaskEqualTo(String value) {
            addCriterion("level_relation_mask =", value, "levelRelationMask");
            return (Criteria) this;
        }

        public Criteria andLevelRelationMaskNotEqualTo(String value) {
            addCriterion("level_relation_mask <>", value, "levelRelationMask");
            return (Criteria) this;
        }

        public Criteria andLevelRelationMaskGreaterThan(String value) {
            addCriterion("level_relation_mask >", value, "levelRelationMask");
            return (Criteria) this;
        }

        public Criteria andLevelRelationMaskGreaterThanOrEqualTo(String value) {
            addCriterion("level_relation_mask >=", value, "levelRelationMask");
            return (Criteria) this;
        }

        public Criteria andLevelRelationMaskLessThan(String value) {
            addCriterion("level_relation_mask <", value, "levelRelationMask");
            return (Criteria) this;
        }

        public Criteria andLevelRelationMaskLessThanOrEqualTo(String value) {
            addCriterion("level_relation_mask <=", value, "levelRelationMask");
            return (Criteria) this;
        }

        public Criteria andLevelRelationMaskLike(String value) {
            addCriterion("level_relation_mask like", value, "levelRelationMask");
            return (Criteria) this;
        }

        public Criteria andLevelRelationMaskNotLike(String value) {
            addCriterion("level_relation_mask not like", value, "levelRelationMask");
            return (Criteria) this;
        }

        public Criteria andLevelRelationMaskIn(List<String> values) {
            addCriterion("level_relation_mask in", values, "levelRelationMask");
            return (Criteria) this;
        }

        public Criteria andLevelRelationMaskNotIn(List<String> values) {
            addCriterion("level_relation_mask not in", values, "levelRelationMask");
            return (Criteria) this;
        }

        public Criteria andLevelRelationMaskBetween(String value1, String value2) {
            addCriterion("level_relation_mask between", value1, value2, "levelRelationMask");
            return (Criteria) this;
        }

        public Criteria andLevelRelationMaskNotBetween(String value1, String value2) {
            addCriterion("level_relation_mask not between", value1, value2, "levelRelationMask");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}