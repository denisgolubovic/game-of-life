import {Component, OnInit} from '@angular/core';
import {Cell} from "../models/cell.model";

@Component({
  selector: 'app-game-of-life',
  template: `
    <div class="game-container" *ngIf="grid">
      <div class="game-grid">
        <div *ngFor="let row of grid" class="grid-row">
          <app-cell *ngFor="let cell of row" [cellData]="cell"></app-cell>
        </div>
      </div>
      <button class="tick-button" (click)="next()">Next tick</button>
    </div>

  `,
  styleUrls: ['./game-of-life.component.css']
})
export class GameOfLifeComponent implements OnInit {
  grid: Cell[][] | undefined;

  constructor() {  }

  async ngOnInit() {
    await this.initializeBoard(10, 10, 0.35);
  }

  async initializeBoard( columns: number, rows: number, aliveProbability: number) {
    const response = await fetch('/api/gameoflife/initialize', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
    body: JSON.stringify({columns: columns, rows: rows, aliveProbability: aliveProbability})
    });
    this.grid = await response.json();
  }

  async next() {
    const response = await fetch('/api/gameoflife/next', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({grid: this.grid})
    });

    this.grid= await response.json();
  }
}
