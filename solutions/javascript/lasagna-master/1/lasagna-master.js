/// <reference path="./global.d.ts" />
// @ts-check

/**
 * Implement the functions needed to solve the exercise here.
 * Do not forget to export them so they are available for the
 * tests. Here an example of the syntax as reminder:
 *
 * export function yourFunction(...) {
 *   ...
 * }
 */

export function cookingStatus(timer) {
  if (timer === undefined) {
    return 'You forgot to set the timer.';
  }
  return timer === 0 ? 'Lasagna is done.' : 'Not done, please wait.';
}

export function preparationTime(layers, minutePerLayer) {
  return layers.length * (minutePerLayer ?? 2);
}

export function quantities(layers) {
  let needed = {noodles: 0, sauce: 0};
  layers.forEach(layer => {
    if (layer === 'noodles') {
      needed.noodles += 50;
    }
    if (layer === 'sauce') {
      needed.sauce += 0.2;
    }
  })
  return needed;
}

export function addSecretIngredient(friends, mine) {
  mine.push(friends[friends.length - 1]);
}

export function scaleRecipe(recipe, scale) {
  scale /= 2;
  const scaled = {...recipe};
  for (let ingredient in scaled) {
    scaled[ingredient] *= scale;
  }
  return scaled;
}