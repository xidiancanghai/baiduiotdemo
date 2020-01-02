package com.example.baiduiotdemo.typehandler;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(JSONObject.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class JSONTypeHandler extends BaseTypeHandler<JSONObject> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, JSONObject object, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, String.valueOf(object.toJSONString()));
    }

    @Override
    public JSONObject getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String res = resultSet.getString(s);
        if (res != null) {
            return JSONObject.parseObject(String.valueOf(res));
        }
        return null;
    }

    @Override
    public JSONObject getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String res = resultSet.getString(i);
        if (res != null) {
            return JSONObject.parseObject(String.valueOf(res));
        }
        return null;
    }

    @Override
    public JSONObject getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String res = callableStatement.getString(i);
        if (res != null) {
            return JSONObject.parseObject(String.valueOf(res));
        }
        return null;
    }
}
