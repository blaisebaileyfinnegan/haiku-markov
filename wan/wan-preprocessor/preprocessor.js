var fs = require('fs');
var csv = require('csv');

if (process.argv.length != 3) throw new Error('Expected input file')

var path = process.argv[2];
var file = fs.readFileSync(path, {
  encoding: 'utf8'
});


csv()
  .from(file)
  .to(console.log)
  .transform(function(row) {
    return [row[0], row[1], row[3], row[4]].map(function(element){
      return String(element).trim();
    });
  })
