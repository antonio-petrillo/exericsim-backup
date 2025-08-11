defmodule SpaceAge do
  @type planet ::
          :mercury
          | :venus
          | :earth
          | :mars
          | :jupiter
          | :saturn
          | :uranus
          | :neptune

  @doc """
  Return the number of years a person that has lived for 'seconds' seconds is
  aged on 'planet', or an error if 'planet' is not a planet.
  """

  @earth_seconds 31557600
  @earth_seconds_to_mercury_factor @earth_seconds * 0.2408467
  @earth_seconds_to_venus_factor @earth_seconds * 0.61519726
  @earth_seconds_to_mars_factor @earth_seconds * 1.8808158
  @earth_seconds_to_jupiter_factor @earth_seconds * 11.862615
  @earth_seconds_to_saturn_factor @earth_seconds * 29.447498
  @earth_seconds_to_uranus_factor @earth_seconds * 84.016846
  @earth_seconds_to_neptune_factor @earth_seconds * 164.79132

  @spec age_on(planet, pos_integer) :: {:ok, float} | {:error, String.t()}
  def age_on(:mercury, seconds), do: {:ok, seconds / @earth_seconds_to_mercury_factor}
  def age_on(:venus, seconds), do: {:ok, seconds / @earth_seconds_to_venus_factor}
  def age_on(:earth, seconds), do: {:ok, seconds / @earth_seconds}
  def age_on(:mars, seconds), do: {:ok, seconds / @earth_seconds_to_mars_factor}
  def age_on(:jupiter, seconds), do: {:ok, seconds / @earth_seconds_to_jupiter_factor}
  def age_on(:saturn, seconds), do: {:ok, seconds / @earth_seconds_to_saturn_factor}
  def age_on(:uranus, seconds), do: {:ok, seconds / @earth_seconds_to_uranus_factor}
  def age_on(:neptune, seconds), do: {:ok, seconds / @earth_seconds_to_neptune_factor}
  def age_on(_surely_not_a_planet_in_our_solar_system, _seconds), do: {:error, "not a planet"}
end
