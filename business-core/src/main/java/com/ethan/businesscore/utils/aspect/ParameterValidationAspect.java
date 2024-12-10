package com.ethan.businesscore.utils.aspect;

import com.ethan.businesscore.utils.annotates.ValidateParameters;
import com.ethan.businesscore.utils.exceptions.ParametersValidationException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ParameterValidationAspect {

    @Before("@annotation(validateParameters)")
    public void validateMethodParameters(JoinPoint joinPoint , ValidateParameters validateParameters) {
        for (Object arg : joinPoint.getArgs()) {
            if (arg == null) {
                throw new ParametersValidationException(validateParameters.message() + ": Parameter cannot be null");
            }
            if (arg instanceof String && ((String) arg).isEmpty()) {
                throw new ParametersValidationException(validateParameters.message() + ": String parameter cannot be empty");
            }
        }
    }

}
