package com.lcw.one.codegen.template;

import com.lcw.one.codegen.bean.CodeGenInfo;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

import java.io.IOException;

public class BeeTlTemplateImpl implements IFillTemplate {

    @Override
    public String fill(String tempFileName, CodeGenInfo genClassBean) {
        String output = null;
        try {
            ClasspathResourceLoader classpathResourceLoader = new ClasspathResourceLoader("template/");
            Configuration cfg = Configuration.defaultConfiguration();
            GroupTemplate gt = new GroupTemplate(classpathResourceLoader, cfg);
            Template t = gt.getTemplate(tempFileName);
            t.binding("beanClassName", genClassBean.getEntityMetaInfo().getClassName());
            t.binding("beanSimpleName", genClassBean.getEntityMetaInfo().getSimpleClassName());
            t.binding("beanIdSimpleName", genClassBean.getEntityMetaInfo().getIdFieldClass().getSimpleName());
            t.binding("beanVarName", genClassBean.getEntityMetaInfo().getVarName());

            t.binding("daoPackageName", genClassBean.getDaoCodeMetaInfo().getPackageName());
            t.binding("daoClassName", genClassBean.getDaoCodeMetaInfo().getClassName());
            t.binding("daoSimpleName", genClassBean.getDaoCodeMetaInfo().getSimpleClassName());
            t.binding("daoVarName", genClassBean.getDaoCodeMetaInfo().getVarName());

            t.binding("servicePackageName", genClassBean.getServiceCodeMetaInfo().getPackageName());
            t.binding("serviceClassName", genClassBean.getServiceCodeMetaInfo().getClassName());
            t.binding("serviceSimpleName", genClassBean.getServiceCodeMetaInfo().getSimpleClassName());
            t.binding("serviceVarName", genClassBean.getServiceCodeMetaInfo().getVarName());

            t.binding("restPackageName", genClassBean.getRestCodeMetaInfo().getPackageName());
            t.binding("restClassName", genClassBean.getRestCodeMetaInfo().getClassName());
            t.binding("restSimpleName", genClassBean.getRestCodeMetaInfo().getSimpleClassName());
            t.binding("restControllerPath", genClassBean.getEntityMetaInfo().getTableName().replaceAll("_", "/"));
            output = t.render();


            // 因为${}不能输入，定义模板得时候将${}定义为#{}，这个地方再替换一下
            output = output.replace("#{", "${");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output;
    }
}
