package com.anaseabra.cpfvalidator;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CpfValidator {

    public boolean validateCpf(String cpf) throws Exception {

        if (isStringCPFValid(cpf) && !hasEqualsCharacters(cpf)){
            cpf = formatCPFString(cpf);

            int totald1 = 0;
            int totald2 = 0;

            for (int index = 1; index < cpf.length() -1; index++) {
                int digit = Integer.parseInt(cpf.substring(index-1, index));
                totald1 += digit * (11 - index);
                totald2 += digit * (12 - index);
            }

            String tenDigit = (totald1 % 11 > 2) ? String.valueOf((11 - totald1 % 11)) : "0";
            totald2 += 2 * Integer.parseInt(tenDigit);
            String elevenDigit = (totald2 % 11 > 2) ? String.valueOf((11 - totald2 % 11)) : "0";
            String finalDigits = tenDigit + elevenDigit;
            return finalDigits.equals(cpf.substring(9, 11));
        }
        return false;
    }

    private boolean hasEqualsCharacters(String cpf) {
        List<String> charArray = Arrays.stream(cpf.split("")).collect(Collectors.toList());
        return charArray.stream().allMatch(chr -> chr.equals(charArray.get(0)));
    }

    private boolean isStringCPFValid(String cpf) throws Exception {
        if (cpf == null) {
            throw new Exception("Cpf cannot be null");
        }
        String formatCpf = formatCPFString(cpf);
        if (formatCpf.length() != 11) {
            throw new Exception("Cpf must have 11 characters");
        }
        return true;
    }

    private String formatCPFString(String cpf) {
        return cpf.replace(".", "")
                .replace("-", "")
                .replace("_", "")
                .replace(" ", "");
    }
}