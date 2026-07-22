package util;

import exception.ValidationException;

public class ValidationUtil {

    public static void validateProduct(
            String nama,
            String harga,
            String stok)
            throws ValidationException {

        if (nama.isEmpty()) {

            throw new ValidationException(
                    "Nama produk tidak boleh kosong."
            );

        }

        if (harga.isEmpty()) {

            throw new ValidationException(
                    "Harga tidak boleh kosong."
            );

        }

        if (stok.isEmpty()) {

            throw new ValidationException(
                    "Stok tidak boleh kosong."
            );

        }

        try {

            double h = Double.parseDouble(harga);

            if (h <= 0) {

                throw new ValidationException(
                        "Harga harus lebih dari 0."
                );

            }

        } catch (NumberFormatException e) {

            throw new ValidationException(
                    "Harga harus berupa angka."
            );

        }

        try {

            int s = Integer.parseInt(stok);

            if (s < 0) {

                throw new ValidationException(
                        "Stok tidak boleh negatif."
                );

            }

        } catch (NumberFormatException e) {

            throw new ValidationException(
                    "Stok harus berupa angka."
            );

        }

    }

}