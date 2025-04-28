package com.kredicart.order.Exceptions;

import feign.FeignException;

import java.util.UUID;
import java.util.function.Supplier;

public class FeignClientExceptionHandler {
    public static <T> T execute(Supplier<T> supplier, String resourceName, UUID resourceId) {
        try {
            return supplier.get();
        } catch (FeignException.NotFound ex) {
            throw new ResourceNotFoundException(String.format("%s not found with id: %s", resourceName, resourceId));
        } catch (FeignException ex) {
            throw new InternalServiceException(String.format("Failed to call %s service", resourceName), ex);
        }
    }
}
