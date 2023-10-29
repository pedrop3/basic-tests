package com.learn.basicTests.util;

public class CpfValidator {

    public static boolean isValidCpf(String cpf) {
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Calcula os dígitos verificadores
        int[] digits = new int[11];
        for (int i = 0; i < 11; i++) {
            digits[i] = Integer.parseInt(cpf.substring(i, i + 1));
        }

        int sum1 = 0;
        int sum2 = 0;

        for (int i = 0; i < 9; i++) {
            sum1 += digits[i] * (10 - i);
            sum2 += digits[i] * (11 - i);
        }

        int remainder1 = sum1 % 11;
        int remainder2 = sum2 % 11;

        if (remainder1 < 2) {
            if (digits[9] != 0) {
                return false;
            }
        } else {
            if (digits[9] != 11 - remainder1) {
                return false;
            }
        }

        if (remainder2 < 2) {
            if (digits[10] != 0) {
                return false;
            }
        } else {
            if (digits[10] != 11 - remainder2) {
                return false;
            }
        }

        return true;
    }
}