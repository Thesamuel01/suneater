defmodule Suneater.Repo.Migrations.CreateRoomTable do
  use Ecto.Migration

  def change do
    create table(:rooms, primary_key: false) do
      add :id, :uuid, primary_key: true
      add :name, :string, null: false, size: 30
      add :description, :string
      add :topic, :string, size: 150
      timestamps()
    end

    create unique_index(:rooms, [:name])
  end
end
