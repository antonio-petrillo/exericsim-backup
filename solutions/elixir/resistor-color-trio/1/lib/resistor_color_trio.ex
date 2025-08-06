defmodule ResistorColorTrio do
  @moduledoc """
  Module for ResistorColorTrio exercise 
  """
  defp color_to_num(:black), do: 0
  defp color_to_num(:brown), do: 1
  defp color_to_num(:red), do: 2
  defp color_to_num(:orange), do: 3
  defp color_to_num(:yellow), do: 4
  defp color_to_num(:green), do: 5
  defp color_to_num(:blue), do: 6
  defp color_to_num(:violet), do: 7
  defp color_to_num(:grey), do: 8
  defp color_to_num(:white), do: 9

  defp multiplier(:black), do: 1
  defp multiplier(:brown), do: 10
  defp multiplier(:red), do: 100
  defp multiplier(:orange), do: 1_000
  defp multiplier(:yellow), do: 10_000
  defp multiplier(:green), do: 100_000
  defp multiplier(:blue), do: 1_000_000
  defp multiplier(:violet), do: 10_000_000
  defp multiplier(:grey), do: 100_000_000
  defp multiplier(:white), do: 1_000_000_000

  @doc """
  Calculate the resistance value in ohms from resistor colors
  """
  @spec label(colors :: [atom]) :: {number, :ohms | :kiloohms | :megaohms | :gigaohms}
  def label([color1, color2, color3 | _]) do
    ohm1 = color_to_num(color1)
    ohm2 = color_to_num(color2)

    factor = multiplier(color3)
    value = (ohm1 * 10 + ohm2) * factor

    cond do
      value < 1_000 -> {value, :ohms}
      value < 1_000_000 -> {value / 1_000, :kiloohms}
      value < 1_000_000_000 -> {value / 1_000_000, :megaohms}
      :else -> {value / 1_000_000_000, :gigaohms}
    end
  end
end
