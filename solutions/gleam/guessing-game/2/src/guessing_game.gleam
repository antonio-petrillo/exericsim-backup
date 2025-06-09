pub fn reply(guess: Int) -> String {
  case guess {
  	   41 | 43 -> "So close"
	   _ if guess < 41 -> "Too low"
	   42 -> "Correct"
	   _ -> "Too high"
  }
}
