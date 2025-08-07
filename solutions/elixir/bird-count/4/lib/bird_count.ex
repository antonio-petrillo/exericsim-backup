defmodule BirdCount do
  def today(list), do: List.first(list)

  def increment_day_count([]), do: [1]
  def increment_day_count([h | tl]), do: [h + 1 | tl]

  def has_day_without_birds?(list), do: Enum.any?(list, fn birds -> birds == 0 end)

  def total(list), do: Enum.sum(list)

  def busy_days(list) do
    list
    |> Enum.filter(fn birds -> birds > 4 end)
    |> Enum.count
  end
end
