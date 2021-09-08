package controllers

import com.google.common.hash.Hashing.md5
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.Inject

class LoginController  @Inject()(cc: ControllerComponents) extends AbstractController(cc)  {
  def login = Action(parse.json){
    req=>
      val requestBody= req.body
      val loginId = requestBody.\("email").as[String]
      val password = requestBody.\("password").as[String]
      if(loginId=="test@test.com" && password=="admin"){
        md5()
       Ok(Json.obj("msg"->"successful"))
      }else{
        Unauthorized(Json.obj("msg"->"unsuccessful"))
      }
  }



}
