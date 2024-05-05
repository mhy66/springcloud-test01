package com.mhy.demo02.enums;


import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum UserStatus {
    NORMAL(1,"正常"),
    FREEZE(2,"冻结"),
    ;

    @EnumValue
    private int value;
    @JsonValue
    private String desc;
    UserStatus(int value,String desc){
        this.value = value;
        this.desc = desc;
    }
}
