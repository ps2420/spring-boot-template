
import { LeftNavChildMenu } from '../model/left-nav-child-menu';

export class LeftNavMenu {

    private _label: string;

    private _field: string;
  
    private _selected: boolean = false;

    private _childMenuItems: LeftNavChildMenu[];
 
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

    public get childMenuItems() : LeftNavChildMenu[] {
        return this._childMenuItems;
    }
 
    public set childMenuItems (value : LeftNavChildMenu []) {
        this._childMenuItems = value;
    }
}
