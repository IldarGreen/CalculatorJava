#include "model.h"

#include <queue>
#include <vector>

namespace s21 {

void Model::ToLowercase(std::string& input) {
  int len = input.size();
  for (int i = 0; i < len; ++i) input[i] = tolower(input[i]);
}

/// @brief Checks if the brackets are entered correctly before parsing into the
/// token.
void Model::InputStringValidation(std::string input) {
  int brackets_balance = 0;

  size_type len = input.size();
  for (size_type i = 0; i < len; ++i) {
    if (input[i] == '(') brackets_balance++;
    if (input[i] == ')') {
      if (brackets_balance > 0)
        brackets_balance--;
      else
        throw std::logic_error("incorrect brackets sequence");
    }
  }
  if (brackets_balance > 0)
    throw std::logic_error("incorrect brackets sequence");
}

/// @brief Parses the input string into tokens.
void Model::ParseInputToElement(std::string& input,
                                std::queue<Token>& queue_in) {
  size_type len = input.size();

  for (size_type i = 0; i < len; ++i) {
    if (input[i] == ' ') {
      continue;
    } else {
      CheckAndGetNumber(input, &i, queue_in);
      CheckAndGetOperator(input, &i, queue_in);
    }
  }
}

/// @brief Сheck for a number or variable.
void Model::CheckAndGetNumber(std::string& input, size_type* index,
                              std::queue<Token>& queue_in) {
  int dot_cnt = 0;
  char number[255] = {0};
  int stop_flag = 1;
  int k = *index;
  for (int n = 0; input[k] && stop_flag; ++k, ++n) {
    if (input[k] == '.') ++dot_cnt;
    if (std::isdigit(input[k]) || (input[k] == '.'))
      number[n] = input[k];
    else if ((input[k] == 'e') &&
             ((input[k + 1] == '+') || (input[k + 1] == '-') ||
              std::isdigit(input[k + 1]))) {
      number[n] = input[k];
      number[++n] = input[++k];
    } else
      stop_flag = 0;
  }
  if (dot_cnt > 1)
    throw std::logic_error("too many dots");
  else {
    if (number[0]) {
      Token token(std::atof(number), 0, NUMBER);
      queue_in.push(token);
      number[0] = '\0';
    }
  }
  *index = --k;
}

/// @brief Сheck for an operator.
void Model::CheckAndGetOperator(std::string& input, size_type* index,
                                std::queue<Token>& queue_in) {
  int stop_flag = 1;
  int k = *index;
  int previous_type = 0;
  int flag = 1;
  for (size_type j = 0; j < Token::kPriorityArrayLenth && stop_flag; ++j) {
    if (input[k] == Token::priority_array[j].operation_name[0]) {
      flag = 1;
      size_type len = (Token::priority_array[j].operation_name).size();
      for (size_type l = 0; l < len && flag; ++l) {
        if (input[k + l] != Token::priority_array[j].operation_name[l])
          flag = 0;
      }
      if (flag) {
        stop_flag = 0;
        k += (Token::priority_array[j].operation_name).size() - 1;
        Token token(0, Token::priority_array[j].priority, j);
        ;
        // handle unary minus and unary plus ------
        if (!queue_in.empty()) {
          Token prev_token = queue_in.back();
          previous_type = prev_token.type_;
        }
        if ((token.type_ == PLUS || token.type_ == MINUS) &&
            previous_type == 0) {
          token.type_ = token.type_ + 15;
          token.priority_ = Token::priority_array[U_PLUS].priority;
        }
        //-----------------------------------
        if ((4 <= token.type_ && token.type_ <= 9) &&
            (4 <= previous_type && previous_type <= 9)) {
          throw std::logic_error("several operators stand in a row");
        }
        queue_in.push(token);
      } else
        k = *index;
    }
  }
  if (flag == 0) throw std::logic_error("operator not found");
  *index = k;
}

/// @brief Сheck if there are enough arguments for operators and functions.
void Model::TokenQueueValidation(std::queue<Token> queue_in) {
  std::vector<Token> vector;
  while (!queue_in.empty()) {
    vector.push_back(queue_in.front());
    queue_in.pop();
  }
  size_type len = vector.size();
  int cur_type = -9;
  for (size_type i = 0; i < len; ++i) {
    cur_type = vector[i].type_;
    if (4 <= cur_type && cur_type <= 9) {
      if (i + 1 < len && i > 0 &&
          (vector[i - 1].type_ != 2 || vector[i - 1].type_ != 3 ||
           vector[i - 1].type_ != 21) &&
          (vector[i + 1].type_ != 2 || vector[i + 1].type_ != 3 ||
           vector[i + 1].type_ != 21)) {
        continue;
      } else {
        throw std::logic_error("operator lacks enough arguments");
      }
    }

    if (10 <= cur_type && cur_type <= 15) {
      if (i + 1 < len) {
        if (vector[i + 1].type_ != 0) {
          throw std::logic_error("missing a bracket after the argument");
        }
      } else {
        throw std::logic_error("missing a bracket after the argument");
      }
    }

    if (16 <= cur_type && cur_type <= 20) {
      if (i + 1 < len) {
        if (vector[i + 1].type_ != 2 || vector[i + 1].type_ != 3 ||
            vector[i + 1].type_ != 21 || vector[i + 1].type_ != 0) {
          continue;
        }
      } else {
        throw std::logic_error("operator lacks enough arguments");
      }
    }
  }
}

/// @brief Converts an infix notation to a postfix notation.
void Model::InfixToPostfix(std::queue<Token>& queue_in,
                           std::stack<Token>& operators,
                           std::stack<Token>& output) {
  int brackets_counter = 0;
  Token poped;
  Token poped_oper;
  Token peeked_oper;
  while (!queue_in.empty()) {
    poped = queue_in.front();
    queue_in.pop();
    if (poped.type_ == 2 || poped.type_ == 3 ||
        poped.type_ == 21) {  // all operand
      output.push(poped);
    } else if (poped.type_ == 0) {  // if OPENING_BRACKETS ( in queue_in
      ++brackets_counter;
      operators.push(poped);
    } else if (poped.type_ == 1) {  // if CLOSING_BRACKETS ) in queue_in
      --brackets_counter;
      while (!operators.empty() &&
             operators.top().type_ !=
                 0) {  // while not OPENING_BRACKETS ( in operators
        output.push(operators.top());
        operators.pop();
      }
      operators.pop();
    } else {
      while (!operators.empty()) {  // if operator
        peeked_oper = operators.top();
        if (peeked_oper.priority_ >= poped.priority_) {
          if (poped.type_ == POWER && peeked_oper.type_ == POWER) break;
          output.push(peeked_oper);
          operators.pop();
        } else
          break;
      }
      operators.push(poped);
    }
  }
  while (!operators.empty()) {
    output.push(operators.top());
    operators.pop();
  }
}

void Model::StackInversion(std::stack<Token>& output) {
  std::stack<Token> temp;
  Token poped;
  while (!output.empty()) {
    poped = output.top();
    output.pop();
    temp.push(poped);
  }
  output = temp;
}

/// @brief Makes calculations using the resulting formula.
double Model::Calculate(std::stack<Token>& output, double x) {
  double result = 0;
  std::stack<Token> for_calc;
  std::list<double> args_list;
  Token::func task;
  Token::priority_t obj_prior;

  while (!output.empty()) {
    Token poped = output.top();
    output.pop();
    if (poped.type_ == 2) {  // numb
      for_calc.push(poped);
    } else if (poped.type_ == 3) {  // x
      poped.value_ = x;
      for_calc.push(poped);
    } else if (poped.type_ == 21) {  // PI
      poped.value_ = kPI;
      for_calc.push(poped);
    } else {
      obj_prior = Token::priority_array[poped.type_];

      for (size_type i = 0;
           (i < obj_prior.accepted_numb_args) && !for_calc.empty(); ++i) {
        args_list.push_back(for_calc.top().value_);
        for_calc.pop();
      }

      task = Token::fun_map[obj_prior.operation_name];
      if (obj_prior.accepted_numb_args <= args_list.size()) {
        poped.value_ = task(args_list);
        for_calc.push(poped);
        for (size_type i = 0; i < obj_prior.accepted_numb_args; ++i) {
          args_list.pop_front();
        }
      } else {
        throw std::invalid_argument("missing argument");
      }
    }
  }

  if (!for_calc.empty()) {
    Token for_res = for_calc.top();
    for_calc.pop();
    result = for_res.value_;
    if (!for_calc.empty()) {
      throw std::invalid_argument("operator missing");
    }
  }

  return result;
}

/// @brief A runner that launches all the necessary functions and works with
/// exceptions.
std::string Model::MainFunRunner(std::string input, std::string xStr) {
  double x = std::stod(xStr);
  std::queue<Token> queue;
  std::stack<Token> operators;
  std::stack<Token> output;
  double result = NAN;
  std::string throwed_error;
  ToLowercase(input);

  try {
    InputStringValidation(input);
    try {
      ParseInputToElement(input, queue);
      TokenQueueValidation(queue);
      InfixToPostfix(queue, operators, output);
      StackInversion(output);
      result = Calculate(output, x);
      if (std::isnan(result))
        throw std::logic_error("result is NAN");
      else if (std::isinf(result))
        throw std::logic_error("result is INFINITY");
    } catch (const std::exception& e) {
      std::cerr << "Caught: MainFunRunner: ParseInputToElement: " << e.what()
                << std::endl;
      throwed_error = e.what();
      return throwed_error;
    }
  } catch (const std::exception& e) {
    std::cerr << "Caught: MainFunRunner: InputStringValidation: " << e.what()
              << '\n';
    throwed_error = e.what();
    return throwed_error;
  }

  std::ostringstream out;
  out.precision(7);
//  out << std::fixed << result;
  out << result;
  return std::move(out).str();
}

void Model::ModelGetCE(std::string& input) {
  if (!input.empty()) input.erase(std::prev(input.end()));
}

void Model::ModelGetAC(std::string& input) {
  if (!input.empty()) input.clear();
}

void Model::ModelGetCleanX(std::string& input) {
  if (!input.empty()) input.clear();
}

void Model::ModelGetPow(std::string& input) { input.push_back('^'); }

void Model::ModelGetMul(std::string& input) { input.push_back('*'); }

void Model::ModelGetDigit(std::string& input, std::string button_text) {
  input += button_text;
}

void Model::ModelGetDigit2(std::string& input, std::string button_text) {
  input += button_text;
}

void Model::ModelGetSub(std::string& input) { input.push_back('-'); }

void Model::ModelGetChangeSignX(std::string& input) {
  if (input[0] == '-') {
    input.erase(0, 1);
  } else {
    std::string result = "-" + input;
    input = result;
  }
}

}  // namespace s21
