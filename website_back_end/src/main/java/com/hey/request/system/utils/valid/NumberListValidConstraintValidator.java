package com.hey.request.system.utils.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

public class NumberListValidConstraintValidator implements ConstraintValidator<NumberListValid,Integer> {

  private final Set<Integer> set = new HashSet<>();

  @Override
  public void initialize(NumberListValid constraintAnnotation) {
    int[] values = constraintAnnotation.values();
    for (int value : values) {
      set.add(value);
    }
  }

  @Override
  public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
    return set.contains(integer);
  }
}