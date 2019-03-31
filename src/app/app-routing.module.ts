import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { componentFactoryName } from '@angular/compiler';
import {HomePageComponent} from './home-page/home-page.component';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { FormUploadComponent } from './upload/form-upload/form-upload.component';

const routes: Routes = [
  {path : 'login/homepage', component:HomePageComponent},
  {path : 'login' , component:LoginComponent},
  {path : 'login/homepage/fileupload' , component:FormUploadComponent},
  {path : '' , component:LoginComponent},
  {path : '**' , component : HomePageComponent}

];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule { }
