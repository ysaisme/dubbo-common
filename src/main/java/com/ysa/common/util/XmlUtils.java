package com.ysa.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * XML相关工具方法
 *
 * @author ysa
 * @date 2020/12/12 7:09 PM
 */
@Slf4j
public class XmlUtils {

    /**
     * map转xml
     * <p>
     * map2xml({1={2=2}, 3=3, 4=4, 5=5}, "root")
     * <root>
     * <1>
     * <2>2</2>
     * </1>
     * <3>3</3>
     * <4>4</4>map2Xml
     * <5>5</5>
     * </root>
     * <p>
     * map2xml(1={"-a"="b"}, "root")
     * <root>
     * <1 a="b"></1>
     * <root/>
     *
     * @param data     xml节点数据 key是节点名，(若key由'-'开头，如'-property' 则是其父节点的属性值)，value若是map，则为子节点，否则是节点数据
     * @param rootName xml根节点名称
     * @return string格式xml数据
     */
    public static String map2Xml(Map<?, ?> data, String rootName) {
        Document document = map2XmlDocument(data, rootName);
        return document2String(document);
    }

    /**
     * @param data     xml节点数据 key是节点名，(若key由'-'开头，如'-property' 则是其父节点的属性值)，value若是map，则为子节点，否则是节点数据
     * @param rootName xml根节点名称
     * @return string格式xml数据
     */
    public static String map2Xml(Map<?, ?> data, String rootName, Map<String, String> properties) {
        Document document = map2xmlDocument(data, rootName, properties);
        return document2String(document);
    }

    /**
     * map转xml
     * 默认根节点 名称为xml
     *
     * @param data map格式数据
     * @return string格式xml数据
     */
    public static String map2Xml(Map<?, ?> data) {
        return map2Xml(data, "xml");
    }


    /**
     * xml转json
     *
     * @param xmlStr
     * @return
     * @throws DocumentException
     */
    public static JSONObject xml2Json(String xmlStr) throws DocumentException {
        Document doc = DocumentHelper.parseText(xmlStr);
        JSONObject json = new JSONObject();
        dom4j2Json(doc.getRootElement(), json);
        return json;
    }


    /**
     * xml转json
     *
     * @param element
     * @param json
     */
    public static void dom4j2Json(Element element, JSONObject json) {
        //如果是属性
        for (Object o : element.attributes()) {
            Attribute attr = (Attribute) o;
            if (!DataUtils.isEmpty(attr.getValue())) {
                json.put("@" + attr.getName(), attr.getValue());
            }
        }
        List<Element> chdEl = element.elements();
        if (chdEl.isEmpty() && !DataUtils.isEmpty(element.getText())) {//如果没有子元素,只有一个值
            json.put(element.getName(), element.getText());
        }

        for (Element e : chdEl) {//有子元素
            if (!e.elements().isEmpty()) {//子元素也有子元素
                JSONObject chdjson = new JSONObject();
                dom4j2Json(e, chdjson);
                Object o = json.get(e.getName());
                if (o != null) {
                    JSONArray jsona = null;
                    if (o instanceof JSONObject) {//如果此元素已存在,则转为jsonArray
                        JSONObject jsono = (JSONObject) o;
                        json.remove(e.getName());
                        jsona = new JSONArray();
                        jsona.add(jsono);
                        jsona.add(chdjson);
                    }
                    if (o instanceof JSONArray) {
                        jsona = (JSONArray) o;
                        jsona.add(chdjson);
                    }
                    json.put(e.getName(), jsona);
                } else {
                    if (!chdjson.isEmpty()) {
                        json.put(e.getName(), chdjson);
                    }
                }


            } else {//子元素没有子元素
                for (Object o : element.attributes()) {
                    Attribute attr = (Attribute) o;
                    if (!DataUtils.isEmpty(attr.getValue())) {
                        json.put("@" + attr.getName(), attr.getValue());
                    }
                }
                if (!e.getText().isEmpty()) {
                    json.put(e.getName(), e.getText());
                }
            }
        }
    }

    //====================================================================================私有方法 start

    /**
     * map to xml
     *
     * @param data
     * @param rootName
     * @return Document格式xml
     */
    private static Document map2XmlDocument(Map<?, ?> data, String rootName) {
        final Document document = DocumentHelper.createDocument();
        final Element root = document.addElement(rootName);
        mapToXml(document, root, data);
        return document;
    }

    /**
     * map to xml
     *
     * @param data
     * @param rootName
     * @param properties 根结点属性
     * @return
     */
    private static Document map2xmlDocument(Map<?, ?> data, String rootName, Map<String, String> properties) {
        final Document document = DocumentHelper.createDocument();
        final Element root = document.addElement(rootName);
        for (Map.Entry<String, String> property : properties.entrySet()) {
            root.addAttribute(property.getKey(), property.getValue());
        }
        mapToXml(document, root, data);
        return document;
    }

    /**
     * @param document
     * @param element
     * @param data
     */
    private static void mapToXml(Document document, Element element, Map<?, ?> data) {
        Element keyElement;
        Object value;
        for (Map.Entry<?, ?> entry : data.entrySet()) {
            if ('-' == entry.getKey().toString().charAt(0)) {
                continue;
            }
            keyElement = element.addElement(entry.getKey().toString());
            value = entry.getValue();
            if (value instanceof Map) {
                Map<?, ?> child = (Map) value;
                for (Map.Entry<?, ?> entry1 : child.entrySet()) {
                    if (entry1.getKey().toString().charAt(0) == '-') {
                        keyElement.addAttribute(entry1.getKey().toString().substring(1, entry1.getKey().toString().length()), entry1.getValue().toString());
                    } else {
                        Map<?, ?> map = new HashMap(1) {{
                            put(entry1.getKey(), entry1.getValue());
                        }};
                        mapToXml(document, keyElement, map);
                    }
                }
            } else {
                keyElement.addText(value.toString());
            }
        }
    }

    /**
     * document to string
     *
     * @param document
     * @return 返回字符串格式
     */
    private static String document2String(Document document) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            OutputFormat format = new OutputFormat("   ", true, "UTF-8");
            XMLWriter writer = new XMLWriter(out, format);
            writer.write(document);
            return out.toString("UTF-8");
        } catch (Exception ex) {
            log.error("");
        }
        return null;
    }
    //====================================================================================私有方法 end
}
