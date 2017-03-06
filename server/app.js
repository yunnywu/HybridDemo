//app.js

//express_demo.js 文件
var express = require('express');
var bodyParser = require('body-parser');
var app = express();
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));

const patchMd5 = "998a9860a41bbb809feafb774f40f602";
//const patchPath = "http://192.168.31.199:9091/patch/125_to_126.zip";
const patchPath = "http://10.21.18.49:9091/patch/res_1.0.0_to_1.0.1_998a9860a41bbb809feafb774f40f602.zip";
const v101MD5 = "629b18b6b84824f2913994e1506312b2";
//const v101Path = "http://192.168.31.199:9091/patch/res_1.0.1_958ee9a3bdac34c2a78acd6ef04747b.zip";
const v101Path = "http://10.21.18.49:9091/patch/res_1.0.1_629b18b6b84824f2913994e1506312b2.zip";

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
   		if(req.body.version == "1.0.1"){
   			res.send({"status": 1, "message":"", "data": {"needUpdate": false}});
   		}else{
   			res.send({"status": 1, "message":"", "data": {"needUpdate": true, "patchUrl": patchPath, 
   				"pathMD5": patchMd5, "entireUrl":v101Path, "entireMD5":v101MD5, "newVersion": "1.0.1"}});
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

