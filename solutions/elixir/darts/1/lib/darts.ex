defmodule Darts do
  @type position :: {number, number}

  @doc """
  Calculate the score of a single dart hitting a target
  """
  @spec score(position) :: integer
  def score({x, y}) do
    dist = (x * x + y * y) ** 0.5
    cond do
      dist <= 1.0 -> 10
      dist <= 5.0 -> 5
      dist <= 10.0 -> 1
      true -> 0
    end
  end
end
