const TAU = Math.PI * 2;

if (true) {
    console.log("True");
} else {
    console.log("False");
}

switch (2) {
    case 2:
        console.log("2");
        break;
    default:
        console.log("Default");
        break;
}

for (const n of [1, 2, 3, 4, 5]) {
    console.log(n * n);
}

async function fetchData() {
    return (await fetch('https://api.example.com/data')).json();
}

fetchData().then(data => console.log(data));

class Foo {
    bar = 42;

    baz() { return this.bar; }
}