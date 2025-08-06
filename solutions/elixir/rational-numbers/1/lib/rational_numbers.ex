defmodule RationalNumbers do
  @module """
  This module implement Rational number type and operations
  """
  @type rational :: {integer, integer}

  @doc """
  Add two rational numbers
  """
  @spec add(a :: rational, b :: rational) :: rational
  def add({num1, den1}, {num2, den2}) do
    mcm = div(den1 * den2, Integer.gcd(den1, den2))
    reduce(num1 * div(mcm, den1) + num2 * div(mcm, den2), mcm)
  end

  @doc """
  Subtract two rational numbers
  """
  @spec subtract(a :: rational, b :: rational) :: rational
  def subtract(a, {num, den}), do: add(a, {-num, den})

  @doc """
  Multiply two rational numbers
  """
  @spec multiply(a :: rational, b :: rational) :: rational
  def multiply({num1, den1}, {num2, den2}), do: reduce(num1 * num2, den1 * den2)

  @doc """
  Divide two rational numbers
  """
  @spec divide_by(num :: rational, den :: rational) :: rational
  def divide_by(a, {num, den}), do: multiply(a, {den, num})

  @doc """
  Absolute value of a rational number
  """
  @spec abs(a :: rational) :: rational
  def abs({num, den}), do: reduce(Kernel.abs(num), Kernel.abs(den))

  @doc """
  Exponentiation of a rational number by an integer
  """
  @spec pow_rational(a :: rational, n :: integer) :: rational
  def pow_rational({num, den}, n) when n < 0, do: pow_rational({den, num}, -n)
  def pow_rational({num, den}, n), do: reduce(Integer.pow(num, n), Integer.pow(den, n))

  @doc """
  Exponentiation of a real number by a rational number
  """
  @spec pow_real(x :: integer, n :: rational) :: float
  def pow_real(x, {num, den}), do: Float.pow(x / 1, num / den)

  defp sign(a) when a < 0, do: -1
  defp sign(a) when a >= 0, do: 1

  @doc """
  Reduce a rational number to its lowest terms
  """
  @spec reduce(a :: rational) :: rational
  def reduce({num, den}) do
    gcd = Integer.gcd(num, den) * sign(den)
    {div(num, gcd), div(den, gcd)}
  end

  @spec reduce(a :: integer, b :: integer) :: rational
  def reduce(num, den) do
    gcd = Integer.gcd(num, den) * sign(den)
    {div(num, gcd), div(den, gcd)}
  end
end
