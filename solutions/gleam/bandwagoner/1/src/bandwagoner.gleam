pub type Coach {
  Coach(name: String, former_player: Bool)
}

pub type Stats {
  Stats(wins: Int, losses: Int)
}

pub type Team {
  Team(name: String, coach: Coach, stats: Stats)
}

pub fn create_coach(name: String, former_player: Bool) -> Coach {
  Coach(former_player: former_player, name: name) // example with label
}

pub fn create_stats(wins: Int, losses: Int) -> Stats {
  Stats(wins, losses) // example without label
}

pub fn create_team(name: String, coach: Coach, stats: Stats) -> Team {
  Team(name: name, coach: coach, stats: stats)
}

pub fn replace_coach(team: Team, coach: Coach) -> Team {
  Team(..team, coach: coach)
}

pub fn is_same_team(home_team: Team, away_team: Team) -> Bool {
  let home_coach = home_team.coach
  let away_coach = away_team.coach
  let home_stats = home_team.stats
  let away_stats = away_team.stats
  let home_name = home_team.name
  let away_name = away_team.name

  home_coach.name == away_coach.name && home_coach.former_player == away_coach.former_player
  && home_stats.wins == away_stats.wins && home_stats.losses == away_stats.losses
  && home_name == away_name
}

pub fn root_for_team(team: Team) -> Bool {
  let coach = team.coach
  let stats = team.stats
  let name = team.name

  coach.name == "Gregg Popovich"
  || coach.former_player
  || stats.wins >= 60 || stats.wins < stats.losses
  || name == "Chicago Bulls"
}
