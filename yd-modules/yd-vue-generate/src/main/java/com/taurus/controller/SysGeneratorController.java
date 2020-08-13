/**
 */

package com.taurus.controller;

import com.taurus.entity.DatabaseInfos;
import com.taurus.service.SysGeneratorService;
import com.taurus.utils.PageUtils;
import com.taurus.utils.Query;
import com.taurus.utils.R;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/generator")
public class SysGeneratorController {
    @Autowired
    private SysGeneratorService sysGeneratorService;

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils pageUtil = sysGeneratorService.queryList(new Query(params));

        return R.ok().put("page", pageUtil);
    }

    /**
     * 生成代码
     */
    @ApiOperation(value = "生成代码", produces = "application/octet-stream")
    @RequestMapping(value = "/code", method = RequestMethod.GET)
    public void code(String tables, HttpServletResponse response) throws IOException {
        byte[] data = sysGeneratorService.generatorCode(tables.split(","));

        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"fast.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }

    /**
     * 定制化生成代码
     */
    @ApiOperation(value = "定制化生成代码", notes = "代码生成并下载")
    @RequestMapping(value = "/generateCode", method = RequestMethod.POST, produces = "application/octet-stream")
    public void generateCode(@RequestBody DatabaseInfos databaseInfos, HttpServletResponse response) throws IOException {
        byte[] data = sysGeneratorService.generatorCodeCustom(databaseInfos);

        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"fast.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/zip");

        IOUtils.write(data, response.getOutputStream());
    }

    /**
     * 连通性测试
     */
    @ApiOperation(value = "连通性测试")
    @RequestMapping(value = "/testConnect", method = RequestMethod.POST)
    public String testConnect(@RequestBody DatabaseInfos databaseInfos) {


        if (databaseInfos.getDriver().contains("mysql")) {

            //数据库驱动
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                DriverManager.getConnection(databaseInfos.getUrl(), databaseInfos.getUserName(), databaseInfos.getPassword());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return "连接失败";
            } catch (SQLException e) {
                e.printStackTrace();
                return "连接失败";
            }


        }

        if (databaseInfos.getDriver().contains("oracle")) {

            //数据库驱动
            try {
                Class.forName("oracle.jdbc.OracleDriver");
                DriverManager.getConnection(databaseInfos.getUrl(), databaseInfos.getUserName(), databaseInfos.getPassword());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return "连接失败";
            } catch (SQLException e) {
                e.printStackTrace();
                return "连接失败";
            }


        }

        if (databaseInfos.getDriver().contains("postgresql")) {

            //数据库驱动
            try {
                Class.forName("org.postgresql.Driver");
                DriverManager.getConnection(databaseInfos.getUrl(), databaseInfos.getUserName(), databaseInfos.getPassword());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return "连接失败";
            } catch (SQLException e) {
                e.printStackTrace();
                return "连接失败";
            }


        }

        if (databaseInfos.getDriver().contains("sqlserver")) {

            //数据库驱动
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                DriverManager.getConnection(databaseInfos.getUrl(), databaseInfos.getUserName(), databaseInfos.getPassword());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return "连接失败";
            } catch (SQLException e) {
                e.printStackTrace();
                return "连接失败";
            }


        }


        return "连接成功";

    }
}
