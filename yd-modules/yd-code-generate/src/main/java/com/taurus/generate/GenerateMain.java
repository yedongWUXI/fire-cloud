package com.taurus.generate;

/**
 * @Description:
 * @Author: yedong
 * @Date: 2020/7/21 10:50
 * @Modified by:
 */

public class GenerateMain {
    private static String dataSourceurl = "jdbc:mysql://148.70.213.66:3306/background?useSSL=false&useUnicode=yes&characterEncoding=UTF8";
    private static String dataSourcename = "root";
    private static String dataSourcepassword = "Root1234!";
    private static String dataSourcedriver = "com.mysql.jdbc.Driver";
    private static String tables = "book";
    private static String packageParent = "com.taurus";
    private static String outPutDir = "D:\\\\ideaproject\\\\mygit\\\\fire-cloud\\\\yd-modules\\\\yd-code-generate\\\\src\\\\main\\\\java";
    private static boolean isNormalize = true;


    public static void generateMybatisPlusTest() {

        new GenerateMybatisPlus().generate(
                dataSourceurl,
                dataSourcename,
                dataSourcepassword,
                dataSourcedriver,
                tables,
                packageParent,
                isNormalize,
                outPutDir);
    }

    public void generateMybatisPlus(GenerateInfo generateInfo) {

        new GenerateMybatisPlus().generate(
                generateInfo.getDataSourceurl(),
                generateInfo.getDataSourcename(),
                generateInfo.getDataSourcepassword(),
                generateInfo.getDataSourcedriver(),
                generateInfo.getTables(),
                generateInfo.getPackageParent(),
                generateInfo.isNormalize(),
                generateInfo.getOutPutDir())
        ;
    }

    public static void main(String[] args) {
        generateMybatisPlusTest();
    }
}
