package com.jaffer.btrip.beans.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TripStandardPOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TripStandardPOExample() {
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

        public Criteria andTripStandardNameIsNull() {
            addCriterion("trip_standard_name is null");
            return (Criteria) this;
        }

        public Criteria andTripStandardNameIsNotNull() {
            addCriterion("trip_standard_name is not null");
            return (Criteria) this;
        }

        public Criteria andTripStandardNameEqualTo(String value) {
            addCriterion("trip_standard_name =", value, "tripStandardName");
            return (Criteria) this;
        }

        public Criteria andTripStandardNameNotEqualTo(String value) {
            addCriterion("trip_standard_name <>", value, "tripStandardName");
            return (Criteria) this;
        }

        public Criteria andTripStandardNameGreaterThan(String value) {
            addCriterion("trip_standard_name >", value, "tripStandardName");
            return (Criteria) this;
        }

        public Criteria andTripStandardNameGreaterThanOrEqualTo(String value) {
            addCriterion("trip_standard_name >=", value, "tripStandardName");
            return (Criteria) this;
        }

        public Criteria andTripStandardNameLessThan(String value) {
            addCriterion("trip_standard_name <", value, "tripStandardName");
            return (Criteria) this;
        }

        public Criteria andTripStandardNameLessThanOrEqualTo(String value) {
            addCriterion("trip_standard_name <=", value, "tripStandardName");
            return (Criteria) this;
        }

        public Criteria andTripStandardNameLike(String value) {
            addCriterion("trip_standard_name like", value, "tripStandardName");
            return (Criteria) this;
        }

        public Criteria andTripStandardNameNotLike(String value) {
            addCriterion("trip_standard_name not like", value, "tripStandardName");
            return (Criteria) this;
        }

        public Criteria andTripStandardNameIn(List<String> values) {
            addCriterion("trip_standard_name in", values, "tripStandardName");
            return (Criteria) this;
        }

        public Criteria andTripStandardNameNotIn(List<String> values) {
            addCriterion("trip_standard_name not in", values, "tripStandardName");
            return (Criteria) this;
        }

        public Criteria andTripStandardNameBetween(String value1, String value2) {
            addCriterion("trip_standard_name between", value1, value2, "tripStandardName");
            return (Criteria) this;
        }

        public Criteria andTripStandardNameNotBetween(String value1, String value2) {
            addCriterion("trip_standard_name not between", value1, value2, "tripStandardName");
            return (Criteria) this;
        }

        public Criteria andFlightLimitIsNull() {
            addCriterion("flight_limit is null");
            return (Criteria) this;
        }

        public Criteria andFlightLimitIsNotNull() {
            addCriterion("flight_limit is not null");
            return (Criteria) this;
        }

        public Criteria andFlightLimitEqualTo(String value) {
            addCriterion("flight_limit =", value, "flightLimit");
            return (Criteria) this;
        }

        public Criteria andFlightLimitNotEqualTo(String value) {
            addCriterion("flight_limit <>", value, "flightLimit");
            return (Criteria) this;
        }

        public Criteria andFlightLimitGreaterThan(String value) {
            addCriterion("flight_limit >", value, "flightLimit");
            return (Criteria) this;
        }

        public Criteria andFlightLimitGreaterThanOrEqualTo(String value) {
            addCriterion("flight_limit >=", value, "flightLimit");
            return (Criteria) this;
        }

        public Criteria andFlightLimitLessThan(String value) {
            addCriterion("flight_limit <", value, "flightLimit");
            return (Criteria) this;
        }

        public Criteria andFlightLimitLessThanOrEqualTo(String value) {
            addCriterion("flight_limit <=", value, "flightLimit");
            return (Criteria) this;
        }

        public Criteria andFlightLimitLike(String value) {
            addCriterion("flight_limit like", value, "flightLimit");
            return (Criteria) this;
        }

        public Criteria andFlightLimitNotLike(String value) {
            addCriterion("flight_limit not like", value, "flightLimit");
            return (Criteria) this;
        }

        public Criteria andFlightLimitIn(List<String> values) {
            addCriterion("flight_limit in", values, "flightLimit");
            return (Criteria) this;
        }

        public Criteria andFlightLimitNotIn(List<String> values) {
            addCriterion("flight_limit not in", values, "flightLimit");
            return (Criteria) this;
        }

        public Criteria andFlightLimitBetween(String value1, String value2) {
            addCriterion("flight_limit between", value1, value2, "flightLimit");
            return (Criteria) this;
        }

        public Criteria andFlightLimitNotBetween(String value1, String value2) {
            addCriterion("flight_limit not between", value1, value2, "flightLimit");
            return (Criteria) this;
        }

        public Criteria andTrainLimitIsNull() {
            addCriterion("train_limit is null");
            return (Criteria) this;
        }

        public Criteria andTrainLimitIsNotNull() {
            addCriterion("train_limit is not null");
            return (Criteria) this;
        }

        public Criteria andTrainLimitEqualTo(String value) {
            addCriterion("train_limit =", value, "trainLimit");
            return (Criteria) this;
        }

        public Criteria andTrainLimitNotEqualTo(String value) {
            addCriterion("train_limit <>", value, "trainLimit");
            return (Criteria) this;
        }

        public Criteria andTrainLimitGreaterThan(String value) {
            addCriterion("train_limit >", value, "trainLimit");
            return (Criteria) this;
        }

        public Criteria andTrainLimitGreaterThanOrEqualTo(String value) {
            addCriterion("train_limit >=", value, "trainLimit");
            return (Criteria) this;
        }

        public Criteria andTrainLimitLessThan(String value) {
            addCriterion("train_limit <", value, "trainLimit");
            return (Criteria) this;
        }

        public Criteria andTrainLimitLessThanOrEqualTo(String value) {
            addCriterion("train_limit <=", value, "trainLimit");
            return (Criteria) this;
        }

        public Criteria andTrainLimitLike(String value) {
            addCriterion("train_limit like", value, "trainLimit");
            return (Criteria) this;
        }

        public Criteria andTrainLimitNotLike(String value) {
            addCriterion("train_limit not like", value, "trainLimit");
            return (Criteria) this;
        }

        public Criteria andTrainLimitIn(List<String> values) {
            addCriterion("train_limit in", values, "trainLimit");
            return (Criteria) this;
        }

        public Criteria andTrainLimitNotIn(List<String> values) {
            addCriterion("train_limit not in", values, "trainLimit");
            return (Criteria) this;
        }

        public Criteria andTrainLimitBetween(String value1, String value2) {
            addCriterion("train_limit between", value1, value2, "trainLimit");
            return (Criteria) this;
        }

        public Criteria andTrainLimitNotBetween(String value1, String value2) {
            addCriterion("train_limit not between", value1, value2, "trainLimit");
            return (Criteria) this;
        }

        public Criteria andHotelLimitIsNull() {
            addCriterion("hotel_limit is null");
            return (Criteria) this;
        }

        public Criteria andHotelLimitIsNotNull() {
            addCriterion("hotel_limit is not null");
            return (Criteria) this;
        }

        public Criteria andHotelLimitEqualTo(String value) {
            addCriterion("hotel_limit =", value, "hotelLimit");
            return (Criteria) this;
        }

        public Criteria andHotelLimitNotEqualTo(String value) {
            addCriterion("hotel_limit <>", value, "hotelLimit");
            return (Criteria) this;
        }

        public Criteria andHotelLimitGreaterThan(String value) {
            addCriterion("hotel_limit >", value, "hotelLimit");
            return (Criteria) this;
        }

        public Criteria andHotelLimitGreaterThanOrEqualTo(String value) {
            addCriterion("hotel_limit >=", value, "hotelLimit");
            return (Criteria) this;
        }

        public Criteria andHotelLimitLessThan(String value) {
            addCriterion("hotel_limit <", value, "hotelLimit");
            return (Criteria) this;
        }

        public Criteria andHotelLimitLessThanOrEqualTo(String value) {
            addCriterion("hotel_limit <=", value, "hotelLimit");
            return (Criteria) this;
        }

        public Criteria andHotelLimitLike(String value) {
            addCriterion("hotel_limit like", value, "hotelLimit");
            return (Criteria) this;
        }

        public Criteria andHotelLimitNotLike(String value) {
            addCriterion("hotel_limit not like", value, "hotelLimit");
            return (Criteria) this;
        }

        public Criteria andHotelLimitIn(List<String> values) {
            addCriterion("hotel_limit in", values, "hotelLimit");
            return (Criteria) this;
        }

        public Criteria andHotelLimitNotIn(List<String> values) {
            addCriterion("hotel_limit not in", values, "hotelLimit");
            return (Criteria) this;
        }

        public Criteria andHotelLimitBetween(String value1, String value2) {
            addCriterion("hotel_limit between", value1, value2, "hotelLimit");
            return (Criteria) this;
        }

        public Criteria andHotelLimitNotBetween(String value1, String value2) {
            addCriterion("hotel_limit not between", value1, value2, "hotelLimit");
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