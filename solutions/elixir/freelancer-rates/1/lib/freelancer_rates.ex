defmodule FreelancerRates do
  def daily_rate(hourly_rate), do: 8.0 * hourly_rate

  def apply_discount(before_discount, discount), do: before_discount - before_discount * (discount / 100)

  def monthly_rate(hourly_rate, discount) do
    hourly_rate
    |> daily_rate
    |> (fn x -> x * 22 end).()
    |> apply_discount(discount)
    |> ceil
  end

  def days_in_budget(budget, hourly_rate, discount) do
    hourly_rate
    |> daily_rate
    |> apply_discount(discount)
    |> (fn x -> budget / x end).()
    |> Float.floor(1)
  end
end
