package src.main.java.com.bank.ledger.controller;

import com.core.common.dto.ApiResponseFormat;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.main.java.com.bank.ledger.dto.CreateLedgerRequest;
import src.main.java.com.bank.ledger.dto.LedgerCreatedResponse;
import src.main.java.com.bank.ledger.service.LedgerService;

@RestController

@RequestMapping("/api/ledger")
@RequiredArgsConstructor
public class LedgerController {

    private final LedgerService ledgerService;

    @PostMapping
    public ResponseEntity<ApiResponseFormat<LedgerCreatedResponse>> create(
            @Valid
            @RequestBody CreateLedgerRequest request
    ) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponseFormat.success(
                                "Ledger entries created successfully",
                                ledgerService.createEntries(request)
                        )
                );
    }
}