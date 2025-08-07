defmodule PaintByNumber do
  defp pow_of_2?(0), do: false
  defp pow_of_2?(1), do: true
  defp pow_of_2?(n), do: Bitwise.band(n, n - 1) == 0

  defp log2(0), do: 1
  defp log2(1), do: 1

  defp log2(n) do
    if pow_of_2?(n) do
      popcount(n - 1, 0)
    else
      nearest_pow_of_2_minus_1(n)
      |> popcount(0)
    end
  end

  defp nearest_pow_of_2_minus_1(n) do
    if pow_of_2?(n) do
      Bitwise.<<<(n, 1) - 1
    else
      nearest_pow_of_2_minus_1(Bitwise.band(n, n - 1))
    end
  end

  defp popcount(0, acc), do: acc
  defp popcount(n, acc), do: popcount(Bitwise.band(n, n - 1), acc + 1)

  def palette_bit_size(color_count), do: log2(color_count)

  def empty_picture() do
    <<>>
  end

  def test_picture() do
    <<0::2, 1::2, 2::2, 3::2>>
  end

  def prepend_pixel(picture, color_count, pixel_color_index) do
    bits = palette_bit_size(color_count)
    <<pixel_color_index::size(bits), picture::bitstring>>
  end

  def get_first_pixel(<<>>, _color_count), do: nil
  def get_first_pixel(picture, color_count) do
    bits = palette_bit_size(color_count)
    <<first_pixel::size(bits), _rest::bitstring>> = picture
    first_pixel
  end

  def drop_first_pixel(<<>>, _color_count), do: <<>>
  def drop_first_pixel(picture, color_count) do
    bits = palette_bit_size(color_count)
    <<_first_pixel::size(bits), rest::bitstring>> = picture
    rest
  end

  def concat_pictures(picture1, picture2) do
    <<picture1::bitstring, picture2::bitstring>>
  end
end
