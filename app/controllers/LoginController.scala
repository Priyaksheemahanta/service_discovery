package controllers

import com.google.common.hash.Hashing.sha512
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}

import java.security.MessageDigest
import javax.inject.Inject

class LoginController  @Inject()(cc: ControllerComponents) extends AbstractController(cc)  {
  def login = Action(parse.json){
    req=>
      val requestBody= req.body
      val loginId = requestBody.\("email").as[String]
      val password = requestBody.\("password").as[String]
      if(loginId=="test@test.com" && password=="admin"){
        val token= hashing(loginId)
       Ok(Json.obj("msg"->"successful","token"->token))
      }else{
        Unauthorized(Json.obj("msg"->"unsuccessful"))
      }
  }

  def hashing(input: String):String={
    def sha1(input1: String): String = {
      val crypt = MessageDigest.getInstance("SHA-1")
      crypt.reset()
      crypt.update(input1.getBytes("UTF-8"))
      bytes2hex(crypt.digest)
    }
    def bytes2hex(bytes: Array[Byte]): String = bytes.map("%02x" format _).mkString
    sha1(input)
  }



}
