defmodule KitchenCalculator do
  def get_volume({_, vol}) do
    vol
  end

  def to_milliliter({:cup, vol}) do
    {:milliliter, vol * 240}
  end

  def to_milliliter({:fluid_ounce, vol}) do
    {:milliliter, vol * 30}
  end

  def to_milliliter({:teaspoon, vol}) do
    {:milliliter, vol * 5}
  end
 
  def to_milliliter({:tablespoon, vol}) do    
    {:milliliter, vol * 15}
  end
 
  def to_milliliter({:milliliter, vol}) do    
    {:milliliter, vol}
  end
 
  def from_milliliter(volume_pair, :milliliter) do
    volume_pair
  end
 
  def from_milliliter({:milliliter, vol}, :cup) do
    {:cup, vol / 240}
  end
   
  def from_milliliter({:milliliter, vol}, :fluid_ounce) do
    {:fluid_ounce, vol / 30}
  end
   
  def from_milliliter({:milliliter, vol}, :teaspoon) do
    {:teaspoon, vol / 5}
  end
   
  def from_milliliter({:milliliter, vol}, :tablespoon) do
    {:tablespoon, vol / 15}
  end

  def convert(volume_pair, unit) do
    volume_pair
    |> to_milliliter
    |> from_milliliter(unit)
  end
end
