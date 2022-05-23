package com.woyo.cms.service;

import com.woyo.cms.DTO.TransactionDTO;
import com.woyo.cms.model.request.TransactionRequestModel;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public interface TransactionService {
    TransactionDTO createTransaction(TransactionRequestModel transactionRequestModel);
    TransactionDTO getTransactionById(int transactionId);
    String exportTransactionToCSV(PrintWriter writer);
}
