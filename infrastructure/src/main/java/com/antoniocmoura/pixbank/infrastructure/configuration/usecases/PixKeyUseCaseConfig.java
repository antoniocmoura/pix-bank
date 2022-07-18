package com.antoniocmoura.pixbank.infrastructure.configuration.usecases;

import com.antoniocmoura.pixbank.application.pixkey.create.CreatePixKeyUseCase;
import com.antoniocmoura.pixbank.application.pixkey.create.DefaultCreatePixKeyUseCase;
import com.antoniocmoura.pixbank.application.pixkey.retrieve.get.DefaultGetPixKeyByIdUseCase;
import com.antoniocmoura.pixbank.application.pixkey.retrieve.get.GetPixKeyByIdUseCase;
import com.antoniocmoura.pixbank.application.pixkey.retrieve.list.DefaultPixKeyListUseCase;
import com.antoniocmoura.pixbank.application.pixkey.retrieve.list.PixKeyListUseCase;
import com.antoniocmoura.pixbank.application.pixkey.update.DefaultUpdatePixKeyUseCase;
import com.antoniocmoura.pixbank.application.pixkey.update.UpdatePixKeyUseCase;
import com.antoniocmoura.pixbank.domain.bankaccount.BankAccountGateway;
import com.antoniocmoura.pixbank.domain.pixkey.PixKeyGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PixKeyUseCaseConfig {
    
    private final PixKeyGateway pixKeyGateway;
    private final BankAccountGateway bankAccountGateway;

    public PixKeyUseCaseConfig(
            final PixKeyGateway pixKeyGateway,
            final BankAccountGateway bankAccountGateway
    ) {
        this.pixKeyGateway = pixKeyGateway;
        this.bankAccountGateway = bankAccountGateway;
    }

    @Bean
    public CreatePixKeyUseCase createPixKeyUseCase() {
        return new DefaultCreatePixKeyUseCase(pixKeyGateway, bankAccountGateway);
    }

    @Bean
    public GetPixKeyByIdUseCase getPixKeyByIdUseCase() {
        return new DefaultGetPixKeyByIdUseCase(pixKeyGateway);
    }

    @Bean
    public PixKeyListUseCase pixKeyListUseCase()  {
        return new DefaultPixKeyListUseCase(pixKeyGateway);
    }

    @Bean
    public UpdatePixKeyUseCase updatePixKeyUseCase() {
        return new DefaultUpdatePixKeyUseCase(pixKeyGateway);
    }
}
