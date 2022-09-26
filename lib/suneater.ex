defmodule Suneater do
  alias Suneater.Room

  defdelegate get_all_rooms(), to: Room.GetAll, as: :call
  defdelegate get_all_general_rooms(), to: Room.General.GetAll, as: :call
  defdelegate create_room(params), to: Room.Create, as: :call
end
