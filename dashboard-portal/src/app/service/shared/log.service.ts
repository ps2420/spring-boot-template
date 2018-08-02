import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LogService {

	constructor() { }

	logJson(value: any): void {
	    console.log("json ==> " + JSON.stringify(value));
	}

	logJsonMsg(value: string, data: any): void {
	    console.log(value + ":" + JSON.stringify(data));
	}

	info(value: string): void {
	    console.log("info ==> " + value)
	}

	error(value: string): void {
	    console.log("error ==> " + value)
	}

	log(value: string): void {
	    console.log("log ==> " + value)
	}

	debug(value: string): void {
	    console.log("debug ==> " + value)
	}
}

