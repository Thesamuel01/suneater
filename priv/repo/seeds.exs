# Script for populating the database. You can run it as:
#
#     mix run priv/repo/seeds.exs
#
# Inside the script, you can read and write to any of your
# repositories directly:
#
#     Suneater.Repo.insert!(%Suneater.SomeSchema{})
#
# We recommend using the bang functions (`insert!`, `update!`
# and so on) as they will fail if something goes wrong.

alias Suneater.{Repo, Room}

Repo.insert!(%Room{
  id: "c3e848c1-6e4f-4bc7-b9ee-9af73262c1f3",
  name: "Cinema",
  description: "Random Description",
  topic: "entertainment",
  type: "general"
})

Repo.insert!(%Room{
  id: "54a88942-7706-4f98-9a31-c5523affce72",
  name: "Sports",
  description: "Random Description",
  topic: "sport",
  type: "general"
})

Repo.insert!(%Room{
  id: "f18ec984-410d-473c-bddf-3d130280275e",
  name: "Conversation",
  description: "Random Description",
  topic: "any",
  type: "general"
})
