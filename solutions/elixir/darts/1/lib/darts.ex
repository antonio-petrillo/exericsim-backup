defmodule Darts do
  @type position :: {number, number}

  @doc """
  Calculate the score of a single dart hitting a target
  """
  @spec score(position) :: integer
  def score({x, y}) do
    d = x * x + y * y

    cond do
      d <= 1 -> 10
      d <= 25 -> 5
      d <= 100 -> 1
      :else -> 0
    end
  end
end
