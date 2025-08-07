defmodule BirdCount do
  def today([]), do: nil
  def today([h | _]), do: h

  def increment_day_count([]), do: [1]
  def increment_day_count([h | tl]), do: [h + 1 | tl]

  def has_day_without_birds?([]), do: false

  def has_day_without_birds?([h | tl]) do
    if h == 0 do
      true
    else
      has_day_without_birds?(tl)
    end
  end

  def total(list), do: Enum.sum(list)

  def busy_days(list) do
    list
    |> Enum.filter(&(&1 > 4)/1)
    |> Enum.count
  end

  def busy_days(list), do: busy_days_(list, 0)
  defp busy_days_([], acc), do: acc

  defp busy_days_([h | tl], acc) do
    if h > 4 do
      busy_days_(tl, acc + 1)
    else
      busy_days_(tl, acc)
    end
  end
end
