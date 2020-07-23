package com.taurus.generate;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;

/**
 * @description:
 * @author: yedong
 * @time: 2020/4/4 21:20
 */

//配置文件位置
public class GenerateMybatisPlus {


    public void generate(String dataSourceurl, String dataSourcename, String dataSourcepassword, String dataSourcedriver, String tables, String packageParent, boolean isNormalize,String outPutDir) {

        AutoGenerator mpg = new AutoGenerator();
        // 配置策略
        // 1、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
//        gc.setOutputDir(projectPath + "/src/main/java");
//        gc.setOutputDir("D:\\ideaproject\\mygit\\fire-cloud\\yd-modules\\yd-code-generate\\src\\main\\java");
        gc.setOutputDir(outPutDir);

        System.out.println(gc.getOutputDir());
        gc.setAuthor("yedong");
        gc.setOpen(false); //是否打开目录
        gc.setFileOverride(true); // 是否覆盖
        gc.setEntityName("%sEntity");
        gc.setServiceName("%sService"); // 去Service的I前缀
        gc.setIdType(IdType.ID_WORKER);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);
        //2、设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(dataSourceurl);
        dsc.setDriverName(dataSourcedriver);
        dsc.setUsername(dataSourcename);
        dsc.setPassword(dataSourcepassword);
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);
        //3、包的配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(packageParent);
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        mpg.setPackageInfo(pc);
        //4、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude(tables.split(",")); // 设置要映射的表名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true); // 自动lombok；
        // 是否需要开启特定规范字段
        if (true == isNormalize) {
            strategy.setLogicDeleteFieldName("deleted");
            // 自动填充配置
            TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
            TableFill gmtModified = new TableFill("gmt_modified",
                    FieldFill.INSERT_UPDATE);
            ArrayList<TableFill> tableFills = new ArrayList<>();
            tableFills.add(gmtCreate);
            tableFills.add(gmtModified);
            strategy.setTableFillList(tableFills);
            // 乐观锁
            strategy.setVersionFieldName("version");
        }
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);

        //模板生成器
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        TemplateConfig tc = new TemplateConfig();
        tc.setController("/templatesFreemaker/controller.java");
        tc.setService("/templatesFreemaker/service.java");
        tc.setServiceImpl("/templatesFreemaker/serviceImpl.java");
        tc.setEntity("/templatesFreemaker/entity.java");
        tc.setMapper("/templatesFreemaker/mapper.java");
        tc.setXml("/templatesFreemaker/mapper.xml");
        mpg.setTemplate(tc);

        mpg.execute(); //执行
    }
}
