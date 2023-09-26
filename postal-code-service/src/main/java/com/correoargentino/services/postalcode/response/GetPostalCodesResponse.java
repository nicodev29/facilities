package com.correoargentino.services.postalcode.response;

import com.correoargentino.services.postalcode.entity.PostalCode;
import java.util.List;

/**
 * GetPostalCodesResponse.
 */
public record GetPostalCodesResponse(List<PostalCode> postalCodes) {
}
