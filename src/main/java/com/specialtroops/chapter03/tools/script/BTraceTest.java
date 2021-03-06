package com.specialtroops.chapter03.tools.script;

import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;

import java.lang.reflect.Field;
import java.util.Map;

@BTrace
public class BTraceTest {
//    static String aaa = "aaa";
	@TLS
    static long startTime;

    @OnMethod(
            clazz="com.specialtroops.chapter03.tools.btrace.TestHello",
            method="test"
    )
    public static void traceExecute() {
            startTime = timeMillis();
    }

    @OnMethod(
            clazz="com.specialtroops.chapter03.tools.btrace.TestHello",
            method="test",
            location=@Location(Kind.RETURN)
    )
    public static void traceExecute(@Return boolean result ,
                                    @Self com.specialtroops.chapter03.tools.btrace.TestHello testHello,
                                    @ProbeClassName String className,
                                    @ProbeMethodName String methodName,
                                    int time ,
                                    String name ,
                                    Map<String , String>map) {
         println("====================================>");
         println(strcat("call class = " , className));
         println(strcat("call method = " , methodName));

         Field fieldName = field(className , "name");
         Field fieldFreeMSize = field(className , "THREE_M_SIZE");

         println(strcat("property name = " , str(get(fieldName , testHello))));
         println(strcat("static property THREE_M_SIZE = " , str(get(fieldFreeMSize))));


         println(strcat("map.toString() = " , str(map)));
         println(strcat("map.get() = " , get(map,"fuck")));
         println(strcat(strcat("times:",str(time)), strcat(" , name = " , name)));
         jstack();
         println(strcat("delay:" , str(timeMillis() - startTime)));
         println(strcat("return value is:",str(result)));

    }
    /**
     * 构造函数btrace无法调用之
     */
//    @OnMethod(
//            clazz="com.specialtroops.chapter03.tools.btrace.TestHello",
//            method="TestHello",
//            location=@Location(Kind.ENTRY)
//    )
//        public static void traceExecute2(@Self com.specialtroops.chapter03.tools.btrace.TestHello TestHello,
//                                    @ProbeClassName String className,
//                                    @ProbeMethodName String methodName) {
//        println("====================================>");
//        println(strcat("call class = " , className));
//        println(strcat("call method = " , methodName));
//        jstack();
//        println(strcat("delay:" , str(timeMillis() - startTime)));
//    }
}
