defmodule LanguageList do
  def new(), do: []

  def add(list, language), do: [language | list]

  # clojure like destructuring, very cool!
  # def remove(list), do: tl(list)
  def remove([_ | tail]), do: tail

  # def first(list), do: hd(list)
  def first([head | _]), do: head

  # haskell like function definition
  # def count(list), do: length(list)
  def count([]), do: 0
  def count([_ | t]), do: 1 + count(t)
  # not tail recursive

  # def functional_list?(list),do: "Elixir" in list
  def functional_list?([]), do: false
  def functional_list?([head | tail]) do
    if head == "Elixir" do
      true
    else 
      functional_list?(tail)
    end
  end
end
