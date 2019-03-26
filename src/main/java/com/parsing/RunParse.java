package com.parsing;


import com.parsing.beanFactory.AnnApplicationContext;
import com.parsing.beanFactory.BeanFactory;
import com.parsing.io.PathMatchingResourcePatternResolver;
import com.parsing.io.Resource;
import com.parsing.io.ResourcePatternResolver;
import com.parsing.resolver.ParseMap;
import com.parsing.resolver.XMLParseResolver;

public class RunParse {
	private static BeanFactory beanFactory;
	
	public static Object getBean(String beanName) {
		return beanFactory.getBean(beanName);
	}
	
	public void init(String path) {
		if (beanFactory==null) {
			beanFactory= new AnnApplicationContext("com.parsing");
		}
		toXMLResolver(path);
	}
	public void toXMLResolver(String path) {
		try {
			XMLParseResolver xmlParseResolver = new XMLParseResolver();
			ResourcePatternResolver resolver =  new PathMatchingResourcePatternResolver();

			// 将加载多个绝对匹配的所有Resource
			// 然后进行遍历模式匹配"
			Resource[] resources = resolver.getResources(path);

			for (Resource resource : resources) {
				xmlParseResolver.XMLResolver(resource.getInputStream()); // 读取所有配置文件形成流
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("                                                                                  \r\n" + 
				"                                                                                  \r\n" + 
				"                                                                                  \r\n" + 
				",-.----.                                    ,--,                                  \r\n" + 
				"\\    /  \\              __  ,-.            ,--.'|         ,---,                    \r\n" + 
				"|   :    |           ,' ,'/ /|  .--.--.   |  |,      ,-+-. /  |  ,----._,.        \r\n" + 
				"|   | .\\ :  ,--.--.  '  | |' | /  /    '  `--'_     ,--.'|'   | /   /  ' /        \r\n" + 
				".   : |: | /       \\ |  |   ,'|  :  /`./  ,' ,'|   |   |  ,\"' ||   :     |        \r\n" + 
				"|   |  \\ :.--.  .-. |'  :  /  |  :  ;_    '  | |   |   | /  | ||   | .\\  .        \r\n" + 
				"|   : .  | \\__\\/: . .|  | '    \\  \\    `. |  | :   |   | |  | |.   ; ';  |        \r\n" + 
				":     |`-' ,\" .--.; |;  : |     `----.   \\'  : |__ |   | |  |/ '   .   . |        \r\n" + 
				":   : :   /  /  ,.  ||  , ;    /  /`--'  /|  | '.'||   | |--'   `---`-'| |        \r\n" + 
				"|   | :  ;  :   .'   \\---'    '--'.     / ;  :    ;|   |/       .'__/\\_: |        \r\n" + 
				"`---'.|  |  ,     .-./          `--'---'  |  ,   / '---'        |   :    :        \r\n" + 
				"  `---`   `--`---'                         ---`-'                \\   \\  /         \r\n" + 
				"                                                                  `--`-'          ");
		System.out.println("解析配置文件成功，共读取：" + ParseMap.XmlBeanMap.size()
				+ "个配置");
	}
}
