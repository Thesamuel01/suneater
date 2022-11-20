defmodule SuneaterWeb.LoginController do
  use SuneaterWeb, :controller

  def index(conn, _params) do
    conn
    |> render("index.html")
  end
end
