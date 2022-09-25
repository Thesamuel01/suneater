defmodule SuneaterWeb.RoomController do
  use SuneaterWeb, :controller

  alias Suneater.Room

  def index(conn, _params) do
    render(conn, "index.html")
  end

  def new(conn, _params) do
    changeset = Room.changeset(%{})

    render(conn, "new.html", changeset: changeset)
  end
end
