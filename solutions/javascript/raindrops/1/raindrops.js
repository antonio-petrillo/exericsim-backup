//
// This is only a SKELETON file for the 'Raindrops' exercise. It's been provided as a
// convenience to get you started writing code faster.
//
function convertWithTable(n, sounds){
  const result = sounds
    .filter(sound => n % sound.key === 0)
    .map(sound => sound.sound)
    .join("")
  return result.length > 0 ? result : `${n}`
}


export const convert = (n) => {
  return convertWithTable(n, [{key: 3, sound: "Pling"}, {key: 5, sound: "Plang"},{key: 7, sound: "Plong"}])
};
