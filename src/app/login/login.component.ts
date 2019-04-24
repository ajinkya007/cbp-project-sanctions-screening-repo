import { Component, OnInit } from '@angular/core';
import {FormControl, Validators, NgForm} from '@angular/forms';

import {UserService} from '../users.service';
import { Users } from '../users';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [UserService]
})
export class LoginComponent implements OnInit {

  emailLogin = new FormControl('', [Validators.required, Validators.email]);
  passwordLogin = new FormControl('',[Validators.required,Validators.required]);

  emailSignUp = new FormControl('',[Validators.required, Validators.email]);
  passwordSignUp = new FormControl('', [Validators.required, Validators.required]);
  userNameSignUp = new FormControl('',[Validators.required,Validators.required]);

  emailId:string;
  users:Users[];

  status:string;
  flag:boolean = false;

  newUser: Users = new Users();

  constructor(private userService : UserService) {
   }

  ngOnInit() {
    this.flag = false;
    console.log('In ngOninit'+this.flag);
    this.getUsers();
  }

  getErrorMessage() {

    if(this.emailLogin.touched){
    return this.emailLogin.hasError('required') ? 'You must enter a value' :
        this.emailLogin.hasError('emailLogin') ? 'Not a valid email' :
            '';
          }
    if(this.emailSignUp.touched){
      return this.emailSignUp.hasError('required') ? 'You must enter a value' :
      this.emailSignUp.hasError('emailSignUp') ? 'Not a valid email' : '';
    }   

    if(this.userNameSignUp.touched){
      return this.userNameSignUp.hasError('required') ? 'You must enter your username' :
      this.userNameSignUp.hasError('userNameSignUp') ? 'Not a valid Username' : '';
    }
  }

  getUsers():void{
    this.userService.getUsers()
    .then(users=>this.users = users);
  }

  // getStatus(){
  //   if(this.emailLogin.value!=null && this.passwordLogin.value!=null){}
  // }
  checkUser(){

    if(this.emailLogin.value == null && this.passwordLogin.value == null)
    return false;

    for(var i in this.users)
    {
      if(this.users[i].emailAddress!=this.emailLogin.value || this.users[i].password!=this.passwordLogin.value){
       this.flag = true;   // Either email address or password does not match       
      }
      else{
        this.flag=false;    // He is a valid user
        break;
      }
    }

    if(this.flag==true && this.emailLogin.value!=null && this.passwordLogin.value!=null){
      console.log('Not a valid user');
      return false;
    }
    else{
      console.log('Valid User');
      console.log(this.passwordLogin.value);
      return true;

    }
  }

  getPasswordErrorMessage()
  {

    if(this.passwordLogin.touched){
    return this.passwordLogin.hasError('required') ? 'You must enter valid password' :
    this.passwordLogin.hasError('passwordLogin') ? 'Not a valid password' :
            '';
    }

    if(this.passwordSignUp.touched){
      return this.passwordSignUp.hasError('required') ? 'You must enter valid password' :
    this.passwordSignUp.hasError('passwordSignUp') ? 'Not a valid password' :
            '';
    }
  }

  createUser(UserForm: NgForm) : void{
    this.newUser.userName = this.userNameSignUp.value;
    this.newUser.emailAddress = this.emailSignUp.value;
    this.newUser.password = this.passwordSignUp.value;

    this.userService.createUser(this.newUser)
    .then(createUser => {
      UserForm.reset();
      this.newUser = new Users();
      this.users.unshift(createUser)
    });
  }

}