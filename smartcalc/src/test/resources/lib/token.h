#ifndef CPP3_S21_SMART_CALC_2_0_MODEL_TOKEN_H_
#define CPP3_S21_SMART_CALC_2_0_MODEL_TOKEN_H_

#include <list>
#include <map>

#include "model.h"
// #include <string>
// #include <cmath>
#include <functional>

namespace s21 {

enum data_type {
  OPENING_BRACKETS = 0,
  CLOSING_BRACKETS = 1,

  NUMBER = 2,
  X = 3,

  PLUS = 4,
  MINUS = 5,

  MULTIPLY = 6,
  DIVIDE = 7,
  MOD = 8,
  POWER = 9,

  COS = 10,
  SIN = 11,
  TAN = 12,
  ACOS = 13,
  ASIN = 14,
  ATAN = 15,

  SQRT = 16,
  LN = 17,
  LOG = 18,

  U_PLUS = 19,
  U_MINUS = 20,

  PI = 21
};

class Model;

class Token {
 public:
  using size_type = size_t;

  using func = double (*)(const std::list<double>);
  // or
  //  typedef double (*func) (const std::list<double>);

  typedef struct operation_priority {
    std::string operation_name;
    int priority;
    size_type accepted_numb_args;
  } priority_t;

  static const size_type kPriorityArrayLenth{22};

  static inline priority_t priority_array[kPriorityArrayLenth]{
      {"(", -1, 0},   {")", -1, 0},   {"number", 0, 0}, {"x", 0, 0},
      {"+", 1, 2},    {"-", 1, 2},    {"*", 2, 2},      {"/", 2, 2},
      {"mod", 3, 2},  {"^", 3, 2},    {"cos", 5, 1},    {"sin", 5, 1},
      {"tan", 5, 1},  {"acos", 5, 1}, {"asin", 5, 1},   {"atan", 5, 1},
      {"sqrt", 5, 1}, {"ln", 5, 1},   {"log", 5, 1},    {"$", 4, 1},
      {"~", 4, 1},    {"π", 0, 0}};

  static double Sin(const std::list<double> args_list) {
    return sin(args_list.front());
  }
  static double Cos(const std::list<double> args_list) {
    return cos(args_list.front());
  }
  static double Tan(const std::list<double> args_list) {
    return tan(args_list.front());
  }
  static double Asin(const std::list<double> args_list) {
    return asin(args_list.front());
  }
  static double Acos(const std::list<double> args_list) {
    return acos(args_list.front());
  }
  static double Atan(const std::list<double> args_list) {
    return atan(args_list.front());
  }
  static double Sqrt(const std::list<double> args_list) {
    return sqrt(args_list.front());
  }
  static double Ln(const std::list<double> args_list) {
    return log(args_list.front());
  }
  static double Log(const std::list<double> args_list) {
    return log10(args_list.front());
  }
  static double UnPlus(const std::list<double> args_list) {
    return args_list.front();
  }
  static double UnMinus(const std::list<double> args_list) {
    return -args_list.front();
  }

  static double Plus(const std::list<double> args_list) {
    return args_list.back() + args_list.front();
  }
  static double Minus(const std::list<double> args_list) {
    return args_list.back() - args_list.front();
  }
  static double Mult(const std::list<double> args_list) {
    return args_list.back() * args_list.front();
  }
  static double Div(const std::list<double> args_list) {
    if (args_list.front() == 0) {
      throw std::invalid_argument("division by zero");
    } else {
      return args_list.back() / args_list.front();
    }
  }
  static double Mod(const std::list<double> args_list) {
    return fmod(args_list.back(), args_list.front());
  }
  static double Pow(const std::list<double> args_list) {
    return pow(args_list.back(), args_list.front());
  }

  static inline std::map<std::string, typename Token::func> fun_map{
      {"sin", Token::Sin},   {"cos", Token::Cos},   {"tan", Token::Tan},
      {"asin", Token::Asin}, {"acos", Token::Acos}, {"atan", Token::Atan},
      {"sqrt", Token::Sqrt}, {"ln", Token::Ln},     {"log", Token::Log},
      {"$", Token::UnPlus},  {"~", Token::UnMinus}, {"+", Token::Plus},
      {"-", Token::Minus},   {"*", Token::Mult},    {"mod", Token::Mod},
      {"/", Token::Div},     {"^", Token::Pow}};

  Token() = default;

  Token(double value) : value_{value} {}  ////
  Token(double value, int priority, int type)
      : value_{value}, priority_{priority}, type_{type} {}
  ~Token() = default;

  double value_;
  int priority_ = -9;
  int type_ = -9;

  friend Model;
};

}  // namespace s21

#endif  // CPP3_S21_SMART_CALC_2_0_MODEL_TOKEN_H_
