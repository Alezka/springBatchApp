package com.example.springbatchtest.config;

import com.example.springbatchtest.entity.HoldingsReconExceptionEntity;
import com.example.springbatchtest.listener.HoldingExceptionJobListener;
import com.example.springbatchtest.mapper.HoldingExceptionMapper;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.PostgresPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.UnaryOperator;

@Configuration
@Data
public class HoldingExceptionEntityBatchConfig
{

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private HoldingExceptionJobListener holdingExceptionJobListener;

    @Autowired
    DataSource dataSource;

    private static final Logger log = LoggerFactory.getLogger(HoldingExceptionEntityBatchConfig.class);
    private static final String FROM2 =
            " from ( select " +
                    " ROW_NUMBER() OVER(ORDER BY he.id ASC) AS sortId, " +
//                    "he.id as sortId, " +
                    "       COALESCE(hc.ENTITY_ID, he.ENTITY_ID) AS entityId, " +
                    "       he.SECURITY_ALIAS AS securityAlias, " +
                    "       COALESCE(hc.EVENT_ID, he.ORIG_EVENT_ID) AS eventId, " +
                    "       he.TRADE_DATE AS tradeDateEts, " +
                    "       hc.TRADE_DATE AS tradeDateClient, " +
                    "       he.QUANTITY AS quantityEts, " +
                    "       hc.QUANTITY AS quantityClient, " +
                    "       ABS(COALESCE(hc.QUANTITY, 0) - COALESCE(he.QUANTITY, 0))                AS quantityDifference, " +
                    "       he.ORIG_FACE                                                            AS originalFaceEts, " +
                    "       hc.ORIG_FACE                                                            AS originalFaceClient, " +
                    "       ABS(COALESCE(hc.ORIG_FACE, 0) - COALESCE(he.ORIG_FACE, 0))              AS originalFaceDifference, " +
                    "       he.BOOK_COST_B                                                          AS bookCostBaseEts, " +
                    "       hc.PRINCIPAL_COST                                                     , " +
                    "       ABS(COALESCE(hc.PRINCIPAL_COST, 0) - COALESCE(he.BOOK_COST_B, 0))       AS bookCostBaseDifference, " +
                    "       he.BOOK_COST_L                                                          AS bookCostLocalEts, " +
                    "       hc.PRINCIPAL_COST_LOCAL                                                 AS bookCostLocalClient, " +
                    "       ABS(COALESCE(hc.PRINCIPAL_COST_LOCAL, 0) - COALESCE(he.BOOK_COST_L, 0)) AS bookCostLocalDifference, " +
                    "       hc.AMORTIZED_COST                                                       AS amortizedCostBaseClient, " +
                    "       hc.AMORTIZED_COST_LOCAL                                                 AS amortizedCostLocalClient, " +
                    "       he.TAX_COST_B                                                           AS taxCostBaseEts, " +
                    "       he.TAX_COST_L                                                           AS taxCostLocalEts, " +
                    "       he.CURRENT_WASH_SALE_DEFERRAL_B                                         AS currentWashSaleDeferralBase, " +
                    "       he.CURRENT_WASH_SALE_DEFERRAL_L                                         AS currentWashSaleDeferralLocal, " +
                    "       he.LONG_SHORT                                                           AS longShortEts, " +
                    "       hc.LONG_SHORT                                                           AS longShortClient, " +
                    "       COALESCE(hc.LOCAL_ASSET_CURRENCY, he.LOCAL_ASSET_CURRENCY)              AS localAssetCurrency, " +
                    "       hc.SECURITY_ID                                                          AS securityIdClient, " +
                    "       CAST(he.PRIMARY_ASSET_ID AS VARCHAR(100))                               AS securityIdEts, " +
                    "       he.TRANSACTION_CODE                                                     AS transactionCode, " +
                    "       he.id                                                                    AS id, " +
                    "       CASE " +
                    "           WHEN hc.ENTITY_ID IS NULL " +
                    "               THEN TRUE " +
                    "           ELSE FALSE " +
                    "       END                                                                     AS clientHoldingMissing, " +
                    "       CASE " +
                    "           WHEN he.ENTITY_ID IS NULL " +
                    "               THEN TRUE " +
                    "           ELSE FALSE " +
                    "       END                                                                     AS taxHoldingMissing, " +
                    "       COALESCE(sr.IS_EXCLUDED,FALSE)                                          AS isExcluded, " +
                    "       COALESCE(sr.IS_INFLATION,FALSE)                                         AS isInflation, " +
                    "       COALESCE(hc.SECURITY_DESCRIPTION, he.SECURITY_DESCRIPTION)              AS securityDescription, " +
                    "       COALESCE(hc.PROCESSING_SECURITY_TYPE, he.PROCESSING_SECURITY_TYPE)      AS processingSecurityType, " +
                    "       COALESCE(hc.SECURITY_TYPE, he.SECURITY_TYPE)                            AS securityType, " +
                    "       COALESCE(hc.INVESTMENT_TYPE, he.INVESTMENT_TYPE)                        AS investmentType, " +
                    "       COALESCE(hc.APP_MNEMONIC, he.APP_MNEMONIC)                              AS appMnemonic, " +
                    "       hc.IMPAIRMENT_LTD_LOCAL                                                 AS impairmentLtdLocal, " +
                    "       hc.IMPAIRMENT_LTD_BASE                                                  AS impairmentLtdBase, " +
                    "       hc.ID   AS holdingClientId, " +
                    "       he.ID   AS holdingEtsId" +
                    " FROM holdings_recon_holding_client hc " +
                    "    FULL JOIN HOLDINGS_RECON_HOLDING_ETS he " +
                    "         ON hc.ENTITY_ID = he.ENTITY_ID " +
                    "         AND hc.APP_MNEMONIC = he.APP_MNEMONIC " +
                    "         AND hc.EVENT_ID = he.ORIG_EVENT_ID " +
                    "         AND hc.CLIENT_ID = he.CLIENT_ID  " +

                    "    LEFT JOIN LATERAL (SELECT IS_EXCLUDED, " +
                    "                              IS_INFLATION " +
                    "                       FROM SECURITY_RULE " +
                    "                       WHERE CLIENT_ID = :p_client_id " +
                    "                       AND CODE IN (hc.SECURITY_ID, hc.SECURITY_TYPE, hc.PROCESSING_SECURITY_TYPE) " +
                    "                       ORDER BY UPDATE_DATE DESC " +
                    "                       FETCH FIRST 1 ROW ONLY) sr " +
                    "        ON TRUE " +

                    " WHERE ((hc.CLIENT_ID = :p_client_id " +
                    "        AND hc.BUSINESS_DATE = :p_business_date) " +
                    " OR hc.ENTITY_ID IS NULL) " +
                    "AND ((he.CLIENT_ID = :p_client_id " +
                    "      AND he.BUSINESS_DATE = :p_business_date) " +
                    " OR he.ENTITY_ID IS NULL) " +
                    "AND ( " +
                    "    :p_is_with_exact_matches IS TRUE " +
                    "    OR COALESCE(sr.IS_EXCLUDED,FALSE) " +
                    "    OR he.TRADE_DATE <> hc.TRADE_DATE " +
                    "    OR he.QUANTITY <> hc.QUANTITY " +
                    "    OR ((he.QUANTITY IS NULL OR hc.QUANTITY IS NULL) AND " +
                    "        NOT (he.QUANTITY IS NULL AND hc.QUANTITY IS NULL)) " +
                    "    OR COALESCE(ABS(he.ORIG_FACE), 0) <> COALESCE(ABS(hc.ORIG_FACE), 0) " +
                    "    OR he.BOOK_COST_B <> hc.PRINCIPAL_COST " +
                    "    OR ((he.BOOK_COST_B IS NULL OR hc.PRINCIPAL_COST IS NULL) AND " +
                    "        NOT (he.BOOK_COST_B IS NULL AND hc.PRINCIPAL_COST IS NULL)) " +
                    "    OR he.BOOK_COST_L <> hc.PRINCIPAL_COST_LOCAL " +
                    "    OR ((he.BOOK_COST_L IS NULL OR hc.PRINCIPAL_COST_LOCAL IS NULL) AND " +
                    "        NOT (he.BOOK_COST_L IS NULL AND hc.PRINCIPAL_COST_LOCAL IS NULL)) " +
                    "    OR he.LONG_SHORT <> hc.LONG_SHORT " +
                    "    OR COALESCE(he.CURRENT_WASH_SALE_DEFERRAL_B, 0) <> 0 " +
                    "    OR hc.SECURITY_ID <> he.PRIMARY_ASSET_ID " +
                    "    OR (hc.QUANTITY > 0 AND hc.LONG_SHORT = 'short') " +
                    "    OR (hc.QUANTITY < 0 AND hc.LONG_SHORT = 'long') " +
                    "    OR hc.ENTITY_ID IS NULL " +
                    "    OR he.ENTITY_ID IS NULL " +
                    "    OR hc.APP_MNEMONIC IS NULL " +
                    "    OR he.APP_MNEMONIC IS NULL " +
                    "    OR hc.EVENT_ID IS NULL " +
                    "    OR he.ORIG_EVENT_ID IS NULL ) " +
                    " ) as mainQuery ";


    private static final String WHERE = " WHERE ((hc.CLIENT_ID = :p_client_id " +
            "        AND hc.BUSINESS_DATE = :p_business_date) " +
            "OR hc.ENTITY_ID IS NULL) " +
            "AND ((he.CLIENT_ID = :p_client_id " +
            "      AND he.BUSINESS_DATE = :p_business_date) " +
            "OR he.ENTITY_ID IS NULL) " +
            "AND ( " +
            "    :p_is_with_exact_matches IS TRUE " +
            "    OR COALESCE(sr.IS_EXCLUDED,FALSE) " +
            "    OR he.TRADE_DATE <> hc.TRADE_DATE " +
            "    OR he.QUANTITY <> hc.QUANTITY " +
            "    OR ((he.QUANTITY IS NULL OR hc.QUANTITY IS NULL) AND " +
            "        NOT (he.QUANTITY IS NULL AND hc.QUANTITY IS NULL)) " +
            "    OR COALESCE(ABS(he.ORIG_FACE), 0) <> COALESCE(ABS(hc.ORIG_FACE), 0) " +
            "    OR he.BOOK_COST_B <> hc.PRINCIPAL_COST " +
            "    OR ((he.BOOK_COST_B IS NULL OR hc.PRINCIPAL_COST IS NULL) AND " +
            "        NOT (he.BOOK_COST_B IS NULL AND hc.PRINCIPAL_COST IS NULL)) " +
            "    OR he.BOOK_COST_L <> hc.PRINCIPAL_COST_LOCAL " +
            "    OR ((he.BOOK_COST_L IS NULL OR hc.PRINCIPAL_COST_LOCAL IS NULL) AND " +
            "        NOT (he.BOOK_COST_L IS NULL AND hc.PRINCIPAL_COST_LOCAL IS NULL)) " +
            "    OR he.LONG_SHORT <> hc.LONG_SHORT " +
            "    OR COALESCE(he.CURRENT_WASH_SALE_DEFERRAL_B, 0) <> 0 " +
            "    OR hc.SECURITY_ID <> he.PRIMARY_ASSET_ID " +
            "    OR (hc.QUANTITY > 0 AND hc.LONG_SHORT = 'short') " +
            "    OR (hc.QUANTITY < 0 AND hc.LONG_SHORT = 'long') " +
            "    OR hc.ENTITY_ID IS NULL " +
            "    OR he.ENTITY_ID IS NULL " +
            "    OR hc.APP_MNEMONIC IS NULL " +
            "    OR he.APP_MNEMONIC IS NULL " +
            "    OR hc.EVENT_ID IS NULL " +
            "    OR he.ORIG_EVENT_ID IS NULL ) " +
            " ) as mainQuery ";
    private final AtomicLong counter = new AtomicLong(0);

    @Bean
    public Job exportDonationDetailsJob(@Autowired Step holdingDataStep)
    {
        return jobBuilderFactory.get("holdingDataReaderStep")
                .listener(holdingExceptionJobListener)
                .start(holdingDataStep)
                .build();
    }

    @Bean("holdingDataStep")
    public Step holdingDataStep()
    {
        ArrayList<HoldingsReconExceptionEntity> lists = new ArrayList<>();
        return stepBuilderFactory.get("holdingDataStep")
                .<HoldingsReconExceptionEntity, HoldingsReconExceptionEntity>chunk(40)
                .reader(reader())
                .processor((UnaryOperator<HoldingsReconExceptionEntity>) holdingsReconExceptionEntity ->
                {
                    long l = this.counter.incrementAndGet();
                    System.out.println("processor" + l);
                    System.out.println(holdingsReconExceptionEntity);
                    return holdingsReconExceptionEntity;
                })
                .writer(items ->
                {

                    log.info("Inside HoldingsReconExceptionEntity writer with following data");
                    HoldingsReconExceptionEntity holdingsReconExceptionEntity = items.get(0);
                    lists.add(holdingsReconExceptionEntity);
                    System.out.println(lists.size());
                    log.info("holdingsReconExceptionEntity id: {}", holdingsReconExceptionEntity.getId());

                })
                .exceptionHandler((context, throwable) ->
                {
                    System.out.println(throwable.getMessage());
                })
                //               .listener(holdingExceptionJobListener)
                .build();
    }
    @Bean
    public JdbcPagingItemReader<HoldingsReconExceptionEntity> reader()
    {
        final JdbcPagingItemReader<HoldingsReconExceptionEntity> reader = new JdbcPagingItemReader<>();
        final HoldingExceptionMapper mapper = new HoldingExceptionMapper();
        reader.setDataSource(dataSource);
        reader.setFetchSize(368);
        reader.setRowMapper(mapper);
        reader.setPageSize(50);
        reader.setQueryProvider(createQuery());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("p_client_id", 2);
        parameters.put("p_business_date", LocalDate.of(2020, 8, 19));
        parameters.put("p_is_with_exact_matches", false);

        reader.setParameterValues(parameters);
        return reader;
    }

    private PostgresPagingQueryProvider createQuery()
    {
        final Map<String, Order> sortKeys = new HashMap<>();
        sortKeys.put("sortId", Order.ASCENDING);
        final PostgresPagingQueryProvider queryProvider = new PostgresPagingQueryProvider();
        queryProvider.setSelectClause("SELECT *");
        queryProvider.setFromClause(FROM2);
        queryProvider.setSortKeys(sortKeys);
        return queryProvider;
    }

}
