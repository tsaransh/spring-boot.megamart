package com.web.megamart.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
