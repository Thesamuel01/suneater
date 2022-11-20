defmodule SuneaterWeb.LobbyLive do
  use SuneaterWeb, :live_view

  alias SuneaterWeb.CreateLive

  def mount(_params, _session, socket) do
    rooms = Suneater.get_all_general_rooms()

    {:ok, assign(socket, :rooms, rooms)}
  end

  def handle_event("general_rooms", _value, socket) do
    rooms = Suneater.get_all_general_rooms()

    {:noreply, assign(socket, :rooms, rooms)}
  end

  def handle_event("community_rooms", _value, socket) do
    rooms = Suneater.get_all_rooms()

    {:noreply, assign(socket, :rooms, rooms)}
  end

  def handle_event("profile", _value, socket) do
    {:noreply, socket}
  end

  def handle_event("settings", _value, socket) do
    {:noreply, socket}
  end

  def handle_event("create_room", _value, socket) do
    {:noreply, redirect(socket, to: Routes.live_path(socket, CreateLive))}
  end
end
