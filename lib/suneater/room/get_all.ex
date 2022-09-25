defmodule Suneater.Room.GetAll do
  alias Suneater.{Repo, Room}

  def call() do
    Repo.all(Room)
  end
end
