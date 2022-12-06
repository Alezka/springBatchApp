package com.example.springbatchtest.entity;

import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class HoldingsReconExceptionEntity
{
    private Long id;

    private String entityId;

    private BigDecimal securityAlias;

    private String eventId;

    private LocalDate tradeDateEts;

    private LocalDate tradeDateClient;

    private BigDecimal quantityEts;

    private BigDecimal quantityClient;

    private BigDecimal quantityDifference;

    private BigDecimal originalFaceEts;

    private BigDecimal originalFaceClient;

    private BigDecimal originalFaceDifference;

    private BigDecimal bookCostBaseEts;

    private BigDecimal bookCostBaseClient;

    private BigDecimal bookCostBaseDifference;

    private BigDecimal bookCostLocalEts;

    private BigDecimal bookCostLocalClient;

    private BigDecimal bookCostLocalDifference;

    private BigDecimal amortizedCostBase;

    private BigDecimal amortizedCostLocal;

    private BigDecimal taxCostBaseEts;

    private BigDecimal taxCostLocalEts;

    private BigDecimal currentWashSaleDeferralBase;

    private BigDecimal currentWashSaleDeferralLocal;

    private BigDecimal impairmentLtdLocal;

    private BigDecimal impairmentLtdBase;

    private String longShortEts;

    private String longShortClient;

    private String localAssetCurrency;

    private String securityIdClient;

    private String securityIdEts;

    private String transactionCode;

    private String securityDescription;

    private String processingSecurityType;

    private String investmentType;

    private String appMnemonic;

    private String securityType;

    private Boolean clientHoldingMissing;

    private Boolean taxHoldingMissing;

    private Boolean isExcluded;

    //   @Column(name = "IS_INFLATION")
    private Boolean isInflation;

    //  @ManyToOne
    // @JoinColumn(name = "EXECUTION_ID", nullable = false)
//    private HoldingsReconExecution execution;
//
//    //  @OneToMany(mappedBy = "holdingsReconExceptionEntity", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<HoldingsReconExceptionStatus> statuses = new ArrayList<>();
//
    private Long holdingClientId;

    private Long holdingEtsId;
//
//    // @OneToOne(mappedBy = "holdingsReconExceptionEntity")
//    private HoldingsReconOverrideExceptionEntity overrideException;


    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getEntityId()
    {
        return entityId;
    }

    public void setEntityId(String entityId)
    {
        this.entityId = entityId;
    }

    public BigDecimal getSecurityAlias()
    {
        return securityAlias;
    }

    public void setSecurityAlias(BigDecimal securityAlias)
    {
        this.securityAlias = securityAlias;
    }

    public String getEventId()
    {
        return eventId;
    }

    public void setEventId(String eventId)
    {
        this.eventId = eventId;
    }

    public LocalDate getTradeDateEts()
    {
        return tradeDateEts;
    }

    public void setTradeDateEts(LocalDate tradeDateEts)
    {
        this.tradeDateEts = tradeDateEts;
    }

    public LocalDate getTradeDateClient()
    {
        return tradeDateClient;
    }

    public void setTradeDateClient(LocalDate tradeDateClient)
    {
        this.tradeDateClient = tradeDateClient;
    }

    public BigDecimal getQuantityEts()
    {
        return quantityEts;
    }

    public void setQuantityEts(BigDecimal quantityEts)
    {
        this.quantityEts = quantityEts;
    }

    public BigDecimal getQuantityClient()
    {
        return quantityClient;
    }

    public void setQuantityClient(BigDecimal quantityClient)
    {
        this.quantityClient = quantityClient;
    }

    public BigDecimal getQuantityDifference()
    {
        return quantityDifference;
    }

    public void setQuantityDifference(BigDecimal quantityDifference)
    {
        this.quantityDifference = quantityDifference;
    }

    public BigDecimal getOriginalFaceEts()
    {
        return originalFaceEts;
    }

    public void setOriginalFaceEts(BigDecimal originalFaceEts)
    {
        this.originalFaceEts = originalFaceEts;
    }

    public BigDecimal getOriginalFaceClient()
    {
        return originalFaceClient;
    }

    public void setOriginalFaceClient(BigDecimal originalFaceClient)
    {
        this.originalFaceClient = originalFaceClient;
    }

    public BigDecimal getOriginalFaceDifference()
    {
        return originalFaceDifference;
    }

    public void setOriginalFaceDifference(BigDecimal originalFaceDifference)
    {
        this.originalFaceDifference = originalFaceDifference;
    }

    public BigDecimal getBookCostBaseEts()
    {
        return bookCostBaseEts;
    }

    public void setBookCostBaseEts(BigDecimal bookCostBaseEts)
    {
        this.bookCostBaseEts = bookCostBaseEts;
    }

    public BigDecimal getBookCostBaseClient()
    {
        return bookCostBaseClient;
    }

    public void setBookCostBaseClient(BigDecimal bookCostBaseClient)
    {
        this.bookCostBaseClient = bookCostBaseClient;
    }

    public BigDecimal getBookCostBaseDifference()
    {
        return bookCostBaseDifference;
    }

    public void setBookCostBaseDifference(BigDecimal bookCostBaseDifference)
    {
        this.bookCostBaseDifference = bookCostBaseDifference;
    }

    public BigDecimal getBookCostLocalEts()
    {
        return bookCostLocalEts;
    }

    public void setBookCostLocalEts(BigDecimal bookCostLocalEts)
    {
        this.bookCostLocalEts = bookCostLocalEts;
    }

    public BigDecimal getBookCostLocalClient()
    {
        return bookCostLocalClient;
    }

    public void setBookCostLocalClient(BigDecimal bookCostLocalClient)
    {
        this.bookCostLocalClient = bookCostLocalClient;
    }

    public BigDecimal getBookCostLocalDifference()
    {
        return bookCostLocalDifference;
    }

    public void setBookCostLocalDifference(BigDecimal bookCostLocalDifference)
    {
        this.bookCostLocalDifference = bookCostLocalDifference;
    }

    public BigDecimal getAmortizedCostBase()
    {
        return amortizedCostBase;
    }

    public void setAmortizedCostBase(BigDecimal amortizedCostBase)
    {
        this.amortizedCostBase = amortizedCostBase;
    }

    public BigDecimal getAmortizedCostLocal()
    {
        return amortizedCostLocal;
    }

    public void setAmortizedCostLocal(BigDecimal amortizedCostLocal)
    {
        this.amortizedCostLocal = amortizedCostLocal;
    }

    public BigDecimal getTaxCostBaseEts()
    {
        return taxCostBaseEts;
    }

    public void setTaxCostBaseEts(BigDecimal taxCostBaseEts)
    {
        this.taxCostBaseEts = taxCostBaseEts;
    }

    public BigDecimal getTaxCostLocalEts()
    {
        return taxCostLocalEts;
    }

    public void setTaxCostLocalEts(BigDecimal taxCostLocalEts)
    {
        this.taxCostLocalEts = taxCostLocalEts;
    }

    public BigDecimal getCurrentWashSaleDeferralBase()
    {
        return currentWashSaleDeferralBase;
    }

    public void setCurrentWashSaleDeferralBase(BigDecimal currentWashSaleDeferralBase)
    {
        this.currentWashSaleDeferralBase = currentWashSaleDeferralBase;
    }

    public BigDecimal getCurrentWashSaleDeferralLocal()
    {
        return currentWashSaleDeferralLocal;
    }

    public void setCurrentWashSaleDeferralLocal(BigDecimal currentWashSaleDeferralLocal)
    {
        this.currentWashSaleDeferralLocal = currentWashSaleDeferralLocal;
    }

    public BigDecimal getImpairmentLtdLocal()
    {
        return impairmentLtdLocal;
    }

    public void setImpairmentLtdLocal(BigDecimal impairmentLtdLocal)
    {
        this.impairmentLtdLocal = impairmentLtdLocal;
    }

    public BigDecimal getImpairmentLtdBase()
    {
        return impairmentLtdBase;
    }

    public void setImpairmentLtdBase(BigDecimal impairmentLtdBase)
    {
        this.impairmentLtdBase = impairmentLtdBase;
    }

    public String getLongShortEts()
    {
        return longShortEts;
    }

    public void setLongShortEts(String longShortEts)
    {
        this.longShortEts = longShortEts;
    }

    public String getLongShortClient()
    {
        return longShortClient;
    }

    public void setLongShortClient(String longShortClient)
    {
        this.longShortClient = longShortClient;
    }

    public String getLocalAssetCurrency()
    {
        return localAssetCurrency;
    }

    public void setLocalAssetCurrency(String localAssetCurrency)
    {
        this.localAssetCurrency = localAssetCurrency;
    }

    public String getSecurityIdClient()
    {
        return securityIdClient;
    }

    public void setSecurityIdClient(String securityIdClient)
    {
        this.securityIdClient = securityIdClient;
    }

    public String getSecurityIdEts()
    {
        return securityIdEts;
    }

    public void setSecurityIdEts(String securityIdEts)
    {
        this.securityIdEts = securityIdEts;
    }

    public String getTransactionCode()
    {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode)
    {
        this.transactionCode = transactionCode;
    }

    public String getSecurityDescription()
    {
        return securityDescription;
    }

    public void setSecurityDescription(String securityDescription)
    {
        this.securityDescription = securityDescription;
    }

    public String getProcessingSecurityType()
    {
        return processingSecurityType;
    }

    public void setProcessingSecurityType(String processingSecurityType)
    {
        this.processingSecurityType = processingSecurityType;
    }

    public String getInvestmentType()
    {
        return investmentType;
    }

    public void setInvestmentType(String investmentType)
    {
        this.investmentType = investmentType;
    }

    public String getAppMnemonic()
    {
        return appMnemonic;
    }

    public void setAppMnemonic(String appMnemonic)
    {
        this.appMnemonic = appMnemonic;
    }

    public String getSecurityType()
    {
        return securityType;
    }

    public void setSecurityType(String securityType)
    {
        this.securityType = securityType;
    }

    public Boolean getClientHoldingMissing()
    {
        return clientHoldingMissing;
    }

    public void setClientHoldingMissing(Boolean clientHoldingMissing)
    {
        this.clientHoldingMissing = clientHoldingMissing;
    }

    public Boolean getTaxHoldingMissing()
    {
        return taxHoldingMissing;
    }

    public void setTaxHoldingMissing(Boolean taxHoldingMissing)
    {
        this.taxHoldingMissing = taxHoldingMissing;
    }

    public Boolean getExcluded()
    {
        return isExcluded;
    }

    public void setExcluded(Boolean excluded)
    {
        isExcluded = excluded;
    }

    public Boolean getInflation()
    {
        return isInflation;
    }

    public void setInflation(Boolean inflation)
    {
        isInflation = inflation;
    }

    public Long getHoldingClientId()
    {
        return holdingClientId;
    }

    public void setHoldingClientId(Long holdingClientId)
    {
        this.holdingClientId = holdingClientId;
    }

    public Long getHoldingEtsId()
    {
        return holdingEtsId;
    }

    public void setHoldingEtsId(Long holdingEtsId)
    {
        this.holdingEtsId = holdingEtsId;
    }

    @Override
    public String   toString()
    {
        return "HoldingsReconExceptionEntity{" +
                "id=" + id +
                ", entityId='" + entityId + '\'' +
                ", securityAlias=" + securityAlias +
                ", eventId='" + eventId + '\'' +
                ", tradeDateEts=" + tradeDateEts +
                ", tradeDateClient=" + tradeDateClient +
                ", quantityEts=" + quantityEts +
                ", quantityClient=" + quantityClient +
                ", quantityDifference=" + quantityDifference +
                ", originalFaceEts=" + originalFaceEts +
                ", originalFaceClient=" + originalFaceClient +
                ", originalFaceDifference=" + originalFaceDifference +
                ", bookCostBaseEts=" + bookCostBaseEts +
                ", bookCostBaseClient=" + bookCostBaseClient +
                ", bookCostBaseDifference=" + bookCostBaseDifference +
                ", bookCostLocalEts=" + bookCostLocalEts +
                ", bookCostLocalClient=" + bookCostLocalClient +
                ", bookCostLocalDifference=" + bookCostLocalDifference +
                ", amortizedCostBase=" + amortizedCostBase +
                ", amortizedCostLocal=" + amortizedCostLocal +
                ", taxCostBaseEts=" + taxCostBaseEts +
                ", taxCostLocalEts=" + taxCostLocalEts +
                ", currentWashSaleDeferralBase=" + currentWashSaleDeferralBase +
                ", currentWashSaleDeferralLocal=" + currentWashSaleDeferralLocal +
                ", impairmentLtdLocal=" + impairmentLtdLocal +
                ", impairmentLtdBase=" + impairmentLtdBase +
                ", longShortEts='" + longShortEts + '\'' +
                ", longShortClient='" + longShortClient + '\'' +
                ", localAssetCurrency='" + localAssetCurrency + '\'' +
                ", securityIdClient='" + securityIdClient + '\'' +
                ", securityIdEts='" + securityIdEts + '\'' +
                ", transactionCode='" + transactionCode + '\'' +
                ", securityDescription='" + securityDescription + '\'' +
                ", processingSecurityType='" + processingSecurityType + '\'' +
                ", investmentType='" + investmentType + '\'' +
                ", appMnemonic='" + appMnemonic + '\'' +
                ", securityType='" + securityType + '\'' +
                ", clientHoldingMissing=" + clientHoldingMissing +
                ", taxHoldingMissing=" + taxHoldingMissing +
                ", isExcluded=" + isExcluded +
                ", isInflation=" + isInflation +
                ", holdingClientId=" + holdingClientId +
                ", holdingEtsId=" + holdingEtsId +
                '}';
    }
}
