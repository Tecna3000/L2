"use_strict"
let http = require('http');
let server = http.createServer((req, res) => {
  let url = url_parser.parse(req.url, true);
  console.log(url);res.writeHead(200, {'content-type':'text/html'});
  res.end('<h1> Température à Marseille </h1>');
}
});
server.listen(3000);
