package com.parsing.deserializer;

import java.lang.reflect.Type;

import com.parsing.bean.JavaBeanInfo;
import com.parsing.exception.UnknowClassTypeException;

public class ParserConfig {
    private final IdentityHashMap<Type, ObjectDeserializer> deserializers         = new IdentityHashMap<Type, ObjectDeserializer>();
    
    private ASMDeserializerFactory asmFactory;
    
    public ParserConfig(){
    	asmFactory = new ASMDeserializerFactory();
    }
    
    public ObjectDeserializer getDeserializer(Type type) throws UnknowClassTypeException {
        ObjectDeserializer derializer = this.deserializers.get(type);
        if (derializer != null) {
            return derializer;
        }

        if (type instanceof Class<?>) {
        	JavaBeanInfo javaBeanInfo = new JavaBeanInfo((Class<?>)type);       	
            try {
            	derializer = asmFactory.createJavaBeanDeserializer(javaBeanInfo);
            	deserializers.put(type, derializer);
				return derializer;
			} catch (Exception e) {
				e.printStackTrace();
			}
        }   
        throw new UnknowClassTypeException("未知的类型");		
    }
    
}
