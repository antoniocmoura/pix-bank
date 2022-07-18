package com.antoniocmoura.pixbank.infrastructure.api;

import com.antoniocmoura.pixbank.domain.pagination.Pagination;
import com.antoniocmoura.pixbank.infrastructure.pixkey.models.CreatePixKeyRequest;
import com.antoniocmoura.pixbank.infrastructure.pixkey.models.PixKeyListResponse;
import com.antoniocmoura.pixbank.infrastructure.pixkey.models.PixKeyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "pix-key")
@Tag(name = "Pix Key")
public interface PixKeyAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create a new Pix key")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> createPixKey(@RequestBody CreatePixKeyRequest input);

    @PutMapping(
            value = "deactivate/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Deactivate Pix Key by it´s key value")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pix Key successfully inactivated"),
            @ApiResponse(responseCode = "404", description = "Pix Key was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown")
    })
    ResponseEntity<?> deactivateById(@PathVariable(name = "id") String keyValue);

    @PutMapping(
            value = "activate/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Activate Pix Key by it´s key value")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pix Key successfully activated"),
            @ApiResponse(responseCode = "404", description = "Pix Key was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown")
    })
    ResponseEntity<?> activateById(@PathVariable(name = "id") String keyValue);

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listed successfully"),
            @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown")
    })
    @Operation(summary = "List all pix keys")
    Pagination<PixKeyListResponse> listPixKeys(
            @RequestParam(name = "search", required = false, defaultValue = "") final String search,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "id") final String sort,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") final String direction
    );

    @GetMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Get a pix key by it´s identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pix Key retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Pix Key was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown")
    })
    PixKeyResponse getById(@PathVariable(name = "id") String id);

}
