defmodule Suneater.Repo.Migrations.AddTypeToRooms do
  use Ecto.Migration

  def change do
    alter table(:rooms) do
      add :type, :string, null: false
    end
  end
end
