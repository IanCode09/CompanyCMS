package com.woyo.cms.controller;

import com.woyo.cms.DTO.TransactionDTO;
import com.woyo.cms.model.request.TransactionRequestModel;
import com.woyo.cms.response.DataResponse;
import com.woyo.cms.response.HandlerResponse;
import com.woyo.cms.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public void createTransaction(HttpServletResponse response, @RequestBody TransactionRequestModel transactionRequestModel) {
        TransactionDTO transactionDTO = transactionService.createTransaction(transactionRequestModel);

        DataResponse<TransactionDTO> data = new DataResponse<>();
        data.setData(transactionDTO);
        HandlerResponse.responseSuccessCreatedWithData(response, data);
    }

    @GetMapping("/{transactionId}")
    public void getTransactionById(HttpServletResponse response, @PathVariable int transactionId) {
        TransactionDTO transactionDTO = transactionService.getTransactionById(transactionId);

        if (transactionDTO != null) {
            DataResponse<TransactionDTO> data = new DataResponse<>();
            data.setData(transactionDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseNotFoundRequest(response, "404", "Transaction not found");
        }
    }

    @GetMapping("/export")
    public void exportTransactionToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.addHeader("Content-Disposition","attachment; filename=\"transaction.csv\"");
        transactionService.exportTransactionToCSV(response.getWriter());
    }

}
