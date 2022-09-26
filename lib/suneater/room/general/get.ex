defmodule Suneater.Room.General.GetAll do
  import Ecto.Query, only: [from: 2]

  alias Suneater.{Repo, Room}

  def call() do
    query = from r in Room, where: r.type == "general", select: r

    Repo.all(query)
  end
end
