defmodule SuneaterWeb.LobbyLive do
  use SuneaterWeb, :live_view

  def mount(_params, _session, socket) do
    rooms = Suneater.get_all_general_rooms()

    {:ok, assign(socket, :rooms, rooms)}
  end

  def handle_event("general_room", _value, socket) do
    rooms = Suneater.get_all_general_rooms()

    {:noreply, assign(socket, :rooms, rooms)}
  end

  def handle_event("community_room", _value, socket) do
    rooms = Suneater.get_all_rooms()

    {:noreply, assign(socket, :rooms, rooms)}
  end

  def handle_event("settings", _value, socket) do
    rooms = Suneater.get_all_rooms()

    {:noreply, assign(socket, :rooms, rooms)}
  end
end
