package com.hansonwang99.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Administrator on 2018/4/8.
 */
@Data
public class UserLombok {
    private final String name;
    private int age;
    private double score;
    private String[] tags;
}
