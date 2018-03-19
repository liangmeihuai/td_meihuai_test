//
//
//import com.sun.btrace.annotations.*;
//
//import java.lang.reflect.Field;
//import java.util.Map;
//
//import static com.sun.btrace.BTraceUtils.*;
//
//@BTrace
//public class AssignResultDataBtrace {
//    //    static String aaa = "aaa";
//    @TLS
//    static long startTime;
//
//    @OnMethod(
//            clazz="com.td.assignresult.controller.AssignResultController",
//            method="computeBitmap"
//    )
//    public static void traceExecute() {
//        startTime = timeMillis();
//    }
//
//    @OnMethod(
//            clazz="com.td.assignresult.controller.AssignResultController",
//            method="computeBitmap",
//            location=@Location(Kind.ENTRY)
//    )
//    public static void traceExecute(@Self com.td.assignresult.controller.AssignResultController computeBitmap,
//                                    @ProbeClassName String className,
//                                    @ProbeMethodName String methodName) {
//        println("====================================>");
//        println(strcat("call class = " , className));
//        println(strcat("call method = " , methodName));
//        jstack();
//        println(strcat("delay:" , str(timeMillis() - startTime)));
//    }
//}
