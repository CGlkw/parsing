package com.parsing.beanFactory;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.parsing.utils.ClassTools;

public class AnnApplicationContext implements BeanFactory{
private Map<String, Object> factoryClasses = new LinkedHashMap<String, Object>();
    
    private Set<Class<?>> classSet = new HashSet<>();
    
    /*
     * 构造函数初始化扫描获取所有类
     */
    public AnnApplicationContext(String packageName) {
      
        try {
            //扫描classPath下的所有类，并返回set
        
        	classSet= ClassTools.getClasses(packageName);
            
            /**
             * 遍历所有类，找出有factory注解的类，并封装到linkedHashMap里
             */
            for (Class<?> cls : classSet){
                Factory factory = (Factory) cls.getAnnotation(Factory.class);
                if(factory != null) 
                try {
                    factoryClasses.put(factory.value(), cls.newInstance());
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }
    
    
    /**
     * 输入的id，对应到factoryGroupedClasses的关系，实例化工厂对象
     * @param beanId
     * @return
     */
    @Override
    public Object getBean(String id) {
        
        return factoryClasses.get(id);
    }
}
