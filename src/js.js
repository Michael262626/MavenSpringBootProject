// class Js {
//    function(numbers) {
//         let leftOperand = 5
//         let rightOperand = 6
//         let result = 0
//     switch (numbers){
//         case "+" : result = leftOperand + rightOperand
//             break;
//         case "-":  result = leftOperand - rightOperand
//             break;
//         case "*":  result =  leftOperand * rightOperand
//             break;
//         case  "/": result = leftOperand / rightOperand
//             break;
//         default: result = NaN;}
// return(result)}}
// js = new Js()
// let value = "*"
// console.log(js.function(value))
function higherOrderFunction(func){
    console.log("higher function")
    func();
}
 higherOrderFunction(()=>{console.log("lower function")});
// higherOrderFunction(lowerOfFunction);
function greetings(greets) {
    return function person(name) {
        return `Hello, ${name}.${greets}`;
    }
}
console.log(greetings("Good morning!")("mike"))