class Js {
    function(numbers) {
        let highest = numbers[0]
        let smallest = numbers[0]
        for (let count = 0; count < numbers.length; count++) {
            if (count > highest) highest = count;
            if (count < smallest) smallest = count;
        }
        return [highest - smallest]
    }
}
js = new Js()
let value = [2, 4, 5, 7, 8]
console.log(js.function(value))
console.log(typeof null)