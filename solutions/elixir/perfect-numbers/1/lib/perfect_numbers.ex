defmodule PerfectNumbers do
  @doc """
  Determine the aliquot sum of the given `number`, by summing all the factors
  of `number`, aside from `number` itself.

  Based on this sum, classify the number as:

  :perfect if the aliquot sum is equal to `number`
  :abundant if the aliquot sum is greater than `number`
  :deficient if the aliquot sum is less than `number`
  """
  @spec classify(number :: integer) :: {:ok, atom} | {:error, String.t()}
  def classify(1), do: {:ok, :deficient}

  def classify(number) when number > 0 do
    aliquot_sum = 1..div(number, 2)
    |> Enum.filter(&(rem(number, &1) == 0))
    |> Enum.sum

    diff = number - aliquot_sum

    class = cond do
      diff > 0 -> :deficient
      diff < 0-> :abundant
      :else -> :perfect
    end
    {:ok, class}
  end

  def classify(_number)  do
    {:error, "Classification is only possible for natural numbers."}
  end


end
