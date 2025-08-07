defmodule NameBadge do
  defp fmt_department(department), do: if department, do: String.upcase(department), else: "OWNER"

  def print(nil, name, department), do: "#{name} - #{fmt_department(department)}"
  def print(id, name, department), do: "[#{id}] - #{name} - #{fmt_department(department)}"
end
