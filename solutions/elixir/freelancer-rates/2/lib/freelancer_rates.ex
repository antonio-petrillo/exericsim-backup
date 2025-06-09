defmodule FreelancerRates do
  @day_in_a_month 22

  def daily_rate(hourly_rate), do: 8.0 * hourly_rate

  def apply_discount(before_discount, discount), do: before_discount * (1.0 - discount / 100.0)

  def monthly_rate(hourly_rate, discount) do
    # Kernel ceil
    ceil(apply_discount(daily_rate(hourly_rate) * @day_in_a_month, discount))
  end

  def days_in_budget(budget, hourly_rate, discount) do
    # Float floor accept rounding digits
    Float.floor(budget / apply_discount(daily_rate(hourly_rate), discount), 1)
  end
end
