package com.bank.credit.model.enums;

public enum ProductType {
    FINANCIAMENTO,
    EMPRESTIMO_PESSOAL,
    CONSIGNADO;

    public Integer getTaxes() {
        switch (this) {
            case FINANCIAMENTO: return 7;
            case EMPRESTIMO_PESSOAL: return 4;
            case CONSIGNADO: return 2;

            default: return null;
        }
    }
}
