defmodule LibraryFees do
  @before_noon 28
  @after_noon 29

  def datetime_from_string(string) do
    NaiveDateTime.from_iso8601!(string)
  end

  def before_noon?(datetime), do: datetime.hour < 12

  def return_date(checkout_datetime) do
    days = if before_noon?(checkout_datetime), do: @before_noon, else: @after_noon 

    NaiveDateTime.to_date(checkout_datetime)
    |> Date.add(days)
  end

  def days_late(planned_return_date, actual_return_datetime) do
    case Date.diff(actual_return_datetime, planned_return_date) do
     x when x < 0 -> 0                               
     x -> x
    end
  end

  def monday?(datetime) do
    1 == Date.day_of_week(datetime)
  end

  def calculate_late_fee(checkout, return, rate) do
    checkout = datetime_from_string(checkout)
    return = datetime_from_string(return)
    fee = days_late(return_date(checkout), return) * rate

    if monday?(return) do
      floor(0.5 * fee)
    else
      fee
    end
  end
end
