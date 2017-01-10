package com.muy.common.datasource.table.builder;

import com.muy.common.datasource.table.strategy.AbstractShardStrategy;
import com.muy.common.utils.ReflectUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 分表配置解析工具类
 *
 * @author by yanglikai on 16/08/17.
 */
public class ShardConfigParser {
    private static final Logger log = Logger.getLogger(ShardConfigParser.class);

    private static final String SHARD_CONFIG_DTD = "/dtd/shardtable-config.dtd";
    private static final String IGNORELIST = "ignoreList";
    private static final String PARSELIST = "parseList";
    private static final String STRATEGY = "strategy";
    private static final String RULE_TAG = "rule";
    private static final String ID_TAG = "id";
    private static final String TABLE_NAME = "tableName";
    private static final String STRATEGY_CLASS_TAG = "strategyClass";
    private static final String EXPRESSION = "expression";
    private static final String KEY = "key";

    /**
     * 默认全局的规则
     */
    private static final Map<String, AbstractShardStrategy> ruleStrategy = new HashMap<String, AbstractShardStrategy>();
    private static final Map<String, String> DOC_TYPE_MAP = new HashMap<String, String>();

    static {
        DOC_TYPE_MAP.put("shardtable-config.dtd".toLowerCase(), SHARD_CONFIG_DTD);
        DOC_TYPE_MAP.put("-//task.qbao.com//DTD Shardtable 2.0//EN".toLowerCase(), SHARD_CONFIG_DTD);
    }

    public ShardConfigHolder parse(InputStream input) throws Exception {
        final ShardConfigHolder configHolder = ShardConfigHolder.getInstance();

        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setValidating(false);
        spf.setNamespaceAware(true);
        SAXParser parser = spf.newSAXParser();
        XMLReader reader = parser.getXMLReader();
        // 解析XML实现
        DefaultHandler handler = new DefaultHandler() {
            private String parentElement;

            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

                if (RULE_TAG.equals(qName)) {
                    String id = attributes.getValue(ID_TAG);
                    String className = attributes.getValue(STRATEGY_CLASS_TAG);
                    String expression = attributes.getValue(EXPRESSION);
                    String key = attributes.getValue(KEY);
                    try {
                        Class<?> clazz = Class.forName(className);
                        AbstractShardStrategy strategy = (AbstractShardStrategy) clazz.newInstance();
                        ReflectUtils.setFieldValue(strategy, EXPRESSION, expression);
                        ReflectUtils.setFieldValue(strategy, KEY, key);
                        // 执行子类init方法，替换规则
                        ReflectUtils.invokeMethodByName(strategy, "init");
                        ruleStrategy.put(id, strategy);
                    }catch (ClassNotFoundException e) {
                        throw new SAXException(e);
                    } catch (InstantiationException e) {
                        throw new SAXException(e);
                    } catch (IllegalAccessException e) {
                        throw new SAXException(e);
                    }
                }
                // 解析<strategy>节点
                if (STRATEGY.equals(qName)) {
                    String table = attributes.getValue(TABLE_NAME);
                    String ruleId = attributes.getValue(RULE_TAG);
                    AbstractShardStrategy strategy = ruleStrategy.get(ruleId);
                    if (strategy == null) {
                        log.error(String.format("%s has not config strategy rule", table));
                        throw new SAXException(table + " has not config strategy rule");
                    }
                    configHolder.register(table, strategy);
                }

                if (IGNORELIST.equals(qName) || PARSELIST.equals(qName)) {
                    parentElement = qName;
                }
            }

            public void characters(char[] ch, int start, int length) throws SAXException {
                if (IGNORELIST.equals(parentElement)) {
                    configHolder.addIgnoreId(new String(ch, start, length).trim());
                } else if (PARSELIST.equals(parentElement)) {
                    configHolder.addParseId(new String(ch, start, length).trim());
                }
            }

            public void error(SAXParseException e) throws SAXException {
                throw e;
            }

            public InputSource resolveEntity(String publicId, String systemId) throws IOException, SAXException {
//                if (publicId != null) publicId = publicId.toLowerCase();
//                if (systemId != null) systemId = systemId.toLowerCase();
                // define myself dtd, please
                InputSource source = null;
                try {
                    String path;
                    if (publicId != null) {
                        path = DOC_TYPE_MAP.get(publicId);
                        source = getInputSourceConfig(path, source);
                    }
                    if (source == null) {
//                        path = DOC_TYPE_MAP.get(systemId);
                        source = getInputSourceConfig(SHARD_CONFIG_DTD, source);
                    }
                } catch (Exception e) {
                    throw new SAXException(e);
                }
                return source;
            }
        };
        reader.setContentHandler(handler);
        reader.setEntityResolver(handler);
        reader.setErrorHandler(handler);
        reader.parse(new InputSource(input));

        return configHolder;
    }

    private InputSource getInputSourceConfig(String path, InputSource source) {
        if (path != null) {
            try {
                InputStream inputStream = new FileInputStream(new ClassPathResource(path).getFile());
                source = new InputSource(inputStream);
            } catch (IOException e) {
                log.warn(e.getMessage());
            }
        }
        return source;
    }

    private InputSource getInputSource(String path, InputSource source) {
        //ResourceUtils.getFile(path)
        if (path != null) {
            InputStream in = null;
            try {
                URL url = new URL(path);
                in = url.openStream();
                source = new InputSource(in);
            } catch (IOException e) {
                log.warn(e.getMessage());
            }
        }
        return source;
    }
}
