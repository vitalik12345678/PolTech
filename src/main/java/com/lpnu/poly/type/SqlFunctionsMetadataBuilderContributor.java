package com.lpnu.poly.type;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.spi.MetadataBuilderContributor;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

public class SqlFunctionsMetadataBuilderContributor implements MetadataBuilderContributor {
    @Override
    public void contribute(MetadataBuilder metadataBuilder) {
        metadataBuilder.applySqlFunction(
                "date_trunc",
                new SQLFunctionTemplate(
                        StandardBasicTypes.TIMESTAMP,
                        "date_trunc('day', (?1 AT TIME ZONE ?2))"
                )
        );
    }
}
