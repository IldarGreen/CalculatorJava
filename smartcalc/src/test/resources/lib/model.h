#ifndef CPP3_S21_SMART_CALC_2_0_MODEL_MODEL_H_
#define CPP3_S21_SMART_CALC_2_0_MODEL_MODEL_H_

#include <cmath>
#include <cstring>
#include <iostream>
#include <queue>
#include <stack>
#include <string>

#include "token.h"
#include "validator.h"

namespace s21 {

class Token;

class Model {
 public:
  using size_type = size_t;

  static constexpr double kPI{3.14159265358979323846};

  Model() = default;
  ~Model() = default;

  static void ToLowercase(std::string& input);
  static void InputStringValidation(std::string input);
  static void ParseInputToElement(std::string& input,
                                  std::queue<Token>& stack_in);
  static void CheckAndGetNumber(std::string& input, size_type* index,
                                std::queue<Token>& stack_in);
  static void CheckAndGetOperator(std::string& input, size_type* index,
                                  std::queue<Token>& stack_in);
  static void TokenQueueValidation(std::queue<s21::Token> queue_in);
  static void InfixToPostfix(std::queue<Token>& queue_in,
                             std::stack<Token>& operators,
                             std::stack<Token>& output);
  static void StackInversion(std::stack<Token>& output);
  static double Calculate(std::stack<Token>& output, double x);
  static std::string MainFunRunner(std::string input, std::string xStr);

  static void ModelGetCE(std::string& input);
  static void ModelGetAC(std::string& input);
  static void ModelGetCleanX(std::string& input);
  static void ModelGetPow(std::string& input);
  static void ModelGetMul(std::string& input);
  static void ModelGetDigit(std::string& input, std::string button_text);
  static void ModelGetDigit2(std::string& input, std::string button_text);
  static void ModelGetSub(std::string& input);
  static void ModelGetChangeSignX(std::string& input);
};

}  // namespace s21

#endif  // CPP3_S21_SMART_CALC_2_0_MODEL_MODEL_H_
