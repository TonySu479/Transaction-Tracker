package com.example.transactiontracker.models.transaction;

public enum TransactionType {
    SALE("sale"),
    RECEIVE("receive");

    final String name;

    TransactionType(String name) {
        this.name = name;
    }
}
