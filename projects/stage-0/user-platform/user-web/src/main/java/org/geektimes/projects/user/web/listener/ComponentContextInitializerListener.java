package org.geektimes.projects.user.web.listener;

import java.lang.management.ManagementFactory;

import javax.annotation.PreDestroy;
import javax.management.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.geektimes.context.ComponentContext;
import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.management.UserManager;


/**
 * {@link ComponentContext} 初始化器
 * ContextLoaderListener
 */
public class ComponentContextInitializerListener implements ServletContextListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        this.servletContext = sce.getServletContext();
        ComponentContext context = new ComponentContext();
        context.init(servletContext);
        registerJmxMBean();
    }

    private void registerJmxMBean(){
      try{
          // 获取平台 MBean Server
          MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
          // Configuration MBean
          ObjectName objectName = new ObjectName("jolokia:type=User");
          // https://jolokia.org/reference/html/protocol.html#read
          mBeanServer.registerMBean(new UserManager(new User()), objectName);
      }catch (Exception e){
         e.printStackTrace();
      }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
//        ComponentContext.getInstance().destoryContent();
    }



    @PreDestroy
    public void preDestory(){
        System.out.println("容器关闭处理preDestory组件");
    }

}
