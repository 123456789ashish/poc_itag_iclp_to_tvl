package com.batch.mapper;

import com.batch.model.TVLTableData;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TVLRowMapper implements RowMapper<TVLTableData> {

   @Override
   public TVLTableData mapRow(ResultSet rs, int rowNum) throws SQLException {
      return TVLTableData.builder()
              .TAG_AGENCY_ID(rs.getString("TAG_AGENCY_ID"))
              .TAG_SERIAL_NUMBER(rs.getString("TAG_SERIAL_NUMBER"))
              .TAG_STATUS(rs.getString("TAG_STATUS"))
              .TAG_ACCT_INFO(rs.getString("TAG_ACCT_INFO"))
              .LIC_STATE(rs.getString("LIC_STATE"))
              .LIC_NUMBER(rs.getString("LIC_NUMBER"))
              .LIC_TYPE(rs.getString("LIC_TYPE"))
              .build();

      /*
      id(rs.getLong("id"))
            .firstName(rs.getString("firstName"))
            .lastName(rs.getString("lastName"))
            .birthdate(rs.getString("birthdate"))
       */
   }

}