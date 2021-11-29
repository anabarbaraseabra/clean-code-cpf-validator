package com.anaseabra.cpfvalidator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CpfValidatorTests {

    @Autowired
    private CpfValidator cpfValidator;

    @Test
    void testCpfValidator_validCpf () throws Exception {
        String cpf = "120.713.896-70";
        boolean validate = cpfValidator.validateCpf(cpf);
        Assertions.assertTrue(validate);
    }

    @Test
    void testCpfValidator_validCpfAgain () throws Exception {
        String cpf = "094-525-106-84";
        boolean validate = cpfValidator.validateCpf(cpf);
        Assertions.assertTrue(validate);
    }

    @Test
    void testCpfValidator_invalidCpf() throws Exception {
        String cpf = "11111111111";

        boolean validate = cpfValidator.validateCpf(cpf);

        Assertions.assertFalse(validate);
    }
}
