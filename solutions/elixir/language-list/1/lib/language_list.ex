defmodule LanguageList do
  def new(), do: []

  def add(list, language), do: [language | list]

  def remove([]), do: []
  def remove([_ | tl]), do: tl

  def count(list), do: count_(list, 0)

  def first([]), do: nil
  def first([h | _]), do: h

  defp count_([], len), do: len
  defp count_([_ | tl], len), do: count_(tl, len + 1)

  def functional_list?([]), do: false

  def functional_list?([h | tl]) do
    if h == "Elixir" do
      true
    else
      functional_list?(tl)
    end
  end
end
