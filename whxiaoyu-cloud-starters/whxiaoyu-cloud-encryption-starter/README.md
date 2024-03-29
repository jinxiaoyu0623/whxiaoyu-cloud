公钥私钥对生成地址
http://www.metools.info/code/c80.html

1 使用SHA256加密参数可以通过以下步骤实现：

将所有参数存储到一个对象中，例如：
let params = {
  name: "Alice",
  age: 30,
  email: "alice@example.com"
};

2 将对象中的参数按照字母顺序进行排序，可以使用以下代码：

let sortedParams = {};
Object.keys(params).sort().forEach(function(key) {
  sortedParams[key] = params[key];
}); 

3 将排序后的参数拼接成一个字符串，例如：

let paramString = "";
for (let key in sortedParams) {
  paramString += key + sortedParams[key];
}

4 对拼接后的字符串进行SHA256哈希计算。可以使用JavaScript中的CryptoJS库实现。
首先需要引入CryptoJS库，可以通过以下方式实现

<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.0.0/crypto-js.min.js"></script>

5 使用以下代码对拼接后的字符串进行SHA256哈希计算：

let hash = CryptoJS.SHA256(paramString);
let signature = hash.toString(CryptoJS.enc.Hex);


6 如何使用非对称性SHA256加密 

1）读取公钥文件内容并解析为CryptoJS格式的密钥对象，例如：
let publicKeyStr = "-----BEGIN PUBLIC KEY-----\nMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+NUVbY...YsdvCgWJFi8eLQIDAQAB\n-----END PUBLIC KEY-----\n";
let publicKey = CryptoJS.KEYUTIL.getKey(publicKeyStr);

2）需要加密的数据转换为字节数组，例如
let data = "Hello World!";
let dataBytes = CryptoJS.enc.Utf8.parse(data);

3）使用CryptoJS库的SHA256函数计算数据的哈希值，例如
let hash = CryptoJS.SHA256(dataBytes);
let hashHex = hash.toString(CryptoJS.enc.Hex);

4）使用公钥对哈希值进行加密，例如：
let encrypted = publicKey.encrypt(hashHex);
let encryptedHex = CryptoJS.enc.Hex.stringify(CryptoJS.enc.Base64.parse(encrypted));


App解密

KeyFactory keyFactory = KeyFactory.getInstance("RSA");
PemReader reader = new PemReader(new FileReader("private.key"));
PemObject pemObject = reader.readPemObject();
byte[] privateKeyBytes = pemObject.getContent();
PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKeyBytes);
PrivateKey privateKey = keyFactory.generatePrivate(spec);

Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
cipher.init(Cipher.DECRYPT_MODE, privateKey);

String data = "vzgqTn27btnfOjNYAsoL0wRf2x55V1n66UAww0KJb/z7NRoozDbuF2RpWf3snB423RV+SD/Ohl0srX3PCx0szdT38G3zjRL8FTOQMPkl3w3ghQDGly7zMxQCemfCCm8JHygg+DDyJ7P8OhbkoMrB72nxnd4dFb7VykeV8RAGOn2EAOUpsLrpLCFhVqOhsZ9Z6KH51ahINGgOmBhQ6NK7Y+RZPdCZSTjVoNHEan6GSlVA7NW5o20xYlv9XHxOlqjOE+69IgcQBgwUfw0XX+dBaMsb90rc7aUmFxedRD7Pzpg2TG7KTMGdiwZ+TAaUubdgtLoOJU+B/5ZYPK0BBxo/1w==";
byte[] base64Bytes = Base64.getDecoder().decode(data);
byte[] decryptedData = cipher.doFinal(base64Bytes);
String str = new String(decryptedData, StandardCharsets.UTF_8);
System.out.println("解密后的数据:" + str); //输入结果为: hell0 world!


后端使用实例

注解：
@Decrypt 解密 用户参数
@Encrypt 加密 用于方法，返回值类型必须是内部统一返回类 ResponseResult
@Sign 签名验证 用于方法

代码实例

//返回数据加密 客户端需要私钥才能解密
@Encrypt
@GetMapping("/api")
public ResponseResult<String> api() {
    return ResponseResult.ok("hello world!");
}


//签名验证 
//并将参数进行排序后重新组合使用sha265加密生成sign值
//sign生成规则如下 请求参数+timestamp+nonce -> 排序 -> sha256加密生成sign
//客户端需要将sign,timestamp,nonce参数放入header中.header参数要加x-前缀区分第三方；x-sign，x-timestamp,x-nonce
@Sign
@GetMapping("sign")
public ResponseResult<String> sign(HttpServletRequest request) {
    return ResponseResult.ok();
}

//解密参数
//客户端必须使用颁发的公钥对参数进行加密后再用base64数据格式传输给服务端
@GetMapping("/test")
public ResponseResult<String> test(@Decrypt String data) {
    return ResponseResult.ok(data);
}



