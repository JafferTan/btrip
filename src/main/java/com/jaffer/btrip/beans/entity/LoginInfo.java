package com.jaffer.btrip.beans.entity;

import lombok.Data;

import java.util.List;

@Data
public class LoginInfo {

    private List<UserCorpsVO> corpVOList;
}
