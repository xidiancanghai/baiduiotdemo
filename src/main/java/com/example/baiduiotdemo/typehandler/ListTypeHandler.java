package com.example.baiduiotdemo.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@MappedTypes(List.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class ListTypeHandler extends BaseTypeHandler<List<?>> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<?> objects, JdbcType jdbcType) throws SQLException {
        Iterator<?> it = objects.iterator();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (it.hasNext()) {
            sb.append(it.next());
        }
        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length()-1);
        }
        sb.append("]");
        preparedStatement.setString(i, sb.toString());
    }

    @Override
    public List<String> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String res = resultSet.getString(s);
        if (res == null) {
            return null;
        }
        return this.converStringToList(res);
    }

    @Override
    public List<String> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String res = resultSet.getString(i);
        if (res == null) {
            return null;
        }
        return this.converStringToList(res);
    }

    @Override
    public List<?> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String res = callableStatement.getString(i);
        if (res == null) {
            return null;
        }
        return  this.converStringToList(res);
    }

    private List<String> converStringToList(String res) {
        String temp = res.substring(1, res.length() - 1);
        String[] strList = temp.split(",");
        List<String> list = new LinkedList<>();
        for (String e : strList) {
            list.add(e);
        }
        return list;
    }
}
