package com.lcw.one.codegen.util;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class PomUtils {

    public static List<String> listModule() {
        List<String> modules = null;
        try {
            String pomPath = CodeGenUtil.BASE_PATH + File.separator + "pom.xml";
            MavenXpp3Reader reader = new MavenXpp3Reader();
            Model model = reader.read(new FileInputStream(pomPath));
            modules = model.getModules();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return modules;
    }
}
