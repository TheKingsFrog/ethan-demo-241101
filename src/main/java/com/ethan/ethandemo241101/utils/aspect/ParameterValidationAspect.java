package com.ethan.ethandemo241101.utils.aspect;

import com.ethan.ethandemo241101.utils.annotates.ValidateParameters;
import com.ethan.ethandemo241101.utils.exceptions.ParametersValidationException;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareAnnotation;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;

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
