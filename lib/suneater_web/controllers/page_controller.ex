defmodule SuneaterWeb.PageController do
  use SuneaterWeb, :controller

  def index(conn, _params) do
    render(conn, "index.html")
  end
end
