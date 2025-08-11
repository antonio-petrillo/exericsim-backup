defmodule Triangle do
  defguardp positive_sides(a, b, c) when a > 0 and b > 0 and c > 0
  defguardp triangle_inequality(a, b, c) when a + b >= c and b + c >= a and a + c >= b

  @type kind :: :equilateral | :isosceles | :scalene

  @doc """
  Return the kind of triangle of a triangle with 'a', 'b' and 'c' as lengths.
  """
  @spec kind(number, number, number) :: {:ok, kind} | {:error, String.t()}
  def kind(a, a, a) when positive_sides(a, a, a) and triangle_inequality(a, a, a), do: {:ok, :equilateral}

  def kind(a, a, c) when positive_sides(a, a, c) and triangle_inequality(a, a, c), do: {:ok, :isosceles}
  def kind(a, b, b) when positive_sides(a, b, b) and triangle_inequality(a, b, b), do: {:ok, :isosceles}
  def kind(a, b, a) when positive_sides(a, b, a) and triangle_inequality(a, b, a), do: {:ok, :isosceles}

  def kind(a, b, c) when positive_sides(a, b, c) and triangle_inequality(a, b, c), do: {:ok, :scalene}

  def kind(a, b, c) when not positive_sides(a, b, c), do: {:error, "all side lengths must be positive"}
  def kind(a, b, c) when not triangle_inequality(a, b, c), do: {:error, "side lengths violate triangle inequality"}
end
