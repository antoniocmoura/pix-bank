package com.antoniocmoura.pixbank.infrastructure.api;

import com.antoniocmoura.pixbank.domain.pagination.Pagination;
import com.antoniocmoura.pixbank.infrastructure.bankaccount.models.BankAccountListResponse;
import com.antoniocmoura.pixbank.infrastructure.bankaccount.models.BankAccountResponse;
import com.antoniocmoura.pixbank.infrastructure.bankaccount.models.CreateBankAccountRequest;
import com.antoniocmoura.pixbank.infrastructure.bankaccount.models.UpdateBankAccountRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "bank-account")
@Tag(name = "Bank Account")
public interface BankAccountAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create a new bank account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown")
    })
    ResponseEntity<?> createBankAccount(@RequestBody CreateBankAccountRequest input);

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listed successfully"),
            @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown")
    })
    @Operation(summary = "List all bank accounts")
    Pagination<BankAccountListResponse> listBankAccounts(
            @RequestParam(name = "search", required = false, defaultValue = "") final String search,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "firstName") final String sort,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") final String direction
    );

    @GetMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Get a bank account by it´s identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bank Account retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Bank Account was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown")
    })
    BankAccountResponse getById(@PathVariable(name = "id") String id);

    @PutMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Update a bank account by it´s identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bank Account updated successfully"),
            @ApiResponse(responseCode = "404", description = "Bank Account was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown")
    })
    ResponseEntity<?> updateById(@PathVariable(name = "id") String id, @RequestBody UpdateBankAccountRequest input);

    @DeleteMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Delete a bank account by it´s identifier")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Bank Account deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Bank Account was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown")
    })
    void deleteById(@PathVariable(name = "id") String id);

}
