defmodule TakeANumber do
  def start() do
    spawn(fn -> handle(0) end)
  end

  defp handle(state) do
    receive do
      {:report_state, sender_pid} ->
        send(sender_pid, state)
        handle(state)

      {:take_a_number, sender_pid} ->
        send(sender_pid, state + 1)
        handle(state + 1)

      :stop ->
        nil

      _ ->
        handle(state)
    end
  end
end
