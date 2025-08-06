defmodule KitchenCalculator do
  def get_volume({_, amount}), do: amount

  def to_milliliter({:milliliter, amount}), do: {:milliliter, amount}
  def to_milliliter({:cup, amount}), do: {:milliliter, amount * 240.0}
  def to_milliliter({:fluid_ounce, amount}), do: {:milliliter, amount * 30.0}
  def to_milliliter({:teaspoon, amount}), do: {:milliliter, amount * 5.0}
  def to_milliliter({:tablespoon, amount}), do: {:milliliter, amount * 15.0}

  def from_milliliter({:milliliter, amount}, :milliliter), do: {:milliliter, amount}
  def from_milliliter({:milliliter, amount}, :cup), do: {:cup, amount / 240.0}
  def from_milliliter({:milliliter, amount}, :fluid_ounce), do: {:fluid_ounce, amount / 30.0}
  def from_milliliter({:milliliter, amount}, :teaspoon), do: {:teaspoon, amount / 5.0}
  def from_milliliter({:milliliter, amount}, :tablespoon), do: {:tablespoon, amount / 15.0}

  def convert(volume_pair, unit) do
    volume_pair
    |> to_milliliter
    |> from_milliliter(unit)
  end
end
