defmodule Suneater.Room do
  use Ecto.Schema
  import Ecto.Changeset

  @primary_key {:id, Ecto.UUID, autogenerate: true}

  schema "rooms" do
    field :description, :string
    field :name, :string
    field :topic, :string
    field :type, :string
    timestamps()
  end

  def build(params) do
    params
    |> changeset()
    |> apply_action(:insert)
  end

  def changeset(params) do
    %__MODULE__{}
    |> cast(params, [:name, :description, :topic, :type])
    |> validate_required([:name])
    |> unique_constraint(:name)
    |> validate_length(:name, min: 5, max: 30)
    |> validate_length(:topic, min: 5, max: 150)
  end
end
