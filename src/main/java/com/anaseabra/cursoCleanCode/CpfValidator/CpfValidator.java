package com.anaseabra.cursoCleanCode.CpfValidator;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CpfValidator {

    private final int FACTOR_10 = 10;
    private final int FACTOR_11 = 11;

    //step-down rule -> leitura de cima para baixo, de acordo com a ordem que os metodos forem aparecendo (na medida do possível)

    public boolean validateCpf(String rawCpf) {
        if (rawCpf == null) {
         return false;
        }

        String cpf = formatCPFString(rawCpf);

        if (!isStringCPFValid(cpf) || hasEqualsCharacters(cpf)) {
            return false;
        }

        int tenDigit = calculateDigit(cpf, FACTOR_10);
        int elevenDigit = calculateDigit(cpf, FACTOR_11);

        String finalDigits = String.format("%d%d", tenDigit, elevenDigit);
        return finalDigits.equals(cpf.substring(9, 11));
    }

    private String formatCPFString(String cpf) {
        return cpf.replaceAll("\\D", "");
    }

    private boolean isStringCPFValid(String cpf) {
        if (cpf == null ) {
            return false;
        }
        return cpf.length() == 11;
    }

    private boolean hasEqualsCharacters(String cpf) {
        List<String> charArray = Arrays.stream(cpf.split("")).collect(Collectors.toList());
        return charArray.stream().allMatch(chr -> chr.equals(charArray.get(0)));
    }

    private Integer calculateDigit(String cpf, int factor) {
        int total = 0;
        for (char digit: cpf.toCharArray()){
            if (factor > 1) {
                total += Integer.parseInt(String.valueOf(digit)) * factor--;
            }
        }
        int rest = total % 11;
        return (rest < 2) ? 0 : 11 - rest;
    }
}