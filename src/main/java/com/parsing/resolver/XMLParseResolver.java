package com.parsing.resolver;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.parsing.resolver.verifytags.ChecknumVerfy;
import com.parsing.resolver.verifytags.HeadVerify;
import com.parsing.resolver.verifytags.LengthVerify;
import com.parsing.resolver.verifytags.TailVerify;
import com.parsing.resolver.verifytags.VerifyData;
import com.parsing.resolver.xmltags.DefaultValueNode;
import com.parsing.resolver.xmltags.ForeachNode;
import com.parsing.resolver.xmltags.IfNode;
import com.parsing.resolver.xmltags.ParseNode;
import com.parsing.resolver.xmltags.SwitchNode;

public class XMLParseResolver {
	// public static HashMap<String, ResultBeanMapper> map = new HashMap<String,
	// ResultBeanMapper>();
	public String path; // application.properties中pars.path的值
	public String sign; // 数据标签

	ParseMap parseMap;

	public static int autoId;

	public XMLParseResolver() {

	}

	public XMLParseResolver(String path) {
		this.path = path;
	}

	/**
	 * 文件加载器读取所有配置文件
	 */
	/*
	 * public void toXMLResolver() { try { ResourcePatternResolver resolver = new
	 * PathMatchingResourcePatternResolver();
	 * 
	 * // 将加载多个绝对匹配的所有Resource // 然后进行遍历模式匹配" Resource[] resources =
	 * resolver.getResources(path);
	 * 
	 * for (Resource resource : resources) { XMLResolver(resource.getInputStream());
	 * // 读取所有配置文件形成流 } } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * System.out.println("====================解析配置文件成功，共读取：" +
	 * ParseMap.XmlBeanMap.size() + "个配置===============================");
	 * System.out.println(
	 * "---------------------------------------------------------------------"); }
	 */

	/**
	 * 解析方法，解析xml配置文件
	 * 
	 * @param is
	 *            文件输入流
	 * @return Map <sign数据标签,xml解析结果对象>
	 */
	@SuppressWarnings({ "unchecked" })
	public void XMLResolver(InputStream is) {

		SAXReader reader = new SAXReader();
		Document document;
		try {
			document = reader.read(is);
			Element root = document.getRootElement();// 得到根节点
			sign = root.attributeValue("sign");// 得到标志符
			parseMap = new ParseMap(sign);
			String resultType = root.attributeValue("resultType");// 得到结果类型

			bytesLabelsResolver(root.elements("bytes")); // 处理bytes节点 标签组

			volJsonLabelsResolver(root.elements("json")); // 处理json节点 标签组

			ResultBeanMapper resultBeanMapper = new ResultBeanMapper(resultType);
			ParseMap.XmlBeanMap.put(sign, resultBeanMapper);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 处理bytes标签组
	 * 
	 * @param bytesElements
	 * @return
	 */

	public void bytesLabelsResolver(List<Element> bytesElements) {
		if (bytesElements.size() > 0) {
			for (Element bytesE : bytesElements) {
				InitialBytesData resolverBytesBean = bytesLabelResolver(null, bytesE);
				parseMap.put(resolverBytesBean.getId(), resolverBytesBean);
			}
		}

	}

	/**
	 * 处理单独bytes标签
	 * 
	 * @param bytesE
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public InitialBytesData bytesLabelResolver(String includeId, Element bytesE) {
		String id = bytesE.attributeValue("id");
		id = id == null ? includeId == null ? sign : includeId : id;
		String initial = bytesE.attributeValue("initial");
		List<Element> byteElements = bytesE.elements("byte");
		List<ParseNode> judgeLabelResolver = judgeLabelResolver(bytesE);
		Element element = bytesE.element("verifys");
		List<VerifyData> verifyLabelResolver = verifyLabelResolver(element);
		List<ResolverByteBean> resolverBeans = byteLabelResolver(byteElements);
		InitialBytesData resolverBytesBean = new InitialBytesData(id, initial, resolverBeans, judgeLabelResolver,verifyLabelResolver);
		resolverBytesBean.setSign(sign);
		return resolverBytesBean;
	}
	/**
	 * 处理数据验证标签
	 * @param verifyElement
	 * @return
	 */
	public List<VerifyData> verifyLabelResolver(Element verifyElement) {
		if(verifyElement==null)
			return null;
		Element headElement = verifyElement.element("head");
		Element tailElement = verifyElement.element("tail");
		Element lengthElement = verifyElement.element("length");
		Element checksumElement = verifyElement.element("checksum");
		List<VerifyData> list = new ArrayList<>();
		headLabelResolver(list, headElement);
		tailLabelResolver(list, tailElement);
		lengthLabelResolver(list, lengthElement);
		checknumLabelResolver(list, checksumElement);
		return list;
	}
	
	public void headLabelResolver(List<VerifyData> verifyDatas,Element element) {
		if(element == null)
			return;
		String text = element.attributeValue("text");
		verifyDatas.add(new HeadVerify(text));
	}
	
	public void tailLabelResolver(List<VerifyData> verifyDatas,Element element) {
		if(element == null)
			return;
		String text = element.attributeValue("text");
		verifyDatas.add(new TailVerify(text));
	}
	public void lengthLabelResolver(List<VerifyData> verifyDatas,Element element) {
		if(element == null)
			return;
		String start = element.attributeValue("start");
		String end = element.attributeValue("end");
		String index = element.attributeValue("index");
		String offset = element.attributeValue("offset");
		verifyDatas.add(new LengthVerify(start, end, index, offset));
	}
	public void checknumLabelResolver(List<VerifyData> verifyDatas,Element element) {
		if(element == null)
			return;
		String start = element.attributeValue("start");
		String end = element.attributeValue("end");
		String index = element.attributeValue("index");
		verifyDatas.add(new ChecknumVerfy(start, end, index));
	}
	/**
	 * 处理批量json标签
	 * 
	 * @param volJsonElements
	 */
	public void volJsonLabelsResolver(List<Element> volJsonElements) {

		if (volJsonElements.size() > 0) {
			for (Element jsonE : volJsonElements) {
				InitialJsonData initialJsonData = jsonLabelResolver(jsonE);
				parseMap.put(initialJsonData.getId(), initialJsonData);
			}
		}

	}

	/**
	 * 处理单独json标签
	 * 
	 * @param jsonE
	 * @return
	 */
	public InitialJsonData jsonLabelResolver(Element jsonE) {

		if (jsonE != null) {
			String id = jsonE.attributeValue("id");
			if (id == null)
				id = sign;
			String initial = jsonE.attributeValue("initial");
			List<ResolverJsonBean> resolverJsonObjBeans = resolverJsonObjBean(jsonE);
			List<ParseNode> judgeLabelResolver = judgeLabelResolver(jsonE);

			InitialJsonData initialJsonData = new InitialJsonData(id, resolverJsonObjBeans);
			initialJsonData.setParseNodes(judgeLabelResolver);
			initialJsonData.setInitialData(initial);
			initialJsonData.setSign(sign);
			return initialJsonData;
		}
		return null;
	}

	/**
	 * 处理所有判断循环标签
	 * 
	 * @param bytesE
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ParseNode> judgeLabelResolver(Element bytesE) {
		List<ParseNode> list = new ArrayList<ParseNode>();
		List<Element> switchElements = bytesE.elements("switch");
		List<Element> ifElements = bytesE.elements("if");
		List<Element> foreachElements = bytesE.elements("foreach");
		List<Element> defaultElements = bytesE.elements("default");
		switchLabelResolver(list, switchElements);
		ifLabelResolver(list, ifElements);
		foreachLabelResolver(list, foreachElements);
		defaultLabelResolver(list, defaultElements);
		return list;
	}

	/**
	 * 处理default标签
	 * 
	 * @param list
	 * @param elements
	 */
	public void defaultLabelResolver(List<ParseNode> list, List<Element> elements) {
		for (Element switchElement : elements) {

			String value = switchElement.attributeValue("value");
			String ognl = switchElement.attributeValue("ognl");
			String key = switchElement.getTextTrim();
			DefaultValueNode defaultValueNode = new DefaultValueNode(key, value, ognl);
			defaultValueNode.setSign(sign);
			list.add(defaultValueNode);
		}
	}

	/**
	 * 处理foreach循环标签
	 * 
	 * @param list
	 * @param elements
	 */
	public void foreachLabelResolver(List<ParseNode> list, List<Element> elements) {
		for (Element switchElement : elements) {

			String key = switchElement.attributeValue("key");
			String index = switchElement.attributeValue("index");
			String endIndex = switchElement.attributeValue("endIndex");
			String itemLength = switchElement.attributeValue("itemLength");
			Element includeElement = switchElement.element("include");
			String includeId = includeElement.attributeValue("id");
			ForeachNode foreachNode = new ForeachNode(index, endIndex, itemLength, includeId, key);
			foreachNode.setSign(sign);
			list.add(foreachNode);
		}
	}

	/**
	 * 处理所有switch标签
	 * 
	 * @param list
	 * @param elements
	 */
	@SuppressWarnings("unchecked")
	public void switchLabelResolver(List<ParseNode> list, List<Element> elements) {
		for (Element switchElement : elements) {

			String key = switchElement.attributeValue("key");
			HashMap<String, String> caseNode = new HashMap<String, String>();

			List<Element> caseElements = switchElement.elements("case");
			for (Element caseElement : caseElements) {
				String includeId = caseElement.element("include").attributeValue("id");
				caseNode.put(caseElement.attributeValue("text"), includeId);
			}
			String defaultId = "";
			Element defaultNode = switchElement.element("default");
			if (defaultNode != null) {
				defaultId = defaultNode.element("include").attributeValue("id");
			}
			SwitchNode switchNode = new SwitchNode(key, caseNode, defaultId);
			switchNode.setSign(sign);
			list.add(switchNode);
		}
	}

	/**
	 * 处理所有if标签
	 *
	 * @param list
	 * @param elements
	 */
	public void ifLabelResolver(List<ParseNode> list, List<Element> elements) {
		for (Element ifElement : elements) {

			String text = ifElement.attributeValue("text");

			String ifNode = ifElement.element("include").attributeValue("id");

			IfNode ifNodeB = new IfNode(text, ifNode);
			ifNodeB.setSign(sign);
			list.add(ifNodeB);
		}
	}

	/**
	 * 处理byte标签组
	 * 
	 * @param byteElements
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ResolverByteBean> byteLabelResolver(List<Element> byteElements) {
		List<ResolverByteBean> list = new ArrayList<ResolverByteBean>();
		if (byteElements.size() > 0) {
			for (Element byteE : byteElements) {
				ResolverByteBean byteResolverBean;
				List<ResolverByteBean> bitBeanList = null;
				String byteIndex = byteE.attributeValue("index");
				String byteOffset = byteE.attributeValue("offset");

				List<Element> bitElements = byteE.elements("bit");
				if (bitElements.size() > 0) {
					bitBeanList = new ArrayList<ResolverByteBean>();
					for (Element bitE : bitElements) {
						String colmnName = bitE.getTextTrim();
						String parsType = bitE.attributeValue("parsType");
						String index = bitE.attributeValue("index");
						String offset = bitE.attributeValue("offset");
						String operator = bitE.attributeValue("operator");
						ResolverByteBean bitBean = new ResolverByteBean(colmnName, parsType, index, offset, operator,
								true, sign);
						bitBeanList.add(bitBean);
					}
				}
				String byteColmnName = byteE.getTextTrim();
				String byteParsType = byteE.attributeValue("parsType");
				String byteOperator = byteE.attributeValue("operator");

				byteResolverBean = new ResolverByteBean(byteColmnName, byteParsType, byteIndex, byteOffset, bitBeanList,
						byteOperator, sign);
				list.add(byteResolverBean);

			}
			return list;
		}

		return null;

	}

	@SuppressWarnings("unchecked")
	public List<ResolverJsonBean> resolverJsonObjBean(Element jsonE) {
		List<ResolverJsonBean> jsonObj = new ArrayList<ResolverJsonBean>();
		if (jsonE != null) {
			List<Element> jsonStrElements = jsonE.elements("jsonStr");
			for (Element bitE : jsonStrElements) {
				String columnName = bitE.getTextTrim();
				String key = bitE.attributeValue("key");
				String parsType = bitE.attributeValue("parsType");
				Element includeDlement = bitE.element("include");
				String includeId = null;
				if (includeDlement != null) {
					includeId = includeDlement.attributeValue("id");
				}
				// 处理内部包含的bytes标签
				Element bytesEle = bitE.element("bytes");
				InitialBytesData resolverBytesBean = null;
				if (bytesEle != null) {
					includeId = "" + (autoId++);
					resolverBytesBean = bytesLabelResolver(includeId, bytesEle);
					parseMap.put(includeId, resolverBytesBean);
				}

				ResolverJsonBean resolverJsonBean = new ResolverJsonBean(columnName, key, includeId, sign, parsType);

				jsonObj.add(resolverJsonBean);
			}
			List<Element> jsonObjElements = jsonE.elements("jsonObj");
			for (Element jsonObjElement : jsonObjElements) {
				String key = jsonObjElement.attributeValue("key");
				List<ResolverJsonBean> resolverJsonBean = resolverJsonObjBean(jsonObjElement);
				ResolverJsonBean resolverJsonBean1 = new ResolverJsonBean(key, resolverJsonBean, sign);
				jsonObj.add(resolverJsonBean1);
			}
			return jsonObj;
		}

		return null;
	}

}