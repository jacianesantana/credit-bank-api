package br.com.sicredi.bank.model.enums;

public enum ProductType {

    FINANCING,
    PERSONAL,
    PAYROLL;

    public Integer getTaxes() {
        switch (this) {
            case FINANCING: return 7;
            case PERSONAL: return 4;
            case PAYROLL: return 2;

            default: return null;
        }
    }

}
