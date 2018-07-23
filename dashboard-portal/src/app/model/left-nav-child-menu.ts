export class LeftNavChildMenu {

    private _label:string = null;

    private _field:string = null;
  
    private _selected:boolean = false;
 
    constructor() {}

    public get label() : string {
        return this._label ;
    }
 
    public set label (value :string) {
        this._label = value;
    }

    public get field() : string {
        return this._field ;
    }
 
    public set field ( value : string) {
        this._field = value;
    }

    public get selected() : boolean {
        return this._selected;
    }
 
    public set selected ( value : boolean) {
        this._selected = value;
    }
}
