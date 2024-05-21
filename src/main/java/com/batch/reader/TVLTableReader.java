package com.batch.reader;

import com.batch.mapper.TVLRowMapper;
import org.springframework.batch.item.database.*;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class TVLTableReader {



    public JdbcPagingItemReader<String> jdbcPagingItemReader(DataSource dataSource) throws Exception {

        // reading ITAG keys records using JDBC in a paging fashion
        JdbcPagingItemReader<String> reader = new JdbcPagingItemReader<String>();
        reader.setDataSource(dataSource);
        reader.setFetchSize(50);
        reader.setPageSize(50);
        reader.setSaveState(true);
        reader.setQueryProvider(createQuery(dataSource));
        reader.setRowMapper(new TVLRowMapper());
        return reader;



    }

    private PagingQueryProvider createQuery(DataSource dataSource) throws Exception {
        final Map<String, Order> sortKeys = new HashMap<>();
        sortKeys.put("TAG_SERIAL_NUMBER", Order.ASCENDING);

        final SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
        queryProvider.setDataSource(dataSource);
        queryProvider.setSelectClause(" TAG_SERIAL_NUMBER ");
        queryProvider.setFromClause(" ITAGENTITY_10052024 ");
        queryProvider.setWhereClause(" TAG_SERIAL_NUMBER like '09900%' ");
        queryProvider.setSortKeys(sortKeys);
        return queryProvider.getObject();
    }

}
