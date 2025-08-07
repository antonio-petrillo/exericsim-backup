defmodule Username do
  def sanitize(username), do: Enum.flat_map(username, &sanitize_char/1)

  defp sanitize_char(char) when is_number(char) do
    case char do
      ?ä -> 'ae'
      ?ö -> 'oe'
      ?ü -> 'ue'
      ?ß -> 'ss'
      ?_ -> '_'
      lowercase when lowercase >= ?a and lowercase <= ?z -> [lowercase]
      _ -> ''
    end
  end
end
