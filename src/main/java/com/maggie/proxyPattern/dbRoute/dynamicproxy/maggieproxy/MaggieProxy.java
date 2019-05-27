package com.maggie.proxyPattern.dbRoute.dynamicproxy.maggieproxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * author:MaggieHao
 * Date:2019-05-25
 * Time:22:12
 */
public class MaggieProxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(MaggieProxy.class);

    public static final String ln = "\r\n";


    public static Object newProxyInstance(MaggieClassLoader classLoader, Class<?>[] interfaces, MaggieInvocationHandler h) {

        //1、动态生成源代码，.java文件
        String src = generateSrc(interfaces);
        LOGGER.info("src:{}", src);

        //2、Java文件输出法磁盘
        String filePath = MaggieProxy.class.getResource("").getPath();
        LOGGER.info("filePath:{}", filePath);
        File f = new File(filePath + "$Proxy0.java");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(f);
            fileWriter.write(src);
            fileWriter.flush();
            fileWriter.close();

            //3、把生成的.java文件编译成.class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
            Iterable iterable = manager.getJavaFileObjects(f);
            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, iterable);

            task.call();
            manager.close();

            //4、编译生成.class文件加载到JVM中来
//            Class proxyClass = classLoader.findClass("$Proxy0.java");
            Class proxyClass = classLoader.findClass("$Proxy0");
            Constructor constructor = proxyClass.getConstructor(MaggieInvocationHandler.class);
            f.delete();

            //5、返回字节码重组以后的新的代理对象
            return constructor.newInstance(h);

        } catch (Exception e) {
            LOGGER.error("", e);
        }

        return null;

    }

    private static String generateSrc(Class<?>[] interfaces) {
        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append("package com.maggie.proxyPattern.dbRoute.dynamicproxy.maggieproxy;" + ln);
        stringBuffer.append("package com.maggie.proxyPattern.dbRoute.dynamicproxy.maggieproxy;" + ln);
        stringBuffer.append("import com.maggie.proxyPattern.Person;" + ln);
        stringBuffer.append("import java.lang.reflect.*;" + ln);
        stringBuffer.append("public class $Proxy0 implements " + interfaces[0].getName() + "{" + ln);
        stringBuffer.append("MaggieInvocationHandler h;" + ln);
        stringBuffer.append("public $Proxy0(MaggieInvocationHandler h) {" +ln);

        stringBuffer.append("this.h = h;");

        stringBuffer.append("}" + ln);

        for (Method m : interfaces[0].getMethods()) {
            Class<?>[] params = m.getParameterTypes();

            StringBuffer paramNames = new StringBuffer();
            StringBuffer paramValues = new StringBuffer();
            StringBuffer paramClasses = new StringBuffer();


            for (int i = 0; i < params.length; i++) {
                Class clazz = params[i];
                String type = clazz.getName();
                String paramName = toLowerFirstCase(clazz.getSimpleName());
                paramNames.append(type + " " + paramName);

                paramValues.append(paramName);
                paramClasses.append(clazz.getName() + ".class");

                for (int i1 = 0; i1 < params.length; i1++) {
                    paramNames.append(",");
                    paramClasses.append(",");
                    paramValues.append(",");
                }
            }
            stringBuffer.append("public " + m.getReturnType().getName() + " " + m.getName() + "(" + paramNames.toString() + ") {" + ln);
            stringBuffer.append("try{" + ln);
            stringBuffer.append("Method m = " + interfaces[0].getName() + ".class.getMethod(\"" + m.getName() + "\",new Class[]{" + paramClasses.toString() + "});" + ln);
            stringBuffer.append((hasReturnValue(m.getReturnType()) ? "return " : "") + getCaseCode("this.h.invoke(this,m,new Object[]{" + paramValues + "})",m.getReturnType()) + ";" + ln);
            stringBuffer.append("}catch(Error _ex) { }");
            stringBuffer.append("catch(Throwable e){" + ln);
            stringBuffer.append("throw new UndeclaredThrowableException(e);" + ln);
            stringBuffer.append("}");
            stringBuffer.append(getReturnEmptyCode(m.getReturnType()));
            stringBuffer.append("}");
        }
        stringBuffer.append("}" + ln);
        return stringBuffer.toString();
    }


    private static Map<Class,Class> mappings = new HashMap<Class, Class>();
    static {
        mappings.put(int.class,Integer.class);
    }

    private static String getReturnEmptyCode(Class<?> returnClass){
        if(mappings.containsKey(returnClass)){
            return "return 0;";
        }else if(returnClass == void.class){
            return "";
        }else {
            return "return null;";
        }
    }

    private static String getCaseCode(String code,Class<?> returnClass){
        if(mappings.containsKey(returnClass)){
            return "((" + mappings.get(returnClass).getName() +  ")" + code + ")." + returnClass.getSimpleName() + "Value()";
        }
        return code;
    }

    private static boolean hasReturnValue(Class<?> clazz){
        return clazz != void.class;
    }
    
    private static String toLowerFirstCase(String src) {

        char [] chars = src.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

}
