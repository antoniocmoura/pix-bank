package com.antoniocmoura.pixbank.infrastructure.api;

import com.antoniocmoura.pixbank.domain.pagination.Pagination;
import com.antoniocmoura.pixbank.infrastructure.transaction.models.CreateDepositTransactionRequest;
import com.antoniocmoura.pixbank.infrastructure.transaction.models.CreateTransferTransactionRequest;
import com.antoniocmoura.pixbank.infrastructure.transaction.models.TransactionListResponse;
import com.antoniocmoura.pixbank.infrastructure.transaction.models.TransactionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "transaction")
@Tag(name = "Transaction")
public interface TransactionAPI {

    @PostMapping(
            value = "transference",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create a new transference transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> createTransferTransaction(@RequestBody CreateTransferTransactionRequest input);

    @PostMapping(
            value = "deposit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create a new transference transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> createDepositTransaction(@RequestBody CreateDepositTransactionRequest input);

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listed successfully"),
            @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown")
    })
    @Operation(summary = "List all transactions")
    Pagination<TransactionListResponse> listTransactions(
            @RequestParam(name = "search", required = false, defaultValue = "") final String search,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "createdAt") final String sort,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") final String direction
    );

    @GetMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Get a transaction by it´s identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Transaction was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown")
    })
    TransactionResponse getById(@PathVariable(name = "id") String id);

    @GetMapping(
            value = "bank-account/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "List transactions by Bank Account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listed successfully"),
            @ApiResponse(responseCode = "404", description = "Transaction was not found"),
            @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown")
    })
    Pagination<TransactionListResponse> getByBankAccountId(
            @PathVariable(name = "id") final String id,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "createdAt") final String sort,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") final String direction
    );

}
