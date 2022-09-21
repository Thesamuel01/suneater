defmodule Suneater.Repo do
  use Ecto.Repo,
    otp_app: :suneater,
    adapter: Ecto.Adapters.Postgres
end
