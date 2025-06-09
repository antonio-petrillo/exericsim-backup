pub fn reply(guess: Int) -> String {
  case guess {
  	   42 -> "Correct"
	   _ if guess < 41 -> "Too low"
	   _ if guess > 43 -> "Too high"
	   _ -> "So close"
  }
}
