package com.batch.reader;

import com.batch.mapper.TVLRowMapper;
import com.batch.model.TVLTableData;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.support.SqlServerPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

public class TVLTableReader {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private TVLRowMapper tvlRowMapper;


    public JdbcPagingItemReader<TVLTableData> customerPagingItemReader(){
        // reading database records using JDBC in a paging fashion
        JdbcPagingItemReader<TVLTableData> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(this.dataSource);
        reader.setFetchSize(10000);
        reader.setRowMapper(tvlRowMapper);

        // Sort Keys
      /*  Map<String, Order> sortKeys = new HashMap<>();
        sortKeys.put("id", Order.ASCENDING);*/

        // MySQL implementation of a PagingQueryProvider using database specific features.
        SqlServerPagingQueryProvider queryProvider = new SqlServerPagingQueryProvider();
        queryProvider.setSelectClause("TAG_AGENCY_ID, TAG_SERIAL_NUMBER, TAG_STATUS, TAG_ACCT_INFO , LIC_STATE,  LIC_NUMBER, LIC_TYPE ");
        queryProvider.setFromClause("from TVLDETAIL");
        //queryProvider.setSortKeys(sortKeys);

        reader.setQueryProvider(queryProvider);

        return reader;
    }

}
