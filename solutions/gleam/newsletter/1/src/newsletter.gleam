import simplifile
import gleam/result
import gleam/string
import gleam/list

pub fn read_emails(path: String) -> Result(List(String), Nil) {
  simplifile.read(path)
  |> result.map(string.trim(_))
  |> result.map(string.split(_, on: "\n"))
  |> result.nil_error
}

pub fn create_log_file(path: String) -> Result(Nil, Nil) {
  simplifile.create_file(path)
  |> result.nil_error
}

pub fn log_sent_email(path: String, email: String) -> Result(Nil, Nil) {
  let log = string.trim(email) <> "\n"
  simplifile.append(to: path, contents: log)
  |> result.nil_error
}

pub fn send_newsletter(
  emails_path: String,
  log_path: String,
  send_email: fn(String) -> Result(Nil, Nil),
) -> Result(Nil, Nil) {
  let assert Ok(emails) = read_emails(log_path)
  let assert Ok(_) = create_log_file(emails_path)

  let consumer = fn(email) {
    let assert Ok(_) = send_email(email)
    log_sent_email(log_path, email)
  }


  list.try_each(emails, consumer)
  |> result.nil_error
}
