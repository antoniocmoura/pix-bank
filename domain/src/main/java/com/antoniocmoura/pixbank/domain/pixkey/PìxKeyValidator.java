package com.antoniocmoura.pixbank.domain.pixkey;

import br.com.caelum.stella.validation.CPFValidator;
import com.antoniocmoura.pixbank.domain.validation.Error;
import com.antoniocmoura.pixbank.domain.validation.ValidationHandler;
import com.antoniocmoura.pixbank.domain.validation.Validator;
import org.apache.commons.validator.routines.EmailValidator;

public class PìxKeyValidator extends Validator {

    private final PixKey pixKey;


    protected PìxKeyValidator(final PixKey pixKey, final ValidationHandler aHandler) {
        super(aHandler);
        this.pixKey = pixKey;
    }

    @Override
    public void validate() {

        final var aPixKeyID = this.pixKey.getId().getValue();
        if (aPixKeyID == null) {
            this.validationHandler().append(new Error("'pix_key' should not be null"));
            return;
        }

        if (aPixKeyID.isBlank()) {
            this.validationHandler().append(new Error("'pix_key' should not be empty"));
            return;
        }

        final var keyKind = this.pixKey.getKeyKind();
        if (keyKind == KeyKind.E_MAIL && !EmailValidator.getInstance().isValid(aPixKeyID)) {
            this.validationHandler().append(new Error("'pix_key' is an invalid e-mail"));
            return;
        }
        if (keyKind == KeyKind.CPF && !validateCPF(aPixKeyID)) {
            this.validationHandler().append(new Error("'pix_key' is an invalid CPF"));
            return;
        }

    }

    public static boolean validateCPF(final String aCPF) {
        CPFValidator cpfValidator = new CPFValidator();
        try {
            cpfValidator.assertValid(aCPF);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
