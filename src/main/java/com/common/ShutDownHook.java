package com.common;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/4/8 15:15
 * @since 1.0
 */
public class ShutDownHook {
    /**
     * private void invokeShutdownHook()
     {
     Thread t = new Thread(new ShutdownHook(), "ShutdownHook-Thread");
     Runtime.getRuntime().addShutdownHook(t);
     }
     第五步，接收到进程退出信号后，在回调的handle接口中执行虚拟机的退出操作，示例代码如下：

     Runtime.getRuntime().exit(0);
     虚拟机退出时，底层会自动检测用户是否注册了ShutdownHook任务，如果有，
     则会自动将ShutdownHook线程拉起，执行它的Run方法，用户只需要在ShutdownHook中执行资源释放操作即可，示例代码如下：

     class ShutdownHook implements Runnable
     {
     @Override
     public void run() {
     System.out.println("ShutdownHook execute start...");
     System.out.print("Netty NioEventLoopGroup shutdownGracefully...");
     try {
     TimeUnit.SECONDS.sleep(10);//模拟应用进程退出前的处理操作
     } catch (InterruptedException e) {
     e.printStackTrace();
     }
     System.out.println("ShutdownHook execute end...");
     System.out.println("Sytem shutdown over, the cost time is 10000MS");
     }
     }
     */
}
