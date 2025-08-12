defmodule SquareRoot do
  @doc """
  Calculate the integer square root of a positive integer
  """
  @spec calculate(radicand :: pos_integer) :: pos_integer
  def calculate(radicand), do: binary_search(radicand, 0, radicand + 1)

  defp binary_search(radicand, left, right) do
    middle = div(left + right, 2)

    cond do
      left == right - 1 -> left
      middle * middle <= radicand -> binary_search(radicand, middle, right)
      :else -> binary_search(radicand, left, middle)
    end
  end
end
