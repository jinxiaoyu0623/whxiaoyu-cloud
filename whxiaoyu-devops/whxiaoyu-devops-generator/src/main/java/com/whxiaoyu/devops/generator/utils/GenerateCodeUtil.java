package com.whxiaoyu.devops.generator.utils;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 自动生成代码
 * @author jinxiaoyu
 */
public class GenerateCodeUtil {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(tip +"：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StrUtil.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("输入不正确！");
    }

    public static void main(String[] args) {

        String projectPath = System.getProperty("user.dir");
        String moduleName = scanner("请输入模块名？");
        String parentName = scanner("请输入父级包名？");

        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/admin?useUnicode=true&useSSL=false&characterEncoding=utf8",
                "username",
                "password")

                //全局配置
                .globalConfig(builder -> {
                    builder.author("jinxiaoyu") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir(projectPath + "/" + moduleName + "/" + moduleName + "-service" + "/src/main/java"); // 指定输出目录
                })
                //包配置
                .packageConfig(builder -> {
                    builder.parent(parentName) // 设置父包名
                            .moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D://")); // 设置mapperXml生成路径
                })
                //策略配置
                .strategyConfig(builder -> {
                    // 设置需要生成的表名
                    builder.addInclude(scanner("表名，多个英文逗号分割").split(","))
                            // 设置过滤表前缀
                            .addTablePrefix("t_", "c_")
                            .controllerBuilder().enableRestStyle();
                })

                //自定义配置
                .injectionConfig(builder -> {
                    // 如果模板引擎是 freemarker
                    String entityTemplatePath = "/templates/entity.java.ftl";
                    String xmlTemplatePath = "/templates/mapper.xml.ftl";
                    Map<String, String> fileMap = new HashMap<>(1);
                    builder.beforeOutputFile((tableInfo, objectMap) -> {
                        //entity自定义
                        fileMap.put(projectPath + "/" + moduleName + "/" +  moduleName + "-api" +  "/src/main/java/" + parentName.replace(".","/") + "/entity/"
                                + tableInfo.getEntityName() + StringPool.DOT_JAVA,entityTemplatePath);
                        //xml自定义
                        fileMap.put(projectPath + "/" + moduleName + "/" + moduleName + "-service" + "/src/main/resources/mapper/"
                                + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML, xmlTemplatePath);
                    })
                    .customMap(Collections.singletonMap("test", "baomidou"))
                    .customFile(fileMap);
                })

                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateConfig(builder -> builder.disable(TemplateType.ENTITY, TemplateType.XML)
                        .controller("/ftl/controller.java")
                )

                //执行生成器
                .execute();
    }
}
