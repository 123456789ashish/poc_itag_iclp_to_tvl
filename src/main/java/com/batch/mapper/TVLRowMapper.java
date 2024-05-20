package com.batch.mapper;

import com.batch.model.TVLEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TVLRowMapper implements RowMapper<String> {

   @Override
   public String mapRow(ResultSet rs, int rowNum) throws SQLException {
      return rs.getString("TAG_SERIAL_NUMBER");
   }

}