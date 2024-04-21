#ifndef CPP3_S21_SMART_CALC_2_0_MODEL_VALIDATOR_H_
#define CPP3_S21_SMART_CALC_2_0_MODEL_VALIDATOR_H_

#include <math.h>
#include <stdio.h>

#include <iostream>
#include <string>

namespace s21 {

class Model;

class Validator {
 public:
  using size_type = size_t;

  Validator() = default;
  ~Validator() = default;

  /// @brief Checking for a valid input int or double, regular and exponential
  /// notation.
  /// @return 0 - invalid, 1 - valid int, 2 - valid double.
  static int FieldValidatorIntDouble(std::string input) {
    short dot_cnt = 0;
    short e_cnt = 0;
    int result = 1;
    size_type len = input.size();
    for (size_type i = 0; i < len; ++i) {
      if (std::isdigit(input[i])) {
        continue;
      } else if (input[i] == '.') {
        if (++dot_cnt > 1) {
          result = 0;
          break;
        }
      } else if (input[i] == 'e' || input[i] == 'E') {
        if (++e_cnt > 1 || i == 0) {
          result = 0;
          break;
        }
      } else if (i == 0 && (input[i] == '-')) {
        continue;
      } else if (input[i] == '+' || (input[i] == '-')) {
        if (i > 0 && ((input[i - 1] == 'e') || (input[i - 1] == 'E'))) {
          continue;
        } else {
          result = 0;
          break;
        }
      } else {
        result = 0;
        break;
      }
    }

    if (dot_cnt > 0 && result == 1) result = 2;

    return result;
  }

  static int FieldValidatorIntDoublePositive(std::string input) {
    int result = FieldValidatorIntDouble(input);
    if ((result == 1 || result == 2) && input[0] == '-') result = 0;
    return result;
  }

  friend Model;
};

}  // namespace s21

#endif  // CPP3_S21_SMART_CALC_2_0_MODEL_VALIDATOR_H_
