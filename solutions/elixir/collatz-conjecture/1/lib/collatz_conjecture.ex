defmodule CollatzConjecture do
  import Integer, only: [is_even: 1]
  @doc """
  calc/1 takes an integer and returns the number of steps required to get the
  number to 1 when following the rules:
    - if number is odd, multiply with 3 and add 1
    - if number is even, divide by 2
  """
  @spec calc(input :: pos_integer()) :: non_neg_integer()
  def calc(input) when is_integer(input) and input > 0, do: calc_(input, 0)

  defp calc_(1, count), do: count
  defp calc_(n, count) when is_even(n), do: calc_(div(n, 2), count + 1)
  defp calc_(n, count), do: calc_(3*n+1, count + 1)
end
