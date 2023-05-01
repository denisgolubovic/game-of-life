export class Cell {
  x: number;
  y: number;
  alive: boolean;

  constructor(x: number, y: number, alive: boolean) {
    this.x = x;
    this.y = y;
    this.alive = alive;
  }
}
