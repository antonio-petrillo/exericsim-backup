defmodule EliudsEggs do
  @doc """
  Given the number, count the number of eggs.
  """
  @spec egg_count(number :: integer()) :: non_neg_integer()
  def egg_count(number), do: egg_count_(number, 0)

  defp egg_count_(0, acc), do: acc
  defp egg_count_(n, acc), do: egg_count_(Bitwise.band(n, n - 1), acc + 1)

end
