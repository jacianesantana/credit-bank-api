package br.com.sicredi.bank.entity.enums;

public enum ProductType {

    FINANCIAMENTO,
    PESSOAL,
    CONSIGNADO;

    public Integer getTaxes() {
        switch (this) {
            case FINANCIAMENTO: return 7;
            case PESSOAL: return 4;
            case CONSIGNADO: return 2;

            default: return null;
        }
    }

}
