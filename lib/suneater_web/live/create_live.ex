defmodule SuneaterWeb.CreateLive do
  use SuneaterWeb, :live_view

  alias Suneater.Room
  alias SuneaterWeb.LobbyLive

  def mount(_params, _session, socket) do
    {:ok, assign(socket, :changeset, Room.changeset(%{}))}
  end

  def handle_event("validate", %{"room" => room}, socket) do
    changeset = Room.changeset(room)

    {:noreply, assign(socket, changeset: changeset)}
  end

  def handle_event("save", %{"room" => room_params} = values, socket) do
    IO.inspect(values)

    case Suneater.create_room(room_params) do
      {:ok, _room} ->
        {:noreply,
         socket
         |> put_flash(:info, "Room created")
         |> redirect(to: Routes.live_path(socket, LobbyLive))}

      {:error, %Ecto.Changeset{} = changeset} ->
        {:noreply, assign(socket, changeset: changeset)}
    end
  end
end
