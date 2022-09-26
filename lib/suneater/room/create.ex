defmodule Suneater.Room.Create do
  alias Suneater.{Repo, Room}

  def call(params) do
    params
    |> Map.put("type", "community")
    |> Room.build()
    |> create_room()
  end

  defp create_room({:ok, struct}), do: Repo.insert(struct)
  defp create_room({:error, _changeset} = error), do: error
end
