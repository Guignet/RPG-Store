import { Component, OnInit } from '@angular/core';
import {Tag} from "../../models/tag";
import {Account} from "../../models/account";
import {FormBuilder, FormGroup} from "@angular/forms";
import {TagService} from "../../services/tag-service";
import {Location} from "@angular/common";
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";
import {CreateTag} from "../../models/create-tag";
import {LoginInfo} from "../../models/login-info";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  submitted: boolean = false;

  account?: Account;
  createAccountForm: FormGroup = this.fb.group({
    username: '',
    password: ''
  });
  constructor(private authService: AuthService,
              private fb: FormBuilder,
              private location: Location,
              private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.submitted = true;

    if (this.createAccountForm.invalid) {
      return;
    }

    let log: LoginInfo = {
      username: this.createAccountForm.value.username,
      password: this.createAccountForm.value.password
    };

    var retlog = this.authService.login(log)
      .subscribe({
        next: ok => {
        }
      });
    // var acc = this.authService.getAccount(retlog.id);
    this.router.navigate(['/products']);
  }
  get f() {
    return this.createAccountForm.controls;
  }

  back(): void {
    this.location.back()
  }

}
