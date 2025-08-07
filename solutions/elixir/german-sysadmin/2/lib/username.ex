defmodule Username do
  def sanitize(username), do: Enum.flat_map(username, &sanitize_char/1)

  defp sanitize_char(char) when is_number(char) do
    case char do
      ?ä -> ~c"ae"
      ?ö -> ~c"oe"
      ?ü -> ~c"ue"
      ?ß -> ~c"ss"
      ?_ -> ~c"_"
      lowercase when lowercase >= ?a and lowercase <= ?z -> [lowercase]
      _ -> ~c""
    end
  end
end
