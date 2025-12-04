#include <string>

#define EXERCISM_RUN_ALL_TESTS

namespace log_line {
std::string message(std::string line) {
  int offset = line.find(":");
  return line.substr(offset + 2);
}

std::string log_level(std::string line) {
  int offset = line.find(":");
  return line.substr(1, offset - 2);
}

std::string reformat(std::string line) {
  std::string msg = message(line);
  std::string log = log_level(line);
  return msg + " (" + log + ")";
}
}  // namespace log_line
