import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule, MatCheckboxModule, MatFormFieldModule,MatRippleModule
  ,MatInputModule, MatTabsModule} from '@angular/material';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { MatCardModule,MatIconModule } from '@angular/material';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatSidenavModule} from '@angular/material/sidenav'
import {MatMenuModule} from '@angular/material/menu';
import {MatTableModule} from '@angular/material/table';
import {MatStepperModule} from '@angular/material/stepper';
import {MatDialogModule} from '@angular/material/dialog';
import {MatSelectModule} from '@angular/material/select';
import {MatTooltipModule} from '@angular/material/tooltip';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { UserService } from './users.service';
import {UploadFileService} from './upload/upload-file.service';
import { HttpModule } from '@angular/http';
import {HttpClientModule} from '@angular/common/http';
import { HomePageComponent } from './home-page/home-page.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AppRoutingModule } from './/app-routing.module';
import { FormUploadComponent } from './upload/form-upload/form-upload.component';
import { ListUploadComponent } from './upload/list-upload/list-upload.component';
import { DetailsUploadComponent } from './upload/details-upload/details-upload.component';
import { UploadFileComponent } from './upload-file/upload-file.component';
import {RecordService} from './records.service';
import {FilesUploadedService} from '../app/files.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomePageComponent,
    NavbarComponent,
    DetailsUploadComponent,
    FormUploadComponent,
    ListUploadComponent,
    UploadFileComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatButtonModule, 
    MatCheckboxModule,
    ReactiveFormsModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule, MatTabsModule,
    HttpModule,
    AppRoutingModule,
    HttpClientModule,
    MatToolbarModule,
    MatIconModule,MatDialogModule,MatSelectModule,
    MatSidenavModule,MatMenuModule,MatTableModule,MatProgressSpinnerModule,MatStepperModule,
    MatTooltipModule
  ],
  providers: [UserService,UploadFileService,RecordService,FilesUploadedService],
  bootstrap: [AppComponent]
})
export class AppModule { }
