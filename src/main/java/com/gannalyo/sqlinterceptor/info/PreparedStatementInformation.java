/**
 * gi-jdbc
 * <p>
 * Copyright (C) 2019 - 2019 gi-jdbc
 * <p>
 * Version 0.1.0.
 * Author Gannalyo
 */
package com.gannalyo.sqlinterceptor.info;

import java.util.HashMap;
import java.util.Map;

/**
 * An information bean for the PreparedStatement.
 *
 * @author Gannalyo
 * @date 20190129
 */
public class PreparedStatementInformation {
    private final String sql;
    private final Map<Integer, Value> parameterValues = new HashMap<>();

    public PreparedStatementInformation(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }

    public String getSqlWithValues() {
        final StringBuilder sb = new StringBuilder();
        final String statementQuery = sql;//getStatementQuery();

        int currentParameter = 0;
        for (int pos = 0; pos < statementQuery.length(); pos++) {
            char character = statementQuery.charAt(pos);
            if (statementQuery.charAt(pos) == '?' && currentParameter <= parameterValues.size()) {
                // replace with parameter value
                Value value = parameterValues.get(currentParameter);
                sb.append(value != null ? value.toString() : new Value().toString());
                currentParameter++;
            } else {
                sb.append(character);
            }
        }

        return sb.toString();
    }

    public void setParameterValue(final int position, final Object value) {
        parameterValues.put(position - 1, new Value(value));
    }

    protected Map<Integer, Value> getParameterValues() {
        return parameterValues;
    }

}
