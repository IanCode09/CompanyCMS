package com.woyo.cms.service.impl;

import com.woyo.cms.DTO.CompanyDTO;
import com.woyo.cms.DTO.ProductDTO;
import com.woyo.cms.DTO.TransactionDTO;
import com.woyo.cms.model.CompanyModel;
import com.woyo.cms.model.ProductModel;
import com.woyo.cms.model.TransactionModel;
import com.woyo.cms.model.request.TransactionRequestModel;
import com.woyo.cms.repository.CompanyRepository;
import com.woyo.cms.repository.ProductRepository;
import com.woyo.cms.repository.TransactionRepository;
import com.woyo.cms.service.TransactionService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public TransactionDTO createTransaction(TransactionRequestModel transactionRequestModel) {
        Optional<ProductModel> product = productRepository.findById(transactionRequestModel.getProductId());
        Optional<CompanyModel> company = companyRepository.findById(transactionRequestModel.getCompanyId());

        int sisaStock = product.get().getStock() - transactionRequestModel.getQty();

        TransactionModel transaction = new TransactionModel();
        transaction.setCompany(company.get());
        transaction.setProduct(product.get());
        transaction.setQty(transactionRequestModel.getQty());
        transaction.setPrice(transactionRequestModel.getPrice());
        transaction.setTotalPrice(transactionRequestModel.getTotalPrice());
        transaction.setStock(sisaStock);
        transaction.setDate(LocalDate.now());
        transaction.setCreatedAt(LocalDateTime.now());

        product.get().setStock(sisaStock);
        productRepository.save(product.get());

        return convertTransactionDTO(transactionRepository.save(transaction));
    }

    @Override
    public TransactionDTO getTransactionById(int transactionId) {
        Optional<TransactionModel> transaction = transactionRepository.findById(transactionId);

        if (transaction.isPresent()) {
            return convertTransactionDTO(transaction.get());
        } else {
            return null;
        }
    }

    @Override
    public String exportTransactionToCSV(PrintWriter writer) {
        List<TransactionModel> transactions = transactionRepository.findAll();

        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            for (TransactionModel transaction : transactions) {
                csvPrinter.printRecord(
                        transaction.getDate(),
                        transaction.getCompany().getCompanyName(),
                        transaction.getProduct().getProductName(),
                        transaction.getQty(),
                        transaction.getPrice(),
                        transaction.getTotalPrice(),
                        transaction.getStock());
            }

            return "SUCCESS";
        } catch (IOException e) {
            System.out.println(e.toString());
            return "FAILED";
        }
    }

    public TransactionDTO convertTransactionDTO(TransactionModel item) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionId(item.getTransactionId());
        transactionDTO.setCompany(convertCompanyDTO(companyRepository.findById(item.getCompany().getCompanyId()).get()));
        transactionDTO.setProduct(convertProductDTO(productRepository.findById(item.getProduct().getProductId()).get()));
        transactionDTO.setQty(item.getQty());
        transactionDTO.setPrice(item.getPrice());
        transactionDTO.setTotalPrice(item.getTotalPrice());
        transactionDTO.setStock(item.getStock());
        transactionDTO.setDate(item.getDate());
        transactionDTO.setCreatedAt(item.getCreatedAt());

        return transactionDTO;
    }

    public ProductDTO convertProductDTO(ProductModel item) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(item.getProductId());
        productDTO.setProductName(item.getProductName());
        productDTO.setPrice(item.getPrice());
        productDTO.setStock(item.getStock());
        productDTO.setCreatedAt(item.getCreatedAt());

        return productDTO;
    }

    public CompanyDTO convertCompanyDTO(CompanyModel item) {
        return new CompanyDTO(item.getCompanyId(), item.getCompanyCode(), item.getCompanyName(),
                item.getCompanyAddress(), item.getCompanyPhone(), item.getCompanyPostalCode());
    }
}
