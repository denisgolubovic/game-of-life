import { Component, OnInit, Input } from '@angular/core';
import { Cell } from '../models/cell.model';

@Component({
  selector: 'app-cell',
  template: `<div class="cell" [ngClass]="{'alive': cellData.alive}"></div>`,
  styleUrls: ['./cell.component.css']
})
export class CellComponent implements OnInit {
  @Input() cellData!: Cell;

  constructor() { }

  ngOnInit(): void {
  }
}
