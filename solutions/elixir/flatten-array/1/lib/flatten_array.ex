defmodule FlattenArray do
  @doc """
    Accept a list and return the list flattened without nil values.

    ## Examples

      iex> FlattenArray.flatten([1, [2], 3, nil])
      [1, 2, 3]

      iex> FlattenArray.flatten([nil, nil])
      []

  """

  @spec flatten(list) :: list
  def flatten([]), do: []
  def flatten([nil | tl]), do: flatten(tl)
  def flatten([h | tl]) when is_list(h), do: flatten(h) ++ flatten(tl)
  def flatten([h | tl]), do: [h | flatten(tl)]
end
