package com.parsing.deserializer;

import java.lang.reflect.Type;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.parsing.bean.FieldInfo;
import com.parsing.bean.JavaBeanInfo;

public class ASMDeserializerFactory implements Opcodes{
	
	public ObjectDeserializer createJavaBeanDeserializer(JavaBeanInfo beanInfo) throws Exception {
		byte[] dump = dump(beanInfo);
		ASMGeneratorClassLoader loader = new ASMGeneratorClassLoader();
		Class<?> loaderClass = loader.loadClass("com.parsing.deserializer.testConvertMap_"+beanInfo.name, dump);
		return (ObjectDeserializer)loaderClass.newInstance();		
	}
	
	class ASMGeneratorClassLoader extends ClassLoader {
        public Class<?> loadClass(String className, byte[] classFile) throws ClassFormatError {
            return defineClass(className, classFile, 0,classFile.length);
        }
    }
	
	public byte[] dump(JavaBeanInfo info) throws Exception {

		ClassWriter cw = new ClassWriter(0);
		FieldVisitor fv;
		MethodVisitor mv = null;
		AnnotationVisitor av0;

		cw.visit(V1_8, ACC_PUBLIC + ACC_SUPER, "com/parsing/deserializer/testConvertMap_"+info.name, null,
				"com/parsing/deserializer/AbsByteUtil", null);

		_init(cw, mv,info);
		
		_convertMap(cw, mv,info);
		
		mv = cw.visitMethod(ACC_PUBLIC + ACC_BRIDGE + ACC_SYNTHETIC, "convertMap", "(Ljava/util/Map;)Ljava/lang/Object;", null, null);
		mv.visitCode();
		mv.visitVarInsn(ALOAD, info.var("first"));
		mv.visitVarInsn(ALOAD, info.var("lexer"));
		mv.visitMethodInsn(INVOKEVIRTUAL, "com/parsing/deserializer/testConvertMap_"+info.name, "convertMap", "(Ljava/util/Map;)L"+info.pathName+";", false);
		mv.visitInsn(ARETURN);
		mv.visitMaxs(2, 2);
		mv.visitEnd();
		
		cw.visitEnd();

		return cw.toByteArray();
	}
	
	public void _init(ClassWriter cw,MethodVisitor mv,JavaBeanInfo info) {
		mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
		mv.visitCode();
		mv.visitVarInsn(ALOAD, info.var("first"));
		mv.visitMethodInsn(INVOKESPECIAL, "com/parsing/deserializer/AbsByteUtil", "<init>", "()V", false);
		mv.visitInsn(RETURN);
		mv.visitMaxs(1, 1);
		mv.visitEnd(); 
	}
	
	public void _convertMap(ClassWriter cw,MethodVisitor mv,JavaBeanInfo info) {
		mv = cw.visitMethod(ACC_PUBLIC, "convertMap", "(Ljava/util/Map;)L"+info.pathName+";",
				"(Ljava/util/Map<Ljava/lang/String;Ljava/lang/Object;>;)L"+info.pathName+";", null);
		mv.visitCode();

		mv.visitTypeInsn(NEW, info.pathName);
		mv.visitInsn(DUP);
		mv.visitMethodInsn(INVOKESPECIAL, info.pathName, "<init>", "()V", false);
		info.var("lexer");
		mv.visitVarInsn(ASTORE, info.var(info.name));
		int i =1;
		for (FieldInfo fieldInfo : info.fieldInfoList) {
			
			Type fieldType = fieldInfo.fieldType;
			
			mv.visitVarInsn(ALOAD, info.var("lexer"));
			mv.visitLdcInsn(fieldInfo.name);
			mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", true);
			mv.visitVarInsn(ASTORE, info.var(fieldInfo.name));
			mv.visitVarInsn(ALOAD, info.var(fieldInfo.name));
			Label endLabel = new Label();
			mv.visitJumpInsn(IFNULL, endLabel);
			mv.visitVarInsn(ALOAD, info.var(info.name));
			mv.visitVarInsn(ALOAD, info.var(fieldInfo.name));
			if(fieldType == int.class) {
				mv.visitTypeInsn(CHECKCAST, "java/lang/Integer");
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Integer", "intValue", "()I", false);
				mv.visitMethodInsn(INVOKEVIRTUAL, info.pathName, fieldInfo.methodName, "(I)V", false);					
			}else if(fieldType == Integer.class) {
				
				mv.visitTypeInsn(CHECKCAST, "java/lang/Integer");
				mv.visitMethodInsn(INVOKEVIRTUAL, info.pathName, fieldInfo.methodName, "(Ljava/lang/Integer;)V", false);
			}else if(fieldType == boolean.class){
				mv.visitTypeInsn(CHECKCAST, "java/lang/Boolean");
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Boolean", "booleanValue", "()Z", false);
				mv.visitMethodInsn(INVOKEVIRTUAL, info.pathName, fieldInfo.methodName, "(Z)V", false);
			}else if(fieldType == Boolean.class) {
				mv.visitTypeInsn(CHECKCAST, "java/lang/Boolean");
				mv.visitMethodInsn(INVOKEVIRTUAL, info.pathName, fieldInfo.methodName, "(Ljava/lang/Boolean;)V", false);
			}else if(fieldType == String.class) {
				mv.visitTypeInsn(CHECKCAST, "java/lang/String");
				mv.visitMethodInsn(INVOKEVIRTUAL, info.pathName, fieldInfo.methodName, "(Ljava/lang/String;)V", false);
			}else if(fieldType == byte.class) {
				mv.visitTypeInsn(CHECKCAST, "java/lang/Byte");
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Byte", "byteValue", "()B", false);
				mv.visitMethodInsn(INVOKEVIRTUAL, info.pathName, fieldInfo.methodName, "(B)V", false);
			}else if(fieldType == Byte.class) {
				mv.visitTypeInsn(CHECKCAST, "java/lang/Byte");
				mv.visitMethodInsn(INVOKEVIRTUAL, info.pathName, fieldInfo.methodName, "(Ljava/lang/Byte;)V", false);
			}else if(fieldType == short.class) {
				mv.visitTypeInsn(CHECKCAST, "java/lang/Short");
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Short", "shortValue", "()S", false);
				mv.visitMethodInsn(INVOKEVIRTUAL, info.pathName, fieldInfo.methodName, "(S)V", false);
			}else if(fieldType == Short.class) {
				mv.visitTypeInsn(CHECKCAST, "java/lang/Short");
				mv.visitMethodInsn(INVOKEVIRTUAL, info.pathName, fieldInfo.methodName, "(Ljava/lang/Short;)V", false);
			}else if(fieldType == long.class) {				
				mv.visitTypeInsn(CHECKCAST, "java/lang/Long");
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Long", "longValue", "()J", false);
				mv.visitMethodInsn(INVOKEVIRTUAL, info.pathName, fieldInfo.methodName, "(J)V", false);
			}else if(fieldType == Long.class) {
				mv.visitTypeInsn(CHECKCAST, "java/lang/Long");
				mv.visitMethodInsn(INVOKEVIRTUAL, info.pathName, fieldInfo.methodName, "(Ljava/lang/Long;)V", false);
			}else if(fieldType == float.class) {
				mv.visitTypeInsn(CHECKCAST, "java/lang/Float");
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Float", "floatValue", "()F", false);
				mv.visitMethodInsn(INVOKEVIRTUAL, info.pathName, fieldInfo.methodName, "(F)V", false);
			}else if(fieldType == Float.class) {
				mv.visitTypeInsn(CHECKCAST, "java/lang/Float");
				mv.visitMethodInsn(INVOKEVIRTUAL, info.pathName, fieldInfo.methodName, "(Ljava/lang/Float;)V", false);
			}else if(fieldType == double.class) {
				mv.visitTypeInsn(CHECKCAST, "java/lang/Double");
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Double", "doubleValue", "()D", false);
				mv.visitMethodInsn(INVOKEVIRTUAL, info.pathName, fieldInfo.methodName, "(D)V", false);
			}else if(fieldType == Double.class) {
				mv.visitTypeInsn(CHECKCAST, "java/lang/Double");
				mv.visitMethodInsn(INVOKEVIRTUAL, info.pathName, fieldInfo.methodName, "(Ljava/lang/Double;)V", false);
			}
			
			mv.visitLabel(endLabel);					
			if(i ==1) {
				mv.visitFrame(Opcodes.F_APPEND,2, new Object[] {info.pathName,"java/lang/Object"}, 0, null);
			}else {
				mv.visitFrame(Opcodes.F_APPEND,1, new Object[] {"java/lang/Object"}, 0, null);
			}
			i++;
		}
				
		mv.visitVarInsn(ALOAD, info.var(info.name));
		mv.visitInsn(ARETURN);
		mv.visitMaxs(3, info.variantIndex+1);
		mv.visitEnd();
	}
}
