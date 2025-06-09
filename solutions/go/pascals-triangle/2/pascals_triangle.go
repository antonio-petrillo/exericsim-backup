package pascal

func Triangle(n int) (res [][]int) {
	if n > 0 {
    	row := []int{1}
    	res = [][]int{row}
    	for i := 1; i < n; i++ {
        	next := append(append([]int{}, row...), 0)
        	for j := 0; j < len(row); j++ {
            	next[j + 1] += row[j]
	        }
    	    res, row = append(res, next), next
    	}
    }
    return
}
