package com.bank.credit.model.enums;

public enum ProductType {
    FINANCING,
    PERSONAL,
    CONSIGNED;

    public Integer getTaxes() {
        switch (this) {
            case FINANCING: return 7;
            case PERSONAL: return 4;
            case CONSIGNED: return 2;

            default: return null;
        }
    }
}
