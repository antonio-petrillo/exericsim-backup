require Integer

defmodule PrimeFactors do
  @doc """
  Compute the prime factors for 'number'.

  The prime factors are prime numbers that when multiplied give the desired
  number.

  The prime factors of 'number' will be ordered lowest to highest.
  """
  @spec factors_for(pos_integer) :: [pos_integer]
  def factors_for(number), do: factors_for(number, 2) |> Enum.reverse

  defp factors_for(n, factor, acc \\ [])
  defp factors_for(1, _factor, acc), do: acc
  defp factors_for(n, 2, acc) when Integer.is_even(n), do: factors_for(div(n, 2), 2, [2 | acc])
  defp factors_for(n, 2, acc), do: factors_for(n, 3, acc)
  defp factors_for(n, factor, acc) do
    if rem(n, factor) == 0 do
      factors_for(div(n, factor), factor, [factor | acc])
    else
      factors_for(n, factor + 2, acc)
    end
  end
end
