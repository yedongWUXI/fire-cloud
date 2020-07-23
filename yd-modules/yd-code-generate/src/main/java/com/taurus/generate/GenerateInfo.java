package com.taurus.generate;

import lombok.Data;

/**
 * @Description:
 * @Author: yedong
 * @Date: 2020/7/21 15:00
 * @Modified by:
 */

@Data
public class GenerateInfo {

    private String dataSourceurl = "jdbc:mysql://148.70.213.66:3306/background?useSSL=false&useUnicode=yes&characterEncoding=UTF8";
    private String dataSourcename = "root";
    private String dataSourcepassword = "Root1234!";
    private String dataSourcedriver = "com.mysql.jdbc.Driver";
    private String tables = "";
    private String packageParent = "";
    private String outPutDir = "";
    private boolean isNormalize = true;

}
