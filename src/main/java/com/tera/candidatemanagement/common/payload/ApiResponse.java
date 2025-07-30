package com.tera.candidatemanagement.common.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private T data;
    private String message;

    // Untuk response sukses
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(data, message);
    }

    // Untuk response error
    public static <T> ApiResponse<T> error(T errors, String message) {
        return new ApiResponse<>(errors, message);
    }
}
