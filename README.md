parsing
==
iot物联网数据解析配置化解决方案
--
  该项目是为了解决在物联网项目中服务端中代码的冗余度过高，不方便后期的维护和修改所建立起来的。使用parsing来配置和解析你的数据，只需一行代码既可解决你的问题。同时也方便了后期在有协议改动而带来的数据解析方面的修改，简单的修改下配置文件，重新启动你的服务器即可，你也可以添加文件扫描功能，来实时更新你的解析配置，无需重启服务器。
+ 项目初始加载所有配置文件，无需每次解析配置文件，极大的减少了解析时间。  
+ 实体类映射采用了ASM字节码编程，减少了使用反射，缩短了映射时间。

### 使用说明
#### 1. 在项目启动时来初始化你的配置文件路径
```
String path = "classpath*:parsing/*.xml";
RunParse runParse = new RunParse();
runParse.init(path);
```
> path可以用相对路径也可以用classpath来表示
#### 2. 编写你的配置文件
> 配置文件采用xml文本进行编写
```
<parsing sign="demo" resultType="">
	<bytes initial="HexStringToBytes">
		<verifys>
			<head text="AA"></head>
			<tail text="BB"></tail>
		</verifys>
		<byte parsType="toHexString" index="0" offset="2">
			sign
		</byte>
		<byte index="44" offset="2">
			<bit parsType="bit" index="0" offset="2">
				overVoltage_alarm
			</bit>
		</byte>
		<default value="123456">
			default
		</default>
		<switch key="sign">
			<case text="01">
				<include id="test1" />
			</case>
			<case text="02">
				<include id="test2" />
			</case>
		</switch>
		<if text='sign == "03" or sign == "85"'>
			<include id="test3" />
		</if>
		<foreach index="2" endIndex ="2" itemLength="4" key ="test">
			<include id = "test1" />
		</foreach>
	</bytes>
	<bytes id="test1">
		<byte parsType="toHexString" index="0" offset="2">
			aa
		</byte>
		<byte parsType="toHexString" index="2" offset="2">
			bb
		</byte>
	</bytes>
</parsing>
```
* `<parsing sign="demo" resultType=""></parsing>` parsing标签为root标签，sign属性代表此配置的唯一标识，不可重复！resultType属性用来配置你的解析后属性返回值，默认为Map
*  `<bytes initial="HexStringToBytes"></bytes>`bytes标签为一级标签，可包含多个，作为解析入口的bytes标签不能含有id属性，含有id属性的标签说明此标签是一个分支标签，initial属性是用来初始话数据，以及初始化方法。项目内包含有几个初始话的方法，如需扩展，只需继承`com.parsing.initialData.factory.AbstractInitailData`类，重写initialData方法，并用`@Factory("value")`来注解即可，value的值就是你的initial属性值。
* `<verifys></verifys>`标签为二级标签，此标签内部用来编写你的数据验证方法。
* `<byte parsType="toHexString" index="0" offset="2" operator="sign / 10.0">sign</byte>`标签为二级标签,表示解析的每一个字段，标签内容表示解析后的字段名，parsType属性表示解析方法，可继承`com.parsing.pars.factory.AbstractParsing`类，重写pars方法，并用`@Factory("value")`注解，来扩展你的解析方法。index属性表示此字段的数据位置，offset表示偏移量，operator可用于后续对对数据的计算（适用与ognl表达式）。
* `<bit parsType="bit" index="0" offset="2">overVoltage_alarm</bit>`标签为三级标签，只能包含以`<byte>`标签内部，用来处理位数据，属性值与`<byte>`标签类似。
* `<default value="123456">default</default>`标签为二级标签,用来为你的结果里面添加默认值。
* 此外还有各种判断循环标签,如上，if标签采用ognl表达式。此类标签为二级标签，判断结果用`<include id="">`标签表示，id为对应的`<bytes>`标签
#### 3. 解析数据
```
T t = ToParsing.toPars("demo", data, T.class);
// or
Map m = (Map)ToParsing.toPars("demo", data);
// or 配置有resultType
Object o = ToParsing.toPars("demo", data);
```
> 第一个参数为你的配置文件唯一标识，第二个参数为需要解析的数据，可以为String 或者byte[]	
