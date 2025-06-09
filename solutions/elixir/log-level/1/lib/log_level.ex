defmodule LogLevel do
  def to_label(level, legacy?) do
    case level do
      0 -> if legacy?, do: :unknown, else:  :trace
      1 -> :debug
      2 -> :info
      3 -> :warning
      4 -> :error
      5 -> if legacy?, do: :unknown, else: :fatal
      _ -> :unknown
    end
  end

  def alert_recipient(level, legacy?) do
    label = to_label(level, legacy?)

    case {label, legacy?} do
      {:error, _} -> :ops 
      {:fatal, _} -> :ops
      {:unknown, true} -> :dev1
      {:unknown, false} -> :dev2
      _ -> false
    end
  end
end
