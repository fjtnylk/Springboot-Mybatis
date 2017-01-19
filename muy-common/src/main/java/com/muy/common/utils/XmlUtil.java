package com.muy.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by yanglikai on 2017/1/19.
 */
public final class XmlUtil {

    public static String createXmlString(Object inTarget) {
        if (inTarget == null) {
            return "";
        }

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("xml");
        try {
            for (Field field : FieldUtils.getAllFieldsList(inTarget.getClass())) {
                String key = field.getName();
                Object value = FieldUtils.readDeclaredField(inTarget, key, true);
                Element element = root.addElement(key);
                element.addText(String.valueOf(value));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return document.getRootElement().asXML();
    }

    public static String createXmlString(Map<String, Object> inTarget) {
        if (CollectionUtils.isEmpty(inTarget)) {
            return "";
        }

        Map<String, Object> treeMap = new TreeMap<>();
        treeMap.putAll(inTarget);

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("xml");
        for (Map.Entry<String, Object> entry : treeMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            Element element = root.addElement(key);
            element.addText(String.valueOf(value));
        }

        return document.getRootElement().asXML();
    }

    public static <T> T createXmlClass(String inTarget, Class<T> valueType) {
        if (StringUtils.isEmpty(inTarget)) {
            return null;
        }

        InputStream inputStream = new ByteArrayInputStream(inTarget.getBytes());
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(inputStream);
            Element root = document.getRootElement();
            T target = BeanUtils.instantiate(valueType);
            for (Iterator<Element> iterator = root.elementIterator(); iterator.hasNext(); ) {
                Element element = iterator.next();
                String key = element.getName();
                String value = element.getText();
                FieldUtils.writeDeclaredField(target, key, value, true);
            }
            return target;
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public static Map<String, Object> createXmlMap(String inTarget) {
        if (StringUtils.isEmpty(inTarget)) {
            return new HashMap<>();
        }

        InputStream inputStream = new ByteArrayInputStream(inTarget.getBytes());
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(inputStream);
            Element root = document.getRootElement();
            Map<String, Object> maps = new HashMap<>();
            for (Iterator<Element> iterator = root.elementIterator(); iterator.hasNext(); ) {
                Element element = iterator.next();
                String key = element.getName();
                String value = element.getText();
                maps.put(key, value);
            }
            return maps;
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return new HashMap<>();
    }
}
