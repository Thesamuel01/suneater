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

  def create(conn, %{"room" => room_params}) do
    case Suneater.create_room(room_params) do
      {:ok, _room} ->
        conn
        |> put_flash(:info, "Room created")
        |> redirect(to: Routes.room_path(conn, :index))

      {:error, %Ecto.Changeset{} = changeset} ->
        render(conn, "new.html", changeset: changeset)
    end
  end
end
