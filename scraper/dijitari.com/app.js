var request = require('request');
var cheerio = require('cheerio');
var async = require('async');

var linkBegin = "http://www.dijitari.com/xvii/categories/all_vdesc_"
var linkEnd = ".html"
var range = 34;

var links = [];

for (var i = 1; i <= range; i++) {
  links.push(linkBegin + i + linkEnd);
}

var requester = function(link, callback) {
  request(link, function(error, response, body) {
    if (error) throw error;

    callback(null, body);
  });
};

var extractor = function(body) {
  var $ = cheerio.load(body);

  // td > font > font > __haiku text, author__ > p
  var haikuBlobs = $('td[valign=TOP]').children().children();
  var haikus = haikuBlobs.map(function() {
    var html = this.html();
    var lines = html.split('<br>');
    // First 3 lines are the haiku
    var haiku = lines.slice(0, 3).map(function(element) {
      // Remove &nbsp; \n and any non-letters
      return element.replace(/&nbsp;/g, ' ').replace(/\n/g, '').replace(/[^a-zA-Z ]/g, '').trim();
    });

    return haiku.join("\n");
  });

  return haikus;
}

var bodies = async.map(links, requester, function(err, results) {
  var haikusGroupedByPage = results.map(extractor);

  haikusGroupedByPage.forEach(function(haikus) {
    haikus.forEach(function(haiku) {
      console.log(haiku);
      console.log("\n");
    })
  });
});
