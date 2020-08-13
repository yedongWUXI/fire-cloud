package com.taurus.entity;

import lombok.Data;

/**
 * @Description:
 * @Author: yedong
 * @Date: 2020/8/13 13:06
 * @Modified by:
 */
@Data
public class DatabaseInfos {
    String tableName;
    String url;
    String driver;
    String userName;
    String password;
}
