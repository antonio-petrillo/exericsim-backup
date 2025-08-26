defmodule BoutiqueInventory do
  def sort_by_price(inventory) do
    Enum.sort_by(inventory, & &1.price)
  end 

  def with_missing_price(inventory) do
    Enum.filter(inventory, & &1.price == nil)
  end

  def update_names(inventory, old_word, new_word) do
    Enum.map(inventory, fn item ->
      name = String.replace(item.name, old_word, new_word)
      %{item | name: name}
    end)
  end

  def increase_quantity(item, count) do
    quantities = item.quantity_by_size
    |> Enum.map(fn {size, amount} -> {size, amount + count} end)
    |> Map.new

    %{item | quantity_by_size: quantities}
  end

  def total_quantity(item) do
    Enum.reduce(item.quantity_by_size, 0, fn {_size, amount}, acc -> acc + amount end)
  end
end
