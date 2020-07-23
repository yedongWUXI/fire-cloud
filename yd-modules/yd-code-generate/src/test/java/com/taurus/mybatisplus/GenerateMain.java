package com.taurus.mybatisplus;

import org.junit.Test;

/**
 * @Description:
 * @Author: yedong
 * @Date: 2020/7/21 10:50
 * @Modified by:
 */

public class GenerateMain {
    private String dataSourceurl = "jdbc:mysql://148.70.213.66:3306/background?useSSL=false&useUnicode=yes&characterEncoding=UTF8";

    private String dataSourcename = "root";

    private String dataSourcepassword = "Root1234!";

    private String dataSourcedriver = "com.mysql.jdbc.Driver";

    private String tables = "book";

    private String packageParent = "com.taurus";

    private boolean isNormalize = true;

    @Test
    public void generateMybatisPlusTest() {
        new GenerateMybatisPlus().generate(
                dataSourceurl,
                dataSourcename,
                dataSourcepassword,
                dataSourcedriver,
                tables,
                packageParent,
                isNormalize);
    }
}
