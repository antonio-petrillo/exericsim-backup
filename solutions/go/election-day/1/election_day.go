package electionday

import "fmt"

func NewVoteCounter(initialVotes int) *int {
	return &initialVotes
}

func VoteCount(counter *int) int {
    if counter != nil {
        return *counter
    }
	return 0
}

func IncrementVoteCount(counter *int, increment int) {
	*counter += increment
}

func NewElectionResult(candidateName string, votes int) *ElectionResult {
	return &ElectionResult{
        Name: candidateName,
        Votes: votes,
    }
}

func DisplayResult(result *ElectionResult) string {
	return fmt.Sprintf("%s (%d)", result.Name, result.Votes)
}

func DecrementVotesOfCandidate(results map[string]int, candidate string) {
	if count, ok := results[candidate]; ok {
        results[candidate] = count - 1
    }
}
