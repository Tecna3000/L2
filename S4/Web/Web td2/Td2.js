function Max(t){
    let lenght = t.length;
    let max = t[0];
    for (let i = 0 ; i < lenght; ++i) {
      if (t[i] > max) {
        max = t[i];
      }
    }

            return max;
}




function argmax(t){
    let lenght = t.length;
    let indicemax = 0;
    for (let i = 1; i < lenght; i++) {
      if (t[i] > t[indicemax]) {
        indicemax = i;
      }
    }

            return indicemax;
}
let t1 = [1, 3, 2];
let t2 = [1, 3, 2];
t2[20] = 23;
let t3 = ["1", "21", "10"];
/*let maximum = Max(t1);
let maximum = Max(t2);
let maximum = Max(t3);
console.log(maximum);*/

let indmax = argmax(t1);
/*let indmax = argmax(t2);
t2[20] = 23;
let indmax = argmax(t3);*/
console.log(indmax);



function Median(t) {
      l = t.lenght;
      if (l % 2 == 0) {
        pos1 = l/2;
          pos2 = (l/2) + 1;
          return (t[pos1] +t[pos2])/2;

      } else {
          pos = (l+1)/2
          return t[pos];
      }
}
let t4 = [1,2,3,5,85,95,100];
let med = Median(t4);
console.log(med);
