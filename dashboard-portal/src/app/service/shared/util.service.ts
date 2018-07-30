import { Injectable } from '@angular/core';

import { Subscription } from "rxjs";
import { TimerObservable } from "rxjs/observable/TimerObservable";

import { Observable } from 'rxjs';
import { of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UtilService {

  constructor() { }

  //https://stackoverflow.com/questions/35813310/how-to-create-timer-in-angular2
}
