//app.js

//express_demo.js 文件
var express = require('express');
var bodyParser = require('body-parser');
var app = express();
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));

const patchMd5 = "9b4c4dfe972df109d3defa2862e2636c";
const patchPath = "http://10.21.1.119:9091/patch/125_to_126.zip";
const v126MD5 = "e506b7a123f49ce6dae369914d596a03";
const v126Path = "http://10.21.1.119:9091/patch/126.zip";

app.use(express.static('public'));


app.get('/check_update', function (req, res) {

   console.log('req.body ' )
    // 输出 JSON 格式
   response = {
       first_name: "wcy",
       last_name: "www"
   };
   console.log(response);
   res.end(JSON.stringify(response));
})

app.post('/check', function (req, res) {
   if (req.body) {
   		console.log(req.body)
   		if(req.body.version == "1.2.6"){
   			res.send({"status": 1, "message":"", "data": {"needUpdate": false}});
   		}else{
   			res.send({"status": 1, "message":"", "data": {"needUpdate": true, "patchUrl": patchPath, 
   				"pathMD5": patchMd5, "entireUrl":v126Path, "entireMD5":v126MD5, "newVersion": "1.2.6"}});
   		}
        
    }else{
    	console.log("fail")
    	res.send({"status": 0, "message": "post data error"})
    }
})




var server = app.listen(9091, function () {

  var host = server.address().address
  var port = server.address().port

  console.log("应用实例，访问地址为 http://%s:%s", host, port)

})

