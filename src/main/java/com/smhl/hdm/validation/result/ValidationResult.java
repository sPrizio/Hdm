package com.smhl.hdm.validation.result;

import com.smhl.hdm.enums.ValidationResponseResult;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

/**
 * Class implementation of a result of validation
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@NoArgsConstructor
@RequiredArgsConstructor
public class ValidationResult {

    @Getter
    @Setter
    @NonNull
    private ValidationResponseResult result;

    @Getter
    @Setter
    @NonNull
    private String message;


    //  METHODS

    public boolean isValid() {
        return (this.result != null && StringUtils.isNotEmpty(message)) && this.result.equals(ValidationResponseResult.SUCCESSFUL);
    }
}
