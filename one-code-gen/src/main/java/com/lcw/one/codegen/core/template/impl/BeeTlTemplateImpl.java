package com.lcw.one.codegen.core.template.impl;

import com.lcw.one.codegen.core.template.IFillTemplate;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

import java.io.IOException;
import java.util.Map;

public class BeeTlTemplateImpl implements IFillTemplate {

    @Override
    public String fill(String tempFileName, Map<String, Object> params) {
        String output = null;
        try {
            ClasspathResourceLoader classpathResourceLoader = new ClasspathResourceLoader("template/");
            Configuration cfg = Configuration.defaultConfiguration();
            cfg.setPlaceholderStart("@{");
            GroupTemplate groupTemplate = new GroupTemplate(classpathResourceLoader, cfg);
            Template template = groupTemplate.getTemplate(tempFileName);
            template.binding(params);
            output = template.render();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output;
    }
}
