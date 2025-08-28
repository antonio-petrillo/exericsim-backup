defmodule FileSniffer do
  def type_from_extension("exe"), do: "application/octet-stream"
  def type_from_extension("bmp"), do: "image/bmp"
  def type_from_extension("png"), do: "image/png"
  def type_from_extension("jpg"), do: "image/jpg"
  def type_from_extension("gif"), do: "image/gif"
  def type_from_extension(_unknow), do: nil

  def type_from_binary(file_binary) do
    case file_binary do
      <<0x7f, 0x45, 0x4c, 0x46, _rest::binary>> -> "application/octet-stream"
      <<0x42, 0x4d, _rest::binary>> -> "image/bmp"
      <<0x89, 0x50, 0x4e, 0x47, 0x0d, 0x0a, 0x1a, 0x0a, _rest::binary>> -> "image/png"
      <<0xff, 0xd8, 0xff, _rest::binary>> -> "image/jpg"
      <<0x47, 0x49, 0x46, _rest::binary>> -> "image/gif"
      _ -> nil
    end
  end

  def verify(file_binary, extension) do
    from_binary = type_from_binary(file_binary)
    from_extension = type_from_extension(extension)
    if from_binary == from_extension and from_binary do
      {:ok, type_from_extension(extension)}
    else
      {:error, "Warning, file format and file extension do not match."}
    end
  end
end
