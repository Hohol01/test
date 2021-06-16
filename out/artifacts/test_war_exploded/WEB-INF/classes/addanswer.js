function addanswer(){
    var elemen = document.getElementsByName('ans2');
    var input = document.createElement('input');
    var br = document.createElement('br');
    input.type = 'text';
    input.name = 'ans';
    input.append('ведите ответ');
    input.appendChild(br);
    elemen.appendChild(input);
}