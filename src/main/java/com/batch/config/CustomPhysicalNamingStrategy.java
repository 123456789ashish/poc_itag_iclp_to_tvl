package com.batch.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomPhysicalNamingStrategy extends PhysicalNamingStrategyStandardImpl {


    @Override
    public Identifier toPhysicalTableName(Identifier logicalName, JdbcEnvironment context) {
        return Identifier.toIdentifier(logicalName.getText().replace("ICLPEntity","ICLPENTITY_10052024"));
    }


}