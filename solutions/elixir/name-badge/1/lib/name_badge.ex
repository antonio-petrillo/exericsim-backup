defmodule NameBadge do
  def print(id, name, nil), do: print(id, name, "OWNER")
  def print(nil, name, department), do: "#{name} - #{String.upcase(department)}"
  def print(id, name, department), do: "[#{id}] - #{name} - #{String.upcase(department)}"
end
