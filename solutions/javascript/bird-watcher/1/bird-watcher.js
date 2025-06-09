// @ts-check
//
// The line above enables type checking for this file. Various IDEs interpret
// the @ts-check directive. It will give you helpful autocompletion when
// implementing this exercise.

/**
 * Calculates the total bird count.
 *
 * @param {number[]} birdsPerDay
 * @returns {number} total bird count
 */
export function totalBirdCount(birdsPerDay) {
  // let sum = 0;
  // for (let i = 0; i < birdsPerDay.length; i++) {
  //   sum += birdsPerDay[i];
  // }
  // return sum;
  return birdsPerDay.reduce((sum, num) => sum + num);
}

/**
 * Calculates the total number of birds seen in a specific week.
 *
 * @param {number[]} birdsPerDay
 * @param {number} week
 * @returns {number} birds counted in the given week
 */
export function birdsInWeek(birdsPerDay, week) {
  // let sum = 0;
  //   for (let i = (week - 1) * 7; i < week * 7 && i < birdsPerDay.length; i++){

  //   sum += birdsPerDay[i];
  // }
  // return sum;
  return birdsPerDay
	.filter((birds, index) => index >= (week - 1) * 7 && index < week * 7)
	.reduce((sum, birds) => sum + birds);

}

/**
 * Fixes the counting mistake by increasing the bird count
 * by one for every second day.
 *
 * @param {number[]} birdsPerDay
 * @returns {number[]} corrected bird count data
 */
export function fixBirdCountLog(birdsPerDay) {
 //  	for (let i = 0 ; i < birdsPerDay.length; i++){
	// 	if ((i & 1) == 0) {
	// 		birdsPerDay[i]++;
	// 	}
	// }
    birdsPerDay.forEach((birds, index) => {
      if ((index & 1) == 0) {
        birdsPerDay[index]++;
      }
    })
	return birdsPerDay;
}
