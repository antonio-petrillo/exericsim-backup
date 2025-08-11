defmodule BasketballWebsite do
  def extract_from_path(data, path), do: get_in_(data, String.split(path, "."))

  defp get_in_(data, []), do: data
  defp get_in_(data, [parent | childs]), do: get_in_(data[parent], childs)

  def get_in_path(data, path), do: get_in(data, String.split(path, "."))
end
