package com.maggie.proxyPattern.dbRoute.dynamicproxy.maggieproxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * Description:
 * author:MaggieHao
 * Date:2019-05-25
 * Time:22:14
 */
public class MaggieClassLoader extends ClassLoader{

    private static final Logger LOGGER = LoggerFactory.getLogger(MaggieClassLoader.class);

    private File classPathFile;

    public MaggieClassLoader() {
        String classPath = MaggieClassLoader.class.getResource("").getPath();

        this.classPathFile = new File(classPath);
    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException{

        String className = MaggieClassLoader.class.getPackage().getName()+"." +name;
        if (classPathFile !=null){
            File classFile = new File(classPathFile,name.replaceAll("\\.","/") + ".class");
            if (classFile.exists()) {
                FileInputStream in = null;
                ByteArrayOutputStream out = null;

                try{
                    in = new FileInputStream(classFile);
                    out = new ByteArrayOutputStream();
                    byte [] buff = new byte[1024];
                    int len;
                    while ((len = in.read(buff)) != -1) {
                        out.write(buff,0,len);
                    }
                    return defineClass(className,out.toByteArray(),0,out.size());

                }catch (Exception e){
                    LOGGER.error("",e);
                }
            }
        }

        return null;
    }
}
