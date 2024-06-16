package org.skyhigh.msskyhighrmm.model.SystemObjects.EndpointValidator;

public enum ValidationResult {
    HTTP_METHOD_UNMATCHED,
    ENDPOINT_UNMATCHED,
    FULL_UNMATCHED,
    SERVICE_NAME_PREFIX_UNMATCHED,
    SUCCESS,
    UNPROCESSABLE_ENTITY
}
