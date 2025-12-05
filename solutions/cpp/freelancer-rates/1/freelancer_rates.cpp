#include <cmath>

double daily_rate(double hourly_rate) {
    return hourly_rate * 8.;
}

double apply_discount(double before_discount, double discount) {
  return before_discount - before_discount * (discount / 100);
}

int monthly_rate(double hourly_rate, double discount) {
  double daily {daily_rate(hourly_rate) * 22.};

  return ceil(apply_discount(daily, discount));
}

int days_in_budget(int budget, double hourly_rate, double discount) {
  double cost {apply_discount(daily_rate(hourly_rate), discount)};
  return floor(budget / cost);
}
