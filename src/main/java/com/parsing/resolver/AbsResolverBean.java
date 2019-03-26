package com.parsing.resolver;

import com.alibaba.fastjson.JSONObject;
import com.parsing.pars.ParsProduce;
import com.parsing.pars.factory.Parsing;

import ognl.Ognl;
import ognl.OgnlException;

import java.util.Map;

public abstract class AbsResolverBean extends ParseMap {
    protected String columnName;
    protected Parsing parsing;
    protected String operator;

    public abstract void parse(byte[] data, Map<String, Object> result);

    public abstract void parse(JSONObject object, Map<String, Object> result);

    /**
     * 处理运算
     * @param result
     */
    public void toOperation( Map<String, Object> result){
        if (operator!=null){
            try {
                Object value = Ognl.getValue(operator, result);
                result.put(columnName,value+"");
            } catch (OgnlException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 数据解析转换
     *
     * @param data
     * @return
     */
    public Object toParse(String data) {
        if (parsing != null) {
        	return parsing.pars(data);
        }
        return data;
    }

    public Parsing getParsing() {
        return parsing;
    }

    public void setParsing(String parsing) {
        if (parsing != null)
            this.parsing = ParsProduce.getPars(parsing);
    }

    public void setOperation(String operator) {
        if (operator != null)
            this.operator = operator;
    }


}
