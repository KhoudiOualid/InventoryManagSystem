package com.khoudidev.inventorymanagementsystem.service;

import com.khoudidev.inventorymanagementsystem.dto.CategoryDTO;
import com.khoudidev.inventorymanagementsystem.dto.Response;
import com.khoudidev.inventorymanagementsystem.dto.TransactionRequest;
import com.khoudidev.inventorymanagementsystem.enums.TransactionStatus;

public interface TransactionService {
    Response restockInventory(TransactionRequest transactionRequest);

    Response sell(TransactionRequest transactionRequest);

    Response returnToSupplier(TransactionRequest transactionRequest );

    Response getAllTransactions(int page, int size, String searchText);

    Response getTransactionById(Long id );

    Response getAllTransactionByMonthAndYear(int month, int year);

    Response updateTransactionStatus(Long transactionId, TransactionStatus transactionStatus);
}
