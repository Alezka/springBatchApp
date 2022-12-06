package com.example.springbatchtest.mapper;

import com.example.springbatchtest.entity.HoldingsReconExceptionEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class HoldingExceptionMapper implements RowMapper<HoldingsReconExceptionEntity>
{

    @Override
    public HoldingsReconExceptionEntity mapRow( ResultSet rs,  int rowNum) throws SQLException
    {
        HoldingsReconExceptionEntity exception = new HoldingsReconExceptionEntity();
        exception.setId(rs.getLong("sortId"));

        exception.setEntityId(rs.getString("entityId"));
        exception.setSecurityAlias(rs.getBigDecimal("securityAlias"));
        exception.setEventId(rs.getString("eventId"));
//        exception.setTradeDateEts(rs.getDate("tradeDateEts").toLocalDate());
 //       exception.setTradeDateClient(rs.getDate("tradeDateClient").toLocalDate());
        exception.setAmortizedCostBase(rs.getBigDecimal("amortizedCostBaseClient"));
        exception.setAmortizedCostLocal(rs.getBigDecimal("amortizedCostLocalClient"));
        exception.setBookCostBaseEts(rs.getBigDecimal("bookCostBaseEts"));
//        exception.setBookCostBaseClient(rs.getBigDecimal("bookCostBaseClient"));
        exception.setBookCostBaseDifference(rs.getBigDecimal("bookCostBaseDifference"));
        exception.setBookCostLocalEts(rs.getBigDecimal("bookCostLocalEts"));
        exception.setBookCostLocalClient(rs.getBigDecimal("bookCostLocalClient"));
         exception.setBookCostLocalDifference(rs.getBigDecimal("bookCostLocalDifference"));
         exception.setOriginalFaceEts(rs.getBigDecimal("originalFaceEts"));
        exception.setOriginalFaceClient(rs.getBigDecimal("originalFaceClient"));
         exception.setOriginalFaceDifference(rs.getBigDecimal("originalFaceDifference"));
         exception.setQuantityEts(rs.getBigDecimal("quantityEts"));
        exception.setQuantityClient(rs.getBigDecimal("quantityClient"));
         exception.setQuantityDifference(rs.getBigDecimal("quantityDifference"));
        exception.setTaxCostBaseEts(rs.getBigDecimal("taxCostBaseEts"));
        exception.setTaxCostLocalEts(rs.getBigDecimal("taxCostLocalEts"));
        exception.setLongShortEts(rs.getString("longShortEts"));
        exception.setLongShortClient(rs.getString("longShortClient"));
         exception.setLocalAssetCurrency(rs.getString("localAssetCurrency"));
        exception.setSecurityIdClient(rs.getString("securityIdClient"));
         exception.setSecurityIdEts(rs.getString("securityIdEts"));
         exception.setTransactionCode(rs.getString("transactionCode"));
         exception.setSecurityDescription(rs.getString("securityDescription"));
         exception.setInvestmentType(rs.getString("investmentType"));
         exception.setProcessingSecurityType(rs.getString("processingSecurityType"));
         exception.setAppMnemonic(rs.getString("appMnemonic"));
         exception.setClientHoldingMissing(rs.getBoolean("clientHoldingMissing"));
         exception.setTaxHoldingMissing(rs.getBoolean("taxHoldingMissing"));
         exception.setExcluded(rs.getBoolean("isExcluded"));
         exception.setInflation(rs.getBoolean("isInflation"));
         exception.setCurrentWashSaleDeferralBase(rs.getBigDecimal("currentWashSaleDeferralBase"));
         exception.setCurrentWashSaleDeferralLocal(rs.getBigDecimal("currentWashSaleDeferralLocal"));
        exception.setImpairmentLtdBase(rs.getBigDecimal("impairmentLtdBase"));
        exception.setImpairmentLtdLocal(rs.getBigDecimal("impairmentLtdLocal"));
         exception.setSecurityType(rs.getString("securityType"));
        exception.setHoldingClientId(rs.getLong("holdingClientId"));
         exception.setHoldingEtsId(rs.getLong("holdingEtsId"));
        return exception;
    }
}

