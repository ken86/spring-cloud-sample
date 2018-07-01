package org.kd.ms.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.cglib.core.SpringNamingPolicy;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Set;

@Configuration
public class App {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(App.class);
        ScanClass scanClass = applicationContext.getBean(ScanClass.class);
        scanClass.print();
    }

    @Component
    public static class BeanScannerConfigurer implements BeanFactoryPostProcessor, ApplicationContextAware {

        private ApplicationContext applicationContext;

        @Override
        public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
            org.kd.ms.annotation.BeanScannerConfigurer.Scanner scanner = new org.kd.ms.annotation.BeanScannerConfigurer.Scanner((BeanDefinitionRegistry) configurableListableBeanFactory);
            scanner.setResourceLoader(this.applicationContext);
            scanner.scan("org.kd.ms.annotation");
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext = applicationContext;
        }

    }

//    public static class CustomFactoryBean<T> implements InitializingBean, FactoryBean<T> {
//
//        private String innerClassName;
//
//        @Override
//        public T getObject() throws Exception {
//            Class innerClass = Class.forName(innerClassName);
//            if (innerClass.isInterface()) {
//                return (T) org.kd.ms.annotation.CustomFactoryBean.InterfaceProxy.newInstance(innerClass);
//            } else {
//                Enhancer enhancer = new Enhancer();
//                enhancer.setSuperclass(innerClass);
//                enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);
//                enhancer.setCallback(new org.kd.ms.annotation.CustomFactoryBean.MethodInterceptorImpl());
//                return (T) enhancer.create();
//            }
//        }
//
//        @Override
//        public Class<?> getObjectType() {
//            try {
//                return Class.forName(innerClassName);
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        public boolean isSingleton() {
//            return true;
//        }
//
//        @Override
//        public void afterPropertiesSet() throws Exception {
//
//        }
//
//        public void setInnerClassName(String innerClassName) {
//            this.innerClassName = innerClassName;
//        }
//
//    }
//
//    public static class InterfaceProxy implements InvocationHandler {
//
//        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//            System.out.println("ObjectProxy execute:" + method.getName());
//            return method.invoke(proxy, args);
//        }
//
//        public static <T> T newInstance(Class<T> innerInterface) {
//            ClassLoader classLoader = innerInterface.getClassLoader();
//            Class[] interfaces = new Class[] { innerInterface };
//            org.kd.ms.annotation.CustomFactoryBean.InterfaceProxy proxy = new org.kd.ms.annotation.CustomFactoryBean.InterfaceProxy();
//            return (T) Proxy.newProxyInstance(classLoader, interfaces, proxy);
//        }
//    }
//
//    public static class MethodInterceptorImpl implements MethodInterceptor {
//
//        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//            System.out.println("MethodInterceptorImpl:" + method.getName());
//            return methodProxy.invokeSuper(o, objects);
//        }
//    }
}




